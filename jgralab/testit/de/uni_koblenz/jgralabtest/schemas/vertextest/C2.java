/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface C2 extends de.uni_koblenz.jgralabtest.schemas.vertextest.C {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestSchema.instance().vc_C2;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.A getNextA();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.vertextest.C vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.C getNextC();

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.vertextest.C2 vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.C2 getNextC2();

	/**
	 * @return the first edge of class J at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.J getFirstJIncidence();

	/**
	 * @return the first edge of class J at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.vertextest.J getFirstJIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>sourceI</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.vertextest.I</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.vertextest.I</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.vertextest.I add_sourceI(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex);
		/**
		 * removes all sourceI adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.I</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> remove_sourceI();
		/**
		 * removes the given vertex as <code>sourceI</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.I</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_sourceI(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'sourceI'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.vertextest.I</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> get_sourceI();
		/**
		 * adds the given vertex as <code>u</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.vertextest.J</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.vertextest.J</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.vertextest.J add_u(de.uni_koblenz.jgralabtest.schemas.vertextest.D2 vertex);
		/**
		 * removes all u adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.J</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D2> remove_u();
		/**
		 * removes the given vertex as <code>u</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.J</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_u(de.uni_koblenz.jgralabtest.schemas.vertextest.D2 vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'u'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.vertextest.J</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D2> get_u();
		/**
		 * adds the given vertex as <code>z</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.vertextest.G</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.vertextest.G</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.vertextest.G add_z(de.uni_koblenz.jgralabtest.schemas.vertextest.D vertex);
		/**
		 * removes all z adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.G</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> remove_z();
		/**
		 * removes the given vertex as <code>z</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.G</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_z(de.uni_koblenz.jgralabtest.schemas.vertextest.D vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'z'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.vertextest.G</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> get_z();
		/**
		 * adds the given vertex as <code>w</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.vertextest.H</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.vertextest.H</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.vertextest.H add_w(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex);
		/**
		 * removes all w adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.H</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.B> remove_w();
		/**
		 * removes the given vertex as <code>w</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.H</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_w(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'w'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.vertextest.H</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.B> get_w();
		/**
		 * adds the given vertex as <code>v</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.vertextest.I</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.vertextest.I</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.vertextest.I add_v(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex);
		/**
		 * removes all v adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.I</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> remove_v();
		/**
		 * removes the given vertex as <code>v</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.I</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_v(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'v'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.vertextest.I</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> get_v();
		/**
		 * adds the given vertex as <code>y</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.vertextest.F</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.vertextest.F</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.vertextest.F add_y(de.uni_koblenz.jgralabtest.schemas.vertextest.D vertex);
		/**
		 * removes all y adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.F</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> remove_y();
		/**
		 * removes the given vertex as <code>y</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.F</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_y(de.uni_koblenz.jgralabtest.schemas.vertextest.D vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'y'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.vertextest.F</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> get_y();
		/**
		 * adds the given vertex as <code>x</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.vertextest.F</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.vertextest.F</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.vertextest.F add_x(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex);
		/**
		 * removes all x adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.F</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> remove_x();
		/**
		 * removes the given vertex as <code>x</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.F</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_x(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'x'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.vertextest.E</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> get_x();
		/**
		 * adds the given vertex as <code>targetK</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.vertextest.K</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.vertextest.K</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.vertextest.K add_targetK(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex);
		/**
		 * removes all targetK adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.K</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.B> remove_targetK();
		/**
		 * removes the given vertex as <code>targetK</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.vertextest.K</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_targetK(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'targetK'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.vertextest.K</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.B> get_targetK();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type J or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.J> getJIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type J.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.J> getJIncidences(EdgeDirection direction);
}
