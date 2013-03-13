package visualisation.view;

import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import visualisation.controller.Controller;
import visualisation.model.CategoryTreeModel;
import visualisation.model.CategoryTreeNode;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
@SuppressWarnings("serial")
public class InfoPanel extends JPanel implements PropertyChangeListener {

	// Outsource colors
	public static final Color zerothColor = Color.LIGHT_GRAY;
	public static final Color firstColor = Color.WHITE;
	public static final Color secondColor = new Color(0xFFCCCC);
	public static final Color thirdColor = new Color(0xFF8080);
	public static final Color fourthColor = new Color(0xFF0000);
	
	private CategoryTreeModel model;
		
	private JLabel lblParentCategoriesValue;
	private JLabel lblSubcategoriesTransitiveValue;
	private JLabel lblPagesTransitiveValue;
	private JLabel lblSubcategoriesValue;
	private JLabel lblPagesValue;
	private JLabel lblHighlightingValue0;
	private JLabel lblHighlightingValue1;
	private JLabel lblHighlightingValue2;
	private JLabel lblHighlightingValue3;
	private JLabel lblHighlightingValue4;
	private JList<String> pagesList;
	private JList<String> parentsList;
		
	/**
	 * Create the panel.
	 */
	public InfoPanel(CategoryTreeModel model) {
		setFont(new Font("Dialog", Font.PLAIN, 12));

		this.model = model;
		
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("8dlu"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(75dlu;pref)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("right:120dlu"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("50dlu"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblSelectedNode = new JLabel("Selected node");
		lblSelectedNode.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblSelectedNode, "2, 2, 7, 1");

		final JLabel lblPages = new JLabel("pages:");
		lblPages.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblPages, "2, 4, 5, 1");
		
				lblPagesValue = new JLabel("");
				lblPagesValue.setFont(new Font("Dialog", Font.PLAIN, 12));
				
						add(lblPagesValue, "8, 4, left, default");

		final JLabel lblSubcategories = new JLabel("subcategories:");
		lblSubcategories.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblSubcategories, "2, 6, 5, 1");
		
				lblSubcategoriesValue = new JLabel("");
				lblSubcategoriesValue.setFont(new Font("Dialog", Font.PLAIN, 12));
				add(lblSubcategoriesValue, "8, 6, left, default");

		final JLabel lblPagesTransitive = new JLabel("pages (transitive):");
		lblPagesTransitive.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblPagesTransitive, "2, 8, 5, 1");
		
				lblPagesTransitiveValue = new JLabel("");
				lblPagesTransitiveValue.setFont(new Font("Dialog", Font.PLAIN, 12));
				add(lblPagesTransitiveValue, "8, 8, left, default");

