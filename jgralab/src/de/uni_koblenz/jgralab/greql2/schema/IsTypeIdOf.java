/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralab.greql2.schema.EdgeRestriction;
import de.uni_koblenz.jgralab.greql2.schema.TypeId;
/**
 * FromVertexClass: TypeId
 * FromRoleName : typeId
 * ToVertexClass: EdgeRestriction
 * ToRoleName : 
 */

public interface IsTypeIdOf extends de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().ec_IsTypeIdOf;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationInGraph();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf getNextIsTypeIdOfInGraph();

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
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf at the "this" vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf getNextIsTypeIdOfIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf getNextIsTypeIdOfIncidence(EdgeDirection orientation);
	public TypeId getAlpha();
	public EdgeRestriction getOmega();
}
