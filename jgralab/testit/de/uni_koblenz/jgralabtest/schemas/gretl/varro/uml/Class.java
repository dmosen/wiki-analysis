/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Class extends de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.NamedElement {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.gretl.varro.UMLSchema.instance().vc_uml_Class;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Class vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Class getNextClass();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.NamedElement vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.NamedElement getNextNamedElement();

	/**
	 * @return the first edge of class ContainsClass at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsClass getFirstContainsClassIncidence();

	/**
	 * @return the first edge of class ContainsClass at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsClass getFirstContainsClassIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class HasType at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasType getFirstHasTypeIncidence();

	/**
	 * @return the first edge of class HasType at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasType getFirstHasTypeIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class HasSub at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSub getFirstHasSubIncidence();

	/**
	 * @return the first edge of class HasSub at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSub getFirstHasSubIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class HasSuper at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSuper getFirstHasSuperIncidence();

	/**
	 * @return the first edge of class HasSuper at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSuper getFirstHasSuperIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class HasAttribute at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAttribute getFirstHasAttributeIncidence();

	/**
	 * @return the first edge of class HasAttribute at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAttribute getFirstHasAttributeIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>feature</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAttribute</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAttribute</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAttribute add_feature(de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Attribute vertex);
		/**
		 * removes all feature adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAttribute</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Attribute> remove_feature();
		/**
		 * removes the given vertex as <code>feature</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAttribute</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_feature(de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Attribute vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'feature'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAttribute</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Attribute> get_feature();
		/**
		 * adds the given vertex as <code>elemOwner</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsClass</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsClass</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsClass add_elemOwner(de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Package vertex);
		/**
		 * removes all elemOwner adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsClass</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Package> remove_elemOwner();
		/**
		 * removes the given vertex as <code>elemOwner</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsClass</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_elemOwner(de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Package vertex);
		/**
		 * @return the vertex to this one with the rolename 'elemOwner' 
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsClass</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Package get_elemOwner();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type ContainsClass or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsClass> getContainsClassIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type ContainsClass.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsClass> getContainsClassIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasType or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasType> getHasTypeIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasType.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasType> getHasTypeIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasSub or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSub> getHasSubIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasSub.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSub> getHasSubIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasSuper or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSuper> getHasSuperIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasSuper.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasSuper> getHasSuperIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasAttribute or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAttribute> getHasAttributeIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasAttribute.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.HasAttribute> getHasAttributeIncidences(EdgeDirection direction);
}
