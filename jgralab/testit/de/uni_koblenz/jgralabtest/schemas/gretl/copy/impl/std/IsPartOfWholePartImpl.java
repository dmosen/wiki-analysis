/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.copy.impl.std;

import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

import de.uni_koblenz.jgralabtest.schemas.gretl.copy.impl.std.ReversedIsPartOfWholePartImpl;

import de.uni_koblenz.jgralabtest.schemas.gretl.copy.Part;
import de.uni_koblenz.jgralabtest.schemas.gretl.copy.WholePart;

import java.io.IOException;
/**
 * FromVertexClass: Part
 * FromRoleName : 
 * ToVertexClass: WholePart
 * ToRoleName : 
 */

public class IsPartOfWholePartImpl extends EdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart {

	public IsPartOfWholePartImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
		((de.uni_koblenz.jgralab.impl.InternalGraph) graph).addEdge(this, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("IsPartOfWholePart doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("IsPartOfWholePart doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("IsPartOfWholePart doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("IsPartOfWholePart doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart getNextIsPartOfWholePartInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart)getNextEdge(de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart getNextIsPartOfWholePartIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart getNextIsPartOfWholePartIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart.class, orientation);
	}

	public de.uni_koblenz.jgralab.schema.AggregationKind getAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getAlphaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getOmegaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.NONE;
	}

	protected de.uni_koblenz.jgralab.impl.ReversedEdgeBaseImpl createReversedEdge() {
		return new ReversedIsPartOfWholePartImpl(this, graph);
	}
	public Part getAlpha() {
		return (Part) super.getAlpha();
	}
	public WholePart getOmega() {
		return (WholePart) super.getOmega();
	}
}
