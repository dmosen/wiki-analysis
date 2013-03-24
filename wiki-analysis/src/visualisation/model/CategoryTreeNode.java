package visualisation.model;

import graph.GraphProperties;

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

	private Vertex vertex;
	private boolean blacklisted;

	public CategoryTreeNode(Vertex vertex) {
		super(vertex);
		this.vertex = vertex;
	}

	public boolean isBlacklisted() {
		return blacklisted;
	}

	public void setBlacklisting(boolean value) {
		this.blacklisted = value;
	}

	public String getTitle() {
		return vertex.getAttribute("title");
	}

	public String getComment() {
		Edge parentEdge = getParentEdge();

		if (parentEdge != null) {
			return parentEdge.getAttribute("comment");
		}

		if (isRoot()) {
			return "root category";
		}

		return "";
	}

	public void setComment(String comment) {
		Edge parentEdge = getParentEdge();

		if (parentEdge != null) {
			if (comment.isEmpty()) {
				parentEdge.setAttribute("comment", null);
			} else {
				parentEdge.setAttribute("comment", comment);
			}
		}
	}

	private Edge getParentEdge() {
		Vertex child = getVertex();
		Vertex parent = null;
		if (getParent() != null) {
			parent = getParent().getVertex();
		}
		Edge parentEdge = null;

		for (Edge e : child.incidences(
				GraphProperties.getInstance().subcategoryLinkEC,
				EdgeDirection.IN)) {
			if (e.getAlpha().equals(parent) && e.getOmega().equals(child)) {
				parentEdge = e;
			}
		}
		return parentEdge;
	}

	public Vertex getVertex() {
		return vertex;
	}

	public CategoryTreeNode getParent() {
		return (CategoryTreeNode) super.getParent();
	}

	public CategoryTreeNode getChildAt(int index) {
		return (CategoryTreeNode) super.getChildAt(index);
	}

	public int getParentCategories() {
		return vertex.getAttribute("parentCategories");
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
		return vertex.getAttribute("transitiveSubCategories");
	}

	public String[] getPageStrings() {
		String[] result = new String[getVertex().getDegree(
				GraphProperties.getInstance().containsPageLinkEC, EdgeDirection.OUT)];
		int i = 0;
		for (Edge e : getVertex().incidences(
				GraphProperties.getInstance().containsPageLinkEC, EdgeDirection.OUT)) {
			result[i++] = ((String) e.getOmega().getAttribute("title"));
		}
		return result;
	}

	public String[] getParentCategoryStrings() {
		String[] result = new String[getVertex().getDegree(
				GraphProperties.getInstance().subcategoryLinkEC,
				EdgeDirection.IN)];
		int i = 0;
		for (Edge e : getVertex().incidences(
				GraphProperties.getInstance().subcategoryLinkEC,
				EdgeDirection.IN)) {
			if (!(Boolean) e.getAttribute("blacklisted")) {
				result[i++] = ((String) e.getAlpha().getAttribute("title"));
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return getTitle();
	}
}
