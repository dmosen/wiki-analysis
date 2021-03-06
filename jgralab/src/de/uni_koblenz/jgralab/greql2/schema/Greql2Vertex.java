/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Greql2Vertex extends de.uni_koblenz.jgralab.Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().vc_Greql2Vertex;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex getNextGreql2Vertex();

	/**
	 * @return the first edge of class IsTypeExprOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf getFirstIsTypeExprOfIncidence();

	/**
	 * @return the first edge of class IsTypeExprOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf getFirstIsTypeExprOfIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class IsBoundExprOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf getFirstIsBoundExprOfIncidence();

	/**
	 * @return the first edge of class IsBoundExprOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf getFirstIsBoundExprOfIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class Greql2Aggregation at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getFirstGreql2AggregationIncidence();

	/**
	 * @return the first edge of class Greql2Aggregation at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getFirstGreql2AggregationIncidence(EdgeDirection orientation);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsTypeExprOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf> getIsTypeExprOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsTypeExprOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf> getIsTypeExprOfIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsBoundExprOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf> getIsBoundExprOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsBoundExprOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf> getIsBoundExprOfIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Greql2Aggregation or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation> getGreql2AggregationIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Greql2Aggregation.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation> getGreql2AggregationIncidences(EdgeDirection direction);
}
