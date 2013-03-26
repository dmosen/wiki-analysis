package utils;

import java.util.HashSet;

import schemas.categoryschema.Category;
import schemas.categoryschema.CategoryGraph;
import schemas.categoryschema.Subcategory;
import visualisation.model.CategoryTreeNode;
import de.uni_koblenz.jgralab.EdgeDirection;
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
	private static Subcategory parentEdge;

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
	public static CategoryTreeNode buildCategoryTreeModel(CategoryGraph graph) {

		Category root = graph.getFirstCategory();

		visitedVertices = new HashSet<Vertex>();
		leftVertices = new HashSet<Vertex>();

		parentNode = null;
		parentEdge = null;

		buildCategoryTreeModel(root);
		return categoryTreeRoot;
	}

	private static void buildCategoryTreeModel(Category current) {

		CategoryTreeNode categoryTreeNode = new CategoryTreeNode(current,
				parentEdge);
		visitedVertices.add(current);

		if (parentNode == null) {
			categoryTreeRoot = categoryTreeNode;
		} else {
			parentNode.add(categoryTreeNode);
		}

		for (Subcategory e : current
				.getSubcategoryIncidences(EdgeDirection.OUT)) {
			if (!e.is_blacklisted()) {

				parentNode = categoryTreeNode;
				parentEdge = e;

				Category next = e.getOmega();

				// detect cycles
				if (visitedVertices.contains(next)
						&& !leftVertices.contains(next)) {
					e.set_blacklisted(true);
					e.set_backwardArc(true);
					if (e.get_comment() == null) {
						e.set_comment("blacklisted by cycle detection");
					}
					System.out.println("Cycle detected from "
							+ e.getAlpha().get_title() + " to "
							+ e.getOmega().get_title() + ".");
				} else {
					buildCategoryTreeModel(next);
				}
			}
		}
		leftVertices.add(current);
	}

}
