/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package schemas.categoryschema;

import de.uni_koblenz.jgralab.EdgeDirection;

import schemas.categoryschema.Category;
/**
 * FromVertexClass: Category
 * FromRoleName : category
 * ToVertexClass: Category
 * ToRoleName : subcategory
 */

public interface Subcategory extends de.uni_koblenz.jgralab.Edge {

	public static final de.uni_koblenz.jgralab.schema.EdgeClass EC = schemas.categoryschema.CategorySchema.instance().ec_Subcategory;

	public boolean is_backwardArc();

	public void set_backwardArc(boolean _backwardArc);

	public boolean is_blacklisted();

	public void set_blacklisted(boolean _blacklisted);

	public java.lang.String get_comment();

	public void set_comment(java.lang.String _comment);

	public boolean is_excluded();

	public void set_excluded(boolean _excluded);

	/**
	 * @return the next schemas.categoryschema.Subcategory edge in the global edge sequence
	 */
	public schemas.categoryschema.Subcategory getNextSubcategoryInGraph();

	/**
	 * @return the next edge of class schemas.categoryschema.Subcategory at the "this" vertex
	 */
	public schemas.categoryschema.Subcategory getNextSubcategoryIncidence();

	/**
	 * @return the next edge of class schemas.categoryschema.Subcategory at the "this" vertex
	 * @param orientation the orientation of the edge
	 */
	public schemas.categoryschema.Subcategory getNextSubcategoryIncidence(EdgeDirection orientation);
	public Category getAlpha();
	public Category getOmega();
}
