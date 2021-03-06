/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest;

public interface SubNode extends de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestSchema.instance().vc_SubNode;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode getNextAbstractSuperNode();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode getNextSubNode();
		/**
		 * adds the given vertex as <code>target</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.vertextest.Link</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.vertextest.Link</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.vertextest.Link add_target(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode vertex);
		/**
		 * removes all target adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.Link</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> remove_target();
		/**
		 * removes the given vertex as <code>target</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.Link</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_target(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'target'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.vertextest.Link</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> get_target();
		/**
		 * adds the given vertex as <code>sourceb</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack add_sourceb(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode vertex);
		/**
		 * removes all sourceb adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> remove_sourceb();
		/**
		 * removes the given vertex as <code>sourceb</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_sourceb(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'sourceb'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> get_sourceb();
}
