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

import de.uni_koblenz.jgralab.grumlschema.structure.EdgeClass;
import de.uni_koblenz.jgralab.grumlschema.structure.IncidenceClass;

import java.io.IOException;

public class ReversedComesFromImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralab.grumlschema.structure.ComesFrom {

	ReversedComesFromImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralab.grumlschema.structure.ComesFrom getNextComesFromInGraph() {
		return ((de.uni_koblenz.jgralab.grumlschema.structure.ComesFrom)normalEdge).getNextComesFromInGraph();
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.ComesFrom getNextComesFromIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.ComesFrom)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.ComesFrom.class);
	}

	public de.uni_koblenz.jgralab.grumlschema.structure.ComesFrom getNextComesFromIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.structure.ComesFrom)getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.ComesFrom.class, orientation);
	}
	public EdgeClass getAlpha() {
		return (EdgeClass) super.getAlpha();
	}
	public IncidenceClass getOmega() {
		return (IncidenceClass) super.getOmega();
	}
}
