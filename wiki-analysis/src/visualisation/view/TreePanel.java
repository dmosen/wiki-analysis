package visualisation.view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import visualisation.controller.Controller;
import visualisation.model.CategoryTreeModel;
import visualisation.model.CategoryTreeNode;

import com.jidesoft.swing.CheckBoxTree;

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
@SuppressWarnings("serial")
public class TreePanel extends JPanel implements PropertyChangeListener {

	private Controller controller;
	private MyCheckBoxTree tree;

	/**
	 * Create the panel.
	 */
	public TreePanel(CategoryTreeModel model, Controller controller) {
		this.controller = controller;

		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		tree = new MyCheckBoxTree();

		controller.setCheckBoxTreeSelectionModel(tree.getCheckBoxTreeSelectionModel());
		
		tree.setDigIn(false);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		scrollPane.setViewportView(tree);

		tree.setModel(model);
		tree.setCellRenderer(new CheckBoxRenderer(model));
		
		addComponentListeners();
	}

	private void addComponentListeners() {
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				controller.treeSelectionChanged(e);
			}
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Controller.highlightingModeChange)) {
			tree.repaint();
		}
	}

}

@SuppressWarnings("serial")
class MyCheckBoxTree extends CheckBoxTree {
	
	@Override
	public boolean isCheckBoxVisible(TreePath path) {
		if (path.getLastPathComponent() instanceof CategoryTreeNode) {
			if (((CategoryTreeNode)path.getLastPathComponent()).isRoot()) {
				return false;
			}
		}
		return super.isCheckBoxVisible(path);
	}
}
