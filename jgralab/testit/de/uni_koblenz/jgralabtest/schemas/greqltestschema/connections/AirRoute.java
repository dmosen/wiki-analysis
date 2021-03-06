/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport;
/**
 * FromVertexClass: junctions.Airport
 * FromRoleName : 
 * ToVertexClass: junctions.Airport
 * ToRoleName : 
 */

public interface AirRoute extends de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteSchema.instance().ec_connections_AirRoute;

	public java.lang.String get_routeName();

	public void set_routeName(java.lang.String _routeName);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute getNextAirRouteInGraph();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute getNextAirRouteIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute getNextAirRouteIncidence(EdgeDirection orientation);

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionIncidence(EdgeDirection orientation);
	public Airport getAlpha();
	public Airport getOmega();
}
