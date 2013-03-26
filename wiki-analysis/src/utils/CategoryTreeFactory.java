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
	private static CategoryTreeNode parentNode;
	private static Edge parentEdge;

	/**
	 * Builds a category tree from a given graph conforming to the schema
	 * <code>category-schema.tg</code> and returns the root node of the
	 * constructed tree. If the graph contains cycles the responsible backward
	 * arcs are detected and marked as inside the graph.
	 * 
	 * @param graph
	 * @return the {@link CategoryTreeNode} representing the root node of the
	 *         tree
	 * @throws AlgorithmTerminatedException
	 */
	public static CategoryTreeNode buildCategoryTreeModel(Graph graph) {

		Vertex root = graph.getFirstVertex();

		visitedVertices = new HashSet<Vertex>();
		leftVertices = new HashSet<Vertex>();

		parentNode = null;
		parentEdge = null;

		buildCategoryTreeModel(root);
		return categoryTreeRoot;
	}

	private static void buildCategoryTreeModel(Vertex current) {

		CategoryTreeNode categoryTreeNode = new CategoryTreeNode(current,
				parentEdge);
		visitedVertices.add(current);

		if (parentNode == null) {
			categoryTreeRoot = categoryTreeNode;
		} else {
			parentNode.add(categoryTreeNode);
		}

		for (Edge e : current.incidences(
				GraphProperties.getInstance().subcategoryLinkEC,
				EdgeDirection.OUT)) {
			if (!(Boolean) e.getAttribute("blacklisted")) {

				parentNode = categoryTreeNode;
				parentEdge = e;

				Vertex next = e.getOmega();

				// detect cycles
				if (visitedVertices.contains(next)
						&& !leftVertices.contains(next)) {
					e.setAttribute("blacklisted", true);
					e.setAttribute("backwardArc", true);
					if (e.getAttribute("comment") == null) {
						e.setAttribute("comment",
								"blacklisted by cycle detection");
					}
					System.out.println("Cycle detected from "
							+ e.getAlpha().getAttribute("title") + " to "
							+ e.getOmega().getAttribute("title") + ".");
				} else {
					buildCategoryTreeModel(next);
				}
			}
		}
		leftVertices.add(current);
	}

}
