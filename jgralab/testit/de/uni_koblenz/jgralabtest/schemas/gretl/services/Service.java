/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.services;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Service extends de.uni_koblenz.jgralab.Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.gretl.services.ServiceSchema.instance().vc_Service;

	public java.lang.String get_name();

	public void set_name(java.lang.String _name);

	public java.lang.String get_owner();

	public void set_owner(java.lang.String _owner);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.services.Service vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Service getNextService();

	/**
	 * @return the first edge of class Calls at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls getFirstCallsIncidence();

	/**
	 * @return the first edge of class Calls at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls getFirstCallsIncidence(EdgeDirection orientation);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Calls or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls> getCallsIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Calls.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls> getCallsIncidences(EdgeDirection direction);
}
