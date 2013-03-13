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

package de.uni_koblenz.jgralabtest.instancetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.pcollections.PMap;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.ImplementationType;
import de.uni_koblenz.jgralab.JGraLab;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.RandomIdGenerator;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.schema.EdgeClass;
import de.uni_koblenz.jgralab.schema.GraphClass;
import de.uni_koblenz.jgralab.schema.Schema;
import de.uni_koblenz.jgralab.schema.VertexClass;
import de.uni_koblenz.jgralab.trans.CommitFailedException;
import de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode;
import de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode;
import de.uni_koblenz.jgralabtest.schemas.vertextest.Link;
import de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack;
import de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink;
import de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode;
import de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode;
import de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph;
import de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestSchema;

@RunWith(Parameterized.class)
public class VertexTest extends InstanceTest {

	private static final int ITERATIONS = 25;

	public VertexTest(ImplementationType implementationType, String dbURL) {
		super(implementationType, dbURL);
	}

	@Parameters
	public static Collection<Object[]> configure() {
		return getParameters();
	}

	private VertexTestGraph g;
	private Random rand;

	/**
	 * Creates a new graph for each test.
	 */
	@Before
	public void setUp() {
		if (implementationType == ImplementationType.DATABASE) {
			dbHandler.connectToDatabase();
			dbHandler.loadVertexTestSchemaIntoGraphDatabase();
		}
		g = createNewGraph();
		rand = new Random(System.currentTimeMillis());
	}

	@After
	public void tearDown() {
		if (implementationType == ImplementationType.DATABASE) {
			cleanAndCloseGraphDatabase();
		}
	}

	private void cleanAndCloseGraphDatabase() {
		// dbHandler.cleanDatabaseOfTestGraph("VertexTest");
		// dbHandler.cleanDatabaseOfTestGraph("anotherGraph");
		// for (int i = 0; i < ITERATIONS; i++) {
		// dbHandler.cleanDatabaseOfTestGraph("VertexTest" + i);
		// }
		// for (String id : graphIdsInUse) {
		// dbHandler.cleanDatabaseOfTestGraph(id);
		// }
		// // this.cleanDatabaseOfTestSchema(g.getSchema());
		dbHandler.clearAllTables();
		dbHandler.closeGraphdatabase();
	}

	/*
	 * Test of the Interface Vertex
	 */

	// tests of the method isIncidenceListModified(long incidenceListVersion);
	/**
	 * Tests if the incidenceList wasn't modified.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void isIncidenceListModifiedTest0() throws CommitFailedException {
		createTransaction(g);
		AbstractSuperNode asn = g.createSubNode();
		SuperNode sn = g.createSuperNode();
		DoubleSubNode dsn = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		long asnIncidenceListVersion = asn.getIncidenceListVersion();
		long snIncidenceListVersion = sn.getIncidenceListVersion();
		long dsnIncidenceListVersion = dsn.getIncidenceListVersion();
		assertFalse(asn.isIncidenceListModified(asnIncidenceListVersion));
		assertFalse(sn.isIncidenceListModified(snIncidenceListVersion));
		assertFalse(dsn.isIncidenceListModified(dsnIncidenceListVersion));
		commit(g);
	}

	/**
	 * If you create and delete edges, only the incidenceLists of the involved
	 * nodes may have been modified.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void isIncidenceListModifiedTest1() throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		long[] versions = new long[3];
		nodes[0] = g.createSubNode();
		versions[0] = nodes[0].getIncidenceListVersion();
		nodes[1] = g.createDoubleSubNode();
		versions[1] = nodes[1].getIncidenceListVersion();
		nodes[2] = g.createSuperNode();
		versions[2] = nodes[2].getIncidenceListVersion();
		commit(g);
		for (int i = 0; i < ITERATIONS; i++) {
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			// create a new edge
			createTransaction(g);
			Link sl = g.createLink((AbstractSuperNode) nodes[start],
					(SuperNode) nodes[end]);
			commit(g);
			createReadOnlyTransaction(g);
			assertTrue(nodes[start].isIncidenceListModified(versions[start]));
			assertTrue(nodes[end].isIncidenceListModified(versions[end]));
			if (start != end) {
				assertFalse(nodes[6 - (start + 1) - (end + 1) - 1]
						.isIncidenceListModified(versions[6 - (start + 1)
								- (end + 1) - 1]));
			} else {
				for (int j = 0; j < 3; j++) {
					if (j != start) {
						assertFalse(nodes[j]
								.isIncidenceListModified(versions[j]));
					}
				}
			}
			// update of versions
			versions[0] = nodes[0].getIncidenceListVersion();
			versions[1] = nodes[1].getIncidenceListVersion();
			versions[2] = nodes[2].getIncidenceListVersion();
			commit(g);

			// delete an edge
			createTransaction(g);
			g.deleteEdge(sl);
			commit(g);

			createReadOnlyTransaction(g);
			assertTrue(nodes[start].isIncidenceListModified(versions[start]));
			assertTrue(nodes[end].isIncidenceListModified(versions[end]));
			if (start != end) {
				assertFalse(nodes[6 - (start + 1) - (end + 1) - 1]
						.isIncidenceListModified(versions[6 - (start + 1)
								- (end + 1) - 1]));
			} else {
				for (int j = 0; j < 3; j++) {
					if (j != start) {
						assertFalse(nodes[j]
								.isIncidenceListModified(versions[j]));
					}
				}
			}
			// update of versions
			versions[0] = nodes[0].getIncidenceListVersion();
			versions[1] = nodes[1].getIncidenceListVersion();
			versions[2] = nodes[2].getIncidenceListVersion();
			commit(g);
		}
	}

	// tests of the method getIncidenceListVersion()
	/**
	 * If you create and delete edges, only the incidenceListVersions of the
	 * involved nodes may have been increased.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getIncidenceListVersionTest0() throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		nodes[0] = g.createSubNode();
		nodes[1] = g.createDoubleSubNode();
		nodes[2] = g.createSuperNode();
		commit(g);
		long[] expectedVersions = new long[] { 0, 0, 0 };
		for (int i = 0; i < ITERATIONS; i++) {
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			// create a new edge
			createTransaction(g);
			Link sl = g.createLink((AbstractSuperNode) nodes[start],
					(SuperNode) nodes[end]);
			expectedVersions[start]++;
			expectedVersions[end]++;
			commit(g);
			createReadOnlyTransaction(g);
			assertEquals(expectedVersions[0],
					nodes[0].getIncidenceListVersion());
			assertEquals(expectedVersions[1],
					nodes[1].getIncidenceListVersion());
			assertEquals(expectedVersions[2],
					nodes[2].getIncidenceListVersion());
			commit(g);
			// delete an edge
			createTransaction(g);
			g.deleteEdge(sl);
			expectedVersions[start]++;
			expectedVersions[end]++;
			commit(g);
			createReadOnlyTransaction(g);
			assertEquals(expectedVersions[0],
					nodes[0].getIncidenceListVersion());
			assertEquals(expectedVersions[1],
					nodes[1].getIncidenceListVersion());
			assertEquals(expectedVersions[2],
					nodes[2].getIncidenceListVersion());
			commit(g);
		}
	}

	// tests of the method getDegree()

	/**
	 * A vertex with no connected incidences has to have a degree of 0.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTest0() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(0, v.getDegree());
		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTest1() throws CommitFailedException {
		createTransaction(g);
		SubNode subn = g.createSubNode();
		DoubleSubNode dsubn = g.createDoubleSubNode();
		DoubleSubNode dsubnWithout = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createLink(subn, supern);
		g.createLink(dsubn, dsubn);
		g.createLinkBack(supern, dsubn);
		g.createLinkBack(dsubn, subn);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(0, dsubnWithout.getDegree());
		assertEquals(2, subn.getDegree());
		assertEquals(2, supern.getDegree());
		assertEquals(4, dsubn.getDegree());
		commit(g);
	}

	/**
	 * Generates a number of edges and checks the correct degrees of the
	 * vertices. After that it deletes the edges and checks the degrees again.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTest2() throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		nodes[0] = g.createSubNode();
		nodes[1] = g.createDoubleSubNode();
		nodes[2] = g.createSuperNode();
		int[] expectedDegrees = new int[] { 0, 0, 0 };
		commit(g);
		// create new edges
		for (int i = 0; i < ITERATIONS; i++) {
			createTransaction(g);
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			g.createLink((AbstractSuperNode) nodes[start],
					(SuperNode) nodes[end]);
			expectedDegrees[start]++;
			expectedDegrees[end]++;
			commit(g);
			createReadOnlyTransaction(g);
			assertEquals(expectedDegrees[0], nodes[0].getDegree());
			assertEquals(expectedDegrees[1], nodes[1].getDegree());
			assertEquals(expectedDegrees[2], nodes[2].getDegree());
			commit(g);
		}
		HashMap<Vertex, Integer> vertices = new HashMap<Vertex, Integer>();
		vertices.put(nodes[0], expectedDegrees[0]);
		vertices.put(nodes[1], expectedDegrees[1]);
		vertices.put(nodes[2], expectedDegrees[2]);
		// delete the edges
		createTransaction(g);
		Link link = g.getFirstLink();
		commit(g);

		while (link != null) {
			createTransaction(g);
			Link nextLink = link.getNextLinkInGraph();

			Vertex start = link.getAlpha();
			vertices.put(start, vertices.get(start) - 1);
			Vertex end = link.getOmega();
			vertices.put(end, vertices.get(end) - 1);
			g.deleteEdge(link);
			commit(g);

			createReadOnlyTransaction(g);
			assertEquals(vertices.get(start).intValue(), start.getDegree());
			assertEquals(vertices.get(end).intValue(), end.getDegree());
			commit(g);
			link = nextLink;
		}
	}

	// tests of the method getDegree(EdgeDirection orientation)

	/**
	 * A vertex with no connected incidences has to have a degree of 0 for each
	 * EdgeDirection.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeDirection0() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(0, v.getDegree(EdgeDirection.IN));
		assertEquals(0, v.getDegree(EdgeDirection.OUT));
		assertEquals(0, v.getDegree(EdgeDirection.INOUT));
		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeDirection1() throws CommitFailedException {
		createTransaction(g);
		SubNode subn = g.createSubNode();
		DoubleSubNode dsubn = g.createDoubleSubNode();
		DoubleSubNode dsubnWithout = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createLink(subn, supern);
		g.createLink(dsubn, dsubn);
		g.createLinkBack(supern, dsubn);
		g.createLinkBack(dsubn, subn);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(0, dsubnWithout.getDegree(EdgeDirection.INOUT));
		assertEquals(0, dsubnWithout.getDegree(EdgeDirection.IN));
		assertEquals(0, dsubnWithout.getDegree(EdgeDirection.OUT));
		assertEquals(2, subn.getDegree(EdgeDirection.INOUT));
		assertEquals(1, subn.getDegree(EdgeDirection.IN));
		assertEquals(1, subn.getDegree(EdgeDirection.OUT));
		assertEquals(2, supern.getDegree(EdgeDirection.INOUT));
		assertEquals(1, supern.getDegree(EdgeDirection.IN));
		assertEquals(1, supern.getDegree(EdgeDirection.OUT));
		assertEquals(4, dsubn.getDegree(EdgeDirection.INOUT));
		assertEquals(2, dsubn.getDegree(EdgeDirection.IN));
		assertEquals(2, dsubn.getDegree(EdgeDirection.OUT));
		commit(g);
	}

	/**
	 * Generates a number of different edges and checks the correct degrees of
	 * the vertices considering the different EdgeDirections. After that it
	 * deletes the edges and checks the degrees again.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeDirection2() throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		nodes[0] = g.createSubNode();
		nodes[1] = g.createDoubleSubNode();
		nodes[2] = g.createSuperNode();
		int[] expectedInOut = new int[] { 0, 0, 0 };
		int[] expectedIn = new int[] { 0, 0, 0 };
		int[] expectedOut = new int[] { 0, 0, 0 };
		commit(g);
		// create new edges
		for (int i = 0; i < ITERATIONS; i++) {
			// decides which edge should be created
			int edge = rand.nextInt(2);
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			createTransaction(g);
			if (edge == 0) {
				// create a Link
				g.createLink((AbstractSuperNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedInOut[start]++;
				expectedOut[start]++;
				expectedInOut[end]++;
				expectedIn[end]++;
			} else {
				// create a LinkBack
				g.createLinkBack((SuperNode) nodes[end],
						(AbstractSuperNode) nodes[start]);
				expectedInOut[end]++;
				expectedOut[end]++;
				expectedInOut[start]++;
				expectedIn[start]++;
			}
			commit(g);
			createReadOnlyTransaction(g);
			assertEquals(expectedInOut[0],
					nodes[0].getDegree(EdgeDirection.INOUT));
			assertEquals(expectedInOut[1],
					nodes[1].getDegree(EdgeDirection.INOUT));
			assertEquals(expectedInOut[2],
					nodes[2].getDegree(EdgeDirection.INOUT));
			assertEquals(expectedIn[0], nodes[0].getDegree(EdgeDirection.IN));
			assertEquals(expectedIn[1], nodes[1].getDegree(EdgeDirection.IN));
			assertEquals(expectedIn[2], nodes[2].getDegree(EdgeDirection.IN));
			assertEquals(expectedOut[0], nodes[0].getDegree(EdgeDirection.OUT));
			assertEquals(expectedOut[1], nodes[1].getDegree(EdgeDirection.OUT));
			assertEquals(expectedOut[2], nodes[2].getDegree(EdgeDirection.OUT));
			commit(g);
		}
		// delete the edges
		HashMap<Vertex, Integer> numbers = new HashMap<Vertex, Integer>();
		numbers.put(nodes[0], 0);
		numbers.put(nodes[1], 1);
		numbers.put(nodes[2], 2);
		createTransaction(g);
		for (int i = g.getFirstEdge().getId(); i < g.getECount(); i++) {
			Edge e = g.getEdge(i);
			int start = numbers.get(e.getAlpha());
			int end = numbers.get(e.getOmega());
			expectedInOut[start]--;
			expectedInOut[end]--;
			expectedIn[end]--;
			expectedOut[start]--;
			g.deleteEdge(e);
			assertEquals(expectedInOut[0],
					nodes[0].getDegree(EdgeDirection.INOUT));
			assertEquals(expectedInOut[1],
					nodes[1].getDegree(EdgeDirection.INOUT));
			assertEquals(expectedInOut[2],
					nodes[2].getDegree(EdgeDirection.INOUT));
			assertEquals(expectedIn[0], nodes[0].getDegree(EdgeDirection.IN));
			assertEquals(expectedIn[1], nodes[1].getDegree(EdgeDirection.IN));
			assertEquals(expectedIn[2], nodes[2].getDegree(EdgeDirection.IN));
			assertEquals(expectedOut[0], nodes[0].getDegree(EdgeDirection.OUT));
			assertEquals(expectedOut[1], nodes[1].getDegree(EdgeDirection.OUT));
			assertEquals(expectedOut[2], nodes[2].getDegree(EdgeDirection.OUT));
		}
		commit(g);
	}

	// tests of the method getDegree(EdgeClass ec)

	/**
	 * A vertex with no connected incidences has to have a degree of 0 for each
	 * EdgeClass.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClass0() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForEdgeClass(v, 0, 0, 0);
		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClass1() throws CommitFailedException {
		createTransaction(g);
		SubNode subn = g.createSubNode();
		DoubleSubNode dsubn = g.createDoubleSubNode();
		DoubleSubNode dsubnWithout = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createLink(subn, supern);
		g.createLink(dsubn, dsubn);
		g.createLinkBack(supern, dsubn);
		g.createLinkBack(dsubn, subn);
		g.createSubLink(dsubn, supern);
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForEdgeClass(dsubnWithout, 0, 0, 0);
		testVertexForEdgeClass(subn, 1, 0, 1);
		testVertexForEdgeClass(dsubn, 3, 1, 2);
		testVertexForEdgeClass(supern, 2, 1, 1);
		commit(g);
	}

	/**
	 * Generates a number of different edges and checks the correct degrees of
	 * the vertices considering the different Edgeclasses. After that it deletes
	 * the edges and checks the degrees again.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClass2() throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		nodes[0] = g.createSubNode();
		nodes[1] = g.createDoubleSubNode();
		nodes[2] = g.createSuperNode();
		commit(g);
		int[] expectedLink = new int[] { 0, 0, 0 };
		int[] expectedLinkBack = new int[] { 0, 0, 0 };
		int[] expectedSubLink = new int[] { 0, 0, 0 };
		// create new edges
		for (int i = 0; i < ITERATIONS; i++) {
			// decides which edge should be created
			int edge = rand.nextInt(3);
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			createTransaction(g);
			if (edge == 0) {
				// create a Link
				g.createLink((AbstractSuperNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLink[start]++;
				expectedLink[end]++;
			} else if (edge == 1) {
				// create a LinkBack
				g.createLinkBack((SuperNode) nodes[end],
						(AbstractSuperNode) nodes[start]);
				expectedLinkBack[start]++;
				expectedLinkBack[end]++;
			} else {
				// create a SubLink
				start = 1;
				g.createSubLink((DoubleSubNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLink[start]++;
				expectedLink[end]++;
				expectedSubLink[start]++;
				expectedSubLink[end]++;
			}
			commit(g);
			createReadOnlyTransaction(g);
			testVertexForEdgeClass(nodes[0], expectedLink[0],
					expectedSubLink[0], expectedLinkBack[0]);
			testVertexForEdgeClass(nodes[1], expectedLink[1],
					expectedSubLink[1], expectedLinkBack[1]);
			testVertexForEdgeClass(nodes[2], expectedLink[2],
					expectedSubLink[2], expectedLinkBack[2]);
			commit(g);
		}
		// delete the edges
		HashMap<Vertex, Integer> numbers = new HashMap<Vertex, Integer>();
		numbers.put(nodes[0], 0);
		numbers.put(nodes[1], 1);
		numbers.put(nodes[2], 2);
		createTransaction(g);
		for (int i = g.getFirstEdge().getId(); i < g.getECount(); i++) {
			Edge e = g.getEdge(i);
			int start = numbers.get(e.getAlpha());
			int end = numbers.get(e.getOmega());
			if (e instanceof SubLink) {
				expectedLink[start]--;
				expectedLink[end]--;
				expectedSubLink[start]--;
				expectedSubLink[end]--;
			} else if (e instanceof LinkBack) {
				expectedLinkBack[start]--;
				expectedLinkBack[end]--;
			} else {
				expectedLink[start]--;
				expectedLink[end]--;
			}
			g.deleteEdge(e);
			testVertexForEdgeClass(nodes[0], expectedLink[0],
					expectedSubLink[0], expectedLinkBack[0]);
			testVertexForEdgeClass(nodes[1], expectedLink[1],
					expectedSubLink[1], expectedLinkBack[1]);
			testVertexForEdgeClass(nodes[2], expectedLink[2],
					expectedSubLink[2], expectedLinkBack[2]);
		}
		commit(g);
	}

	/**
	 * Tests if a Vertex has the expected degree considering the EdgeClass.
	 * 
	 * @param forNode
	 *            the Vertex, which degrees should be tested
	 * @param expectedLink
	 *            the expected number of incident Links
	 * @param expectedSubLink
	 *            the expected number of incident SubLinks
	 * @param expectedLinkBack
	 *            the expected number of incident LinkBacks
	 */
	private void testVertexForEdgeClass(Vertex forNode, int expectedLink,
			int expectedSubLink, int expectedLinkBack) {
		EdgeClass[] ecs = getEdgeClasses();
		assertEquals(expectedLink, forNode.getDegree(ecs[0]));
		assertEquals(expectedSubLink, forNode.getDegree(ecs[1]));
		assertEquals(expectedLinkBack, forNode.getDegree(ecs[2]));
	}

	/**
	 * Creates an array of the EdgeClasses.
	 * 
	 * @return {Link, SubLink, LinkBack}
	 */
	private EdgeClass[] getEdgeClasses() {
		EdgeClass[] ecs = new EdgeClass[3];
		List<EdgeClass> a = g.getGraphClass().getEdgeClasses();
		for (EdgeClass ec : a) {
			if (ec.getSimpleName().equals("Link")) {
				ecs[0] = ec;
			} else if (ec.getSimpleName().equals("SubLink")) {
				ecs[1] = ec;
			}
			if (ec.getSimpleName().equals("LinkBack")) {
				ecs[2] = ec;
			}
		}
		return ecs;
	}

	// tests of the method getDegree(Class<? extends Edge> ec)

	/**
	 * A vertex with no connected incidences has to have a degree of 0 for each
	 * Class extends Edge.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClass0() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(0, v.getDegree(Link.class));
		assertEquals(0, v.getDegree(SubLink.class));
		assertEquals(0, v.getDegree(LinkBack.class));
		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClass1() throws CommitFailedException {
		createTransaction(g);
		SubNode subn = g.createSubNode();
		DoubleSubNode dsubn = g.createDoubleSubNode();
		DoubleSubNode dsubnWithout = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createLink(subn, supern);
		g.createLink(dsubn, dsubn);
		g.createLinkBack(supern, dsubn);
		g.createLinkBack(dsubn, subn);
		g.createSubLink(dsubn, supern);
		commit(g);
		createTransaction(g);
		assertEquals(0, dsubnWithout.getDegree(Link.class));
		assertEquals(0, dsubnWithout.getDegree(LinkBack.class));
		assertEquals(0, dsubnWithout.getDegree(SubLink.class));
		assertEquals(1, subn.getDegree(Link.class));
		assertEquals(1, subn.getDegree(LinkBack.class));
		assertEquals(0, subn.getDegree(SubLink.class));
		assertEquals(3, dsubn.getDegree(Link.class));
		assertEquals(2, dsubn.getDegree(LinkBack.class));
		assertEquals(1, dsubn.getDegree(SubLink.class));
		assertEquals(2, supern.getDegree(Link.class));
		assertEquals(1, supern.getDegree(LinkBack.class));
		assertEquals(1, supern.getDegree(SubLink.class));
		commit(g);
	}

	/**
	 * Generates a number of different edges and checks the correct degrees of
	 * the vertices considering the different Classes. After that it deletes the
	 * edges and checks the degrees again.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClass2() throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		nodes[0] = g.createSubNode();
		nodes[1] = g.createDoubleSubNode();
		nodes[2] = g.createSuperNode();
		int[] expectedLink = new int[] { 0, 0, 0 };
		int[] expectedLinkBack = new int[] { 0, 0, 0 };
		int[] expectedSubLink = new int[] { 0, 0, 0 };
		commit(g);
		// create new edges
		for (int i = 0; i < ITERATIONS; i++) {
			// decides which edge should be created
			int edge = rand.nextInt(3);
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			createTransaction(g);
			if (edge == 0) {
				// create a Link
				g.createLink((AbstractSuperNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLink[start]++;
				expectedLink[end]++;
			} else if (edge == 1) {
				// create a LinkBack
				g.createLinkBack((SuperNode) nodes[end],
						(AbstractSuperNode) nodes[start]);
				expectedLinkBack[start]++;
				expectedLinkBack[end]++;
			} else {
				// create a SubLink
				start = 1;
				g.createSubLink((DoubleSubNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLink[start]++;
				expectedLink[end]++;
				expectedSubLink[start]++;
				expectedSubLink[end]++;
			}
			commit(g);
			createReadOnlyTransaction(g);
			assertEquals(expectedLink[0], nodes[0].getDegree(Link.class));
			assertEquals(expectedLink[1], nodes[1].getDegree(Link.class));
			assertEquals(expectedLink[2], nodes[2].getDegree(Link.class));
			assertEquals(expectedLinkBack[0],
					nodes[0].getDegree(LinkBack.class));
			assertEquals(expectedLinkBack[1],
					nodes[1].getDegree(LinkBack.class));
			assertEquals(expectedLinkBack[2],
					nodes[2].getDegree(LinkBack.class));
			assertEquals(expectedSubLink[0], nodes[0].getDegree(SubLink.class));
			assertEquals(expectedSubLink[1], nodes[1].getDegree(SubLink.class));
			assertEquals(expectedSubLink[2], nodes[2].getDegree(SubLink.class));
			commit(g);
		}
		// delete the edges
		HashMap<Vertex, Integer> numbers = new HashMap<Vertex, Integer>();
		numbers.put(nodes[0], 0);
		numbers.put(nodes[1], 1);
		numbers.put(nodes[2], 2);
		createTransaction(g);
		for (int i = g.getFirstEdge().getId(); i < g.getECount(); i++) {
			Edge e = g.getEdge(i);
			int start = numbers.get(e.getAlpha());
			int end = numbers.get(e.getOmega());
			if (e instanceof SubLink) {
				expectedLink[start]--;
				expectedLink[end]--;
				expectedSubLink[start]--;
				expectedSubLink[end]--;
			} else if (e instanceof LinkBack) {
				expectedLinkBack[start]--;
				expectedLinkBack[end]--;
			} else {
				expectedLink[start]--;
				expectedLink[end]--;
			}
			g.deleteEdge(e);
			assertEquals(expectedLink[0], nodes[0].getDegree(Link.class));
			assertEquals(expectedLink[1], nodes[1].getDegree(Link.class));
			assertEquals(expectedLink[2], nodes[2].getDegree(Link.class));
			assertEquals(expectedLinkBack[0],
					nodes[0].getDegree(LinkBack.class));
			assertEquals(expectedLinkBack[1],
					nodes[1].getDegree(LinkBack.class));
			assertEquals(expectedLinkBack[2],
					nodes[2].getDegree(LinkBack.class));
			assertEquals(expectedSubLink[0], nodes[0].getDegree(SubLink.class));
			assertEquals(expectedSubLink[1], nodes[1].getDegree(SubLink.class));
			assertEquals(expectedSubLink[2], nodes[2].getDegree(SubLink.class));
		}
		commit(g);
	}

	// tests of the method getDegree(EdgeClass ec, boolean noSubClasses)

	/**
	 * A vertex with no connected incidences has to have a degree of 0 for each
	 * EdgeClass.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassBoolean0() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		testVertexForEdgeClassSubClass(v, 0, 0, 0);
	}

	/**
	 * Checks the degrees in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassBoolean1() throws CommitFailedException {
		createTransaction(g);
		SubNode subn = g.createSubNode();
		DoubleSubNode dsubn = g.createDoubleSubNode();
		DoubleSubNode dsubnWithout = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createLink(subn, supern);
		g.createLink(dsubn, dsubn);
		g.createLinkBack(supern, dsubn);
		g.createLinkBack(dsubn, subn);
		g.createSubLink(dsubn, supern);
		commit(g);
		testVertexForEdgeClassSubClass(dsubnWithout, 0, 0, 0);
		testVertexForEdgeClassSubClass(subn, 1, 0, 1);
		testVertexForEdgeClassSubClass(dsubn, 3, 1, 2);
		testVertexForEdgeClassSubClass(supern, 2, 1, 1);
	}

	/**
	 * Checks the degrees in a manually build graph, which has only SubLinks.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassBoolean2() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode dsubn = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createSubLink(dsubn, supern);
		g.createSubLink(dsubn, dsubn);
		commit(g);
		testVertexForEdgeClassSubClass(dsubn, 3, 3, 0);
		testVertexForEdgeClassSubClass(supern, 1, 1, 0);
	}

	/**
	 * Generates a number of different edges and checks the correct degrees of
	 * the vertices considering the different Edgeclasses and their subclasses.
	 * After that it deletes the edges and checks the degrees again.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassBoolean3() throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		nodes[0] = g.createSubNode();
		nodes[1] = g.createDoubleSubNode();
		nodes[2] = g.createSuperNode();
		int[] expectedLink = new int[] { 0, 0, 0 };
		int[] expectedLinkBack = new int[] { 0, 0, 0 };
		int[] expectedSubLink = new int[] { 0, 0, 0 };
		commit(g);
		// create new edges
		for (int i = 0; i < ITERATIONS; i++) {
			// decides which edge should be created
			int edge = rand.nextInt(3);
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			createTransaction(g);
			if (edge == 0) {
				// create a Link
				g.createLink((AbstractSuperNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLink[start]++;
				expectedLink[end]++;
			} else if (edge == 1) {
				// create a LinkBack
				g.createLinkBack((SuperNode) nodes[end],
						(AbstractSuperNode) nodes[start]);
				expectedLinkBack[start]++;
				expectedLinkBack[end]++;
			} else {
				// create a SubLink
				start = 1;
				g.createSubLink((DoubleSubNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLink[start]++;
				expectedLink[end]++;
				expectedSubLink[start]++;
				expectedSubLink[end]++;
			}
			commit(g);
			testVertexForEdgeClassSubClass(nodes[0], expectedLink[0],
					expectedSubLink[0], expectedLinkBack[0]);
			testVertexForEdgeClassSubClass(nodes[1], expectedLink[1],
					expectedSubLink[1], expectedLinkBack[1]);
			testVertexForEdgeClassSubClass(nodes[2], expectedLink[2],
					expectedSubLink[2], expectedLinkBack[2]);
		}
		// delete the edges
		HashMap<Vertex, Integer> numbers = new HashMap<Vertex, Integer>();
		numbers.put(nodes[0], 0);
		numbers.put(nodes[1], 1);
		numbers.put(nodes[2], 2);
		createTransaction(g);
		for (int i = g.getFirstEdge().getId(); i < g.getECount(); i++) {
			Edge e = g.getEdge(i);
			int start = numbers.get(e.getAlpha());
			int end = numbers.get(e.getOmega());
			if (e instanceof SubLink) {
				expectedLink[start]--;
				expectedLink[end]--;
				expectedSubLink[start]--;
				expectedSubLink[end]--;
			} else if (e instanceof LinkBack) {
				expectedLinkBack[start]--;
				expectedLinkBack[end]--;
			} else {
				expectedLink[start]--;
				expectedLink[end]--;
			}
			g.deleteEdge(e);
			commit(g);
			testVertexForEdgeClassSubClass(nodes[0], expectedLink[0],
					expectedSubLink[0], expectedLinkBack[0]);
			testVertexForEdgeClassSubClass(nodes[1], expectedLink[1],
					expectedSubLink[1], expectedLinkBack[1]);
			testVertexForEdgeClassSubClass(nodes[2], expectedLink[2],
					expectedSubLink[2], expectedLinkBack[2]);
			createTransaction(g);
		}
		commit(g);
	}

	/**
	 * Tests if a Vertex has the expected degree considering the EdgeClass and
	 * SubClasses.
	 * 
	 * @param forNode
	 *            the Vertex, which degrees should be tested
	 * @param expectedLink
	 *            the expected number of incident Links
	 * @param expectedSubLink
	 *            the expected number of incident SubLinks
	 * @param expectedLinkBack
	 *            the expected number of incident LinkBacks
	 * @throws CommitFailedException
	 */
	private void testVertexForEdgeClassSubClass(Vertex forNode,
			int expectedLink, int expectedSubLink, int expectedLinkBack)
			throws CommitFailedException {
		createReadOnlyTransaction(g);
		EdgeClass[] ecs = getEdgeClasses();
		assertEquals(expectedLink, forNode.getDegree(ecs[0]));
		assertEquals(expectedSubLink, forNode.getDegree(ecs[1]));
		assertEquals(expectedLinkBack, forNode.getDegree(ecs[2]));
		commit(g);
	}

