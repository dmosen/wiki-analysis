/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml;

public interface NamedElement extends de.uni_koblenz.jgralab.Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.gretl.varro.UMLSchema.instance().vc_uml_NamedElement;

	public java.lang.String get_name();

	public void set_name(java.lang.String _name);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.NamedElement vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.varro.uml.NamedElement getNextNamedElement();
}
