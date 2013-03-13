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

import de.uni_koblenz.jgralab.grumlschema.impl.std.structure.ReversedRedefinesImpl;

import de.uni_koblenz.jgralab.grumlschema.structure.IncidenceClass;

import java.io.IOException;
/**
 * FromVertexClass: structure.IncidenceClass
 * FromRoleName : 
 * ToVertexClass: structure.IncidenceClass
 * ToRoleName : 
 */

public class RedefinesImpl extends EdgeImpl implements de.uni_koblenz.jgralab.grumlschema.structure.Redefines, de.uni_koblenz.jgralab.grumlschema.structure.Subsets {

	public RedefinesImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
		((de.uni_koblenz.jgralab.impl.InternalGraph) graph).addEdge(this, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralab.grumlschema.structure.Redefines.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralab.grumlschema.structure.Redefines.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("structure.Redefines doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("structure.Redefines doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("structure.Redefines doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("structure.Redefines doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.Redefines getNextRedefinesInGraph() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.Redefines)getNextEdge(de.uni_koblenz.jgralab.grumlschema.structure.Redefines.class);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.Subsets getNextSubsetsInGraph() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.Subsets)getNextEdge(de.uni_koblenz.jgralab.grumlschema.structure.Subsets.class);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.Redefines getNextRedefinesIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.Redefines)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.Redefines.class);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.Redefines getNextRedefinesIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.structure.Redefines)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.Redefines.class, orientation);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.Subsets getNextSubsetsIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.Subsets)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.Subsets.class);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.Subsets getNextSubsetsIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.structure.Subsets)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.Subsets.class, orientation);
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
		return new ReversedRedefinesImpl(this, graph);
	}
	public IncidenceClass getAlpha() {
		return (IncidenceClass) super.getAlpha();
	}
	public IncidenceClass getOmega() {
		return (IncidenceClass) super.getOmega();
	}
}
