/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.services;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.gretl.services.BasicService;
import de.uni_koblenz.jgralabtest.schemas.gretl.services.Database;
/**
 * FromVertexClass: BasicService
 * FromRoleName : 
 * ToVertexClass: Database
 * ToRoleName : 
 */

public interface Accesses extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.gretl.services.ServiceSchema.instance().ec_Accesses;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.services.Accesses edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Accesses getNextAccessesInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.services.Accesses at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Accesses getNextAccessesIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.services.Accesses at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Accesses getNextAccessesIncidence(EdgeDirection orientation);
	public BasicService getAlpha();
	public Database getOmega();
}
