/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.families;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.gretl.families.Family;
import de.uni_koblenz.jgralabtest.schemas.gretl.families.Member;
/**
 * FromVertexClass: Family
 * FromRoleName : familyDaughter
 * ToVertexClass: Member
 * ToRoleName : daughters
 */

public interface HasDaughter extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.gretl.families.FamilySchema.instance().ec_HasDaughter;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter getNextHasDaughterInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter getNextHasDaughterIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter getNextHasDaughterIncidence(EdgeDirection orientation);
	public Family getAlpha();
	public Member getOmega();
}
