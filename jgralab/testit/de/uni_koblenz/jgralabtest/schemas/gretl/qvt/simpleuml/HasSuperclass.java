/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Class;
/**
 * FromVertexClass: Class
 * FromRoleName : subclass
 * ToVertexClass: Class
 * ToRoleName : general
 */

public interface HasSuperclass extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.SimpleUMLSchema.instance().ec_HasSuperclass;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasSuperclass edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasSuperclass getNextHasSuperclassInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasSuperclass at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasSuperclass getNextHasSuperclassIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasSuperclass at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.HasSuperclass getNextHasSuperclassIncidence(EdgeDirection orientation);
	public Class getAlpha();
	public Class getOmega();
}
