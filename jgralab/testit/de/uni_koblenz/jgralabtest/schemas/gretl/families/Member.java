/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.families;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Member extends de.uni_koblenz.jgralab.Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.gretl.families.FamilySchema.instance().vc_Member;

	public int get_age();

	public void set_age(int _age);

	public java.lang.String get_firstName();

	public void set_firstName(java.lang.String _firstName);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.families.Member vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.Member getNextMember();

	/**
	 * @return the first edge of class HasMother at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasMother getFirstHasMotherIncidence();

	/**
	 * @return the first edge of class HasMother at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasMother getFirstHasMotherIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class HasDaughter at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter getFirstHasDaughterIncidence();

	/**
	 * @return the first edge of class HasDaughter at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter getFirstHasDaughterIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class HasSon at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasSon getFirstHasSonIncidence();

	/**
	 * @return the first edge of class HasSon at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasSon getFirstHasSonIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class HasFather at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasFather getFirstHasFatherIncidence();

	/**
	 * @return the first edge of class HasFather at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasFather getFirstHasFatherIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>familyFather</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasFather</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasFather</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasFather add_familyFather(de.uni_koblenz.jgralabtest.schemas.gretl.families.Family vertex);
		/**
		 * removes all familyFather adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasFather</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.families.Family> remove_familyFather();
		/**
		 * removes the given vertex as <code>familyFather</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasFather</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_familyFather(de.uni_koblenz.jgralabtest.schemas.gretl.families.Family vertex);
		/**
		 * @return the vertex to this one with the rolename 'familyFather' 
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasFather</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.families.Family get_familyFather();
		/**
		 * adds the given vertex as <code>familySon</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasSon</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasSon</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasSon add_familySon(de.uni_koblenz.jgralabtest.schemas.gretl.families.Family vertex);
		/**
		 * removes all familySon adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasSon</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.families.Family> remove_familySon();
		/**
		 * removes the given vertex as <code>familySon</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasSon</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_familySon(de.uni_koblenz.jgralabtest.schemas.gretl.families.Family vertex);
		/**
		 * @return the vertex to this one with the rolename 'familySon' 
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasSon</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.families.Family get_familySon();
		/**
		 * adds the given vertex as <code>familyDaughter</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter add_familyDaughter(de.uni_koblenz.jgralabtest.schemas.gretl.families.Family vertex);
		/**
		 * removes all familyDaughter adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.families.Family> remove_familyDaughter();
		/**
		 * removes the given vertex as <code>familyDaughter</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_familyDaughter(de.uni_koblenz.jgralabtest.schemas.gretl.families.Family vertex);
		/**
		 * @return the vertex to this one with the rolename 'familyDaughter' 
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.families.Family get_familyDaughter();
		/**
		 * adds the given vertex as <code>familyMother</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasMother</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasMother</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.families.HasMother add_familyMother(de.uni_koblenz.jgralabtest.schemas.gretl.families.Family vertex);
		/**
		 * removes all familyMother adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasMother</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.families.Family> remove_familyMother();
		/**
		 * removes the given vertex as <code>familyMother</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasMother</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_familyMother(de.uni_koblenz.jgralabtest.schemas.gretl.families.Family vertex);
		/**
		 * @return the vertex to this one with the rolename 'familyMother' 
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.families.HasMother</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.families.Family get_familyMother();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasMother or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.families.HasMother> getHasMotherIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasMother.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.families.HasMother> getHasMotherIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasDaughter or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter> getHasDaughterIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasDaughter.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.families.HasDaughter> getHasDaughterIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasSon or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.families.HasSon> getHasSonIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasSon.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.families.HasSon> getHasSonIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasFather or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.families.HasFather> getHasFatherIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasFather.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.families.HasFather> getHasFatherIncidences(EdgeDirection direction);
}
