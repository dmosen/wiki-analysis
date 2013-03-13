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

package de.uni_koblenz.jgralab.jniserver;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import de.uni_koblenz.jgralab.AttributedElement;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphException;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.ImplementationType;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.WorkInProgress;
import de.uni_koblenz.jgralab.schema.Attribute;
import de.uni_koblenz.jgralab.schema.AttributedElementClass;
import de.uni_koblenz.jgralab.schema.Domain;
import de.uni_koblenz.jgralab.schema.EdgeClass;
import de.uni_koblenz.jgralab.schema.EnumDomain;
import de.uni_koblenz.jgralab.schema.Schema;
import de.uni_koblenz.jgralab.schema.VertexClass;

/**
 * A JNI server class for calling JGraLab from C++.
 * 
 * @author ist@uni-koblenz.de
 * 
 */
@WorkInProgress(description = "totally incomplete, mainly untested", responsibleDevelopers = "riediger")
public class JniServer {
	private static int keyGenerator;

	/**
	 * the {@code Map} holding the graphs created or loaded via the
	 * {@code JGraLabFacade}
	 */
	private Map<Integer, Graph> graphs;

	public JniServer() {
		keyGenerator = 1;
		graphs = new Hashtable<Integer, Graph>();
	}

	/**
	 * Stores {@code graph} and returns the graphId for accessing the graph.
	 * 
	 * @param graph
	 *            a graph
	 * @return handle for accessing a graph in the Map {@code graphs}
	 */
	private int addGraph(Graph graph) {
		if (graphs.containsValue(graph)) {
			throw new GraphException("Graph was already added before!");
		}
		int key = keyGenerator;
		++keyGenerator;
		graphs.put(key, graph);
		return key;
	}

	/**
	 * Removes the graph with id {@code graphId} from this JniServer.
	 * 
	 * @param graphId
	 *            the id of the graph to be deleted
	 */
	public void deleteGraph(int graphId) {
		graphs.remove(graphId);
	}

	/**
	 * Checks whether a graph with the handle {@code graphId} exists.
	 * 
	 * @param graphId
	 *            the handle for which the existence of a graph shall be checked
	 * @return {@code true} if a graph with handle {@code graphNo} exists,
	 *         {@code false} otherwise
	 */
	public boolean containsGraph(int graphId) {
		return graphs.containsKey(graphId);
	}

	public int createGraph(String schemaName) {
		Class<?> schemaClass;
		try {
			schemaClass = Class.forName(schemaName);
			Schema schema = (Schema) (schemaClass.getMethod("instance",
					(Class[]) null).invoke(null));

			Graph g = schema.createGraph(ImplementationType.STANDARD);
			return addGraph(g);
		} catch (Exception e) {
			throw new GraphException("Exception while creating graph.", e);
		}
	}

	public void saveGraph(int graphId, String fileName) {
		try {
			GraphIO.saveGraphToFile(graphs.get(graphId), fileName, null);
		} catch (Exception e) {
			throw new GraphException("Exception while saving graph.", e);
		}
	}

	public int loadGraph(String fileName) {
		try {
			Graph g = GraphIO.loadGraphFromFile(fileName, null);
			return addGraph(g);
		} catch (Exception e) {
			throw new GraphException("Exception while loading graph.", e);
		}
	}

	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------

	public int createVertex(int graphId, String vertexClassName) {
		Graph graph = graphs.get(graphId);
		VertexClass schemaClass = graph.getGraphClass().getVertexClass(
				vertexClassName);
		return graph.createVertex(schemaClass).getId();
	}

	public void deleteVertex(int graphId, int vertexId) {
		graphs.get(graphId).getVertex(vertexId).delete();
	}

	public void setVertexAttribute(int graphId, int vertexId,
			String attributeName, boolean value) {
		setAttribute(graphs.get(graphId).getVertex(vertexId), attributeName,
				value);
	}

	public void setVertexAttribute(int graphId, int vertexId,
			String attributeName, int value) {
		setAttribute(graphs.get(graphId).getVertex(vertexId), attributeName,
				value);
	}

	public void setVertexAttribute(int graphId, int vertexId,
			String attributeName, long value) {
		setAttribute(graphs.get(graphId).getVertex(vertexId), attributeName,
				value);
	}

