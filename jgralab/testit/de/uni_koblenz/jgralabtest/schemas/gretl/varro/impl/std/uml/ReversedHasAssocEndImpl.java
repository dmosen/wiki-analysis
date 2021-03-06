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

import de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Assoc;
import de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.AssocEnd;

import java.io.IOException;

public class ReversedHasAssocEndImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAssocEnd {

	ReversedHasAssocEndImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAssocEnd getNextHasAssocEndInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAssocEnd)normalEdge).getNextHasAssocEndInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAssocEnd getNextHasAssocEndIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAssocEnd)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAssocEnd.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAssocEnd getNextHasAssocEndIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAssocEnd)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAssocEnd.class, orientation);
	}
	public Assoc getAlpha() {
		return (Assoc) super.getAlpha();
	}
	public AssocEnd getOmega() {
		return (AssocEnd) super.getOmega();
	}
}
