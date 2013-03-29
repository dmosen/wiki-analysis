package visualisation.view.extract;

import graph.GraphExtractor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import org.xml.sax.SAXException;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import de.uni_koblenz.jgralab.NoSuchAttributeException;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
@SuppressWarnings("serial")
public class ExtractionProgressDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private GraphExtractor extractor;
	private SwingWorker<GraphExtractor, Void> worker;
	private JScrollPane scrollPane;
	private JTextArea progressArea;
	private JButton btnOk;
	private JButton btnCancel;

	private boolean nextLevel;

	/**
	 * Create the dialog.
	 */
	public ExtractionProgressDialog(GraphExtractor extractor, boolean nextLevel) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.extractor = extractor;
		this.nextLevel = nextLevel;

		initView();
		initWorker();

	}

	public void showDialog() {
		this.setModal(true);

		worker.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {

				if (evt.getPropertyName().equals("state")) {
					if (worker.getState().equals(SwingWorker.StateValue.DONE)) {
						progressArea.append("DONE!");
						btnOk.setEnabled(true);
					}
				}

			}
		});

		worker.execute();

		this.setVisible(true);
	}

	private void initWorker() {
		worker = new SwingWorker<GraphExtractor, Void>() {

			@Override
			protected GraphExtractor doInBackground() {
				try {
					extractor.extract(nextLevel);
				} catch (NoSuchAttributeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return extractor;
			}

		};

		extractor.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName().equals(GraphExtractor.categoryChange)) {
					progressArea.append(evt.getNewValue().toString() + "\n");
					progressArea.setCaretPosition(progressArea.getDocument()
							.getLength());
				}
			}
		});
	}

	private void initView() {
		setTitle("Extracting Graph");
		setBounds(100, 100, 482, 290);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("50dlu"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("50dlu"),
						FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.GLUE_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "2, 2, 5, 1, fill, fill");
			{
				progressArea = new JTextArea();
				progressArea.setEditable(false);
				scrollPane.setViewportView(progressArea);
			}
		}
		{
			btnOk = new JButton("OK");
			btnOk.setEnabled(false);
			contentPanel.add(btnOk, "4, 4, fill, default");
		}
		{
			btnCancel = new JButton("Cancel");
			contentPanel.add(btnCancel, "6, 4");
		}

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				extractor.cancel();
				extractor.restoreState();
				dispose();
			}
		});
	}

}
