/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction;
/**
 * FromVertexClass: junctions.Junction
 * FromRoleName : src
 * ToVertexClass: junctions.Junction
 * ToRoleName : dst
 */

public interface Connection extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteSchema.instance().ec_connections_Connection;

	public double get_length();

	public void set_length(double _length);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getNextConnectionIncidence(EdgeDirection orientation);
	public Junction getAlpha();
	public Junction getOmega();
}
