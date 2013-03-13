/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.citymap.impl.db;

import de.uni_koblenz.jgralab.impl.db.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

import de.uni_koblenz.jgralabtest.schemas.citymap.impl.db.ReversedStreetImpl;

import de.uni_koblenz.jgralabtest.schemas.citymap.Junction;

import java.io.IOException;
/**
 * FromVertexClass: Junction
 * FromRoleName : 
 * ToVertexClass: Junction
 * ToRoleName : 
 */

public class StreetImpl extends EdgeImpl implements de.uni_koblenz.jgralabtest.schemas.citymap.Street, de.uni_koblenz.jgralabtest.schemas.citymap.Way {

	protected int _length;

	protected java.lang.String _name;

	protected boolean _oneway;

	public StreetImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.citymap.Street.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.citymap.Street.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("length")) return (T) (java.lang.Integer) get_length();
		if (attributeName.equals("name")) return (T) get_name();
		if (attributeName.equals("oneway")) return (T) (java.lang.Boolean) is_oneway();
		throw new NoSuchAttributeException("Street doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("length")) {
			set_length((java.lang.Integer) data);
			return;
		}
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		if (attributeName.equals("oneway")) {
			set_oneway((java.lang.Boolean) data);
			return;
		}
		throw new NoSuchAttributeException("Street doesn't contain an attribute " + attributeName);
	}

	public int get_length() {
		return _length;
	}

	public void set_length(int _length) {
		this._length = _length;
		attributeChanged("length");
	}

	public java.lang.String get_name() {
		return _name;
	}

	public void set_name(java.lang.String _name) {
		this._name = _name;
		attributeChanged("name");
	}

	public boolean is_oneway() {
		return _oneway;
	}

	public void set_oneway(boolean _oneway) {
		this._oneway = _oneway;
		attributeChanged("oneway");
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_length = io.matchInteger();
		set_length(_length);
		_name = io.matchUtfString();
		set_name(_name);
		_oneway = io.matchBoolean();
		set_oneway(_oneway);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("length")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_length = io.matchInteger();
			set_length(_length);
			return;
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		if (attributeName.equals("oneway")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_oneway = io.matchBoolean();
			set_oneway(_oneway);
			return;
		}
		throw new NoSuchAttributeException("Street doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeInteger(_length);
		io.writeUtfString(_name);
		io.writeBoolean(_oneway);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("length")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeInteger(_length);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("oneway")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeBoolean(_oneway);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("Street doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralabtest.schemas.citymap.Street getNextStreetInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Street)getNextEdge(de.uni_koblenz.jgralabtest.schemas.citymap.Street.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.citymap.Way getNextWayInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Way)getNextEdge(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.citymap.Street getNextStreetIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Street)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Street.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.citymap.Street getNextStreetIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Street)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Street.class, orientation);
	}

	public de.uni_koblenz.jgralabtest.schemas.citymap.Way getNextWayIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Way)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.citymap.Way getNextWayIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Way)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, orientation);
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
		return new ReversedStreetImpl(this, graph);
	}
	public Junction getAlpha() {
		return (Junction) super.getAlpha();
	}
	public Junction getOmega() {
		return (Junction) super.getOmega();
	}
}
