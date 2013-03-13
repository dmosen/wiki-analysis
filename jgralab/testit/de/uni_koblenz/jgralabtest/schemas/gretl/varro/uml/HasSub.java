/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Class;
import de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.General;
/**
 * FromVertexClass: uml.General
 * FromRoleName : 
 * ToVertexClass: uml.Class
 * ToRoleName : childElem
 */

public interface HasSub extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.gretl.varro.UMLSchema.instance().ec_uml_HasSub;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSub edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSub getNextHasSubInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSub at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSub getNextHasSubIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSub at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSub getNextHasSubIncidence(EdgeDirection orientation);
	public General getAlpha();
	public Class getOmega();
}
