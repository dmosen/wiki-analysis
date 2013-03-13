/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.db.localities;

import de.uni_koblenz.jgralab.impl.db.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.db.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City;
import de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County;

import java.io.IOException;

public class ReversedHasCapitalImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital {

	ReversedHasCapitalImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital getNextHasCapitalInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital)normalEdge).getNextHasCapitalInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital getNextHasCapitalIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital getNextHasCapitalIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital.class, orientation);
	}
	public County getAlpha() {
		return (County) super.getAlpha();
	}
	public City getOmega() {
		return (City) super.getOmega();
	}
}
