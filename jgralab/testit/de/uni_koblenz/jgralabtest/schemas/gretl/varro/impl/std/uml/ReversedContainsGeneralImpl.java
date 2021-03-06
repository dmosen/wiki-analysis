/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.varro.impl.std.uml;

import de.uni_koblenz.jgralab.impl.std.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.General;
import de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Package;

import java.io.IOException;

public class ReversedContainsGeneralImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsGeneral {

	ReversedContainsGeneralImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsGeneral getNextContainsGeneralInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsGeneral)normalEdge).getNextContainsGeneralInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsGeneral getNextContainsGeneralIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsGeneral)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsGeneral.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsGeneral getNextContainsGeneralIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsGeneral)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsGeneral.class, orientation);
	}
	public Package getAlpha() {
		return (Package) super.getAlpha();
	}
	public General getOmega() {
		return (General) super.getOmega();
	}
}
