/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std;

import de.uni_koblenz.jgralab.impl.std.VertexImpl;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class RModelElementImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement, de.uni_koblenz.jgralab.Vertex {

	protected java.lang.String _kind;

	protected java.lang.String _name;

	public RModelElementImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("kind")) return (T) get_kind();
		if (attributeName.equals("name")) return (T) get_name();
		throw new NoSuchAttributeException("RModelElement doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("kind")) {
			set_kind((java.lang.String) data);
			return;
		}
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("RModelElement doesn't contain an attribute " + attributeName);
	}

	public java.lang.String get_kind() {
		return _kind;
	}

	public void set_kind(java.lang.String _kind) {
		ecaAttributeChanging("kind", this._kind, _kind);
		Object oldValue = this._kind;
		this._kind = _kind;
		graphModified();
	ecaAttributeChanged("kind", oldValue, _kind);
	}

	public java.lang.String get_name() {
		return _name;
	}

	public void set_name(java.lang.String _name) {
		ecaAttributeChanging("name", this._name, _name);
		Object oldValue = this._name;
		this._name = _name;
		graphModified();
	ecaAttributeChanged("name", oldValue, _name);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_kind = io.matchUtfString();
		set_kind(_kind);
		_name = io.matchUtfString();
		set_name(_name);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("kind")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_kind = io.matchUtfString();
			set_kind(_kind);
			return;
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		throw new NoSuchAttributeException("RModelElement doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_kind);
		io.writeUtfString(_name);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("kind")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_kind);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("RModelElement doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement getNextRModelElement() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement)getNextVertex(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement.class);
	}
}
