package visualisation.view.table;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import visualisation.model.CategoryTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import de.uni_koblenz.jgralab.Graph;

@SuppressWarnings("serial")
public class TableDialog extends JDialog {

	private Graph graph;
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	private JButton okButton;

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
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
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
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
