/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Definition extends de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().vc_Definition;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Definition vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Definition getNextDefinition();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex getNextGreql2Vertex();

	/**
	 * @return the first edge of class IsVarOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsVarOf getFirstIsVarOfIncidence();

	/**
	 * @return the first edge of class IsVarOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsVarOf getFirstIsVarOfIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class IsExprOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsExprOf getFirstIsExprOfIncidence();

	/**
	 * @return the first edge of class IsExprOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsExprOf getFirstIsExprOfIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class IsDefinitionOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsDefinitionOf getFirstIsDefinitionOfIncidence();

	/**
	 * @return the first edge of class IsDefinitionOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsDefinitionOf getFirstIsDefinitionOfIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>var</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsVarOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsVarOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsVarOf add_var(de.uni_koblenz.jgralab.greql2.schema.Variable vertex);
		/**
		 * removes all var adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsVarOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Variable> remove_var();
		/**
		 * removes the given vertex as <code>var</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsVarOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_var(de.uni_koblenz.jgralab.greql2.schema.Variable vertex);
		/**
		 * @return the vertex to this one with the rolename 'var' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsVarOf</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Variable get_var();
		/**
		 * adds the given vertex as <code>expr</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsExprOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsExprOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsExprOf add_expr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * removes all expr adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsExprOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Expression> remove_expr();
		/**
		 * removes the given vertex as <code>expr</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsExprOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_expr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * @return the vertex to this one with the rolename 'expr' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsExprOf</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Expression get_expr();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsVarOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsVarOf> getIsVarOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsVarOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsVarOf> getIsVarOfIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsExprOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsExprOf> getIsExprOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsExprOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsExprOf> getIsExprOfIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsDefinitionOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsDefinitionOf> getIsDefinitionOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsDefinitionOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsDefinitionOf> getIsDefinitionOfIncidences(EdgeDirection direction);
}
