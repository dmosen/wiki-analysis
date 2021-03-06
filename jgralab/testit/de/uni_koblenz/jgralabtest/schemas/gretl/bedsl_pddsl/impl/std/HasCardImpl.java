/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std;

import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

import de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.impl.std.ReversedHasCardImpl;

import de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Card;
import de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Slot;

import java.io.IOException;
/**
 * FromVertexClass: Slot
 * FromRoleName : 
 * ToVertexClass: Card
 * ToRoleName : card
 */

public class HasCardImpl extends EdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard {

	public HasCardImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
		((de.uni_koblenz.jgralab.impl.InternalGraph) graph).addEdge(this, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("HasCard doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("HasCard doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("HasCard doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("HasCard doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard getNextHasCardInGraph() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard)getNextEdge(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard getNextHasCardIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard getNextHasCardIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasCard.class, orientation);
	}

	public de.uni_koblenz.jgralab.schema.AggregationKind getAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.SHARED;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getAlphaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.NONE;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getOmegaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.SHARED;
	}

	protected de.uni_koblenz.jgralab.impl.ReversedEdgeBaseImpl createReversedEdge() {
		return new ReversedHasCardImpl(this, graph);
	}
	public Slot getAlpha() {
		return (Slot) super.getAlpha();
	}
	public Card getOmega() {
		return (Card) super.getOmega();
	}
}
