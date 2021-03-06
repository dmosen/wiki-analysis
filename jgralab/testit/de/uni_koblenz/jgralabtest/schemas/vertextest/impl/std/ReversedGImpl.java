/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest.impl.std;

import de.uni_koblenz.jgralab.impl.std.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.vertextest.C;
import de.uni_koblenz.jgralabtest.schemas.vertextest.D;

import java.io.IOException;

public class ReversedGImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralabtest.schemas.vertextest.E, de.uni_koblenz.jgralabtest.schemas.vertextest.G {

	ReversedGImpl(EdgeImpl e, Graph g) {
		super(e, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return getNormalEdge().getAttributedElementClass();
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		throw new GraphIOException("Can not call readAttributeValues for reversed Edges.");
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new GraphIOException("Can not call readAttributeValuesFromString for reversed Edges.");
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		throw new GraphIOException("Can not call writeAttributeValues for reversed Edges.");
	}

	public String writeAttributeValueToString(String _attributeName) throws IOException, GraphIOException {
		throw new GraphIOException("Can not call writeAttributeValueToString for reversed Edges.");
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.vertextest.E)normalEdge).getNextEInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.G getNextGInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.vertextest.G)normalEdge).getNextGInGraph();
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
	public C getAlpha() {
		return (C) super.getAlpha();
	}
	public D getOmega() {
		return (D) super.getOmega();
	}
}
