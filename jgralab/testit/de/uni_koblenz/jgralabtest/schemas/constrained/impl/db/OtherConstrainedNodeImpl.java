/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.constrained.impl.db;

import de.uni_koblenz.jgralab.impl.db.VertexImpl;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class OtherConstrainedNodeImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode, de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode {

	protected java.lang.String _name;

	protected int _niceness;

	protected int _uid;

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
		return _name;
	}

	public void set_name(java.lang.String _name) {
		this._name = _name;
		attributeChanged("name");
	}

	public int get_niceness() {
		return _niceness;
	}

	public void set_niceness(int _niceness) {
		this._niceness = _niceness;
		attributeChanged("niceness");
	}

	public int get_uid() {
		return _uid;
	}

	public void set_uid(int _uid) {
		this._uid = _uid;
		attributeChanged("uid");
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_name = io.matchUtfString();
		set_name(_name);
		_niceness = io.matchInteger();
		set_niceness(_niceness);
		_uid = io.matchInteger();
		set_uid(_uid);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		if (attributeName.equals("niceness")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_niceness = io.matchInteger();
			set_niceness(_niceness);
			return;
		}
		if (attributeName.equals("uid")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_uid = io.matchInteger();
			set_uid(_uid);
			return;
		}
		throw new NoSuchAttributeException("OtherConstrainedNode doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_name);
		io.writeInteger(_niceness);
		io.writeInteger(_uid);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("niceness")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeInteger(_niceness);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("uid")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeInteger(_uid);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("OtherConstrainedNode doesn't contain an attribute " + attributeName);
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