	// tests of the method getDegree(Class<? extends Edge> ec, boolean
	// noSubClasses)

	/**
	 * A vertex with no connected incidences has to have a degree of 0 for each
	 * Class extends Edge.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassBoolean0() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		testVertexForClassSubClass(v, 0, 0, 0);
	}

	/**
	 * Checks the degrees in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassBoolean1() throws CommitFailedException {
		createTransaction(g);
		SubNode subn = g.createSubNode();
		DoubleSubNode dsubn = g.createDoubleSubNode();
		DoubleSubNode dsubnWithout = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createLink(subn, supern);
		g.createLink(dsubn, dsubn);
		g.createLinkBack(supern, dsubn);
		g.createLinkBack(dsubn, subn);
		g.createSubLink(dsubn, supern);
		commit(g);
		testVertexForClassSubClass(dsubnWithout, 0, 0, 0);
		testVertexForClassSubClass(subn, 1, 0, 1);
		testVertexForClassSubClass(dsubn, 3, 1, 2);
	}

	/**
	 * Checks the degrees in a manually build graph, which has only SubLinks.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassBoolean2() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode dsubn = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createSubLink(dsubn, supern);
		g.createSubLink(dsubn, dsubn);
		commit(g);
		testVertexForClassSubClass(dsubn, 3, 3, 0);
		testVertexForClassSubClass(supern, 1, 1, 0);
	}

	/**
	 * Generates a number of different edges and checks the correct degrees of
	 * the vertices considering the different Classes and Subclasses. After that
	 * it deletes the edges and checks the degrees again.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassBoolean3() throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		nodes[0] = g.createSubNode();
		nodes[1] = g.createDoubleSubNode();
		nodes[2] = g.createSuperNode();
		int[] expectedLink = new int[] { 0, 0, 0 };
		int[] expectedLinkBack = new int[] { 0, 0, 0 };
		int[] expectedSubLink = new int[] { 0, 0, 0 };
		commit(g);
		// create new edges
		for (int i = 0; i < ITERATIONS; i++) {
			// decides which edge should be created
			int edge = rand.nextInt(3);
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			createTransaction(g);
			if (edge == 0) {
				// create a Link
				g.createLink((AbstractSuperNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLink[start]++;
				expectedLink[end]++;
			} else if (edge == 1) {
				// create a LinkBack
				g.createLinkBack((SuperNode) nodes[end],
						(AbstractSuperNode) nodes[start]);
				expectedLinkBack[start]++;
				expectedLinkBack[end]++;
			} else {
				// create a SubLink
				start = 1;
				g.createSubLink((DoubleSubNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLink[start]++;
				expectedLink[end]++;
				expectedSubLink[start]++;
				expectedSubLink[end]++;
			}
			commit(g);
			testVertexForEdgeClassSubClass(nodes[0], expectedLink[0],
					expectedSubLink[0], expectedLinkBack[0]);
			testVertexForEdgeClassSubClass(nodes[1], expectedLink[1],
					expectedSubLink[1], expectedLinkBack[1]);
			testVertexForEdgeClassSubClass(nodes[2], expectedLink[2],
					expectedSubLink[2], expectedLinkBack[2]);
		}
		// delete the edges
		HashMap<Vertex, Integer> numbers = new HashMap<Vertex, Integer>();
		numbers.put(nodes[0], 0);
		numbers.put(nodes[1], 1);
		numbers.put(nodes[2], 2);
		createTransaction(g);
		for (int i = g.getFirstEdge().getId(); i < g.getECount(); i++) {
			Edge e = g.getEdge(i);
			int start = numbers.get(e.getAlpha());
			int end = numbers.get(e.getOmega());
			if (e instanceof SubLink) {
				expectedLink[start]--;
				expectedLink[end]--;
				expectedSubLink[start]--;
				expectedSubLink[end]--;
			} else if (e instanceof LinkBack) {
				expectedLinkBack[start]--;
				expectedLinkBack[end]--;
			} else {
				expectedLink[start]--;
				expectedLink[end]--;
			}
			g.deleteEdge(e);
			commit(g);
			testVertexForEdgeClassSubClass(nodes[0], expectedLink[0],
					expectedSubLink[0], expectedLinkBack[0]);
			testVertexForEdgeClassSubClass(nodes[1], expectedLink[1],
					expectedSubLink[1], expectedLinkBack[1]);
			testVertexForEdgeClassSubClass(nodes[2], expectedLink[2],
					expectedSubLink[2], expectedLinkBack[2]);
			createTransaction(g);
		}
		commit(g);
	}

	/**
	 * Tests if a Vertex has the expected degree considering the Classes
	 * extending Edge and SubClasses.
	 * 
	 * @param forNode
	 *            the Vertex, which degrees should be tested
	 * @param expectedLink
	 *            the expected number of incident Links
	 * @param expectedSubLink
	 *            the expected number of incident SubLinks
	 * @param expectedLinkBack
	 *            the expected number of incident LinkBacks
	 * @throws CommitFailedException
	 */
	private void testVertexForClassSubClass(Vertex forNode, int expectedLink,
			int expectedSubLink, int expectedLinkBack)
			throws CommitFailedException {
		createReadOnlyTransaction(g);

		assertEquals(expectedLink, forNode.getDegree(Link.class));
		assertEquals(expectedSubLink, forNode.getDegree(SubLink.class));
		assertEquals(expectedLinkBack, forNode.getDegree(LinkBack.class));
		commit(g);
	}

	// tests of the method getDegree(EdgeClass ec, EdgeDirection orientation)

	/**
	 * A vertex with no connected incidences has to have a degree of 0 for each
	 * EdgeClass.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassEdgeDirection0()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		testVertexForEdgeClassEdgeDirection(v, 0, 0, 0, EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirection(v, 0, 0, 0, EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirection(v, 0, 0, 0, EdgeDirection.OUT);
	}

	/**
	 * Checks the degrees in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassEdgeDirection1()
			throws CommitFailedException {
		createTransaction(g);
		SubNode subn = g.createSubNode();
		DoubleSubNode dsubn = g.createDoubleSubNode();
		DoubleSubNode dsubnWithout = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createLink(subn, supern);
		g.createLink(dsubn, dsubn);
		g.createSubLink(dsubn, supern);
		g.createLinkBack(supern, dsubn);
		g.createLinkBack(dsubn, subn);
		commit(g);
		testVertexForEdgeClassEdgeDirection(dsubnWithout, 0, 0, 0,
				EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirection(dsubnWithout, 0, 0, 0,
				EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirection(dsubnWithout, 0, 0, 0,
				EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirection(subn, 1, 0, 1, EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirection(subn, 0, 0, 1, EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirection(subn, 1, 0, 0, EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirection(dsubn, 3, 1, 2, EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirection(dsubn, 1, 0, 1, EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirection(dsubn, 2, 1, 1, EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirection(supern, 2, 1, 1,
				EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirection(supern, 2, 1, 0, EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirection(supern, 0, 0, 1, EdgeDirection.OUT);
	}

	/**
	 * Checks the degrees in a manually build graph, which has only one
	 * LinkBack.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassEdgeDirection2()
			throws CommitFailedException {
		createTransaction(g);
		SuperNode dsubn = g.createSuperNode();
		AbstractSuperNode supern = g.createSubNode();
		g.createLinkBack(dsubn, supern);
		commit(g);
		testVertexForEdgeClassEdgeDirection(dsubn, 0, 0, 0, EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirection(dsubn, 0, 0, 1, EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirection(dsubn, 0, 0, 1, EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirection(supern, 0, 0, 1, EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirection(supern, 0, 0, 0, EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirection(supern, 0, 0, 1,
				EdgeDirection.INOUT);
	}

	/**
	 * Generates a number of different edges and checks the correct degrees of
	 * the vertices considering the different Edgeclasses and their
	 * EdgeDirections. After that it deletes the edges and checks the degrees
	 * again.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassEdgeDirection3()
			throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		nodes[0] = g.createSubNode();
		nodes[1] = g.createDoubleSubNode();
		nodes[2] = g.createSuperNode();
		int[] expectedLinkIn = new int[] { 0, 0, 0 };
		int[] expectedLinkOut = new int[] { 0, 0, 0 };
		int[] expectedLinkBackIn = new int[] { 0, 0, 0 };
		int[] expectedLinkBackOut = new int[] { 0, 0, 0 };
		int[] expectedSubLinkIn = new int[] { 0, 0, 0 };
		int[] expectedSubLinkOut = new int[] { 0, 0, 0 };
		commit(g);
		// create new edges
		for (int i = 0; i < ITERATIONS; i++) {
			// decides which edge should be created
			int edge = rand.nextInt(3);
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			createTransaction(g);
			if (edge == 0) {
				// create a Link
				g.createLink((AbstractSuperNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLinkOut[start]++;
				expectedLinkIn[end]++;
			} else if (edge == 1) {
				// create a LinkBack
				g.createLinkBack((SuperNode) nodes[end],
						(AbstractSuperNode) nodes[start]);
				expectedLinkBackOut[end]++;
				expectedLinkBackIn[start]++;
			} else {
				// create a SubLink
				start = 1;
				g.createSubLink((DoubleSubNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLinkOut[start]++;
				expectedLinkIn[end]++;
				expectedSubLinkOut[start]++;
				expectedSubLinkIn[end]++;
			}
			commit(g);
			testVertexForEdgeClassEdgeDirection(nodes[0], expectedLinkIn[0],
					expectedSubLinkIn[0], expectedLinkBackIn[0],
					EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirection(nodes[0], expectedLinkOut[0],
					expectedSubLinkOut[0], expectedLinkBackOut[0],
					EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirection(nodes[0], expectedLinkIn[0]
					+ expectedLinkOut[0], expectedSubLinkIn[0]
					+ expectedSubLinkOut[0], expectedLinkBackIn[0]
					+ expectedLinkBackOut[0], EdgeDirection.INOUT);
			testVertexForEdgeClassEdgeDirection(nodes[1], expectedLinkIn[1],
					expectedSubLinkIn[1], expectedLinkBackIn[1],
					EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirection(nodes[1], expectedLinkOut[1],
					expectedSubLinkOut[1], expectedLinkBackOut[1],
					EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirection(nodes[1], expectedLinkIn[1]
					+ expectedLinkOut[1], expectedSubLinkIn[1]
					+ expectedSubLinkOut[1], expectedLinkBackIn[1]
					+ expectedLinkBackOut[1], EdgeDirection.INOUT);
			testVertexForEdgeClassEdgeDirection(nodes[2], expectedLinkIn[2],
					expectedSubLinkIn[2], expectedLinkBackIn[2],
					EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirection(nodes[2], expectedLinkOut[2],
					expectedSubLinkOut[2], expectedLinkBackOut[2],
					EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirection(nodes[2], expectedLinkIn[2]
					+ expectedLinkOut[2], expectedSubLinkIn[2]
					+ expectedSubLinkOut[2], expectedLinkBackIn[2]
					+ expectedLinkBackOut[2], EdgeDirection.INOUT);
		}
		// delete the edges
		HashMap<Vertex, Integer> numbers = new HashMap<Vertex, Integer>();
		numbers.put(nodes[0], 0);
		numbers.put(nodes[1], 1);
		numbers.put(nodes[2], 2);
		createTransaction(g);
		for (int i = g.getFirstEdge().getId(); i < g.getECount(); i++) {
			Edge e = g.getEdge(i);
			int start = numbers.get(e.getAlpha());
			int end = numbers.get(e.getOmega());
			if (e instanceof SubLink) {
				expectedLinkOut[start]--;
				expectedLinkIn[end]--;
				expectedSubLinkOut[start]--;
				expectedSubLinkIn[end]--;
			} else if (e instanceof LinkBack) {
				expectedLinkBackOut[start]--;
				expectedLinkBackIn[end]--;
			} else {
				expectedLinkOut[start]--;
				expectedLinkIn[end]--;
			}
			g.deleteEdge(e);
			commit(g);
			testVertexForEdgeClassEdgeDirection(nodes[0], expectedLinkIn[0],
					expectedSubLinkIn[0], expectedLinkBackIn[0],
					EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirection(nodes[0], expectedLinkOut[0],
					expectedSubLinkOut[0], expectedLinkBackOut[0],
					EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirection(nodes[0], expectedLinkIn[0]
					+ expectedLinkOut[0], expectedSubLinkIn[0]
					+ expectedSubLinkOut[0], expectedLinkBackIn[0]
					+ expectedLinkBackOut[0], EdgeDirection.INOUT);
			testVertexForEdgeClassEdgeDirection(nodes[1], expectedLinkIn[1],
					expectedSubLinkIn[1], expectedLinkBackIn[1],
					EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirection(nodes[1], expectedLinkOut[1],
					expectedSubLinkOut[1], expectedLinkBackOut[1],
					EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirection(nodes[1], expectedLinkIn[1]
					+ expectedLinkOut[1], expectedSubLinkIn[1]
					+ expectedSubLinkOut[1], expectedLinkBackIn[1]
					+ expectedLinkBackOut[1], EdgeDirection.INOUT);
			testVertexForEdgeClassEdgeDirection(nodes[2], expectedLinkIn[2],
					expectedSubLinkIn[2], expectedLinkBackIn[2],
					EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirection(nodes[2], expectedLinkOut[2],
					expectedSubLinkOut[2], expectedLinkBackOut[2],
					EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirection(nodes[2], expectedLinkIn[2]
					+ expectedLinkOut[2], expectedSubLinkIn[2]
					+ expectedSubLinkOut[2], expectedLinkBackIn[2]
					+ expectedLinkBackOut[2], EdgeDirection.INOUT);
			createTransaction(g);
		}
		commit(g);
	}

	/**
	 * Tests if a Vertex has the expected degree considering the EdgeClass and
	 * the EdgeDirection.
	 * 
	 * @param forNode
	 *            the Vertex, which degrees should be tested
	 * @param expectedLink
	 *            the expected number of incident Links
	 * @param expectedSubLink
	 *            the expected number of incident SubLinks
	 * @param expectedLinkBack
	 *            the expected number of incident LinkBacks
	 * @param direction
	 *            the direction of the incidences
	 * @throws CommitFailedException
	 */
	private void testVertexForEdgeClassEdgeDirection(Vertex forNode,
			int expectedLink, int expectedSubLink, int expectedLinkBack,
			EdgeDirection direction) throws CommitFailedException {
		createReadOnlyTransaction(g);
		EdgeClass[] ecs = getEdgeClasses();
		assertEquals(expectedLink, forNode.getDegree(ecs[0], direction));
		assertEquals(expectedSubLink, forNode.getDegree(ecs[1], direction));
		assertEquals(expectedLinkBack, forNode.getDegree(ecs[2], direction));
		commit(g);
	}

	// tests of the method getDegree(Class<? extends Edge> ec, EdgeDirection
	// orientation)

	/**
	 * A vertex with no connected incidences has to have a degree of 0 for each
	 * Class.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassEdgeDirection0() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForClassEdgeDirection(v, 0, 0, 0, EdgeDirection.INOUT);
		testVertexForClassEdgeDirection(v, 0, 0, 0, EdgeDirection.IN);
		testVertexForClassEdgeDirection(v, 0, 0, 0, EdgeDirection.OUT);
		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassEdgeDirection1() throws CommitFailedException {
		createTransaction(g);
		SubNode subn = g.createSubNode();
		DoubleSubNode dsubn = g.createDoubleSubNode();
		DoubleSubNode dsubnWithout = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createLink(subn, supern);
		g.createLink(dsubn, dsubn);
		g.createSubLink(dsubn, supern);
		g.createLinkBack(supern, dsubn);
		g.createLinkBack(dsubn, subn);
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForClassEdgeDirection(dsubnWithout, 0, 0, 0,
				EdgeDirection.INOUT);
		testVertexForClassEdgeDirection(dsubnWithout, 0, 0, 0, EdgeDirection.IN);
		testVertexForClassEdgeDirection(dsubnWithout, 0, 0, 0,
				EdgeDirection.OUT);
		testVertexForClassEdgeDirection(subn, 1, 0, 1, EdgeDirection.INOUT);
		testVertexForClassEdgeDirection(subn, 0, 0, 1, EdgeDirection.IN);
		testVertexForClassEdgeDirection(subn, 1, 0, 0, EdgeDirection.OUT);
		testVertexForClassEdgeDirection(dsubn, 3, 1, 2, EdgeDirection.INOUT);
		testVertexForClassEdgeDirection(dsubn, 1, 0, 1, EdgeDirection.IN);
		testVertexForClassEdgeDirection(dsubn, 2, 1, 1, EdgeDirection.OUT);
		testVertexForClassEdgeDirection(supern, 2, 1, 1, EdgeDirection.INOUT);
		testVertexForClassEdgeDirection(supern, 2, 1, 0, EdgeDirection.IN);
		testVertexForClassEdgeDirection(supern, 0, 0, 1, EdgeDirection.OUT);
		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph, which has only one
	 * LinkBack.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassEdgeDirection2() throws CommitFailedException {
		createTransaction(g);
		SuperNode dsubn = g.createSuperNode();
		AbstractSuperNode supern = g.createSubNode();
		g.createLinkBack(dsubn, supern);
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForClassEdgeDirection(dsubn, 0, 0, 0, EdgeDirection.IN);
		testVertexForClassEdgeDirection(dsubn, 0, 0, 1, EdgeDirection.OUT);
		testVertexForClassEdgeDirection(dsubn, 0, 0, 1, EdgeDirection.INOUT);
		testVertexForClassEdgeDirection(supern, 0, 0, 1, EdgeDirection.IN);
		testVertexForClassEdgeDirection(supern, 0, 0, 0, EdgeDirection.OUT);
		testVertexForClassEdgeDirection(supern, 0, 0, 1, EdgeDirection.INOUT);
		commit(g);
	}

	/**
	 * Generates a number of different edges and checks the correct degrees of
	 * the vertices considering the different Classes and their EdgeDirections.
	 * After that it deletes the edges and checks the degrees again.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassEdgeDirection3() throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		nodes[0] = g.createSubNode();
		nodes[1] = g.createDoubleSubNode();
		nodes[2] = g.createSuperNode();
		int[] expectedLinkIn = new int[] { 0, 0, 0 };
		int[] expectedLinkOut = new int[] { 0, 0, 0 };
		int[] expectedLinkBackIn = new int[] { 0, 0, 0 };
		int[] expectedLinkBackOut = new int[] { 0, 0, 0 };
		int[] expectedSubLinkIn = new int[] { 0, 0, 0 };
		int[] expectedSubLinkOut = new int[] { 0, 0, 0 };
		commit(g);
		// create new edges
		for (int i = 0; i < ITERATIONS; i++) {
			// decides which edge should be created
			int edge = rand.nextInt(3);
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			createTransaction(g);
			if (edge == 0) {
				// create a Link
				g.createLink((AbstractSuperNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLinkOut[start]++;
				expectedLinkIn[end]++;
			} else if (edge == 1) {
				// create a LinkBack
				g.createLinkBack((SuperNode) nodes[end],
						(AbstractSuperNode) nodes[start]);
				expectedLinkBackOut[end]++;
				expectedLinkBackIn[start]++;
			} else {
				// create a SubLink
				start = 1;
				g.createSubLink((DoubleSubNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLinkOut[start]++;
				expectedLinkIn[end]++;
				expectedSubLinkOut[start]++;
				expectedSubLinkIn[end]++;
			}
			commit(g);
			createReadOnlyTransaction(g);
			testVertexForClassEdgeDirection(nodes[0], expectedLinkIn[0],
					expectedSubLinkIn[0], expectedLinkBackIn[0],
					EdgeDirection.IN);
			testVertexForClassEdgeDirection(nodes[0], expectedLinkOut[0],
					expectedSubLinkOut[0], expectedLinkBackOut[0],
					EdgeDirection.OUT);
			testVertexForClassEdgeDirection(nodes[0], expectedLinkIn[0]
					+ expectedLinkOut[0], expectedSubLinkIn[0]
					+ expectedSubLinkOut[0], expectedLinkBackIn[0]
					+ expectedLinkBackOut[0], EdgeDirection.INOUT);
			testVertexForClassEdgeDirection(nodes[1], expectedLinkIn[1],
					expectedSubLinkIn[1], expectedLinkBackIn[1],
					EdgeDirection.IN);
			testVertexForClassEdgeDirection(nodes[1], expectedLinkOut[1],
					expectedSubLinkOut[1], expectedLinkBackOut[1],
					EdgeDirection.OUT);
			testVertexForClassEdgeDirection(nodes[1], expectedLinkIn[1]
					+ expectedLinkOut[1], expectedSubLinkIn[1]
					+ expectedSubLinkOut[1], expectedLinkBackIn[1]
					+ expectedLinkBackOut[1], EdgeDirection.INOUT);
			testVertexForClassEdgeDirection(nodes[2], expectedLinkIn[2],
					expectedSubLinkIn[2], expectedLinkBackIn[2],
					EdgeDirection.IN);
			testVertexForClassEdgeDirection(nodes[2], expectedLinkOut[2],
					expectedSubLinkOut[2], expectedLinkBackOut[2],
					EdgeDirection.OUT);
			testVertexForClassEdgeDirection(nodes[2], expectedLinkIn[2]
					+ expectedLinkOut[2], expectedSubLinkIn[2]
					+ expectedSubLinkOut[2], expectedLinkBackIn[2]
					+ expectedLinkBackOut[2], EdgeDirection.INOUT);
			commit(g);
		}
		// delete the edges
		HashMap<Vertex, Integer> numbers = new HashMap<Vertex, Integer>();
		numbers.put(nodes[0], 0);
		numbers.put(nodes[1], 1);
		numbers.put(nodes[2], 2);
		createTransaction(g);
		for (int i = g.getFirstEdge().getId(); i < g.getECount(); i++) {
			Edge e = g.getEdge(i);
			int start = numbers.get(e.getAlpha());
			int end = numbers.get(e.getOmega());
			if (e instanceof SubLink) {
				expectedLinkOut[start]--;
				expectedLinkIn[end]--;
				expectedSubLinkOut[start]--;
				expectedSubLinkIn[end]--;
			} else if (e instanceof LinkBack) {
				expectedLinkBackOut[start]--;
				expectedLinkBackIn[end]--;
			} else {
				expectedLinkOut[start]--;
				expectedLinkIn[end]--;
			}
			g.deleteEdge(e);
			testVertexForClassEdgeDirection(nodes[0], expectedLinkIn[0],
					expectedSubLinkIn[0], expectedLinkBackIn[0],
					EdgeDirection.IN);
			testVertexForClassEdgeDirection(nodes[0], expectedLinkOut[0],
					expectedSubLinkOut[0], expectedLinkBackOut[0],
					EdgeDirection.OUT);
			testVertexForClassEdgeDirection(nodes[0], expectedLinkIn[0]
					+ expectedLinkOut[0], expectedSubLinkIn[0]
					+ expectedSubLinkOut[0], expectedLinkBackIn[0]
					+ expectedLinkBackOut[0], EdgeDirection.INOUT);
			testVertexForClassEdgeDirection(nodes[1], expectedLinkIn[1],
					expectedSubLinkIn[1], expectedLinkBackIn[1],
					EdgeDirection.IN);
			testVertexForClassEdgeDirection(nodes[1], expectedLinkOut[1],
					expectedSubLinkOut[1], expectedLinkBackOut[1],
					EdgeDirection.OUT);
			testVertexForClassEdgeDirection(nodes[1], expectedLinkIn[1]
					+ expectedLinkOut[1], expectedSubLinkIn[1]
					+ expectedSubLinkOut[1], expectedLinkBackIn[1]
					+ expectedLinkBackOut[1], EdgeDirection.INOUT);
			testVertexForClassEdgeDirection(nodes[2], expectedLinkIn[2],
					expectedSubLinkIn[2], expectedLinkBackIn[2],
					EdgeDirection.IN);
			testVertexForClassEdgeDirection(nodes[2], expectedLinkOut[2],
					expectedSubLinkOut[2], expectedLinkBackOut[2],
					EdgeDirection.OUT);
			testVertexForClassEdgeDirection(nodes[2], expectedLinkIn[2]
					+ expectedLinkOut[2], expectedSubLinkIn[2]
					+ expectedSubLinkOut[2], expectedLinkBackIn[2]
					+ expectedLinkBackOut[2], EdgeDirection.INOUT);
		}
		commit(g);
	}

	/**
	 * Tests if a Vertex has the expected degree considering the Class and the
	 * EdgeDirection.
	 * 
	 * @param forNode
	 *            the Vertex, which degrees should be tested
	 * @param expectedLink
	 *            the expected number of incident Links
	 * @param expectedSubLink
	 *            the expected number of incident SubLinks
	 * @param expectedLinkBack
	 *            the expected number of incident LinkBacks
	 * @param direction
	 *            the direction of the incidences
	 */
	private void testVertexForClassEdgeDirection(Vertex forNode,
			int expectedLink, int expectedSubLink, int expectedLinkBack,
			EdgeDirection direction) {
		assertEquals(expectedLink, forNode.getDegree(Link.class, direction));
		assertEquals(expectedSubLink,
				forNode.getDegree(SubLink.class, direction));
		assertEquals(expectedLinkBack,
				forNode.getDegree(LinkBack.class, direction));
	}

	// tests of the method getDegree(EdgeClass ec, EdgeDirection orientation,
	// boolean noSubClasses)

	/**
	 * A vertex with no connected incidences has to have a degree of 0 for each
	 * EdgeClass.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassEdgeDirectionBoolean0()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForEdgeClassEdgeDirectionBoolean(v, 0, 0, 0,
				EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirectionBoolean(v, 0, 0, 0, EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirectionBoolean(v, 0, 0, 0,
				EdgeDirection.OUT);
		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassEdgeDirectionBoolean1()
			throws CommitFailedException {
		createTransaction(g);
		SubNode subn = g.createSubNode();
		DoubleSubNode dsubn = g.createDoubleSubNode();
		DoubleSubNode dsubnWithout = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createLink(subn, supern);
		g.createLink(dsubn, dsubn);
		g.createSubLink(dsubn, supern);
		g.createLinkBack(supern, dsubn);
		g.createLinkBack(dsubn, subn);
		commit(g);
		createTransaction(g);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubnWithout, 0, 0, 0,
				EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubnWithout, 0, 0, 0,
				EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubnWithout, 0, 0, 0,
				EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirectionBoolean(subn, 1, 0, 1,
				EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirectionBoolean(subn, 0, 0, 1,
				EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirectionBoolean(subn, 1, 0, 0,
				EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubn, 3, 1, 2,
				EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubn, 1, 0, 1,
				EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubn, 2, 1, 1,
				EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirectionBoolean(supern, 2, 1, 1,
				EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirectionBoolean(supern, 2, 1, 0,
				EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirectionBoolean(supern, 0, 0, 1,
				EdgeDirection.OUT);
		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph, which has only one
	 * LinkBack.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassEdgeDirectionBoolean2()
			throws CommitFailedException {
		createTransaction(g);
		SuperNode dsubn = g.createSuperNode();
		AbstractSuperNode supern = g.createSubNode();
		g.createLinkBack(dsubn, supern);
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubn, 0, 0, 0,
				EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubn, 0, 0, 1,
				EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubn, 0, 0, 1,
				EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirectionBoolean(supern, 0, 0, 1,
				EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirectionBoolean(supern, 0, 0, 0,
				EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirectionBoolean(supern, 0, 0, 1,
				EdgeDirection.INOUT);

		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph, which has only one Link.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassEdgeDirectionBoolean3()
			throws CommitFailedException {
		createTransaction(g);
		SuperNode dsubn = g.createSuperNode();
		AbstractSuperNode supern = g.createSubNode();
		g.createLink(supern, dsubn);
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubn, 1, 0, 0,
				EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubn, 0, 0, 0,
				EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirectionBoolean(dsubn, 1, 0, 0,
				EdgeDirection.INOUT);
		testVertexForEdgeClassEdgeDirectionBoolean(supern, 0, 0, 0,
				EdgeDirection.IN);
		testVertexForEdgeClassEdgeDirectionBoolean(supern, 1, 0, 0,
				EdgeDirection.OUT);
		testVertexForEdgeClassEdgeDirectionBoolean(supern, 1, 0, 0,
				EdgeDirection.INOUT);
		commit(g);
	}

	/**
	 * Generates a number of different edges and checks the correct degrees of
	 * the vertices considering the different Edgeclasses, their EdgeDirections
	 * and their SubClasses. After that it deletes the edges and checks the
	 * degrees again.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestEdgeClassEdgeDirectionBoolean5()
			throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		nodes[0] = g.createSubNode();
		nodes[1] = g.createDoubleSubNode();
		nodes[2] = g.createSuperNode();
		int[] expectedLinkIn = new int[] { 0, 0, 0 };
		int[] expectedLinkOut = new int[] { 0, 0, 0 };
		int[] expectedLinkBackIn = new int[] { 0, 0, 0 };
		int[] expectedLinkBackOut = new int[] { 0, 0, 0 };
		int[] expectedSubLinkIn = new int[] { 0, 0, 0 };
		int[] expectedSubLinkOut = new int[] { 0, 0, 0 };
		commit(g);
		// create new edges
		for (int i = 0; i < ITERATIONS; i++) {
			// decides which edge should be created
			int edge = rand.nextInt(3);
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			createTransaction(g);
			if (edge == 0) {
				// create a Link
				g.createLink((AbstractSuperNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLinkOut[start]++;
				expectedLinkIn[end]++;
			} else if (edge == 1) {
				// create a LinkBack
				g.createLinkBack((SuperNode) nodes[end],
						(AbstractSuperNode) nodes[start]);
				expectedLinkBackOut[end]++;
				expectedLinkBackIn[start]++;
			} else {
				// create a SubLink
				start = 1;
				g.createSubLink((DoubleSubNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLinkOut[start]++;
				expectedLinkIn[end]++;
				expectedSubLinkOut[start]++;
				expectedSubLinkIn[end]++;
			}
			commit(g);
			createReadOnlyTransaction(g);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[0],
					expectedLinkIn[0], expectedSubLinkIn[0],
					expectedLinkBackIn[0], EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[0],
					expectedLinkOut[0], expectedSubLinkOut[0],
					expectedLinkBackOut[0], EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[0],
					expectedLinkIn[0] + expectedLinkOut[0],
					expectedSubLinkIn[0] + expectedSubLinkOut[0],
					expectedLinkBackIn[0] + expectedLinkBackOut[0],
					EdgeDirection.INOUT);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[1],
					expectedLinkIn[1], expectedSubLinkIn[1],
					expectedLinkBackIn[1], EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[1],
					expectedLinkOut[1], expectedSubLinkOut[1],
					expectedLinkBackOut[1], EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[1],
					expectedLinkIn[1] + expectedLinkOut[1],
					expectedSubLinkIn[1] + expectedSubLinkOut[1],
					expectedLinkBackIn[1] + expectedLinkBackOut[1],
					EdgeDirection.INOUT);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[2],
					expectedLinkIn[2], expectedSubLinkIn[2],
					expectedLinkBackIn[2], EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[2],
					expectedLinkOut[2], expectedSubLinkOut[2],
					expectedLinkBackOut[2], EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[2],
					expectedLinkIn[2] + expectedLinkOut[2],
					expectedSubLinkIn[2] + expectedSubLinkOut[2],
					expectedLinkBackIn[2] + expectedLinkBackOut[2],
					EdgeDirection.INOUT);
			commit(g);
		}
		// delete the edges
		HashMap<Vertex, Integer> numbers = new HashMap<Vertex, Integer>();
		numbers.put(nodes[0], 0);
		numbers.put(nodes[1], 1);
		numbers.put(nodes[2], 2);
		createTransaction(g);
		for (int i = g.getFirstEdge().getId(); i < g.getECount(); i++) {
			Edge e = g.getEdge(i);
			int start = numbers.get(e.getAlpha());
			int end = numbers.get(e.getOmega());
			if (e instanceof SubLink) {
				expectedLinkOut[start]--;
				expectedLinkIn[end]--;
				expectedSubLinkOut[start]--;
				expectedSubLinkIn[end]--;
			} else if (e instanceof LinkBack) {
				expectedLinkBackOut[start]--;
				expectedLinkBackIn[end]--;
			} else {
				expectedLinkOut[start]--;
				expectedLinkIn[end]--;
			}
			g.deleteEdge(e);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[0],
					expectedLinkIn[0], expectedSubLinkIn[0],
					expectedLinkBackIn[0], EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[0],
					expectedLinkOut[0], expectedSubLinkOut[0],
					expectedLinkBackOut[0], EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[0],
					expectedLinkIn[0] + expectedLinkOut[0],
					expectedSubLinkIn[0] + expectedSubLinkOut[0],
					expectedLinkBackIn[0] + expectedLinkBackOut[0],
					EdgeDirection.INOUT);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[1],
					expectedLinkIn[1], expectedSubLinkIn[1],
					expectedLinkBackIn[1], EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[1],
					expectedLinkOut[1], expectedSubLinkOut[1],
					expectedLinkBackOut[1], EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[1],
					expectedLinkIn[1] + expectedLinkOut[1],
					expectedSubLinkIn[1] + expectedSubLinkOut[1],
					expectedLinkBackIn[1] + expectedLinkBackOut[1],
					EdgeDirection.INOUT);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[2],
					expectedLinkIn[2], expectedSubLinkIn[2],
					expectedLinkBackIn[2], EdgeDirection.IN);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[2],
					expectedLinkOut[2], expectedSubLinkOut[2],
					expectedLinkBackOut[2], EdgeDirection.OUT);
			testVertexForEdgeClassEdgeDirectionBoolean(nodes[2],
					expectedLinkIn[2] + expectedLinkOut[2],
					expectedSubLinkIn[2] + expectedSubLinkOut[2],
					expectedLinkBackIn[2] + expectedLinkBackOut[2],
					EdgeDirection.INOUT);
		}
		commit(g);
	}

	/**
	 * Tests if a Vertex has the expected degree considering the EdgeClass, the
	 * EdgeDirection and the Subclasses.
	 * 
	 * @param forNode
	 *            the Vertex, which degrees should be tested
	 * @param expectedLink
	 *            the expected number of incident Links
	 * @param expectedSubLink
	 *            the expected number of incident SubLinks
	 * @param expectedLinkBack
	 *            the expected number of incident LinkBacks
	 * @param direction
	 *            the direction of the incidences
	 */
	private void testVertexForEdgeClassEdgeDirectionBoolean(Vertex forNode,
			int expectedLink, int expectedSubLink, int expectedLinkBack,
			EdgeDirection direction) {
		EdgeClass[] ecs = getEdgeClasses();
		assertEquals(expectedLink, forNode.getDegree(ecs[0], direction));
		assertEquals(expectedSubLink, forNode.getDegree(ecs[1], direction));
		assertEquals(expectedLinkBack, forNode.getDegree(ecs[2], direction));
	}

