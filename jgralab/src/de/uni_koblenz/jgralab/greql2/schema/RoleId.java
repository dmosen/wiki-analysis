/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface RoleId extends de.uni_koblenz.jgralab.greql2.schema.TypeOrRoleId {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralab.greql2.schema.Greql2Schema.instance().vc_RoleId;

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Expression vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Expression getNextExpression();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex getNextGreql2Vertex();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.Identifier vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.Identifier getNextIdentifier();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.RoleId vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.RoleId getNextRoleId();

	/**
	 * @return the next de.uni_koblenz.jgralab.greql2.schema.TypeOrRoleId vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.greql2.schema.TypeOrRoleId getNextTypeOrRoleId();

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
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsRoleIdOf or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf> getIsRoleIdOfIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsRoleIdOf.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsRoleIdOf> getIsRoleIdOfIncidences(EdgeDirection direction);
}
