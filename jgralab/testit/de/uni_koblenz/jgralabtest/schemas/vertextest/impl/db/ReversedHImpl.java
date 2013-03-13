/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest.impl.db;

import de.uni_koblenz.jgralab.impl.db.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.db.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.vertextest.A;
import de.uni_koblenz.jgralabtest.schemas.vertextest.B;

import java.io.IOException;

public class ReversedHImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralabtest.schemas.vertextest.E, de.uni_koblenz.jgralabtest.schemas.vertextest.H {

	ReversedHImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralabtest.schemas.vertextest.H getNextHInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.vertextest.H)normalEdge).getNextHInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.E)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.E)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class, orientation);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.H getNextHIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.H)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.H getNextHIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.H)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, orientation);
	}
	public A getAlpha() {
		return (A) super.getAlpha();
	}
	public B getOmega() {
		return (B) super.getOmega();
	}
}
