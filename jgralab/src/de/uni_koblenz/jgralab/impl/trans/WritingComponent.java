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
package de.uni_koblenz.jgralab.impl.trans;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import de.uni_koblenz.jgralab.AttributedElement;
import de.uni_koblenz.jgralab.GraphException;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.impl.IncidenceImpl;
import de.uni_koblenz.jgralab.trans.ListPosition;
import de.uni_koblenz.jgralab.trans.TransactionState;
import de.uni_koblenz.jgralab.trans.VersionedDataObject;
import de.uni_koblenz.jgralab.trans.VertexPosition;

/**
 * Executes writing phase for a transaction. At most one transaction should be
 * executing write() at the same time. No other transaction should be doing BOT
 * or validation.
 * 
 * @author Jose Monte(monte@uni-koblenz.de)
 */
public class WritingComponent {
	private TransactionImpl transaction;
	private GraphImpl graph;

	/**
	 * @param transaction
	 */
	protected WritingComponent(TransactionImpl transaction) {
		assert (transaction != null);
		this.transaction = transaction;
		graph = (GraphImpl) transaction.getGraph();
	}

	/**
	 * Commented out the "garbage collection", if exception occurs during
	 * writing-process. Exception should not happen anyway. Exception handling
	 * with "garbage collection" would include changes to
	 * VersionedDataObjectImpl with higher memory usage (use of persistentValue
	 * wouldn't be possible).
	 * 
	 * @throws Exception
	 */
	protected void write() throws Exception {
		assert (transaction.getState() == TransactionState.WRITING);
		// try {
		// if at least one vertex is to be added or deleted...
		if (((transaction.addedVertices != null && transaction.addedVertices
				.size() > 0) || (transaction.deletedVertices != null && transaction.deletedVertices
				.size() > 0))
				&& graph.vertex.isLatestPersistentValueReferenced()) {
			// important to synchronize here!!!
			graph.vertexSync.readLock().lock();
			// create new persistent value for vertex...
			VertexImpl[] lastVertex = graph.vertex.getLatestPersistentValue();
			graph.vertex.setValidValue(lastVertex.clone(), transaction);
			graph.vertexSync.readLock().unlock();
		}
		// if at least one edge is to be added or deleted...
		if (((transaction.addedEdges != null && transaction.addedEdges.size() > 0) || (transaction.deletedEdges != null && transaction.deletedEdges
				.size() > 0))
		// looking for graph.edge is sufficient, because edge and
		// revEdge are one unit..
				&& graph.edge.isLatestPersistentValueReferenced()) {
			// important to synchronize here!!!
			graph.edgeSync.readLock().lock();
			// create new persistent value for edge and revEdge...
			EdgeImpl[] lastEdge = graph.edge.getLatestPersistentValue();
			graph.edge.setValidValue(lastEdge.clone(), transaction);
			ReversedEdgeImpl[] lastRevEdge = graph.revEdge
					.getLatestPersistentValue();
			graph.revEdge.setValidValue(lastRevEdge.clone(), transaction);
			graph.edgeSync.readLock().unlock();
		}
		deleteVertices();
		deleteEdges();
		addVertices();
		addEdges();
		changeVseq();
		changeEseq();
		changeEdges();
		changeIseq();
		changeAttributes();
		transaction.removeNonReferencedPersistentValues();
	}

	/**
	 * Delete vertices.
	 * 
	 * @throws Exception
	 */
	private void deleteVertices() throws Exception {
		// graph.vertexSync.writeLock().lock();
		// delete vertices
		if (transaction.deletedVertices != null) {
			for (VertexImpl vertex : transaction.deletedVertices) {
				if (transaction.deletedVerticesWhileWriting == null
						|| !transaction.deletedVerticesWhileWriting
								.contains(vertex)) {
					assert (transaction.addedVertices == null || !transaction.addedVertices
							.contains(vertex));
					// delete current vertex
					vertex.delete();
				}
			}
			transaction.deletedVerticesWhileWriting = null;
			if (transaction.deletedVertices.size() > 0) {
				transaction.changedDuringCommit.add(graph.vertex);
			}
		}
		// graph.vertexSync.writeLock().unlock();
	}

