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

import de.uni_koblenz.jgralab.greql2.schema.Expression;
import de.uni_koblenz.jgralab.greql2.schema.SubgraphRestrictedExpression;

import java.io.IOException;

public class ReversedIsExpressionOnSubgraphImpl extends ReversedEdgeImpl implements de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation, de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph {

	ReversedIsExpressionOnSubgraphImpl(EdgeImpl e, Graph g) {
		super(e, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return getNormalEdge().getAttributedElementClass();
	}

	public org.pcollections.PVector<de.uni_koblenz.jgralab.greql2.schema.SourcePosition> get_sourcePositions() {
		return ((de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph)normalEdge).get_sourcePositions();
	}

	public void set_sourcePositions(org.pcollections.PVector<de.uni_koblenz.jgralab.greql2.schema.SourcePosition> _sourcePositions) {
		((de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph)normalEdge).set_sourcePositions(_sourcePositions);
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

	public de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph getNextIsExpressionOnSubgraphInGraph() {
		return ((de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph)normalEdge).getNextIsExpressionOnSubgraphInGraph();
	}

	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation.class);
	}

	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation.class, orientation);
	}

	public de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph getNextIsExpressionOnSubgraphIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph.class);
	}

	public de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph getNextIsExpressionOnSubgraphIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph.class, orientation);
	}
	public Expression getAlpha() {
		return (Expression) super.getAlpha();
	}
	public SubgraphRestrictedExpression getOmega() {
		return (SubgraphRestrictedExpression) super.getOmega();
	}
}
