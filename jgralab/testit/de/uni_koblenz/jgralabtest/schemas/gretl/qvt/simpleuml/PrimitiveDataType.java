/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml;

public interface PrimitiveDataType extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Classifier {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.SimpleUMLSchema.instance().vc_PrimitiveDataType;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Classifier vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Classifier getNextClassifier();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.PackagableElement vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.PackagableElement getNextPackagableElement();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.PrimitiveDataType vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.PrimitiveDataType getNextPrimitiveDataType();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.UMLModelElement vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.UMLModelElement getNextUMLModelElement();
		/**
		 * adds the given vertex as <code>namespace</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Contains</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Contains</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Contains add_namespace(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Package vertex);
		/**
		 * removes all namespace adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Contains</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Package> remove_namespace();
		/**
		 * removes the given vertex as <code>namespace</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Contains</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_namespace(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Package vertex);
		/**
		 * @return the vertex to this one with the rolename 'namespace' 
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Contains</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simpleuml.Package get_namespace();
}
