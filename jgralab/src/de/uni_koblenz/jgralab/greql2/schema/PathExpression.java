/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface PathExpression extends de.uni_koblenz.jgralab.greql2.schema.Expression {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().vc_PathExpression;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Expression vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Expression getNextExpression();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex getNextGreql2Vertex();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.PathExpression vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.PathExpression getNextPathExpression();

	/**
	 * @return the first edge of class IsStartExprOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsStartExprOf getFirstIsStartExprOfIncidence();

	/**
	 * @return the first edge of class IsStartExprOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsStartExprOf getFirstIsStartExprOfIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class IsPathOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsPathOf getFirstIsPathOfIncidence();

	/**
	 * @return the first edge of class IsPathOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsPathOf getFirstIsPathOfIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class IsTargetExprOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTargetExprOf getFirstIsTargetExprOfIncidence();

	/**
	 * @return the first edge of class IsTargetExprOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTargetExprOf getFirstIsTargetExprOfIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>typeRestr</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfExpression</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfExpression</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfExpression add_typeRestr(de.uni_koblenz.jgralab.greql2.schema.TypeId vertex);
		/**
		 * removes all typeRestr adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfExpression</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.TypeId> remove_typeRestr();
		/**
		 * removes the given vertex as <code>typeRestr</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfExpression</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_typeRestr(de.uni_koblenz.jgralab.greql2.schema.TypeId vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'typeRestr'
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfExpression</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralab.greql2.schema.TypeId> get_typeRestr();
		/**
		 * adds the given vertex as <code>path</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsPathOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsPathOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsPathOf add_path(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * removes all path adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsPathOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Expression> remove_path();
		/**
		 * removes the given vertex as <code>path</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsPathOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_path(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * @return the vertex to this one with the rolename 'path' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsPathOf</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Expression get_path();
		/**
		 * adds the given vertex as <code>startExpr</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsStartExprOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsStartExprOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsStartExprOf add_startExpr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * removes all startExpr adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsStartExprOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Expression> remove_startExpr();
		/**
		 * removes the given vertex as <code>startExpr</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsStartExprOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_startExpr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * @return the vertex to this one with the rolename 'startExpr' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsStartExprOf</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Expression get_startExpr();
		/**
		 * adds the given vertex as <code>targetExpr</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsTargetExprOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsTargetExprOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsTargetExprOf add_targetExpr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * removes all targetExpr adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsTargetExprOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Expression> remove_targetExpr();
		/**
		 * removes the given vertex as <code>targetExpr</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsTargetExprOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_targetExpr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * @return the vertex to this one with the rolename 'targetExpr' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsTargetExprOf</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Expression get_targetExpr();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsStartExprOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsStartExprOf> getIsStartExprOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsStartExprOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsStartExprOf> getIsStartExprOfIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsPathOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsPathOf> getIsPathOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsPathOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsPathOf> getIsPathOfIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsTargetExprOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsTargetExprOf> getIsTargetExprOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsTargetExprOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsTargetExprOf> getIsTargetExprOfIncidences(EdgeDirection direction);
}
