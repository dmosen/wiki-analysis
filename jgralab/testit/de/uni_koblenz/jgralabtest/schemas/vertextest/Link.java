/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode;
import de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode;
/**
 * FromVertexClass: AbstractSuperNode
 * FromRoleName : source
 * ToVertexClass: SuperNode
 * ToRoleName : target
 */

public interface Link extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestSchema.instance().ec_Link;

	public java.lang.String get_aString();

	public void set_aString(java.lang.String _aString);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.vertextest.Link edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getNextLinkInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.vertextest.Link at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getNextLinkIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.vertextest.Link at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getNextLinkIncidence(EdgeDirection orientation);
	public AbstractSuperNode getAlpha();
	public SuperNode getOmega();
}
