/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.impl.std;

import de.uni_koblenz.jgralab.impl.EdgeIterable;
import de.uni_koblenz.jgralab.impl.VertexIterable;

import de.uni_koblenz.jgralab.impl.std.GraphImpl;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;

import java.io.IOException;

import org.pcollections.POrderedSet;

public class AddressBookGraphImpl extends GraphImpl implements de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBookGraph, de.uni_koblenz.jgralab.Graph {

	protected java.lang.String _name;

	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public AddressBookGraphImpl() {
		this(null);
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public AddressBookGraphImpl(int vMax, int eMax) {
		this(null, vMax, eMax);
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public AddressBookGraphImpl(java.lang.String id, int vMax, int eMax) {
		super(id, de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBookGraph.GC, vMax, eMax);
		initializeAttributesWithDefaultValues();
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public AddressBookGraphImpl(java.lang.String id) {
		super(id, de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBookGraph.GC);
		initializeAttributesWithDefaultValues();
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.GraphClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBookGraph.GC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Graph> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBookGraph.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("name")) return (T) get_name();
		throw new NoSuchAttributeException("AddressBookGraph doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("AddressBookGraph doesn't contain an attribute " + attributeName);
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
		_name = io.matchUtfString();
		set_name(_name);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		throw new NoSuchAttributeException("AddressBookGraph doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_name);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("AddressBookGraph doesn't contain an attribute " + attributeName);
	}
	
	protected GreqlEvaluator greqlEvaluator;

	@Override
	public synchronized <T extends Vertex> POrderedSet<T> reachableVertices(Vertex startVertex, String pathDescription, Class<T> vertexType) {
		if (greqlEvaluator == null) {
			greqlEvaluator = new GreqlEvaluator((String) null, this, null);
		}
		greqlEvaluator.setVariable("v", startVertex);
		greqlEvaluator.setQuery("using v: v " + pathDescription);
		greqlEvaluator.startEvaluation();
		return greqlEvaluator.getResultSet();
	}

	// ------------------------ Code for AddressBook ------------------------

	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook getFirstAddressBook() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook createAddressBook() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook> createVertex(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook createAddressBook(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook> createVertex(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook.VC, id, this);
	}

	// ------------------------ Code for Contact ------------------------

	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact getFirstContact() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact createContact() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact> createVertex(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact createContact(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact> createVertex(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact.VC, id, this);
	}

	// ------------------------ Code for Contains ------------------------

	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains getFirstContains() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains createContains(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook alpha, de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains> createEdge(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains createContains(int id, de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook alpha, de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains> createEdge(de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains.EC, id, this, alpha, omega);
	}

	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains> getContainsEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains>(this, de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook> getAddressBookVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook>(this, de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBook.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact> getContactVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact>(this, de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact.class);
	}
	
}
