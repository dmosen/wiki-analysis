/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest.impl.std;

import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

import de.uni_koblenz.jgralabtest.schemas.vertextest.impl.std.ReversedSubLinkImpl;

import de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode;
import de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode;

import java.io.IOException;
/**
 * FromVertexClass: DoubleSubNode
 * FromRoleName : sourcec
 * ToVertexClass: SuperNode
 * ToRoleName : targetc
 */

public class SubLinkImpl extends EdgeImpl implements de.uni_koblenz.jgralabtest.schemas.vertextest.Link, de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink {

	protected java.lang.String _aString;

	protected int _anInt;

	public SubLinkImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
		((de.uni_koblenz.jgralab.impl.InternalGraph) graph).addEdge(this, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("aString")) return (T) get_aString();
		if (attributeName.equals("anInt")) return (T) (java.lang.Integer) get_anInt();
		throw new NoSuchAttributeException("SubLink doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("aString")) {
			set_aString((java.lang.String) data);
			return;
		}
		if (attributeName.equals("anInt")) {
			set_anInt((java.lang.Integer) data);
			return;
		}
		throw new NoSuchAttributeException("SubLink doesn't contain an attribute " + attributeName);
	}

	public java.lang.String get_aString() {
		return _aString;
	}

	public void set_aString(java.lang.String _aString) {
		ecaAttributeChanging("aString", this._aString, _aString);
		Object oldValue = this._aString;
		this._aString = _aString;
		graphModified();
	ecaAttributeChanged("aString", oldValue, _aString);
	}

	public int get_anInt() {
		return _anInt;
	}

	public void set_anInt(int _anInt) {
		ecaAttributeChanging("anInt", this._anInt, _anInt);
		Object oldValue = this._anInt;
		this._anInt = _anInt;
		graphModified();
	ecaAttributeChanged("anInt", oldValue, _anInt);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_aString = io.matchUtfString();
		set_aString(_aString);
		_anInt = io.matchInteger();
		set_anInt(_anInt);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("aString")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_aString = io.matchUtfString();
			set_aString(_aString);
			return;
		}
		if (attributeName.equals("anInt")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_anInt = io.matchInteger();
			set_anInt(_anInt);
			return;
		}
		throw new NoSuchAttributeException("SubLink doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_aString);
		io.writeInteger(_anInt);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("aString")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_aString);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("anInt")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeInteger(_anInt);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("SubLink doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getNextLinkInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.Link)getNextEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink getNextSubLinkInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)getNextEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getNextLinkIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.Link)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getNextLinkIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.Link)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, orientation);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink getNextSubLinkIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink getNextSubLinkIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, orientation);
	}

	public de.uni_koblenz.jgralab.schema.AggregationKind getAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getAlphaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.NONE;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getOmegaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE;
	}

	protected de.uni_koblenz.jgralab.impl.ReversedEdgeBaseImpl createReversedEdge() {
		return new ReversedSubLinkImpl(this, graph);
	}
	public DoubleSubNode getAlpha() {
		return (DoubleSubNode) super.getAlpha();
	}
	public SuperNode getOmega() {
		return (SuperNode) super.getOmega();
	}
}