	public void setVertexAttribute(int graphId, int vertexId,
			String attributeName, double value) {
		setAttribute(graphs.get(graphId).getVertex(vertexId), attributeName,
				value);
	}

	public void setVertexAttribute(int graphId, int vertexId,
			String attributeName, String value) {
		setAttribute(graphs.get(graphId).getVertex(vertexId), attributeName,
				value);
	}

	public void setVertexEnumAttribute(int graphId, int vertexId,
			String attributeName, String value) {
		setEnumAttribute(graphs.get(graphId).getVertex(vertexId),
				attributeName, value);
	}

	public void setVertexListAttribute(int graphId, int vertexId,
			String attributeName, List<?> value) {
		setAttribute(graphs.get(graphId).getVertex(vertexId), attributeName,
				value);
	}

	public String getVertexClassName(int graphId, int vertexId) {
		return graphs.get(graphId).getVertex(vertexId)
				.getAttributedElementClass().getQualifiedName();
	}

	public boolean getVertexBooleanAttribute(int graphId, int vertexId,
			String attributeName) {
		return (Boolean) getAttribute(graphs.get(graphId).getVertex(vertexId),
				attributeName);
	}

	public int getVertexIntegerAttribute(int graphId, int vertexId,
			String attributeName) {
		return (Integer) getAttribute(graphs.get(graphId).getVertex(vertexId),
				attributeName);
	}

	public long getVertexLongAttribute(int graphId, int vertexId,
			String attributeName) {
		return (Long) getAttribute(graphs.get(graphId).getVertex(vertexId),
				attributeName);
	}

	public double getVertexDoubleAttribute(int graphId, int vertexId,
			String attributeName) {
		return (Double) getAttribute(graphs.get(graphId).getVertex(vertexId),
				attributeName);
	}

	public String getVertexStringAttribute(int graphId, int vertexId,
			String attributeName) {
		return (String) getAttribute(graphs.get(graphId).getVertex(vertexId),
				attributeName);

	}

	public String getVertexEnumAttribute(int graphId, int vertexId,
			String attributeName) {
		return getEnumAttribute(graphs.get(graphId).getVertex(vertexId),
				attributeName);
	}

	public List<?> getVertexListAttribute(int graphId, int vertexId,
			String attributeName) {
		return (List<?>) getAttribute(graphs.get(graphId).getVertex(vertexId),
				attributeName);
	}

	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------

	public int createEdge(int graphId, String edgeClassName, int alphaId,
			int omegaId) {
		Graph graph = graphs.get(graphId);
		EdgeClass schemaClass = graph.getGraphClass().getEdgeClass(
				edgeClassName);
		return graph.createEdge(schemaClass, graph.getVertex(alphaId),
				graph.getVertex(omegaId)).getId();
	}

	public void deleteEdge(int graphId, int edgeId) {
		graphs.get(graphId).getEdge(edgeId).delete();
	}

	public Edge getEdge(int graphId, int edgeId) {
		return graphs.get(graphId).getEdge(edgeId);
	}

	public String getEdgeClassName(int graphId, int edgeId) {
		return graphs.get(graphId).getEdge(edgeId).getAttributedElementClass()
				.getQualifiedName();
	}

	public void setEdgeAttribute(int graphId, int edgeId, String attributeName,
			boolean value) {
		setAttribute(graphs.get(graphId).getEdge(edgeId), attributeName, value);
	}

	public void setEdgeAttribute(int graphId, int edgeId, String attributeName,
			int value) {
		setAttribute(graphs.get(graphId).getEdge(edgeId), attributeName, value);
	}

	public void setEdgeAttribute(int graphId, int edgeId, String attributeName,
			long value) {
		setAttribute(graphs.get(graphId).getEdge(edgeId), attributeName, value);
	}

	public void setEdgeAttribute(int graphId, int edgeId, String attributeName,
			double value) {
		setAttribute(graphs.get(graphId).getEdge(edgeId), attributeName, value);
	}

	public void setEdgeAttribute(int graphId, int edgeId, String attributeName,
			String value) {
		setAttribute(graphs.get(graphId).getEdge(edgeId), attributeName, value);
	}

	public void setEdgeEnumAttribute(int graphId, int edgeId,
			String attributeName, String value) {
		setEnumAttribute(graphs.get(graphId).getEdge(edgeId), attributeName,
				value);
	}

