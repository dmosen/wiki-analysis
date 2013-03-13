package visualisation.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import visualisation.controller.Controller;
import visualisation.controller.HighlightingMode;

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
public class ControlPanel extends JPanel {

	private Controller controller;
	private JComboBox<HighlightingMode> cbHighlightingMode;
	private JButton btnRemoveMarkedCategories;
	
	/**
	 * Create the panel.
	 */
	public ControlPanel(Controller controller) {
		this.controller = controller;
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("pref:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,}));
		
		JLabel lblHighlightingMode = new JLabel("Highlighting mode");
		lblHighlightingMode.setFont(new Font("Dialog", Font.BOLD, 12));
		add(lblHighlightingMode, "2, 2");
		
		cbHighlightingMode = new JComboBox<HighlightingMode>(HighlightingMode.values());
		add(cbHighlightingMode, "2, 4, fill, default");
		
		btnRemoveMarkedCategories = new JButton("remove marked categories");
		add(btnRemoveMarkedCategories, "6, 4, right, default");
		
		addComponentListeners();
	}
	
	public void addComponentListeners() {
		cbHighlightingMode.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				controller.highlightingSelectionChanged(e);				
			}
		});
		
		btnRemoveMarkedCategories.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.removeMarkedCategoriesButtonClicked(e);				
			}
		});
	}
	
}
