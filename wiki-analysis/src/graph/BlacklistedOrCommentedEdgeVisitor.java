package graph;

import java.util.ArrayList;

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

	ArrayList<Edge> edges = new ArrayList<Edge>();

	@Override
	public void visitEdge(Edge e) throws AlgorithmTerminatedException {
		super.visitEdge(e);
		if (e.isInstanceOf(GraphProperties.getInstance().subcategoryLinkEC)) {
			if ((Boolean) e.getAttribute("blacklisted")
					|| e.getAttribute("comment") != null) {
				edges.add(e);
			}
		}
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
}
