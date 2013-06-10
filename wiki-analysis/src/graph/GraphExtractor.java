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
import schemas.categoryschema.HasSubcategory;
import schemas.categoryschema.Page;
import utils.WikipediaAPI;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
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

	public static final String categoryChange = "categoryChange";

	private PropertyChangeSupport propertyChangeSupport;

	private String rootCategory;
	private CategoryGraph graph;

	private boolean cancelled;

	// current state
	private LinkedList<Category> queue;
	private int maxLevel;
	private int maxReachedLevel;
	private HashMap<String, Category> categoryMap;
	private HashMap<String, Page> pageMap;
	private boolean extractPages;
	private ArrayList<Category> removeAtMaxLevel;

	// saved state
	private ArrayList<Edge> createdEdges;
	private ArrayList<Vertex> createdVertices;
	private LinkedList<Category> savedQueue;
	private int savedMaxLevel;
	private int savedMaxReachedLevel;
	private HashMap<String, Category> savedCategoryMap;
	private HashMap<String, Page> savedPageMap;

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
		removeAtMaxLevel = new ArrayList<Category>();
		extractPages = true;
	}

	public CategoryGraph getGraph() {
		return graph;
	}

	public LinkedList<Category> getQueue() {
		return queue;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public int getMaxReachedLevel() {
		return maxReachedLevel;
	}

	public void incrementMaxLevel() {
		this.maxLevel++;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
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
			removeAtMaxLevel.add(vertex);
			return true;
		}
		return false;
	}

	private void processExcludedCategories(boolean remove) {
		for (Category vertex : removeAtMaxLevel) {
			if (vertex != null && queue.contains(vertex)) {

				if (remove) {
					queue.remove(vertex);
				}

				for (HasSubcategory e : vertex
						.getHasSubcategoryIncidences(EdgeDirection.IN)) {
					if (remove) {
						e.set_blacklisted(true);
						e.set_excluded(true);
						e.set_comment("excluded on extraction");
					} else {
						e.set_blacklisted(false);
						e.set_comment(null);
					}
				}
			}
		}
	}

	public void extract(boolean nextLevel) throws NoSuchAttributeException,
			SAXException, IOException, InterruptedException {
		cancelled = false;

		if (queue == null) {
			initBfs();
		}

		saveState();

		processExcludedCategories(true);

		if (nextLevel) {
			maxLevel++;
		} else {
			maxLevel = Integer.MAX_VALUE;
		}

		String currentCategory = "";
		Integer currentLevel = 0;

		// bfs
		while (!queue.isEmpty()) {
			if (cancelled) {
				return;
			}

			synchronized (this) {
				Category currentVertex = queue.peek();
				currentCategory = currentVertex.getAttribute("title");
				currentLevel = currentVertex.getAttribute("level");
				maxReachedLevel = Math.max(maxReachedLevel, currentLevel);

				// stop if queue contains all categories from maxLevel
				if (currentLevel >= maxLevel) {
					return;
				}

				propertyChangeSupport.firePropertyChange(
						GraphExtractor.categoryChange, null, currentLevel + " "
								+ currentCategory);

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
							createdVertices.add(pageVertex);
						} else {
							pageVertex = pageMap.get(page);
						}
						ContainsPage containsPageEdge = graph
								.createContainsPage(currentVertex, pageVertex);
						createdEdges.add(containsPageEdge);
					}
				}

				List<String> subCategories = WikipediaAPI
						.getSubCategories(currentVertex.get_title());

				// add all next categories to the queue if not seen already
				for (String nextCategory : subCategories) {
					// if category not seen already and level < maximum level,
					// add
					// new vertex and edge
					if (!categoryMap.containsKey(nextCategory)) {
						Category nextVertex = graph.createCategory();
						createdVertices.add(nextVertex);
						nextVertex.set_title(nextCategory);
						nextVertex.set_level(currentLevel + 1);

						queue.add(nextVertex);

						categoryMap.put(nextCategory, nextVertex);
						createdEdges.add(graph.createHasSubcategory(
								currentVertex, nextVertex));
					}
					// if category already seen, add an edge only and report a
					// frond
					else {
						createdEdges.add(graph.createHasSubcategory(
								currentVertex, categoryMap.get(nextCategory)));
					}
				}
			}
		}
	}

	public void cancel() {
		cancelled = true;
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

		// Add the fist vertex to the map
		categoryMap.put(rootCategory, root);

		// init the queue with the root node
		queue = new LinkedList<Category>();
		queue.add(root);
	}

	@SuppressWarnings("unchecked")
	private synchronized void saveState() {
		createdEdges = new ArrayList<Edge>();
		createdVertices = new ArrayList<Vertex>();

		savedQueue = (LinkedList<Category>) queue.clone();
		savedMaxLevel = maxLevel;
		savedMaxReachedLevel = maxReachedLevel;
		savedCategoryMap = (HashMap<String, Category>) categoryMap.clone();
		savedPageMap = (HashMap<String, Page>) pageMap.clone();
	}

	public synchronized void restoreState() {

		for (Edge e : createdEdges) {
			graph.deleteEdge(e);
		}

		for (Vertex v : createdVertices) {
			graph.deleteVertex(v);
		}

		queue = savedQueue;
		maxLevel = savedMaxLevel;
		maxReachedLevel = savedMaxReachedLevel;
		categoryMap = savedCategoryMap;
		pageMap = savedPageMap;
		processExcludedCategories(false);
		removeAtMaxLevel.clear();
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		propertyChangeSupport.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		propertyChangeSupport.removePropertyChangeListener(l);
	}
}