		final JLabel lblSubcategoriesTransitive = new JLabel(
				"subcategories (transitive):");
		lblSubcategoriesTransitive.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblSubcategoriesTransitive, "2, 10, 5, 1");
		
				lblSubcategoriesTransitiveValue = new JLabel("");
				lblSubcategoriesTransitiveValue.setFont(new Font("Dialog", Font.PLAIN, 12));
				add(lblSubcategoriesTransitiveValue, "8, 10, left, default");

		final JLabel lblParentCategories = new JLabel("parent categories:");
		lblParentCategories.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblParentCategories, "2, 12, 5, 1");
		
				lblParentCategoriesValue = new JLabel("");
				lblParentCategoriesValue.setFont(new Font("Dialog", Font.PLAIN, 12));
				add(lblParentCategoriesValue, "8, 12, left, default");
		
		JLabel lblHighlighting = new JLabel("Highlighting");
		lblHighlighting.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblHighlighting, "2, 14, 7, 1");
		
		JLabel lblHighlightinhColor0 = new JLabel("​");
		lblHighlightinhColor0.setOpaque(true);
		lblHighlightinhColor0.setFont(new Font("Dialog", Font.BOLD, 12));
		lblHighlightinhColor0.setBackground(Color.LIGHT_GRAY);
		add(lblHighlightinhColor0, "2, 16");
		
		JLabel label_0 = new JLabel("=");
		add(label_0, "4, 16");
		
		lblHighlightingValue0 = new JLabel("");
		lblHighlightingValue0.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblHighlightingValue0, "6, 16, 3, 1");
		
		JLabel lblHighlightingColor1 = new JLabel("\u200B");
		lblHighlightingColor1.setBackground(firstColor);
		lblHighlightingColor1.setOpaque(true);
		lblHighlightingColor1.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblHighlightingColor1, "2, 18, fill, default");
		
		JLabel label = new JLabel("\u2264");
		add(label, "4, 18, center, default");
		
		lblHighlightingValue1 = new JLabel("");
		lblHighlightingValue1.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblHighlightingValue1, "6, 18, 3, 1");
		
		JLabel lblHighlightingColor2 = new JLabel("\u200B");
		lblHighlightingColor2.setBackground(secondColor);
		lblHighlightingColor2.setOpaque(true);
		lblHighlightingColor2.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblHighlightingColor2, "2, 20, fill, default");
		
		JLabel label_1 = new JLabel("\u2264");
		add(label_1, "4, 20, center, default");
		
		lblHighlightingValue2 = new JLabel("");
		lblHighlightingValue2.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblHighlightingValue2, "6, 20, 3, 1");
		
		JLabel lblHighlightingColor3 = new JLabel("\u200B");
		lblHighlightingColor3.setBackground(thirdColor);
		lblHighlightingColor3.setOpaque(true);
		lblHighlightingColor3.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblHighlightingColor3, "2, 22, fill, default");
		
		JLabel label_2 = new JLabel("\u2264");
		add(label_2, "4, 22, center, default");
		
		lblHighlightingValue3 = new JLabel("");
		lblHighlightingValue3.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblHighlightingValue3, "6, 22, 3, 1");
		
		JLabel lblHighlightingColor4 = new JLabel("\u200B");
		lblHighlightingColor4.setBackground(fourthColor);
		lblHighlightingColor4.setOpaque(true);
		lblHighlightingColor4.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblHighlightingColor4, "2, 24, fill, default");
		
		JLabel label_3 = new JLabel("\u2264");
		add(label_3, "4, 24, center, default");
		
		lblHighlightingValue4 = new JLabel("");
		lblHighlightingValue4.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblHighlightingValue4, "6, 24, 3, 1");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "2, 28, 7, 1, fill, fill");
		
		parentsList = new JList<String>();
		scrollPane_1.setViewportView(parentsList);
		
		JLabel lblPagesHeading = new JLabel("Pages");
		lblPagesHeading.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblPagesHeading, "2, 30, 7, 1");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 32, 7, 1, fill, fill");
		
		pagesList = new JList<String>();
		scrollPane.setViewportView(pagesList);
		
		JLabel lblParentsHeading = new JLabel("Parent Categories");
		lblParentsHeading.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblParentsHeading, "2, 26, 7, 1");
		
		updateLegend();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		String property = evt.getPropertyName();
		
		if (property.equals(Controller.treeSelectionChange)) {
			updateSelectionValues();
		}
		
		if (property.equals(Controller.highlightingModeChange)) {
			updateLegend();
		}
		
		if (property.equals(Controller.graphChange)) {
			updateLegend();
		}
		
	}
	
	private void updateSelectionValues() {
		CategoryTreeNode node = model.getSelectedNode();
		lblPagesValue.setText("" + node.getPages());
		lblPagesTransitiveValue.setText("" + node.getPagesTransitive());
		lblSubcategoriesValue.setText("" + node.getSubcategories());
		lblSubcategoriesTransitiveValue.setText("" + node.getSubcategoriesTransitive());
		lblParentCategoriesValue.setText("" + node.getParentCategories());
		pagesList.setListData(node.getPageStrings());
		parentsList.setListData(node.getParentCategoryStrings());
	}
	
	private void updateLegend() {
		lblHighlightingValue0.setText(model.getHighlightingText0());
		lblHighlightingValue1.setText(model.getHighlightingText1());
		lblHighlightingValue2.setText(model.getHighlightingText2());
		lblHighlightingValue3.setText(model.getHighlightingText3());
		lblHighlightingValue4.setText(model.getHighlightingText4());
	}


}
