/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralab.greql2.schema.Expression;
import de.uni_koblenz.jgralab.greql2.schema.FunctionApplication;
/**
 * FromVertexClass: Expression
 * FromRoleName : typeExpr
 * ToVertexClass: FunctionApplication
 * ToRoleName : 
 */

public interface IsTypeExprOfFunction extends de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().ec_IsTypeExprOfFunction;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationInGraph();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf getNextIsTypeExprOfInGraph();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfFunction edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfFunction getNextIsTypeExprOfFunctionInGraph();

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
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf at the "this" vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf getNextIsTypeExprOfIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf getNextIsTypeExprOfIncidence(EdgeDirection orientation);

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfFunction at the "this" vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfFunction getNextIsTypeExprOfFunctionIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfFunction at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfFunction getNextIsTypeExprOfFunctionIncidence(EdgeDirection orientation);
	public Expression getAlpha();
	public FunctionApplication getOmega();
}
