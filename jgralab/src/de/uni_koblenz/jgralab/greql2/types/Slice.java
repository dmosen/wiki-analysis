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

package de.uni_koblenz.jgralab.greql2.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import org.pcollections.PSet;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphElement;
import de.uni_koblenz.jgralab.JGraLab;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.greql2.funlib.FunLib;

public class Slice {

	/**
	 * This HashMap stores references from a tuple (Vertex,State) to a list of
	 * tuples(ParentVertex, ParentEdge, ParentState, DistanceToRoot)
	 */
	private HashMap<PathSystemKey, List<PathSystemEntry>> keyToEntryMap;

	/**
	 * This HashMap stores references from a vertex to the first occurence of
	 * this vertex in the above HashMap<PathSystemKey, PathSystemEntry>
	 * keyToEntryMap
	 */
	private HashMap<Vertex, PathSystemKey> vertexToFirstKeyMap;

	/**
	 * This is the rootvertex of the slice
	 */
	private PSet<Vertex> sliCritVertices;

	/**
	 * this set stores the keys of the leaves of this slice. It is created the
	 * first time it is needed. So the creation (which is in O(n²) ) has to be
	 * done only once.
	 */
	private ArrayList<PathSystemKey> leafKeys = null;

	/**
	 * returns the slicing criterion vertices of this slice
	 */
	public PSet<Vertex> getSlicingCriterionVertices() {
		return sliCritVertices;
	}

	/**
	 * This is a reference to the datagraph this slice is part of
	 */
	private Graph datagraph;

	/**
	 * returns the datagraph this slice is part of
	 */
	public Graph getDataGraph() {
		return datagraph;
	}

