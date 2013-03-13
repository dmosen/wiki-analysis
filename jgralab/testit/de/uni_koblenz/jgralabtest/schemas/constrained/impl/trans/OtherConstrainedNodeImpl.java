/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.constrained.impl.trans;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.trans.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class OtherConstrainedNodeImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode, de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode {

	protected de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.String> _name;

	protected de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.Integer> _niceness;

	protected de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.Integer> _uid;

	public OtherConstrainedNodeImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("name")) return (T) get_name();
		if (attributeName.equals("niceness")) return (T) (java.lang.Integer) get_niceness();
		if (attributeName.equals("uid")) return (T) (java.lang.Integer) get_uid();
		throw new NoSuchAttributeException("OtherConstrainedNode doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		if (attributeName.equals("niceness")) {
			set_niceness((java.lang.Integer) data);
			return;
		}
		if (attributeName.equals("uid")) {
			set_uid((java.lang.Integer) data);
			return;
		}
		throw new NoSuchAttributeException("OtherConstrainedNode doesn't contain an attribute " + attributeName);
	}

	public java.lang.String get_name() {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'name', because " + this + " isn't valid in current transaction.");
		if (_name == null)
			return null;
		java.lang.String value = _name.getValidValue(graph.getCurrentTransaction());
		return (value == null) ? null : value;
	}

	public void set_name(java.lang.String _name) {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'name', because " + this + " isn't valid in current transaction.");
		if (graph.isLoading()) {
			this._name = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.String>(this, _name, "name");
		}
		if (this._name == null) {
			this._name = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.String>(this, null, "name");
		}
		this._name.setValidValue((java.lang.String) _name, graph.getCurrentTransaction());
		attributeChanged(this._name);
		graphModified();
	}

	public int get_niceness() {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'niceness', because " + this + " isn't valid in current transaction.");
		if (_niceness == null)
			return 0;
		java.lang.Integer value = _niceness.getValidValue(graph.getCurrentTransaction());
		return (value == null) ? 0 : value;
	}

	public void set_niceness(int _niceness) {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'niceness', because " + this + " isn't valid in current transaction.");
		if (graph.isLoading()) {
			this._niceness = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.Integer>(this, _niceness, "niceness");
		}
		if (this._niceness == null) {
			this._niceness = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.Integer>(this, null, "niceness");
		}
		this._niceness.setValidValue((java.lang.Integer) _niceness, graph.getCurrentTransaction());
		attributeChanged(this._niceness);
		graphModified();
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
		java.lang.String _name = io.matchUtfString();
		set_name(_name);
		int _niceness = io.matchInteger();
		set_niceness(_niceness);
		int _uid = io.matchInteger();
		set_uid(_uid);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			java.lang.String tmpVar = null;
			tmpVar = io.matchUtfString();
			set_name(tmpVar);
			return;
		}
		if (attributeName.equals("niceness")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			java.lang.Integer tmpVar = null;
			tmpVar = io.matchInteger();
			set_niceness(tmpVar);
			return;
		}
		if (attributeName.equals("uid")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			java.lang.Integer tmpVar = null;
			tmpVar = io.matchInteger();
			set_uid(tmpVar);
			return;
		}
		throw new NoSuchAttributeException("OtherConstrainedNode doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(get_name());
		io.writeInteger(get_niceness());
		io.writeInteger(get_uid());
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(get_name());
			return io.getStringWriterResult();
		}
		if (attributeName.equals("niceness")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeInteger(get_niceness());
			return io.getStringWriterResult();
		}
		if (attributeName.equals("uid")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeInteger(get_uid());
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("OtherConstrainedNode doesn't contain an attribute " + attributeName);
	}
	public java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes() {
		java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes = new java.util.HashSet<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>>();
		attributes.add(_name);
		attributes.add(_niceness);
		attributes.add(_uid);
		return attributes;
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode getNextConstrainedNode() {
		return (de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode)getNextVertex(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode getNextOtherConstrainedNode() {
		return (de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode)getNextVertex(de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink getFirstConstrainedLinkIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink getFirstConstrainedLinkIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.class, orientation);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink> getConstrainedLinkIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink>(this, de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink> getConstrainedLinkIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink>(this, de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.class, direction);
	}
}
