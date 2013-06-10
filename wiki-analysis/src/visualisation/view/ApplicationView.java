package visualisation.view;

import graph.GraphExtractor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import schemas.categoryschema.CategoryGraph;
import schemas.categoryschema.CategorySchema;
import utils.WikipediaAnalysis;
import visualisation.controller.Controller;
import visualisation.model.CategoryTreeModel;
import visualisation.view.extract.ExtractDialog;
import visualisation.view.table.TableDialog;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jidesoft.plaf.LookAndFeelFactory;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.ImplementationType;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class ApplicationView {

	private final String JSON_SUFFIX = ".json";
	private final String WIKITAX_SUFFIX = ".wikitax";
	private final String FILE = "data/simple_graph.tg";

	private Controller controller;
	private CategoryTreeModel model;
	private CategoryGraph graph;

	private JFrame frmCategoryTreeExtraction;
	private JFileChooser saveFileChooser;
	private JFileChooser exportJSONFileChooser;

	private JMenuItem mntmLoad;
	private JMenuItem mntmExtract;
	private JMenuItem mntmSave;

	private JMenuItem mntmTable;
	private JMenuItem mntmExportAsJson;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationView window = new ApplicationView();
					window.frmCategoryTreeExtraction.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplicationView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LookAndFeelFactory.installDefaultLookAndFeelAndExtension();

		graph = null;
		model = null;

		try {
			graph = GraphIO.loadGraphFromFile(FILE, CategorySchema.instance(),
					ImplementationType.STANDARD, null);
			model = new CategoryTreeModel(graph);
		} catch (GraphIOException e) {
			JOptionPane
					.showMessageDialog(
							frmCategoryTreeExtraction,
							"Error on loading file "
									+ new File(FILE).getAbsolutePath()
									+ ".\nMake sure it is readable and has the correct format.",
							"Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}

		Controller controller = new Controller();
		controller.setModel(model);
		this.controller = controller;

		exportJSONFileChooser = new JFileChooser();
		exportJSONFileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "JSON files";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}
				return f.getName().toLowerCase().endsWith(JSON_SUFFIX);
			}
		});

		saveFileChooser = new JFileChooser();
		saveFileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Wikitax files";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}
				return f.getName().toLowerCase().endsWith(WIKITAX_SUFFIX);
			}
		});

		frmCategoryTreeExtraction = new JFrame();
		frmCategoryTreeExtraction.setTitle("WikiTax");
		frmCategoryTreeExtraction.setBounds(100, 100, 1000, 700);
		frmCategoryTreeExtraction
				.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCategoryTreeExtraction.getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("pref:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.PREF_COLSPEC, },
						new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
								RowSpec.decode("fill:pref:grow"),
								FormFactory.LINE_GAP_ROWSPEC,
								FormFactory.PREF_ROWSPEC, }));

		TreePanel treePanel = new TreePanel(model, controller);
		frmCategoryTreeExtraction.getContentPane().add(treePanel,
				"2, 2, fill, fill");
		model.addPropertyChangeListener(treePanel);

		InfoPanel infoPanel = new InfoPanel(model, controller);
		frmCategoryTreeExtraction.getContentPane().add(infoPanel,
				"4, 2, fill, fill");
		model.addPropertyChangeListener(infoPanel);

		ControlPanel controlPanel = new ControlPanel(controller);
		frmCategoryTreeExtraction.getContentPane().add(controlPanel,
				"2, 4, 3, 1, fill, fill");

		JMenuBar menuBar = new JMenuBar();
		frmCategoryTreeExtraction.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmExtract = new JMenuItem("Extract ...");
		mnFile.add(mntmExtract);

		mntmLoad = new JMenuItem("Load ...");
		mnFile.add(mntmLoad);

		mntmSave = new JMenuItem("Save ...");
		mnFile.add(mntmSave);

		mntmExportAsJson = new JMenuItem("Export as JSON ...");
		mnFile.add(mntmExportAsJson);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		mntmTable = new JMenuItem("Table ...");
		mnView.add(mntmTable);

		addComponentListeners();

	}

	private void addComponentListeners() {
		mntmLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadGraph();
			}

		});

		mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile(saveFileChooser, WIKITAX_SUFFIX);
			}
		});

		mntmExtract.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ExtractDialog dialog = new ExtractDialog();
				GraphExtractor extractor = dialog.showDialog();
				if (extractor != null) {
					graph = extractor.getGraph();
					controller.graphChanged(graph);
				}
			}
		});

		mntmExportAsJson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile(exportJSONFileChooser, JSON_SUFFIX);
			}
		});

		mntmTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TableDialog dialog = new TableDialog(graph);
				if (dialog.showDialog()) {
					controller.reloadGraph();
				}
			}
		});
	}

	private void loadGraph() {
		int value = saveFileChooser.showOpenDialog(frmCategoryTreeExtraction);

		if (value == JFileChooser.APPROVE_OPTION) {
			File file = saveFileChooser.getSelectedFile();

			CategoryGraph tGraph = null;
			try {
				tGraph = GraphIO.loadGraphFromFile(file.getAbsolutePath(),
						CategorySchema.instance(), ImplementationType.STANDARD,
						null);
				if (tGraph != null) {
					graph = tGraph;
					controller.graphChanged(graph);
				}
			} catch (GraphIOException e1) {
				JOptionPane
						.showMessageDialog(
								frmCategoryTreeExtraction,
								"Error on loading file "
										+ file.getAbsolutePath()
										+ ".\nMake sure it is readable and has the correct format.",
								"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void saveFile(JFileChooser fileChooser, String suffix) {
		fileChooser.setDialogTitle("Save graph");
		int value = fileChooser.showSaveDialog(frmCategoryTreeExtraction);

		if (value == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			if (!file.getPath().toLowerCase().endsWith(suffix)) {
				file = new File(file.getPath() + suffix);
			}

			int override = JOptionPane.YES_OPTION;
			if (file.exists()) {
				override = JOptionPane.showConfirmDialog(
						frmCategoryTreeExtraction, "\"" + file.getPath()
								+ "\" already exists. Overwrite?", "File exists",
						JOptionPane.YES_NO_OPTION);
			}

			if (override == JOptionPane.YES_OPTION) {
				try {
					if (suffix == WIKITAX_SUFFIX) {
						GraphIO.saveGraphToFile(graph, file.getPath(), null);
					} else if (suffix == JSON_SUFFIX) {
						WikipediaAnalysis.saveGraphAsJSON(graph, file);
					}
					JOptionPane.showMessageDialog(frmCategoryTreeExtraction,
							"Saved to \"" + file.getAbsolutePath() + "\".",
							"Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(
							frmCategoryTreeExtraction,
							"Error on writing to file "
									+ file.getAbsolutePath() + ".", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (GraphIOException e) {
					JOptionPane
							.showMessageDialog(
									frmCategoryTreeExtraction,
									"Error on loading file "
											+ new File(FILE).getAbsolutePath()
											+ ".\nMake sure it is readable and has the correct format.",
									"Error", JOptionPane.ERROR_MESSAGE);
				} catch (AlgorithmTerminatedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
