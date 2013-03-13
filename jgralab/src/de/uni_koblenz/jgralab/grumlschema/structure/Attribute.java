/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.grumlschema.structure;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Attribute extends de.uni_koblenz.jgralab.Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralab.grumlschema.GrumlSchema.instance().vc_structure_Attribute;

	public java.lang.String get_defaultValue();

	public void set_defaultValue(java.lang.String _defaultValue);

	public java.lang.String get_name();

	public void set_name(java.lang.String _name);

	/**
	 * @return the next de.uni_koblenz.jgralab.grumlschema.structure.Attribute vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.grumlschema.structure.Attribute getNextAttribute();

	/**
	 * @return the first edge of class HasDomain at this vertex
	 */
	public de.uni_koblenz.jgralab.grumlschema.structure.HasDomain getFirstHasDomainIncidence();

	/**
	 * @return the first edge of class HasDomain at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.grumlschema.structure.HasDomain getFirstHasDomainIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class HasAttribute at this vertex
	 */
	public de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute getFirstHasAttributeIncidence();

	/**
	 * @return the first edge of class HasAttribute at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute getFirstHasAttributeIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>domain</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.grumlschema.structure.HasDomain</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.grumlschema.structure.HasDomain</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.grumlschema.structure.HasDomain add_domain(de.uni_koblenz.jgralab.grumlschema.domains.Domain vertex);
		/**
		 * removes all domain adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.grumlschema.structure.HasDomain</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.grumlschema.domains.Domain> remove_domain();
		/**
		 * removes the given vertex as <code>domain</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.grumlschema.structure.HasDomain</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_domain(de.uni_koblenz.jgralab.grumlschema.domains.Domain vertex);
		/**
		 * @return the vertex to this one with the rolename 'domain' 
		 *         (connected with a <code>de.uni_koblenz.jgralab.grumlschema.structure.HasDomain</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralab.grumlschema.domains.Domain get_domain();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasDomain or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.HasDomain> getHasDomainIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasDomain.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.HasDomain> getHasDomainIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasAttribute or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute> getHasAttributeIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasAttribute.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.HasAttribute> getHasAttributeIncidences(EdgeDirection direction);
}