	public void setEdgeListAttribute(int graphId, int edgeId,
			String attributeName, List<?> value) {
		setAttribute(graphs.get(graphId).getEdge(edgeId), attributeName, value);
	}

	public boolean getEdgeBooleanAttribute(int graphId, int edgeId,
			String attributeName) {
		return (Boolean) getAttribute(graphs.get(graphId).getEdge(edgeId),
				attributeName);
	}

	public int getEdgeIntegerAttribute(int graphId, int edgeId,
			String attributeName) {
		return (Integer) getAttribute(graphs.get(graphId).getEdge(edgeId),
				attributeName);

	}

	public long getEdgeLongAttribute(int graphId, int edgeId,
			String attributeName) {
		return (Long) getAttribute(graphs.get(graphId).getEdge(edgeId),
				attributeName);
	}

	public double getEdgeDoubleAttribute(int graphId, int edgeId,
			String attributeName) {
		return (Double) getAttribute(graphs.get(graphId).getEdge(edgeId),
				attributeName);
	}

	public String getEdgeStringAttribute(int graphId, int edgeId,
			String attributeName) {
		return (String) getAttribute(graphs.get(graphId).getEdge(edgeId),
				attributeName);

	}

	public String getEdgeEnumAttribute(int graphId, int edgeId,
			String attributeName) {
		return getEnumAttribute(graphs.get(graphId).getEdge(edgeId),
				attributeName);
	}

	public List<?> getEdgeListAttribute(int graphId, int edgeId,
			String attributeName) {
		return (List<?>) getAttribute(graphs.get(graphId).getEdge(edgeId),
				attributeName);
	}

	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------

	public int getAlpha(int graphId, int edgeId) {
		return graphs.get(graphId).getEdge(edgeId).getAlpha().getId();
	}

	public int getOmega(int graphId, int edgeId) {
		return graphs.get(graphId).getEdge(edgeId).getOmega().getId();
	}

	public int getThis(int graphId, int edgeId) {
		return graphs.get(graphId).getEdge(edgeId).getThis().getId();
	}

	public int getThat(int graphId, int edgeId) {
		return graphs.get(graphId).getEdge(edgeId).getThat().getId();
	}

	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------

	public Vertex getVertex(int graphId, int vertexId) {
		return graphs.get(graphId).getVertex(vertexId);
	}

	public int getFirstVertex(int graphId, String vertexClassName) {
		Graph g = graphs.get(graphId);
		Vertex v = (vertexClassName != null) ? g.getFirstVertex((VertexClass) g
				.getSchema().getAttributedElementClass(vertexClassName)) : g
				.getFirstVertex();
		return (v == null) ? 0 : v.getId();
	}

	public int getNextVertex(int graphId, int vertexId, String vertexClassName) {
		Graph g = graphs.get(graphId);
		Vertex v = (vertexClassName != null) ? g.getVertex(vertexId)
				.getNextVertex(
						((VertexClass) g.getSchema().getAttributedElementClass(
								vertexClassName))) : g.getVertex(vertexId)
				.getNextVertex();
		return (v == null) ? 0 : v.getId();
	}

	public int getFirstEdgeInGraph(int graphId, String edgeClassName) {
		Graph g = graphs.get(graphId);
		Edge e = (edgeClassName != null) ? g.getFirstEdge((EdgeClass) g
				.getSchema().getAttributedElementClass(edgeClassName)) : g
				.getFirstEdge();
		return (e == null) ? 0 : e.getId();
	}

	public int getNextEdgeInGraph(int graphId, int edgeId, String edgeClassName) {
		Graph g = graphs.get(graphId);
		Edge e = (edgeClassName != null) ? g.getEdge(edgeId).getNextEdge(
				((EdgeClass) g.getSchema().getAttributedElementClass(
						edgeClassName))) : g.getEdge(edgeId).getNextEdge();
		return (e == null) ? 0 : e.getId();
	}

	public int getFirstEdge(int graphId, int vertexId, String edgeClassName) {
		Graph g = graphs.get(graphId);
		Edge e = (edgeClassName != null) ? g.getVertex(vertexId)
				.getFirstIncidence(
						(EdgeClass) g.getSchema().getAttributedElementClass(
								edgeClassName)) : g.getVertex(vertexId)
				.getFirstIncidence();
		return (e == null) ? 0 : e.getId();
	}