	// tests of the method getDegree(Class<? extends Edge> ec, EdgeDirection
	// orientation, boolean noSubClasses)

	/**
	 * A vertex with no connected incidences has to have a degree of 0 for each
	 * Class.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassEdgeDirectionBoolean0()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForClassEdgeDirectionBoolean(v, 0, 0, 0, EdgeDirection.INOUT);
		testVertexForClassEdgeDirectionBoolean(v, 0, 0, 0, EdgeDirection.IN);
		testVertexForClassEdgeDirectionBoolean(v, 0, 0, 0, EdgeDirection.OUT);
		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassEdgeDirectionBoolean1()
			throws CommitFailedException {
		createTransaction(g);
		SubNode subn = g.createSubNode();
		DoubleSubNode dsubn = g.createDoubleSubNode();
		DoubleSubNode dsubnWithout = g.createDoubleSubNode();
		SuperNode supern = g.createSuperNode();
		g.createLink(subn, supern);
		g.createLink(dsubn, dsubn);
		g.createSubLink(dsubn, supern);
		g.createLinkBack(supern, dsubn);
		g.createLinkBack(dsubn, subn);
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForClassEdgeDirectionBoolean(dsubnWithout, 0, 0, 0,
				EdgeDirection.INOUT);
		testVertexForClassEdgeDirectionBoolean(dsubnWithout, 0, 0, 0,
				EdgeDirection.IN);
		testVertexForClassEdgeDirectionBoolean(dsubnWithout, 0, 0, 0,
				EdgeDirection.OUT);
		testVertexForClassEdgeDirectionBoolean(subn, 1, 0, 1,
				EdgeDirection.INOUT);
		testVertexForClassEdgeDirectionBoolean(subn, 0, 0, 1, EdgeDirection.IN);
		testVertexForClassEdgeDirectionBoolean(subn, 1, 0, 0, EdgeDirection.OUT);
		testVertexForClassEdgeDirectionBoolean(dsubn, 3, 1, 2,
				EdgeDirection.INOUT);
		testVertexForClassEdgeDirectionBoolean(dsubn, 1, 0, 1, EdgeDirection.IN);
		testVertexForClassEdgeDirectionBoolean(dsubn, 2, 1, 1,
				EdgeDirection.OUT);
		testVertexForClassEdgeDirectionBoolean(supern, 2, 1, 1,
				EdgeDirection.INOUT);
		testVertexForClassEdgeDirectionBoolean(supern, 2, 1, 0,
				EdgeDirection.IN);
		testVertexForClassEdgeDirectionBoolean(supern, 0, 0, 1,
				EdgeDirection.OUT);
		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph, which has only one
	 * LinkBack.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassEdgeDirectionBoolean2()
			throws CommitFailedException {
		createTransaction(g);
		SuperNode dsubn = g.createSuperNode();
		AbstractSuperNode supern = g.createSubNode();
		g.createLinkBack(dsubn, supern);
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForClassEdgeDirectionBoolean(dsubn, 0, 0, 0, EdgeDirection.IN);
		testVertexForClassEdgeDirectionBoolean(dsubn, 0, 0, 1,
				EdgeDirection.OUT);
		testVertexForClassEdgeDirectionBoolean(dsubn, 0, 0, 1,
				EdgeDirection.INOUT);
		testVertexForClassEdgeDirectionBoolean(supern, 0, 0, 1,
				EdgeDirection.IN);
		testVertexForClassEdgeDirectionBoolean(supern, 0, 0, 0,
				EdgeDirection.OUT);
		testVertexForClassEdgeDirectionBoolean(supern, 0, 0, 1,
				EdgeDirection.INOUT);
		commit(g);
	}

	/**
	 * Checks the degrees in a manually build graph, which has only one Link.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassEdgeDirectionBoolean3()
			throws CommitFailedException {
		createTransaction(g);
		SuperNode dsubn = g.createSuperNode();
		AbstractSuperNode supern = g.createSubNode();
		g.createLink(supern, dsubn);
		commit(g);
		createReadOnlyTransaction(g);
		testVertexForClassEdgeDirectionBoolean(dsubn, 1, 0, 0, EdgeDirection.IN);
		testVertexForClassEdgeDirectionBoolean(dsubn, 0, 0, 0,
				EdgeDirection.OUT);
		testVertexForClassEdgeDirectionBoolean(dsubn, 1, 0, 0,
				EdgeDirection.INOUT);
		testVertexForClassEdgeDirectionBoolean(supern, 0, 0, 0,
				EdgeDirection.IN);
		testVertexForClassEdgeDirectionBoolean(supern, 1, 0, 0,
				EdgeDirection.OUT);
		testVertexForClassEdgeDirectionBoolean(supern, 1, 0, 0,
				EdgeDirection.INOUT);
		commit(g);
	}

	/**
	 * Generates a number of different edges and checks the correct degrees of
	 * the vertices considering the different Classes, their EdgeDirections and
	 * their SubClasses. After that it deletes the edges and checks the degrees
	 * again.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getDegreeTestClassEdgeDirectionBoolean5()
			throws CommitFailedException {
		createTransaction(g);
		Vertex[] nodes = new Vertex[3];
		nodes[0] = g.createSubNode();
		nodes[1] = g.createDoubleSubNode();
		nodes[2] = g.createSuperNode();
		int[] expectedLinkIn = new int[] { 0, 0, 0 };
		int[] expectedLinkOut = new int[] { 0, 0, 0 };
		int[] expectedLinkBackIn = new int[] { 0, 0, 0 };
		int[] expectedLinkBackOut = new int[] { 0, 0, 0 };
		int[] expectedSubLinkIn = new int[] { 0, 0, 0 };
		int[] expectedSubLinkOut = new int[] { 0, 0, 0 };
		commit(g);
		// create new edges
		for (int i = 0; i < ITERATIONS; i++) {
			// decides which edge should be created
			int edge = rand.nextInt(3);
			int start = rand.nextInt(2);
			int end = rand.nextInt(2) + 1;
			createTransaction(g);
			if (edge == 0) {
				// create a Link
				g.createLink((AbstractSuperNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLinkOut[start]++;
				expectedLinkIn[end]++;
			} else if (edge == 1) {
				// create a LinkBack
				g.createLinkBack((SuperNode) nodes[end],
						(AbstractSuperNode) nodes[start]);
				expectedLinkBackOut[end]++;
				expectedLinkBackIn[start]++;
			} else {
				// create a SubLink
				start = 1;
				g.createSubLink((DoubleSubNode) nodes[start],
						(SuperNode) nodes[end]);
				expectedLinkOut[start]++;
				expectedLinkIn[end]++;
				expectedSubLinkOut[start]++;
				expectedSubLinkIn[end]++;
			}
			commit(g);
			createReadOnlyTransaction(g);
			testVertexForClassEdgeDirectionBoolean(nodes[0], expectedLinkIn[0],
					expectedSubLinkIn[0], expectedLinkBackIn[0],
					EdgeDirection.IN);
			testVertexForClassEdgeDirectionBoolean(nodes[0],
					expectedLinkOut[0], expectedSubLinkOut[0],
					expectedLinkBackOut[0], EdgeDirection.OUT);
			testVertexForClassEdgeDirectionBoolean(nodes[0], expectedLinkIn[0]
					+ expectedLinkOut[0], expectedSubLinkIn[0]
					+ expectedSubLinkOut[0], expectedLinkBackIn[0]
					+ expectedLinkBackOut[0], EdgeDirection.INOUT);
			testVertexForClassEdgeDirectionBoolean(nodes[1], expectedLinkIn[1],
					expectedSubLinkIn[1], expectedLinkBackIn[1],
					EdgeDirection.IN);
			testVertexForClassEdgeDirectionBoolean(nodes[1],
					expectedLinkOut[1], expectedSubLinkOut[1],
					expectedLinkBackOut[1], EdgeDirection.OUT);
			testVertexForClassEdgeDirectionBoolean(nodes[1], expectedLinkIn[1]
					+ expectedLinkOut[1], expectedSubLinkIn[1]
					+ expectedSubLinkOut[1], expectedLinkBackIn[1]
					+ expectedLinkBackOut[1], EdgeDirection.INOUT);
			testVertexForClassEdgeDirectionBoolean(nodes[2], expectedLinkIn[2],
					expectedSubLinkIn[2], expectedLinkBackIn[2],
					EdgeDirection.IN);
			testVertexForClassEdgeDirectionBoolean(nodes[2],
					expectedLinkOut[2], expectedSubLinkOut[2],
					expectedLinkBackOut[2], EdgeDirection.OUT);
			testVertexForClassEdgeDirectionBoolean(nodes[2], expectedLinkIn[2]
					+ expectedLinkOut[2], expectedSubLinkIn[2]
					+ expectedSubLinkOut[2], expectedLinkBackIn[2]
					+ expectedLinkBackOut[2], EdgeDirection.INOUT);
			commit(g);
		}
		// delete the edges
		HashMap<Vertex, Integer> numbers = new HashMap<Vertex, Integer>();
		numbers.put(nodes[0], 0);
		numbers.put(nodes[1], 1);
		numbers.put(nodes[2], 2);
		createTransaction(g);
		for (int i = g.getFirstEdge().getId(); i < g.getECount(); i++) {
			Edge e = g.getEdge(i);
			int start = numbers.get(e.getAlpha());
			int end = numbers.get(e.getOmega());
			if (e instanceof SubLink) {
				expectedLinkOut[start]--;
				expectedLinkIn[end]--;
				expectedSubLinkOut[start]--;
				expectedSubLinkIn[end]--;
			} else if (e instanceof LinkBack) {
				expectedLinkBackOut[start]--;
				expectedLinkBackIn[end]--;
			} else {
				expectedLinkOut[start]--;
				expectedLinkIn[end]--;
			}
			g.deleteEdge(e);
			testVertexForClassEdgeDirectionBoolean(nodes[0], expectedLinkIn[0],
					expectedSubLinkIn[0], expectedLinkBackIn[0],
					EdgeDirection.IN);
			testVertexForClassEdgeDirectionBoolean(nodes[0],
					expectedLinkOut[0], expectedSubLinkOut[0],
					expectedLinkBackOut[0], EdgeDirection.OUT);
			testVertexForClassEdgeDirectionBoolean(nodes[0], expectedLinkIn[0]
					+ expectedLinkOut[0], expectedSubLinkIn[0]
					+ expectedSubLinkOut[0], expectedLinkBackIn[0]
					+ expectedLinkBackOut[0], EdgeDirection.INOUT);
			testVertexForClassEdgeDirectionBoolean(nodes[1], expectedLinkIn[1],
					expectedSubLinkIn[1], expectedLinkBackIn[1],
					EdgeDirection.IN);
			testVertexForClassEdgeDirectionBoolean(nodes[1],
					expectedLinkOut[1], expectedSubLinkOut[1],
					expectedLinkBackOut[1], EdgeDirection.OUT);
			testVertexForClassEdgeDirectionBoolean(nodes[1], expectedLinkIn[1]
					+ expectedLinkOut[1], expectedSubLinkIn[1]
					+ expectedSubLinkOut[1], expectedLinkBackIn[1]
					+ expectedLinkBackOut[1], EdgeDirection.INOUT);
			testVertexForClassEdgeDirectionBoolean(nodes[2], expectedLinkIn[2],
					expectedSubLinkIn[2], expectedLinkBackIn[2],
					EdgeDirection.IN);
			testVertexForClassEdgeDirectionBoolean(nodes[2],
					expectedLinkOut[2], expectedSubLinkOut[2],
					expectedLinkBackOut[2], EdgeDirection.OUT);
			testVertexForClassEdgeDirectionBoolean(nodes[2], expectedLinkIn[2]
					+ expectedLinkOut[2], expectedSubLinkIn[2]
					+ expectedSubLinkOut[2], expectedLinkBackIn[2]
					+ expectedLinkBackOut[2], EdgeDirection.INOUT);
		}
		commit(g);
	}

	/**
	 * Tests if a Vertex has the expected degree considering the Class, the
	 * EdgeDirection and the Subclasses.
	 * 
	 * @param forNode
	 *            the Vertex, which degrees should be tested
	 * @param expectedLink
	 *            the expected number of incident Links
	 * @param expectedSubLink
	 *            the expected number of incident SubLinks
	 * @param expectedLinkBack
	 *            the expected number of incident LinkBacks
	 * @param direction
	 *            the direction of the incidences
	 */
	private void testVertexForClassEdgeDirectionBoolean(Vertex forNode,
			int expectedLink, int expectedSubLink, int expectedLinkBack,
			EdgeDirection direction) {
		assertEquals(expectedLink, forNode.getDegree(Link.class, direction));
		assertEquals(expectedSubLink,
				forNode.getDegree(SubLink.class, direction));
		assertEquals(expectedLinkBack,
				forNode.getDegree(LinkBack.class, direction));
	}

	// tests of the method getPrevVertex();

	/**
	 * Tests the method if there is only one Vertex in the graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getPrevVertexTest0() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createSuperNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertNull(v.getPrevVertex());
		commit(g);
	}

	/**
	 * Tests the correctness in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getPrevVertexTest1() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createSubNode();
		Vertex v2 = g.createSuperNode();
		Vertex v3 = g.createSuperNode();
		Vertex v4 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v3, v4.getPrevVertex());
		assertEquals(v2, v3.getPrevVertex());
		assertEquals(v1, v2.getPrevVertex());
		assertEquals(v0, v1.getPrevVertex());
		assertNull(v0.getPrevVertex());
		commit(g);
	}

	/**
	 * Tests the correctness in an random graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getPrevVertexTest2() throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			Vertex[] vertices = new Vertex[30];
			// Create Vertices
			createTransaction(g);
			for (int j = 0; j < vertices.length; j++) {
				vertices[j] = g.createDoubleSubNode();
			}
			commit(g);
			createReadOnlyTransaction(g);
			// Check correctness
			for (int j = vertices.length - 1; j >= 0; j--) {
				assertEquals(j == 0 ? null : vertices[j - 1],
						vertices[j].getPrevVertex());
			}
			commit(g);
		}
	}

	private VertexTestGraph createNewGraph() {
		VertexTestGraph graph = null;
		switch (implementationType) {
		case STANDARD:
			graph = VertexTestSchema.instance().createVertexTestGraph(
					ImplementationType.STANDARD);
			break;
		case TRANSACTION:
			graph = VertexTestSchema.instance().createVertexTestGraph(
					ImplementationType.TRANSACTION);
			break;
		case DATABASE:
			graph = createVertexTestGraphWithDatabaseSupport();
			break;
		default:
			fail("Implementation " + implementationType
					+ " not yet supported by this test.");
		}
		return graph;
	}

	private ArrayList<String> graphIdsInUse = new ArrayList<String>();

	private VertexTestGraph createVertexTestGraphWithDatabaseSupport() {
		String id = RandomIdGenerator.generateId();
		while (graphIdsInUse.contains(id)) {
			id = RandomIdGenerator.generateId();
		}
		graphIdsInUse.add(id);
		VertexTestGraph graph = dbHandler
				.createVertexTestGraphWithDatabaseSupport(id, 100, 100);
		return graph;
	}

	// tests of the method getNextVertex();
	// (tested in LoadTest, too)

	/**
	 * Tests the method if there is only one Vertex in the graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTest0() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createSuperNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertNull(v.getNextVertex());
		commit(g);
	}

	/**
	 * Tests the correctness in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTest1() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createSubNode();
		Vertex v2 = g.createSuperNode();
		Vertex v3 = g.createSuperNode();
		Vertex v4 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v1, v0.getNextVertex());
		assertEquals(v2, v1.getNextVertex());
		assertEquals(v3, v2.getNextVertex());
		assertEquals(v4, v3.getNextVertex());
		assertNull(v4.getNextVertex());
		commit(g);
	}

	/**
	 * Tests the correctness in an random graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTest2() throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			Vertex[] vertices = new Vertex[30];
			// Create Vertices
			createTransaction(g);
			for (int j = 0; j < vertices.length; j++) {
				vertices[j] = g.createDoubleSubNode();
			}
			commit(g);
			// Check correctness
			createReadOnlyTransaction(g);
			for (int j = 0; j < vertices.length; j++) {
				assertEquals(j == (vertices.length - 1) ? null
						: vertices[j + 1], vertices[j].getNextVertex());
			}
			commit(g);
		}
	}

	// tests of the method getNextVertex(VertexClass aVertexClass);

	/**
	 * Creates an array <code>ret</code> of all VertexClasses.<br>
	 * ret[0]=AbstractSuperNode<br>
	 * ret[1]=SubNode<br>
	 * ret[2]=SuperNode<br>
	 * ret[3]=DoubleSubNode
	 * 
	 * @return an array <code>ret</code> of all VertexClasses
	 */
	private VertexClass[] getVertexClasses() {
		List<VertexClass> vclasses = g.getGraphClass().getVertexClasses();
		VertexClass[] vcret = new VertexClass[4];
		for (VertexClass vc : vclasses) {
			if (vc.getSimpleName().equals("AbstractSuperNode")) {
				vcret[0] = vc;
			} else if (vc.getSimpleName().equals("SubNode")) {
				vcret[1] = vc;
			} else if (vc.getSimpleName().equals("SuperNode")) {
				vcret[2] = vc;
			} else if (vc.getSimpleName().equals("DoubleSubNode")) {
				vcret[3] = vc;
			}
		}
		return vcret;
	}

