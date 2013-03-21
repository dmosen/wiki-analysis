package visualisation.view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

import visualisation.controller.Controller;
import visualisation.model.CategoryTreeModel;

import com.jidesoft.swing.CheckBoxTree;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
@SuppressWarnings("serial")
public class TreePanel extends JPanel implements PropertyChangeListener {

	private CategoryTreeModel model;
	private Controller controller;
	private CheckBoxTree tree;
	private JLabel lblRootCategory;

	/**
	 * Create the panel.
	 */
	public TreePanel(CategoryTreeModel model, Controller controller) {

		this.model = model;
		this.controller = controller;

		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		tree = new CheckBoxTree();
		tree.setDigIn(false);
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		scrollPane.setViewportView(tree);

		tree.setModel(model);
		tree.setCellRenderer(new CheckBoxRenderer(model));
		
		lblRootCategory = new JLabel(model.getRootCategory());
		lblRootCategory.setFont(new Font("Dialog", Font.BOLD, 12));
		scrollPane.setColumnHeaderView(lblRootCategory);

		addComponentListeners();
	}

	private void addComponentListeners() {
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				controller.treeSelectionChanged(e);
			}
		});
		
		tree.getCheckBoxTreeSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				controller.treeBlacklistingChanged(e);
			}
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if (evt.getPropertyName().equals(Controller.highlightingModeChange)) {
			tree.repaint();
		}
		
		if (evt.getPropertyName().equals(Controller.graphChange)) {
			lblRootCategory.setText(model.getRootCategory());
		}
	}

}
