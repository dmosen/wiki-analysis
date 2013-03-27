package graph;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.SAXException;

import schemas.categoryschema.Category;
import schemas.categoryschema.CategoryGraph;
import schemas.categoryschema.CategorySchema;
import schemas.categoryschema.ContainsPage;
import schemas.categoryschema.Page;
import schemas.categoryschema.Subcategory;
import utils.WikipediaAPI;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.ImplementationType;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

/**
 * This class handles the extraction of a category graph in breadth first order
 * using {@link WikipediaAPI}. There are two possibilities of usage:
 * <ol>
 * <li>Extract the whole graph for a given root category. (i.e. the maximal
 * depth is set to <code>Interger.MAX_VALUE</code>)</li>
 * <li>Extract the graph until a given level is reached. Whenever the given
 * level is reached it is possible to exclude vertices from this level.</li>
 * </ol>
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class GraphExtractor {

	public static final String levelChange = "levelChange";
	public static final String categoryChange = "categoryChange";

	private PropertyChangeSupport propertyChangeSupport;

	private String rootCategory;
	private CategoryGraph graph;
	private LinkedList<Category> queue;
	private int maxLevel;
	private int maxReachedLevel;
	private HashMap<String, Category> categoryMap;
	private HashMap<String, Page> pageMap;
	private boolean extractPages;

	private List<List<Category>> removedCategoryPaths;

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
		removedCategoryPaths = new ArrayList<List<Category>>();
		extractPages = true;
	}

	public CategoryGraph getGraph() {
		return graph;
	}

	public LinkedList<Category> getQueue() {
		return queue;
	}

	public int getMaxReachedLevel() {
		return maxReachedLevel;
	}

	public List<List<Category>> getRemovedCategoryPaths() {
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
	public boolean removeAtMaxLevel(Category vertex) {
		if (vertex != null && queue.contains(vertex)) {

			Category child = vertex;
			Category parent = null;

			LinkedList<Category> path = new LinkedList<Category>();
			path.addFirst(vertex);
			do {
				if (child.getDegree(Subcategory.EC, EdgeDirection.IN) > 0) {
					Subcategory e = child
							.getFirstSubcategoryIncidence(EdgeDirection.IN);
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
				categoryMap.remove(vertex.get_title());
				queue.remove(vertex);

				// delete all subcategory links pointing to the vertex
				for (Subcategory e : vertex
						.getSubcategoryIncidences(EdgeDirection.IN)) {
					removeEdges.add(e);
				}

				// delete all page links pointing from the vertex to a page
				for (ContainsPage e : vertex
						.getContainsPageIncidences(EdgeDirection.OUT)) {
					removeEdges.add(e);
					// delete only these pages which are not contained in
					// another category
					if (e.getOmega().getDegree(ContainsPage.EC,
							EdgeDirection.IN) == 1) {
						removeVertices.add(e.getOmega());
						pageMap.remove(e.getOmega().get_title());
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

		String currentCategory = "";
		Integer currentLevel = 0;

		// bfs
		while (!queue.isEmpty()) {

			Category currentVertex = queue.peek();
			currentCategory = currentVertex.getAttribute("title");
			currentLevel = currentVertex.getAttribute("level");
			maxReachedLevel = Math.max(maxReachedLevel, currentLevel);

			// stop if queue contains all categories from maxLevel
			if (currentLevel >= maxLevel) {
				return graph;
			}

			propertyChangeSupport.firePropertyChange(
					GraphExtractor.categoryChange, null, currentLevel + " "
							+ currentCategory);

			System.out.println(currentLevel + " " + currentCategory);

			queue.poll();

			if (extractPages) {

				List<String> pages = WikipediaAPI.getPages(currentCategory);

				// add all contained pages to the category
				for (String page : pages) {
					Page pageVertex;
					if (!pageMap.containsKey(page)) {
						pageVertex = graph.createPage();
						pageVertex.set_title(page);
						pageMap.put(page, pageVertex);
					} else {
						pageVertex = pageMap.get(page);
					}
					graph.createContainsPage(currentVertex, pageVertex);
				}
			}

			List<String> subCategories = WikipediaAPI
					.getSubCategories(currentVertex.get_title());

			// add all next categories to the queue if not seen already
			for (String nextCategory : subCategories) {
				// if category not seen already and level < maximum level, add
				// new vertex and edge
				if (!categoryMap.containsKey(nextCategory)) {
					Category nextVertex = graph.createCategory();
					nextVertex.set_title(nextCategory);
					nextVertex.set_level(currentLevel + 1);
					queue.add(nextVertex);
					categoryMap.put(nextCategory, nextVertex);
					graph.createSubcategory(currentVertex, nextVertex);
				}
				// if category already seen, add an edge only and report a frond
				else {
					System.out.println("Frond from " + currentCategory + " to "
							+ nextCategory + " detected!");
					graph.createSubcategory(currentVertex,
							categoryMap.get(nextCategory));
				}
			}
		}

		return graph;
	}

	private void initBfs() {

		// Create graph conforming to the schema
		CategorySchema schema = CategorySchema.instance();
		graph = schema.createCategoryGraph(ImplementationType.STANDARD);

		categoryMap = new HashMap<String, Category>();

		pageMap = new HashMap<String, Page>();

		// Create first vertex
		Category root = graph.createCategory();
		root.set_title(rootCategory);
		root.set_level(0);
		propertyChangeSupport.firePropertyChange(GraphExtractor.levelChange,
				null, 0);

		// Add the fist vertex to the map
		categoryMap.put(rootCategory, root);

		// init the queue with the root node
		queue = new LinkedList<Category>();
		queue.add(root);
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		propertyChangeSupport.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		propertyChangeSupport.removePropertyChangeListener(l);
	}
}
