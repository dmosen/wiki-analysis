package graph;

import java.util.ArrayList;

import schemas.categoryschema.HasSubcategory;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.algolib.algorithms.AlgorithmTerminatedException;
import de.uni_koblenz.jgralab.algolib.algorithms.search.visitors.DFSVisitorAdapter;

/**
 * This visitor collects all edges that are blacklisted or contain a comment.
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class BlacklistedOrCommentedEdgeVisitor extends DFSVisitorAdapter {

	ArrayList<HasSubcategory> edges = new ArrayList<HasSubcategory>();

	@Override
	public void visitEdge(Edge e) throws AlgorithmTerminatedException {
		super.visitEdge(e);
		if (e.isInstanceOf(HasSubcategory.EC)) {
			HasSubcategory s = (HasSubcategory) e;
			if (s.is_blacklisted() || s.get_comment() != null) {
				edges.add(s);
			}
		}
	}

	public ArrayList<HasSubcategory> getEdges() {
		return edges;
	}
}
