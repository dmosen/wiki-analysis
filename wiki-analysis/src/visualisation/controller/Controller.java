package visualisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import javax.swing.event.TreeSelectionEvent;

import visualisation.model.CategoryTreeModel;
import visualisation.model.CategoryTreeNode;
import visualisation.view.EditCommentDialog;

import com.jidesoft.swing.CheckBoxTreeSelectionModel;

import de.uni_koblenz.jgralab.Graph;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class Controller {

	/**
	 * Constant representing a selection change inside the tree panel
	 */
	public static final String treeSelectionChange = "treeSelectionChange";

	/**
	 * Constant representing the change of the highlighting mode applied to tree
	 * nodes
	 */
	public static final String highlightingModeChange = "highlightingModeChange";

	/**
	 * Constant representing the change of the entire category graph, for
	 * example when a new category graph is loaded
	 */
	public static final String graphChange = "graphChange";

	/**
	 * Constant representing the change of a comment for a category node from
	 * the category tree
	 */
	public static final String commentChange = "commentChange";

	/**
	 * Constant representing the change of the graph statistics, for example
	 * when categories are removed and the graph statistics were therefore
	 * recomputed
	 */
	public static final String statsChange = "statsChange";

	private CategoryTreeModel model;
	private CheckBoxTreeSelectionModel checkBoxTreeSelectionModel;

	public void setModel(CategoryTreeModel model) {
		this.model = model;
	}

	public void setCheckBoxTreeSelectionModel(
			CheckBoxTreeSelectionModel checkBoxTreeSelectionModel) {
		this.checkBoxTreeSelectionModel = checkBoxTreeSelectionModel;
	}

	public void graphChanged(Graph graph) {
		model.setGraph(graph);
	}

	public void treeSelectionChanged(TreeSelectionEvent e) {

		if (e.getPath() != null
				&& e.getPath().getLastPathComponent() instanceof CategoryTreeNode) {
			CategoryTreeNode node = (CategoryTreeNode) e.getPath()
					.getLastPathComponent();

			if (model != null) {
				model.setSelectedNode(node);
			} else {
				System.out.println(this.getClass().getCanonicalName()
						+ ": Model not initialized!");
			}
		}
	}

	public void highlightingSelectionChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			model.setHighlightingMode((HighlightingMode) e.getItem());
		}
	}

	public void removeMarkedCategoriesButtonClicked(ActionEvent e) {
		model.removeBlacklistedCategories(checkBoxTreeSelectionModel
				.getSelectionPaths());
	}

	public void editCommentButtonClicked(ActionEvent e) {
		EditCommentDialog dialog = new EditCommentDialog(model
				.getSelectedNode().getTitle(), model.getSelectedNode()
				.getComment());

		String comment = dialog.showDialog();
		if (comment != null) {
			model.setComment(model.getSelectedNode(), comment);
		}
	}

}
