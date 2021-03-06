/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Column extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSSchema.instance().vc_Column;

	public java.lang.String get_type();

	public void set_type(java.lang.String _type);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column getNextColumn();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement getNextRModelElement();

	/**
	 * @return the first edge of class IsIdentifiedBy at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIdentifiedBy getFirstIsIdentifiedByIncidence();

	/**
	 * @return the first edge of class IsIdentifiedBy at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIdentifiedBy getFirstIsIdentifiedByIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class IsIn at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn getFirstIsInIncidence();

	/**
	 * @return the first edge of class IsIn at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn getFirstIsInIncidence(EdgeDirection orientation);

	/**
	 * @return the first edge of class HasColumn at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn getFirstHasColumnIncidence();

	/**
	 * @return the first edge of class HasColumn at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn getFirstHasColumnIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>foreignKey</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn add_foreignKey(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey vertex);
		/**
		 * removes all foreignKey adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey> remove_foreignKey();
		/**
		 * removes the given vertex as <code>foreignKey</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_foreignKey(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey vertex);
		/**
		 * @return the vertex to this one with the rolename 'foreignKey' 
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn</code> edge), or null if no such vertex exists
		 */
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey get_foreignKey();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsIdentifiedBy or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIdentifiedBy> getIsIdentifiedByIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsIdentifiedBy.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIdentifiedBy> getIsIdentifiedByIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsIn or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn> getIsInIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type IsIn.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn> getIsInIncidences(EdgeDirection direction);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasColumn or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn> getHasColumnIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type HasColumn.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn> getHasColumnIncidences(EdgeDirection direction);
}
