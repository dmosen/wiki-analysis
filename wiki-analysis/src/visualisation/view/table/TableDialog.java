package visualisation.view.table;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;

import schemas.categoryschema.CategoryGraph;
import visualisation.model.CategoryTableModel;

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
public class TableDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	CategoryTableModel model;

	private String comment;

	private JButton okButton;
	private JButton btnExportAsCsv;

	private final JFileChooser fc = new JFileChooser();
	private JPanel panel;
	private JLabel lblComment;
	private JEditorPane editorPane;
	private JScrollPane scrollPane_1;

	/**
	 * Create the dialog.
	 */
	public TableDialog(CategoryGraph graph) {
		setTitle("Blacklisted or commented categories");

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);

		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("150dlu"), }, new RowSpec[] { RowSpec
						.decode("default:grow"), }));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "1, 1, fill, fill");
			{
				table = new JTable();
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.getTableHeader().setResizingAllowed(true);
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);

				model = new CategoryTableModel(graph);
				table.setModel(model);
			}
		}
		{
			panel = new JPanel();
			contentPanel.add(panel, "3, 1, fill, fill");
			panel.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec
					.decode("default:grow"), }, new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,
					RowSpec.decode("default:grow"), }));
			{
				lblComment = new JLabel("Commet");
				lblComment.setFont(new Font("Tahoma", Font.BOLD, 11));
				panel.add(lblComment, "1, 1");
			}
			{
				scrollPane_1 = new JScrollPane();
				panel.add(scrollPane_1, "1, 2, fill, fill");
				{
					editorPane = new JEditorPane();
					editorPane.setEnabled(false);
					scrollPane_1.setViewportView(editorPane);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FormLayout(
					new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
							ColumnSpec.decode("default:grow"),
							FormFactory.RELATED_GAP_COLSPEC,
							FormFactory.DEFAULT_COLSPEC,
							FormFactory.UNRELATED_GAP_COLSPEC,
							ColumnSpec.decode("47px"),
							FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
							FormFactory.LINE_GAP_ROWSPEC,
							RowSpec.decode("23px"),
							FormFactory.RELATED_GAP_ROWSPEC, }));
			{
				btnExportAsCsv = new JButton("Export as CSV file ...");
				btnExportAsCsv.setHorizontalAlignment(SwingConstants.LEFT);
			}
			buttonPane.add(btnExportAsCsv, "2, 2, left, top");
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			buttonPane.add(okButton, "6, 2, left, top");
		}
		addComponentListeners();
	}

	private void addComponentListeners() {

		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				int selectedRow = table.getSelectedRow();

				if (selectedRow >= 0) {
					editorPane.setEnabled(model.isCellEditable(selectedRow, 2));
					editorPane.setText((String) model
							.getValueAt(selectedRow, 3));
				}
			}
		});

		editorPane.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				textChanged(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				textChanged(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			private void textChanged(DocumentEvent e) {
				try {
					comment = e.getDocument().getText(0,
							e.getDocument().getLength());
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				model.setValueAt(comment, table.getSelectedRow(), 3);
			}
		});

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnExportAsCsv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = fc.showSaveDialog(TableDialog.this);

				if (value == JFileChooser.APPROVE_OPTION) {
					try {
						writeCSVFile(fc.getSelectedFile());
						JOptionPane.showMessageDialog(TableDialog.this,
								"Saved to " + fc.getSelectedFile() + ".",
								"Success", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(
								TableDialog.this,
								"Error on writing to file "
										+ fc.getSelectedFile() + ".", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}
			}
		});
	}

	private void writeCSVFile(File file) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		for (int col = 0; col < table.getColumnCount(); col++) {
			bw.write(table.getColumnName(col));
			if (col < table.getColumnCount() - 1) {
				bw.write(",");
			}
		}
		bw.newLine();

		TableModel model = table.getModel();

		for (int row = 0; row < model.getRowCount(); row++) {
			for (int col = 0; col < model.getColumnCount(); col++) {
				Object obj = model.getValueAt(row, col);
				String str;
				if (obj == null) {
					str = "null";
				} else {
					str = obj.toString();
				}
				bw.write("\"" + str + "\"");
				if (col < model.getColumnCount() - 1) {
					bw.write(",");
				}
			}
			bw.newLine();
		}
		bw.close();
	}

	public boolean showDialog() {
		setVisible(true);
		return model.isBlacklistingChanged();
	}

}
