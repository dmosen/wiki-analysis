/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Attribute;
import de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity;
/**
 * FromVertexClass: Entity
 * FromRoleName : 
 * ToVertexClass: Attribute
 * ToRoleName : attribute
 */

public interface HasAttribute extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.IntegratedBedslPddslSchema.instance().ec_HasAttribute;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute getNextHasAttributeInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute getNextHasAttributeIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute getNextHasAttributeIncidence(EdgeDirection orientation);
	public Entity getAlpha();
	public Attribute getOmega();
}
