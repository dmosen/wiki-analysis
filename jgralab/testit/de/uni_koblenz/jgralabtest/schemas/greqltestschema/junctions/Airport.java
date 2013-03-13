/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Airport extends de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteSchema.instance().vc_junctions_Airport;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement getNextNamedElement();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport getNextAirport();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction getNextJunction();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality getNextLocality();

	/**
	 * @return the first edge of class AirRoute at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute getFirstAirRouteIncidence();

	/**
	 * @return the first edge of class AirRoute at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute getFirstAirRouteIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>src</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection add_src(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction vertex);
		/**
		 * removes all src adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction> remove_src();
		/**
		 * removes the given vertex as <code>src</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_src(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'src'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction> get_src();
		/**
		 * adds the given vertex as <code>dst</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection add_dst(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction vertex);
		/**
		 * removes all dst adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction> remove_dst();
		/**
		 * removes the given vertex as <code>dst</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_dst(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'dst'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction> get_dst();
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

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type AirRoute or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute> getAirRouteIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type AirRoute.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute> getAirRouteIncidences(EdgeDirection direction);
}
