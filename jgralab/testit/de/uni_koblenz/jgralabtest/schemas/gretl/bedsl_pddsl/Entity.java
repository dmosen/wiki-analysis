/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Entity extends de.uni_koblenz.jgralab.Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.IntegratedBedslPddslSchema.instance().vc_Entity;

	public java.lang.String get_name();

	public void set_name(java.lang.String _name);

	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.TransformationSource get_source();

	public void set_source(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.TransformationSource _source);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity getNextEntity();

	/**
	 * @return the first edge of class HasSupertype at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype getFirstHasSupertypeIncidence();

	/**
	 * @return the first edge of class HasSupertype at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype getFirstHasSupertypeIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class References at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.References getFirstReferencesIncidence();

	/**
	 * @return the first edge of class References at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.References getFirstReferencesIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class HasAttribute at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute getFirstHasAttributeIncidence();

	/**
	 * @return the first edge of class HasAttribute at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute getFirstHasAttributeIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>superType</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype add_superType(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity vertex);
		/**
		 * removes all superType adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity> remove_superType();
		/**
		 * removes the given vertex as <code>superType</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_superType(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity vertex);
		/**
		 * @return the vertex to this one with the rolename 'superType' 
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity get_superType();
		/**
		 * adds the given vertex as <code>subType</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype add_subType(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity vertex);
		/**
		 * removes all subType adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity> remove_subType();
		/**
		 * removes the given vertex as <code>subType</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_subType(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'subType'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity> get_subType();
		/**
		 * adds the given vertex as <code>attribute</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute add_attribute(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Attribute vertex);
		/**
		 * removes all attribute adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Attribute> remove_attribute();
		/**
		 * removes the given vertex as <code>attribute</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_attribute(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Attribute vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'attribute'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Attribute> get_attribute();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasSupertype or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype> getHasSupertypeIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasSupertype.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasSupertype> getHasSupertypeIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type References or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.References> getReferencesIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type References.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.References> getReferencesIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasAttribute or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute> getHasAttributeIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasAttribute.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.HasAttribute> getHasAttributeIncidences(EdgeDirection direction);
}