	/**
	 * returns the hashcode of this slice
	 */
	@Override
	public int hashCode() {
		return keyToEntryMap.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Slice)) {
			return false;
		}
		return keyToEntryMap.equals(((Slice) o).keyToEntryMap);
	}

	/**
	 * creates a new JValueSlice with the given rootVertex in the given
	 * datagraph
	 */
	public Slice(Graph graph) {
		datagraph = graph;
		keyToEntryMap = new HashMap<PathSystemKey, List<PathSystemEntry>>();
		vertexToFirstKeyMap = new HashMap<Vertex, PathSystemKey>();
		sliCritVertices = JGraLab.set();

	}

	private Queue<PathSystemEntry> entriesWithoutParentEdge = new LinkedList<PathSystemEntry>();

	boolean isCleared = true;

	public void clearPathSystem() {
		if (!isCleared) {
			while (!entriesWithoutParentEdge.isEmpty()) {
				PathSystemEntry te = entriesWithoutParentEdge.poll();
				Vertex p = te.getParentVertex();
				if (p == null) {
					// root vertex
				} else {
					List<PathSystemEntry> pel = keyToEntryMap
							.get(new PathSystemKey(p, te.getParentStateNumber()));
					PathSystemEntry pe = pel.get(0);
					te.setParentEdge(pe.getParentEdge());
					te.setDistanceToRoot(pe.getDistanceToRoot());
					te.setParentStateNumber(pe.getParentStateNumber());
					te.setParentVertex(pe.getParentVertex());
					if (te.getParentEdge() == null) {
						entriesWithoutParentEdge.add(te);
					}
				}
			}
			isCleared = true;
		}
	}

	/**
	 * adds a vertex of the slice which is described by the parameters to the
	 * slicing criterion
	 * 
	 * @param vertex
	 *            the vertex to add
	 * @param stateNumber
	 *            the number of the DFAState the DFA was in when this vertex was
	 *            visited
	 * @param finalState
	 *            true if the vertex is visited by the dfa in a final state
	 */
	public void addSlicingCriterionVertex(Vertex vertex, int stateNumber,
			boolean finalState) {
		// System.out.println("Adding vertex " + vertex +
		// " as slicing criterion");
		PathSystemKey key = new PathSystemKey(vertex, stateNumber);
		PathSystemEntry entry = new PathSystemEntry(null, null, -1, 0,
				finalState);
		List<PathSystemEntry> entryList = new ArrayList<PathSystemEntry>();
		entryList.add(entry);
		keyToEntryMap.put(key, entryList);
		if (!vertexToFirstKeyMap.containsKey(vertex)) {
			vertexToFirstKeyMap.put(vertex, key);
		}
		leafKeys = null;
		sliCritVertices = sliCritVertices.plus(vertex);
	}

	/**
	 * adds a vertex of the slice which is described by the parameters to the
	 * slice
	 * 
	 * @param vertex
	 *            the vertex to add
	 * @param stateNumber
	 *            the number of the DFAState the DFA was in when this vertex was
	 *            visited
	 * @param parentEdge
	 *            the edge which leads from vertex to parentVertex
	 * @param parentVertex
	 *            the parentVertex of the vertex in the slice
	 * @param parentStateNumber
	 *            the number of the DFAState the DFA was in when the
	 *            parentVertex was visited
	 */
	public void addVertex(Vertex vertex, int stateNumber, Edge parentEdge,
			Vertex parentVertex, int parentStateNumber, boolean finalState) {
		PathSystemKey key = new PathSystemKey(vertex, stateNumber);
		List<PathSystemEntry> entryList = keyToEntryMap.get(key);
		if (entryList == null) {
			entryList = new ArrayList<PathSystemEntry>();
			keyToEntryMap.put(key, entryList);
			if (!vertexToFirstKeyMap.containsKey(vertex)) {
				vertexToFirstKeyMap.put(vertex, key);
			}
			leafKeys = null;
		}
		PathSystemEntry entry = new PathSystemEntry(parentVertex, parentEdge,
				parentStateNumber, 0, finalState);
		if (!entryList.contains(entry)) {
			entryList.add(entry);
		}
		if (parentEdge == null) {
			entriesWithoutParentEdge.add(entry);
			isCleared = false;
		}
	}

	/**
	 * Calculates the parent vertices of the given vertex in this slice. If the
	 * given vertex exists more than one times in this slice, the first
	 * occurrence is used. If the given vertex is not part of this slice, an
	 * invalid JValue will be returned
	 */
	public PSet<Vertex> parents(Vertex vertex) {
		clearPathSystem();
		PathSystemKey key = vertexToFirstKeyMap.get(vertex);
		return parents(key);
	}

	/**
	 * Calculates the parent vertices of the given key in this slice.
	 */
	public PSet<Vertex> parents(PathSystemKey key) {
		clearPathSystem();
		PSet<Vertex> resultSet = JGraLab.set();

		for (PathSystemEntry entry : keyToEntryMap.get(key)) {
			resultSet = resultSet.plus(entry.getParentVertex());
		}

		return resultSet;
	}

	/**
	 * Calculates the set of edges nodes in this slice.
	 */
	public PSet<Edge> getEdges() {
		clearPathSystem();
		PSet<Edge> resultSet = JGraLab.set();
		for (Map.Entry<PathSystemKey, List<PathSystemEntry>> mapEntry : keyToEntryMap
				.entrySet()) {
			for (PathSystemEntry thisEntry : mapEntry.getValue()) {
				if (thisEntry.getParentEdge() != null) {
					resultSet = resultSet.plus(thisEntry.getParentEdge());
				}
			}
		}
		return resultSet;
	}

	public boolean contains(GraphElement<?, ?> elem) {
		for (Entry<PathSystemKey, List<PathSystemEntry>> e : keyToEntryMap
				.entrySet()) {

			if (e.getKey().getVertex() == elem) {
				return true;
			}
			if (!(elem instanceof Edge)) {
				continue;
			}
			for (PathSystemEntry pse : e.getValue()) {
				if (pse.getParentEdge() == elem) {
					return true;
				}
				// TODO: Don't we need to check parentVertex, too?? Or is that
				// the key?
			}
		}
		return false;
	}

	/**
	 * Calculates the set of nodes which are part of this slice.
	 */
	public PSet<Vertex> getVertices() {
		clearPathSystem();
		PSet<Vertex> resultSet = JGraLab.set();
		for (PathSystemKey mapKey : keyToEntryMap.keySet()) {
			resultSet = resultSet.plus(mapKey.getVertex());
		}

		return resultSet;
	}

	/**
	 * Calculates the set of leaves in this slice. Costs: O(n²) where n is the
	 * number of vertices in the slice. The created set is stored as private
	 * field <code>leaves</code>, so the creation has to be done only once.
	 */
	public PSet<Vertex> getLeaves() {
		clearPathSystem();
		PSet<Vertex> leaves = JGraLab.set();
		if (leafKeys == null) {
			createLeafKeys();
		}
		// create the set of leaves out of the key set
		for (PathSystemKey key : leafKeys) {
			leaves = leaves.plus(key.getVertex());
		}
		return leaves;
	}

	/**
	 * create the set of leaf keys if it is not already created
	 */
	private void createLeafKeys() {
		clearPathSystem();
		if (leafKeys != null) {
			return;
		}
		leafKeys = new ArrayList<PathSystemKey>();
		for (Map.Entry<PathSystemKey, List<PathSystemEntry>> mapEntry : keyToEntryMap
				.entrySet()) {
			boolean isFinal = false;
			for (PathSystemEntry entry : mapEntry.getValue()) {
				if (entry.getStateIsFinal()) {
					isFinal = true;
				}
			}
			if (isFinal) {
				leafKeys.add(mapEntry.getKey());
			}
		}
	}

	/**
	 * calculate the number of vertices this slice has. If a vertex is part of
	 * this slice n times, it is counted n times
	 */
	public int weight() {
		clearPathSystem();
		return keyToEntryMap.size();
	}

	/**
	 * @return true if the given first vertex is a neighbour of the given second
	 *         vertex, that means, if there is a edge from v1 to v2. If one or
	 *         both of the given vertices are part of the slice more than once,
	 *         the first occurence is used. If one of the vertices is not part
	 *         of this slice, false is returned
	 */
	public boolean isNeighbour(Vertex v1, Vertex v2) {
		clearPathSystem();
		PathSystemKey key1 = vertexToFirstKeyMap.get(v1);
		PathSystemKey key2 = vertexToFirstKeyMap.get(v2);
		return isNeighbour(key1, key2);
	}

	/**
	 * @return true if the given first key is a neighbour of the given second
	 *         key, that means, if there is a edge from key1.vertex to
	 *         key2.vertex and the states matches. If one of the keys is not
	 *         part of this slice, false is returned
	 */
	public boolean isNeighbour(PathSystemKey key1, PathSystemKey key2) {
		clearPathSystem();
		if ((key1 == null) || (key2 == null)) {
			return false;
		}
		for (PathSystemEntry entry1 : keyToEntryMap.get(key1)) {
			for (PathSystemEntry entry2 : keyToEntryMap.get(key2)) {
				if ((entry1.getParentVertex() == key2.getVertex())
						&& (entry1.getParentStateNumber() == key2
								.getStateNumber())) {
					return true;
				}
				if ((entry2.getParentVertex() == key1.getVertex())
						&& (entry2.getParentStateNumber() == key1
								.getStateNumber())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Prints the <key, List<entry>> map as single <key, entry> entries, i.e. a
	 * key may occur multiple times.
	 */
	public void printEntryMap() {
		clearPathSystem();
		if (FunLib.getLogger() == null) {
			return;
		}
		FunLib.getLogger().info("<Key, Entry> set of slice is:");
		for (Map.Entry<PathSystemKey, List<PathSystemEntry>> mapEntry : keyToEntryMap
				.entrySet()) {
			for (PathSystemEntry entry : mapEntry.getValue()) {
				FunLib.getLogger().info(
						mapEntry.getKey().toString() + " maps to "
								+ entry.toString());
			}
		}
	}

	/**
	 * Prints the <vertex, key map>.
	 */
	public void printKeyMap() {
		clearPathSystem();
		if (FunLib.getLogger() == null) {
			return;
		}
		Iterator<Map.Entry<Vertex, PathSystemKey>> iter = vertexToFirstKeyMap
				.entrySet().iterator();
		FunLib.getLogger().info("<Vertex, FirstKey> set of slice is:");
		while (iter.hasNext()) {
			Map.Entry<Vertex, PathSystemKey> mapEntry = iter.next();
			PathSystemKey thisKey = mapEntry.getValue();
			Vertex vertex = mapEntry.getKey();
			FunLib.getLogger().info(vertex + " maps to " + thisKey.toString());
		}
	}

	/**
	 * returns a string representation of this slice
	 */
	@Override
	public String toString() {
		clearPathSystem();
		Set<Vertex> vset = new HashSet<Vertex>();
		vset.addAll(vertexToFirstKeyMap.keySet());
		Set<Edge> eset = new HashSet<Edge>();
		for (List<PathSystemEntry> pl : keyToEntryMap.values()) {
			for (PathSystemEntry pe : pl) {
				eset.add(pe.getParentEdge());
			}
		}

		StringBuffer returnString = new StringBuffer("Slice: ");
		returnString.append("Vertices: ");
		boolean first = true;
		for (Vertex v : vset) {
			if (first) {
				first = false;
				returnString.append(v);
			} else {
				returnString.append(", ");
				returnString.append(v);
			}
		}
		returnString.append(", Edges: ");
		first = true;
		for (Edge e : eset) {
			if (first) {
				first = false;
				returnString.append(e);
			} else {
				returnString.append(", ");
				returnString.append(e);
			}
		}
		return returnString.toString();
	}
}
