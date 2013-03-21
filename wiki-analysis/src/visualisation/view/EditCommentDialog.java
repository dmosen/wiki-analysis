package visualisation.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

@SuppressWarnings("serial")
public class EditCommentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JTextPane textPane;
	private JButton cancelButton;

	private boolean ok;

	/**
	 * Create the dialog.
	 */
	public EditCommentDialog(String category, String comment) {
		setModal(true);
		ok = false;

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"), }));
		{
			JLabel lblCategory = new JLabel(category);
			lblCategory.setFont(new Font("Dialog", Font.BOLD, 12));
			contentPanel.add(lblCategory, "2, 1");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "2, 3, fill, fill");
			{
				textPane = new JTextPane();
				if (comment != null) {
					textPane.setText(comment);
				}
				textPane.setFont(new Font("Dialog", Font.PLAIN, 12));
				scrollPane.setViewportView(textPane);
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
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		addComponentListeners();
	}

	private void addComponentListeners() {
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ok = true;
				setVisible(false);
				invalidate();
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ok = false;
				setVisible(false);
				invalidate();
			}
		});

	}

	public String showDialog() {
		setVisible(true);
		if (ok) {
			return textPane.getText();
		} else {
			return null;
		}
	}
}
