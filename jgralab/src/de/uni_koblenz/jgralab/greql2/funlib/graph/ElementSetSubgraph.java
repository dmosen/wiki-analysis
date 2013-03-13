package de.uni_koblenz.jgralab.greql2.funlib.graph;


import org.pcollections.PCollection;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.graphmarker.SubGraphMarker;
import de.uni_koblenz.jgralab.greql2.funlib.Function;
import de.uni_koblenz.jgralab.greql2.funlib.NeedsGraphArgument;

@NeedsGraphArgument
public class ElementSetSubgraph extends Function {

	public ElementSetSubgraph() {
		super(
				"Returns the subgraph induced by the vertex type given.",
				7, 1, 1.0, Category.GRAPH);
	}

	public SubGraphMarker evaluate(Graph graph, PCollection<Vertex> vertexSet, PCollection<Edge> edgeSet) {
		SubGraphMarker subgraphMarker = new SubGraphMarker(graph);
		for (Vertex currentVertex : vertexSet) {
			subgraphMarker.mark(currentVertex);
		}
		// add all edges
		for (Edge currentEdge : edgeSet) {
			if ((subgraphMarker.isMarked(currentEdge.getAlpha()) && (subgraphMarker.isMarked(currentEdge.getOmega())))) {
				subgraphMarker.mark(currentEdge);
			}
		}
		
		return subgraphMarker;
	}
	
}
