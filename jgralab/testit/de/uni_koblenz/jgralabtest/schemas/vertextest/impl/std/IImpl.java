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

import de.uni_koblenz.jgralabtest.schemas.vertextest.impl.std.ReversedIImpl;

import de.uni_koblenz.jgralabtest.schemas.vertextest.A;

import java.io.IOException;
/**
 * FromVertexClass: A
 * FromRoleName : sourceI
 * ToVertexClass: A
 * ToRoleName : v
 */

public class IImpl extends EdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.vertextest.I {

	public IImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
		((de.uni_koblenz.jgralab.impl.InternalGraph) graph).addEdge(this, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.I.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.I.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("I doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("I doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("I doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("I doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getNextIInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.I)getNextEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getNextIIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.I)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getNextIIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.I)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, orientation);
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
		return new ReversedIImpl(this, graph);
	}
	public A getAlpha() {
		return (A) super.getAlpha();
	}
	public A getOmega() {
		return (A) super.getOmega();
	}
}
