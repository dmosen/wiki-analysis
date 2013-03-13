package graph;


import java.util.ArrayList;
import java.util.HashMap;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;

/**
 * This implements a recursive depth first search for category graphs conforming
 * to the schema <code>category-schema.tg</code> ignoring blacklisted edges. The
 * search is performed for exactly one given root vertex (i.e. vertices not
 * reachable from the root are not visited).
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class BlacklistedGraphDFS {

	private ArrayList<BlacklistedGraphDFSVisitor> visitors = new ArrayList<BlacklistedGraphDFSVisitor>();
	private Vertex root;
	private Integer time;
	private HashMap<Vertex, Integer> visitTime;
	private HashMap<Vertex, Integer> leaveTime;
	private GraphProperties gp;

	public void execute(Graph graph) {
		this.execute(graph, null);
	}

	public void execute(Graph graph, Vertex v) {
		if (v == null) {
			root = graph.getFirstVertex();
		} else {
			root = v;
		}

		gp = GraphProperties.getInstance();

		visitTime = new HashMap<Vertex, Integer>();
		leaveTime = new HashMap<Vertex, Integer>();
		time = 0;

		visitRoot(root);

		execute(root);
	}

	private void execute(Vertex current) {

		visitVertex(current);
		visitTime.put(current, ++time);

		for (Edge e : current.incidences(EdgeDirection.OUT)) {
			// process only those edges which are not blacklisted
			if (!e.isInstanceOf(gp.subCategoryLinkEC)
					|| !(Boolean) e.getAttribute("blacklisted")) {

				visitEdge(e);

				Vertex next = e.getOmega();

				if (!visitTime.containsKey(next)) {
					execute(next);
				} else {
					visitTreeEdge(e);
				}
			}
		}

		leaveVertex(current);
		leaveTime.put(current, ++time);
	}

	private void visitRoot(Vertex v) {
		for (BlacklistedGraphDFSVisitor visitor : visitors) {
			visitor.visitRoot(v);
		}
	}

	private void visitEdge(Edge e) {
		for (BlacklistedGraphDFSVisitor visitor : visitors) {
			visitor.visitEdge(e);
		}
	}

	private void visitTreeEdge(Edge e) {
		for (BlacklistedGraphDFSVisitor visitor : visitors) {
			visitor.visitTreeEdge(e);
		}
	}

	private void visitVertex(Vertex v) {
		for (BlacklistedGraphDFSVisitor visitor : visitors) {
			visitor.visitVertex(v);
		}
	}

	private void leaveVertex(Vertex v) {
		for (BlacklistedGraphDFSVisitor visitor : visitors) {
			visitor.leaveVertex(v);
		}
	}

	public void addVisitor(BlacklistedGraphDFSVisitor v) {
		visitors.add(v);
	}

	public void removeVisitor(BlacklistedGraphDFSVisitor v) {
		visitors.remove(v);
	}

}
