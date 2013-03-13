package utils;

import graph.GraphProperties;

import java.util.HashSet;

import visualisation.model.CategoryTreeNode;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
public class CategoryTreeFactory {
	
	private static HashSet<Vertex> visitedVertices;
	private static HashSet<Vertex> leftVertices;
	private static CategoryTreeNode categoryTreeRoot;
	private static CategoryTreeNode parent;

	/**
	 * Builds a category tree from a given graph conforming to the schema
	 * <code>category-schema.tg</code> and returns the root node of the
	 * constructed tree. If the graph contains cycles the responsible backward
	 * links are detected and marked as "blacklisted" inside the graph.
	 * 
	 * @param graph
	 * @return the {@link CategoryTreeNode} representing the root node of the
	 *         tree
	 * @throws AlgorithmTerminatedException
	 */
	public static CategoryTreeNode buildCategoryTreeModel(Graph graph) {

		// Check if there are vertices which are unreachable from the root.
		for (Vertex v : graph
				.vertices(GraphProperties.getInstance().categoryNodeVC)) {
			if (!v.equals(graph.getFirstVertex())) {
				blacklistRecursively(v);
			}
		}

		Vertex root = graph.getFirstVertex();

		visitedVertices = new HashSet<Vertex>();
		leftVertices = new HashSet<Vertex>();

		parent = null;

		buildCategoryTreeModel(root);
		return categoryTreeRoot;
	}

	private static void buildCategoryTreeModel(Vertex current) {

		CategoryTreeNode categoryTreeNode = new CategoryTreeNode(current);
		visitedVertices.add(current);

		if (parent == null) {
			categoryTreeRoot = categoryTreeNode;
		} else {
			parent.add(categoryTreeNode);
		}

		for (Edge e : current.incidences(
				GraphProperties.getInstance().subCategoryLinkEC,
				EdgeDirection.OUT)) {
			if (!(Boolean) e.getAttribute("blacklisted")) {

				parent = categoryTreeNode;
				Vertex next = e.getOmega();

				if (visitedVertices.contains(next)
						&& !leftVertices.contains(next)) {
					e.setAttribute("blacklisted", true);
				} else {
					buildCategoryTreeModel(next);
				}
			}
		}
		leftVertices.add(current);
	}

	private static void blacklistRecursively(Vertex v) {

		// check if vertex is unreachable (i.e. all incoming links are
		// blacklisted)
		boolean allIncomingEdgesBlacklisted = true;
		for (Edge e : v.incidences(
				GraphProperties.getInstance().subCategoryLinkEC,
				EdgeDirection.IN)) {
			if (!(Boolean) e.getAttribute("blacklisted")) {
				allIncomingEdgesBlacklisted = false;
				break;
			}
		}

		// if all incoming edges are blacklisted, blacklist the vertex
		// itself and all its outgoing edges
		if (allIncomingEdgesBlacklisted) {
			v.setAttribute("blacklisted", true);

			for (Edge e : v.incidences(
					GraphProperties.getInstance().subCategoryLinkEC,
					EdgeDirection.OUT)) {
				if (!(Boolean) e.getAttribute("blacklisted")) {
					e.setAttribute("blacklisted", true);

					// check if targets of the edges must be blacklisted
					blacklistRecursively(e.getOmega());
				}
			}
		}
	}

}
