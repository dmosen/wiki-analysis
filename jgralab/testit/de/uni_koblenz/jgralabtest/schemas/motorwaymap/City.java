/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.motorwaymap;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface City extends de.uni_koblenz.jgralab.Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.motorwaymap.MotorwayMapSchema.instance().vc_City;

	public java.lang.String get_name();

	public void set_name(java.lang.String _name);

	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum get_testEnum();

	public void set_testEnum(de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum _testEnum);

	public org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> get_testList();

	public void set_testList(org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> _testList);

	public org.pcollections.PMap<java.lang.String, java.lang.String> get_testMap();

	public void set_testMap(org.pcollections.PMap<java.lang.String, java.lang.String> _testMap);

	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord get_testRecord();

	public void set_testRecord(de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord _testRecord);

	public org.pcollections.PSet<java.lang.String> get_testSet();

	public void set_testSet(org.pcollections.PSet<java.lang.String> _testSet);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.motorwaymap.City vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.City getNextCity();

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
	 * Returns an Iterable for all incidence edges of this vertex that are of type Exit or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit> getExitIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Exit.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit> getExitIncidences(EdgeDirection direction);
}
