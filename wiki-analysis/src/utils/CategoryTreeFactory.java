package utils;

import java.util.HashSet;

import schemas.categoryschema.Category;
import schemas.categoryschema.CategoryGraph;
import schemas.categoryschema.HasSubcategory;
import visualisation.model.CategoryTreeNode;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class CategoryTreeFactory {

	private static HashSet<Category> visitedVertices;
	private static HashSet<Category> leftVertices;

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

		CategoryTreeNode rootNode = new CategoryTreeNode(root, null);
		rootNode.setRoot(true);

		visitedVertices = new HashSet<Category>();
		leftVertices = new HashSet<Category>();

		buildCategoryTreeModel(root);

		return rootNode;
	}

	private static void buildCategoryTreeModel(Category current) {

		visitedVertices.add(current);

		for (HasSubcategory e : current
				.getHasSubcategoryIncidences(EdgeDirection.OUT)) {
			if (!e.is_blacklisted()) {

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
