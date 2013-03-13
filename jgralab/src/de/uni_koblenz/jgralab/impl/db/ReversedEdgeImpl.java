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

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.impl.InternalEdge;
import de.uni_koblenz.jgralab.impl.IncidenceImpl;
import de.uni_koblenz.jgralab.impl.ReversedEdgeBaseImpl;
import de.uni_koblenz.jgralab.impl.VertexBaseImpl;

/**
 * Implements a database persistable reversed edge.
 * 
 * @author ultbreit@uni-koblenz.de
 */
public abstract class ReversedEdgeImpl extends ReversedEdgeBaseImpl implements
		DatabasePersistableEdge, DatabasePersistableIncidence {

	/**
	 * Number mapping incidence's sequence in LambdaSeq of incident vertex.
	 */
	private long sequenceNumberInLambdaSeq;

	/**
	 * Primary key of incident vertex in database.
	 */
	private int omegaVId;

	/**
	 * Normal edge.
	 */
	protected DatabasePersistableEdge normalEdge;

	/**
	 * Creates a new <code>ReversedEdge</code>.
	 * 
	 * @param normalEdge
	 *            Normal edge.
	 * @param graph
	 *            Graph edge is part of.
	 */
	public ReversedEdgeImpl(EdgeImpl normalEdge, Graph graph) {
		super(normalEdge, graph);
		this.normalEdge = normalEdge;
	}

	@Override
	public VertexBaseImpl getIncidentVertex() {
		if (omegaVId > 0) {
			return (VertexBaseImpl) graph.getVertex(omegaVId);
		} else {
			return null;
		}
	}

	private GraphImpl getGraphImpl() {
		return (GraphImpl) super.graph;
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
	public void setIncidentVertex(Vertex v) {
		setIncidentVId(v.getId());
	}

	@Override
	public void setNextIncidenceInternal(InternalEdge nextIncidence) {
		nextIncidence.putIncidenceAfter(this);
	}

	@Override
	public void setPrevIncidenceInternal(InternalEdge prevIncidence) {
		prevIncidence.putIncidenceBefore(this);
	}

	@Override
	public boolean isValid() {
		return normalEdge.isValid();
	}

	@Override
	public int getIncidentVId() {
		return omegaVId;
	}

	@Override
	public long getSequenceNumberInLambdaSeq() {
		return sequenceNumberInLambdaSeq;
	}

	@Override
	public int getGId() {
		return normalEdge.getGId();
	}

	@Override
	public long getSequenceNumberInESeq() {
		return normalEdge.getSequenceNumberInESeq();
	}

	@Override
	public void setIncidentVId(int incidentVId) {
		if (omegaVId != incidentVId) {
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
		normalEdge.setSequenceNumberInESeq(sequenceNumberInESeq);
	}

	/**
	 * Updates number mapping incidences's sequence in LambdaSeq of incident
	 * vertex.
	 * 
	 * @param sequenceNumber
	 */
	private void updateSequenceNumberInLambdaSeq(long sequenceNumber) {
		sequenceNumberInLambdaSeq = sequenceNumber;
		if (isPersistent() && isInitialized()) {
			getGraphImpl().writeSequenceNumberInLambdaSeqBack(this);
		}
	}

	/**
	 * Updates primary key of incident vertex.
	 * 
	 * @param vId
	 *            Id of incident vertex.
	 */
	private void updateIncidentVId(int vId) {
		omegaVId = vId;
		// this.getGraph();
		if (isPersistent() && isInitialized() && vId > 0) {
			;
		}
		getGraphImpl().writeIncidentVIdBack(this);
	}

	@Override
	public boolean isPersistent() {
		return normalEdge.isPersistent();
	}

	@Override
	public boolean isInitialized() {
		return normalEdge.isInitialized();
	}

	@Override
	public void setInitialized(boolean initialized) {
		normalEdge.setInitialized(initialized);
	}

	@Override
	public void setPersistent(boolean persistent) {
		normalEdge.setPersistent(persistent);
	}

	@Override
	public void deleted() {
		normalEdge.deleted();
	}

	@Override
	public int getIncidentEId() {
		return getId();
	}
}
