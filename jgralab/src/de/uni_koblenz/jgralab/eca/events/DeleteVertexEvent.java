package de.uni_koblenz.jgralab.eca.events;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.eca.events.EventDescription.EventTime;
import de.uni_koblenz.jgralab.schema.VertexClass;

public class DeleteVertexEvent extends Event<VertexClass> {

	/**
	 * The to be deleted Vertex or null if the EventTime is after
	 */
	private Vertex vertex;

	/**
	 * Creates an DeleteVertexEvent with the given parameters
	 * 
	 * @param nestedCalls
	 *            depth of nested trigger calls
	 * @param graph
	 *            Graph where the Event happened
	 * @param vertex
	 *            the to be deleted Vertex or null if the EventTime is after
	 */
	public DeleteVertexEvent(int nestedCalls, Graph graph, Vertex vertex) {
		super(nestedCalls, EventTime.BEFORE, graph, vertex
				.getAttributedElementClass());
		this.vertex = vertex;
	}

	/**
	 * Creates an DeleteVertexEvent with the given parameters
	 * 
	 * @param nestedCalls
	 *            depth of nested trigger calls
	 * @param graph
	 *            Graph where the Event happened
	 * @param type
	 *            the type of the deleted Vertex
	 */
	public DeleteVertexEvent(int nestedCalls, Graph graph, VertexClass type) {
		super(nestedCalls, EventTime.AFTER, graph, type);
		vertex = null;
	}

	/**
	 * @return the AttributedElement that causes this Event or null if the
	 *         EventTime is after
	 */
	@Override
	public Vertex getElement() {
		return vertex;
	}

}
