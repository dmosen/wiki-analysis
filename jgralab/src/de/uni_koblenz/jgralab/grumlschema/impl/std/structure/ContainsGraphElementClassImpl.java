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

import de.uni_koblenz.jgralab.grumlschema.impl.std.structure.ReversedContainsGraphElementClassImpl;

import de.uni_koblenz.jgralab.grumlschema.structure.GraphElementClass;
import de.uni_koblenz.jgralab.grumlschema.structure.Package;

import java.io.IOException;
/**
 * FromVertexClass: structure.Package
 * FromRoleName : 
 * ToVertexClass: structure.GraphElementClass
 * ToRoleName : 
 */

public class ContainsGraphElementClassImpl extends EdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass {

	public ContainsGraphElementClassImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
		((de.uni_koblenz.jgralab.impl.InternalGraph) graph).addEdge(this, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("structure.ContainsGraphElementClass doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("structure.ContainsGraphElementClass doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("structure.ContainsGraphElementClass doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("structure.ContainsGraphElementClass doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass getNextContainsGraphElementClassInGraph() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass)getNextEdge(de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass.class);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass getNextContainsGraphElementClassIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass.class);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass getNextContainsGraphElementClassIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.ContainsGraphElementClass.class, orientation);
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
		return new ReversedContainsGraphElementClassImpl(this, graph);
	}
	public Package getAlpha() {
		return (Package) super.getAlpha();
	}
	public GraphElementClass getOmega() {
		return (GraphElementClass) super.getOmega();
	}
}
