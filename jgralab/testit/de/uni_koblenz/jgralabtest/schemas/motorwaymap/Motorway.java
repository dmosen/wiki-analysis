/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.motorwaymap;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Motorway extends de.uni_koblenz.jgralab.Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.motorwaymap.MotorwayMapSchema.instance().vc_Motorway;

	public double get_length();

	public void set_length(double _length);

	public java.lang.String get_name();

	public void set_name(java.lang.String _name);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.motorwaymap.Motorway vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Motorway getNextMotorway();

	/**
	 * @return the first edge of class Offers at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers getFirstOffersIncidence();

	/**
	 * @return the first edge of class Offers at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers getFirstOffersIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class Exit at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit getFirstExitIncidence();

	/**
	 * @return the first edge of class Exit at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit getFirstExitIncidence(EdgeDirection orientation);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Offers or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers> getOffersIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Offers.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Offers> getOffersIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Exit or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit> getExitIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Exit.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit> getExitIncidences(EdgeDirection direction);
}
