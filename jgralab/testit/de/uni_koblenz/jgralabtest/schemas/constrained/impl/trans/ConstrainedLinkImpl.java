/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.constrained.impl.trans;

import de.uni_koblenz.jgralab.impl.trans.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

import de.uni_koblenz.jgralabtest.schemas.constrained.impl.trans.ReversedConstrainedLinkImpl;

import de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode;

import java.io.IOException;
/**
 * FromVertexClass: ConstrainedNode
 * FromRoleName : 
 * ToVertexClass: ConstrainedNode
 * ToRoleName : 
 */

public class ConstrainedLinkImpl extends EdgeImpl implements de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink, de.uni_koblenz.jgralab.Edge {

	protected de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.Integer> _uid;

	public ConstrainedLinkImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("uid")) return (T) (java.lang.Integer) get_uid();
		throw new NoSuchAttributeException("ConstrainedLink doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("uid")) {
			set_uid((java.lang.Integer) data);
			return;
		}
		throw new NoSuchAttributeException("ConstrainedLink doesn't contain an attribute " + attributeName);
	}

	public int get_uid() {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'uid', because " + this + " isn't valid in current transaction.");
		if (_uid == null)
			return 0;
		java.lang.Integer value = _uid.getValidValue(graph.getCurrentTransaction());
		return (value == null) ? 0 : value;
	}

	public void set_uid(int _uid) {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'uid', because " + this + " isn't valid in current transaction.");
		if (graph.isLoading()) {
			this._uid = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.Integer>(this, _uid, "uid");
		}
		if (this._uid == null) {
			this._uid = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.Integer>(this, null, "uid");
		}
		this._uid.setValidValue((java.lang.Integer) _uid, graph.getCurrentTransaction());
		attributeChanged(this._uid);
		graphModified();
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		int _uid = io.matchInteger();
		set_uid(_uid);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("uid")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			java.lang.Integer tmpVar = null;
			tmpVar = io.matchInteger();
			set_uid(tmpVar);
			return;
		}
		throw new NoSuchAttributeException("ConstrainedLink doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeInteger(get_uid());
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("uid")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeInteger(get_uid());
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("ConstrainedLink doesn't contain an attribute " + attributeName);
	}
	public java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes() {
		java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes = new java.util.HashSet<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>>();
		attributes.add(_uid);
		return attributes;
	}

	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink getNextConstrainedLinkInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink)getNextEdge(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink getNextConstrainedLinkIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink getNextConstrainedLinkIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.class, orientation);
	}

	public de.uni_koblenz.jgralab.schema.AggregationKind getAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.NONE;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getAlphaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.NONE;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getOmegaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.NONE;
	}

	protected de.uni_koblenz.jgralab.impl.ReversedEdgeBaseImpl createReversedEdge() {
		return new ReversedConstrainedLinkImpl(this, graph);
	}
	public ConstrainedNode getAlpha() {
		return (ConstrainedNode) super.getAlpha();
	}
	public ConstrainedNode getOmega() {
		return (ConstrainedNode) super.getOmega();
	}
}
