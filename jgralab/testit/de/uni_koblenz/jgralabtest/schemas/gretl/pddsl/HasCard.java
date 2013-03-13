/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.pddsl;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Card;
import de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot;
/**
 * FromVertexClass: Slot
 * FromRoleName : 
 * ToVertexClass: Card
 * ToRoleName : card
 */

public interface HasCard extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.PddslSchema.instance().ec_HasCard;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasCard edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasCard getNextHasCardInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasCard at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasCard getNextHasCardIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasCard at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasCard getNextHasCardIncidence(EdgeDirection orientation);
	public Slot getAlpha();
	public Card getOmega();
}
