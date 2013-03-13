package graph;


import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;
import de.uni_koblenz.jgralab.algolib.algorithms.search.visitors.DFSVisitorAdapter;

//FIXME Ignores blacklisting of edges and vertices

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
public class JSONVisitor extends DFSVisitorAdapter {

	private JSONObject root;
	private Stack<JSONObject> stack = new Stack<JSONObject>();
	private GraphProperties gp;

	@Override
	public void visitRoot(Vertex v) throws AlgorithmTerminatedException {
		super.visitRoot(v);
		gp = GraphProperties.getInstance();
		root = new JSONObject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void visitVertex(Vertex v) throws AlgorithmTerminatedException {
		super.visitVertex(v);

		if (v.isInstanceOf(gp.categoryNodeVC)) {

			JSONObject current;

			if (v.getId() == 1) {
				current = root;
			} else {
				current = new JSONObject();
			}

			// populate the current JSON object with data from vertex v
			modifyJSONObjectOnVisit(current, v);

			// Add an empty JSON array to collect children of the current JSON
			// object
			JSONArray currentArray = new JSONArray();
			current.put("children", currentArray);

			// Add current JSON object to the children of the superior JSON
			// object if existing
			if (!stack.isEmpty()) {
				if (stack.peek().get("children") instanceof JSONArray) {
					((JSONArray) stack.peek().get("children")).add(current);
				} else {
					new RuntimeException("JSONObject children of "
							+ stack.peek() + " should be of type "
							+ JSONArray.class);
				}
			}

			// push the current JSON object onto the stack
			stack.push(current);
		}
	}

	@Override
	public void leaveVertex(Vertex v) throws AlgorithmTerminatedException {
		super.leaveVertex(v);
		if (v.isInstanceOf(gp.categoryNodeVC)) {
			stack.pop();
		}
	}

	public JSONObject getJSON() {
		return root;
	}

	@SuppressWarnings("unchecked")
	private void modifyJSONObjectOnVisit(JSONObject current, Vertex v) {
		current.put("id", v.getId());
		current.put("name", v.getAttribute("title"));

		JSONObject data = new JSONObject();
		data.put("subCategories", v.getAttribute("subCategories"));
		data.put("transitiveSubCategories",
				v.getAttribute("transitiveSubCategories"));
		data.put("superCategories", v.getAttribute("superCategories"));
		data.put("pages", v.getAttribute("pages"));
		data.put("transitivePages", v.getAttribute("transitivePages"));

		current.put("data", data);
	}
}
