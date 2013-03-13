/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey;
import de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key;
/**
 * FromVertexClass: ForeignKey
 * FromRoleName : referrer
 * ToVertexClass: Key
 * ToRoleName : referred
 */

public interface RefersTo extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSSchema.instance().ec_RefersTo;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo getNextRefersToInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo getNextRefersToIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo getNextRefersToIncidence(EdgeDirection orientation);
	public ForeignKey getAlpha();
	public Key getOmega();
}