	/**
	 * Tests if there is only one vertex in the graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestVertexClass0() throws CommitFailedException {
		createTransaction(g);
		VertexClass[] vertices = getVertexClasses();
		Vertex v = g.createSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertNull(v.getNextVertex(vertices[0]));
		assertNull(v.getNextVertex(vertices[1]));
		assertNull(v.getNextVertex(vertices[2]));
		assertNull(v.getNextVertex(vertices[3]));
		commit(g);
	}

	/**
	 * The next vertex is an instance of a class which is a subclass of another
	 * vertexclass.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestVertexClass1() throws CommitFailedException {
		createTransaction(g);
		VertexClass[] vertices = getVertexClasses();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v1, v0.getNextVertex(vertices[0]));
		assertEquals(v1, v0.getNextVertex(vertices[1]));
		assertNull(v0.getNextVertex(vertices[2]));
		assertNull(v0.getNextVertex(vertices[3]));
		commit(g);
	}

	/**
	 * The next vertex is an instance of a class which is a subclass of tow
	 * other vertexclasses.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestVertexClass2() throws CommitFailedException {
		createTransaction(g);
		VertexClass[] vertices = getVertexClasses();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v1, v0.getNextVertex(vertices[0]));
		assertEquals(v1, v0.getNextVertex(vertices[1]));
		assertEquals(v1, v0.getNextVertex(vertices[2]));
		assertEquals(v1, v0.getNextVertex(vertices[3]));
		commit(g);
	}

	/**
	 * Test in a manually build graph: SubNode SuperNode DoubleSubNode SuperNode
	 * SubNode SuperNode DoubleSubNode
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestVertexClass3() throws CommitFailedException {
		createTransaction(g);
		VertexClass[] vertices = getVertexClasses();
		Vertex v0 = g.createSubNode();
		Vertex v1 = g.createSuperNode();
		Vertex v2 = g.createDoubleSubNode();
		Vertex v3 = g.createSuperNode();
		Vertex v4 = g.createSubNode();
		Vertex v5 = g.createSuperNode();
		Vertex v6 = g.createDoubleSubNode();
		commit(g);

		createReadOnlyTransaction(g);
		assertEquals(v2, v0.getNextVertex(vertices[0]));
		assertEquals(v2, v0.getNextVertex(vertices[1]));
		assertEquals(v1, v0.getNextVertex(vertices[2]));
		assertEquals(v2, v0.getNextVertex(vertices[3]));

		assertEquals(v2, v1.getNextVertex(vertices[0]));
		assertEquals(v2, v1.getNextVertex(vertices[1]));
		assertEquals(v2, v1.getNextVertex(vertices[2]));
		assertEquals(v2, v1.getNextVertex(vertices[3]));

		assertEquals(v4, v2.getNextVertex(vertices[0]));
		assertEquals(v4, v2.getNextVertex(vertices[1]));
		assertEquals(v3, v2.getNextVertex(vertices[2]));
		assertEquals(v6, v2.getNextVertex(vertices[3]));

		assertEquals(v4, v3.getNextVertex(vertices[0]));
		assertEquals(v4, v3.getNextVertex(vertices[1]));
		assertEquals(v5, v3.getNextVertex(vertices[2]));
		assertEquals(v6, v3.getNextVertex(vertices[3]));

		assertEquals(v6, v4.getNextVertex(vertices[0]));
		assertEquals(v6, v4.getNextVertex(vertices[1]));
		assertEquals(v5, v4.getNextVertex(vertices[2]));
		assertEquals(v6, v4.getNextVertex(vertices[3]));

		assertEquals(v6, v5.getNextVertex(vertices[0]));
		assertEquals(v6, v5.getNextVertex(vertices[1]));
		assertEquals(v6, v5.getNextVertex(vertices[2]));
		assertEquals(v6, v5.getNextVertex(vertices[3]));

		assertNull(v6.getNextVertex(vertices[0]));
		assertNull(v6.getNextVertex(vertices[1]));
		assertNull(v6.getNextVertex(vertices[2]));
		assertNull(v6.getNextVertex(vertices[3]));
		commit(g);
	}

	/**
	 * RandomTests
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestVertexClass4() throws CommitFailedException {
		createReadOnlyTransaction(g);
		VertexClass[] vClasses = getVertexClasses();
		commit(g);

		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			// all vertices in the graph
			Vertex[] vertices = new Vertex[20];
			// the next AbstractSuperNode for the i-th Vertex
			Vertex[] nextAbstractSuperNode = new Vertex[vertices.length];
			// the position of the last Vertex which has a nextApstractSuperNode
			int lastAbstractSuperNode = -1;
			Vertex[] nextSubNode = new Vertex[vertices.length];
			int lastSubNode = -1;
			Vertex[] nextSuperNode = new Vertex[vertices.length];
			int lastSuperNode = -1;
			Vertex[] nextDoubleSubNode = new Vertex[vertices.length];
			int lastDoubleSubNode = -1;
			for (int j = 0; j < vertices.length; j++) {
				// create vertices
				int vclass = rand.nextInt(3);
				switch (vclass) {
				case 0:
					vertices[j] = g.createSubNode();
					// all vertices until j(exclusive) have the new vertex as
					// next AbstractSuperNode
					while (lastAbstractSuperNode < j) {
						if (lastAbstractSuperNode >= 0) {
							nextAbstractSuperNode[lastAbstractSuperNode] = vertices[j];
						}
						lastAbstractSuperNode++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SubNode
					while (lastSubNode < j) {
						if (lastSubNode >= 0) {
							nextSubNode[lastSubNode] = vertices[j];
						}
						lastSubNode++;
					}
					break;
				case 1:
					vertices[j] = g.createSuperNode();
					// all vertices until j(exclusive) have the new vertex as
					// next SuperNode
					while (lastSuperNode < j) {
						if (lastSuperNode >= 0) {
							nextSuperNode[lastSuperNode] = vertices[j];
						}
						lastSuperNode++;
					}
					break;
				case 2:
					vertices[j] = g.createDoubleSubNode();
					// all vertices until j(exclusive) have the new vertex as
					// next AbstractSuperNode
					while (lastAbstractSuperNode < j) {
						if (lastAbstractSuperNode >= 0) {
							nextAbstractSuperNode[lastAbstractSuperNode] = vertices[j];
						}
						lastAbstractSuperNode++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SubNode
					while (lastSubNode < j) {
						if (lastSubNode >= 0) {
							nextSubNode[lastSubNode] = vertices[j];
						}
						lastSubNode++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SuperNode
					while (lastSuperNode < j) {
						if (lastSuperNode >= 0) {
							nextSuperNode[lastSuperNode] = vertices[j];
						}
						lastSuperNode++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next DoubleSubNode
					while (lastDoubleSubNode < j) {
						if (lastDoubleSubNode >= 0) {
							nextDoubleSubNode[lastDoubleSubNode] = vertices[j];
						}
						lastDoubleSubNode++;
					}
					break;
				}
			}
			commit(g);
			// check nextVertex after creating
			createReadOnlyTransaction(g);
			for (int j = 0; j < vertices.length; j++) {
				assertEquals(nextAbstractSuperNode[j],
						vertices[j].getNextVertex(vClasses[0]));
				assertEquals(nextSubNode[j],
						vertices[j].getNextVertex(vClasses[1]));
				assertEquals(nextSuperNode[j],
						vertices[j].getNextVertex(vClasses[2]));
				assertEquals(nextDoubleSubNode[j],
						vertices[j].getNextVertex(vClasses[3]));
			}
			commit(g);
		}
	}

	// tests of the method getNextVertex(Class<? extends Vertex>
	// aSchemaVertexClass);

	/**
	 * Tests if there is only one vertex in the graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestClass0() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertNull(v.getNextVertex(AbstractSuperNode.class));
		assertNull(v.getNextVertex(SubNode.class));
		assertNull(v.getNextVertex(SuperNode.class));
		assertNull(v.getNextVertex(DoubleSubNode.class));
		commit(g);
	}

	/**
	 * The next vertex is an instance of a class which is a subclass of another
	 * vertexclass.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestClass1() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v1, v0.getNextVertex(AbstractSuperNode.class));
		assertEquals(v1, v0.getNextVertex(SubNode.class));
		assertNull(v0.getNextVertex(SuperNode.class));
		assertNull(v0.getNextVertex(DoubleSubNode.class));
		commit(g);
	}

	/**
	 * The next vertex is an instance of a class which is a subclass of tow
	 * other vertexclasses.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestClass2() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v1, v0.getNextVertex(AbstractSuperNode.class));
		assertEquals(v1, v0.getNextVertex(SubNode.class));
		assertEquals(v1, v0.getNextVertex(SuperNode.class));
		assertEquals(v1, v0.getNextVertex(DoubleSubNode.class));
		commit(g);
	}

	/**
	 * Test in a manually build graph: SubNode SuperNode DoubleSubNode SuperNode
	 * SubNode SuperNode DoubleSubNode
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestClass3() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createSubNode();
		Vertex v1 = g.createSuperNode();
		Vertex v2 = g.createDoubleSubNode();
		Vertex v3 = g.createSuperNode();
		Vertex v4 = g.createSubNode();
		Vertex v5 = g.createSuperNode();
		Vertex v6 = g.createDoubleSubNode();
		commit(g);

		createReadOnlyTransaction(g);
		assertEquals(v2, v0.getNextVertex(AbstractSuperNode.class));
		assertEquals(v2, v0.getNextVertex(SubNode.class));
		assertEquals(v1, v0.getNextVertex(SuperNode.class));
		assertEquals(v2, v0.getNextVertex(DoubleSubNode.class));

		assertEquals(v2, v1.getNextVertex(AbstractSuperNode.class));
		assertEquals(v2, v1.getNextVertex(SubNode.class));
		assertEquals(v2, v1.getNextVertex(SuperNode.class));
		assertEquals(v2, v1.getNextVertex(DoubleSubNode.class));

		assertEquals(v4, v2.getNextVertex(AbstractSuperNode.class));
		assertEquals(v4, v2.getNextVertex(SubNode.class));
		assertEquals(v3, v2.getNextVertex(SuperNode.class));
		assertEquals(v6, v2.getNextVertex(DoubleSubNode.class));

		assertEquals(v4, v3.getNextVertex(AbstractSuperNode.class));
		assertEquals(v4, v3.getNextVertex(SubNode.class));
		assertEquals(v5, v3.getNextVertex(SuperNode.class));
		assertEquals(v6, v3.getNextVertex(DoubleSubNode.class));

		assertEquals(v6, v4.getNextVertex(AbstractSuperNode.class));
		assertEquals(v6, v4.getNextVertex(SubNode.class));
		assertEquals(v5, v4.getNextVertex(SuperNode.class));
		assertEquals(v6, v4.getNextVertex(DoubleSubNode.class));

		assertEquals(v6, v5.getNextVertex(AbstractSuperNode.class));
		assertEquals(v6, v5.getNextVertex(SubNode.class));
		assertEquals(v6, v5.getNextVertex(SuperNode.class));
		assertEquals(v6, v5.getNextVertex(DoubleSubNode.class));

		assertNull(v6.getNextVertex(AbstractSuperNode.class));
		assertNull(v6.getNextVertex(SubNode.class));
		assertNull(v6.getNextVertex(SuperNode.class));
		assertNull(v6.getNextVertex(DoubleSubNode.class));
		commit(g);
	}

	/**
	 * RandomTests
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestClass4() throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			// all vertices in the graph
			Vertex[] vertices = new Vertex[20];
			// the next AbstractSuperNode for the i-th Vertex
			Vertex[] nextAbstractSuperNode = new Vertex[vertices.length];
			// the position of the last Vertex which has a nextApstractSuperNode
			int lastAbstractSuperNode = -1;
			Vertex[] nextSubNode = new Vertex[vertices.length];
			int lastSubNode = -1;
			Vertex[] nextSuperNode = new Vertex[vertices.length];
			int lastSuperNode = -1;
			Vertex[] nextDoubleSubNode = new Vertex[vertices.length];
			int lastDoubleSubNode = -1;
			for (int j = 0; j < vertices.length; j++) {
				// create vertices
				int vclass = rand.nextInt(3);
				switch (vclass) {
				case 0:
					vertices[j] = g.createSubNode();
					// all vertices until j(exclusive) have the new vertex as
					// next AbstractSuperNode
					while (lastAbstractSuperNode < j) {
						if (lastAbstractSuperNode >= 0) {
							nextAbstractSuperNode[lastAbstractSuperNode] = vertices[j];
						}
						lastAbstractSuperNode++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SubNode
					while (lastSubNode < j) {
						if (lastSubNode >= 0) {
							nextSubNode[lastSubNode] = vertices[j];
						}
						lastSubNode++;
					}
					break;
				case 1:
					vertices[j] = g.createSuperNode();
					// all vertices until j(exclusive) have the new vertex as
					// next SuperNode
					while (lastSuperNode < j) {
						if (lastSuperNode >= 0) {
							nextSuperNode[lastSuperNode] = vertices[j];
						}
						lastSuperNode++;
					}
					break;
				case 2:
					vertices[j] = g.createDoubleSubNode();
					// all vertices until j(exclusive) have the new vertex as
					// next AbstractSuperNode
					while (lastAbstractSuperNode < j) {
						if (lastAbstractSuperNode >= 0) {
							nextAbstractSuperNode[lastAbstractSuperNode] = vertices[j];
						}
						lastAbstractSuperNode++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SubNode
					while (lastSubNode < j) {
						if (lastSubNode >= 0) {
							nextSubNode[lastSubNode] = vertices[j];
						}
						lastSubNode++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SuperNode
					while (lastSuperNode < j) {
						if (lastSuperNode >= 0) {
							nextSuperNode[lastSuperNode] = vertices[j];
						}
						lastSuperNode++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next DoubleSubNode
					while (lastDoubleSubNode < j) {
						if (lastDoubleSubNode >= 0) {
							nextDoubleSubNode[lastDoubleSubNode] = vertices[j];
						}
						lastDoubleSubNode++;
					}
					break;
				}
			}
			commit(g);
			createReadOnlyTransaction(g);
			// check nextVertex after creating
			for (int j = 0; j < vertices.length; j++) {
				assertEquals(nextAbstractSuperNode[j],
						vertices[j].getNextVertex(AbstractSuperNode.class));
				assertEquals(nextSubNode[j],
						vertices[j].getNextVertex(SubNode.class));
				assertEquals(nextSuperNode[j],
						vertices[j].getNextVertex(SuperNode.class));
				assertEquals(nextDoubleSubNode[j],
						vertices[j].getNextVertex(DoubleSubNode.class));
			}
			commit(g);
		}
	}

	/**
	 * RandomTests
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestVertexClassBoolean4()
			throws CommitFailedException {
		createTransaction(g);
		VertexClass[] vClasses = getVertexClasses();
		commit(g);
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			// all vertices in the graph
			createTransaction(g);
			Vertex[] vertices = new Vertex[20];
			// the next AbstractSuperNode for the i-th Vertex
			Vertex[] nextAbstractSuperNodeFalse = new Vertex[vertices.length];
			// the position of the last Vertex which has a next
			// AbstractSuperNode
			int lastAbstractSuperNodeFalse = -1;
			// the next AbstractSuperNode(no subclasses) for the i-th Vertex
			// Vertex[] nextAbstractSuperNodeTrue = new Vertex[vertices.length];
			// the position of the last Vertex which has a next
			// AbstractSuperNode(no subclasses)
			// int lastAbstractSuperNodeTrue = -1;
			Vertex[] nextSubNodeFalse = new Vertex[vertices.length];
			int lastSubNodeFalse = -1;
			Vertex[] nextSubNodeTrue = new Vertex[vertices.length];
			int lastSubNodeTrue = -1;
			Vertex[] nextSuperNodeFalse = new Vertex[vertices.length];
			int lastSuperNodeFalse = -1;
			Vertex[] nextSuperNodeTrue = new Vertex[vertices.length];
			int lastSuperNodeTrue = -1;
			Vertex[] nextDoubleSubNodeFalse = new Vertex[vertices.length];
			int lastDoubleSubNodeFalse = -1;
			Vertex[] nextDoubleSubNodeTrue = new Vertex[vertices.length];
			int lastDoubleSubNodeTrue = -1;
			for (int j = 0; j < vertices.length; j++) {
				// create vertices
				int vclass = rand.nextInt(3);
				switch (vclass) {
				case 0:
					vertices[j] = g.createSubNode();
					// all vertices until j(exclusive) have the new vertex as
					// next AbstractSuperNode
					while (lastAbstractSuperNodeFalse < j) {
						if (lastAbstractSuperNodeFalse >= 0) {
							nextAbstractSuperNodeFalse[lastAbstractSuperNodeFalse] = vertices[j];
						}
						lastAbstractSuperNodeFalse++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SubNode
					while (lastSubNodeFalse < j) {
						if (lastSubNodeFalse >= 0) {
							nextSubNodeFalse[lastSubNodeFalse] = vertices[j];
						}
						lastSubNodeFalse++;
					}
					// with no subclasses
					while (lastSubNodeTrue < j) {
						if (lastSubNodeTrue >= 0) {
							nextSubNodeTrue[lastSubNodeTrue] = vertices[j];
						}
						lastSubNodeTrue++;
					}
					break;
				case 1:
					vertices[j] = g.createSuperNode();
					// all vertices until j(exclusive) have the new vertex as
					// next SuperNode
					while (lastSuperNodeFalse < j) {
						if (lastSuperNodeFalse >= 0) {
							nextSuperNodeFalse[lastSuperNodeFalse] = vertices[j];
						}
						lastSuperNodeFalse++;
					}
					// with no subclasses
					while (lastSuperNodeTrue < j) {
						if (lastSuperNodeTrue >= 0) {
							nextSuperNodeTrue[lastSuperNodeTrue] = vertices[j];
						}
						lastSuperNodeTrue++;
					}
					break;
				case 2:
					vertices[j] = g.createDoubleSubNode();
					// all vertices until j(exclusive) have the new vertex as
					// next AbstractSuperNode
					while (lastAbstractSuperNodeFalse < j) {
						if (lastAbstractSuperNodeFalse >= 0) {
							nextAbstractSuperNodeFalse[lastAbstractSuperNodeFalse] = vertices[j];
						}
						lastAbstractSuperNodeFalse++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SubNode
					while (lastSubNodeFalse < j) {
						if (lastSubNodeFalse >= 0) {
							nextSubNodeFalse[lastSubNodeFalse] = vertices[j];
						}
						lastSubNodeFalse++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SuperNode
					while (lastSuperNodeFalse < j) {
						if (lastSuperNodeFalse >= 0) {
							nextSuperNodeFalse[lastSuperNodeFalse] = vertices[j];
						}
						lastSuperNodeFalse++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next DoubleSubNode
					while (lastDoubleSubNodeFalse < j) {
						if (lastDoubleSubNodeFalse >= 0) {
							nextDoubleSubNodeFalse[lastDoubleSubNodeFalse] = vertices[j];
						}
						lastDoubleSubNodeFalse++;
					}
					// with no subclasses
					while (lastDoubleSubNodeTrue < j) {
						if (lastDoubleSubNodeTrue >= 0) {
							nextDoubleSubNodeTrue[lastDoubleSubNodeTrue] = vertices[j];
						}
						lastDoubleSubNodeTrue++;
					}
					break;
				}
			}
			commit(g);
			// check nextVertex after creating
			createReadOnlyTransaction(g);
			for (int j = 0; j < vertices.length; j++) {
				assertEquals(nextAbstractSuperNodeFalse[j],
						vertices[j].getNextVertex(vClasses[0]));
				assertEquals(nextSubNodeFalse[j],
						vertices[j].getNextVertex(vClasses[1]));
				assertEquals(nextSuperNodeFalse[j],
						vertices[j].getNextVertex(vClasses[2]));
				assertEquals(nextDoubleSubNodeFalse[j],
						vertices[j].getNextVertex(vClasses[3]));
			}
			commit(g);
		}
	}

	/**
	 * RandomTests
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getNextVertexTestVertexBoolean4() throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			// all vertices in the graph
			Vertex[] vertices = new Vertex[20];
			// the next AbstractSuperNode for the i-th Vertex
			Vertex[] nextAbstractSuperNodeFalse = new Vertex[vertices.length];
			// the position of the last Vertex which has a next
			// AbstractSuperNode
			int lastAbstractSuperNodeFalse = -1;
			// the next AbstractSuperNode(no subclasses) for the i-th Vertex
			// Vertex[] nextAbstractSuperNodeTrue = new Vertex[vertices.length];
			// the position of the last Vertex which has a next
			// AbstractSuperNode(no subclasses)
			// int lastAbstractSuperNodeTrue = -1;
			Vertex[] nextSubNodeFalse = new Vertex[vertices.length];
			int lastSubNodeFalse = -1;
			Vertex[] nextSubNodeTrue = new Vertex[vertices.length];
			int lastSubNodeTrue = -1;
			Vertex[] nextSuperNodeFalse = new Vertex[vertices.length];
			int lastSuperNodeFalse = -1;
			Vertex[] nextSuperNodeTrue = new Vertex[vertices.length];
			int lastSuperNodeTrue = -1;
			Vertex[] nextDoubleSubNodeFalse = new Vertex[vertices.length];
			int lastDoubleSubNodeFalse = -1;
			Vertex[] nextDoubleSubNodeTrue = new Vertex[vertices.length];
			int lastDoubleSubNodeTrue = -1;
			for (int j = 0; j < vertices.length; j++) {
				// create vertices
				int vclass = rand.nextInt(3);
				switch (vclass) {
				case 0:
					vertices[j] = g.createSubNode();
					// all vertices until j(exclusive) have the new vertex as
					// next AbstractSuperNode
					while (lastAbstractSuperNodeFalse < j) {
						if (lastAbstractSuperNodeFalse >= 0) {
							nextAbstractSuperNodeFalse[lastAbstractSuperNodeFalse] = vertices[j];
						}
						lastAbstractSuperNodeFalse++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SubNode
					while (lastSubNodeFalse < j) {
						if (lastSubNodeFalse >= 0) {
							nextSubNodeFalse[lastSubNodeFalse] = vertices[j];
						}
						lastSubNodeFalse++;
					}
					// with no subclasses
					while (lastSubNodeTrue < j) {
						if (lastSubNodeTrue >= 0) {
							nextSubNodeTrue[lastSubNodeTrue] = vertices[j];
						}
						lastSubNodeTrue++;
					}
					break;
				case 1:
					vertices[j] = g.createSuperNode();
					// all vertices until j(exclusive) have the new vertex as
					// next SuperNode
					while (lastSuperNodeFalse < j) {
						if (lastSuperNodeFalse >= 0) {
							nextSuperNodeFalse[lastSuperNodeFalse] = vertices[j];
						}
						lastSuperNodeFalse++;
					}
					// with no subclasses
					while (lastSuperNodeTrue < j) {
						if (lastSuperNodeTrue >= 0) {
							nextSuperNodeTrue[lastSuperNodeTrue] = vertices[j];
						}
						lastSuperNodeTrue++;
					}
					break;
				case 2:
					vertices[j] = g.createDoubleSubNode();
					// all vertices until j(exclusive) have the new vertex as
					// next AbstractSuperNode
					while (lastAbstractSuperNodeFalse < j) {
						if (lastAbstractSuperNodeFalse >= 0) {
							nextAbstractSuperNodeFalse[lastAbstractSuperNodeFalse] = vertices[j];
						}
						lastAbstractSuperNodeFalse++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SubNode
					while (lastSubNodeFalse < j) {
						if (lastSubNodeFalse >= 0) {
							nextSubNodeFalse[lastSubNodeFalse] = vertices[j];
						}
						lastSubNodeFalse++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next SuperNode
					while (lastSuperNodeFalse < j) {
						if (lastSuperNodeFalse >= 0) {
							nextSuperNodeFalse[lastSuperNodeFalse] = vertices[j];
						}
						lastSuperNodeFalse++;
					}
					// all vertices until j(exclusive) have the new vertex as
					// next DoubleSubNode
					while (lastDoubleSubNodeFalse < j) {
						if (lastDoubleSubNodeFalse >= 0) {
							nextDoubleSubNodeFalse[lastDoubleSubNodeFalse] = vertices[j];
						}
						lastDoubleSubNodeFalse++;
					}
					// with no subclasses
					while (lastDoubleSubNodeTrue < j) {
						if (lastDoubleSubNodeTrue >= 0) {
							nextDoubleSubNodeTrue[lastDoubleSubNodeTrue] = vertices[j];
						}
						lastDoubleSubNodeTrue++;
					}
					break;
				}
			}
			commit(g);
			createReadOnlyTransaction(g);
			// check nextVertex after creating
			for (int j = 0; j < vertices.length; j++) {
				assertEquals(nextAbstractSuperNodeFalse[j],
						vertices[j].getNextVertex(AbstractSuperNode.class));
				assertEquals(nextSubNodeFalse[j],
						vertices[j].getNextVertex(SubNode.class));
				assertEquals(nextSuperNodeFalse[j],
						vertices[j].getNextVertex(SuperNode.class));
				assertEquals(nextDoubleSubNodeFalse[j],
						vertices[j].getNextVertex(DoubleSubNode.class));
			}
			commit(g);
		}
	}

	// tests of the method Edge getLastEdge();
	// (tested in IncidenceListTest)

	// tests of the method Edge getFirstEdge();
	// (tested in IncidenceListTest)

	// tests of the method Edge getFirstEdge(EdgeDirection orientation);

	/**
	 * Tests if a node has no Edges
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeDirection0() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertNull(v0.getFirstIncidence(EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(EdgeDirection.OUT));
		commit(g);
	}

	/**
	 * Tests if a node has only one Edge
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeDirection1() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e.getReversedEdge(),
				v1.getFirstIncidence(EdgeDirection.INOUT));
		assertEquals(e.getReversedEdge(),
				v1.getFirstIncidence(EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(EdgeDirection.OUT));
		assertEquals(e, v0.getFirstIncidence(EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(EdgeDirection.IN));
		assertEquals(e, v0.getFirstIncidence(EdgeDirection.OUT));
		commit(g);
	}

	/**
	 * Tests if a node has two Edges with the same direction.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeDirection2() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(EdgeDirection.INOUT));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(EdgeDirection.OUT));
		assertEquals(e1, v0.getFirstIncidence(EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(EdgeDirection.IN));
		assertEquals(e1, v0.getFirstIncidence(EdgeDirection.OUT));
		commit(g);
	}

	/**
	 * Tests if a node has two Edges with different direction.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeDirection3() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge e2 = g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(EdgeDirection.INOUT));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(EdgeDirection.IN));
		assertEquals(e2, v1.getFirstIncidence(EdgeDirection.OUT));
		assertEquals(e1, v0.getFirstIncidence(EdgeDirection.INOUT));
		assertEquals(e2.getReversedEdge(),
				v0.getFirstIncidence(EdgeDirection.IN));
		assertEquals(e1, v0.getFirstIncidence(EdgeDirection.OUT));
		commit(g);
	}

	/**
	 * Tests if alpha and omega of an Edge is the same Vertex.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeDirection4() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(EdgeDirection.INOUT));
		assertEquals(e1.getReversedEdge(),
				v0.getFirstIncidence(EdgeDirection.IN));
		assertEquals(e1, v0.getFirstIncidence(EdgeDirection.OUT));
		commit(g);
	}

	/**
	 * Random tests
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeDirection5() throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			Vertex[] vertices = new Vertex[] { g.createSubNode(),
					g.createDoubleSubNode(), g.createSuperNode() };
			Edge[] firstInEdge = new Edge[3];
			Edge[] firstOutEdge = new Edge[3];
			Edge[] firstInOutEdge = new Edge[3];
			for (int j = 0; j < 5; j++) {
				int edgetype = rand.nextInt(3);
				int start = rand.nextInt(2);
				int end = rand.nextInt(2) + 1;
				switch (edgetype) {
				case 0:
					Edge e0 = g.createLink((AbstractSuperNode) vertices[start],
							(SuperNode) vertices[end]);
					if (firstOutEdge[start] == null) {
						firstOutEdge[start] = e0;
					}
					if (firstInOutEdge[start] == null) {
						firstInOutEdge[start] = e0;
					}
					if (firstInEdge[end] == null) {
						firstInEdge[end] = e0.getReversedEdge();
					}
					if ((firstInOutEdge[end] == null) && (start != end)) {
						firstInOutEdge[end] = e0.getReversedEdge();
					}
					break;
				case 1:
					Edge e1 = g.createLinkBack((SuperNode) vertices[end],
							(AbstractSuperNode) vertices[start]);
					if (firstOutEdge[end] == null) {
						firstOutEdge[end] = e1;
					}
					if (firstInOutEdge[end] == null) {
						firstInOutEdge[end] = e1;
					}
					if (firstInEdge[start] == null) {
						firstInEdge[start] = e1.getReversedEdge();
					}
					if ((firstInOutEdge[start] == null) && (start != end)) {
						firstInOutEdge[start] = e1.getReversedEdge();
					}
					break;
				case 2:
					Edge e2 = g.createSubLink((DoubleSubNode) vertices[1],
							(SuperNode) vertices[end]);
					if (firstOutEdge[1] == null) {
						firstOutEdge[1] = e2;
					}
					if (firstInOutEdge[1] == null) {
						firstInOutEdge[1] = e2;
					}
					if (firstInEdge[end] == null) {
						firstInEdge[end] = e2.getReversedEdge();
					}
					if ((firstInOutEdge[end] == null) && (1 != end)) {
						firstInOutEdge[end] = e2.getReversedEdge();
					}
					break;
				}
			}
			commit(g);
			createReadOnlyTransaction(g);
			assertEquals(firstInEdge[0],
					vertices[0].getFirstIncidence(EdgeDirection.IN));
			assertEquals(firstInEdge[1],
					vertices[1].getFirstIncidence(EdgeDirection.IN));
			assertEquals(firstInEdge[2],
					vertices[2].getFirstIncidence(EdgeDirection.IN));
			assertEquals(firstOutEdge[0],
					vertices[0].getFirstIncidence(EdgeDirection.OUT));
			assertEquals(firstOutEdge[1],
					vertices[1].getFirstIncidence(EdgeDirection.OUT));
			assertEquals(firstOutEdge[2],
					vertices[2].getFirstIncidence(EdgeDirection.OUT));
			assertEquals(firstInOutEdge[0],
					vertices[0].getFirstIncidence(EdgeDirection.INOUT));
			assertEquals(firstInOutEdge[1],
					vertices[1].getFirstIncidence(EdgeDirection.INOUT));
			assertEquals(firstInOutEdge[2],
					vertices[2].getFirstIncidence(EdgeDirection.INOUT));
			commit(g);
		}
	}

	// tests of the method Edge getFirstEdgeOfClass(EdgeClass anEdgeClass);

	/**
	 * Tests if a node has no Edges
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClass0() throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertNull(v0.getFirstIncidence(eclasses[0]));
		assertNull(v0.getFirstIncidence(eclasses[1]));
		assertNull(v0.getFirstIncidence(eclasses[2]));
		commit(g);
	}

	/**
	 * Tests if a node has only one Edge
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClass1() throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e, v0.getFirstIncidence(eclasses[0]));
		assertNull(v0.getFirstIncidence(eclasses[1]));
		assertNull(v0.getFirstIncidence(eclasses[2]));
		assertEquals(e.getReversedEdge(), v1.getFirstIncidence(eclasses[0]));
		assertNull(v1.getFirstIncidence(eclasses[1]));
		assertNull(v1.getFirstIncidence(eclasses[2]));
		commit(g);
	}

	/**
	 * Tests if a node has an edge which extends another edge
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClass2() throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createSubLink((DoubleSubNode) v0, (SuperNode) v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(eclasses[0]));
		assertEquals(e1, v0.getFirstIncidence(eclasses[1]));
		assertNull(v0.getFirstIncidence(eclasses[2]));
		assertEquals(e1.getReversedEdge(), v1.getFirstIncidence(eclasses[0]));
		assertEquals(e1.getReversedEdge(), v1.getFirstIncidence(eclasses[1]));
		assertNull(v1.getFirstIncidence(eclasses[2]));
		commit(g);
	}

	/**
	 * Tests if a node has two Edges.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClass3() throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge e2 = g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(eclasses[0]));
		assertNull(v0.getFirstIncidence(eclasses[1]));
		assertEquals(e2.getReversedEdge(), v0.getFirstIncidence(eclasses[2]));
		assertEquals(e1.getReversedEdge(), v1.getFirstIncidence(eclasses[0]));
		assertNull(v1.getFirstIncidence(eclasses[1]));
		assertEquals(e2, v1.getFirstIncidence(eclasses[2]));
		commit(g);
	}

	/**
	 * Tests if alpha and omega of an Edge is the same Vertex.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClass4() throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(eclasses[0]));
		assertNull(v0.getFirstIncidence(eclasses[1]));
		assertNull(v0.getFirstIncidence(eclasses[2]));
		commit(g);
	}

	/**
	 * Random tests
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClass5() throws CommitFailedException {
		createReadOnlyTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		commit(g);
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			Vertex[] vertices = new Vertex[] { g.createSubNode(),
					g.createDoubleSubNode(), g.createSuperNode() };
			Edge[] firstLink = new Edge[3];
			Edge[] firstLinkBack = new Edge[3];
			Edge[] firstSubLink = new Edge[3];
			for (int j = 0; j < 5; j++) {
				int edgetype = rand.nextInt(3);
				int start = rand.nextInt(2);
				int end = rand.nextInt(2) + 1;
				switch (edgetype) {
				case 0:
					Edge e0 = g.createLink((AbstractSuperNode) vertices[start],
							(SuperNode) vertices[end]);
					if (firstLink[start] == null) {
						firstLink[start] = e0;
					}
					if (firstLink[end] == null) {
						firstLink[end] = e0.getReversedEdge();
					}
					break;
				case 1:
					Edge e1 = g.createLinkBack((SuperNode) vertices[end],
							(AbstractSuperNode) vertices[start]);
					if (firstLinkBack[end] == null) {
						firstLinkBack[end] = e1;
					}
					if (firstLinkBack[start] == null) {
						firstLinkBack[start] = e1.getReversedEdge();
					}
					break;
				case 2:
					Edge e2 = g.createSubLink((DoubleSubNode) vertices[1],
							(SuperNode) vertices[end]);
					if (firstLink[1] == null) {
						firstLink[1] = e2;
					}
					if (firstSubLink[1] == null) {
						firstSubLink[1] = e2;
					}
					if (firstLink[end] == null) {
						firstLink[end] = e2.getReversedEdge();
					}
					if (firstSubLink[end] == null) {
						firstSubLink[end] = e2.getReversedEdge();
					}
					break;
				}
			}
			commit(g);
			createReadOnlyTransaction(g);
			assertEquals(firstLink[0],
					vertices[0].getFirstIncidence(eclasses[0]));
			assertEquals(firstLink[1],
					vertices[1].getFirstIncidence(eclasses[0]));
			assertEquals(firstLink[2],
					vertices[2].getFirstIncidence(eclasses[0]));
			assertEquals(firstLinkBack[0],
					vertices[0].getFirstIncidence(eclasses[2]));
			assertEquals(firstLinkBack[1],
					vertices[1].getFirstIncidence(eclasses[2]));
			assertEquals(firstLinkBack[2],
					vertices[2].getFirstIncidence(eclasses[2]));
			assertEquals(firstSubLink[0],
					vertices[0].getFirstIncidence(eclasses[1]));
			assertEquals(firstSubLink[1],
					vertices[1].getFirstIncidence(eclasses[1]));
			assertEquals(firstSubLink[2],
					vertices[2].getFirstIncidence(eclasses[1]));
			commit(g);
		}
	}

	// tests of the method Edge getFirstEdgeOfClass(Class<? extends Edge>
	// anEdgeClass);

	/**
	 * Tests if a node has no Edges
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClass0() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertNull(v0.getFirstIncidence(Link.class));
		assertNull(v0.getFirstIncidence(SubLink.class));
		assertNull(v0.getFirstIncidence(LinkBack.class));
		commit(g);
	}

	/**
	 * Tests if a node has only one Edge
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClass1() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e, v0.getFirstIncidence(Link.class));
		assertNull(v0.getFirstIncidence(SubLink.class));
		assertNull(v0.getFirstIncidence(LinkBack.class));
		assertEquals(e.getReversedEdge(), v1.getFirstIncidence(Link.class));
		assertNull(v1.getFirstIncidence(SubLink.class));
		assertNull(v1.getFirstIncidence(LinkBack.class));
		commit(g);
	}

	/**
	 * Tests if a node has an edge which extends another edge
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClass2() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createSubLink((DoubleSubNode) v0, (SuperNode) v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(Link.class));
		assertEquals(e1, v0.getFirstIncidence(SubLink.class));
		assertNull(v0.getFirstIncidence(LinkBack.class));
		assertEquals(e1.getReversedEdge(), v1.getFirstIncidence(Link.class));
		assertEquals(e1.getReversedEdge(), v1.getFirstIncidence(SubLink.class));
		assertNull(v1.getFirstIncidence(LinkBack.class));
		commit(g);
	}

	/**
	 * Tests if a node has two Edges.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClass3() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge e2 = g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(Link.class));
		assertNull(v0.getFirstIncidence(SubLink.class));
		assertEquals(e2.getReversedEdge(), v0.getFirstIncidence(LinkBack.class));
		assertEquals(e1.getReversedEdge(), v1.getFirstIncidence(Link.class));
		assertNull(v1.getFirstIncidence(SubLink.class));
		assertEquals(e2, v1.getFirstIncidence(LinkBack.class));
		commit(g);
	}

	/**
	 * Tests if alpha and omega of an Edge is the same Vertex.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClass4() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(Link.class));
		assertNull(v0.getFirstIncidence(SubLink.class));
		assertNull(v0.getFirstIncidence(LinkBack.class));
		commit(g);
	}

	/**
	 * Random tests
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClass5() throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			Vertex[] vertices = new Vertex[] { g.createSubNode(),
					g.createDoubleSubNode(), g.createSuperNode() };
			Edge[] firstLink = new Edge[3];
			Edge[] firstLinkBack = new Edge[3];
			Edge[] firstSubLink = new Edge[3];
			for (int j = 0; j < 5; j++) {
				int edgetype = rand.nextInt(3);
				int start = rand.nextInt(2);
				int end = rand.nextInt(2) + 1;
				switch (edgetype) {
				case 0:
					Edge e0 = g.createLink((AbstractSuperNode) vertices[start],
							(SuperNode) vertices[end]);
					if (firstLink[start] == null) {
						firstLink[start] = e0;
					}
					if (firstLink[end] == null) {
						firstLink[end] = e0.getReversedEdge();
					}
					break;
				case 1:
					Edge e1 = g.createLinkBack((SuperNode) vertices[end],
							(AbstractSuperNode) vertices[start]);
					if (firstLinkBack[end] == null) {
						firstLinkBack[end] = e1;
					}
					if (firstLinkBack[start] == null) {
						firstLinkBack[start] = e1.getReversedEdge();
					}
					break;
				case 2:
					Edge e2 = g.createSubLink((DoubleSubNode) vertices[1],
							(SuperNode) vertices[end]);
					if (firstLink[1] == null) {
						firstLink[1] = e2;
					}
					if (firstSubLink[1] == null) {
						firstSubLink[1] = e2;
					}
					if (firstLink[end] == null) {
						firstLink[end] = e2.getReversedEdge();
					}
					if (firstSubLink[end] == null) {
						firstSubLink[end] = e2.getReversedEdge();
					}
					break;
				}
			}
			commit(g);
			createReadOnlyTransaction(g);
			assertEquals(firstLink[0],
					vertices[0].getFirstIncidence(Link.class));
			assertEquals(firstLink[1],
					vertices[1].getFirstIncidence(Link.class));
			assertEquals(firstLink[2],
					vertices[2].getFirstIncidence(Link.class));
			assertEquals(firstLinkBack[0],
					vertices[0].getFirstIncidence(LinkBack.class));
			assertEquals(firstLinkBack[1],
					vertices[1].getFirstIncidence(LinkBack.class));
			assertEquals(firstLinkBack[2],
					vertices[2].getFirstIncidence(LinkBack.class));
			assertEquals(firstSubLink[0],
					vertices[0].getFirstIncidence(SubLink.class));
			assertEquals(firstSubLink[1],
					vertices[1].getFirstIncidence(SubLink.class));
			assertEquals(firstSubLink[2],
					vertices[2].getFirstIncidence(SubLink.class));
			commit(g);
		}
	}

	// tests of the method Edge getFirstEdgeOfClass(EdgeClass anEdgeClass,
	// EdgeDirection orientation);

	/**
	 * Tests if a node has no Edges
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClassEdgeDirection0()
			throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		commit(g);

		createReadOnlyTransaction(g);
		assertNull(v0.getFirstIncidence(eclasses[0], EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.INOUT));

		assertNull(v0.getFirstIncidence(eclasses[0], EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.OUT));

		assertNull(v0.getFirstIncidence(eclasses[0], EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.IN));
		commit(g);
	}

	/**
	 * Tests if a node has only one Edge
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClassEdgeDirection1()
			throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		commit(g);

		createReadOnlyTransaction(g);
		assertEquals(e, v0.getFirstIncidence(eclasses[0], EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.INOUT));
		assertEquals(e.getReversedEdge(),
				v1.getFirstIncidence(eclasses[0], EdgeDirection.INOUT));
		assertNull(v1.getFirstIncidence(eclasses[1], EdgeDirection.INOUT));
		assertNull(v1.getFirstIncidence(eclasses[2], EdgeDirection.INOUT));

		assertEquals(e, v0.getFirstIncidence(eclasses[0], EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(eclasses[0], EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(eclasses[1], EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(eclasses[2], EdgeDirection.OUT));

		assertNull(v0.getFirstIncidence(eclasses[0], EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.IN));
		assertEquals(e.getReversedEdge(),
				v1.getFirstIncidence(eclasses[0], EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(eclasses[1], EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(eclasses[2], EdgeDirection.IN));
		commit(g);
	}

	/**
	 * Tests if a node has an edge which extends another edge
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClassEdgeDirection2()
			throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createSubLink((DoubleSubNode) v0, (SuperNode) v1);
		commit(g);

		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(eclasses[0], EdgeDirection.INOUT));
		assertEquals(e1, v0.getFirstIncidence(eclasses[1], EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.INOUT));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(eclasses[0], EdgeDirection.INOUT));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(eclasses[1], EdgeDirection.INOUT));
		assertNull(v1.getFirstIncidence(eclasses[2], EdgeDirection.INOUT));

		assertEquals(e1, v0.getFirstIncidence(eclasses[0], EdgeDirection.OUT));
		assertEquals(e1, v0.getFirstIncidence(eclasses[1], EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(eclasses[0], EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(eclasses[1], EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(eclasses[2], EdgeDirection.OUT));

		assertNull(v0.getFirstIncidence(eclasses[0], EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.IN));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(eclasses[0], EdgeDirection.IN));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(eclasses[1], EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(eclasses[2], EdgeDirection.IN));
		commit(g);
	}

	/**
	 * Tests if a node has two Edges.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClassEdgeDirection3()
			throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge e2 = g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		commit(g);

		createTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(eclasses[0], EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.INOUT));
		assertEquals(e2.getReversedEdge(),
				v0.getFirstIncidence(eclasses[2], EdgeDirection.INOUT));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(eclasses[0], EdgeDirection.INOUT));
		assertNull(v1.getFirstIncidence(eclasses[1], EdgeDirection.INOUT));
		assertEquals(e2, v1.getFirstIncidence(eclasses[2], EdgeDirection.INOUT));

		assertEquals(e1, v0.getFirstIncidence(eclasses[0], EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(eclasses[0], EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(eclasses[1], EdgeDirection.OUT));
		assertEquals(e2, v1.getFirstIncidence(eclasses[2], EdgeDirection.OUT));

		assertNull(v0.getFirstIncidence(eclasses[0], EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.IN));
		assertEquals(e2.getReversedEdge(),
				v0.getFirstIncidence(eclasses[2], EdgeDirection.IN));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(eclasses[0], EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(eclasses[1], EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(eclasses[2], EdgeDirection.IN));
		commit(g);
	}

	/**
	 * Tests if alpha and omega of an Edge is the same Vertex.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClassEdgeDirection4()
			throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v0);
		commit(g);

		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(eclasses[0], EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.INOUT));

		assertEquals(e1, v0.getFirstIncidence(eclasses[0], EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.OUT));

		assertEquals(e1.getReversedEdge(),
				v0.getFirstIncidence(eclasses[0], EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(eclasses[1], EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(eclasses[2], EdgeDirection.IN));
		commit(g);
	}

	/**
	 * Random tests
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestEdgeClassEdgeDirection5()
			throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] eclasses = getEdgeClasses();
		commit(g);
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			Vertex[] vertices = new Vertex[] { g.createSubNode(),
					g.createDoubleSubNode(), g.createSuperNode() };
			Edge[] firstLinkInOut = new Edge[3];
			Edge[] firstLinkBackInOut = new Edge[3];
			Edge[] firstSubLinkInOut = new Edge[3];
			Edge[] firstLinkOut = new Edge[3];
			Edge[] firstLinkBackOut = new Edge[3];
			Edge[] firstSubLinkOut = new Edge[3];
			Edge[] firstLinkIn = new Edge[3];
			Edge[] firstLinkBackIn = new Edge[3];
			Edge[] firstSubLinkIn = new Edge[3];
			for (int j = 0; j < 5; j++) {
				int edgetype = rand.nextInt(3);
				int start = rand.nextInt(2);
				int end = rand.nextInt(2) + 1;
				switch (edgetype) {
				case 0:
					Edge e0 = g.createLink((AbstractSuperNode) vertices[start],
							(SuperNode) vertices[end]);
					if (firstLinkInOut[start] == null) {
						firstLinkInOut[start] = e0;
					}
					if (firstLinkOut[start] == null) {
						firstLinkOut[start] = e0;
					}
					if (firstLinkInOut[end] == null) {
						firstLinkInOut[end] = e0.getReversedEdge();
					}
					if (firstLinkIn[end] == null) {
						firstLinkIn[end] = e0.getReversedEdge();
					}
					break;
				case 1:
					Edge e1 = g.createLinkBack((SuperNode) vertices[end],
							(AbstractSuperNode) vertices[start]);
					if (firstLinkBackInOut[end] == null) {
						firstLinkBackInOut[end] = e1;
					}
					if (firstLinkBackOut[end] == null) {
						firstLinkBackOut[end] = e1;
					}
					if (firstLinkBackInOut[start] == null) {
						firstLinkBackInOut[start] = e1.getReversedEdge();
					}
					if (firstLinkBackIn[start] == null) {
						firstLinkBackIn[start] = e1.getReversedEdge();
					}
					break;
				case 2:
					Edge e2 = g.createSubLink((DoubleSubNode) vertices[1],
							(SuperNode) vertices[end]);
					if (firstLinkInOut[1] == null) {
						firstLinkInOut[1] = e2;
					}
					if (firstLinkOut[1] == null) {
						firstLinkOut[1] = e2;
					}
					if (firstSubLinkInOut[1] == null) {
						firstSubLinkInOut[1] = e2;
					}
					if (firstSubLinkOut[1] == null) {
						firstSubLinkOut[1] = e2;
					}
					if (firstLinkInOut[end] == null) {
						firstLinkInOut[end] = e2.getReversedEdge();
					}
					if (firstLinkIn[end] == null) {
						firstLinkIn[end] = e2.getReversedEdge();
					}
					if (firstSubLinkInOut[end] == null) {
						firstSubLinkInOut[end] = e2.getReversedEdge();
					}
					if (firstSubLinkIn[end] == null) {
						firstSubLinkIn[end] = e2.getReversedEdge();
					}
					break;
				}
			}
			commit(g);

			createReadOnlyTransaction(g);
			assertEquals(firstLinkInOut[0], vertices[0].getFirstIncidence(
					eclasses[0], EdgeDirection.INOUT));
			assertEquals(firstLinkInOut[1], vertices[1].getFirstIncidence(
					eclasses[0], EdgeDirection.INOUT));
			assertEquals(firstLinkInOut[2], vertices[2].getFirstIncidence(
					eclasses[0], EdgeDirection.INOUT));
			assertEquals(firstLinkBackInOut[0], vertices[0].getFirstIncidence(
					eclasses[2], EdgeDirection.INOUT));
			assertEquals(firstLinkBackInOut[1], vertices[1].getFirstIncidence(
					eclasses[2], EdgeDirection.INOUT));
			assertEquals(firstLinkBackInOut[2], vertices[2].getFirstIncidence(
					eclasses[2], EdgeDirection.INOUT));
			assertEquals(firstSubLinkInOut[0], vertices[0].getFirstIncidence(
					eclasses[1], EdgeDirection.INOUT));
			assertEquals(firstSubLinkInOut[1], vertices[1].getFirstIncidence(
					eclasses[1], EdgeDirection.INOUT));
			assertEquals(firstSubLinkInOut[2], vertices[2].getFirstIncidence(
					eclasses[1], EdgeDirection.INOUT));

			assertEquals(firstLinkOut[0], vertices[0].getFirstIncidence(
					eclasses[0], EdgeDirection.OUT));
			assertEquals(firstLinkOut[1], vertices[1].getFirstIncidence(
					eclasses[0], EdgeDirection.OUT));
			assertEquals(firstLinkOut[2], vertices[2].getFirstIncidence(
					eclasses[0], EdgeDirection.OUT));
			assertEquals(firstLinkBackOut[0], vertices[0].getFirstIncidence(
					eclasses[2], EdgeDirection.OUT));
			assertEquals(firstLinkBackOut[1], vertices[1].getFirstIncidence(
					eclasses[2], EdgeDirection.OUT));
			assertEquals(firstLinkBackOut[2], vertices[2].getFirstIncidence(
					eclasses[2], EdgeDirection.OUT));
			assertEquals(firstSubLinkOut[0], vertices[0].getFirstIncidence(
					eclasses[1], EdgeDirection.OUT));
			assertEquals(firstSubLinkOut[1], vertices[1].getFirstIncidence(
					eclasses[1], EdgeDirection.OUT));
			assertEquals(firstSubLinkOut[2], vertices[2].getFirstIncidence(
					eclasses[1], EdgeDirection.OUT));

			assertEquals(firstLinkIn[0], vertices[0].getFirstIncidence(
					eclasses[0], EdgeDirection.IN));
			assertEquals(firstLinkIn[1], vertices[1].getFirstIncidence(
					eclasses[0], EdgeDirection.IN));
			assertEquals(firstLinkIn[2], vertices[2].getFirstIncidence(
					eclasses[0], EdgeDirection.IN));
			assertEquals(firstLinkBackIn[0], vertices[0].getFirstIncidence(
					eclasses[2], EdgeDirection.IN));
			assertEquals(firstLinkBackIn[1], vertices[1].getFirstIncidence(
					eclasses[2], EdgeDirection.IN));
			assertEquals(firstLinkBackIn[2], vertices[2].getFirstIncidence(
					eclasses[2], EdgeDirection.IN));
			assertEquals(firstSubLinkIn[0], vertices[0].getFirstIncidence(
					eclasses[1], EdgeDirection.IN));
			assertEquals(firstSubLinkIn[1], vertices[1].getFirstIncidence(
					eclasses[1], EdgeDirection.IN));
			assertEquals(firstSubLinkIn[2], vertices[2].getFirstIncidence(
					eclasses[1], EdgeDirection.IN));
			commit(g);
		}
	}

	// tests of the method Edge getFirstEdgeOfClass(Class<? extends Edge>
	// anEdgeClass, EdgeDirection orientation);

	/**
	 * Tests if a node has no Edges
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClassEdgeDirection0()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		commit(g);

		createReadOnlyTransaction(g);
		assertNull(v0.getFirstIncidence(Link.class, EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.INOUT));

		assertNull(v0.getFirstIncidence(Link.class, EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.OUT));

		assertNull(v0.getFirstIncidence(Link.class, EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.IN));
		commit(g);
	}

	/**
	 * Tests if a node has only one Edge
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClassEdgeDirection1()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		commit(g);

		createReadOnlyTransaction(g);
		assertEquals(e, v0.getFirstIncidence(Link.class, EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.INOUT));
		assertEquals(e.getReversedEdge(),
				v1.getFirstIncidence(Link.class, EdgeDirection.INOUT));
		assertNull(v1.getFirstIncidence(SubLink.class, EdgeDirection.INOUT));
		assertNull(v1.getFirstIncidence(LinkBack.class, EdgeDirection.INOUT));

		assertEquals(e, v0.getFirstIncidence(Link.class, EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(Link.class, EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(SubLink.class, EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(LinkBack.class, EdgeDirection.OUT));

		assertNull(v0.getFirstIncidence(Link.class, EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.IN));
		assertEquals(e.getReversedEdge(),
				v1.getFirstIncidence(Link.class, EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(SubLink.class, EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(LinkBack.class, EdgeDirection.IN));
		commit(g);
	}

	/**
	 * Tests if a node has an edge which extends another edge
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClassEdgeDirection2()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createSubLink((DoubleSubNode) v0, (SuperNode) v1);
		commit(g);

		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(Link.class, EdgeDirection.INOUT));
		assertEquals(e1,
				v0.getFirstIncidence(SubLink.class, EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.INOUT));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(Link.class, EdgeDirection.INOUT));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(SubLink.class, EdgeDirection.INOUT));
		assertNull(v1.getFirstIncidence(LinkBack.class, EdgeDirection.INOUT));

		assertEquals(e1, v0.getFirstIncidence(Link.class, EdgeDirection.OUT));
		assertEquals(e1, v0.getFirstIncidence(SubLink.class, EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(Link.class, EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(SubLink.class, EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(LinkBack.class, EdgeDirection.OUT));

		assertNull(v0.getFirstIncidence(Link.class, EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.IN));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(Link.class, EdgeDirection.IN));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(SubLink.class, EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(LinkBack.class, EdgeDirection.IN));
		commit(g);
	}

	/**
	 * Tests if a node has two Edges.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClassEdgeDirection3()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge e2 = g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		commit(g);

		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(Link.class, EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.INOUT));
		assertEquals(e2.getReversedEdge(),
				v0.getFirstIncidence(LinkBack.class, EdgeDirection.INOUT));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(Link.class, EdgeDirection.INOUT));
		assertNull(v1.getFirstIncidence(SubLink.class, EdgeDirection.INOUT));
		assertEquals(e2,
				v1.getFirstIncidence(LinkBack.class, EdgeDirection.INOUT));

		assertEquals(e1, v0.getFirstIncidence(Link.class, EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(Link.class, EdgeDirection.OUT));
		assertNull(v1.getFirstIncidence(SubLink.class, EdgeDirection.OUT));
		assertEquals(e2,
				v1.getFirstIncidence(LinkBack.class, EdgeDirection.OUT));

		assertNull(v0.getFirstIncidence(Link.class, EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.IN));
		assertEquals(e2.getReversedEdge(),
				v0.getFirstIncidence(LinkBack.class, EdgeDirection.IN));
		assertEquals(e1.getReversedEdge(),
				v1.getFirstIncidence(Link.class, EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(SubLink.class, EdgeDirection.IN));
		assertNull(v1.getFirstIncidence(LinkBack.class, EdgeDirection.IN));
		commit(g);
	}

	/**
	 * Tests if alpha and omega of an Edge is the same Vertex.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClassEdgeDirection4()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v0);
		commit(g);

		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstIncidence(Link.class, EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.INOUT));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.INOUT));

		assertEquals(e1, v0.getFirstIncidence(Link.class, EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.OUT));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.OUT));

		assertEquals(e1.getReversedEdge(),
				v0.getFirstIncidence(Link.class, EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(SubLink.class, EdgeDirection.IN));
		assertNull(v0.getFirstIncidence(LinkBack.class, EdgeDirection.IN));
		commit(g);
	}

	/**
	 * Random tests
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getFirstEdgeTestClassEdgeDirection5()
			throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			Vertex[] vertices = new Vertex[] { g.createSubNode(),
					g.createDoubleSubNode(), g.createSuperNode() };
			Edge[] firstLinkInOut = new Edge[3];
			Edge[] firstLinkBackInOut = new Edge[3];
			Edge[] firstSubLinkInOut = new Edge[3];
			Edge[] firstLinkOut = new Edge[3];
			Edge[] firstLinkBackOut = new Edge[3];
			Edge[] firstSubLinkOut = new Edge[3];
			Edge[] firstLinkIn = new Edge[3];
			Edge[] firstLinkBackIn = new Edge[3];
			Edge[] firstSubLinkIn = new Edge[3];
			for (int j = 0; j < 5; j++) {
				int edgetype = rand.nextInt(3);
				int start = rand.nextInt(2);
				int end = rand.nextInt(2) + 1;
				switch (edgetype) {
				case 0:
					Edge e0 = g.createLink((AbstractSuperNode) vertices[start],
							(SuperNode) vertices[end]);
					if (firstLinkInOut[start] == null) {
						firstLinkInOut[start] = e0;
					}
					if (firstLinkOut[start] == null) {
						firstLinkOut[start] = e0;
					}
					if (firstLinkInOut[end] == null) {
						firstLinkInOut[end] = e0.getReversedEdge();
					}
					if (firstLinkIn[end] == null) {
						firstLinkIn[end] = e0.getReversedEdge();
					}
					break;
				case 1:
					Edge e1 = g.createLinkBack((SuperNode) vertices[end],
							(AbstractSuperNode) vertices[start]);
					if (firstLinkBackInOut[end] == null) {
						firstLinkBackInOut[end] = e1;
					}
					if (firstLinkBackOut[end] == null) {
						firstLinkBackOut[end] = e1;
					}
					if (firstLinkBackInOut[start] == null) {
						firstLinkBackInOut[start] = e1.getReversedEdge();
					}
					if (firstLinkBackIn[start] == null) {
						firstLinkBackIn[start] = e1.getReversedEdge();
					}
					break;
				case 2:
					Edge e2 = g.createSubLink((DoubleSubNode) vertices[1],
							(SuperNode) vertices[end]);
					if (firstLinkInOut[1] == null) {
						firstLinkInOut[1] = e2;
					}
					if (firstLinkOut[1] == null) {
						firstLinkOut[1] = e2;
					}
					if (firstSubLinkInOut[1] == null) {
						firstSubLinkInOut[1] = e2;
					}
					if (firstSubLinkOut[1] == null) {
						firstSubLinkOut[1] = e2;
					}
					if (firstLinkInOut[end] == null) {
						firstLinkInOut[end] = e2.getReversedEdge();
					}
					if (firstLinkIn[end] == null) {
						firstLinkIn[end] = e2.getReversedEdge();
					}
					if (firstSubLinkInOut[end] == null) {
						firstSubLinkInOut[end] = e2.getReversedEdge();
					}
					if (firstSubLinkIn[end] == null) {
						firstSubLinkIn[end] = e2.getReversedEdge();
					}
					break;
				}
			}
			commit(g);

			createReadOnlyTransaction(g);
			assertEquals(firstLinkInOut[0], vertices[0].getFirstIncidence(
					Link.class, EdgeDirection.INOUT));
			assertEquals(firstLinkInOut[1], vertices[1].getFirstIncidence(
					Link.class, EdgeDirection.INOUT));
			assertEquals(firstLinkInOut[2], vertices[2].getFirstIncidence(
					Link.class, EdgeDirection.INOUT));
			assertEquals(firstLinkBackInOut[0], vertices[0].getFirstIncidence(
					LinkBack.class, EdgeDirection.INOUT));
			assertEquals(firstLinkBackInOut[1], vertices[1].getFirstIncidence(
					LinkBack.class, EdgeDirection.INOUT));
			assertEquals(firstLinkBackInOut[2], vertices[2].getFirstIncidence(
					LinkBack.class, EdgeDirection.INOUT));
			assertEquals(firstSubLinkInOut[0], vertices[0].getFirstIncidence(
					SubLink.class, EdgeDirection.INOUT));
			assertEquals(firstSubLinkInOut[1], vertices[1].getFirstIncidence(
					SubLink.class, EdgeDirection.INOUT));
			assertEquals(firstSubLinkInOut[2], vertices[2].getFirstIncidence(
					SubLink.class, EdgeDirection.INOUT));

			assertEquals(firstLinkOut[0], vertices[0].getFirstIncidence(
					Link.class, EdgeDirection.OUT));
			assertEquals(firstLinkOut[1], vertices[1].getFirstIncidence(
					Link.class, EdgeDirection.OUT));
			assertEquals(firstLinkOut[2], vertices[2].getFirstIncidence(
					Link.class, EdgeDirection.OUT));
			assertEquals(firstLinkBackOut[0], vertices[0].getFirstIncidence(
					LinkBack.class, EdgeDirection.OUT));
			assertEquals(firstLinkBackOut[1], vertices[1].getFirstIncidence(
					LinkBack.class, EdgeDirection.OUT));
			assertEquals(firstLinkBackOut[2], vertices[2].getFirstIncidence(
					LinkBack.class, EdgeDirection.OUT));
			assertEquals(firstSubLinkOut[0], vertices[0].getFirstIncidence(
					SubLink.class, EdgeDirection.OUT));
			assertEquals(firstSubLinkOut[1], vertices[1].getFirstIncidence(
					SubLink.class, EdgeDirection.OUT));
			assertEquals(firstSubLinkOut[2], vertices[2].getFirstIncidence(
					SubLink.class, EdgeDirection.OUT));

			assertEquals(firstLinkIn[0],
					vertices[0].getFirstIncidence(Link.class, EdgeDirection.IN));
			assertEquals(firstLinkIn[1],
					vertices[1].getFirstIncidence(Link.class, EdgeDirection.IN));
			assertEquals(firstLinkIn[2],
					vertices[2].getFirstIncidence(Link.class, EdgeDirection.IN));
			assertEquals(firstLinkBackIn[0], vertices[0].getFirstIncidence(
					LinkBack.class, EdgeDirection.IN));
			assertEquals(firstLinkBackIn[1], vertices[1].getFirstIncidence(
					LinkBack.class, EdgeDirection.IN));
			assertEquals(firstLinkBackIn[2], vertices[2].getFirstIncidence(
					LinkBack.class, EdgeDirection.IN));
			assertEquals(firstSubLinkIn[0], vertices[0].getFirstIncidence(
					SubLink.class, EdgeDirection.IN));
			assertEquals(firstSubLinkIn[1], vertices[1].getFirstIncidence(
					SubLink.class, EdgeDirection.IN));
			assertEquals(firstSubLinkIn[2], vertices[2].getFirstIncidence(
					SubLink.class, EdgeDirection.IN));
			commit(g);
		}
	}

	// tests of the method boolean isBefore(Vertex v);
	// (tested in VertexList Test)

	/**
	 * A vertex is not before itself.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void isBeforeTest0() throws CommitFailedException {
		createTransaction(g);
		Vertex v1 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertFalse(v1.isBefore(v1));
		commit(g);
	}

	// tests of the method void putBefore(Vertex v);
	// (tested in VertexList Test)

	// tests of the method boolean isAfter(Vertex v);
	// (tested in VertexList Test)

	/**
	 * A vertex is not after itself.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void isAfterTest0() throws CommitFailedException {
		createTransaction(g);
		Vertex v1 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertFalse(v1.isAfter(v1));
		commit(g);
	}

	// tests of the method void putAfter(Vertex v);
	// (tested in VertexList Test)

	// tests of the method void delete(Vertex v);
	// (tested in VertexList Test)

	/**
	 * Deleting v3 in v1<>---e1----v2<>-----e2-----v3
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void deleteTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		DoubleSubNode v2 = g.createDoubleSubNode();
		DoubleSubNode v3 = g.createDoubleSubNode();
		SubLink e1 = g.createSubLink(v1, v2);
		g.createSubLink(v2, v3);

		v3.delete();
		commit(g);
		createReadOnlyTransaction(g);
		assertFalse(v3.isValid());

		checkEdgeList(e1);
		assertEquals(2, g.getVCount());
		boolean first = true;
		for (Vertex v : g.vertices()) {
			if (first) {
				assertEquals(v1, v);
				first = false;
			} else {
				assertEquals(v2, v);
			}
		}
		commit(g);
	}

	/**
	 * Deleting v2 in v1<>---e1----v2<>-----e2-----v3
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void deleteTest1() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		DoubleSubNode v2 = g.createDoubleSubNode();
		DoubleSubNode v3 = g.createDoubleSubNode();
		g.createSubLink(v1, v2);
		g.createSubLink(v2, v3);
		v2.delete();
		commit(g);
		createReadOnlyTransaction(g);
		assertFalse(v2.isValid());
		assertEquals(0, g.getECount());
		assertEquals(1, g.getVCount());
		boolean first = true;
		for (Vertex v : g.vertices()) {
			if (first) {
				assertEquals(v1, v);
				first = false;
			} else {
				fail("No further vertices expected!");
			}
		}
		commit(g);
	}

	/**
	 * Deleting v1 in v1<>---e1----v2<>-----e2-----v3
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void deleteTest2() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		DoubleSubNode v2 = g.createDoubleSubNode();
		DoubleSubNode v3 = g.createDoubleSubNode();
		g.createSubLink(v1, v2);
		g.createSubLink(v2, v3);
		v1.delete();
		commit(g);
		createReadOnlyTransaction(g);
		assertFalse(v1.isValid());
		assertEquals(0, g.getECount());
		assertEquals(0, g.getVCount());
		commit(g);
	}

	/**
	 * Deleting v1 in v1<>---e1----v2 v1<>-----e2-----v3
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void deleteTest3() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		DoubleSubNode v2 = g.createDoubleSubNode();
		DoubleSubNode v3 = g.createDoubleSubNode();
		g.createSubLink(v1, v2);
		g.createSubLink(v1, v3);
		v1.delete();
		commit(g);
		createReadOnlyTransaction(g);
		assertFalse(v1.isValid());
		assertEquals(0, g.getECount());
		assertEquals(0, g.getVCount());
		commit(g);
	}

	/**
	 * Deleting v1 in v1<>---e1----v2 v1<>-----e2-----v2
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void deleteTest4() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		DoubleSubNode v2 = g.createDoubleSubNode();
		g.createSubLink(v1, v2);
		g.createSubLink(v1, v2);
		v1.delete();
		commit(g);
		createReadOnlyTransaction(g);
		assertFalse(v1.isValid());
		assertEquals(0, g.getECount());
		assertEquals(0, g.getVCount());
		commit(g);
	}

	/**
	 * Deleting v1 in v1<>---e1----v2-----e2-----v3
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void deleteTest5() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		DoubleSubNode v2 = g.createDoubleSubNode();
		DoubleSubNode v3 = g.createDoubleSubNode();
		g.createSubLink(v1, v2);
		g.createLink(v2, v3);
		v1.delete();
		commit(g);

		createReadOnlyTransaction(g);
		assertFalse(v1.isValid());
		assertEquals(0, g.getECount());
		assertEquals(1, g.getVCount());
		commit(g);
	}

	// tests of the method Iterable<Edge> incidences();
	// (tested in VertexList Test except failfast)

	/**
	 * An exception should occur if you want to remove an edge via the iterator.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void incidencesTest0() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Iterator<Edge> iter = v0.incidences().iterator();
		iter.remove();
		commit(g);
	}

	/**
	 * If you call hasNext several time, the current edge of the iterator must
	 * stay the same.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTest1() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Iterator<Edge> iter = v0.incidences().iterator();
		assertTrue(iter.hasNext());
		assertTrue(iter.hasNext());
		assertEquals(e1, iter.next());
		commit(g);
	}

	/**
	 * If there exists no further edges, hasNext must return false.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTes2() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e1 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge e2 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge e3 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Iterator<Edge> iter = v0.incidences().iterator();

		assertTrue(iter.hasNext());
		assertEquals(e1, iter.next());
		assertTrue(iter.hasNext());
		assertEquals(e2, iter.next());
		assertTrue(iter.hasNext());
		assertEquals(e3, iter.next());
		assertFalse(iter.hasNext());
		commit(g);
	}

	/**
	 * An exception should occur if the current edge is deleted.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void incidencesTestFailFast0() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Iterator<Edge> iter = v0.incidences().iterator();
		iter.hasNext();
		Edge e = iter.next();
		e.delete();
		iter.hasNext();
		iter.next();
		commit(g);
	}

	/**
	 * An exception should occur if the position of the current edge is changed.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void incidencesTestFailFast1() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge last = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Iterator<Edge> iter = v0.incidences().iterator();
		iter.hasNext();
		Edge e = iter.next();
		e.putIncidenceAfter(last);
		iter.hasNext();
		iter.next();
		commit(g);
	}

	/**
	 * An exception should occur if a previous edge is deleted.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void incidencesTestFailFast2() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Iterator<Edge> iter = v0.incidences().iterator();
		iter.hasNext();
		Edge e = iter.next();
		iter.hasNext();
		iter.next();
		e.delete();
		iter.hasNext();
		iter.next();
		commit(g);
	}

	/**
	 * An exception should occur if a following edge is deleted.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void incidencesTestFailFast3() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge last = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Iterator<Edge> iter = v0.incidences().iterator();
		last.delete();
		iter.hasNext();
		iter.next();
		commit(g);
	}

	/**
	 * An exception should occur if an edge is added.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void incidencesTestFailFast4() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Iterator<Edge> iter = v0.incidences().iterator();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		iter.hasNext();
		iter.next();
		commit(g);
	}

	/**
	 * An exception should occur if an edge gets another alpha vertex.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void incidencesTestFailFast5() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Iterator<Edge> iter = v0.incidences().iterator();
		e.setAlpha(v1);
		iter.hasNext();
		iter.next();
		commit(g);
	}

	// tests of the method Iterable<Edge> incidences(EdgeDirection dir);

	/**
	 * Checks if the expected incidences equals the returned incidences.
	 * 
	 * @param v
	 *            the node of which the incidences should be tested
	 * @param ec
	 *            or <code>c</code> must be <code>null</code>
	 * @param c
	 *            or <code>ec</code> must be <code>null</code>
	 * @param dir
	 *            must be != <code>null</code> if ec==null and c==null
	 * @param expectedIncidences
	 *            the expected incidences
	 */
	private void checkIncidenceList(Vertex v, EdgeClass ec,
			Class<? extends Edge> c, EdgeDirection dir,
			List<Edge> expectedIncidences) {
		int i = 0;
		if (dir == null) {
			if (ec == null) {
				for (Edge e : v.incidences(c)) {
					assertEquals(expectedIncidences.get(i), e);
					i++;
				}
			} else {
				for (Edge e : v.incidences(ec)) {
					assertEquals(expectedIncidences.get(i), e);
					i++;
				}
			}
		} else {
			if (ec != null) {
				for (Edge e : v.incidences(ec, dir)) {
					assertEquals(expectedIncidences.get(i), e);
					i++;
				}
			} else if (c != null) {
				for (Edge e : v.incidences(c, dir)) {
					assertEquals(expectedIncidences.get(i), e);
					i++;
				}
			} else {
				for (Edge e : v.incidences(dir)) {
					assertEquals(expectedIncidences.get(i), e);
					i++;
				}
			}
		}
	}

