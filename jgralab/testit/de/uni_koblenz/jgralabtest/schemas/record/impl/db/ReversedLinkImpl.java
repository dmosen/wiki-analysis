/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.record.impl.db;

import de.uni_koblenz.jgralab.impl.db.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.db.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.record.Node;

import java.io.IOException;

public class ReversedLinkImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.record.Link {

	ReversedLinkImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralabtest.schemas.record.Link getNextLinkInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.record.Link)normalEdge).getNextLinkInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.record.Link getNextLinkIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.record.Link)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.record.Link.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.record.Link getNextLinkIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.record.Link)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.record.Link.class, orientation);
	}
	public Node getAlpha() {
		return (Node) super.getAlpha();
	}
	public Node getOmega() {
		return (Node) super.getOmega();
	}
}
