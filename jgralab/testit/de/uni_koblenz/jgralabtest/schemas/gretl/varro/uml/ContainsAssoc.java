/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Assoc;
import de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.Package;
/**
 * FromVertexClass: uml.Package
 * FromRoleName : elemOwner
 * ToVertexClass: uml.Assoc
 * ToRoleName : 
 */

public interface ContainsAssoc extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.gretl.varro.UMLSchema.instance().ec_uml_ContainsAssoc;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsAssoc edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsAssoc getNextContainsAssocInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsAssoc at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsAssoc getNextContainsAssocIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsAssoc at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.ContainsAssoc getNextContainsAssocIncidence(EdgeDirection orientation);
	public Package getAlpha();
	public Assoc getOmega();
}
