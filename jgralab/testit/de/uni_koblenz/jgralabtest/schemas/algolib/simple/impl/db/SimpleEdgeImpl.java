/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.algolib.simple.impl.db;

import de.uni_koblenz.jgralab.impl.db.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

import de.uni_koblenz.jgralabtest.schemas.algolib.simple.impl.db.ReversedSimpleEdgeImpl;

import de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex;

import java.io.IOException;
/**
 * FromVertexClass: SimpleVertex
 * FromRoleName : alphaVertex
 * ToVertexClass: SimpleVertex
 * ToRoleName : omegaVertex
 */

public class SimpleEdgeImpl extends EdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge {

	public SimpleEdgeImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("SimpleEdge doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("SimpleEdge doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("SimpleEdge doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("SimpleEdge doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getNextSimpleEdgeInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge)getNextEdge(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getNextSimpleEdgeIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getNextSimpleEdgeIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge.class, orientation);
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
		return new ReversedSimpleEdgeImpl(this, graph);
	}
	public SimpleVertex getAlpha() {
		return (SimpleVertex) super.getAlpha();
	}
	public SimpleVertex getOmega() {
		return (SimpleVertex) super.getOmega();
	}
}
