/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.algolib.simple;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex;
/**
 * FromVertexClass: SimpleVertex
 * FromRoleName : alphaVertex
 * ToVertexClass: SimpleVertex
 * ToRoleName : omegaVertex
 */

public interface SimpleEdge extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleSchema.instance().ec_SimpleEdge;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getNextSimpleEdgeInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getNextSimpleEdgeIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getNextSimpleEdgeIncidence(EdgeDirection orientation);
	public SimpleVertex getAlpha();
	public SimpleVertex getOmega();
}