	/**
	 * Delete edges.
	 * 
	 * @throws Exception
	 */
	private void deleteEdges() throws Exception {
		// graph.edgeSync.writeLock().lock();
		// delete edges
		if (transaction.deletedEdges != null) {
			for (EdgeImpl edge : transaction.deletedEdges) {
				assert (edge.isNormal() && (transaction.addedEdges == null || !transaction.addedEdges
						.contains(edge)));
				// only delete if still present in graph; could be that
				// <code>edge</code> has already been deleted within
				// deleteVertices()
				if (edge.isValid()) {
					edge.delete();
				}
			}
			if (transaction.deletedEdges.size() > 0) {
				transaction.changedDuringCommit.add(graph.edge);
			}
		}
		// graph.edgeSync.writeLock().unlock();
	}

	/**
	 * Add new vertices.
	 * 
	 * @throws Exception
	 */
	private void addVertices() throws Exception {
		// graph.vertexSync.writeLock().lock();
		// add vertices
		if (transaction.addedVertices != null) {
			for (VertexImpl vertex : transaction.addedVertices) {
				assert (transaction.deletedVertices == null || !transaction.deletedVertices
						.contains(vertex));
				// add current vertex to graph
				graph.addVertex(vertex);
			}
			if (transaction.addedVertices.size() > 0) {
				transaction.changedDuringCommit.add(graph.vertex);
			}
		}
		// graph.vertexSync.writeLock().unlock();
	}

	/**
	 * Add new edges.
	 * 
	 * @throws Exception
	 */
	private void addEdges() throws Exception {
		// graph.edgeSync.writeLock().lock();
		// add edges
		if (transaction.addedEdges != null) {
			for (EdgeImpl edge : transaction.addedEdges) {
				assert (edge.isNormal() && (transaction.deletedEdges == null || !transaction.deletedEdges
						.contains(edge)));
				// alpha and omega should be the last temporary value within
				// the current transaction
				Vertex tempAlpha = edge.incidentVertex
						.getTemporaryValue(transaction);
				Vertex tempOmega = ((ReversedEdgeImpl) edge.getReversedEdge()).incidentVertex
						.getTemporaryValue(transaction);
				graph.addEdge(edge, tempAlpha, tempOmega);
			}
			if (transaction.addedEdges.size() > 0) {
				transaction.changedDuringCommit.add(graph.edge);
			}
		}
		// graph.edgeSync.writeLock().unlock();
	}

	/**
	 * Commit changes made to Vseq.
	 * 
	 * @throws Exception
	 */
	private void changeVseq() throws Exception {
		// changes for Vseq
		if (transaction.changedVseqVertices != null) {
			Set<Entry<VertexImpl, Map<ListPosition, Boolean>>> vertices = transaction.changedVseqVertices
					.entrySet();
			if (vertices != null) {
				for (Entry<VertexImpl, Map<ListPosition, Boolean>> entry : vertices) {
					VertexImpl vertex = entry.getKey();
					// get positions changed for current vertex
					Map<ListPosition, Boolean> positionsMap = entry.getValue();
					Set<Entry<ListPosition, Boolean>> positions = positionsMap
							.entrySet();
					for (Entry<ListPosition, Boolean> listPositionEntry : positions) {
						// has the vertex been moved?
						boolean movedVertex = listPositionEntry.getValue();
						switch (listPositionEntry.getKey()) {
						case PREV: {
							// temporary previous vertex of vertex in Vseq for
							// current transaction
							VertexImpl tempPrevVertex = vertex.prevVertex
									.getTemporaryValue(transaction);
							if (movedVertex) {
								vertex.putAfter(tempPrevVertex);
							} else {
								tempPrevVertex.putBefore(vertex);
							}
							break;
						}
						case NEXT: {
							// temporary next vertex of vertex in Vseq for
							// current transaction
							VertexImpl tempNextVertex = vertex.nextVertex
									.getTemporaryValue(transaction);
							if (movedVertex) {
								vertex.putBefore(tempNextVertex);
							} else {
								tempNextVertex.putAfter(vertex);
							}
							break;
						}
						default: {
							throw new GraphException(
									"Literal of ListPosition expected. This should not happen!");
						}
						}

					}
				}
			}
		}
	}

