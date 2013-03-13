package de.uni_koblenz.jgralab.greql2.funlib.graph;

import org.pcollections.PVector;

import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.greql2.funlib.Function;
import de.uni_koblenz.jgralab.greql2.types.Path;

public class VertexTrace extends Function {

	public VertexTrace() {
		super("Returns the vertex trace of the given path.",
				Category.PATHS_AND_PATHSYSTEMS_AND_SLICES);
	}

	public PVector<Vertex> evaluate(Path p) {
		return p.getVertexTrace();
	}
}
