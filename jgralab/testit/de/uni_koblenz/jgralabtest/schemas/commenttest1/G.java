/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.commenttest1;

public interface G extends de.uni_koblenz.jgralab.Graph {

	public static final de.uni_koblenz.jgralab.schema.GraphClass GC = de.uni_koblenz.jgralabtest.schemas.commenttest1.CommentTestSchema.instance().gc_G;

	// ------------------------ Code for p.E ------------------------

	/**
	 * @return the first E edge in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.commenttest1.p.E getFirstE();

	/**
	 * Creates a new E edge in this graph.
	 *
	 * @param alpha the start vertex of the edge
	 * @param omega the target vertex of the edge
	*/
	public de.uni_koblenz.jgralabtest.schemas.commenttest1.p.E createE(de.uni_koblenz.jgralabtest.schemas.commenttest1.p.V alpha, de.uni_koblenz.jgralabtest.schemas.commenttest1.p.V omega);

	// ------------------------ Code for p.V ------------------------

	/**
	 * @return the first V vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.commenttest1.p.V getFirstV();

	/**
	 * Creates a new V vertex in this graph.
	 *
	*/
	public de.uni_koblenz.jgralabtest.schemas.commenttest1.p.V createV();

	/**
	 * @return an Iterable for all edges of this graph that are of type p.E or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.commenttest1.p.E> getEEdges();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type p.V or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.commenttest1.p.V> getVVertices();
	
}
