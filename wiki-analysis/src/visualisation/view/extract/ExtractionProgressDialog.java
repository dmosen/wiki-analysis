package visualisation.view.extract;

import graph.GraphExtractor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

	private JLabel lblCurrentCategoryValue;

	private JLabel lblLevelValue;

	/**
	 * Create the dialog.
	 */
	public ExtractionProgressDialog(GraphExtractor extractor) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.extractor = extractor;
		
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
						setVisible(false);
						dispose();
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
					extractor.extract();
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
				if (evt.getPropertyName().equals(GraphExtractor.levelChange)) {
					lblLevelValue.setText(evt.getNewValue().toString());
				} else if (evt.getPropertyName().equals(
						GraphExtractor.categoryChange)) {
					lblCurrentCategoryValue.setText(evt.getNewValue()
							.toString());
				}
			}
		});
	}

	private void initView() {
		setTitle("Extracting Graph");
		setBounds(100, 100, 482, 149);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.UNRELATED_GAP_COLSPEC,
				FormFactory.GLUE_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.GLUE_ROWSPEC,}));
		{
			JLabel lblLevel = new JLabel("Level");
			lblLevel.setFont(new Font("Dialog", Font.BOLD, 12));
			contentPanel.add(lblLevel, "2, 2");
		}
		{
			JLabel lblCurrentCategory = new JLabel("Current category");
			lblCurrentCategory.setFont(new Font("Dialog", Font.BOLD, 12));
			contentPanel.add(lblCurrentCategory, "4, 2");
		}
		{
			lblLevelValue = new JLabel("");
			lblLevelValue.setFont(new Font("Dialog", Font.PLAIN, 12));
			contentPanel.add(lblLevelValue, "2, 4");
		}
		{
			lblCurrentCategoryValue = new JLabel("");
			lblCurrentCategoryValue.setFont(new Font("Dialog", Font.PLAIN, 12));
			contentPanel.add(lblCurrentCategoryValue, "4, 4");
		}
	}

}
