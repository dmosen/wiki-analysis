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
package de.uni_koblenz.jgralab.impl.db;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.impl.EdgeBaseImpl;
import de.uni_koblenz.jgralab.impl.IncidenceImpl;
import de.uni_koblenz.jgralab.impl.InternalEdge;
import de.uni_koblenz.jgralab.impl.InternalVertex;
import de.uni_koblenz.jgralab.impl.VertexBaseImpl;

/**
 * Implements a database persistable edge.
 * 
 * @author ultbreit@uni-koblenz.de
 */
public abstract class EdgeImpl extends EdgeBaseImpl implements
		DatabasePersistableEdge, DatabasePersistableIncidence {

	/**
	 * Primary key of incident vertex.
	 */
	private int alphaVId = 0;

	/**
	 * Number mapping incidence's sequence in LambdaSeq of incident vertex
	 * (alpha).
	 */
	private long sequenceNumberInLambdaSeq;

	/**
	 * Number mapping edge's sequence in ESeq of graph.
	 */
	private long sequenceNumberInESeq;

	/**
	 * Flag indicating if an edge has been fully initialized, so it can be used
	 * without unwanted effects.
	 */
	private boolean initialized;

	/**
	 * Flag indicating whether an edge is persistent in database.
	 */
	private boolean persistent = false;

	/**
	 * Creates and initializes a new <code>EdgeImpl</code>.
	 * 
	 * @param anId
	 *            Id edge will have.
	 * @param graph
	 *            Graph created edge will belong to.
	 * @param alpha
	 *            Start vertex of edge.
	 * @param omega
	 *            End vertex of edge.
	 */
	protected EdgeImpl(int anId, Graph graph, Vertex alpha, Vertex omega) {
		super(anId, graph, alpha, omega);
		((GraphImpl) graph).addEdge(this, alpha, omega);
	}

	private GraphImpl getGraphImpl() {
		return (GraphImpl) super.graph;
	}

	@Override
	public int getGId() {
		return getGraphImpl().getGId();
	}

	@Override
	public long getSequenceNumberInLambdaSeq() {
		return sequenceNumberInLambdaSeq;
	}

	@Override
	public long getSequenceNumberInESeq() {
		return sequenceNumberInESeq;
	}

	@Override
	public int getIncidentVId() {
		return alphaVId;
	}

	@Override
	public void setIncidentVId(int incidentVId) {
		if (alphaVId != incidentVId) {
			updateIncidentVId(incidentVId);
		}
	}

	@Override
	public void setSequenceNumberInLambdaSeq(long sequenceNumber) {
		if (sequenceNumberInLambdaSeq != sequenceNumber) {
			updateSequenceNumberInLambdaSeq(sequenceNumber);
		}
	}

	@Override
	public void setSequenceNumberInESeq(long sequenceNumberInESeq) {
		if (this.sequenceNumberInESeq != sequenceNumberInESeq) {
			updateSequenceNumberInEseq(sequenceNumberInESeq);
		}
	}

	/**
	 * Updates id of incident vertex.
	 * 
	 * @param incidentVId
	 *            Id of incident vertex.
	 */
	private void updateIncidentVId(int incidentVId) {
		alphaVId = incidentVId;
		if (isPersistent() && isInitialized() && (incidentVId > 0)) {
			; // TODO check
		}
		getGraphImpl().writeIncidentVIdBack(this);
	}

	/**
	 * Updates number mapping edge's sequence LambdaSeq of incident vertex.
	 * 
	 * @param sequenceNumber
	 *            Number of vertex mapping it's sequence LambdaSeq of incident
	 *            vertex.
	 */
	private void updateSequenceNumberInLambdaSeq(long sequenceNumber) {
		sequenceNumberInLambdaSeq = sequenceNumber;
		if (isPersistent() && isInitialized()) {
			getGraphImpl().writeSequenceNumberInLambdaSeqBack(this);
		}
	}

	/**
	 * Updates number mapping edge's sequence in VSeq.
	 * 
	 * @param sequenceNumber
	 *            Number of vertex mapping it's sequence in VSeq.
	 */
	private void updateSequenceNumberInEseq(long sequenceNumberInESeq) {
		this.sequenceNumberInESeq = sequenceNumberInESeq;
		if (isPersistent() && isInitialized()) {
			getGraphImpl().writeSequenceNumberInESeqBack(this);
		}
	}

	@Override
	public boolean isPersistent() {
		return persistent;
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public void setInitialized(boolean initialized) {
		this.initialized = true;
	}

	@Override
	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	@Override
	public boolean isBeforeIncidence(Edge e) {
		assertPreCondition(e);
		if (!equals(e)) {
			return sequenceNumberInLambdaSeq < ((DatabasePersistableEdge) e)
					.getSequenceNumberInLambdaSeq();
		} else {
			return false;
		}
	}

	@Override
	public boolean isAfterIncidence(Edge e) {
		assertPreCondition(e);
		if (!equals(e)) {
			return sequenceNumberInLambdaSeq > ((DatabasePersistableEdge) e)
					.getSequenceNumberInLambdaSeq();
		} else {
			return false;
		}
	}

	@Override
	public boolean isBeforeEdge(Edge e) {
		assertGraphPreCondition(e);
		if (this != e.getNormalEdge()) {
			e = e.getNormalEdge();
			return sequenceNumberInESeq < ((DatabasePersistableEdge) e)
					.getSequenceNumberInESeq();
		} else {
			return false;
		}
	}

	@Override
	public boolean isAfterEdge(Edge e) {
		assertGraphPreCondition(e);
		if (this != e.getNormalEdge()) {
			e = e.getNormalEdge();
			return sequenceNumberInESeq > ((DatabasePersistableEdge) e)
					.getSequenceNumberInESeq();
		} else {
			return false;
		}
	}

	private void assertPreCondition(Edge e) {
		assertGraphPreCondition(e);
		assert ((VertexImpl) getThis()).equals(e.getThis());
	}

	private void assertGraphPreCondition(Edge e) {
		assert e != null;
		assert isValid();
		assert e.isValid();
		assert graph == e.getGraph();
	}

	@Override
	public InternalEdge getPrevEdgeInESeq() {
		return getGraphImpl().getPrevEdge(this);
	}

	@Override
	public InternalEdge getNextEdgeInESeq() {
		return getGraphImpl().getNextEdge(this);
	}

	@Override
	public IncidenceImpl getPrevIncidenceInISeq() {
		VertexImpl vertex = (VertexImpl) getIncidentVertex();
		return (IncidenceImpl) vertex.getPrevIncidence(this);
	}

	@Override
	public IncidenceImpl getNextIncidenceInISeq() {
		VertexImpl vertex = (VertexImpl) getIncidentVertex();
		return (IncidenceImpl) vertex.getNextIncidence(this);
	}

	@Override
	public void setPrevEdgeInGraph(Edge prevEdge) {
		prevEdge.putBeforeEdge(this);
	}

	@Override
	public void setNextEdgeInGraph(Edge nextEdge) {
		nextEdge.putAfterEdge(this);
	}

	@Override
	public void setIncidentVertex(Vertex v) {
		// does not add this edge to incidence list of vertex as it is taken
		// care of elsewhere
		setIncidentVId(v.getId());
	}

	@Override
	public void setNextIncidenceInternal(InternalEdge nextIncidence) {
		nextIncidence.putIncidenceAfter(this);
	}

	private boolean isNotTheSameEdgeAs(Edge e) {
		return !equals(e);
	}

	private boolean isPartOfSameGraphAs(Edge e) {
		return (graph == e.getGraph()) && isValid() && e.isValid();
	}

	@Override
	public void putIncidenceBefore(Edge e) {
		assert e != null;
		assert isPartOfSameGraphAs(e);
		assert getThis() == e.getThis();
		VertexImpl v = (VertexImpl) getThis();
		assert v.isValid();
		if ((this != e) && (getNextIncidenceInISeq() != e)) {
			v.putIncidenceBefore((IncidenceImpl) e, this);
			v.incidenceListModified();
		}
	}

	@Override
	public void putIncidenceAfter(Edge e) {
		assert e != null;
		assert isPartOfSameGraphAs(e);
		assert getThis() == e.getThis();
		VertexImpl v = (VertexImpl) getThis();
		assert v.isValid();
		// if (this.isNotTheSameEdgeAs(e)){
		if ((this != e)
				&& (this != ((InternalEdge) e).getNextIncidenceInISeq())) {
			// System.out.println("putEdgeAfter calls v.putIncidenceAfter");
			v.putIncidenceAfter((IncidenceImpl) e, this);
			v.incidenceListModified();
		}
	}

	@Override
	public void putAfterEdge(Edge e) {
		assert e != null;
		assert isPartOfSameGraphAs(e);
		assert isNotTheSameEdgeAs(e);
		assert e != reversedEdge;
		if ((this != e) && (this != ((InternalEdge) e).getNextEdgeInESeq())) {
			getGraphImpl().putEdgeAfterInGraph(
					(EdgeBaseImpl) e.getNormalEdge(), this);
			getGraphImpl().edgeListModified();
		}
	}

	@Override
	public void putBeforeEdge(Edge e) {
		assert e != null;
		assert isPartOfSameGraphAs(e);
		assert this != e;
		assert e != reversedEdge;
		if ((this != e) && (getNextEdgeInESeq() != e)) {
			getGraphImpl().putEdgeBeforeInGraph(
					(EdgeBaseImpl) e.getNormalEdge(), this);
			getGraphImpl().edgeListModified();
		}
	}

	@Override
	public void setPrevIncidenceInternal(InternalEdge prevIncidence) {
		prevIncidence.putIncidenceBefore(this);
	}

	/**
	 * Sets id of edge if it is different than previous one.
	 * 
	 * @param id
	 *            Id of edge.
	 */
	@Override
	public void setId(int id) {
		assert id >= 0;
		if (super.getId() != id) {
			updateId(id);
		}
	}

	/**
	 * Updates id of edge.
	 * 
	 * @param graphId
	 *            Id of edge.
	 */
	private void updateId(int newEId) {
		if (isPersistent() && isInitialized()) {
			getGraphImpl().writeBackEdgeId(this, newEId);
		}
		id = newEId;
	}

	@Override
	public InternalVertex getIncidentVertex() {
		if (alphaVId > 0) {
			return (VertexBaseImpl) graph.getVertex(alphaVId);
		} else {
			return null;
		}
	}

	/**
	 * Notifies edge that one of his attributes has changed. Called from
	 * generated edge implementation classes when an attribute is changed.
	 * 
	 * @param attributeName
	 *            Name of attribute that has been changed.
	 */
	protected void attributeChanged(String attributeName) {
		if (isPersistent() && isInitialized()) {
			getGraphImpl().updateEdgeAttributeValueInDatabase(this,
					attributeName);
			getGraphImpl().internalGraphModified();
		}
	}

	@Override
	public boolean isValid() {
		if (graph != null) {
			return graph.eSeqContainsEdge(this);
		} else {
			return false;
		}
	}

	@Override
	public void deleted() {
		persistent = false;
		initialized = false;
		id = 0;
		graph = null;
	}

	@Override
	public int getIncidentEId() {
		return getId();
	}
}
