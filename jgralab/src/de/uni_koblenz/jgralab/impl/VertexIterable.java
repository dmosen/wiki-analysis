/*
 * JGraLab - The Java Graph Laboratory
 * 
 * Copyright (C) 2006-2011 Institute for Software Technology
 *                         University of Koblenz-Landau, Germany
 *                         ist@uni-koblenz.de
 * 
 * For bug reports, documentation and further information, visit
 * 
 *                         http://jgralab.uni-koblenz.de
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 3 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses>.
 * 
 * Additional permission under GNU GPL version 3 section 7
 * 
 * If you modify this Program, or any covered work, by linking or combining
 * it with Eclipse (or a modified version of that program or an Eclipse
 * plugin), containing parts covered by the terms of the Eclipse Public
 * License (EPL), the licensors of this Program grant you additional
 * permission to convey the resulting work.  Corresponding Source for a
 * non-source form of such a combination shall include the source code for
 * the parts of JGraLab used as well as that of the covered work.
 */

package de.uni_koblenz.jgralab.impl;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.schema.VertexClass;

/**
 * This class provides an Iterable to iterate over vertices in a graph. One may
 * use this class to use the advanced for-loop of Java 5. Instances of this
 * class should never, and this means <b>never</b> created manually but only
 * using the methods <code>vertices(params)</code> of th graph. Every special
 * graphclass contains generated methods similar to
 * <code>vertices(params)</code> for every VertexClass that is part of the
 * GraphClass.
 * 
 * @author ist@uni-koblenz.de
 * 
 * @param <V>
 *            The type of the vertices to iterate over. To mention it again,
 *            <b>don't</b> create instances of this class directly.
 */
public class VertexIterable<V extends Vertex> implements Iterable<V> {

	/**
	 * This Iterator iterates over all vertices in a graph
	 * 
	 * @author ist@uni-koblenz.de
	 * 
	 */
	class VertexIterator implements Iterator<V> {

		/**
		 * the vertex that hasNext() retrieved and that a call of next() will
		 * return
		 */
		protected V current = null;

		/**
		 * the graph this iterator works on
		 */
		protected InternalGraph graph = null;

		protected Class<? extends Vertex> vc;
		
		protected VertexClass schemaVc;

		/**
		 * the version of the vertex list of the graph at the beginning of the
		 * iteration. This information is used to check if the vertex list has
		 * changed, the failfast-iterator will then throw an exception the next
		 * time "next()" is called
		 */
		protected long vertexListVersion;

		/**
		 * creates a new VertexIterator for the given graph
		 * 
		 * @param g
		 *            the graph to work on
		 */
		@SuppressWarnings("unchecked")
		VertexIterator(InternalGraph g, Class<? extends Vertex> vc) {
			graph = g;
			this.vc = vc;
			vertexListVersion = g.getVertexListVersion();
			current = (V) (vc == null ? graph.getFirstVertex() : graph
					.getFirstVertex(vc));
		}
		
		/**
		 * Creates a new Vertex iterator for the given <code>Graph</code>, that iterates over
		 * vertices of a given <code>VertexClass</code>
		 * @param g The <code>Graph</code>.
		 * @param vc They <code>VertexClass</code> determining which type of vertex should be
		 * iterated over.
		 */
		@SuppressWarnings("unchecked")
		VertexIterator(InternalGraph g, VertexClass vc) {
			graph = g;
			schemaVc = vc;
			vertexListVersion = g.getVertexListVersion();
			current = (V) (vc == null ? graph.getFirstVertex() : graph
					.getFirstVertex(vc));
		}

		/**
		 * @return the next vertex in the graph which mathes the conditions of
		 *         this iterator
		 */
		@SuppressWarnings("unchecked")
		public V next() {
			if (graph.isVertexListModified(vertexListVersion)) {
				throw new ConcurrentModificationException(
						"The vertex list of the graph has been modified - the iterator is not longer valid");
			}
			if (current == null) {
				throw new NoSuchElementException();
			}
			V result = current;
			current = (V) (vc == null && schemaVc == null ? current.getNextVertex() : schemaVc == null ? current
					.getNextVertex(vc) : current.getNextVertex(schemaVc));
			return result;
		}

		/**
		 * @return true iff there is at least one next vertex to retrieve
		 */
		public boolean hasNext() {
			return current != null;
		}

		/**
		 * Using the VertexIterator, it is <b>not</b> possible to remove
		 * vertices from a graph neither the iterator will recognize such a
		 * removal.
		 * 
		 * @throw UnsupportedOperationException every time the method is called
		 */
		public void remove() {
			throw new UnsupportedOperationException(
					"It is not allowed to remove vertices during iteration.");
		}
	}

	private VertexIterator iter;

	public VertexIterable(Graph g) {
		this(g, (Class<? extends Vertex>) null);
	}

	public VertexIterable(Graph g, Class<? extends Vertex> vc) {
		assert g != null;
		iter = new VertexIterator((InternalGraph) g, vc);
	}

	public VertexIterable(Graph g, VertexClass vc) {
		assert g != null;
		iter = new VertexIterator((InternalGraph) g, vc);
	}

	public Iterator<V> iterator() {
		return iter;
	}

}
