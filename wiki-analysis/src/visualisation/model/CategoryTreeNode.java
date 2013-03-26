package visualisation.model;

import graph.GraphProperties;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Vertex;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
@SuppressWarnings("serial")
public class CategoryTreeNode extends DefaultMutableTreeNode {

	GraphProperties gp = GraphProperties.getInstance();
	
	private Vertex vertex;
	private Edge edge;

	public CategoryTreeNode(Vertex vertex, Edge edge) {
		super(edge);
		this.vertex = vertex;
		this.edge = edge;
	}

	public boolean isBlacklisted() {
		if (edge != null) {
			return edge.getAttribute("blacklisted");
		}
		return false;
	}

	public void setBlacklisting(boolean value) {
		if (edge != null) {
			edge.setAttribute("blacklisted", value);
		}
	}

	public String getTitle() {
		return vertex.getAttribute("title");
	}

	public String getComment() {
		if (isRoot()) {
			return "root category";
		}
		return edge.getAttribute("comment");
	}

	public void setComment(String comment) {
		edge.setAttribute("comment", comment);
	}

	public CategoryTreeNode getParent() {
		return (CategoryTreeNode) super.getParent();
	}

	public CategoryTreeNode getChildAt(int index) {
		return (CategoryTreeNode) super.getChildAt(index);
	}

	public int getPages() {
		return vertex.getAttribute("pages");
	}

	public int getPagesTransitive() {
		return vertex.getAttribute("transitivePages");
	}

	public int getSubcategories() {
		return vertex.getAttribute("subcategories");
	}

	public int getSubcategoriesTransitive() {
		return vertex.getAttribute("transitiveSubcategories");
	}

	public String[] getPageStrings() {
		String[] result = new String[vertex.getDegree(
				GraphProperties.getInstance().containsPageLinkEC,
				EdgeDirection.OUT)];
		int i = 0;
		for (Edge e : vertex.incidences(
				GraphProperties.getInstance().containsPageLinkEC,
				EdgeDirection.OUT)) {
			result[i++] = ((String) e.getOmega().getAttribute("title"));
		}
		return result;
	}

	public String[] getParentCategoryStrings() {
		ArrayList<String> results = new ArrayList<String>();
		for (Edge e : vertex.incidences(
				GraphProperties.getInstance().subcategoryLinkEC,
				EdgeDirection.IN)) {
			if (!(Boolean) e.getAttribute("blacklisted")) {
				results.add((String) e.getAlpha().getAttribute("title"));
			}
		}
		return results.toArray(new String[results.size()]);
	}

	@Override
	public String toString() {
		return getTitle();
	}
}
