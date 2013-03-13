package utils;

import graph.BlacklistedGraphDFS;
import graph.CSVVisitor;
import graph.GraphExtractor;
import graph.GraphProperties;
import graph.GraphStats;
import graph.JSONVisitor;
import graph.ReachabilityCountVisitor;
import graph.StatisticalVisitor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.xml.sax.SAXException;

import visualisation.model.CategoryTreeModel;
import visualisation.model.CategoryTreeNode;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;
import de.uni_koblenz.jgralab.algolib.algorithms.search.IterativeDepthFirstSearch;

/**
 * This class provides convenience functions for constructing category graphs
 * from Wikipedia and processing the extracted graphs
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class WikipediaAnalysis {

	private static final String category = "Programming_languages";
	private static final String categoryTableFilename = category
			+ "_category_table.csv";
	private static final String pageTableFilename = category
			+ "_page_table.csv";

	/**
	 * Saves the category tree as JSON file
	 * 
	 * @throws AlgorithmTerminatedException
	 */
	public static void saveJSONTree(Graph graph) throws IOException,
			AlgorithmTerminatedException {
		IterativeDepthFirstSearch dfs = new IterativeDepthFirstSearch(graph);
		JSONVisitor visitor = new JSONVisitor();
		dfs.addVisitor(visitor);
		dfs.execute();

		BufferedWriter jsonWriter = new BufferedWriter(new FileWriter(new File(
				"json.js")));
		jsonWriter.write("var json = " + visitor.getJSON() + ";");
		jsonWriter.close();
	}

	/**
	 * Computes statistical data on the given category graph
	 * 
	 * @throws AlgorithmTerminatedException
	 */
	public static GraphStats computeStatistics(Graph graph) {
		// Calculate simple statistics for the graph
		BlacklistedGraphDFS dfs = new BlacklistedGraphDFS();
		StatisticalVisitor statisticalVisitor = new StatisticalVisitor();
		dfs.addVisitor(statisticalVisitor);
		dfs.execute(graph);
		GraphStats stats = statisticalVisitor.computeGraphStats();
		dfs.removeVisitor(statisticalVisitor);

		// Calculate reachability count for every vertex
		ReachabilityCountVisitor reachabilityCountVisitor = new ReachabilityCountVisitor();
		dfs.addVisitor(reachabilityCountVisitor);
		for (Vertex vertex : graph
				.vertices(GraphProperties.getInstance().categoryNodeVC)) {
			dfs.execute(graph, vertex);
		}

		return stats;
	}

	/**
	 * Creates CSV files from a given category graph. One file listing all
	 * categories and one listing all pages.
	 * 
	 * @param graph
	 * @throws AlgorithmTerminatedException
	 * @throws IOException
	 */
	public static void saveTables(Graph graph)
			throws AlgorithmTerminatedException, IOException {
		IterativeDepthFirstSearch dfs = new IterativeDepthFirstSearch(graph);
		CSVVisitor visitor = new CSVVisitor();
		dfs.addVisitor(visitor);
		dfs.execute();
		BufferedWriter categoryTable = new BufferedWriter(new FileWriter(
				new File(categoryTableFilename)));
		categoryTable.write(visitor.getCategoryTable());
		categoryTable.close();

		BufferedWriter pageTable = new BufferedWriter(new FileWriter(new File(
				pageTableFilename)));
		pageTable.write(visitor.getPageTable());
		pageTable.close();
	}

	/**
	 * Creates a category tree model from a previously saved graph conforming to
	 * the schema <code>category-schema.tg</code>. The {@link CategoryTreeModel}
	 * is composed of {@link CategoryTreeNode}s referring to vertices in the
	 * underlying graph. Other than the graph the tree can have several nodes
	 * referring the same vertex in the graph.
	 * 
	 * @param file
	 *            a TG-file containing the graph
	 * @return the corresponding category tree model
	 */
	public static CategoryTreeModel createCategoryTreeModel(File file) {
		// Load graph from file
		Graph graph = null;
		try {
			graph = GraphIO.loadGraphFromFile(file.getAbsolutePath(), null);
		} catch (GraphIOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return createCategoryTreeModel(graph);
	}

	/**
	 * Creates a category tree model from a graph conforming to the schema
	 * <code>category-schema.tg</code>. The {@link CategoryTreeModel} is
	 * composed of {@link CategoryTreeNode}s referring to vertices in the
	 * underlying graph. Other than the graph the tree can have several nodes
	 * referring the same vertex in the graph.
	 * 
	 * @param graph
	 * @return the corresponding category tree model
	 */
	public static CategoryTreeModel createCategoryTreeModel(Graph graph) {

		computeStatistics(graph);

		CategoryTreeModel model = new CategoryTreeModel(graph);

		return model;
	}

	/**
	 * Extracts a graph from Wikipedia consisting of categories for a given root
	 * category.
	 * 
	 * @param rootCategory
	 *            the root category
	 * @param maxLevel
	 *            the maximal depth at which the extraction is stopped
	 * @return
	 * @throws NoSuchAttributeException
	 * @throws SAXException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static Graph extractGraph(String rootCategory, int maxLevel)
			throws NoSuchAttributeException, SAXException, IOException,
			InterruptedException {
		Graph graph;
		GraphExtractor extractor = new GraphExtractor(rootCategory, maxLevel);

		System.out.println("Extracting Graph for " + rootCategory + " ...");
		graph = extractor.extract();
		return graph;
	}

}
