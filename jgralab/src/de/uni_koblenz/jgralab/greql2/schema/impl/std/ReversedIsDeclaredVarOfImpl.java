/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema.impl.std;

import de.uni_koblenz.jgralab.impl.std.ReversedEdgeImpl;
import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralab.greql2.schema.SimpleDeclaration;
import de.uni_koblenz.jgralab.greql2.schema.Variable;

import java.io.IOException;

public class ReversedIsDeclaredVarOfImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation, de.uni_koblenz.jgralab.greql2.schema.IsDeclaredVarOf {

	ReversedIsDeclaredVarOfImpl(EdgeImpl e, Graph g) {
		super(e, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return getNormalEdge().getAttributedElementClass();
	}

	public org.pcollections.PVector<de.uni_koblenz.jgralab.greql2.schema.SourcePosition> get_sourcePositions() {
		return ((de.uni_koblenz.jgralab.greql2.schema.IsDeclaredVarOf)normalEdge).get_sourcePositions();
	}

	public void set_sourcePositions(org.pcollections.PVector<de.uni_koblenz.jgralab.greql2.schema.SourcePosition> _sourcePositions) {
		((de.uni_koblenz.jgralab.greql2.schema.IsDeclaredVarOf)normalEdge).set_sourcePositions(_sourcePositions);
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

	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationInGraph() {
		return ((de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation)normalEdge).getNextGreql2AggregationInGraph();
	}

	public de.uni_koblenz.jgralab.greql2.schema.IsDeclaredVarOf getNextIsDeclaredVarOfInGraph() {
		return ((de.uni_koblenz.jgralab.greql2.schema.IsDeclaredVarOf)normalEdge).getNextIsDeclaredVarOfInGraph();
	}

	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation.class);
	}

	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation.class, orientation);
	}

	public de.uni_koblenz.jgralab.greql2.schema.IsDeclaredVarOf getNextIsDeclaredVarOfIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.IsDeclaredVarOf)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsDeclaredVarOf.class);
	}

	public de.uni_koblenz.jgralab.greql2.schema.IsDeclaredVarOf getNextIsDeclaredVarOfIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.IsDeclaredVarOf)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsDeclaredVarOf.class, orientation);
	}
	public Variable getAlpha() {
		return (Variable) super.getAlpha();
	}
	public SimpleDeclaration getOmega() {
		return (SimpleDeclaration) super.getOmega();
	}
}
