/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.grumlschema.structure;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralab.grumlschema.domains.Domain;
import de.uni_koblenz.jgralab.grumlschema.structure.Attribute;
/**
 * FromVertexClass: structure.Attribute
 * FromRoleName : 
 * ToVertexClass: domains.Domain
 * ToRoleName : domain
 */

public interface HasDomain extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralab.grumlschema.GrumlSchema.instance().ec_structure_HasDomain;

	/**
	 * @return the next de.uni_koblenz.jgralab.grumlschema.structure.HasDomain edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralab.grumlschema.structure.HasDomain getNextHasDomainInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.grumlschema.structure.HasDomain at the "this" vertex
	 */
	public de.uni_koblenz.jgralab.grumlschema.structure.HasDomain getNextHasDomainIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralab.grumlschema.structure.HasDomain at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralab.grumlschema.structure.HasDomain getNextHasDomainIncidence(EdgeDirection orientation);
	public Attribute getAlpha();
	public Domain getOmega();
}
