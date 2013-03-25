package graph;

import de.uni_koblenz.jgralab.Edge;
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
	GraphProperties gp;

	@Override
	public void visitRoot(Vertex v) throws AlgorithmTerminatedException {
		super.visitRoot(v);

		gp = GraphProperties.getInstance();

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

		if (v.isInstanceOf(gp.categoryNodeVC)) {
			categoryTable.append(v.getId() + "\t");
			categoryTable.append(v.getAttribute("title") + "\t");
			categoryTable.append(v.getAttribute("subCategories") + "\t");
			categoryTable.append(v.getAttribute("transitiveSubCategories") + "\t");
			categoryTable.append(v.getAttribute("superCategories") + "\t");
			categoryTable.append(v.getAttribute("pages") + "\t");
			categoryTable.append(v.getAttribute("transitivePages") + "\n");
		}

		if (v.isInstanceOf(gp.pageNodeVC)) {
			for (Edge e : v.incidences(gp.containsPageLinkEC, EdgeDirection.IN)) {
				pageTable.append(v.getId() + "\t");
				pageTable.append(v.getAttribute("title") + "\t");
				pageTable.append(e.getAlpha().getId() + "\t");
				pageTable.append(e.getAlpha().getAttribute("title") + "\t");
				pageTable.append(v.getDegree(gp.containsPageLinkEC, EdgeDirection.IN) + "\n");
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
