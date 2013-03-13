/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.copy;

import de.uni_koblenz.jgralab.EdgeDirection;

import de.uni_koblenz.jgralabtest.schemas.gretl.copy.Part;
import de.uni_koblenz.jgralabtest.schemas.gretl.copy.WholePart;
/**
 * FromVertexClass: Part
 * FromRoleName : 
 * ToVertexClass: WholePart
 * ToRoleName : 
 */

public interface IsPartOfWholePart extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = de.uni_koblenz.jgralabtest.schemas.gretl.copy.CopySchema.instance().ec_IsPartOfWholePart;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart edge in the global edge sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart getNextIsPartOfWholePartInGraph();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart at the "this" vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart getNextIsPartOfWholePartIncidence();

	/**
	 * @return the next edge of class de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.copy.IsPartOfWholePart getNextIsPartOfWholePartIncidence(EdgeDirection orientation);
	public Part getAlpha();
	public WholePart getOmega();
}
