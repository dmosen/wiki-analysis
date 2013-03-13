/*
 * JGraLab - The Java Graph Laboratory
 * 
 * Copyright (C) 2006-2011 Institute for Software Technology
 *                         University of Koblenz-Landau, Germany
 *                         ist@uni-koblenz.de
 * 
 * For bug reports, documentation and further information, visit
 * 
 *                         http://jgralab.uni-koblenz.de
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 3 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses>.
 * 
 * Additional permission under GNU GPL version 3 section 7
 * 
 * If you modify this Program, or any covered work, by linking or combining
 * it with Eclipse (or a modified version of that program or an Eclipse
 * plugin), containing parts covered by the terms of the Eclipse Public
 * License (EPL), the licensors of this Program grant you additional
 * permission to convey the resulting work.  Corresponding Source for a
 * non-source form of such a combination shall include the source code for
 * the parts of JGraLab used as well as that of the covered work.
 */
package de.uni_koblenz.jgralabtest.impl.trans;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ConcurrentModificationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.GraphException;
import de.uni_koblenz.jgralab.ImplementationType;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.impl.trans.AttributedElementIterable;
import de.uni_koblenz.jgralab.trans.CommitFailedException;
import de.uni_koblenz.jgralab.trans.Transaction;
import de.uni_koblenz.jgralabtest.schemas.motorwaymap.City;
import de.uni_koblenz.jgralabtest.schemas.motorwaymap.Motorway;
import de.uni_koblenz.jgralabtest.schemas.motorwaymap.MotorwayMap;
import de.uni_koblenz.jgralabtest.schemas.motorwaymap.MotorwayMapSchema;

//import junit.framework.JUnit4TestAdapter;

/**
 * Test cases for class AttributedElementIterableTest. Generalized
 * Iterable-Implementation for transaction concept.
 * 
 * @author Jose Monte(monte@uni-koblenz.de)
 */
public class AttributedElementIterableTest {
	private MotorwayMap motorwayMap;
	private Transaction readWriteTransaction1;
	private Transaction readWriteTransaction2;

	private static final int V = 1;
	private static final int E = 1;
	private static final int N = 10;

	@Before
	public void setUp() throws CommitFailedException {
		MotorwayMapSchema schema = MotorwayMapSchema.instance();
		motorwayMap = schema.createMotorwayMap(ImplementationType.TRANSACTION,
				null, V, E);
		motorwayMap.newTransaction();
		// city (v1) and motorway (v2) have 10 incidences
		// (<e1,e2,e3,e4,e5,e6,...,e10> and
		// <-e1,-e2,-e3,-e4,-e5,-e6,...,-e10> respectively)
		City city = motorwayMap.createCity();

		Motorway motorway = motorwayMap.createMotorway();
		for (int i = 0; i < N; i++) {
			motorwayMap.createExit(city, motorway);
		}
		for (int i = 0; i < N; i++) {
			city = motorwayMap.createCity();
			motorway = motorwayMap.createMotorway();
			motorwayMap.createExit(city, motorway);
		}
		motorwayMap.commit();

		readWriteTransaction1 = motorwayMap.newTransaction();
		readWriteTransaction2 = motorwayMap.newTransaction();
	}

	@After
	public void tearDown() {
		motorwayMap = null;
	}