	/**
	 * Commit changes made to Eseq.
	 * 
	 * @throws Exception
	 */
	private void changeEseq() throws Exception {
		// changes for Eseq
		if (transaction.changedEseqEdges != null) {
			Set<Entry<EdgeImpl, Map<ListPosition, Boolean>>> edges = transaction.changedEseqEdges
					.entrySet();
			for (Entry<EdgeImpl, Map<ListPosition, Boolean>> entry : edges) {
				EdgeImpl edge = entry.getKey();
				// get positions changed for current edge
				Map<ListPosition, Boolean> positionsMap = entry.getValue();
				Set<Entry<ListPosition, Boolean>> positions = positionsMap
						.entrySet();
				for (Entry<ListPosition, Boolean> listPositionEntry : positions) {
					// has the edge been moved?
					boolean movedEdge = listPositionEntry.getValue();
					switch (listPositionEntry.getKey()) {
					case PREV: {
						// temporary previous edge of edge in Eseq for current
						// transaction
						EdgeImpl tempPrevEdge = edge.prevEdge
								.getTemporaryValue(transaction);
						if (movedEdge) {
							edge.putAfterEdge(tempPrevEdge);
						} else {
							tempPrevEdge.putBeforeEdge(edge);
						}
						break;
					}
					case NEXT: {
						// temporary previous edge of edge in Eseq for current
						// transaction
						EdgeImpl tempNextEdge = edge.nextEdge
								.getTemporaryValue(transaction);
						if (movedEdge) {
							edge.putBeforeEdge(tempNextEdge);
						} else {
							tempNextEdge.putAfterEdge(edge);
						}
						break;
					}
					default: {
						throw new GraphException(
								"Literal of ListPosition expected. This should not happen!");
					}
					}
				}
			}
		}
	}

	/**
	 * Commit alpha- and omega-changes made to edges.
	 * 
	 * @throws Exception
	 */
	private void changeEdges() throws Exception {
		// changes for Eseq
		if (transaction.changedEdges != null) {
			Set<Entry<EdgeImpl, VertexPosition>> edges = transaction.changedEdges
					.entrySet();
			for (Entry<EdgeImpl, VertexPosition> entry : edges) {
				EdgeImpl edge = entry.getKey();
				assert (edge.isNormal());
				boolean pass = false;
				switch (entry.getValue()) {
				case ALPHAOMEGA: {
					// intentional fall-through; denounced by FindBugs
					pass = true;
				}
				case ALPHA: {
					// temporary alpha of edge for current transaction
					Vertex tempAlpha = edge.incidentVertex
							.getTemporaryValue(transaction);
					edge.setAlpha(tempAlpha);
					if (!pass) {
						break;
					}
				}
				case OMEGA: {
					// temporary omega of edge for current transaction
					Vertex tempOmega = ((ReversedEdgeImpl) edge
							.getReversedEdge()).incidentVertex
							.getTemporaryValue(transaction);
					edge.setOmega(tempOmega);
					break;
				}
				default: {
					throw new GraphException(
							"Literal of VertexPosition expected. This should not happen!");
				}
				}
			}
		}
	}

