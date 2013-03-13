package visualisation.view.extract;

import graph.GraphExtractor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import utils.WikipediaAPI;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import de.uni_koblenz.jgralab.Vertex;

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
	private JList<CheckBoxListItem> list;

	private CheckBoxListModel model;
	private JButton okButton;
	private JButton cancelButton;
	private JCheckBox ckbxExtractPages;

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
				RowSpec.decode("12dlu"), FormFactory.PREF_ROWSPEC,
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
			contentPanel.add(separator, "2, 5, 5, 1");
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
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "2, 8, 5, 1, fill, fill");
			{
				list = new JList<CheckBoxListItem>();
				scrollPane.setViewportView(list);
				list.setCellRenderer(new CheckBoxListRenderer());
			}
		}
		{
			btnExtractNextLevel = new JButton("Extract next level ...");
			contentPanel.add(btnExtractNextLevel, "2, 10, 3, 1");
		}
		{
			btnExtractWholeGraph = new JButton("Extract whole graph");
			contentPanel.add(btnExtractWholeGraph, "2, 12, 3, 1");
		}
		{
			ckbxExtractPages = new JCheckBox("extract pages for categories");
			ckbxExtractPages.setSelected(true);
			ckbxExtractPages.setFont(new Font("Dialog", Font.PLAIN, 12));
			contentPanel.add(ckbxExtractPages, "2, 14, 4, 1");
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
				String category = "Category:" + textField.getText();

				// valid category chosen
				if (WikipediaAPI.isValidPage(category)) {
					int answer = JOptionPane
							.showConfirmDialog(
									ExtractDialog.this,
									"This will start the whole extraction of the category graph for \"http://en.wikipedia.org/wiki/"
											+ category
											+ "\".\nDo you want to continue? ",
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
						if (model != null) {
							model.clear();
						}
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
				String category = "Category:" + textField.getText();

				// valid category chosen
				if (WikipediaAPI.isValidPage(category)) {
					if (extractor == null) {
						textField.setEditable(false);
						extractor = new GraphExtractor(category, 1);
						extractor.setExtractPages(ckbxExtractPages.isSelected());
						ckbxExtractPages.setEnabled(false);
						okButton.setEnabled(true);
					} else {
						for (Vertex v : model.getSelectedItems()) {
							extractor.removeAtMaxLevel(v);
						}
						extractor.incrementMaxLevel();
					}

					ExtractionProgressDialog extractionProgressDialog = new ExtractionProgressDialog(
							extractor);
					extractionProgressDialog.showDialog();
					lblLevelValue.setText("" + extractor.getMaxLevel());

					if (extractor.getQueue().isEmpty()) {
						if (model != null) {
							model.clear();
						}
						btnExtractNextLevel.setEnabled(false);
						btnExtractWholeGraph.setEnabled(false);
					} else {
						model = new CheckBoxListModel(extractor.getQueue());
						list.setModel(model);
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

		list.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					CheckBoxListItem item = list.getSelectedValue();
					int index = list.getSelectedIndex();

					model.setSelected(item, index, !item.isSelected());
				}
			};
		});

		list.addMouseListener(new MouseAdapter() {

			private int pressedIndex;

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				pressedIndex = getIndex(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				if (pressedIndex == getIndex(e) && pressedIndex > -1) {
					model.setSelected(model.getElementAt(pressedIndex),
							pressedIndex, !model.get(pressedIndex).isSelected());
				}
			}

			private int getIndex(MouseEvent e) {
				super.mouseClicked(e);
				int index = list.locationToIndex(e.getPoint());
				return index;
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
				setVisible(false);
				invalidate();
			}
		});
	}

	public GraphExtractor showDialog() {
		setVisible(true);
		return extractor;
	}
}

@SuppressWarnings("serial")
class CheckBoxListRenderer extends DefaultListCellRenderer {

	private Color selectionBackground;

	public CheckBoxListRenderer() {
		selectionBackground = UIManager.getColor("List.selectionBackground");
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (value instanceof CheckBoxListItem) {
			CheckBoxListItem checkBox = (CheckBoxListItem) value;
			if (isSelected) {
				checkBox.setBackground(selectionBackground);
			} else {
				checkBox.setBackground(list.getBackground());
			}
			return checkBox;
		} else {
			return super.getListCellRendererComponent(list, value, index,
					isSelected, cellHasFocus);
		}
	}
}