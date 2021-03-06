/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.impl.std;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.std.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class ContactImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact, de.uni_koblenz.jgralab.Vertex {

	protected java.lang.String _address;

	protected java.lang.String _name;

	public ContactImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("address")) return (T) get_address();
		if (attributeName.equals("name")) return (T) get_name();
		throw new NoSuchAttributeException("Contact doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("address")) {
			set_address((java.lang.String) data);
			return;
		}
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("Contact doesn't contain an attribute " + attributeName);
	}

	public java.lang.String get_address() {
		return _address;
	}

	public void set_address(java.lang.String _address) {
		ecaAttributeChanging("address", this._address, _address);
		Object oldValue = this._address;
		this._address = _address;
		graphModified();
	ecaAttributeChanged("address", oldValue, _address);
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
		_address = io.matchUtfString();
		set_address(_address);
		_name = io.matchUtfString();
		set_name(_name);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("address")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_address = io.matchUtfString();
			set_address(_address);
			return;
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		throw new NoSuchAttributeException("Contact doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_address);
		io.writeUtfString(_name);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("address")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_address);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("Contact doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact getNextContact() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact)getNextVertex(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains getFirstContainsIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains getFirstContainsIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains.class, orientation);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains> getContainsIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains>(this, de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains> getContainsIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains>(this, de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains.class, direction);
	}
}
