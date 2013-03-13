/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Schema;
import de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table;
/**
 * FromVertexClass: Schema
 * FromRoleName : schema
 * ToVertexClass: Table
 * ToRoleName : table
 */

public interface HasTable extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSSchema.instance().ec_HasTable;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable getNextHasTableInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable getNextHasTableIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable getNextHasTableIncidence(EdgeDirection orientation);
	public Schema getAlpha();
	public Table getOmega();
}
