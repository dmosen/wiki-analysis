package visualisation.model;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import schemas.categoryschema.Category;
import schemas.categoryschema.ContainsPage;
import schemas.categoryschema.Subcategory;
import de.uni_koblenz.jgralab.EdgeDirection;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
@SuppressWarnings("serial")
public class CategoryTreeNode extends DefaultMutableTreeNode {

	private Category vertex;
	private Subcategory edge;

	public CategoryTreeNode(Category vertex, Subcategory edge) {
		super(edge);
		this.vertex = vertex;
		this.edge = edge;
	}

	public boolean isBlacklisted() {
		if (edge != null) {
			return edge.is_blacklisted();
		}
		return false;
	}

	public void setBlacklisting(boolean value) {
		if (edge != null) {
			edge.set_blacklisted(value);
		}
	}

	public String getTitle() {
		return vertex.get_title();
	}

	public String getComment() {
		if (isRoot()) {
			return "root category";
		}
		return edge.get_comment();
	}

	public void setComment(String comment) {
		edge.set_comment(comment);
	}

	public CategoryTreeNode getParent() {
		return (CategoryTreeNode) super.getParent();
	}

	public CategoryTreeNode getChildAt(int index) {
		return (CategoryTreeNode) super.getChildAt(index);
	}

	public int getPages() {
		return vertex.get_pages();
	}

	public int getPagesTransitive() {
		return vertex.get_transitivePages();
	}

	public int getSubcategories() {
		return vertex.get_subcategories();
	}

	public int getSubcategoriesTransitive() {
		return vertex.get_transitiveSubcategories();
	}

	public String[] getPageStrings() {
		String[] result = new String[vertex.getDegree(ContainsPage.EC,
				EdgeDirection.OUT)];
		int i = 0;
		for (ContainsPage e : vertex
				.getContainsPageIncidences(EdgeDirection.OUT)) {
			result[i++] = (e.getOmega().get_title());
		}
		return result;
	}

	public String[] getParentCategoryStrings() {
		ArrayList<String> results = new ArrayList<String>();
		for (Subcategory e : vertex.getSubcategoryIncidences(EdgeDirection.IN)) {
			if (!e.is_blacklisted()) {
				results.add(e.getAlpha().get_title());
			}
		}
		return results.toArray(new String[results.size()]);
	}

	@Override
	public String toString() {
		return getTitle();
	}
}
