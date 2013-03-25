package visualisation.view;

import graph.GraphExtractor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class ApplicationView {

	private Controller controller;
	private CategoryTreeModel model;
	private Graph graph;

	private JFrame frmCategoryTreeExtraction;
	private JFileChooser fileChooser;

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

		// TODO instead of directly loading a graph leave it to the user to load
		// an existing graph or to extract a model
		graph = null;
		model = null;

		try {
			graph = GraphIO.loadGraphFromFile("data/simple_graph.tg", null);
			model = new CategoryTreeModel(graph);
		} catch (GraphIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Controller controller = new Controller();
		controller.setModel(model);
		this.controller = controller;

		fileChooser = new JFileChooser();

		frmCategoryTreeExtraction = new JFrame();
		frmCategoryTreeExtraction.setTitle("Category tree extraction");
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
				showSaveGraphDialog();
			}
		});

		mntmExtract.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ExtractDialog dialog = new ExtractDialog();
				GraphExtractor extractor = dialog.showDialog();
				if (extractor != null) {
					graph = extractor.getGraph();
					if (extractor.getRemovedCategoryPaths().size() > 0) {
						showSaveRemovedCategoriesDialog(extractor);
					}
					controller.graphChanged(graph);
				}
			}
		});

		mntmExportAsJson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setDialogTitle("Save JSON graph");
				int value = fileChooser
						.showSaveDialog(frmCategoryTreeExtraction);

				if (value == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();

					try {
						WikipediaAnalysis.saveGraphAsJSON(graph, file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (AlgorithmTerminatedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		mntmTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TableDialog dialog = new TableDialog(graph);
				dialog.showDialog();
				controller.graphChanged(graph);
			}
		});
	}

	private void loadGraph() {
		int value = fileChooser.showOpenDialog(frmCategoryTreeExtraction);

		if (value == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			graph = null;
			try {
				graph = GraphIO.loadGraphFromFile(file.getAbsolutePath(), null);
			} catch (GraphIOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			controller.graphChanged(graph);
		}
	}

	private void showSaveGraphDialog() {
		fileChooser.setDialogTitle("Save graph");
		int value = fileChooser.showSaveDialog(frmCategoryTreeExtraction);

		if (value == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			try {
				GraphIO.saveGraphToFile(graph, file.getAbsolutePath(), null);
			} catch (GraphIOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void showSaveRemovedCategoriesDialog(GraphExtractor extractor) {
		fileChooser.setDialogTitle("Save removed category paths");
		int value = fileChooser.showSaveDialog(frmCategoryTreeExtraction);

		if (value == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write("");

				String result = "";
				for (List<Vertex> path : extractor.getRemovedCategoryPaths()) {
					for (Vertex v : path) {
						result = result + v.getAttribute("title") + " ";
					}
					result = result += "\n";
				}
				bw.write(result);
				bw.close();
			} catch (IOException e) {
				JOptionPane
						.showMessageDialog(
								frmCategoryTreeExtraction,
								"Could not write to file "
										+ file.getAbsolutePath()
										+ ". Please try again and choose a writable location.",
								"File access error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
