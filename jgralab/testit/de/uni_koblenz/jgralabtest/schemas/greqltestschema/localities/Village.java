/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities;

public interface Village extends de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteSchema.instance().vc_localities_Village;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement getNextNamedElement();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality getNextLocality();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village getNextVillage();
		/**
		 * adds the given vertex as <code>crossroads</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad add_crossroads(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad vertex);
		/**
		 * removes all crossroads adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad> remove_crossroads();
		/**
		 * removes the given vertex as <code>crossroads</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_crossroads(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'crossroads'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad> get_crossroads();
}
