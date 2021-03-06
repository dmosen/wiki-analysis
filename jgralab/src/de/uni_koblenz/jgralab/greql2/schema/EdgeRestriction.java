/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface EdgeRestriction extends de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().vc_EdgeRestriction;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.EdgeRestriction vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.EdgeRestriction getNextEdgeRestriction();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex getNextGreql2Vertex();

	/**
	 * @return the first edge of class IsEdgeRestrOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsEdgeRestrOf getFirstIsEdgeRestrOfIncidence();

	/**
	 * @return the first edge of class IsEdgeRestrOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsEdgeRestrOf getFirstIsEdgeRestrOfIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class IsBooleanPredicateOfEdgeRestriction at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsBooleanPredicateOfEdgeRestriction getFirstIsBooleanPredicateOfEdgeRestrictionIncidence();

	/**
	 * @return the first edge of class IsBooleanPredicateOfEdgeRestriction at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsBooleanPredicateOfEdgeRestriction getFirstIsBooleanPredicateOfEdgeRestrictionIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class IsRoleIdOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf getFirstIsRoleIdOfIncidence();

	/**
	 * @return the first edge of class IsRoleIdOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf getFirstIsRoleIdOfIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class IsTypeIdOf at this vertex
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf getFirstIsTypeIdOfIncidence();

	/**
	 * @return the first edge of class IsTypeIdOf at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf getFirstIsTypeIdOfIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>typeId</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf add_typeId(de.uni_koblenz.jgralab.greql2.schema.TypeId vertex);
		/**
		 * removes all typeId adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.TypeId> remove_typeId();
		/**
		 * removes the given vertex as <code>typeId</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_typeId(de.uni_koblenz.jgralab.greql2.schema.TypeId vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'typeId'
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralab.greql2.schema.TypeId> get_typeId();
		/**
		 * adds the given vertex as <code>roleId</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf add_roleId(de.uni_koblenz.jgralab.greql2.schema.RoleId vertex);
		/**
		 * removes all roleId adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.RoleId> remove_roleId();
		/**
		 * removes the given vertex as <code>roleId</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_roleId(de.uni_koblenz.jgralab.greql2.schema.RoleId vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'roleId'
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralab.greql2.schema.RoleId> get_roleId();
		/**
		 * adds the given vertex as <code>booleanPredicate</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.greql2.schema.IsBooleanPredicateOfEdgeRestriction</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.greql2.schema.IsBooleanPredicateOfEdgeRestriction</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.greql2.schema.IsBooleanPredicateOfEdgeRestriction add_booleanPredicate(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * removes all booleanPredicate adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.greql2.schema.IsBooleanPredicateOfEdgeRestriction</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Expression> remove_booleanPredicate();
		/**
		 * removes the given vertex as <code>booleanPredicate</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.greql2.schema.IsBooleanPredicateOfEdgeRestriction</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_booleanPredicate(de.uni_koblenz.jgralab.greql2.schema.Expression vertex);
		/**
		 * @return the vertex to this one with the rolename 'booleanPredicate' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.greql2.schema.IsBooleanPredicateOfEdgeRestriction</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.greql2.schema.Expression get_booleanPredicate();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsEdgeRestrOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsEdgeRestrOf> getIsEdgeRestrOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsEdgeRestrOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsEdgeRestrOf> getIsEdgeRestrOfIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsBooleanPredicateOfEdgeRestriction or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsBooleanPredicateOfEdgeRestriction> getIsBooleanPredicateOfEdgeRestrictionIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsBooleanPredicateOfEdgeRestriction.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsBooleanPredicateOfEdgeRestriction> getIsBooleanPredicateOfEdgeRestrictionIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsRoleIdOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf> getIsRoleIdOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsRoleIdOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf> getIsRoleIdOfIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsTypeIdOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf> getIsTypeIdOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsTypeIdOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf> getIsTypeIdOfIncidences(EdgeDirection direction);
}
