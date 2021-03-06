/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City;
import de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County;
/**
 * FromVertexClass: localities.County
 * FromRoleName : 
 * ToVertexClass: localities.City
 * ToRoleName : capital
 */

public interface HasCapital extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteSchema.instance().ec_localities_HasCapital;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital getNextHasCapitalInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital getNextHasCapitalIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital getNextHasCapitalIncidence(EdgeDirection orientation);
	public County getAlpha();
	public City getOmega();
}
