package visualisation.model;

import graph.GraphStats;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import schemas.categoryschema.CategoryGraph;
import schemas.categoryschema.Subcategory;
import utils.CategoryTreeFactory;
import utils.WikipediaAnalysis;
import visualisation.controller.Controller;
import visualisation.controller.HighlightingMode;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
@SuppressWarnings("serial")
public class CategoryTreeModel extends DefaultTreeModel {

	public static HashMap<Subcategory, CategoryTreeNode> edgeToNodeMap = new HashMap<Subcategory, CategoryTreeNode>();

	private PropertyChangeSupport propertyChangeSupport;

	private CategoryGraph graph;
	private GraphStats stats;

	private CategoryTreeNode selectedNode;

	private List<CategoryTreeNode> blacklistedNodes;
	private HighlightingMode highlightingMode;

	public CategoryTreeModel(CategoryGraph graph) {
		super(CategoryTreeFactory.buildCategoryTreeModel(graph));
		CategoryTreeModel.edgeToNodeMap.clear();
		propertyChangeSupport = new PropertyChangeSupport(this);
		highlightingMode = HighlightingMode.pages;
		blacklistedNodes = new ArrayList<CategoryTreeNode>();
		this.graph = graph;
		stats = WikipediaAnalysis.computeStatistics(graph);
	}

	public CategoryGraph getGraph() {
		return graph;
	}

	public void setGraph(CategoryGraph graph) {
		CategoryTreeModel.edgeToNodeMap.clear();
		blacklistedNodes.clear();
		this.graph = graph;

		root = CategoryTreeFactory.buildCategoryTreeModel(graph);

		stats = WikipediaAnalysis.computeStatistics(graph);

		firePropertyChangeEvent(Controller.graphChange, null, graph);
	}

	public void reloadGraph() {
		nodeStructureChanged(root);
		stats = WikipediaAnalysis.computeStatistics(graph);
		firePropertyChangeEvent(Controller.statsChange, null, stats);
	}

	public GraphStats getStats() {
		return stats;
	}

	public String getRootCategory() {
		return ((CategoryTreeNode) getRoot()).getTitle();
	}

	public void setSelectedNode(CategoryTreeNode selectedNode) {
		firePropertyChangeEvent(Controller.treeSelectionChange,
				this.selectedNode, this.selectedNode = selectedNode);
	}

	public CategoryTreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setBlacklisting(CategoryTreeNode node, boolean value) {
		if (value) {
			node.setBlacklisting(true);
			if (!blacklistedNodes.contains(node)) {
				blacklistedNodes.add(node);
			}
		} else {
			node.setBlacklisting(false);
			if (blacklistedNodes.contains(node)) {
				blacklistedNodes.remove(node);
			}
		}
	}

	public void setComment(CategoryTreeNode node, String comment) {
		String oldComment = node.getComment();
		node.setComment(comment);
		firePropertyChangeEvent(Controller.commentChange, oldComment, comment);
	}

	public HighlightingMode getHighlightingMode() {
		return highlightingMode;
	}

	public void setHighlightingMode(HighlightingMode highlightingMode) {
		firePropertyChangeEvent(Controller.highlightingModeChange,
				this.highlightingMode, this.highlightingMode = highlightingMode);
	}

	public HighlightingMode[] getHighlightingModes() {
		return HighlightingMode.values();
	}

