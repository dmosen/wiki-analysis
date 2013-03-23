package visualisation.view.table;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import visualisation.model.CategoryTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import de.uni_koblenz.jgralab.Graph;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
@SuppressWarnings("serial")
public class TableDialog extends JDialog {

	private Graph graph;

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private JButton okButton;
	private JButton btnExportAsCsv;

	private final JFileChooser fc = new JFileChooser();

	/**
	 * Create the dialog.
	 */
	public TableDialog(Graph graph) {
		setTitle("Blacklisted or commented categories");

		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setModal(true);

		this.graph = graph;

		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec
				.decode("default:grow"), }, new RowSpec[] { RowSpec
				.decode("default:grow"), }));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "1, 1, fill, fill");
			{
				table = new JTable();
				table.getTableHeader().setResizingAllowed(true);
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FormLayout(
					new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
							ColumnSpec.decode("594px:grow"),
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
			buttonPane.add(okButton, "4, 2, left, top");
		}
		addComponentListeners();
	}

	private void addComponentListeners() {
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
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
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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

	public void showDialog() {
		CategoryTableModel model = new CategoryTableModel(graph);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);

		setVisible(true);
	}

}
