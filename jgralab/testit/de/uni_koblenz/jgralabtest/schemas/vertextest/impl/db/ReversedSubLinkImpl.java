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

import de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode;
import de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode;

import java.io.IOException;

public class ReversedSubLinkImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralabtest.schemas.vertextest.Link, de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink {

	ReversedSubLinkImpl(EdgeImpl e, Graph g) {
		super(e, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return getNormalEdge().getAttributedElementClass();
	}

	public java.lang.String get_aString() {
		return ((de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)normalEdge).get_aString();
	}

	public void set_aString(java.lang.String _aString) {
		((de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)normalEdge).set_aString(_aString);
	}

	public int get_anInt() {
		return ((de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)normalEdge).get_anInt();
	}

	public void set_anInt(int _anInt) {
		((de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)normalEdge).set_anInt(_anInt);
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

	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getNextLinkInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.vertextest.Link)normalEdge).getNextLinkInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink getNextSubLinkInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)normalEdge).getNextSubLinkInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getNextLinkIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.Link)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getNextLinkIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.Link)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, orientation);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink getNextSubLinkIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink getNextSubLinkIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, orientation);
	}
	public DoubleSubNode getAlpha() {
		return (DoubleSubNode) super.getAlpha();
	}
	public SuperNode getOmega() {
		return (SuperNode) super.getOmega();
	}
}