	/**
	 * Checks if a vertex has no incidences.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeDirection0() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		checkIncidenceList(v0, null, null, EdgeDirection.INOUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, null, null, EdgeDirection.OUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, null, null, EdgeDirection.IN,
				new LinkedList<Edge>());
		commit(g);
	}

	/**
	 * Checks if a vertex has only outgoing or ingoing incidences.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeDirection1() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		LinkedList<Edge> v0inout = new LinkedList<Edge>();
		LinkedList<Edge> v0out = new LinkedList<Edge>();
		LinkedList<Edge> v0in = new LinkedList<Edge>();
		LinkedList<Edge> v1inout = new LinkedList<Edge>();
		LinkedList<Edge> v1out = new LinkedList<Edge>();
		LinkedList<Edge> v1in = new LinkedList<Edge>();
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		v0inout.add(e);
		v0out.add(e);
		v1inout.add(e.getReversedEdge());
		v1in.add(e.getReversedEdge());
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, null, null, EdgeDirection.INOUT, v0inout);
		checkIncidenceList(v0, null, null, EdgeDirection.OUT, v0out);
		checkIncidenceList(v0, null, null, EdgeDirection.IN, v0in);

		checkIncidenceList(v1, null, null, EdgeDirection.INOUT, v1inout);
		checkIncidenceList(v1, null, null, EdgeDirection.OUT, v1out);
		checkIncidenceList(v1, null, null, EdgeDirection.IN, v1in);
		commit(g);
	}

	/**
	 * Checks incidences in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeDirection2() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Vertex v2 = g.createSuperNode();
		LinkedList<Edge> v0inout = new LinkedList<Edge>();
		LinkedList<Edge> v0out = new LinkedList<Edge>();
		LinkedList<Edge> v0in = new LinkedList<Edge>();
		LinkedList<Edge> v1inout = new LinkedList<Edge>();
		LinkedList<Edge> v1out = new LinkedList<Edge>();
		LinkedList<Edge> v1in = new LinkedList<Edge>();
		LinkedList<Edge> v2inout = new LinkedList<Edge>();
		LinkedList<Edge> v2out = new LinkedList<Edge>();
		LinkedList<Edge> v2in = new LinkedList<Edge>();
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		v0inout.add(e);
		v0out.add(e);
		v1inout.add(e.getReversedEdge());
		v1in.add(e.getReversedEdge());
		e = g.createSubLink((DoubleSubNode) v1, (SuperNode) v2);
		v1inout.add(e);
		v1out.add(e);
		v2inout.add(e.getReversedEdge());
		v2in.add(e.getReversedEdge());
		e = g.createLinkBack((SuperNode) v2, (DoubleSubNode) v1);
		v2inout.add(e);
		v2out.add(e);
		v1inout.add(e.getReversedEdge());
		v1in.add(e.getReversedEdge());
		e = g.createLinkBack((SuperNode) v1, (DoubleSubNode) v1);
		v1inout.add(e);
		v1out.add(e);
		v1inout.add(e.getReversedEdge());
		v1in.add(e.getReversedEdge());
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, null, null, EdgeDirection.INOUT, v0inout);
		checkIncidenceList(v0, null, null, EdgeDirection.OUT, v0out);
		checkIncidenceList(v0, null, null, EdgeDirection.IN, v0in);

		checkIncidenceList(v1, null, null, EdgeDirection.INOUT, v1inout);
		checkIncidenceList(v1, null, null, EdgeDirection.OUT, v1out);
		checkIncidenceList(v1, null, null, EdgeDirection.IN, v1in);

		checkIncidenceList(v2, null, null, EdgeDirection.INOUT, v2inout);
		checkIncidenceList(v2, null, null, EdgeDirection.OUT, v2out);
		checkIncidenceList(v2, null, null, EdgeDirection.IN, v2in);
		commit(g);
	}

	/**
	 * Random test.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeDirection3() throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			Vertex[] vertices = new Vertex[] { g.createSubNode(),
					g.createDoubleSubNode(), g.createSuperNode() };
			LinkedList<LinkedList<Edge>> inout = new LinkedList<LinkedList<Edge>>();
			inout.add(new LinkedList<Edge>());
			inout.add(new LinkedList<Edge>());
			inout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> out = new LinkedList<LinkedList<Edge>>();
			out.add(new LinkedList<Edge>());
			out.add(new LinkedList<Edge>());
			out.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> in = new LinkedList<LinkedList<Edge>>();
			in.add(new LinkedList<Edge>());
			in.add(new LinkedList<Edge>());
			in.add(new LinkedList<Edge>());
			for (int j = 0; j < 30; j++) {
				int edge = rand.nextInt(3);
				int start = rand.nextInt(2);
				int end = rand.nextInt(2) + 1;
				Edge e = null;
				switch (edge) {
				case 0:
					e = g.createLink((AbstractSuperNode) vertices[start],
							(SuperNode) vertices[end]);
					inout.get(start).add(e);
					out.get(start).add(e);
					inout.get(end).add(e.getReversedEdge());
					in.get(end).add(e.getReversedEdge());
					break;
				case 1:
					e = g.createLinkBack((SuperNode) vertices[end],
							(AbstractSuperNode) vertices[start]);
					inout.get(end).add(e);
					out.get(end).add(e);
					inout.get(start).add(e.getReversedEdge());
					in.get(start).add(e.getReversedEdge());
					break;
				case 2:
					e = g.createSubLink((DoubleSubNode) vertices[1],
							(SuperNode) vertices[end]);
					inout.get(1).add(e);
					out.get(1).add(e);
					inout.get(end).add(e.getReversedEdge());
					in.get(end).add(e.getReversedEdge());
					break;
				}
			}
			commit(g);

			createReadOnlyTransaction(g);
			checkIncidenceList(vertices[0], null, null, EdgeDirection.INOUT,
					inout.get(0));
			checkIncidenceList(vertices[0], null, null, EdgeDirection.OUT,
					out.get(0));
			checkIncidenceList(vertices[0], null, null, EdgeDirection.IN,
					in.get(0));

			checkIncidenceList(vertices[1], null, null, EdgeDirection.INOUT,
					inout.get(1));
			checkIncidenceList(vertices[1], null, null, EdgeDirection.OUT,
					out.get(1));
			checkIncidenceList(vertices[1], null, null, EdgeDirection.IN,
					in.get(1));

			checkIncidenceList(vertices[2], null, null, EdgeDirection.INOUT,
					inout.get(2));
			checkIncidenceList(vertices[2], null, null, EdgeDirection.OUT,
					out.get(2));
			checkIncidenceList(vertices[2], null, null, EdgeDirection.IN,
					in.get(2));
			commit(g);
		}
	}

	/**
	 * If the IN-edges are iterated the OUT-edges could not be deleted.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void inciencesTestEdgeDirectionFailFast0()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e0 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		Iterator<Edge> it = v0.incidences(EdgeDirection.IN).iterator();
		e0.delete();
		it.hasNext();
		it.next();
		commit(g);
	}

	/**
	 * If the IN-edges are iterated the OUT-edges could not be changed.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void inciencesTestEdgeDirectionFailFast1()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Edge e0 = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		Iterator<Edge> it = v0.incidences(EdgeDirection.IN).iterator();
		e0.setAlpha(v1);
		it.hasNext();
		it.next();
		commit(g);
	}

	/**
	 * If the IN-edges are iterated a new OUT-edges could not be created.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void inciencesTestEdgeDirectionFailFast2()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		Iterator<Edge> it = v0.incidences(EdgeDirection.IN).iterator();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		it.hasNext();
		it.next();
		commit(g);
	}

	/**
	 * If the OUT-edges are iterated the IN-edges could not be deleted.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void inciencesTestEdgeDirectionFailFast3()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge e0 = g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		Iterator<Edge> it = v0.incidences(EdgeDirection.OUT).iterator();
		e0.delete();
		it.hasNext();
		it.next();
		commit(g);
	}

	/**
	 * If the OUT-edges are iterated the IN-edges could not be changed.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void inciencesTestEdgeDirectionFailFast4()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		Edge e0 = g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		Iterator<Edge> it = v0.incidences(EdgeDirection.OUT).iterator();
		e0.setAlpha(v0);
		it.hasNext();
		it.next();
		commit(g);
	}

	/**
	 * If the OUT-edges are iterated a new IN-edges could not be created.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = ConcurrentModificationException.class)
	public void inciencesTestEdgeDirectionFailFast5()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		g.createLinkBack((SuperNode) v1, (AbstractSuperNode) v0);
		Iterator<Edge> it = v0.incidences(EdgeDirection.OUT).iterator();
		g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		it.hasNext();
		it.next();
		commit(g);
	}

	// tests of the method Iterable<Edge> incidences(EdgeClass eclass);

	/**
	 * Checks if a vertex has no incidences.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeClass0() throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] ecs = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		checkIncidenceList(v0, ecs[0], null, null, new LinkedList<Edge>());
		checkIncidenceList(v0, ecs[1], null, null, new LinkedList<Edge>());
		checkIncidenceList(v0, ecs[2], null, null, new LinkedList<Edge>());
		commit(g);
	}

	/**
	 * Checks if a vertex has only incident edges of type SubLink.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeClass1() throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] ecs = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		LinkedList<Edge> v0link = new LinkedList<Edge>();
		LinkedList<Edge> v0sublink = new LinkedList<Edge>();
		LinkedList<Edge> v0linkback = new LinkedList<Edge>();
		LinkedList<Edge> v1link = new LinkedList<Edge>();
		LinkedList<Edge> v1sublink = new LinkedList<Edge>();
		LinkedList<Edge> v1linkback = new LinkedList<Edge>();
		Edge e = g.createSubLink((DoubleSubNode) v0, (SuperNode) v1);
		v0link.add(e);
		v0sublink.add(e);
		v1link.add(e.getReversedEdge());
		v1sublink.add(e.getReversedEdge());
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, ecs[0], null, null, v0link);
		checkIncidenceList(v0, ecs[1], null, null, v0sublink);
		checkIncidenceList(v0, ecs[2], null, null, v0linkback);

		checkIncidenceList(v1, ecs[0], null, null, v1link);
		checkIncidenceList(v1, ecs[1], null, null, v1sublink);
		checkIncidenceList(v1, ecs[2], null, null, v1linkback);
		commit(g);
	}

	/**
	 * Checks incidences in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeClass2() throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] ecs = getEdgeClasses();
		Vertex v0 = g.createSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Vertex v2 = g.createSuperNode();
		LinkedList<Edge> v0link = new LinkedList<Edge>();
		LinkedList<Edge> v0sublink = new LinkedList<Edge>();
		LinkedList<Edge> v0linkback = new LinkedList<Edge>();
		LinkedList<Edge> v1link = new LinkedList<Edge>();
		LinkedList<Edge> v1sublink = new LinkedList<Edge>();
		LinkedList<Edge> v1linkback = new LinkedList<Edge>();
		LinkedList<Edge> v2link = new LinkedList<Edge>();
		LinkedList<Edge> v2sublink = new LinkedList<Edge>();
		LinkedList<Edge> v2linkback = new LinkedList<Edge>();
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		v0link.add(e);
		v1link.add(e.getReversedEdge());
		e = g.createSubLink((DoubleSubNode) v1, (SuperNode) v2);
		v1link.add(e);
		v1sublink.add(e);
		v2link.add(e.getReversedEdge());
		v2sublink.add(e.getReversedEdge());
		e = g.createLinkBack((SuperNode) v2, (DoubleSubNode) v1);
		v2linkback.add(e);
		v1linkback.add(e.getReversedEdge());
		e = g.createLinkBack((SuperNode) v1, (DoubleSubNode) v1);
		v1linkback.add(e);
		v1linkback.add(e.getReversedEdge());
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, ecs[0], null, null, v0link);
		checkIncidenceList(v0, ecs[1], null, null, v0sublink);
		checkIncidenceList(v0, ecs[2], null, null, v0linkback);

		checkIncidenceList(v1, ecs[0], null, null, v1link);
		checkIncidenceList(v1, ecs[1], null, null, v1sublink);
		checkIncidenceList(v1, ecs[2], null, null, v1linkback);

		checkIncidenceList(v2, ecs[0], null, null, v2link);
		checkIncidenceList(v2, ecs[1], null, null, v2sublink);
		checkIncidenceList(v2, ecs[2], null, null, v2linkback);
		commit(g);
	}

	/**
	 * Random test.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeClass3() throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			EdgeClass[] ecs = getEdgeClasses();
			Vertex[] vertices = new Vertex[] { g.createSubNode(),
					g.createDoubleSubNode(), g.createSuperNode() };
			LinkedList<LinkedList<Edge>> link = new LinkedList<LinkedList<Edge>>();
			link.add(new LinkedList<Edge>());
			link.add(new LinkedList<Edge>());
			link.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> sublink = new LinkedList<LinkedList<Edge>>();
			sublink.add(new LinkedList<Edge>());
			sublink.add(new LinkedList<Edge>());
			sublink.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> linkback = new LinkedList<LinkedList<Edge>>();
			linkback.add(new LinkedList<Edge>());
			linkback.add(new LinkedList<Edge>());
			linkback.add(new LinkedList<Edge>());
			for (int j = 0; j < 30; j++) {
				int edge = rand.nextInt(3);
				int start = rand.nextInt(2);
				int end = rand.nextInt(2) + 1;
				Edge e = null;
				switch (edge) {
				case 0:
					e = g.createLink((AbstractSuperNode) vertices[start],
							(SuperNode) vertices[end]);
					link.get(start).add(e);
					link.get(end).add(e.getReversedEdge());
					break;
				case 1:
					e = g.createLinkBack((SuperNode) vertices[end],
							(AbstractSuperNode) vertices[start]);
					linkback.get(end).add(e);
					linkback.get(start).add(e.getReversedEdge());
					break;
				case 2:
					e = g.createSubLink((DoubleSubNode) vertices[1],
							(SuperNode) vertices[end]);
					link.get(1).add(e);
					sublink.get(1).add(e);
					link.get(end).add(e.getReversedEdge());
					sublink.get(end).add(e.getReversedEdge());
					break;
				}
			}
			commit(g);

			createReadOnlyTransaction(g);
			checkIncidenceList(vertices[0], ecs[0], null, null, link.get(0));
			checkIncidenceList(vertices[0], ecs[1], null, null, sublink.get(0));
			checkIncidenceList(vertices[0], ecs[2], null, null, linkback.get(0));

			checkIncidenceList(vertices[1], ecs[0], null, null, link.get(1));
			checkIncidenceList(vertices[1], ecs[1], null, null, sublink.get(1));
			checkIncidenceList(vertices[1], ecs[2], null, null, linkback.get(1));

			checkIncidenceList(vertices[2], ecs[0], null, null, link.get(2));
			checkIncidenceList(vertices[2], ecs[1], null, null, sublink.get(2));
			checkIncidenceList(vertices[2], ecs[2], null, null, linkback.get(2));
			commit(g);
		}
	}

	// tests of the method Iterable<Edge> incidences(Class<? extends Edge>
	// eclass);

	/**
	 * Checks if a vertex has no incidences.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestClass0() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		checkIncidenceList(v0, null, Link.class, null, new LinkedList<Edge>());
		checkIncidenceList(v0, null, SubLink.class, null,
				new LinkedList<Edge>());
		checkIncidenceList(v0, null, LinkBack.class, null,
				new LinkedList<Edge>());
		commit(g);
	}

	/**
	 * Checks if a vertex has only incident edges of type SubLink.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestClass1() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		LinkedList<Edge> v0link = new LinkedList<Edge>();
		LinkedList<Edge> v0sublink = new LinkedList<Edge>();
		LinkedList<Edge> v0linkback = new LinkedList<Edge>();
		LinkedList<Edge> v1link = new LinkedList<Edge>();
		LinkedList<Edge> v1sublink = new LinkedList<Edge>();
		LinkedList<Edge> v1linkback = new LinkedList<Edge>();
		Edge e = g.createSubLink((DoubleSubNode) v0, (SuperNode) v1);
		v0link.add(e);
		v0sublink.add(e);
		v1link.add(e.getReversedEdge());
		v1sublink.add(e.getReversedEdge());
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, null, Link.class, null, v0link);
		checkIncidenceList(v0, null, SubLink.class, null, v0sublink);
		checkIncidenceList(v0, null, LinkBack.class, null, v0linkback);

		checkIncidenceList(v1, null, Link.class, null, v1link);
		checkIncidenceList(v1, null, SubLink.class, null, v1sublink);
		checkIncidenceList(v1, null, LinkBack.class, null, v1linkback);
		commit(g);
	}

	/**
	 * Checks incidences in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestClass2() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Vertex v2 = g.createSuperNode();
		LinkedList<Edge> v0link = new LinkedList<Edge>();
		LinkedList<Edge> v0sublink = new LinkedList<Edge>();
		LinkedList<Edge> v0linkback = new LinkedList<Edge>();
		LinkedList<Edge> v1link = new LinkedList<Edge>();
		LinkedList<Edge> v1sublink = new LinkedList<Edge>();
		LinkedList<Edge> v1linkback = new LinkedList<Edge>();
		LinkedList<Edge> v2link = new LinkedList<Edge>();
		LinkedList<Edge> v2sublink = new LinkedList<Edge>();
		LinkedList<Edge> v2linkback = new LinkedList<Edge>();
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		v0link.add(e);
		v1link.add(e.getReversedEdge());
		e = g.createSubLink((DoubleSubNode) v1, (SuperNode) v2);
		v1link.add(e);
		v1sublink.add(e);
		v2link.add(e.getReversedEdge());
		v2sublink.add(e.getReversedEdge());
		e = g.createLinkBack((SuperNode) v2, (DoubleSubNode) v1);
		v2linkback.add(e);
		v1linkback.add(e.getReversedEdge());
		e = g.createLinkBack((SuperNode) v1, (DoubleSubNode) v1);
		v1linkback.add(e);
		v1linkback.add(e.getReversedEdge());
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, null, Link.class, null, v0link);
		checkIncidenceList(v0, null, SubLink.class, null, v0sublink);
		checkIncidenceList(v0, null, LinkBack.class, null, v0linkback);

		checkIncidenceList(v1, null, Link.class, null, v1link);
		checkIncidenceList(v1, null, SubLink.class, null, v1sublink);
		checkIncidenceList(v1, null, LinkBack.class, null, v1linkback);

		checkIncidenceList(v2, null, Link.class, null, v2link);
		checkIncidenceList(v2, null, SubLink.class, null, v2sublink);
		checkIncidenceList(v2, null, LinkBack.class, null, v2linkback);
		commit(g);
	}

	/**
	 * Random test.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestClass3() throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			Vertex[] vertices = new Vertex[] { g.createSubNode(),
					g.createDoubleSubNode(), g.createSuperNode() };
			LinkedList<LinkedList<Edge>> link = new LinkedList<LinkedList<Edge>>();
			link.add(new LinkedList<Edge>());
			link.add(new LinkedList<Edge>());
			link.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> sublink = new LinkedList<LinkedList<Edge>>();
			sublink.add(new LinkedList<Edge>());
			sublink.add(new LinkedList<Edge>());
			sublink.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> linkback = new LinkedList<LinkedList<Edge>>();
			linkback.add(new LinkedList<Edge>());
			linkback.add(new LinkedList<Edge>());
			linkback.add(new LinkedList<Edge>());
			for (int j = 0; j < 30; j++) {
				int edge = rand.nextInt(3);
				int start = rand.nextInt(2);
				int end = rand.nextInt(2) + 1;
				Edge e = null;
				switch (edge) {
				case 0:
					e = g.createLink((AbstractSuperNode) vertices[start],
							(SuperNode) vertices[end]);
					link.get(start).add(e);
					link.get(end).add(e.getReversedEdge());
					break;
				case 1:
					e = g.createLinkBack((SuperNode) vertices[end],
							(AbstractSuperNode) vertices[start]);
					linkback.get(end).add(e);
					linkback.get(start).add(e.getReversedEdge());
					break;
				case 2:
					e = g.createSubLink((DoubleSubNode) vertices[1],
							(SuperNode) vertices[end]);
					link.get(1).add(e);
					sublink.get(1).add(e);
					link.get(end).add(e.getReversedEdge());
					sublink.get(end).add(e.getReversedEdge());
					break;
				}
			}
			commit(g);

			createReadOnlyTransaction(g);
			checkIncidenceList(vertices[0], null, Link.class, null, link.get(0));
			checkIncidenceList(vertices[0], null, SubLink.class, null,
					sublink.get(0));
			checkIncidenceList(vertices[0], null, LinkBack.class, null,
					linkback.get(0));

			checkIncidenceList(vertices[1], null, Link.class, null, link.get(1));
			checkIncidenceList(vertices[1], null, SubLink.class, null,
					sublink.get(1));
			checkIncidenceList(vertices[1], null, LinkBack.class, null,
					linkback.get(1));

			checkIncidenceList(vertices[2], null, Link.class, null, link.get(2));
			checkIncidenceList(vertices[2], null, SubLink.class, null,
					sublink.get(2));
			checkIncidenceList(vertices[2], null, LinkBack.class, null,
					linkback.get(2));
			commit(g);
		}
	}

	// tests of the method Iterable<Edge> incidences(EdgeClass eclass,
	// EdgeDirection dir);

	/**
	 * Checks if a vertex has no incidences.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeClassEdgeDirection0()
			throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] ecs = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, ecs[0], null, EdgeDirection.INOUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, ecs[0], null, EdgeDirection.OUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, ecs[0], null, EdgeDirection.IN,
				new LinkedList<Edge>());

		checkIncidenceList(v0, ecs[1], null, EdgeDirection.INOUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, ecs[1], null, EdgeDirection.OUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, ecs[1], null, EdgeDirection.IN,
				new LinkedList<Edge>());

		checkIncidenceList(v0, ecs[2], null, EdgeDirection.INOUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, ecs[2], null, EdgeDirection.OUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, ecs[2], null, EdgeDirection.IN,
				new LinkedList<Edge>());
		commit(g);
	}

	/**
	 * Checks if a vertex has only incident edges of type SubLink.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeClassEdgeDirection1()
			throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] ecs = getEdgeClasses();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		LinkedList<Edge> v0linkInout = new LinkedList<Edge>();
		LinkedList<Edge> v0linkOut = new LinkedList<Edge>();
		LinkedList<Edge> v0linkIn = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkInout = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkOut = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkIn = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackInout = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackOut = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackIn = new LinkedList<Edge>();
		LinkedList<Edge> v1linkInout = new LinkedList<Edge>();
		LinkedList<Edge> v1linkOut = new LinkedList<Edge>();
		LinkedList<Edge> v1linkIn = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkInout = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkOut = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkIn = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackInout = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackOut = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackIn = new LinkedList<Edge>();
		Edge e = g.createSubLink((DoubleSubNode) v0, (SuperNode) v1);
		v0linkInout.add(e);
		v0linkOut.add(e);
		v0sublinkInout.add(e);
		v0sublinkOut.add(e);
		v1linkInout.add(e.getReversedEdge());
		v1linkIn.add(e.getReversedEdge());
		v1sublinkInout.add(e.getReversedEdge());
		v1sublinkIn.add(e.getReversedEdge());
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, ecs[0], null, EdgeDirection.INOUT, v0linkInout);
		checkIncidenceList(v0, ecs[0], null, EdgeDirection.OUT, v0linkOut);
		checkIncidenceList(v0, ecs[0], null, EdgeDirection.IN, v0linkIn);

		checkIncidenceList(v0, ecs[1], null, EdgeDirection.INOUT,
				v0sublinkInout);
		checkIncidenceList(v0, ecs[1], null, EdgeDirection.OUT, v0sublinkOut);
		checkIncidenceList(v0, ecs[1], null, EdgeDirection.IN, v0sublinkIn);

		checkIncidenceList(v0, ecs[2], null, EdgeDirection.INOUT,
				v0linkbackInout);
		checkIncidenceList(v0, ecs[2], null, EdgeDirection.OUT, v0linkbackOut);
		checkIncidenceList(v0, ecs[2], null, EdgeDirection.IN, v0linkbackIn);

		checkIncidenceList(v1, ecs[0], null, EdgeDirection.INOUT, v1linkInout);
		checkIncidenceList(v1, ecs[0], null, EdgeDirection.OUT, v1linkOut);
		checkIncidenceList(v1, ecs[0], null, EdgeDirection.IN, v1linkIn);

		checkIncidenceList(v1, ecs[1], null, EdgeDirection.INOUT,
				v1sublinkInout);
		checkIncidenceList(v1, ecs[1], null, EdgeDirection.OUT, v1sublinkOut);
		checkIncidenceList(v1, ecs[1], null, EdgeDirection.IN, v1sublinkIn);

		checkIncidenceList(v1, ecs[2], null, EdgeDirection.INOUT,
				v1linkbackInout);
		checkIncidenceList(v1, ecs[2], null, EdgeDirection.OUT, v1linkbackOut);
		checkIncidenceList(v1, ecs[2], null, EdgeDirection.IN, v1linkbackIn);
		commit(g);
	}

	/**
	 * Checks incidences in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeClassEdgeDirection2()
			throws CommitFailedException {
		createTransaction(g);
		EdgeClass[] ecs = getEdgeClasses();
		Vertex v0 = g.createSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Vertex v2 = g.createSuperNode();
		LinkedList<Edge> v0linkInout = new LinkedList<Edge>();
		LinkedList<Edge> v0linkOut = new LinkedList<Edge>();
		LinkedList<Edge> v0linkIn = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkInout = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkOut = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkIn = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackInout = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackOut = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackIn = new LinkedList<Edge>();
		LinkedList<Edge> v1linkInout = new LinkedList<Edge>();
		LinkedList<Edge> v1linkOut = new LinkedList<Edge>();
		LinkedList<Edge> v1linkIn = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkInout = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkOut = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkIn = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackInout = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackOut = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackIn = new LinkedList<Edge>();
		LinkedList<Edge> v2linkInout = new LinkedList<Edge>();
		LinkedList<Edge> v2linkOut = new LinkedList<Edge>();
		LinkedList<Edge> v2linkIn = new LinkedList<Edge>();
		LinkedList<Edge> v2sublinkInout = new LinkedList<Edge>();
		LinkedList<Edge> v2sublinkOut = new LinkedList<Edge>();
		LinkedList<Edge> v2sublinkIn = new LinkedList<Edge>();
		LinkedList<Edge> v2linkbackInout = new LinkedList<Edge>();
		LinkedList<Edge> v2linkbackOut = new LinkedList<Edge>();
		LinkedList<Edge> v2linkbackIn = new LinkedList<Edge>();
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		v0linkInout.add(e);
		v0linkOut.add(e);
		v1linkInout.add(e.getReversedEdge());
		v1linkIn.add(e.getReversedEdge());
		e = g.createSubLink((DoubleSubNode) v1, (SuperNode) v2);
		v1linkInout.add(e);
		v1linkOut.add(e);
		v1sublinkInout.add(e);
		v1sublinkOut.add(e);
		v2linkInout.add(e.getReversedEdge());
		v2linkIn.add(e.getReversedEdge());
		v2sublinkInout.add(e.getReversedEdge());
		v2sublinkIn.add(e.getReversedEdge());
		e = g.createLinkBack((SuperNode) v2, (DoubleSubNode) v1);
		v2linkbackInout.add(e);
		v2linkbackOut.add(e);
		v1linkbackInout.add(e.getReversedEdge());
		v1linkbackIn.add(e.getReversedEdge());
		e = g.createLinkBack((SuperNode) v1, (DoubleSubNode) v1);
		v1linkbackInout.add(e);
		v1linkbackOut.add(e);
		v1linkbackInout.add(e.getReversedEdge());
		v1linkbackIn.add(e.getReversedEdge());
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, ecs[0], null, EdgeDirection.INOUT, v0linkInout);
		checkIncidenceList(v0, ecs[0], null, EdgeDirection.OUT, v0linkOut);
		checkIncidenceList(v0, ecs[0], null, EdgeDirection.IN, v0linkIn);

		checkIncidenceList(v0, ecs[1], null, EdgeDirection.INOUT,
				v0sublinkInout);
		checkIncidenceList(v0, ecs[1], null, EdgeDirection.OUT, v0sublinkOut);
		checkIncidenceList(v0, ecs[1], null, EdgeDirection.IN, v0sublinkIn);

		checkIncidenceList(v0, ecs[2], null, EdgeDirection.INOUT,
				v0linkbackInout);
		checkIncidenceList(v0, ecs[2], null, EdgeDirection.OUT, v0linkbackOut);
		checkIncidenceList(v0, ecs[2], null, EdgeDirection.IN, v0linkbackIn);

		checkIncidenceList(v1, ecs[0], null, EdgeDirection.INOUT, v1linkInout);
		checkIncidenceList(v1, ecs[0], null, EdgeDirection.OUT, v1linkOut);
		checkIncidenceList(v1, ecs[0], null, EdgeDirection.IN, v1linkIn);

		checkIncidenceList(v1, ecs[1], null, EdgeDirection.INOUT,
				v1sublinkInout);
		checkIncidenceList(v1, ecs[1], null, EdgeDirection.OUT, v1sublinkOut);
		checkIncidenceList(v1, ecs[1], null, EdgeDirection.IN, v1sublinkIn);

		checkIncidenceList(v1, ecs[2], null, EdgeDirection.INOUT,
				v1linkbackInout);
		checkIncidenceList(v1, ecs[2], null, EdgeDirection.OUT, v1linkbackOut);
		checkIncidenceList(v1, ecs[2], null, EdgeDirection.IN, v1linkbackIn);

		checkIncidenceList(v2, ecs[0], null, EdgeDirection.INOUT, v2linkInout);
		checkIncidenceList(v2, ecs[0], null, EdgeDirection.OUT, v2linkOut);
		checkIncidenceList(v2, ecs[0], null, EdgeDirection.IN, v2linkIn);

		checkIncidenceList(v2, ecs[1], null, EdgeDirection.INOUT,
				v2sublinkInout);
		checkIncidenceList(v2, ecs[1], null, EdgeDirection.OUT, v2sublinkOut);
		checkIncidenceList(v2, ecs[1], null, EdgeDirection.IN, v2sublinkIn);

		checkIncidenceList(v2, ecs[2], null, EdgeDirection.INOUT,
				v2linkbackInout);
		checkIncidenceList(v2, ecs[2], null, EdgeDirection.OUT, v2linkbackOut);
		checkIncidenceList(v2, ecs[2], null, EdgeDirection.IN, v2linkbackIn);
		commit(g);
	}

	/**
	 * Random test.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestEdgeClassEdgeDirection3()
			throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			EdgeClass[] ecs = getEdgeClasses();
			Vertex[] vertices = new Vertex[] { g.createSubNode(),
					g.createDoubleSubNode(), g.createSuperNode() };
			LinkedList<LinkedList<Edge>> linkinout = new LinkedList<LinkedList<Edge>>();
			linkinout.add(new LinkedList<Edge>());
			linkinout.add(new LinkedList<Edge>());
			linkinout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> linkout = new LinkedList<LinkedList<Edge>>();
			linkout.add(new LinkedList<Edge>());
			linkout.add(new LinkedList<Edge>());
			linkout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> linkin = new LinkedList<LinkedList<Edge>>();
			linkin.add(new LinkedList<Edge>());
			linkin.add(new LinkedList<Edge>());
			linkin.add(new LinkedList<Edge>());

			LinkedList<LinkedList<Edge>> sublinkinout = new LinkedList<LinkedList<Edge>>();
			sublinkinout.add(new LinkedList<Edge>());
			sublinkinout.add(new LinkedList<Edge>());
			sublinkinout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> sublinkout = new LinkedList<LinkedList<Edge>>();
			sublinkout.add(new LinkedList<Edge>());
			sublinkout.add(new LinkedList<Edge>());
			sublinkout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> sublinkin = new LinkedList<LinkedList<Edge>>();
			sublinkin.add(new LinkedList<Edge>());
			sublinkin.add(new LinkedList<Edge>());
			sublinkin.add(new LinkedList<Edge>());

			LinkedList<LinkedList<Edge>> linkbackinout = new LinkedList<LinkedList<Edge>>();
			linkbackinout.add(new LinkedList<Edge>());
			linkbackinout.add(new LinkedList<Edge>());
			linkbackinout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> linkbackout = new LinkedList<LinkedList<Edge>>();
			linkbackout.add(new LinkedList<Edge>());
			linkbackout.add(new LinkedList<Edge>());
			linkbackout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> linkbackin = new LinkedList<LinkedList<Edge>>();
			linkbackin.add(new LinkedList<Edge>());
			linkbackin.add(new LinkedList<Edge>());
			linkbackin.add(new LinkedList<Edge>());

			for (int j = 0; j < 30; j++) {
				int edge = rand.nextInt(3);
				int start = rand.nextInt(2);
				int end = rand.nextInt(2) + 1;
				Edge e = null;
				switch (edge) {
				case 0:
					e = g.createLink((AbstractSuperNode) vertices[start],
							(SuperNode) vertices[end]);
					linkinout.get(start).add(e);
					linkout.get(start).add(e);
					linkinout.get(end).add(e.getReversedEdge());
					linkin.get(end).add(e.getReversedEdge());
					break;
				case 1:
					e = g.createLinkBack((SuperNode) vertices[end],
							(AbstractSuperNode) vertices[start]);
					linkbackinout.get(end).add(e);
					linkbackout.get(end).add(e);
					linkbackinout.get(start).add(e.getReversedEdge());
					linkbackin.get(start).add(e.getReversedEdge());
					break;
				case 2:
					e = g.createSubLink((DoubleSubNode) vertices[1],
							(SuperNode) vertices[end]);
					linkinout.get(1).add(e);
					linkout.get(1).add(e);
					sublinkinout.get(1).add(e);
					sublinkout.get(1).add(e);
					linkinout.get(end).add(e.getReversedEdge());
					linkin.get(end).add(e.getReversedEdge());
					sublinkinout.get(end).add(e.getReversedEdge());
					sublinkin.get(end).add(e.getReversedEdge());
					break;
				}
			}
			commit(g);

			createReadOnlyTransaction(g);
			checkIncidenceList(vertices[0], ecs[0], null, EdgeDirection.INOUT,
					linkinout.get(0));
			checkIncidenceList(vertices[0], ecs[0], null, EdgeDirection.OUT,
					linkout.get(0));
			checkIncidenceList(vertices[0], ecs[0], null, EdgeDirection.IN,
					linkin.get(0));

			checkIncidenceList(vertices[0], ecs[1], null, EdgeDirection.INOUT,
					sublinkinout.get(0));
			checkIncidenceList(vertices[0], ecs[1], null, EdgeDirection.OUT,
					sublinkout.get(0));
			checkIncidenceList(vertices[0], ecs[1], null, EdgeDirection.IN,
					sublinkin.get(0));

			checkIncidenceList(vertices[0], ecs[2], null, EdgeDirection.INOUT,
					linkbackinout.get(0));
			checkIncidenceList(vertices[0], ecs[2], null, EdgeDirection.OUT,
					linkbackout.get(0));
			checkIncidenceList(vertices[0], ecs[2], null, EdgeDirection.IN,
					linkbackin.get(0));

			checkIncidenceList(vertices[1], ecs[0], null, EdgeDirection.INOUT,
					linkinout.get(1));
			checkIncidenceList(vertices[1], ecs[0], null, EdgeDirection.OUT,
					linkout.get(1));
			checkIncidenceList(vertices[1], ecs[0], null, EdgeDirection.IN,
					linkin.get(1));

			checkIncidenceList(vertices[1], ecs[1], null, EdgeDirection.INOUT,
					sublinkinout.get(1));
			checkIncidenceList(vertices[1], ecs[1], null, EdgeDirection.OUT,
					sublinkout.get(1));
			checkIncidenceList(vertices[1], ecs[1], null, EdgeDirection.IN,
					sublinkin.get(1));

			checkIncidenceList(vertices[1], ecs[2], null, EdgeDirection.INOUT,
					linkbackinout.get(1));
			checkIncidenceList(vertices[1], ecs[2], null, EdgeDirection.OUT,
					linkbackout.get(1));
			checkIncidenceList(vertices[1], ecs[2], null, EdgeDirection.IN,
					linkbackin.get(1));

			checkIncidenceList(vertices[2], ecs[0], null, EdgeDirection.INOUT,
					linkinout.get(2));
			checkIncidenceList(vertices[2], ecs[0], null, EdgeDirection.OUT,
					linkout.get(2));
			checkIncidenceList(vertices[2], ecs[0], null, EdgeDirection.IN,
					linkin.get(2));

			checkIncidenceList(vertices[2], ecs[1], null, EdgeDirection.INOUT,
					sublinkinout.get(2));
			checkIncidenceList(vertices[2], ecs[1], null, EdgeDirection.OUT,
					sublinkout.get(2));
			checkIncidenceList(vertices[2], ecs[1], null, EdgeDirection.IN,
					sublinkin.get(2));

			checkIncidenceList(vertices[2], ecs[2], null, EdgeDirection.INOUT,
					linkbackinout.get(2));
			checkIncidenceList(vertices[2], ecs[2], null, EdgeDirection.OUT,
					linkbackout.get(2));
			checkIncidenceList(vertices[2], ecs[2], null, EdgeDirection.IN,
					linkbackin.get(2));
			commit(g);
		}
	}

	// tests of the method Iterable<Edge> incidences(Class<? extends Edge>
	// eclass, EdgeDirection dir);

	/**
	 * Checks if a vertex has no incidences.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestClassEdgeDirection0()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, null, Link.class, EdgeDirection.INOUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, null, Link.class, EdgeDirection.OUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, null, Link.class, EdgeDirection.IN,
				new LinkedList<Edge>());

		checkIncidenceList(v0, null, SubLink.class, EdgeDirection.INOUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, null, SubLink.class, EdgeDirection.OUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, null, SubLink.class, EdgeDirection.IN,
				new LinkedList<Edge>());

		checkIncidenceList(v0, null, LinkBack.class, EdgeDirection.INOUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, null, LinkBack.class, EdgeDirection.OUT,
				new LinkedList<Edge>());
		checkIncidenceList(v0, null, LinkBack.class, EdgeDirection.IN,
				new LinkedList<Edge>());
		commit(g);
	}

	/**
	 * Checks if a vertex has only incident edges of type SubLink.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestClassEdgeDirection1()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		LinkedList<Edge> v0linkInout = new LinkedList<Edge>();
		LinkedList<Edge> v0linkOut = new LinkedList<Edge>();
		LinkedList<Edge> v0linkIn = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkInout = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkOut = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkIn = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackInout = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackOut = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackIn = new LinkedList<Edge>();
		LinkedList<Edge> v1linkInout = new LinkedList<Edge>();
		LinkedList<Edge> v1linkOut = new LinkedList<Edge>();
		LinkedList<Edge> v1linkIn = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkInout = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkOut = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkIn = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackInout = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackOut = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackIn = new LinkedList<Edge>();
		Edge e = g.createSubLink((DoubleSubNode) v0, (SuperNode) v1);
		v0linkInout.add(e);
		v0linkOut.add(e);
		v0sublinkInout.add(e);
		v0sublinkOut.add(e);
		v1linkInout.add(e.getReversedEdge());
		v1linkIn.add(e.getReversedEdge());
		v1sublinkInout.add(e.getReversedEdge());
		v1sublinkIn.add(e.getReversedEdge());
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, null, Link.class, EdgeDirection.INOUT,
				v0linkInout);
		checkIncidenceList(v0, null, Link.class, EdgeDirection.OUT, v0linkOut);
		checkIncidenceList(v0, null, Link.class, EdgeDirection.IN, v0linkIn);

		checkIncidenceList(v0, null, SubLink.class, EdgeDirection.INOUT,
				v0sublinkInout);
		checkIncidenceList(v0, null, SubLink.class, EdgeDirection.OUT,
				v0sublinkOut);
		checkIncidenceList(v0, null, SubLink.class, EdgeDirection.IN,
				v0sublinkIn);

		checkIncidenceList(v0, null, LinkBack.class, EdgeDirection.INOUT,
				v0linkbackInout);
		checkIncidenceList(v0, null, LinkBack.class, EdgeDirection.OUT,
				v0linkbackOut);
		checkIncidenceList(v0, null, LinkBack.class, EdgeDirection.IN,
				v0linkbackIn);

		checkIncidenceList(v1, null, Link.class, EdgeDirection.INOUT,
				v1linkInout);
		checkIncidenceList(v1, null, Link.class, EdgeDirection.OUT, v1linkOut);
		checkIncidenceList(v1, null, Link.class, EdgeDirection.IN, v1linkIn);

		checkIncidenceList(v1, null, SubLink.class, EdgeDirection.INOUT,
				v1sublinkInout);
		checkIncidenceList(v1, null, SubLink.class, EdgeDirection.OUT,
				v1sublinkOut);
		checkIncidenceList(v1, null, SubLink.class, EdgeDirection.IN,
				v1sublinkIn);

		checkIncidenceList(v1, null, LinkBack.class, EdgeDirection.INOUT,
				v1linkbackInout);
		checkIncidenceList(v1, null, LinkBack.class, EdgeDirection.OUT,
				v1linkbackOut);
		checkIncidenceList(v1, null, LinkBack.class, EdgeDirection.IN,
				v1linkbackIn);
		commit(g);
	}

	/**
	 * Checks incidences in a manually build graph.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestClassEdgeDirection2()
			throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createSubNode();
		Vertex v1 = g.createDoubleSubNode();
		Vertex v2 = g.createSuperNode();
		LinkedList<Edge> v0linkInout = new LinkedList<Edge>();
		LinkedList<Edge> v0linkOut = new LinkedList<Edge>();
		LinkedList<Edge> v0linkIn = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkInout = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkOut = new LinkedList<Edge>();
		LinkedList<Edge> v0sublinkIn = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackInout = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackOut = new LinkedList<Edge>();
		LinkedList<Edge> v0linkbackIn = new LinkedList<Edge>();
		LinkedList<Edge> v1linkInout = new LinkedList<Edge>();
		LinkedList<Edge> v1linkOut = new LinkedList<Edge>();
		LinkedList<Edge> v1linkIn = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkInout = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkOut = new LinkedList<Edge>();
		LinkedList<Edge> v1sublinkIn = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackInout = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackOut = new LinkedList<Edge>();
		LinkedList<Edge> v1linkbackIn = new LinkedList<Edge>();
		LinkedList<Edge> v2linkInout = new LinkedList<Edge>();
		LinkedList<Edge> v2linkOut = new LinkedList<Edge>();
		LinkedList<Edge> v2linkIn = new LinkedList<Edge>();
		LinkedList<Edge> v2sublinkInout = new LinkedList<Edge>();
		LinkedList<Edge> v2sublinkOut = new LinkedList<Edge>();
		LinkedList<Edge> v2sublinkIn = new LinkedList<Edge>();
		LinkedList<Edge> v2linkbackInout = new LinkedList<Edge>();
		LinkedList<Edge> v2linkbackOut = new LinkedList<Edge>();
		LinkedList<Edge> v2linkbackIn = new LinkedList<Edge>();
		Edge e = g.createLink((AbstractSuperNode) v0, (SuperNode) v1);
		v0linkInout.add(e);
		v0linkOut.add(e);
		v1linkInout.add(e.getReversedEdge());
		v1linkIn.add(e.getReversedEdge());
		e = g.createSubLink((DoubleSubNode) v1, (SuperNode) v2);
		v1linkInout.add(e);
		v1linkOut.add(e);
		v1sublinkInout.add(e);
		v1sublinkOut.add(e);
		v2linkInout.add(e.getReversedEdge());
		v2linkIn.add(e.getReversedEdge());
		v2sublinkInout.add(e.getReversedEdge());
		v2sublinkIn.add(e.getReversedEdge());
		e = g.createLinkBack((SuperNode) v2, (DoubleSubNode) v1);
		v2linkbackInout.add(e);
		v2linkbackOut.add(e);
		v1linkbackInout.add(e.getReversedEdge());
		v1linkbackIn.add(e.getReversedEdge());
		e = g.createLinkBack((SuperNode) v1, (DoubleSubNode) v1);
		v1linkbackInout.add(e);
		v1linkbackOut.add(e);
		v1linkbackInout.add(e.getReversedEdge());
		v1linkbackIn.add(e.getReversedEdge());
		commit(g);

		createReadOnlyTransaction(g);
		checkIncidenceList(v0, null, Link.class, EdgeDirection.INOUT,
				v0linkInout);
		checkIncidenceList(v0, null, Link.class, EdgeDirection.OUT, v0linkOut);
		checkIncidenceList(v0, null, Link.class, EdgeDirection.IN, v0linkIn);

		checkIncidenceList(v0, null, SubLink.class, EdgeDirection.INOUT,
				v0sublinkInout);
		checkIncidenceList(v0, null, SubLink.class, EdgeDirection.OUT,
				v0sublinkOut);
		checkIncidenceList(v0, null, SubLink.class, EdgeDirection.IN,
				v0sublinkIn);

		checkIncidenceList(v0, null, LinkBack.class, EdgeDirection.INOUT,
				v0linkbackInout);
		checkIncidenceList(v0, null, LinkBack.class, EdgeDirection.OUT,
				v0linkbackOut);
		checkIncidenceList(v0, null, LinkBack.class, EdgeDirection.IN,
				v0linkbackIn);

		checkIncidenceList(v1, null, Link.class, EdgeDirection.INOUT,
				v1linkInout);
		checkIncidenceList(v1, null, Link.class, EdgeDirection.OUT, v1linkOut);
		checkIncidenceList(v1, null, Link.class, EdgeDirection.IN, v1linkIn);

		checkIncidenceList(v1, null, SubLink.class, EdgeDirection.INOUT,
				v1sublinkInout);
		checkIncidenceList(v1, null, SubLink.class, EdgeDirection.OUT,
				v1sublinkOut);
		checkIncidenceList(v1, null, SubLink.class, EdgeDirection.IN,
				v1sublinkIn);

		checkIncidenceList(v1, null, LinkBack.class, EdgeDirection.INOUT,
				v1linkbackInout);
		checkIncidenceList(v1, null, LinkBack.class, EdgeDirection.OUT,
				v1linkbackOut);
		checkIncidenceList(v1, null, LinkBack.class, EdgeDirection.IN,
				v1linkbackIn);

		checkIncidenceList(v2, null, Link.class, EdgeDirection.INOUT,
				v2linkInout);
		checkIncidenceList(v2, null, Link.class, EdgeDirection.OUT, v2linkOut);
		checkIncidenceList(v2, null, Link.class, EdgeDirection.IN, v2linkIn);

		checkIncidenceList(v2, null, SubLink.class, EdgeDirection.INOUT,
				v2sublinkInout);
		checkIncidenceList(v2, null, SubLink.class, EdgeDirection.OUT,
				v2sublinkOut);
		checkIncidenceList(v2, null, SubLink.class, EdgeDirection.IN,
				v2sublinkIn);

		checkIncidenceList(v2, null, LinkBack.class, EdgeDirection.INOUT,
				v2linkbackInout);
		checkIncidenceList(v2, null, LinkBack.class, EdgeDirection.OUT,
				v2linkbackOut);
		checkIncidenceList(v2, null, LinkBack.class, EdgeDirection.IN,
				v2linkbackIn);
		commit(g);
	}

	/**
	 * Random test.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void incidencesTestClassEdgeDirection3()
			throws CommitFailedException {
		for (int i = 0; i < ITERATIONS; i++) {
			g = createNewGraph();
			createTransaction(g);
			Vertex[] vertices = new Vertex[] { g.createSubNode(),
					g.createDoubleSubNode(), g.createSuperNode() };
			LinkedList<LinkedList<Edge>> linkinout = new LinkedList<LinkedList<Edge>>();
			linkinout.add(new LinkedList<Edge>());
			linkinout.add(new LinkedList<Edge>());
			linkinout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> linkout = new LinkedList<LinkedList<Edge>>();
			linkout.add(new LinkedList<Edge>());
			linkout.add(new LinkedList<Edge>());
			linkout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> linkin = new LinkedList<LinkedList<Edge>>();
			linkin.add(new LinkedList<Edge>());
			linkin.add(new LinkedList<Edge>());
			linkin.add(new LinkedList<Edge>());

			LinkedList<LinkedList<Edge>> sublinkinout = new LinkedList<LinkedList<Edge>>();
			sublinkinout.add(new LinkedList<Edge>());
			sublinkinout.add(new LinkedList<Edge>());
			sublinkinout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> sublinkout = new LinkedList<LinkedList<Edge>>();
			sublinkout.add(new LinkedList<Edge>());
			sublinkout.add(new LinkedList<Edge>());
			sublinkout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> sublinkin = new LinkedList<LinkedList<Edge>>();
			sublinkin.add(new LinkedList<Edge>());
			sublinkin.add(new LinkedList<Edge>());
			sublinkin.add(new LinkedList<Edge>());

			LinkedList<LinkedList<Edge>> linkbackinout = new LinkedList<LinkedList<Edge>>();
			linkbackinout.add(new LinkedList<Edge>());
			linkbackinout.add(new LinkedList<Edge>());
			linkbackinout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> linkbackout = new LinkedList<LinkedList<Edge>>();
			linkbackout.add(new LinkedList<Edge>());
			linkbackout.add(new LinkedList<Edge>());
			linkbackout.add(new LinkedList<Edge>());
			LinkedList<LinkedList<Edge>> linkbackin = new LinkedList<LinkedList<Edge>>();
			linkbackin.add(new LinkedList<Edge>());
			linkbackin.add(new LinkedList<Edge>());
			linkbackin.add(new LinkedList<Edge>());

			for (int j = 0; j < 30; j++) {
				int edge = rand.nextInt(3);
				int start = rand.nextInt(2);
				int end = rand.nextInt(2) + 1;
				Edge e = null;
				switch (edge) {
				case 0:
					e = g.createLink((AbstractSuperNode) vertices[start],
							(SuperNode) vertices[end]);
					linkinout.get(start).add(e);
					linkout.get(start).add(e);
					linkinout.get(end).add(e.getReversedEdge());
					linkin.get(end).add(e.getReversedEdge());
					break;
				case 1:
					e = g.createLinkBack((SuperNode) vertices[end],
							(AbstractSuperNode) vertices[start]);
					linkbackinout.get(end).add(e);
					linkbackout.get(end).add(e);
					linkbackinout.get(start).add(e.getReversedEdge());
					linkbackin.get(start).add(e.getReversedEdge());
					break;
				case 2:
					e = g.createSubLink((DoubleSubNode) vertices[1],
							(SuperNode) vertices[end]);
					linkinout.get(1).add(e);
					linkout.get(1).add(e);
					sublinkinout.get(1).add(e);
					sublinkout.get(1).add(e);
					linkinout.get(end).add(e.getReversedEdge());
					linkin.get(end).add(e.getReversedEdge());
					sublinkinout.get(end).add(e.getReversedEdge());
					sublinkin.get(end).add(e.getReversedEdge());
					break;
				}
			}
			commit(g);

			createReadOnlyTransaction(g);
			checkIncidenceList(vertices[0], null, Link.class,
					EdgeDirection.INOUT, linkinout.get(0));
			checkIncidenceList(vertices[0], null, Link.class,
					EdgeDirection.OUT, linkout.get(0));
			checkIncidenceList(vertices[0], null, Link.class, EdgeDirection.IN,
					linkin.get(0));

			checkIncidenceList(vertices[0], null, SubLink.class,
					EdgeDirection.INOUT, sublinkinout.get(0));
			checkIncidenceList(vertices[0], null, SubLink.class,
					EdgeDirection.OUT, sublinkout.get(0));
			checkIncidenceList(vertices[0], null, SubLink.class,
					EdgeDirection.IN, sublinkin.get(0));

			checkIncidenceList(vertices[0], null, LinkBack.class,
					EdgeDirection.INOUT, linkbackinout.get(0));
			checkIncidenceList(vertices[0], null, LinkBack.class,
					EdgeDirection.OUT, linkbackout.get(0));
			checkIncidenceList(vertices[0], null, LinkBack.class,
					EdgeDirection.IN, linkbackin.get(0));

			checkIncidenceList(vertices[1], null, Link.class,
					EdgeDirection.INOUT, linkinout.get(1));
			checkIncidenceList(vertices[1], null, Link.class,
					EdgeDirection.OUT, linkout.get(1));
			checkIncidenceList(vertices[1], null, Link.class, EdgeDirection.IN,
					linkin.get(1));

			checkIncidenceList(vertices[1], null, SubLink.class,
					EdgeDirection.INOUT, sublinkinout.get(1));
			checkIncidenceList(vertices[1], null, SubLink.class,
					EdgeDirection.OUT, sublinkout.get(1));
			checkIncidenceList(vertices[1], null, SubLink.class,
					EdgeDirection.IN, sublinkin.get(1));

			checkIncidenceList(vertices[1], null, LinkBack.class,
					EdgeDirection.INOUT, linkbackinout.get(1));
			checkIncidenceList(vertices[1], null, LinkBack.class,
					EdgeDirection.OUT, linkbackout.get(1));
			checkIncidenceList(vertices[1], null, LinkBack.class,
					EdgeDirection.IN, linkbackin.get(1));

			checkIncidenceList(vertices[2], null, Link.class,
					EdgeDirection.INOUT, linkinout.get(2));
			checkIncidenceList(vertices[2], null, Link.class,
					EdgeDirection.OUT, linkout.get(2));
			checkIncidenceList(vertices[2], null, Link.class, EdgeDirection.IN,
					linkin.get(2));

			checkIncidenceList(vertices[2], null, SubLink.class,
					EdgeDirection.INOUT, sublinkinout.get(2));
			checkIncidenceList(vertices[2], null, SubLink.class,
					EdgeDirection.OUT, sublinkout.get(2));
			checkIncidenceList(vertices[2], null, SubLink.class,
					EdgeDirection.IN, sublinkin.get(2));

			checkIncidenceList(vertices[2], null, LinkBack.class,
					EdgeDirection.INOUT, linkbackinout.get(2));
			checkIncidenceList(vertices[2], null, LinkBack.class,
					EdgeDirection.OUT, linkbackout.get(2));
			checkIncidenceList(vertices[2], null, LinkBack.class,
					EdgeDirection.IN, linkbackin.get(2));
			commit(g);
		}
	}

	/*
	 * Test of the Interface GraphElement
	 */

