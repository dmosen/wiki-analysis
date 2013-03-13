/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

public interface VertexTypeSubgraph extends de.uni_koblenz.jgralab.greql2.schema.SubgraphDefinition {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().vc_VertexTypeSubgraph;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex getNextGreql2Vertex();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.SubgraphDefinition vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.SubgraphDefinition getNextSubgraphDefinition();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.VertexTypeSubgraph vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.VertexTypeSubgraph getNextVertexTypeSubgraph();
		/**
		 * adds the given vertex as <code>typeRestr</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfSubgraph</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfSubgraph</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfSubgraph add_typeRestr(de.uni_koblenz.jgralab.greql2.schema.TypeId vertex);
		/**
		 * removes all typeRestr adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfSubgraph</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.TypeId> remove_typeRestr();
		/**
		 * removes the given vertex as <code>typeRestr</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfSubgraph</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_typeRestr(de.uni_koblenz.jgralab.greql2.schema.TypeId vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'typeRestr'
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfSubgraph</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralab.greql2.schema.TypeId> get_typeRestr();
}
