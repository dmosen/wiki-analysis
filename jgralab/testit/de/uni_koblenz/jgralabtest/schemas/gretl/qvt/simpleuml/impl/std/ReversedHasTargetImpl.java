/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.impl.std;

import de.uni_koblenz.jgralab.impl.std.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Association;
import de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Class;

import java.io.IOException;

public class ReversedHasTargetImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.Edge, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasTarget {

	ReversedHasTargetImpl(EdgeImpl e, Graph g) {
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

	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasTarget getNextHasTargetInGraph() {
		return ((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasTarget)normalEdge).getNextHasTargetInGraph();
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasTarget getNextHasTargetIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasTarget)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasTarget.class);
	}

	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasTarget getNextHasTargetIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasTarget)getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasTarget.class, orientation);
	}
	public Association getAlpha() {
		return (Association) super.getAlpha();
	}
	public Class getOmega() {
		return (Class) super.getOmega();
	}
}
