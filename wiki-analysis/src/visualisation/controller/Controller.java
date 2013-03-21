package visualisation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreePath;

import visualisation.model.CategoryTreeModel;
import visualisation.model.CategoryTreeNode;
import visualisation.view.EditCommentDialog;
import de.uni_koblenz.jgralab.Graph;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class Controller {

	public static final String treeSelectionChange = "treeSelectionChange";
	public static final String highlightingModeChange = "highlightingModeChange";
	public static final String graphChange = "graphChange";
	public static final String commentChange = "commentChange";

	private CategoryTreeModel model;

	public void setModel(CategoryTreeModel model) {
		this.model = model;
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
		model.removeBlacklistedCategories();
	}

	public void treeBlacklistingChanged(TreeSelectionEvent e) {

		for (TreePath path : e.getPaths()) {
			if (path.getLastPathComponent() instanceof CategoryTreeNode) {
				model.setBlacklisting(
						(CategoryTreeNode) path.getLastPathComponent(),
						e.isAddedPath(path));
			}
		}
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
