/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Medium extends de.uni_koblenz.jgralab.Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.SimpleLibrarySchema.instance().vc_Medium;

	public java.lang.String get_title();

	public void set_title(java.lang.String _title);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Medium vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Medium getNextMedium();

	/**
	 * @return the first edge of class HasMedia at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.HasMedia getFirstHasMediaIncidence();

	/**
	 * @return the first edge of class HasMedia at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.HasMedia getFirstHasMediaIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class Loans at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans getFirstLoansIncidence();

	/**
	 * @return the first edge of class Loans at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans getFirstLoansIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>library</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.HasMedia</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.HasMedia</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.HasMedia add_library(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Library vertex);
		/**
		 * removes all library adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.HasMedia</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Library> remove_library();
		/**
		 * removes the given vertex as <code>library</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.HasMedia</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_library(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Library vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'library'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.HasMedia</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Library> get_library();
		/**
		 * adds the given vertex as <code>user</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans add_user(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.User vertex);
		/**
		 * removes all user adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.User> remove_user();
		/**
		 * removes the given vertex as <code>user</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_user(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.User vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'user'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.User> get_user();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasMedia or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.HasMedia> getHasMediaIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasMedia.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.HasMedia> getHasMediaIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Loans or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans> getLoansIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Loans.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans> getLoansIncidences(EdgeDirection direction);
}
