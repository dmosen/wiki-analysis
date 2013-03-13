package graph;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Vertex;

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
public interface BlacklistedGraphDFSVisitor {
	public void visitRoot(Vertex v);
	public void visitVertex(Vertex v);
	public void leaveVertex(Vertex v);
	public void visitEdge(Edge e);
	public void visitTreeEdge(Edge e);
}
