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

import de.uni_koblenz.jgralabtest.schemas.vertextest.C2;
import de.uni_koblenz.jgralabtest.schemas.vertextest.D2;

import java.io.IOException;

public class ReversedJImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralabtest.schemas.vertextest.E, de.uni_koblenz.jgralabtest.schemas.vertextest.J {

	ReversedJImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.vertextest.E)normalEdge).getNextEInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.J getNextJInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.vertextest.J)normalEdge).getNextJInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.E)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.E)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class, orientation);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.J getNextJIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.J)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.J.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.J getNextJIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.J)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.J.class, orientation);
	}
	public C2 getAlpha() {
		return (C2) super.getAlpha();
	}
	public D2 getOmega() {
		return (D2) super.getOmega();
	}
}
