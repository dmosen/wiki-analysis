package visualisation.view.extract;

import graph.GraphExtractor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import schemas.categoryschema.Category;
import utils.WikipediaAPI;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jidesoft.swing.CheckBoxListWithSelectable;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
@SuppressWarnings("serial")
public class ExtractDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton btnExtractWholeGraph;

	private GraphExtractor extractor;
	private JButton btnExtractNextLevel;
	private JLabel lblLevelValue;
	private CheckBoxListWithSelectable list;

	private JButton okButton;
	private JButton cancelButton;
	private JCheckBox ckbxExtractPages;
	private JLabel lblSelectCategoriesTo;

	/**
	 * Create the dialog.
	 */
	public ExtractDialog() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);
		extractor = null;

		setTitle("Extract category graph from Wikipedia");
		setBounds(100, 100, 469, 523);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		FormLayout fl_contentPanel = new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;pref)"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;pref)"),
				ColumnSpec.decode("max(100dlu;pref)"),
				ColumnSpec.decode("pref:grow"),
				FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				RowSpec.decode("12dlu"), FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, });
		contentPanel.setLayout(fl_contentPanel);
		{
			JLabel lblRootCategory = new JLabel("Root category");
			lblRootCategory.setFont(new Font("Dialog", Font.BOLD, 12));
			contentPanel.add(lblRootCategory, "2, 2, 3, 1");
		}
		{
			JLabel lblCategory = new JLabel("Category:");
			lblCategory.setFont(new Font("Dialog", Font.PLAIN, 12));
			contentPanel.add(lblCategory, "2, 4");
		}
		{
			textField = new JTextField();
			contentPanel.add(textField, "4, 4, 2, 1, fill, default");
			textField.setColumns(10);
		}
		{
			JSeparator separator = new JSeparator();
			contentPanel.add(separator, "2, 7, 5, 1");
		}
		{
			JLabel lblCurrentLevel = new JLabel("Current level:");
			lblCurrentLevel.setFont(new Font("Dialog", Font.PLAIN, 12));
			contentPanel.add(lblCurrentLevel, "2, 6");
		}
		{
			lblLevelValue = new JLabel("0");
			lblLevelValue.setFont(new Font("Dialog", Font.PLAIN, 12));
			contentPanel.add(lblLevelValue, "4, 6");
		}
		{
			lblSelectCategoriesTo = new JLabel(
					"Select categories to be excluded:");
			lblSelectCategoriesTo.setFont(new Font("Dialog", Font.ITALIC, 12));
			contentPanel.add(lblSelectCategoriesTo, "2, 8, 5, 1");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "2, 10, 5, 1, fill, fill");
			{
				list = new CheckBoxListWithSelectable();
				scrollPane.setViewportView(list);
			}
		}
		{
			btnExtractNextLevel = new JButton("Extract next level ...");
			contentPanel.add(btnExtractNextLevel, "2, 12, 3, 1");
		}
		{
			btnExtractWholeGraph = new JButton("Extract whole graph");
			contentPanel.add(btnExtractWholeGraph, "2, 14, 3, 1");
		}
		{
			ckbxExtractPages = new JCheckBox("extract pages for categories");
			ckbxExtractPages.setSelected(true);
			ckbxExtractPages.setFont(new Font("Dialog", Font.PLAIN, 12));
			contentPanel.add(ckbxExtractPages, "2, 16, 4, 1");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		addComponentListeners();
	}

	private void addComponentListeners() {
		btnExtractWholeGraph.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String category = textField.getText();

				// valid category chosen
				if (WikipediaAPI.isValidCategory(category)) {

					// warn the user
					int answer = JOptionPane
							.showConfirmDialog(
									ExtractDialog.this,
									"This will start the whole extraction of the category graph for \"http://en.wikipedia.org/wiki/"
											+ category
											+ "\". This could take a lot of time. \nDo you want to continue? ",
									"Run extraction?",
									JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE);

					// extract the whole graph
					if (answer == JOptionPane.OK_OPTION) {
						if (extractor == null) {
							extractor = new GraphExtractor(category);
						} else {
							extractor.setMaxLevel(Integer.MAX_VALUE);
						}
						ExtractionProgressDialog extractionProgressDialog = new ExtractionProgressDialog(
								extractor);

						extractionProgressDialog.showDialog();
						btnExtractNextLevel.setEnabled(false);
						btnExtractWholeGraph.setEnabled(false);
						okButton.setEnabled(true);
					}
				}
				// invalid category chosen
				else {
					JOptionPane.showMessageDialog(ExtractDialog.this, "\""
							+ category + "\"" + " is not a valid category!",
							"Invalid category", JOptionPane.WARNING_MESSAGE);
				}
			}

		});

		btnExtractNextLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String category = textField.getText();

				// valid category chosen
				if (WikipediaAPI.isValidCategory(category)) {

					if (extractor == null) {
						// no graph extractor available => construct one
						textField.setEditable(false);
						extractor = new GraphExtractor(category, 1);
						extractor.setExtractPages(ckbxExtractPages.isSelected());
						ckbxExtractPages.setEnabled(false);
						okButton.setEnabled(true);
					} else {
						// graph extractor available => remove items that the
						// user has selected
						for (Object o : list.getSelectedObjects()) {
							if (o instanceof CheckBoxListItem) {
								CheckBoxListItem item = (CheckBoxListItem) o;
								extractor.removeAtMaxLevel(item.getVertex());
							}
						}
						extractor.incrementMaxLevel();
					}

					// show extraction progress to indicate that something is
					// happening
					ExtractionProgressDialog extractionProgressDialog = new ExtractionProgressDialog(
							extractor);
					extractionProgressDialog.showDialog();
					lblLevelValue.setText("" + extractor.getMaxLevel());

					// show items that were just extracted at the current level
					// to the user
					if (extractor.getQueue().isEmpty()) {
						list.setListData(new CheckBoxListItem[0]);
						btnExtractNextLevel.setEnabled(false);
						btnExtractWholeGraph.setEnabled(false);
					} else {
						CheckBoxListItem[] items = new CheckBoxListItem[extractor
								.getQueue().size()];
						int i = 0;
						for (Category v : extractor.getQueue()) {
							items[i++] = new CheckBoxListItem(v);
						}
						list.setListData(items);
					}
				}

				// invalid category chosen => inform user
				else {
					JOptionPane.showMessageDialog(ExtractDialog.this, "\""
							+ category + "\"" + " is not a valid category!",
							"Invalid category", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				invalidate();
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				extractor = null;
				dispose();
			}
		});
	}

	public GraphExtractor showDialog() {
		setVisible(true);
		return extractor;
	}
}