	/**
	 * Commit changes made to Iseq.
	 * 
	 * @throws Exception
	 */
	private void changeIseq() throws Exception {
		// changes for Iseq
		if (transaction.changedIncidences != null) {
			Set<Entry<VertexImpl, Map<IncidenceImpl, Map<ListPosition, Boolean>>>> vertices = transaction.changedIncidences
					.entrySet();
			for (Entry<VertexImpl, Map<IncidenceImpl, Map<ListPosition, Boolean>>> entry : vertices) {
				// VertexImpl vertex = entry.getKey();
				Map<IncidenceImpl, Map<ListPosition, Boolean>> incidences = entry
						.getValue();
				for (Entry<IncidenceImpl, Map<ListPosition, Boolean>> incidenceEntry : incidences
						.entrySet()) {
					IncidenceImpl incidence = incidenceEntry.getKey();
					// need references to versioned previous and next
					// incidence...
					VersionedReferenceImpl<IncidenceImpl> prevIncidence = null;
					VersionedReferenceImpl<IncidenceImpl> nextIncidence = null;
					// but concrete type of incidence (EdgeImpl or
					// ReversedEdgeImpl) must be determined first
					if (incidence instanceof EdgeImpl) {
						EdgeImpl edge = (EdgeImpl) incidence;
						prevIncidence = edge.prevIncidence;
						nextIncidence = edge.nextIncidence;
					}
					if (incidence instanceof ReversedEdgeImpl) {
						ReversedEdgeImpl revEdge = (ReversedEdgeImpl) incidence;
						prevIncidence = revEdge.prevIncidence;
						nextIncidence = revEdge.nextIncidence;
					}
					// get positions changed for current incidence
					Map<ListPosition, Boolean> positionsMap = incidenceEntry
							.getValue();
					// boolean pass = false;
					Set<Entry<ListPosition, Boolean>> positions = positionsMap
							.entrySet();
					for (Entry<ListPosition, Boolean> listPositionEntry : positions) {
						// has the incidence been moved?
						boolean movedIncidence = listPositionEntry.getValue();
						switch (listPositionEntry.getKey()) {
						case PREV: {
							IncidenceImpl tempPrevIncidence = prevIncidence
									.getTemporaryValue(transaction);
							if (movedIncidence) {
								incidence.putIncidenceAfter(tempPrevIncidence);
							} else {
								tempPrevIncidence.putIncidenceBefore(incidence);
							}
							break;
						}
						case NEXT: {
							IncidenceImpl tempNextIncidence = nextIncidence
									.getTemporaryValue(transaction);
							if (movedIncidence) {
								incidence.putIncidenceBefore(tempNextIncidence);
							} else {
								tempNextIncidence.putIncidenceAfter(incidence);
							}
							break;
						}
						default: {
							throw new GraphException(
									"Literal of ListPosition expected. This should not happen!");
						}
						}
					}
				}
			}
		}
	}

	/**
	 * Commit changes made to attributes.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	private void changeAttributes() throws Exception {
		if (transaction.changedAttributes != null) {
			Set<Entry<AttributedElement<?, ?>, Set<VersionedDataObject<?>>>> elements = transaction.changedAttributes
					.entrySet();
			for (Entry<AttributedElement<?, ?>, Set<VersionedDataObject<?>>> entry : elements) {
				// TODO how can the unchecked usage of VersionedDataObject can
				// be avoided (using <?> doesn't work)
				Set<VersionedDataObject<?>> attributes = entry.getValue();
				for (VersionedDataObject<?> attribute : attributes) {
					// the temporary value of attribute for current transaction
					Object tempValue = attribute.getTemporaryValue(transaction);
					((VersionedDataObjectImpl<Object>) attribute)
							.setValidValue(tempValue,
									graph.getCurrentTransaction(), true);
					graph.setGraphVersion(graph.getGraphVersion() + 1);
				}
			}
		}
		// also write persistent values for remaining versioned dataobjects
		// should be all "somehow" be part of an attribute of a GraphElement
		Set<VersionedDataObject<?>> versionedDataObjects = transaction
				.getRemainingVersionedDataObjects();
		for (VersionedDataObject<?> vdo : versionedDataObjects) {
			if (((transaction.changedDuringCommit == null || (transaction.changedDuringCommit != null && !transaction.changedDuringCommit
					.contains(vdo))) && vdo.isCloneable())) {
				Object tempValue = vdo.getTemporaryValue(transaction);
				((VersionedDataObjectImpl<Object>) vdo).setValidValue(
						tempValue, graph.getCurrentTransaction(), true);
				graph.setGraphVersion(graph.getGraphVersion() + 1);
			}
		}
	}
}
