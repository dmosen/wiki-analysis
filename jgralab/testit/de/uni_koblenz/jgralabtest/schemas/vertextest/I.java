/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.vertextest.A;
/**
 * FromVertexClass: A
 * FromRoleName : sourceI
 * ToVertexClass: A
 * ToRoleName : v
 */

public interface I extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestSchema.instance().ec_I;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.vertextest.I edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getNextIInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.vertextest.I at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getNextIIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.vertextest.I at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getNextIIncidence(EdgeDirection orientation);
	public A getAlpha();
	public A getOmega();
}