	public int getNextEdge(int graphId, int edgeId, String edgeClassName) {
		Graph g = graphs.get(graphId);
		Edge e = (edgeClassName != null) ? g.getEdge(edgeId).getNextIncidence(
				((EdgeClass) g.getSchema().getAttributedElementClass(
						edgeClassName))) : g.getEdge(edgeId).getNextIncidence();
		return (e == null) ? 0 : e.getId();
	}

	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------

	public List<?> createList() {
		return new ArrayList<Object>();
	}

	void clearList(List<?> list) {
		list.clear();
	}

	void addListElement(List<Object> list, int index, Object element) {
		if (index < 0) {
			list.add(element);
		} else {
			list.add(index, element);
		}
	}

	void addIntegerListElement(List<Object> list, int index, int element) {
		addListElement(list, index, Integer.valueOf(element));
	}

	void addLongListElement(List<Object> list, int index, long element) {
		addListElement(list, index, Long.valueOf(element));
	}

	void addDoubleListElement(List<Object> list, int index, double element) {
		addListElement(list, index, Double.valueOf(element));
	}

	void addStringListElement(List<Object> list, int index, String element) {
		addListElement(list, index, element);
	}

	int getListSize(List<?> list) {
		return list.size();
	}

	Object getListElement(List<Object> list, int index) {
		return list.get(index);
	}

	int getIntegerListElement(List<Integer> list, int index) {
		return list.get(index);
	}

	long getLongListElement(List<Long> list, int index) {
		return list.get(index);
	}

	double getDoubleListElement(List<Double> list, int index) {
		return list.get(index);
	}

	String getStringListElement(List<String> list, int index) {
		return list.get(index);
	}

	void removeListElement(List<?> list, int index) {
		list.remove(index);
	}

	// ----------------------------------------------------------------------------
	// ----------------------------------------------------------------------------

	private void setEnumAttribute(AttributedElement<?, ?> e,
			String attributeName, Object value) {
		try {
			AttributedElementClass<?, ?> aec = e.getAttributedElementClass();
			Attribute attr = aec.getAttribute(attributeName);
			if (attr == null) {
				throw new GraphException("Attribute " + attributeName
						+ " not defined in class " + aec.getQualifiedName());
			}
			Domain domain = attr.getDomain();
			if (!(domain instanceof EnumDomain)) {
				throw new GraphException("Domain of attribute " + attributeName
						+ " is no EnumDomain.");
			}
			Class<?> attrType = Class.forName(domain.getSchema()
					.getPackagePrefix() + "." + domain.getQualifiedName());
			Object enumValue = attrType.getMethod("valueOf",
					new Class[] { String.class }).invoke(null, value);
			if (enumValue == null) {
				throw new GraphException("Enum value " + value
						+ " not defined in domain " + domain.getQualifiedName());
			}
			setAttribute(e, attributeName, enumValue);
		} catch (ClassNotFoundException ex) {
			throw new GraphException(ex);
		} catch (IllegalArgumentException ex) {
			throw new GraphException(ex);
		} catch (SecurityException ex) {
			throw new GraphException(ex);
		} catch (IllegalAccessException ex) {
			throw new GraphException(ex);
		} catch (InvocationTargetException ex) {
			throw new GraphException(ex);
		} catch (NoSuchMethodException ex) {
			throw new GraphException(ex);
		}
	}

	private void setAttribute(AttributedElement<?, ?> e, String attributeName,
			Object value) {
		e.setAttribute(attributeName, value);
	}

	private String getEnumAttribute(AttributedElement<?, ?> e,
			String attributeName) {
		AttributedElementClass<?, ?> aec = e.getAttributedElementClass();
		Attribute attr = aec.getAttribute(attributeName);
		if (attr == null) {
			throw new GraphException("Attribute " + attributeName
					+ " not defined in class " + aec.getQualifiedName());
		}
		Domain domain = attr.getDomain();
		if (!(domain instanceof EnumDomain)) {
			throw new GraphException("Domain of attribute " + attributeName
					+ " is no EnumDomain.");
		}
		return (String) getAttribute(e, attributeName);

	}

	private Object getAttribute(AttributedElement<?, ?> e, String attributeName) {
		return e.getAttribute(attributeName);
	}

}
