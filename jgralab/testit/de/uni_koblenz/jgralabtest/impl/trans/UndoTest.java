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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pcollections.PMap;
import org.pcollections.PSet;
import org.pcollections.PVector;

import de.uni_koblenz.jgralab.ImplementationType;
import de.uni_koblenz.jgralab.JGraLab;
import de.uni_koblenz.jgralab.trans.CommitFailedException;
import de.uni_koblenz.jgralab.trans.InvalidSavepointException;
import de.uni_koblenz.jgralab.trans.Savepoint;
import de.uni_koblenz.jgralab.trans.Transaction;
import de.uni_koblenz.jgralabtest.schemas.record.BooleanType;
import de.uni_koblenz.jgralabtest.schemas.record.Hugo;
import de.uni_koblenz.jgralabtest.schemas.record.Link;
import de.uni_koblenz.jgralabtest.schemas.record.Node;
import de.uni_koblenz.jgralabtest.schemas.record.RecordTestGraph;
import de.uni_koblenz.jgralabtest.schemas.record.RecordTestSchema;

public class UndoTest {

	private RecordTestGraph graph;
	Node node1;
	Node node2;
	String filename = "testit/testdata/record_testgraph.tg";

	@Before
	public void setUp() throws CommitFailedException {
		graph = RecordTestSchema.instance()
				.createRecordTestGraph(ImplementationType.TRANSACTION);
		graph.newTransaction();
		graph.commit();

	}

	@After
	public void tearDown() {
		// new File(filename).delete();
	}

	@Test
	public void testSimpleUndo() throws CommitFailedException,
			InvalidSavepointException {
		Transaction trans = graph.newTransaction();
		assertNull(graph.getFirstNode());
		Savepoint sp = trans.defineSavepoint();
		assertNull(graph.getFirstNode());

		node2 = graph.createNode();
		PMap<Integer, String> map = JGraLab.map();
		map = map.plus(1, "Hugo").plus(100, "Volker");
		node2.set_nodeMap(map);
		node2.set_testEnum(Hugo.A);
		PVector<String> list = JGraLab.vector();
		list = list.plus("Hugo").plus("Lalala");
		node2.set_testList(list);
		PSet<Integer> set = JGraLab.set();
		set = set.plus(1).plus(3).plus(8);
		node2.set_testSet(set);
		node2.set_testString("Hugo");
		node2.set_testRecord(new BooleanType(true, true));

		assertTrue(graph.getFirstNode() != null);
		assertTrue(graph.getFirstNode() == node2);

		trans.restoreSavepoint(sp);
		assertNull(graph.getFirstNode());
		graph.commit();

		graph.newReadOnlyTransaction();
		assertEquals(0, graph.getVCount());
		assertEquals(0, graph.getECount());
		graph.commit();

		trans = graph.newTransaction();
		node1 = graph.createNode();
		node2 = graph.createNode();

		assertNull(graph.getFirstLink());
		sp = graph.defineSavepoint();
		assertNull(graph.getFirstLink());

		Link link = graph.createLink(node1, node2);

		assertTrue(graph.getFirstLink() != null);
		assertTrue(graph.getFirstLink() == link);
		trans.restoreSavepoint(sp);
		assertNull(graph.getFirstLink());
		graph.commit();

	}

}
