package graph;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import schemas.categoryschema.Category;
import schemas.categoryschema.ContainsPage;
import schemas.categoryschema.Page;
import schemas.categoryschema.Subcategory;

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

	@Override
	public void visitRoot(Vertex v) throws AlgorithmTerminatedException {
		super.visitRoot(v);
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
		
		if (v.isInstanceOf(Category.VC)) {
			Category c = (Category) v;
			jsonVertex.put("type", "Category");
			jsonVertex.put("level", c.get_level());
			jsonVertex.put("subcategories", c.get_subcategories());
			jsonVertex.put("transitiveSubcategories", c.get_transitiveSubcategories());
			jsonVertex.put("parentCategories", c.get_parentCategories());
			jsonVertex.put("pages", c.get_pages());
			jsonVertex.put("transitivePages", c.get_transitivePages());
			jsonArray.add(jsonVertex);
		} else if (v.isInstanceOf(Page.VC)) {
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
		
		if (e.isInstanceOf(Subcategory.EC)) {
			Subcategory s = (Subcategory) e;
			jsonEdge.put("type", "Subcategory");
			jsonEdge.put("backwardArc", s.is_backwardArc());
			jsonEdge.put("blacklisted", s.is_blacklisted());
			jsonEdge.put("comment", s.get_comment());
			jsonArray.add(jsonEdge);
		} else if (e.isInstanceOf(ContainsPage.EC)) {
			jsonEdge.put("type", "ContainsPage");
			jsonArray.add(jsonEdge);
		}
	}

	public JSONArray getJSON() {
		return jsonArray;
	}
}
