/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest.impl.trans;

import de.uni_koblenz.jgralab.impl.trans.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.trans.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.vertextest.A;

import java.io.IOException;

public class ReversedIImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.vertextest.I {

	ReversedIImpl(EdgeImpl e, Graph g) {
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
	protected java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes() {
		return ((EdgeImpl) normalEdge).attributes();
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getNextIInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.vertextest.I)normalEdge).getNextIInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getNextIIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.I)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getNextIIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.I)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, orientation);
	}
	public A getAlpha() {
		return (A) super.getAlpha();
	}
	public A getOmega() {
		return (A) super.getOmega();
	}
}
