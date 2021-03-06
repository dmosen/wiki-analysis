package graph;

import schemas.categoryschema.Category;
import schemas.categoryschema.Page;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Vertex;

/**
 * This visitor counts the reachable categories and pages for a given Vertex.
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class ReachabilityCountVisitor implements BlacklistedGraphDFSVisitor {

	private Vertex root;
	private Integer subcategoryCount;
	private Integer pagesCount;

	@Override
	public void visitRoot(Vertex v) {
		root = v;
		subcategoryCount = 0;
		pagesCount = 0;
	}

	@Override
	public void visitVertex(Vertex v) {
		if (!v.equals(root)) {
			if (v.isInstanceOf(Category.VC)) {
				subcategoryCount++;
			} else if (v.isInstanceOf(Page.VC)) {
				pagesCount++;
			}
		}
	}

	@Override
	public void leaveVertex(Vertex v) {
		if (v.equals(root)) {
			v.setAttribute("transitiveSubcategories", subcategoryCount);
			v.setAttribute("transitivePages", pagesCount);
		}
	}

	@Override
	public void visitEdge(Edge e) {
	}

	@Override
	public void visitTreeEdge(Edge e) {
	}

}
