/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std;

import de.uni_koblenz.jgralab.impl.std.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey;
import de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table;

import java.io.IOException;

public class ReversedHasForeignKeyImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey {

	ReversedHasForeignKeyImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey getNextHasForeignKeyInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey)normalEdge).getNextHasForeignKeyInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey getNextHasForeignKeyIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey getNextHasForeignKeyIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.class, orientation);
	}
	public Table getAlpha() {
		return (Table) super.getAlpha();
	}
	public ForeignKey getOmega() {
		return (ForeignKey) super.getOmega();
	}
}
