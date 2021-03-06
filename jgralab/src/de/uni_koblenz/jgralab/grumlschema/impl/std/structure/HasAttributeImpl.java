/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.grumlschema.impl.std.structure;

import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

import de.uni_koblenz.jgralab.grumlschema.impl.std.structure.ReversedHasAttributeImpl;

import de.uni_koblenz.jgralab.grumlschema.structure.Attribute;
import de.uni_koblenz.jgralab.grumlschema.structure.AttributedElementClass;

import java.io.IOException;
/**
 * FromVertexClass: structure.AttributedElementClass
 * FromRoleName : 
 * ToVertexClass: structure.Attribute
 * ToRoleName : attribute
 */

public class HasAttributeImpl extends EdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute {

	public HasAttributeImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
		((de.uni_koblenz.jgralab.impl.InternalGraph) graph).addEdge(this, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("structure.HasAttribute doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("structure.HasAttribute doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("structure.HasAttribute doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("structure.HasAttribute doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute getNextHasAttributeInGraph() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute)getNextEdge(de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute.class);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute getNextHasAttributeIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute.class);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute getNextHasAttributeIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute.class, orientation);
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
		return new ReversedHasAttributeImpl(this, graph);
	}
	public AttributedElementClass getAlpha() {
		return (AttributedElementClass) super.getAlpha();
	}
	public Attribute getOmega() {
		return (Attribute) super.getOmega();
	}
}
