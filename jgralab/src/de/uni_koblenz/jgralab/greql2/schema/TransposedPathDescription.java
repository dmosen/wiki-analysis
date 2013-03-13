/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface TransposedPathDescription extends de.uni_koblenz.jgralab.greql2.schema.PathDescription {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().vc_TransposedPathDescription;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Expression vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Expression getNextExpression();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex getNextGreql2Vertex();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.PathDescription vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.PathDescription getNextPathDescription();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.TransposedPathDescription vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.TransposedPathDescription getNextTransposedPathDescription();

	/**
	 * @return the first edge of class IsTransposedPathOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTransposedPathOf getFirstIsTransposedPathOfIncidence();

	/**
	 * @return the first edge of class IsTransposedPathOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTransposedPathOf getFirstIsTransposedPathOfIncidence(EdgeDirection orientation);
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
		 * adds the given vertex as <code>goalRestr</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsGoalRestrOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsGoalRestrOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsGoalRestrOf add_goalRestr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * removes all goalRestr adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsGoalRestrOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Expression> remove_goalRestr();
		/**
		 * removes the given vertex as <code>goalRestr</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsGoalRestrOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_goalRestr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * @return the vertex to this one with the rolename 'goalRestr' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsGoalRestrOf</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Expression get_goalRestr();
		/**
		 * adds the given vertex as <code>startRestr</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsStartRestrOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsStartRestrOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsStartRestrOf add_startRestr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * removes all startRestr adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsStartRestrOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Expression> remove_startRestr();
		/**
		 * removes the given vertex as <code>startRestr</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsStartRestrOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_startRestr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * @return the vertex to this one with the rolename 'startRestr' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsStartRestrOf</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Expression get_startRestr();
		/**
		 * adds the given vertex as <code>transposedPath</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsTransposedPathOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsTransposedPathOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsTransposedPathOf add_transposedPath(de.uni_koblenz.jgralab.greql2.schema.PathDescription vertex);
		/**
		 * removes all transposedPath adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsTransposedPathOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.PathDescription> remove_transposedPath();
		/**
		 * removes the given vertex as <code>transposedPath</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsTransposedPathOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_transposedPath(de.uni_koblenz.jgralab.greql2.schema.PathDescription vertex);
		/**
		 * @return the vertex to this one with the rolename 'transposedPath' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsTransposedPathOf</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.PathDescription get_transposedPath();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsTransposedPathOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsTransposedPathOf> getIsTransposedPathOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsTransposedPathOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsTransposedPathOf> getIsTransposedPathOfIncidences(EdgeDirection direction);
}