	/**
	 * Iterate through Vseq without changing V. Should work without Exception.
	 */
	@Test
	public void testVertexIterableNoChange() {
		try {
			motorwayMap.setCurrentTransaction(readWriteTransaction1);
			Iterable<Vertex> vertices = motorwayMap.vertices();
			assertTrue(vertices instanceof AttributedElementIterable<?>);
			for (Vertex vertex : vertices) {
				vertex.getId();
			}
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Iterate through Vseq and deleting the last vertex. Exception expected.
	 */
	@Test
	public void testVertexIterableChange() {
		try {
			motorwayMap.setCurrentTransaction(readWriteTransaction1);
			Iterable<Vertex> vertices = motorwayMap.vertices();
			assertTrue(vertices instanceof AttributedElementIterable<?>);
			for (Vertex vertex : vertices) {
				motorwayMap.getVertex(N).delete();
				vertex.getId();
			}
			fail();
		} catch (ConcurrentModificationException e) {
			System.out.println("\n- testVertexIterableChange -");
			System.out.println("##########################");
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}

	/**
	 * Iterable-instance created within <code>readWriteTransaction1</code> is
	 * used within <code>readWriteTransaction2</code>. Because
	 * <code>readWriteTransaction2</code> is an invalid transaction for the
	 * Iterable-instance, an exception should be thrown.
	 */
	@Test
	public void testVertexIterableInvalidTransaction() {
		try {
			motorwayMap.setCurrentTransaction(readWriteTransaction1);
			Iterable<Vertex> vertices = motorwayMap.vertices();
			assertTrue(vertices instanceof AttributedElementIterable<?>);
			motorwayMap.setCurrentTransaction(readWriteTransaction2);
			for (Vertex vertex : vertices) {
				fail();
				vertex.delete();
			}
		} catch (GraphException e) {
			System.out.println("\n- testVertexIterableInvalidTransaction -");
			System.out.println("##########################");
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}

	/**
	 * Iterate through Eseq without changing E. Should work without exception.
	 */
	@Test
	public void testEdgeIterableNoChange() {
		try {
			motorwayMap.setCurrentTransaction(readWriteTransaction1);
			Iterable<Edge> edges = motorwayMap.edges();
			assertTrue(edges instanceof AttributedElementIterable<?>);
			for (Edge edge : edges) {
				edge.getId();
			}
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Iterate through Eseq and deleting the last edge. Exception expected.
	 */
	@Test
	public void testEdgeIterableChange() {
		try {
			motorwayMap.setCurrentTransaction(readWriteTransaction1);
			Iterable<Edge> edges = motorwayMap.edges();
			assertTrue(edges instanceof AttributedElementIterable<?>);
			for (Edge edge : edges) {
				motorwayMap.getEdge(N).delete();
				edge.getId();
			}
			fail();
		} catch (ConcurrentModificationException e) {
			System.out.println("\n- testEdgeIterableChange -");
			System.out.println("##########################");
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}

	/**
	 * Iterable-instance created within <code>readWriteTransaction1</code> is
	 * used within <code>readWriteTransaction2</code>. Because
	 * <code>readWriteTransaction2</code> is an invalid transaction for the
	 * Iterable-instance, an exception should be thrown.
	 */
	@Test
	public void testEdgeIterableInvalidTransaction() {
		try {
			motorwayMap.setCurrentTransaction(readWriteTransaction1);
			Iterable<Edge> edges = motorwayMap.edges();
			assertTrue(edges instanceof AttributedElementIterable<?>);
			motorwayMap.setCurrentTransaction(readWriteTransaction2);
			for (Edge edge : edges) {
				fail();
				edge.delete();
			}
			fail();
		} catch (GraphException e) {
			System.out.println("\n- testEdgeIterableInvalidTransaction -");
			System.out.println("##########################");
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}

	/**
	 * Iterate through Iseq(v1) without changing E. Should work without
	 * exception.
	 */
	@Test
	public void testIncidenceIterableNoChange() {
		try {
			motorwayMap.setCurrentTransaction(readWriteTransaction1);
			Iterable<Edge> incidences = motorwayMap.getVertex(1).incidences();
			assertTrue(incidences instanceof AttributedElementIterable<?>);
			for (Edge edge : incidences) {
				edge.getId();
			}
		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Iterate through Iseq(v1) and deleting the last incidence. Exception
	 * expected.
	 */
	@Test
	public void testIncidenceIterableChange() {
		try {
			motorwayMap.setCurrentTransaction(readWriteTransaction1);
			Vertex v1 = motorwayMap.getVertex(1);
			Iterable<Edge> incidences = v1.incidences();
			assertTrue(incidences instanceof AttributedElementIterable<?>);
			for (Edge edge : incidences) {
				v1.getLastIncidence().delete();
				edge.getId();
			}
			fail();
		} catch (ConcurrentModificationException e) {
			System.out.println("\n- testIncidenceIterableChange -");
			System.out.println("##########################");
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}

	/**
	 * Iterable-instance created within <code>readWriteTransaction1</code> is
	 * used within <code>readWriteTransaction2</code>. Because
	 * <code>readWriteTransaction2</code> is an invalid transaction for the
	 * Iterable-instance, an exception should be thrown.
	 */
	@Test
	public void testIncidenceIterableInvalidTransaction() {
		try {
			motorwayMap.setCurrentTransaction(readWriteTransaction1);
			Vertex v1 = motorwayMap.getVertex(1);
			Iterable<Edge> incidences = v1.incidences();
			motorwayMap.setCurrentTransaction(readWriteTransaction2);
			assertTrue(incidences instanceof AttributedElementIterable<?>);
			for (Edge edge : incidences) {
				fail();
				edge.delete();
			}
			fail();
		} catch (GraphException e) {
			System.out.println("\n- testIncidenceIterableInvalidTransaction -");
			System.out.println("##########################");
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}

	/**
	 * 
	 * @return
	 */
	/*
	 * public static junit.framework.Test suite() { return new
	 * JUnit4TestAdapter(AttributedElementIterableTest.class); }
	 */
}
