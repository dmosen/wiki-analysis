/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.motorwaymap.impl.std;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.std.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class MotorwayImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.motorwaymap.Motorway, de.uni_koblenz.jgralab.Vertex {

	protected double _length;

	protected java.lang.String _name;

	public MotorwayImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.motorwaymap.Motorway.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.motorwaymap.Motorway.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("length")) return (T) (java.lang.Double) get_length();
		if (attributeName.equals("name")) return (T) get_name();
		throw new NoSuchAttributeException("Motorway doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("length")) {
			set_length((java.lang.Double) data);
			return;
		}
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("Motorway doesn't contain an attribute " + attributeName);
	}

	public double get_length() {
		return _length;
	}

	public void set_length(double _length) {
		ecaAttributeChanging("length", this._length, _length);
		Object oldValue = this._length;
		this._length = _length;
		graphModified();
	ecaAttributeChanged("length", oldValue, _length);
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
		_length = io.matchDouble();
		set_length(_length);
		_name = io.matchUtfString();
		set_name(_name);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("length")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_length = io.matchDouble();
			set_length(_length);
			return;
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		throw new NoSuchAttributeException("Motorway doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeDouble(_length);
		io.writeUtfString(_name);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("length")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeDouble(_length);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("Motorway doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Motorway getNextMotorway() {
		return (de.uni_koblenz.jgralabtest.schemas.motorwaymap.Motorway)getNextVertex(de.uni_koblenz.jgralabtest.schemas.motorwaymap.Motorway.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers getFirstOffersIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers getFirstOffersIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit getFirstExitIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit getFirstExitIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit.class, orientation);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers> getOffersIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers>(this, de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers> getOffersIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers>(this, de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit> getExitIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit>(this, de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit> getExitIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit>(this, de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit.class, direction);
	}
}
