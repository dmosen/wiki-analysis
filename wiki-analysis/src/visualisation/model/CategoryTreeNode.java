package visualisation.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import schemas.categoryschema.Category;
import schemas.categoryschema.ContainsPage;
import schemas.categoryschema.HasSubcategory;
import de.uni_koblenz.jgralab.EdgeDirection;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class CategoryTreeNode implements MutableTreeNode {

	private Category vertex;
	private HasSubcategory edge;
	private CategoryTreeNode parent;

	private boolean root = false;

	public CategoryTreeNode(Category vertex, HasSubcategory edge) {
		this.vertex = vertex;

		CategoryTreeModel.edgeToNodeMap.put(edge, this);

		this.edge = edge;
	}

	public boolean isBlacklisted() {
		if (edge != null) {
			return edge.is_blacklisted();
		}
		return false;
	}

	public boolean isBackwardArc() {
		if (edge != null) {
			return edge.is_backwardArc();
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
		if (edge.get_comment() != null) {
			return edge.get_comment();
		}
		return "";
	}

	public void setComment(String comment) {
		edge.set_comment(comment);
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
		for (HasSubcategory e : vertex
				.getHasSubcategoryIncidences(EdgeDirection.IN)) {
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

	@Override
	public CategoryTreeNode getChildAt(int childIndex) {
		return getChildren().get(childIndex);

	}

	@Override
	public int getChildCount() {
		return getChildren().size();
	}

	@Override
	public CategoryTreeNode getParent() {
		if (!isRoot() && !edge.is_blacklisted()) {
			return parent;
		}
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		for (int index = 0; index < getChildCount(); index++) {
			if (getChildAt(index).equals(node)) {
				return index;
			}
		}
		return -1;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public boolean isLeaf() {
		return getChildCount() <= 0;
	}

	@Override
	public Enumeration<CategoryTreeNode> children() {
		return getChildren().elements();
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		throw new UnsupportedOperationException(
				"This operation is unsupported by this tree node");
	}

	@Override
	public void remove(int index) {
		throw new UnsupportedOperationException(
				"This operation is unsupported by this tree node");
	}

	@Override
	public void remove(MutableTreeNode node) {
		throw new UnsupportedOperationException(
				"This operation is unsupported by this tree node");
	}

	@Override
	public void removeFromParent() {
		throw new UnsupportedOperationException(
				"This operation is unsupported by this tree node");
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		parent = (CategoryTreeNode) newParent;
	}

	public void setRoot(boolean value) {
		this.root = value;
	}

	public boolean isRoot() {
		return root;
	}

	private Vector<CategoryTreeNode> getChildren() {
		Vector<CategoryTreeNode> children = new Vector<CategoryTreeNode>();
		for (HasSubcategory sub : vertex
				.getHasSubcategoryIncidences(EdgeDirection.OUT)) {

			CategoryTreeNode node;

			// create tree node lazily
			if (CategoryTreeModel.edgeToNodeMap.containsKey(sub)) {
				node = CategoryTreeModel.edgeToNodeMap.get(sub);
			} else {
				node = new CategoryTreeNode(sub.getOmega(), sub);
				node.setParent(this);
				CategoryTreeModel.edgeToNodeMap.put(sub, node);
			}
			if (!sub.is_blacklisted() && !sub.is_backwardArc()) {
				children.add(node);
			}
		}
		return children;
	}

	public List<CategoryTreeNode> getPath() {
		LinkedList<CategoryTreeNode> path = new LinkedList<CategoryTreeNode>();
		path.addFirst(this);
		CategoryTreeNode current = parent;
		while (current != null) {
			path.addFirst(current);
			current = current.getParent();
		}
		return path;
	}

	@Override
	public void setUserObject(Object object) {
		throw new UnsupportedOperationException(
				"This operation is unsupported by this tree node");
	}

	public boolean isExcluded() {
		return edge.is_excluded();
	}
}