	public int getHighlighting(CategoryTreeNode node) {

		double ratio = 0;

		switch (highlightingMode) {
		case pages:
			if (node.getPages() == 0) {
				return 0;
			} else if (node.getPages() <= stats.getPagesQuantil25()) {
				return 1;
			} else if (node.getPages() <= stats.getPagesMedian()) {
				return 2;
			} else if (node.getPages() <= stats.getPagesQuantil75()) {
				return 3;
			}
			return 4;

		case pagesTransitive:

			if (node.isRoot()) {
				if (node.getPagesTransitive() == 0) {
					ratio = 0;
				} else {
					ratio = 1;
				}
			} else {
				if (node.getParent().getPagesTransitive() == 0) {
					ratio = 0;
				} else {
					ratio = node.getPagesTransitive()
							/ (double) node.getParent().getPagesTransitive();
				}
			}

			if (ratio == 0) {
				return 0;
			} else if (ratio <= 0.25) {
				return 1;
			} else if (ratio <= 0.5) {
				return 2;
			} else if (ratio <= 0.75) {
				return 3;
			} else {
				return 4;
			}

		case subcategories:
			if (node.getSubcategories() == 0) {
				return 0;
			} else if (node.getSubcategories() <= stats
					.getSubcategoriesQuantil25()) {
				return 1;
			} else if (node.getSubcategories() <= stats
					.getSubcategoriesMedian()) {
				return 2;
			} else if (node.getSubcategories() <= stats
					.getSubcategoriesQuantil75()) {
				return 3;
			}
			return 4;

		case subcategoriesTransitive:

			if (node.isRoot()) {
				if (node.getSubcategoriesTransitive() == 0) {
					ratio = 0;
				} else {
					ratio = 1;
				}
			} else {
				if (node.getParent().getSubcategoriesTransitive() == 0) {
					ratio = 0;
				} else {
					ratio = node.getSubcategoriesTransitive()
							/ (double) node.getParent()
									.getSubcategoriesTransitive();
				}
			}

			if (ratio == 0) {
				return 0;
			} else if (ratio <= 0.25) {
				return 1;
			} else if (ratio <= 0.5) {
				return 2;
			} else if (ratio <= 0.75) {
				return 3;
			} else {
				return 4;
			}
		default:
			return 0;
		}

	}

	public String getHighlightingText0() {
		switch (highlightingMode) {
		case pages:
			return "0 pages";
		case pagesTransitive:
			return "0 pages (transitive)";

		case subcategories:
			return "0 subcategories";

		case subcategoriesTransitive:
			return "0 subcategories (transitive)";

		default:
			return "";
		}
	}

	public String getHighlightingText1() {
		switch (highlightingMode) {
		case pages:
			return stats.getPagesQuantil25() + " pages";
		case pagesTransitive:
			return "25% pages (transitive) of the parent category";

		case subcategories:
			return "" + stats.getSubcategoriesQuantil25() + " subcategories";

		case subcategoriesTransitive:
			return "25% subcategories (transitive) of the parent category";

		default:
			return "";
		}
	}

	public String getHighlightingText2() {
		switch (highlightingMode) {
		case pages:
			return stats.getPagesMedian() + " pages";
		case pagesTransitive:
			return "50% pages (transitive) of the parent category";

		case subcategories:
			return "" + stats.getSubcategoriesMedian() + " subcategories";

		case subcategoriesTransitive:
			return "50% subcategories (transitive) of the parent category";

		default:
			return "";
		}
	}

	public String getHighlightingText3() {
		switch (highlightingMode) {
		case pages:
			return stats.getPagesQuantil75() + " pages";
		case pagesTransitive:
			return "75% pages (transitive) of the parent category";

		case subcategories:
			return "" + stats.getSubcategoriesQuantil75() + " subcategories";

		case subcategoriesTransitive:
			return "75% subcategories (transitive) of the parent category";

		default:
			return "";
		}
	}

	public String getHighlightingText4() {
		switch (highlightingMode) {
		case pages:
			return stats.getMaxPages() + " pages";
		case pagesTransitive:
			return "100% pages (transitive) of the parent category";

		case subcategories:
			return "" + stats.getMaxSubcategories() + " subcategories";

		case subcategoriesTransitive:
			return "100% subcategories (transitive) of the parent category";

		default:
			return "";
		}
	}

	public void saveToFile(File file) {
		try {
			GraphIO.saveGraphToFile(graph, file.toString(), null);
		} catch (GraphIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeBlacklistedCategories(TreePath[] paths) {

		for (int i = 0; i < paths.length; i++) {
			if (paths[i].getLastPathComponent() instanceof CategoryTreeNode) {
				CategoryTreeNode node = (CategoryTreeNode) paths[i]
						.getLastPathComponent();

				if (node.isRoot()) {
					continue;
				}
				CategoryTreeNode parent = node.getParent();
				int index = parent.getIndex(node);

				node.setBlacklisting(true);

				fireTreeNodesRemoved(this, parent.getPath(),
						new int[] { index }, new CategoryTreeNode[] { node });
			}
		}
		stats = WikipediaAnalysis.computeStatistics(graph);
		firePropertyChangeEvent(Controller.statsChange, null, stats);
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		propertyChangeSupport.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		propertyChangeSupport.removePropertyChangeListener(l);
	}

	protected void firePropertyChangeEvent(String propertyName,
			Object oldValue, Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,
				newValue);
	}
}