	// tests of the method Graph getGraph();
	/**
	 * Checks some cases for true and false.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getGraphTest() throws CommitFailedException {
		VertexTestGraph anotherGraph = createNewGraph();
		createTransaction(anotherGraph);
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = anotherGraph.createDoubleSubNode();
		Vertex v2 = g.createDoubleSubNode();
		commit(g);
		commit(anotherGraph);
		createReadOnlyTransaction(anotherGraph);
		createReadOnlyTransaction(g);
		assertEquals(g, v0.getGraph());
		assertEquals(anotherGraph, v1.getGraph());
		assertEquals(g, v2.getGraph());
		commit(anotherGraph);
		commit(g);
	}

	// tests of the method void graphModified();

	/**
	 * Tests if the graphversion is increased if the method is called.
	 */
	// @Test
	// public void graphModifiedTest0() {
	// Vertex v = graph.createDoubleSubNode();
	// long graphversion = graph.getGraphVersion();
	// v.graphModified();
	// assertEquals(++graphversion, graph.getGraphVersion());
	// }
	/**
	 * Tests if the graphversion is increased by creating a new vertex.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void graphModifiedTest1() throws CommitFailedException {
		createReadOnlyTransaction(g);
		long graphversion = g.getGraphVersion();
		commit(g);
		createTransaction(g);
		g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		// Because of a flag in AttributeImpl "defaultValueComputed" a creation
		// of an element already created once, can modify the graph version more
		// than by 1. This is why this test only works with comparison.
		// REMARK: This test would work in the case of an unused graph.
		assertTrue(graphversion < g.getGraphVersion());
		commit(g);
	}

	/**
	 * Tests if the graphversion is increased by deleting a vertex.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void graphModifiedTest2() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		long graphversion = g.getGraphVersion();
		commit(g);
		createTransaction(g);
		v.delete();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(graphversion + 1, g.getGraphVersion());
		commit(g);
	}

	/**
	 * Tests if the graphversion is increased by changing the attributes of a
	 * vertex.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void graphModifiedTest3() throws CommitFailedException {
		createTransaction(g);
		Vertex v = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		long graphversion = g.getGraphVersion();
		commit(g);
		createTransaction(g);
		((DoubleSubNode) v).set_number(4);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(graphversion + 1, g.getGraphVersion());
		commit(g);
	}

	/*
	 * Test of the Interface AttributedElement
	 */

