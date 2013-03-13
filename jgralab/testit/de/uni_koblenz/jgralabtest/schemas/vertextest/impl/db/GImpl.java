/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest.impl.db;

import de.uni_koblenz.jgralab.impl.db.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

import de.uni_koblenz.jgralabtest.schemas.vertextest.impl.db.ReversedGImpl;

import de.uni_koblenz.jgralabtest.schemas.vertextest.C;
import de.uni_koblenz.jgralabtest.schemas.vertextest.D;

import java.io.IOException;
/**
 * FromVertexClass: C
 * FromRoleName : sourceG
 * ToVertexClass: D
 * ToRoleName : z
 */

public class GImpl extends EdgeImpl implements de.uni_koblenz.jgralabtest.schemas.vertextest.E, de.uni_koblenz.jgralabtest.schemas.vertextest.G {

	public GImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.G.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.G.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("G doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("G doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("G doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("G doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.E)getNextEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.G getNextGInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.G)getNextEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.E)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.E)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class, orientation);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.G getNextGIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.G)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.G getNextGIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.G)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, orientation);
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
		return new ReversedGImpl(this, graph);
	}
	public C getAlpha() {
		return (C) super.getAlpha();
	}
	public D getOmega() {
		return (D) super.getOmega();
	}
}
