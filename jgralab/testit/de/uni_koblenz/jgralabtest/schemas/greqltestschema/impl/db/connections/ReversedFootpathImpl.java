/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.db.connections;

import de.uni_koblenz.jgralab.impl.db.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.db.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad;

import java.io.IOException;

public class ReversedFootpathImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath, de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way {

	ReversedFootpathImpl(EdgeImpl e, Graph g) {
		super(e, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return getNormalEdge().getAttributedElementClass();
	}

	public double get_length() {
		return ((de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath)normalEdge).get_length();
	}

	public void set_length(double _length) {
		((de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath)normalEdge).set_length(_length);
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

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection)normalEdge).getNextConnectionInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath getNextFootpathInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath)normalEdge).getNextFootpathInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way getNextWayInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way)normalEdge).getNextWayInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection.class, orientation);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath getNextFootpathIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath getNextFootpathIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath.class, orientation);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way getNextWayIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way getNextWayIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way.class, orientation);
	}
	public Crossroad getAlpha() {
		return (Crossroad) super.getAlpha();
	}
	public Crossroad getOmega() {
		return (Crossroad) super.getOmega();
	}
}
