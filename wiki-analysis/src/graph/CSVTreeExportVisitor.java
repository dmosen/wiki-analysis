package graph;

import schemas.categoryschema.Category;
import schemas.categoryschema.ContainsPage;
import schemas.categoryschema.Page;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;
import de.uni_koblenz.jgralab.algolib.algorithms.search.visitors.DFSVisitorAdapter;

//FIXME Ignores blacklisting of edges and vertices

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
public class CSVTreeExportVisitor extends DFSVisitorAdapter {

	StringBuilder categoryTable;
	StringBuilder pageTable;

	@Override
	public void visitRoot(Vertex v) throws AlgorithmTerminatedException {
		super.visitRoot(v);

		categoryTable = new StringBuilder();
		categoryTable.append("ID\t");
		categoryTable.append("Title\t");
		categoryTable.append("Subcategories\t");
		categoryTable.append("Transitive Subcategories\t");
		categoryTable.append("Containing categories\t");
		categoryTable.append("Pages\t");
		categoryTable.append("TransitivePages\n");

		pageTable = new StringBuilder();
		pageTable.append("ID\t");
		pageTable.append("Title\t");
		pageTable.append("Containing category ID\t");
		pageTable.append("Containing category\t");
		pageTable.append("Containing categories\n");	
	}

	@Override
	public void visitVertex(Vertex v) throws AlgorithmTerminatedException {
		super.visitVertex(v);

		if (v.isInstanceOf(Category.VC)) {
			Category c = (Category) v;
			categoryTable.append(c.getId() + "\t");
			categoryTable.append(c.get_title() + "\t");
			categoryTable.append(c.get_subcategories() + "\t");
			categoryTable.append(c.get_transitiveSubcategories() + "\t");
			categoryTable.append(c.get_parentCategories() + "\t");
			categoryTable.append(c.get_pages() + "\t");
			categoryTable.append(c.get_transitivePages() + "\n");
		}

		if (v.isInstanceOf(Page.VC)) {
			Page p = (Page) v;
			for (ContainsPage e : p.getContainsPageIncidences(EdgeDirection.IN)) {
				pageTable.append(p.getId() + "\t");
				pageTable.append(p.get_title() + "\t");
				pageTable.append(e.getAlpha().getId() + "\t");
				pageTable.append(e.getAlpha().get_title() + "\t");
				pageTable.append(p.getDegree(ContainsPage.EC, EdgeDirection.IN) + "\n");
			}
		}
	}
	
	public String getCategoryTable() {
		return categoryTable.toString();
	}

	public String getPageTable() {
		return pageTable.toString();
	}
	
}
