/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.citymap.impl.std;

import de.uni_koblenz.jgralab.impl.std.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.citymap.Junction;

import java.io.IOException;

public class ReversedFootpathImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralabtest.schemas.citymap.Footpath, de.uni_koblenz.jgralabtest.schemas.citymap.Way {

	ReversedFootpathImpl(EdgeImpl e, Graph g) {
		super(e, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return getNormalEdge().getAttributedElementClass();
	}

	public int get_length() {
		return ((de.uni_koblenz.jgralabtest.schemas.citymap.Footpath)normalEdge).get_length();
	}

	public void set_length(int _length) {
		((de.uni_koblenz.jgralabtest.schemas.citymap.Footpath)normalEdge).set_length(_length);
	}

	public java.lang.String get_name() {
		return ((de.uni_koblenz.jgralabtest.schemas.citymap.Footpath)normalEdge).get_name();
	}

	public void set_name(java.lang.String _name) {
		((de.uni_koblenz.jgralabtest.schemas.citymap.Footpath)normalEdge).set_name(_name);
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

	public de.uni_koblenz.jgralabtest.schemas.citymap.Footpath getNextFootpathInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.citymap.Footpath)normalEdge).getNextFootpathInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.citymap.Way getNextWayInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.citymap.Way)normalEdge).getNextWayInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.citymap.Footpath getNextFootpathIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Footpath)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Footpath.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.citymap.Footpath getNextFootpathIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Footpath)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Footpath.class, orientation);
	}

	public de.uni_koblenz.jgralabtest.schemas.citymap.Way getNextWayIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Way)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.citymap.Way getNextWayIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Way)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, orientation);
	}
	public Junction getAlpha() {
		return (Junction) super.getAlpha();
	}
	public Junction getOmega() {
		return (Junction) super.getOmega();
	}
}
