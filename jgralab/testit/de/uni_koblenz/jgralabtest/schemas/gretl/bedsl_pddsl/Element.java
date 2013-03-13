/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl;

public interface Element extends de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.IntegratedBedslPddslSchema.instance().vc_Element;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Element vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Element getNextElement();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl_pddsl.Entity getNextEntity();
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
}
