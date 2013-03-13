package visualisation.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import visualisation.model.CategoryTreeModel;
import visualisation.model.CategoryTreeNode;

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
@SuppressWarnings("serial")
public class CheckBoxRenderer extends DefaultTreeCellRenderer {

	private CategoryTreeModel model;
	private Color selectionBackground;

	public CheckBoxRenderer(CategoryTreeModel model) {
		selectionBackground = new Color(51, 153, 255);
		this.model = model;
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		if ((value != null) && (value instanceof CategoryTreeNode)) {
			CategoryTreeNode node = (CategoryTreeNode) value;
			setText(node.getTitle());
			
			setOpaque(true);
			
			if (selected) {
				setBackground(selectionBackground);
			} else {
				switch (model.getHighlighting(node)) {
				case 0:
					setBackground(InfoPanel.zerothColor);
					break;

				case 1:
					setBackground(InfoPanel.firstColor);
					break;
					
				case 2:
					setBackground(InfoPanel.secondColor);
					break;
					
				case 3:
					setBackground(InfoPanel.thirdColor);
					break;

				default:
					setBackground(InfoPanel.fourthColor);
					break;
				}
			}
		}
		return this;
	}

}
