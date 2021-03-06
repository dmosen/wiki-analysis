/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.algolib.simple;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface SimpleVertex extends de.uni_koblenz.jgralab.Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleSchema.instance().vc_SimpleVertex;

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex getNextSimpleVertex();

	/**
	 * @return the first edge of class SimpleEdge at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getFirstSimpleEdgeIncidence();

	/**
	 * @return the first edge of class SimpleEdge at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getFirstSimpleEdgeIncidence(EdgeDirection orientation);
		/**
		 * adds the given vertex as <code>alphaVertex</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge add_alphaVertex(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex vertex);
		/**
		 * removes all alphaVertex adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex> remove_alphaVertex();
		/**
		 * removes the given vertex as <code>alphaVertex</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_alphaVertex(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'alphaVertex'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex> get_alphaVertex();
		/**
		 * adds the given vertex as <code>omegaVertex</code> to this vertex, i.e. creates an
		 * <code>de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge</code> edge from this vertex to the given 
		 * one and returns the created edge.
		 * @return  a newly created edge of type <code>de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge</code>
		 *          between this vertex and the given one.
		 */
		public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge add_omegaVertex(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex vertex);
		/**
		 * removes all omegaVertex adjacences to all vertices by 
		 * deleting the <code>de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge</code> edges of this vertex to 
		 * all other ones, but doesn't delete those vertices.
		 *
		 * @return the adjacent vertices prior to removal of incidences
		 */
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex> remove_omegaVertex();
		/**
		 * removes the given vertex as <code>omegaVertex</code> from this vertex, i.e. 
		 * deletes the <code>de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge</code> edge connections of this vertex with 
		 * the given one.
		 */
		public boolean remove_omegaVertex(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex vertex);
		/**
		 * @return an Iterable of all vertices adjacent to this one with the rolename 'omegaVertex'
		 *         (connected with a <code>de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge</code> edge).
		 */
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex> get_omegaVertex();

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type SimpleEdge or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge> getSimpleEdgeIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type SimpleEdge.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge> getSimpleEdgeIncidences(EdgeDirection direction);
}