	// tests of the method AttributedElementClass getAttributedElementClass();
	/**
	 * Some test cases for getAttributedElementClass
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getAttributedElementClassTest() throws CommitFailedException {
		createTransaction(g);
		VertexClass[] vertices = getVertexClasses();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createSubNode();
		Vertex v2 = g.createSuperNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(vertices[3], v0.getAttributedElementClass());
		assertEquals(vertices[1], v1.getAttributedElementClass());
		assertEquals(vertices[2], v2.getAttributedElementClass());
		commit(g);
	}

	// tests of the method AttributedElementClass getAttributedElementClass();

	/**
	 * Some test cases for getSchemaClass
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getSchemaClassTest() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createSubNode();
		Vertex v2 = g.createSuperNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(DoubleSubNode.class, v0.getSchemaClass());
		assertEquals(SubNode.class, v1.getSchemaClass());
		assertEquals(SuperNode.class, v2.getSchemaClass());
		commit(g);
	}

	// tests of the method GraphClass getGraphClass();

	/**
	 * Some test cases for getGraphClass
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getGraphClassTest() throws CommitFailedException {
		VertexTestGraph anotherGraph = createNewGraph();
		createTransaction(anotherGraph);
		createTransaction(g);
		GraphClass gc = g.getSchema().getGraphClass();
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = anotherGraph.createDoubleSubNode();
		Vertex v2 = g.createDoubleSubNode();
		commit(anotherGraph);
		commit(g);
		createReadOnlyTransaction(anotherGraph);
		createReadOnlyTransaction(g);
		assertEquals(gc, v0.getGraphClass());
		assertEquals(gc, v1.getGraphClass());
		assertEquals(gc, v2.getGraphClass());
		commit(anotherGraph);
		commit(g);
	}

	// tests of the method Object getAttribute(String name) throws
	// NoSuchFieldException;

	/**
	 * Tests if the value of the correct attribute is returned.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getAttributeTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v = g.createDoubleSubNode();
		PMap<Integer, String> map = JGraLab.map();
		v.set_nodeMap(map);
		v.set_name("test");
		v.set_number(4);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(map, v.getAttribute("nodeMap"));
		assertEquals("test", v.getAttribute("name"));
		assertEquals(4, v.getAttribute("number"));
		commit(g);
	}

	/**
	 * Tests if an exception is thrown if you want to get an attribute which
	 * doesn't exist.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = NoSuchAttributeException.class)
	public void getAttributeTest1() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		v.getAttribute("cd");
		commit(g);
	}

	/**
	 * Tests if an exception is thrown if you want to get an attribute with an
	 * empty name.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = NoSuchAttributeException.class)
	public void getAttributeTest2() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		v.getAttribute("");
		commit(g);
	}

	// tests of the method void setAttribute(String name, Object data) throws
	// NoSuchFieldException;

	/**
	 * Tests if an existing attribute is correct set.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void setAttributeTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v = g.createDoubleSubNode();
		PMap<Integer, String> map = JGraLab.map();
		v.setAttribute("nodeMap", map);
		v.setAttribute("name", "test");
		v.setAttribute("number", 4);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(map, v.getAttribute("nodeMap"));
		assertEquals("test", v.getAttribute("name"));
		assertEquals(4, v.getAttribute("number"));
		commit(g);
	}

	/**
	 * Tests if an existing attribute is set to null.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void setAttributeTest1() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v = g.createDoubleSubNode();
		v.setAttribute("nodeMap", null);
		v.setAttribute("name", null);
		commit(g);
		createReadOnlyTransaction(g);
		assertNull(v.getAttribute("nodeMap"));
		assertNull(v.getAttribute("name"));
		commit(g);
	}

	/**
	 * Tests if an exception is thrown if you want to get an attribute which
	 * doesn't exist.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = NoSuchAttributeException.class)
	public void setAttributeTest2() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v = g.createDoubleSubNode();
		v.setAttribute("cd", "a");
		commit(g);
	}

	/**
	 * Tests if an exception is thrown if you want to get an attribute with an
	 * empty name.
	 * 
	 * @throws CommitFailedException
	 */
	@Test(expected = NoSuchAttributeException.class)
	public void setAttributeTest3() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v = g.createDoubleSubNode();
		v.setAttribute("", "a");
		commit(g);
	}

	// tests of the method Schema getSchema();

	/**
	 * Some tests.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void getSchemaTest() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createSubNode();
		Vertex v2 = g.createSuperNode();
		Schema schema = g.getSchema();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(schema, v0.getSchema());
		assertEquals(schema, v1.getSchema());
		assertEquals(schema, v2.getSchema());
		commit(g);
	}

	/*
	 * Test of the Interface Comparable
	 */

	// tests of the method int compareTo(AttributedElement a);
	/**
	 * Test if a vertex is equal to itself.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void compareToTest0() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(0, v0.compareTo(v0));
		commit(g);
	}

	/**
	 * Test if a vertex is smaller than another.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void compareToTest1() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertTrue(v0.compareTo(v1) < 0);
		commit(g);
	}

	/**
	 * Test if a vertex is greater than another.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void compareToTest2() throws CommitFailedException {
		createTransaction(g);
		Vertex v0 = g.createDoubleSubNode();
		Vertex v1 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertTrue(v1.compareTo(v0) > 0);
		commit(g);
	}

	/*
	 * Test of the generated methods
	 */

	// tests of the methods setName and getName
	@Test
	public void setGetNameTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		v0.set_name("aName");
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals("aName", v0.get_name());
		commit(g);
		createTransaction(g);
		v0.set_name("bName");
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals("bName", v0.get_name());
		commit(g);
		createTransaction(g);
		v0.set_name("cName");
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals("cName", v0.get_name());
		commit(g);
	}

	// tests of the methods setNumber and getNumber
	@Test
	public void setGetNumberTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		v0.set_number(0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(0, v0.get_number());
		commit(g);
		createTransaction(g);
		v0.set_number(1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(1, v0.get_number());
		commit(g);
		createTransaction(g);
		v0.set_number(-1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(-1, v0.get_number());
		commit(g);
	}

	// tests of the methods setNodeMap and getNodeMap
	@Test
	public void setGetNodeMapTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		PMap<Integer, String> map = JGraLab.map();
		v0.set_nodeMap(map);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(map, v0.get_nodeMap());
		commit(g);
		createTransaction(g);
		map = map.plus(1, "first");
		v0.set_nodeMap(map);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(map, v0.get_nodeMap());
		commit(g);
		createTransaction(g);
		map = map.plus(2, "second");
		v0.set_nodeMap(map);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(map, v0.get_nodeMap());
		commit(g);
	}

	// tests of the method addSource
	@Test
	public void addSourceTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		// v0 -->{Link} v0
		Link e0 = v0.add_source(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e0.getAlpha());
		assertEquals(v0, e0.getOmega());
		commit(g);
		// v1 -->{Link} v0
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		Link e1 = v0.add_source(v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v1, e1.getAlpha());
		assertEquals(v0, e1.getOmega());
		commit(g);
		// v0 -->{Link} v1
		createTransaction(g);
		Link e2 = v1.add_source(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e2.getAlpha());
		assertEquals(v1, e2.getOmega());
		commit(g);
		// checks if the edges are in the edge-list of the graph
		createReadOnlyTransaction(g);
		checkEdgeList(e0, e1, e2);
		// checks if the edges are in the incidenceList of both vertices
		checkIncidences(v0, e0, e0.getReversedEdge(), e1.getReversedEdge(), e2);
		checkIncidences(v1, e1, e2.getReversedEdge());
		commit(g);
	}

	// tests of the method removeSource
	@Test
	public void removeSourceTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		Link e0 = v0.add_source(v0);
		v0.add_source(v1);
		Link e2 = v1.add_source(v1);
		v0.add_source(v1);
		// remove all edges v1 --> v0
		v0.remove_source(v1);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList(e0, e2);
		checkIncidences(v0, e0, e0.getReversedEdge());
		checkIncidences(v1, e2, e2.getReversedEdge());
		commit(g);
		// remove all edges v0 --> v0
		createTransaction(g);
		v0.remove_source(v0);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList(e2);
		checkIncidences(v0);
		checkIncidences(v1, e2, e2.getReversedEdge());
		commit(g);
		// remove all edges v1 --> v1
		createTransaction(g);
		v1.remove_source(v1);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList();
		checkIncidences(v0);
		checkIncidences(v1);
		commit(g);
	}

	// tests of the method getSourceList
	@Test
	public void getSourceListTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		SuperNode v1 = g.createSuperNode();
		SubNode v2 = g.createSubNode();
		v0.add_source(v0);
		v0.add_source(v2);
		v1.add_source(v0);
		commit(g);

		createReadOnlyTransaction(g);
		Iterator<? extends AbstractSuperNode> nodes = v0.get_source()
				.iterator();
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertTrue(nodes.hasNext());
		assertEquals(v2, nodes.next());
		assertFalse(nodes.hasNext());

		nodes = v1.get_source().iterator();
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertFalse(nodes.hasNext());
		commit(g);
	}

	/**
	 * Checks if <code>v.incidences()</code> has the same elements in the same
	 * order like <code>e</code>.
	 * 
	 * @param v
	 *            the Vertex which incident edges should be checked
	 * @param e
	 *            the edges to check
	 */
	private void checkIncidences(Vertex v, Edge... e) {
		assertEquals(v.getDegree(), e.length);
		int i = 0;
		for (Edge f : v.incidences()) {
			if (i >= e.length) {
				fail("No further edges expected!");
			} else {
				assertEquals(f, e[i]);
				i++;
			}
		}
	}

	/**
	 * Checks if <code>graph.edges()</code> has the same elements in the same
	 * order like <code>e</code>.
	 * 
	 * @param e
	 *            the edges to check
	 */
	private void checkEdgeList(Edge... e) {
		assertEquals(e.length, g.getECount());
		int i = 0;
		for (Edge f : g.edges()) {
			if (i >= e.length) {
				fail("No further edges expected!");
			} else {
				assertEquals(f, e[i]);
				i++;
			}
		}
	}

	// tests of the method addSourceb
	@Test
	public void addSourcebTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		// v0 -->{LinkBack} v0
		LinkBack e0 = v0.add_sourceb(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e0.getAlpha());
		assertEquals(v0, e0.getOmega());
		commit(g);
		// v1 -->{LinkBack} v0
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		LinkBack e1 = v0.add_sourceb(v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v1, e1.getAlpha());
		assertEquals(v0, e1.getOmega());
		commit(g);
		// v0 -->{LinkBack} v1
		createTransaction(g);
		LinkBack e2 = v1.add_sourceb(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e2.getAlpha());
		assertEquals(v1, e2.getOmega());
		// checks if the edges are in the edge-list of the graph
		checkEdgeList(e0, e1, e2);
		// checks if the edges are in the incidenceList of both vertices
		checkIncidences(v0, e0, e0.getReversedEdge(), e1.getReversedEdge(), e2);
		checkIncidences(v1, e1, e2.getReversedEdge());
		commit(g);
	}

	// tests of the method removeSourceb
	@Test
	public void removeSourcebTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		LinkBack e0 = v0.add_sourceb(v0);
		v0.add_sourceb(v1);
		LinkBack e2 = v1.add_sourceb(v1);
		v0.add_sourceb(v1);
		// remove all edges v1 --> v0
		v0.remove_sourceb(v1);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList(e0, e2);
		checkIncidences(v0, e0, e0.getReversedEdge());
		checkIncidences(v1, e2, e2.getReversedEdge());
		commit(g);
		// remove all edges v0 --> v0
		createTransaction(g);
		v0.remove_sourceb(v0);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList(e2);
		checkIncidences(v0);
		checkIncidences(v1, e2, e2.getReversedEdge());
		commit(g);
		// remove all edges v1 --> v1
		createTransaction(g);
		v1.remove_sourceb(v1);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList();
		checkIncidences(v0);
		checkIncidences(v1);
		commit(g);
	}

	// tests of the method getSourcebList
	@Test
	public void getSourcebListTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		SuperNode v1 = g.createSuperNode();
		SubNode v2 = g.createSubNode();
		v0.add_sourceb(v0);
		v0.add_sourceb(v1);
		v2.add_sourceb(v0);
		commit(g);
		createReadOnlyTransaction(g);
		Iterator<? extends SuperNode> nodes = v0.get_sourceb().iterator();
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertTrue(nodes.hasNext());
		assertEquals(v1, nodes.next());
		assertFalse(nodes.hasNext());

		nodes = v2.get_sourceb().iterator();
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertFalse(nodes.hasNext());
		commit(g);
	}

	// tests of the method addSourcec
	@Test
	public void addSourcecTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		// v0 -->{SubLink} v0
		SubLink e0 = v0.add_sourcec(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e0.getAlpha());
		assertEquals(v0, e0.getOmega());
		commit(g);
		// v1 -->{SubLink} v0
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		SubLink e1 = v0.add_sourcec(v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v1, e1.getAlpha());
		assertEquals(v0, e1.getOmega());
		commit(g);
		// v0 -->{SubLink} v1
		createTransaction(g);
		SubLink e2 = v1.add_sourcec(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e2.getAlpha());
		assertEquals(v1, e2.getOmega());
		// checks if the edges are in the edge-list of the graph
		checkEdgeList(e0, e1, e2);
		// checks if the edges are in the incidenceList of both vertices
		checkIncidences(v0, e0, e0.getReversedEdge(), e1.getReversedEdge(), e2);
		checkIncidences(v1, e1, e2.getReversedEdge());
		commit(g);
	}

	// tests of the method remove_sourcec
	/**
	 * Removes the sourcec of v0 --&gt v0.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void remove_sourcecTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		v0.add_sourcec(v0);
		v0.remove_sourcec(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(0, g.getECount());
		commit(g);
	}

	/**
	 * Removes the sourcec of v0 --&gt v1.
	 * 
	 * @throws CommitFailedException
	 */
	@Test
	public void remove_sourcecTest1() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		SuperNode v1 = g.createSuperNode();
		v1.add_sourcec(v0);
		v1.remove_sourcec(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(0, g.getECount());
		commit(g);
	}

	// tests of the method getSourcecList
	@Test
	public void getSourcecListTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		SuperNode v1 = g.createSuperNode();
		v0.add_sourcec(v0);
		v0.add_sourcec(v0);
		v1.add_sourcec(v0);
		commit(g);
		createReadOnlyTransaction(g);
		Iterator<? extends SuperNode> nodes = v0.get_sourcec().iterator();
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertFalse(nodes.hasNext());

		nodes = v1.get_sourcec().iterator();
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertFalse(nodes.hasNext());
		commit(g);
	}

	// tests of the method addTarget
	@Test
	public void addTargetTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		// v0 -->{Link} v0
		Link e0 = v0.add_target(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e0.getAlpha());
		assertEquals(v0, e0.getOmega());
		commit(g);
		// v0 -->{Link} v1
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		Link e1 = v0.add_target(v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e1.getAlpha());
		assertEquals(v1, e1.getOmega());
		commit(g);
		// v1 -->{Link} v0
		createTransaction(g);
		Link e2 = v1.add_target(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v1, e2.getAlpha());
		assertEquals(v0, e2.getOmega());

		// checks if the edges are in the edge-list of the graph
		int i = 0;
		for (Edge e : g.edges()) {
			switch (i) {
			case 0:
				assertEquals(e0, e);
				i++;
				break;
			case 1:
				assertEquals(e1, e);
				i++;
				break;
			case 2:
				assertEquals(e2, e);
				i++;
				break;
			default:
				fail("No further edges expected!");
			}
		}
		// checks if the edges are in the incidenceList of both vertices
		i = 0;
		for (Edge e : v0.incidences()) {
			switch (i) {
			case 0:
				assertEquals(e0, e);
				i++;
				break;
			case 1:
				assertEquals(e0.getReversedEdge(), e);
				i++;
				break;
			case 2:
				assertEquals(e1, e);
				i++;
				break;
			case 3:
				assertEquals(e2.getReversedEdge(), e);
				i++;
				break;
			default:
				fail("No further edges expected!");
			}
		}
		i = 0;
		for (Edge e : v1.incidences()) {
			switch (i) {
			case 0:
				assertEquals(e1.getReversedEdge(), e);
				i++;
				break;
			case 1:
				assertEquals(e2, e);
				i++;
				break;
			default:
				fail("No further edges expected!");
			}
		}
		commit(g);
	}

	// tests of the method remove_target
	@Test
	public void remove_targetTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		Link e0 = v0.add_target(v0);
		v0.add_target(v1);
		Link e2 = v1.add_target(v1);
		v0.add_target(v1);
		// remove all edges v1 --> v0
		v0.remove_target(v1);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList(e0, e2);
		checkIncidences(v0, e0, e0.getReversedEdge());
		checkIncidences(v1, e2, e2.getReversedEdge());
		commit(g);
		// remove all edges v0 --> v0
		createTransaction(g);
		v0.remove_target(v0);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList(e2);
		checkIncidences(v0);
		checkIncidences(v1, e2, e2.getReversedEdge());
		commit(g);
		// remove all edges v1 --> v1
		createTransaction(g);
		v1.remove_target(v1);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList();
		checkIncidences(v0);
		checkIncidences(v1);
		commit(g);
	}

	// tests of the method getTargetList
	@Test
	public void getTargetListTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		SuperNode v1 = g.createSuperNode();
		SubNode v2 = g.createSubNode();
		v0.add_target(v0);
		v2.add_target(v0);
		v0.add_target(v1);
		commit(g);

		createReadOnlyTransaction(g);
		Iterator<? extends SuperNode> nodes = v0.get_target().iterator();
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertTrue(nodes.hasNext());
		assertEquals(v1, nodes.next());
		assertFalse(nodes.hasNext());

		nodes = v2.get_target().iterator();
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertFalse(nodes.hasNext());
		commit(g);
	}

	// tests of the method addTargetb
	@Test
	public void addTargetbTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		// v0 -->{LinkBack} v0
		LinkBack e0 = v0.add_targetb(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e0.getAlpha());
		assertEquals(v0, e0.getOmega());
		commit(g);
		// v0 -->{LinkBack} v1
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		LinkBack e1 = v0.add_targetb(v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e1.getAlpha());
		assertEquals(v1, e1.getOmega());
		commit(g);
		// v1 -->{LinkBack} v0
		createTransaction(g);
		LinkBack e2 = v1.add_targetb(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v1, e2.getAlpha());
		assertEquals(v0, e2.getOmega());
		// checks if the edges are in the edge-list of the graph
		int i = 0;
		for (Edge e : g.edges()) {
			switch (i) {
			case 0:
				assertEquals(e0, e);
				i++;
				break;
			case 1:
				assertEquals(e1, e);
				i++;
				break;
			case 2:
				assertEquals(e2, e);
				i++;
				break;
			default:
				fail("No further edges expected!");
			}
		}
		// checks if the edges are in the incidenceList of both vertices
		i = 0;
		for (Edge e : v0.incidences()) {
			switch (i) {
			case 0:
				assertEquals(e0, e);
				i++;
				break;
			case 1:
				assertEquals(e0.getReversedEdge(), e);
				i++;
				break;
			case 2:
				assertEquals(e1, e);
				i++;
				break;
			case 3:
				assertEquals(e2.getReversedEdge(), e);
				i++;
				break;
			default:
				fail("No further edges expected!");
			}
		}
		i = 0;
		for (Edge e : v1.incidences()) {
			switch (i) {
			case 0:
				assertEquals(e1.getReversedEdge(), e);
				i++;
				break;
			case 1:
				assertEquals(e2, e);
				i++;
				break;
			default:
				fail("No further edges expected!");
			}
		}
		commit(g);
	}

	// tests of the method remove_targetb
	@Test
	public void remove_targetbTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		LinkBack e0 = v0.add_targetb(v0);
		v0.add_targetb(v1);
		LinkBack e2 = v1.add_targetb(v1);
		v0.add_targetb(v1);
		// remove all edges v1 --> v0
		v0.remove_targetb(v1);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList(e0, e2);
		checkIncidences(v0, e0, e0.getReversedEdge());
		checkIncidences(v1, e2, e2.getReversedEdge());
		commit(g);
		// remove all edges v0 --> v0
		createTransaction(g);
		v0.remove_targetb(v0);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList(e2);
		checkIncidences(v0);
		checkIncidences(v1, e2, e2.getReversedEdge());
		commit(g);
		// remove all edges v1 --> v1
		createTransaction(g);
		v1.remove_targetb(v1);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList();
		checkIncidences(v0);
		checkIncidences(v1);
		commit(g);
	}

	// tests of the method getTargetbList
	@Test
	public void getTargetbListTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		SuperNode v1 = g.createSuperNode();
		SubNode v2 = g.createSubNode();
		v0.add_targetb(v0);
		v0.add_targetb(v2);
		v1.add_targetb(v0);
		commit(g);

		createReadOnlyTransaction(g);
		Iterator<? extends AbstractSuperNode> nodes = v0.get_targetb()
				.iterator();
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertTrue(nodes.hasNext());
		assertEquals(v2, nodes.next());
		assertFalse(nodes.hasNext());

		nodes = v1.get_targetb().iterator();
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertFalse(nodes.hasNext());
		commit(g);
	}

	// tests of the method addTargetc
	@Test
	public void addTargetcTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		// v0 -->{SubLink} v0
		SubLink e0 = v0.add_targetc(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e0.getAlpha());
		assertEquals(v0, e0.getOmega());
		commit(g);
		// v0 -->{SubLink} v1
		createTransaction(g);
		DoubleSubNode v1 = g.createDoubleSubNode();
		SubLink e1 = v0.add_targetc(v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v0, e1.getAlpha());
		assertEquals(v1, e1.getOmega());
		commit(g);
		// v1 -->{SubLink} v0
		createTransaction(g);
		SubLink e2 = v1.add_targetc(v0);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v1, e2.getAlpha());
		assertEquals(v0, e2.getOmega());
		// checks if the edges are in the edge-list of the graph
		int i = 0;
		for (Edge e : g.edges()) {
			switch (i) {
			case 0:
				assertEquals(e0, e);
				i++;
				break;
			case 1:
				assertEquals(e1, e);
				i++;
				break;
			case 2:
				assertEquals(e2, e);
				i++;
				break;
			default:
				fail("No further edges expected!");
			}
		}
		// checks if the edges are in the incidenceList of both vertices
		i = 0;
		for (Edge e : v0.incidences()) {
			switch (i) {
			case 0:
				assertEquals(e0, e);
				i++;
				break;
			case 1:
				assertEquals(e0.getReversedEdge(), e);
				i++;
				break;
			case 2:
				assertEquals(e1, e);
				i++;
				break;
			case 3:
				assertEquals(e2.getReversedEdge(), e);
				i++;
				break;
			default:
				fail("No further edges expected!");
			}
		}
		i = 0;
		for (Edge e : v1.incidences()) {
			switch (i) {
			case 0:
				assertEquals(e1.getReversedEdge(), e);
				i++;
				break;
			case 1:
				assertEquals(e2, e);
				i++;
				break;
			default:
				fail("No further edges expected!");
			}
		}
		commit(g);
	}

	// tests of the method remove_targetc
	@Test
	public void remove_targetcTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		SubLink e0 = v0.add_targetc(v0);
		v0.add_targetc(v1);
		SubLink e2 = v1.add_targetc(v1);
		v0.add_targetc(v1);
		// remove all edges v1 --> v0
		v0.remove_targetc(v1);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList(e0, e2);
		checkIncidences(v0, e0, e0.getReversedEdge());
		checkIncidences(v1, e2, e2.getReversedEdge());
		commit(g);
		// remove all edges v0 --> v0
		createTransaction(g);
		v0.remove_targetc(v0);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList(e2);
		checkIncidences(v0);
		checkIncidences(v1, e2, e2.getReversedEdge());
		commit(g);
		// remove all edges v1 --> v1
		createTransaction(g);
		v1.remove_targetc(v1);
		commit(g);
		createReadOnlyTransaction(g);
		checkEdgeList();
		checkIncidences(v0);
		checkIncidences(v1);
		commit(g);
	}

	// tests of the method getTargetcList
	@Test
	public void getTargetcListTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		SuperNode v1 = g.createSuperNode();
		v0.add_targetc(v0);
		v0.add_targetc(v1);
		commit(g);

		createReadOnlyTransaction(g);
		Iterator<? extends SuperNode> nodes = v0.get_targetc().iterator();
		assertTrue(nodes.hasNext());
		assertEquals(v0, nodes.next());
		assertTrue(nodes.hasNext());
		assertEquals(v1, nodes.next());
		assertFalse(nodes.hasNext());
		commit(g);
	}

	// tests of the method getNextAbstractSuperNode
	@Test
	public void getNextAbstractSuperNodeTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		g.createSuperNode();
		SubNode v2 = g.createSubNode();
		g.createSuperNode();
		DoubleSubNode v4 = g.createDoubleSubNode();
		DoubleSubNode v5 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v2, v0.getNextAbstractSuperNode());
		assertEquals(v4, v2.getNextAbstractSuperNode());
		assertEquals(v5, v4.getNextAbstractSuperNode());
		assertNull(v5.getNextAbstractSuperNode());
		commit(g);
	}

	// tests of the method getNextSubNode
	@Test
	public void getNextSubNodeTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		g.createSuperNode();
		SubNode v2 = g.createSubNode();
		g.createSuperNode();
		DoubleSubNode v4 = g.createDoubleSubNode();
		DoubleSubNode v5 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v2, v0.getNextSubNode());
		assertEquals(v4, v2.getNextSubNode());
		assertEquals(v5, v4.getNextSubNode());
		assertNull(v5.getNextSubNode());
		commit(g);
	}

	// tests of the method getNextSuperNode
	@Test
	public void getNextSuperNodeTest0() throws CommitFailedException {
		createTransaction(g);
		SuperNode v0 = g.createSuperNode();
		g.createSubNode();
		SuperNode v2 = g.createSuperNode();
		g.createSubNode();
		DoubleSubNode v4 = g.createDoubleSubNode();
		DoubleSubNode v5 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v2, v0.getNextSuperNode());
		assertEquals(v4, v2.getNextSuperNode());
		assertEquals(v5, v4.getNextSuperNode());
		assertNull(v5.getNextSuperNode());
		commit(g);
	}

	// tests of the method getNextDoubleSubNode
	@Test
	public void getNextDoubleSubNodeTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		g.createSubNode();
		DoubleSubNode v2 = g.createDoubleSubNode();
		g.createSuperNode();
		DoubleSubNode v4 = g.createDoubleSubNode();
		DoubleSubNode v5 = g.createDoubleSubNode();
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(v2, v0.getNextDoubleSubNode());
		assertEquals(v4, v2.getNextDoubleSubNode());
		assertEquals(v5, v4.getNextDoubleSubNode());
		assertNull(v5.getNextDoubleSubNode());
		commit(g);
	}

	// tests of the method getFirstLink
	@Test
	public void getFirstLinkTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		g.createLinkBack(v0, v1);
		SubLink e1 = g.createSubLink(v0, v0);
		Link e2 = g.createLink(v0, v1);
		g.createSubLink(v1, v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstLinkIncidence());
		assertEquals(e2.getReversedEdge(), v1.getFirstLinkIncidence());
		commit(g);
	}

	// tests of the method getFirstLink(EdgeDirection)
	@Test
	public void getFirstLinkEdgeDirectionTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		g.createLinkBack(v0, v1);
		SubLink e1 = g.createSubLink(v0, v0);
		Link e2 = g.createLink(v0, v1);
		SubLink e3 = g.createSubLink(v1, v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstLinkIncidence(EdgeDirection.INOUT));
		assertEquals(e1, v0.getFirstLinkIncidence(EdgeDirection.OUT));
		assertEquals(e1.getReversedEdge(),
				v0.getFirstLinkIncidence(EdgeDirection.IN));
		assertEquals(e2.getReversedEdge(),
				v1.getFirstLinkIncidence(EdgeDirection.INOUT));
		assertEquals(e3, v1.getFirstLinkIncidence(EdgeDirection.OUT));
		assertEquals(e2.getReversedEdge(),
				v1.getFirstLinkIncidence(EdgeDirection.IN));
		commit(g);
	}

	// tests of the method getFirstLinkBack
	@Test
	public void getFirstLinkBackTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		g.createLink(v0, v1);
		LinkBack e1 = g.createLinkBack(v0, v0);
		LinkBack e2 = g.createLinkBack(v0, v1);
		g.createLinkBack(v1, v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstLinkBackIncidence());
		assertEquals(e2.getReversedEdge(), v1.getFirstLinkBackIncidence());
		commit(g);
	}

	// tests of the method getFirstLinkBack(EdgeDirection)
	@Test
	public void getFirstLinkBackEdgeDirectionTest0()
			throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		g.createLink(v0, v1);
		LinkBack e1 = g.createLinkBack(v0, v0);
		LinkBack e2 = g.createLinkBack(v0, v1);
		LinkBack e3 = g.createLinkBack(v1, v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstLinkBackIncidence(EdgeDirection.INOUT));
		assertEquals(e1, v0.getFirstLinkBackIncidence(EdgeDirection.OUT));
		assertEquals(e1.getReversedEdge(),
				v0.getFirstLinkBackIncidence(EdgeDirection.IN));
		assertEquals(e2.getReversedEdge(),
				v1.getFirstLinkBackIncidence(EdgeDirection.INOUT));
		assertEquals(e3, v1.getFirstLinkBackIncidence(EdgeDirection.OUT));
		assertEquals(e2.getReversedEdge(),
				v1.getFirstLinkBackIncidence(EdgeDirection.IN));
		commit(g);
	}

	// tests of the method getFirstSubLink
	@Test
	public void getFirstSubLinkTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		g.createLink(v0, v1);
		SubLink e1 = g.createSubLink(v0, v0);
		SubLink e2 = g.createSubLink(v0, v1);
		g.createSubLink(v1, v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstSubLinkIncidence());
		assertEquals(e2.getReversedEdge(), v1.getFirstSubLinkIncidence());
		commit(g);
	}

	// tests of the method getFirstSubLink(EdgeDirection)
	@Test
	public void getFirstSubLinkEdgeDirectionTest0()
			throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		g.createLink(v0, v1);
		SubLink e1 = g.createSubLink(v0, v0);
		SubLink e2 = g.createSubLink(v0, v1);
		SubLink e3 = g.createSubLink(v1, v1);
		commit(g);
		createReadOnlyTransaction(g);
		assertEquals(e1, v0.getFirstSubLinkIncidence(EdgeDirection.INOUT));
		assertEquals(e1, v0.getFirstSubLinkIncidence(EdgeDirection.OUT));
		assertEquals(e1.getReversedEdge(),
				v0.getFirstSubLinkIncidence(EdgeDirection.IN));
		assertEquals(e2.getReversedEdge(),
				v1.getFirstSubLinkIncidence(EdgeDirection.INOUT));
		assertEquals(e3, v1.getFirstSubLinkIncidence(EdgeDirection.OUT));
		assertEquals(e2.getReversedEdge(),
				v1.getFirstSubLinkIncidence(EdgeDirection.IN));
		commit(g);
	}

	/**
	 * Checks if the edges which are returned by an get#Edge#incidences are the
	 * expected ones.
	 * 
	 * @param incidenceName
	 *            Name of #Edge#
	 * @param v
	 *            the vertex which incident edges should be checked
	 * @param direction
	 *            the direction of the iterated edges or null
	 * @param edges
	 *            the expected edges
	 */
	private void checkGeneratedIncidences(String incidenceName,
			DoubleSubNode v, EdgeDirection direction, Edge... edges) {
		int i = 0;
		if (direction == null) {
			if (incidenceName.equals("Link")) {
				for (Edge e : v.getLinkIncidences()) {
					assertEquals(edges[i], e);
					i++;
				}
			} else if (incidenceName.equals("LinkBack")) {
				for (Edge e : v.getLinkBackIncidences()) {
					assertEquals(edges[i], e);
					i++;
				}
			} else if (incidenceName.equals("SubLink")) {
				for (Edge e : v.getSubLinkIncidences()) {
					assertEquals(edges[i], e);
					i++;
				}
			}
		} else {
			if (incidenceName.equals("Link")) {
				for (Edge e : v.getLinkIncidences(direction)) {
					assertEquals(edges[i], e);
					i++;
				}
			} else if (incidenceName.equals("LinkBack")) {
				for (Edge e : v.getLinkBackIncidences(direction)) {
					assertEquals(edges[i], e);
					i++;
				}
			} else if (incidenceName.equals("SubLink")) {
				for (Edge e : v.getSubLinkIncidences(direction)) {
					assertEquals(edges[i], e);
					i++;
				}
			}
		}
		if (i != edges.length) {
			fail("There were more edges expected");
		}
	}

	// tests of the method get#Edge#Incidences
	@Test
	public void getLinkIncidencesTest0() throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		LinkBack e0 = g.createLinkBack(v0, v1);
		SubLink e1 = g.createSubLink(v0, v0);
		Link e2 = g.createLink(v1, v0);
		SubLink e3 = g.createSubLink(v1, v1);
		LinkBack e4 = g.createLinkBack(v1, v0);
		commit(g);
		createReadOnlyTransaction(g);
		checkGeneratedIncidences("Link", v0, null, e1, e1.getReversedEdge(),
				e2.getReversedEdge());
		checkGeneratedIncidences("Link", v1, null, e2, e3, e3.getReversedEdge());
		checkGeneratedIncidences("LinkBack", v0, null, e0, e4.getReversedEdge());
		checkGeneratedIncidences("LinkBack", v1, null, e0.getReversedEdge(), e4);
		checkGeneratedIncidences("SubLink", v0, null, e1, e1.getReversedEdge());
		checkGeneratedIncidences("SubLink", v1, null, e3, e3.getReversedEdge());
		commit(g);
	}

	// tests of the method get#Edge#Incidences(EdgeDirection)
	@Test
	public void getLinkIncidencesTestEdgeDirection0()
			throws CommitFailedException {
		createTransaction(g);
		DoubleSubNode v0 = g.createDoubleSubNode();
		DoubleSubNode v1 = g.createDoubleSubNode();
		LinkBack e0 = g.createLinkBack(v0, v1);
		SubLink e1 = g.createSubLink(v0, v0);
		Link e2 = g.createLink(v1, v0);
		SubLink e3 = g.createSubLink(v1, v1);
		LinkBack e4 = g.createLinkBack(v1, v0);
		commit(g);
		createReadOnlyTransaction(g);
		checkGeneratedIncidences("Link", v0, EdgeDirection.INOUT, e1,
				e1.getReversedEdge(), e2.getReversedEdge());
		checkGeneratedIncidences("Link", v1, EdgeDirection.INOUT, e2, e3,
				e3.getReversedEdge());
		checkGeneratedIncidences("Link", v0, EdgeDirection.OUT, e1);
		checkGeneratedIncidences("Link", v1, EdgeDirection.OUT, e2, e3);
		checkGeneratedIncidences("Link", v0, EdgeDirection.IN,
				e1.getReversedEdge(), e2.getReversedEdge());
		checkGeneratedIncidences("Link", v1, EdgeDirection.IN,
				e3.getReversedEdge());
		checkGeneratedIncidences("LinkBack", v0, EdgeDirection.INOUT, e0,
				e4.getReversedEdge());
		checkGeneratedIncidences("LinkBack", v1, EdgeDirection.INOUT,
				e0.getReversedEdge(), e4);
		checkGeneratedIncidences("LinkBack", v0, EdgeDirection.OUT, e0);
		checkGeneratedIncidences("LinkBack", v1, EdgeDirection.OUT, e4);
		checkGeneratedIncidences("LinkBack", v0, EdgeDirection.IN,
				e4.getReversedEdge());
		checkGeneratedIncidences("LinkBack", v1, EdgeDirection.IN,
				e0.getReversedEdge());
		checkGeneratedIncidences("SubLink", v0, EdgeDirection.INOUT, e1,
				e1.getReversedEdge());
		checkGeneratedIncidences("SubLink", v1, EdgeDirection.INOUT, e3,
				e3.getReversedEdge());
		checkGeneratedIncidences("SubLink", v0, EdgeDirection.OUT, e1);
		checkGeneratedIncidences("SubLink", v1, EdgeDirection.OUT, e3);
		checkGeneratedIncidences("SubLink", v0, EdgeDirection.IN,
				e1.getReversedEdge());
		checkGeneratedIncidences("SubLink", v1, EdgeDirection.IN,
				e3.getReversedEdge());
		commit(g);
	}

	@Test
	public void isInstanceOfTest() throws CommitFailedException {
		VertexClass a = g.getSchema().getGraphClass().getVertexClass("A");
		VertexClass asn = g.getSchema().getGraphClass()
				.getVertexClass("AbstractSuperNode");
		VertexClass sn = g.getSchema().getGraphClass()
				.getVertexClass("SuperNode");

		createTransaction(g);
		for (DoubleSubNode x : g.getDoubleSubNodeVertices()) {
			assertTrue(x.isInstanceOf(x.getAttributedElementClass()));
			assertTrue(x.isInstanceOf(asn));
			assertTrue(x.isInstanceOf(sn));
			assertFalse(x.isInstanceOf(a));
		}

		for (AbstractSuperNode x : g.getAbstractSuperNodeVertices()) {
			assertTrue(x.isInstanceOf(asn));
			assertFalse(x.isInstanceOf(sn));
			assertFalse(x.isInstanceOf(a));
		}
		commit(g);
	}
}
