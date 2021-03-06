/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.grumlschema.impl.std.structure;

import de.uni_koblenz.jgralab.impl.std.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralab.grumlschema.structure.Package;
import de.uni_koblenz.jgralab.grumlschema.structure.Schema;

import java.io.IOException;

public class ReversedContainsDefaultPackageImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralab.grumlschema.structure.ContainsDefaultPackage {

	ReversedContainsDefaultPackageImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralab.grumlschema.structure.ContainsDefaultPackage getNextContainsDefaultPackageInGraph() {
		return ((de.uni_koblenz.jgralab.grumlschema.structure.ContainsDefaultPackage)normalEdge).getNextContainsDefaultPackageInGraph();
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.ContainsDefaultPackage getNextContainsDefaultPackageIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.ContainsDefaultPackage)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.ContainsDefaultPackage.class);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.ContainsDefaultPackage getNextContainsDefaultPackageIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.structure.ContainsDefaultPackage)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.ContainsDefaultPackage.class, orientation);
	}
	public Schema getAlpha() {
		return (Schema) super.getAlpha();
	}
	public Package getOmega() {
		return (Package) super.getOmega();
	}
}
