/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.jniclient.impl.db;

import de.uni_koblenz.jgralab.impl.db.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.db.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode;

import java.io.IOException;

public class ReversedSimpleEdgeImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge {

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

	public de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge getNextSimpleEdgeInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge)normalEdge).getNextSimpleEdgeInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge getNextSimpleEdgeIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge getNextSimpleEdgeIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge.class, orientation);
	}
	public SimpleNode getAlpha() {
		return (SimpleNode) super.getAlpha();
	}
	public SimpleNode getOmega() {
		return (SimpleNode) super.getOmega();
	}
}
