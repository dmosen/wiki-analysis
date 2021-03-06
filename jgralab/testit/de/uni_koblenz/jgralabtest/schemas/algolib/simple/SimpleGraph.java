/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.algolib.simple;

public interface SimpleGraph extends de.uni_koblenz.jgralab.Graph {

	public static final de.uni_koblenz.jgralab.schema.GraphClass GC = de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleSchema.instance().gc_SimpleGraph;

	// ------------------------ Code for SimpleEdge ------------------------

	/**
	 * @return the first SimpleEdge edge in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getFirstSimpleEdge();

	/**
	 * Creates a new SimpleEdge edge in this graph.
	 *
	 * @param alpha the start vertex of the edge
	 * @param omega the target vertex of the edge
	*/
	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge createSimpleEdge(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex alpha, de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex omega);

	// ------------------------ Code for SimpleVertex ------------------------

	/**
	 * @return the first SimpleVertex vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex getFirstSimpleVertex();

	/**
	 * Creates a new SimpleVertex vertex in this graph.
	 *
	*/
	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex createSimpleVertex();

	/**
	 * @return an Iterable for all edges of this graph that are of type SimpleEdge or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge> getSimpleEdgeEdges();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type SimpleVertex or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex> getSimpleVertexVertices();
	
}
