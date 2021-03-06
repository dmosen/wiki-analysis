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

import de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode;
import de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode;

import java.io.IOException;

public class ReversedLinkBackImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack {

	ReversedLinkBackImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack getNextLinkBackInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack)normalEdge).getNextLinkBackInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack getNextLinkBackIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack getNextLinkBackIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, orientation);
	}
	public SuperNode getAlpha() {
		return (SuperNode) super.getAlpha();
	}
	public AbstractSuperNode getOmega() {
		return (AbstractSuperNode) super.getOmega();
	}
}
