/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestVertex;
/**
 * FromVertexClass: TestVertex
 * FromRoleName : start
 * ToVertexClass: TestVertex
 * ToRoleName : end
 */

public interface TestEdge extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.DefaultValueTestSchema.instance().ec_TestEdge;

	public boolean is_boolEdge();

	public void set_boolEdge(boolean _boolEdge);

	public org.pcollections.PVector<org.pcollections.PVector<java.lang.Boolean>> get_complexListEdge();

	public void set_complexListEdge(org.pcollections.PVector<org.pcollections.PVector<java.lang.Boolean>> _complexListEdge);

	public org.pcollections.PMap<org.pcollections.PVector<java.lang.Boolean>, org.pcollections.PSet<java.lang.Boolean>> get_complexMapEdge();

	public void set_complexMapEdge(org.pcollections.PMap<org.pcollections.PVector<java.lang.Boolean>, org.pcollections.PSet<java.lang.Boolean>> _complexMapEdge);

	public org.pcollections.PSet<org.pcollections.PSet<java.lang.Boolean>> get_complexSetEdge();

	public void set_complexSetEdge(org.pcollections.PSet<org.pcollections.PSet<java.lang.Boolean>> _complexSetEdge);

	public double get_doubleEdge();

	public void set_doubleEdge(double _doubleEdge);

	public de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEnumDomain get_enumEdge();

	public void set_enumEdge(de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEnumDomain _enumEdge);

	public int get_intEdge();

	public void set_intEdge(int _intEdge);

	public org.pcollections.PVector<java.lang.Boolean> get_listEdge();

	public void set_listEdge(org.pcollections.PVector<java.lang.Boolean> _listEdge);

	public long get_longEdge();

	public void set_longEdge(long _longEdge);

	public org.pcollections.PMap<java.lang.Integer, java.lang.Boolean> get_mapEdge();

	public void set_mapEdge(org.pcollections.PMap<java.lang.Integer, java.lang.Boolean> _mapEdge);

	public de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestRecordDomain get_recordEdge();

	public void set_recordEdge(de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestRecordDomain _recordEdge);

	public org.pcollections.PSet<java.lang.Boolean> get_setEdge();

	public void set_setEdge(org.pcollections.PSet<java.lang.Boolean> _setEdge);

	public java.lang.String get_stringEdge();

	public void set_stringEdge(java.lang.String _stringEdge);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEdge edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEdge getNextTestEdgeInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEdge at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEdge getNextTestEdgeIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEdge at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEdge getNextTestEdgeIncidence(EdgeDirection orientation);
	public TestVertex getAlpha();
	public TestVertex getOmega();
}
