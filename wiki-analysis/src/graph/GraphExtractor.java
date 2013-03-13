package graph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.SAXException;

import utils.WikipediaAPI;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.ImplementationType;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class GraphExtractor {

	public static final String levelChange = "levelChange";
	public static final String categoryChange = "categoryChange";

	private PropertyChangeSupport propertyChangeSupport;

	private String rootCategory;
	private Graph graph;
	private GraphProperties gp;
	private LinkedList<Vertex> queue;
	private int maxLevel;
	private HashMap<String, Vertex> categoryMap;
	private HashMap<String, Vertex> pageMap;
	private boolean extractPages;

	private List<List<Vertex>> removedCategoryPaths;

	public GraphExtractor(String rootCategory) {
		this(rootCategory, Integer.MAX_VALUE);
	}

	public void setExtractPages(boolean value) {
		extractPages = value;
	}

	public GraphExtractor(String rootCategory, int maxLevel) {
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		this.rootCategory = rootCategory;
		this.maxLevel = maxLevel;
		removedCategoryPaths = new ArrayList<List<Vertex>>();
		extractPages = true;
	}

	public Graph getGraph() {
		return graph;
	}

	public LinkedList<Vertex> getQueue() {
		return queue;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public List<List<Vertex>> getRemovedCategoryPaths() {
		return removedCategoryPaths;
	}

	public void incrementMaxLevel() {
		this.maxLevel++;
	}

	/**
	 * removes the given vertex form the current maxLevel if it is located at
	 * the current maxLevel, adds the path of the removed vertex to the removed
	 * category paths.
	 * 
	 * @param v
	 *            the vertex to be removed from the current maxLevel
	 * @return true if the vertex was removed, false otherwise
	 */
	public boolean removeAtMaxLevel(Vertex vertex) {
		if (vertex != null && queue.contains(vertex)) {

			Vertex child = vertex;
			Vertex parent = null;

			LinkedList<Vertex> path = new LinkedList<Vertex>();
			path.addFirst(vertex);
			do {
				if (child.getDegree(gp.subCategoryLinkEC, EdgeDirection.IN) > 0) {
					Edge e = child.getFirstIncidence(gp.subCategoryLinkEC,
							EdgeDirection.IN);
					parent = e.getAlpha();
					path.addFirst(parent);
					child = parent;
				} else {
					parent = null;
				}
			} while (parent != null);

			if (!path.isEmpty()) {
				removedCategoryPaths.add(path);

				List<Edge> removeEdges = new ArrayList<Edge>();
				List<Vertex> removeVertices = new ArrayList<Vertex>();

				// delete the vertex itself
				removeVertices.add(vertex);
				categoryMap.remove(vertex.getAttribute("title"));
				queue.remove(vertex);

				// delete all sub category links pointing to the vertex
				for (Edge e : vertex.incidences(gp.subCategoryLinkEC,
						EdgeDirection.IN)) {
					removeEdges.add(e);
				}

				// delete all page links pointing from the vertex to a page
				for (Edge e : vertex.incidences(gp.pageLinkEC,
						EdgeDirection.OUT)) {
					removeEdges.add(e);
					// delete only these pages which are not contained in
					// another category
					if (e.getOmega().getDegree(gp.pageLinkEC, EdgeDirection.IN) == 1) {
						removeVertices.add(e.getOmega());
						pageMap.remove(e.getOmega().getAttribute("title"));
					}
				}

				// process the deletion of edges
				for (Edge e : removeEdges) {
					graph.deleteEdge(e);
				}

				// process the deletion of vertices
				for (Vertex v : removeVertices) {
					graph.deleteVertex(v);
				}
				
				return true;
			}
		}
		return false;
	}

	public Graph extract() throws NoSuchAttributeException, SAXException,
			IOException, InterruptedException {

		if (queue == null) {
			initBfs();
		}

		String lastCategory = "";
		String currentCategory = "";

		Integer lastLevel = null;
		Integer currentLevel = null;

		// bfs
		while (!queue.isEmpty()) {

			Vertex currentVertex = queue.peek();
			currentCategory = currentVertex.getAttribute("title");
			propertyChangeSupport.firePropertyChange(
					GraphExtractor.categoryChange, lastCategory,
					currentCategory);

			currentLevel = currentVertex.getAttribute("level");
			propertyChangeSupport.firePropertyChange(
					GraphExtractor.levelChange, lastLevel, currentLevel);

			// stop if queue contains all categories from maxLevel
			if (currentLevel >= maxLevel) {
				return graph;
			}

			queue.poll();
			System.out.println(currentLevel + "\t" + currentCategory);

			if (extractPages) {

				List<String> pages = WikipediaAPI.getPages(currentCategory);

				// add all contained pages to the category
				for (String page : pages) {
					Vertex pageVertex;
					if (!pageMap.containsKey(page)) {
						pageVertex = graph.createVertex(gp.pageNodeVC);
						pageVertex.setAttribute("title", page);
						pageMap.put(page, pageVertex);
					} else {
						pageVertex = pageMap.get(page);
					}
					graph.createEdge(gp.pageLinkEC, currentVertex, pageVertex);
				}
			}

			List<String> subCategories = WikipediaAPI
					.getSubCategories((String) currentVertex
							.getAttribute("title"));

			// add all next categories to the queue if not seen already
			for (String nextCategory : subCategories) {
				// if category not seen already and level < maximum level, add
				// new vertex and edge
				if (!categoryMap.containsKey(nextCategory)) {
					Vertex nextVertex = graph.createVertex(gp.categoryNodeVC);
					nextVertex.setAttribute("title", nextCategory);
					nextVertex.setAttribute("level", currentLevel + 1);
					queue.add(nextVertex);
					categoryMap.put(nextCategory, nextVertex);
					graph.createEdge(gp.subCategoryLinkEC, currentVertex,
							nextVertex);
				}
				// if category already seen, add an edge only and report a frond
				else {
					System.out.println("Frond from " + currentCategory + " to "
							+ nextCategory + " detected!");
					graph.createEdge(gp.subCategoryLinkEC, currentVertex,
							categoryMap.get(nextCategory));
				}
			}
			lastCategory = currentCategory;
			lastLevel = currentLevel;
		}

		return graph;
	}

	private void initBfs() {
		gp = GraphProperties.getInstance();

		// Create graph conforming to the schema
		graph = gp.schema.createGraph(ImplementationType.GENERIC);

		categoryMap = new HashMap<String, Vertex>();

		pageMap = new HashMap<String, Vertex>();

		// Create first vertex
		Vertex root = graph.createVertex(gp.categoryNodeVC);
		root.setAttribute("title", rootCategory);
		root.setAttribute("level", 0);
		propertyChangeSupport.firePropertyChange(GraphExtractor.levelChange,
				null, 0);

		// Add the fist vertex to the map
		categoryMap.put(rootCategory, root);

		// init the queue with the root node
		queue = new LinkedList<Vertex>();
		queue.add(root);
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		propertyChangeSupport.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		propertyChangeSupport.removePropertyChangeListener(l);
	}
}
