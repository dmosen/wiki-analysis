/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.algolib.weighted;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.algolib.weighted.Location;
/**
 * FromVertexClass: Location
 * FromRoleName : alphaVertex
 * ToVertexClass: Location
 * ToRoleName : omegaVertex
 */

public interface Way extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.algolib.weighted.WeightedSchema.instance().ec_Way;

	public double get_weight();

	public void set_weight(double _weight);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.algolib.weighted.Way edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.algolib.weighted.Way getNextWayInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.algolib.weighted.Way at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.algolib.weighted.Way getNextWayIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.algolib.weighted.Way at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.algolib.weighted.Way getNextWayIncidence(EdgeDirection orientation);
	public Location getAlpha();
	public Location getOmega();
}
