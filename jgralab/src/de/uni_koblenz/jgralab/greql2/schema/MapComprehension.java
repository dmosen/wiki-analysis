/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface MapComprehension extends de.uni_koblenz.jgralab.greql2.schema.Comprehension {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().vc_MapComprehension;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Comprehension vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Comprehension getNextComprehension();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Expression vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Expression getNextExpression();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex getNextGreql2Vertex();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.MapComprehension vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.MapComprehension getNextMapComprehension();

	/**
	 * @return the first edge of class IsKeyExprOfComprehension at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsKeyExprOfComprehension getFirstIsKeyExprOfComprehensionIncidence();

	/**
	 * @return the first edge of class IsKeyExprOfComprehension at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsKeyExprOfComprehension getFirstIsKeyExprOfComprehensionIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class IsValueExprOfComprehension at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsValueExprOfComprehension getFirstIsValueExprOfComprehensionIncidence();

	/**
	 * @return the first edge of class IsValueExprOfComprehension at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsValueExprOfComprehension getFirstIsValueExprOfComprehensionIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>compDecl</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsCompDeclOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsCompDeclOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsCompDeclOf add_compDecl(de.uni_koblenz.jgralab.greql2.schema.Declaration vertex);
		/**
		 * removes all compDecl adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsCompDeclOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Declaration> remove_compDecl();
		/**
		 * removes the given vertex as <code>compDecl</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsCompDeclOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_compDecl(de.uni_koblenz.jgralab.greql2.schema.Declaration vertex);
		/**
		 * @return the vertex to this one with the rolename 'compDecl' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsCompDeclOf</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Declaration get_compDecl();
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
		 * adds the given vertex as <code>keyExpr</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsKeyExprOfComprehension</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsKeyExprOfComprehension</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsKeyExprOfComprehension add_keyExpr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * removes all keyExpr adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsKeyExprOfComprehension</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Expression> remove_keyExpr();
		/**
		 * removes the given vertex as <code>keyExpr</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsKeyExprOfComprehension</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_keyExpr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * @return the vertex to this one with the rolename 'keyExpr' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsKeyExprOfComprehension</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Expression get_keyExpr();
		/**
		 * adds the given vertex as <code>valueExpr</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsValueExprOfComprehension</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsValueExprOfComprehension</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsValueExprOfComprehension add_valueExpr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * removes all valueExpr adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsValueExprOfComprehension</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Expression> remove_valueExpr();
		/**
		 * removes the given vertex as <code>valueExpr</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsValueExprOfComprehension</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_valueExpr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * @return the vertex to this one with the rolename 'valueExpr' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsValueExprOfComprehension</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Expression get_valueExpr();
		/**
		 * adds the given vertex as <code>compResultDef</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsCompResultDefOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsCompResultDefOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsCompResultDefOf add_compResultDef(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * removes all compResultDef adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsCompResultDefOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Expression> remove_compResultDef();
		/**
		 * removes the given vertex as <code>compResultDef</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsCompResultDefOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_compResultDef(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * @return the vertex to this one with the rolename 'compResultDef' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsCompResultDefOf</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Expression get_compResultDef();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsKeyExprOfComprehension or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsKeyExprOfComprehension> getIsKeyExprOfComprehensionIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsKeyExprOfComprehension.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsKeyExprOfComprehension> getIsKeyExprOfComprehensionIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsValueExprOfComprehension or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsValueExprOfComprehension> getIsValueExprOfComprehensionIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsValueExprOfComprehension.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsValueExprOfComprehension> getIsValueExprOfComprehensionIncidences(EdgeDirection direction);
}
