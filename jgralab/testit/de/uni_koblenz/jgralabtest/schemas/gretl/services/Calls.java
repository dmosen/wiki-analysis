/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.services;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.gretl.services.Service;
/**
 * FromVertexClass: Service
 * FromRoleName : 
 * ToVertexClass: Service
 * ToRoleName : 
 */

public interface Calls extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.gretl.services.ServiceSchema.instance().ec_Calls;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls getNextCallsInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls getNextCallsIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls getNextCallsIncidence(EdgeDirection orientation);
	public Service getAlpha();
	public Service getOmega();
}
