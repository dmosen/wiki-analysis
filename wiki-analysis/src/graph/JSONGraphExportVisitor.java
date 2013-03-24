package graph;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;
import de.uni_koblenz.jgralab.algolib.algorithms.search.visitors.DFSVisitorAdapter;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class JSONGraphExportVisitor extends DFSVisitorAdapter {

	private JSONArray jsonArray;
	private GraphProperties gp;

	@Override
	public void visitRoot(Vertex v) throws AlgorithmTerminatedException {
		super.visitRoot(v);
		gp = GraphProperties.getInstance();
		jsonArray = new JSONArray();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void visitVertex(Vertex v) throws AlgorithmTerminatedException {
		super.visitVertex(v);

		JSONObject jsonVertex;

		jsonVertex = new JSONObject();
		
		jsonVertex.put("id", v.getId());
		jsonVertex.put("title", v.getAttribute("title"));
		
		if (v.isInstanceOf(gp.categoryNodeVC)) {
			jsonVertex.put("type", "Category");
			jsonVertex.put("level", v.getAttribute("level"));
			jsonVertex.put("subcategories", v.getAttribute("subcategories"));
			jsonVertex.put("transitiveSubcategories", v.getAttribute("transitiveSubcategories"));
			jsonVertex.put("parentCategories", v.getAttribute("parentCategories"));
			jsonVertex.put("pages", v.getAttribute("pages"));
			jsonVertex.put("transitivePages", v.getAttribute("transitivePages"));
			jsonArray.add(jsonVertex);
		} else if (v.isInstanceOf(gp.pageNodeVC)) {
			jsonVertex.put("type", "Page");
			jsonArray.add(jsonVertex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void visitEdge(Edge e) throws AlgorithmTerminatedException {
		super.visitEdge(e);

		JSONObject jsonEdge;

		jsonEdge = new JSONObject();
		
		jsonEdge.put("start", e.getAlpha().getId());
		jsonEdge.put("end", e.getOmega().getId());
		
		if (e.isInstanceOf(gp.subcategoryLinkEC)) {
			jsonEdge.put("type", "Subcategory");
			jsonEdge.put("backwardArc", e.getAttribute("backwardArc"));
			jsonEdge.put("blacklisted", e.getAttribute("blacklisted"));
			jsonEdge.put("comment", e.getAttribute("comment"));
			jsonArray.add(jsonEdge);
		} else if (e.isInstanceOf(gp.containsPageLinkEC)) {
			jsonEdge.put("type", "ContainsPage");
			jsonArray.add(jsonEdge);
		}
	}

	public JSONArray getJSON() {
		return jsonArray;
	}
}
