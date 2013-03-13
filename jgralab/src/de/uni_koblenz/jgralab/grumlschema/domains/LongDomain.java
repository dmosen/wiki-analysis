/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.grumlschema.domains;

public interface LongDomain extends de.uni_koblenz.jgralab.grumlschema.domains.BasicDomain {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralab.grumlschema.GrumlSchema.instance().vc_domains_LongDomain;

	/**
	 * @return the next de.uni_koblenz.jgralab.grumlschema.domains.BasicDomain vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.grumlschema.domains.BasicDomain getNextBasicDomain();

	/**
	 * @return the next de.uni_koblenz.jgralab.grumlschema.domains.Domain vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.grumlschema.domains.Domain getNextDomain();

	/**
	 * @return the next de.uni_koblenz.jgralab.grumlschema.domains.LongDomain vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.grumlschema.domains.LongDomain getNextLongDomain();

	/**
	 * @return the next de.uni_koblenz.jgralab.grumlschema.structure.NamedElement vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralab.grumlschema.structure.NamedElement getNextNamedElement();
		/**
		 * adds the given vertex as <code>comment</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralab.grumlschema.structure.Annotates</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralab.grumlschema.structure.Annotates</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralab.grumlschema.structure.Annotates add_comment(de.uni_koblenz.jgralab.grumlschema.structure.Comment vertex);
		/**
		 * removes all comment adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralab.grumlschema.structure.Annotates</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralab.grumlschema.structure.Comment> remove_comment();
		/**
		 * removes the given vertex as <code>comment</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralab.grumlschema.structure.Annotates</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_comment(de.uni_koblenz.jgralab.grumlschema.structure.Comment vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'comment'
		 *         (connected with a <code>de.uni_koblenz.jgralab.grumlschema.structure.Annotates</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralab.grumlschema.structure.Comment> get_comment();
}
