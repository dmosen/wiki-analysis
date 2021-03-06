/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralab.greql2.schema.Expression;
import de.uni_koblenz.jgralab.greql2.schema.QuantifiedExpression;
/**
 * FromVertexClass: Expression
 * FromRoleName : boundExprOfQuantifier
 * ToVertexClass: QuantifiedExpression
 * ToRoleName : 
 */

public interface IsBoundExprOfQuantifier extends de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().ec_IsBoundExprOfQuantifier;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationInGraph();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf getNextIsBoundExprOfInGraph();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOfQuantifier edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOfQuantifier getNextIsBoundExprOfQuantifierInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation at the "this" vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationIncidence(EdgeDirection orientation);

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf at the "this" vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf getNextIsBoundExprOfIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf getNextIsBoundExprOfIncidence(EdgeDirection orientation);

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOfQuantifier at the "this" vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOfQuantifier getNextIsBoundExprOfQuantifierIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOfQuantifier at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOfQuantifier getNextIsBoundExprOfQuantifierIncidence(EdgeDirection orientation);
	public Expression getAlpha();
	public QuantifiedExpression getOmega();
}
