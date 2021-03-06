/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.connections;

import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

import de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.connections.ReversedStreetImpl;

import de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad;

import java.io.IOException;
/**
 * FromVertexClass: junctions.Crossroad
 * FromRoleName : 
 * ToVertexClass: junctions.Crossroad
 * ToRoleName : 
 */

public class StreetImpl extends EdgeImpl implements de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street, de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way {

	protected double _length;

	protected java.lang.String _name;

	protected boolean _oneway;

	public StreetImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
		((de.uni_koblenz.jgralab.impl.InternalGraph) graph).addEdge(this, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("length")) return (T) (java.lang.Double) get_length();
		if (attributeName.equals("name")) return (T) get_name();
		if (attributeName.equals("oneway")) return (T) (java.lang.Boolean) is_oneway();
		throw new NoSuchAttributeException("connections.Street doesn't contain an attribute " + attributeName);
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
		if (attributeName.equals("oneway")) {
			set_oneway((java.lang.Boolean) data);
			return;
		}
		throw new NoSuchAttributeException("connections.Street doesn't contain an attribute " + attributeName);
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

	public boolean is_oneway() {
		return _oneway;
	}

	public void set_oneway(boolean _oneway) {
		ecaAttributeChanging("oneway", this._oneway, _oneway);
		Object oldValue = this._oneway;
		this._oneway = _oneway;
		graphModified();
	ecaAttributeChanged("oneway", oldValue, _oneway);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_length = io.matchDouble();
		set_length(_length);
		_name = io.matchUtfString();
		set_name(_name);
		_oneway = io.matchBoolean();
		set_oneway(_oneway);
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
		if (attributeName.equals("oneway")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_oneway = io.matchBoolean();
			set_oneway(_oneway);
			return;
		}
		throw new NoSuchAttributeException("connections.Street doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeDouble(_length);
		io.writeUtfString(_name);
		io.writeBoolean(_oneway);
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
		if (attributeName.equals("oneway")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeBoolean(_oneway);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("connections.Street doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection)getNextEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street getNextStreetInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street)getNextEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way getNextWayInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way)getNextEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection.class, orientation);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street getNextStreetIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street getNextStreetIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street.class, orientation);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way getNextWayIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way getNextWayIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way.class, orientation);
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
	public Crossroad getAlpha() {
		return (Crossroad) super.getAlpha();
	}
	public Crossroad getOmega() {
		return (Crossroad) super.getOmega();
	}
}
