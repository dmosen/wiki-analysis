/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.vertextest.C;
import de.uni_koblenz.jgralabtest.schemas.vertextest.D;
/**
 * FromVertexClass: C
 * FromRoleName : sourceG
 * ToVertexClass: D
 * ToRoleName : z
 */

public interface G extends de.uni_koblenz.jgralabtest.schemas.vertextest.E {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestSchema.instance().ec_G;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.vertextest.E edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEInGraph();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.vertextest.G edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.G getNextGInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.vertextest.E at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.vertextest.E at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getNextEIncidence(EdgeDirection orientation);

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.vertextest.G at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.G getNextGIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.vertextest.G at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.G getNextGIncidence(EdgeDirection orientation);
	public C getAlpha();
	public D getOmega();
}
