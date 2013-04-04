package utils;

import graph.BlacklistedGraphDFS;
import graph.BlacklistedOrCommentedEdgeVisitor;
import graph.GraphStats;
import graph.JSONGraphExportVisitor;
import graph.ReachabilityCountVisitor;
import graph.StatisticalVisitor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import schemas.categoryschema.Category;
import schemas.categoryschema.CategoryGraph;
import schemas.categoryschema.HasSubcategory;
import visualisation.model.CategoryTreeModel;
import visualisation.model.CategoryTreeNode;
import de.uni_koblenz.jgralab.Graph;
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

	/**
	 * Saves the category graph as JSON file
	 * 
	 * @throws AlgorithmTerminatedException
	 */
	public static void saveGraphAsJSON(Graph graph, File file)
			throws IOException, AlgorithmTerminatedException {
		IterativeDepthFirstSearch dfs = new IterativeDepthFirstSearch(graph);
		JSONGraphExportVisitor visitor = new JSONGraphExportVisitor();
		dfs.addVisitor(visitor);
		dfs.execute();

		BufferedWriter jsonWriter = new BufferedWriter(new FileWriter(file));
		jsonWriter.write(visitor.getJSON().toJSONString());
		jsonWriter.close();
	}

	/**
	 * Computes statistical data on the given category graph
	 * 
	 * @param graph
	 * @return
	 */
	public static GraphStats computeStatistics(CategoryGraph graph) {
		// Calculate simple statistics for the graph
		BlacklistedGraphDFS dfs = new BlacklistedGraphDFS();
		StatisticalVisitor statisticalVisitor = new StatisticalVisitor(true);
		dfs.addVisitor(statisticalVisitor);
		dfs.execute(graph);
		GraphStats stats = statisticalVisitor.getGraphStats();
		dfs.removeVisitor(statisticalVisitor);

		// Calculate reachability count for every vertex
		ReachabilityCountVisitor reachabilityCountVisitor = new ReachabilityCountVisitor();
		dfs.addVisitor(reachabilityCountVisitor);
		for (Category vertex : graph.getCategoryVertices()) {
			dfs.execute(graph, vertex);
		}
		return stats;
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
	public static CategoryTreeModel createCategoryTreeModel(CategoryGraph graph) {

		CategoryTreeModel model = new CategoryTreeModel(graph);

		computeStatistics(graph);

		return model;
	}

	public static ArrayList<HasSubcategory> getBlacklistedOrCommentedEdges(
			Graph graph) throws AlgorithmTerminatedException {
		IterativeDepthFirstSearch dfs = new IterativeDepthFirstSearch(graph);
		BlacklistedOrCommentedEdgeVisitor visitor = new BlacklistedOrCommentedEdgeVisitor();
		dfs.addVisitor(visitor);
		dfs.execute();
		return visitor.getEdges();
	}

}
