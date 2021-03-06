/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralab.greql2.schema.AlternativePathDescription;
import de.uni_koblenz.jgralab.greql2.schema.PathDescription;
/**
 * FromVertexClass: PathDescription
 * FromRoleName : alternatePath
 * ToVertexClass: AlternativePathDescription
 * ToRoleName : 
 */

public interface IsAlternativePathOf extends de.uni_koblenz.jgralab.greql2.schema.IsPathDescriptionOf {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().ec_IsAlternativePathOf;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationInGraph();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.IsAlternativePathOf edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsAlternativePathOf getNextIsAlternativePathOfInGraph();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.IsPathDescriptionOf edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsPathDescriptionOf getNextIsPathDescriptionOfInGraph();

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
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsAlternativePathOf at the "this" vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsAlternativePathOf getNextIsAlternativePathOfIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsAlternativePathOf at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsAlternativePathOf getNextIsAlternativePathOfIncidence(EdgeDirection orientation);

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsPathDescriptionOf at the "this" vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsPathDescriptionOf getNextIsPathDescriptionOfIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.greql2.schema.IsPathDescriptionOf at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsPathDescriptionOf getNextIsPathDescriptionOfIncidence(EdgeDirection orientation);
	public PathDescription getAlpha();
	public AlternativePathDescription getOmega();
}
