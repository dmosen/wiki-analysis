package graph;

import java.util.ArrayList;

import schemas.categoryschema.Subcategory;
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

	ArrayList<Subcategory> edges = new ArrayList<Subcategory>();

	@Override
	public void visitEdge(Edge e) throws AlgorithmTerminatedException {
		super.visitEdge(e);
		if (e.isInstanceOf(Subcategory.EC)) {
			Subcategory s = (Subcategory) e;
			if (s.is_blacklisted() || s.get_comment() != null) {
				edges.add(s);
			}
		}
	}

	public ArrayList<Subcategory> getEdges() {
		return edges;
	}
}
