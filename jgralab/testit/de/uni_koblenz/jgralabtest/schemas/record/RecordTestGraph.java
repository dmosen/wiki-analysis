/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.record;

public interface RecordTestGraph extends de.uni_koblenz.jgralab.Graph {

	public static final de.uni_koblenz.jgralab.schema.GraphClass GC = de.uni_koblenz.jgralabtest.schemas.record.RecordTestSchema.instance().gc_RecordTestGraph;

	// ------------------------ Code for Link ------------------------

	/**
	 * @return the first Link edge in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.record.Link getFirstLink();

	/**
	 * Creates a new Link edge in this graph.
	 *
	 * @param alpha the start vertex of the edge
	 * @param omega the target vertex of the edge
	*/
	public de.uni_koblenz.jgralabtest.schemas.record.Link createLink(de.uni_koblenz.jgralabtest.schemas.record.Node alpha, de.uni_koblenz.jgralabtest.schemas.record.Node omega);

	// ------------------------ Code for Node ------------------------

	/**
	 * @return the first Node vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.record.Node getFirstNode();

	/**
	 * Creates a new Node vertex in this graph.
	 *
	*/
	public de.uni_koblenz.jgralabtest.schemas.record.Node createNode();

	/**
	 * @return an Iterable for all edges of this graph that are of type Link or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.record.Link> getLinkEdges();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type Node or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.record.Node> getNodeVertices();
	
}
