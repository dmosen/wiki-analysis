package visualisation.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import visualisation.controller.Controller;
import visualisation.model.CategoryTreeModel;
import visualisation.model.CategoryTreeNode;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

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
	private Controller controller;

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
	private JLabel lblSelectedNodeValue;
	private JButton btnEditComment;
	private JLabel lblCategoriesOverallValue;
	private JLabel lblPagesOverallValue;
	private JLabel lblCommentValue;

	/**
	 * Create the panel.
	 */
	public InfoPanel(CategoryTreeModel model, Controller controller) {
		setFont(new Font("Dialog", Font.PLAIN, 12));

		this.model = model;
		this.controller = controller;

		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("8dlu"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(75dlu;pref)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("left:120dlu"),
				FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("50dlu"),
				FormFactory.UNRELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("max(50dlu;min):grow"), }));

		JLabel lblCategoriesOverall = new JLabel("Categories overall:");
		lblCategoriesOverall.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblCategoriesOverall, "2, 2, 5, 1");

		lblCategoriesOverallValue = new JLabel("");
		lblCategoriesOverallValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblCategoriesOverallValue, "8, 2");

		JLabel lblPagesOverall = new JLabel("Pages overall:");
		lblPagesOverall.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblPagesOverall, "2, 4, 5, 1");

		lblPagesOverallValue = new JLabel("");
		lblPagesOverallValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblPagesOverallValue, "8, 4");

		JLabel lblSelectedNode = new JLabel("Selected node:");
		lblSelectedNode.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblSelectedNode, "2, 6, 5, 1");

		lblSelectedNodeValue = new JLabel("");
		lblSelectedNodeValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblSelectedNodeValue, "8, 6");

		final JLabel lblPages = new JLabel("pages:");
		lblPages.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblPages, "2, 8, 5, 1");

		lblPagesValue = new JLabel("");
		lblPagesValue.setFont(new Font("Dialog", Font.PLAIN, 12));

		add(lblPagesValue, "8, 8");

		final JLabel lblSubcategories = new JLabel("subcategories:");
		lblSubcategories.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblSubcategories, "2, 10, 5, 1");

		lblSubcategoriesValue = new JLabel("");
		lblSubcategoriesValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblSubcategoriesValue, "8, 10");

		final JLabel lblPagesTransitive = new JLabel("pages (transitive):");
		lblPagesTransitive.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblPagesTransitive, "2, 12, 5, 1");

		lblPagesTransitiveValue = new JLabel("");
		lblPagesTransitiveValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblPagesTransitiveValue, "8, 12");

		final JLabel lblSubcategoriesTransitive = new JLabel(
				"subcategories (transitive):");
		lblSubcategoriesTransitive.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblSubcategoriesTransitive, "2, 14, 5, 1");

		lblSubcategoriesTransitiveValue = new JLabel("");
		lblSubcategoriesTransitiveValue.setFont(new Font("Dialog", Font.PLAIN,
				12));
		add(lblSubcategoriesTransitiveValue, "8, 14");

		final JLabel lblParentCategories = new JLabel("parent categories:");
		lblParentCategories.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblParentCategories, "2, 16, 5, 1");

		lblParentCategoriesValue = new JLabel("");
		lblParentCategoriesValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblParentCategoriesValue, "8, 16");

		JLabel lblComment = new JLabel("comment:");
		lblComment.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblComment, "2, 18, 5, 1");

		lblCommentValue = new JLabel("");
		lblCommentValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblCommentValue, "8, 18");

		btnEditComment = new JButton("Edit comment ...");
		btnEditComment.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(btnEditComment, "2, 20, 5, 1");

		JLabel lblHighlighting = new JLabel("Highlighting");
		lblHighlighting.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblHighlighting, "2, 22, 7, 1");

		JLabel lblHighlightinhColor0 = new JLabel("​");
		lblHighlightinhColor0.setOpaque(true);
		lblHighlightinhColor0.setFont(new Font("Dialog", Font.BOLD, 12));
		lblHighlightinhColor0.setBackground(Color.LIGHT_GRAY);
		add(lblHighlightinhColor0, "2, 24");

		JLabel label_0 = new JLabel("=");
		label_0.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(label_0, "4, 24");

		lblHighlightingValue0 = new JLabel("");
		lblHighlightingValue0.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblHighlightingValue0, "6, 24, 3, 1");

		JLabel lblHighlightingColor1 = new JLabel("\u200B");
		lblHighlightingColor1.setBackground(firstColor);
		lblHighlightingColor1.setOpaque(true);
		lblHighlightingColor1.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblHighlightingColor1, "2, 26, fill, default");

		JLabel label = new JLabel("\u2264");
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(label, "4, 26, center, default");

		lblHighlightingValue1 = new JLabel("");
		lblHighlightingValue1.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblHighlightingValue1, "6, 26, 3, 1");

		JLabel lblHighlightingColor2 = new JLabel("\u200B");
		lblHighlightingColor2.setBackground(secondColor);
		lblHighlightingColor2.setOpaque(true);
		lblHighlightingColor2.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblHighlightingColor2, "2, 28, fill, default");

		JLabel label_1 = new JLabel("\u2264");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(label_1, "4, 28, center, default");

		lblHighlightingValue2 = new JLabel("");
		lblHighlightingValue2.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblHighlightingValue2, "6, 28, 3, 1");

		JLabel lblHighlightingColor3 = new JLabel("\u200B");
		lblHighlightingColor3.setBackground(thirdColor);
		lblHighlightingColor3.setOpaque(true);
		lblHighlightingColor3.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblHighlightingColor3, "2, 30, fill, default");

		JLabel label_2 = new JLabel("\u2264");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(label_2, "4, 30, center, default");

		lblHighlightingValue3 = new JLabel("");
		lblHighlightingValue3.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblHighlightingValue3, "6, 30, 3, 1");

		JLabel lblHighlightingColor4 = new JLabel("\u200B");
		lblHighlightingColor4.setBackground(fourthColor);
		lblHighlightingColor4.setOpaque(true);
		lblHighlightingColor4.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblHighlightingColor4, "2, 32, fill, default");

		JLabel label_3 = new JLabel("\u2264");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(label_3, "4, 32, center, default");

		lblHighlightingValue4 = new JLabel("");
		lblHighlightingValue4.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(lblHighlightingValue4, "6, 32, 3, 1");

		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "2, 36, 7, 1, fill, fill");

		parentsList = new JList<String>();
		parentsList.setFont(new Font("Dialog", Font.PLAIN, 12));
		scrollPane_1.setViewportView(parentsList);

		JLabel lblPagesHeading = new JLabel("Pages");
		lblPagesHeading.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblPagesHeading, "2, 38, 7, 1");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 40, 7, 1, fill, fill");

		pagesList = new JList<String>();
		pagesList.setFont(new Font("Dialog", Font.PLAIN, 12));
		scrollPane.setViewportView(pagesList);

		JLabel lblParentsHeading = new JLabel("Parent Categories");
		lblParentsHeading.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblParentsHeading, "2, 34, 7, 1");

		addComponentListeners();

		updateLegend();
		updateOverallValues();
		clearSelectionValues();
	}

	private void addComponentListeners() {
		btnEditComment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.editCommentButtonClicked(e);
			}
		});
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
			updateOverallValues();
			updateLegend();
			clearSelectionValues();
		}

		if (property.equals(Controller.commentChange)) {
			updateComment();
		}

	}

	private void updateOverallValues() {
		lblCategoriesOverallValue
				.setText("" + model.getStats().getCategories());
		lblPagesOverallValue.setText("" + model.getStats().getPages());
	}

	private void clearSelectionValues() {
		lblSelectedNodeValue.setText("");
		lblPagesValue.setText("");
		lblPagesTransitiveValue.setText("");
		lblSubcategoriesValue.setText("");
		lblSubcategoriesTransitiveValue.setText("");
		lblParentCategoriesValue.setText("");
		pagesList.setModel(new DefaultListModel<String>());
		parentsList.setModel(new DefaultListModel<String>());
		lblCommentValue.setText("");
		btnEditComment.setEnabled(false);
	}

	private void updateSelectionValues() {
		CategoryTreeNode node = model.getSelectedNode();
		lblSelectedNodeValue.setText(node.getTitle());
		lblPagesValue.setText("" + node.getPages());
		lblPagesTransitiveValue.setText("" + node.getPagesTransitive());
		lblSubcategoriesValue.setText("" + node.getSubcategories());
		lblSubcategoriesTransitiveValue.setText(""
				+ node.getSubcategoriesTransitive());
		lblParentCategoriesValue.setText("" + node.getParentCategories());
		pagesList.setListData(node.getPageStrings());
		parentsList.setListData(node.getParentCategoryStrings());

		if (model.getSelectedNode().isRoot()) {
			btnEditComment.setEnabled(false);
		} else {
			btnEditComment.setEnabled(true);
		}
		
		updateComment();
	}

	private void updateComment() {
		CategoryTreeNode node = model.getSelectedNode();
		lblCommentValue.setText(node.getComment());
	}

	private void updateLegend() {
		lblHighlightingValue0.setText(model.getHighlightingText0());
		lblHighlightingValue1.setText(model.getHighlightingText1());
		lblHighlightingValue2.setText(model.getHighlightingText2());
		lblHighlightingValue3.setText(model.getHighlightingText3());
		lblHighlightingValue4.setText(model.getHighlightingText4());
	}

}
