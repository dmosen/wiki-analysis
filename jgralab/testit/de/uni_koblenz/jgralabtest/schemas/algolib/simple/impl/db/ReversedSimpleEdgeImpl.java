/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.algolib.simple.impl.db;

import de.uni_koblenz.jgralab.impl.db.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.db.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex;

import java.io.IOException;

public class ReversedSimpleEdgeImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge {

	ReversedSimpleEdgeImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getNextSimpleEdgeInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge)normalEdge).getNextSimpleEdgeInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getNextSimpleEdgeIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getNextSimpleEdgeIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge.class, orientation);
	}
	public SimpleVertex getAlpha() {
		return (SimpleVertex) super.getAlpha();
	}
	public SimpleVertex getOmega() {
		return (SimpleVertex) super.getOmega();
	}
}
