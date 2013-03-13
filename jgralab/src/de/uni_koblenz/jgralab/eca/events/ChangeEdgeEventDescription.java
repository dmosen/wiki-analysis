package de.uni_koblenz.jgralab.eca.events;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.eca.ECARule;
import de.uni_koblenz.jgralab.schema.EdgeClass;

public class ChangeEdgeEventDescription extends EventDescription<EdgeClass> {

	public enum EdgeEnd {
		ALPHA, OMEGA, ANY
	}

	/**
	 * Whether this EventDescription monitors changes on ALPHA, OMEGA or BOTH
	 */
	private EdgeEnd edgeEnd;

	/**
	 * Creates an ChangeEdgeEventDescription with the given parameters
	 * 
	 * @param time
	 *            the EventTime, BEFORE or AFTER
	 * @param type
	 *            the Class of elements, this EventDescription monitors
	 */
	public ChangeEdgeEventDescription(EventTime time, EdgeClass type,
			EdgeEnd end) {
		super(time, type);
		edgeEnd = end;
	}

	/**
	 * Creates an ChangeEdgeEventDescription with the given parameters
	 * 
	 * @param time
	 *            the EventTime, BEFORE or AFTER
	 * @param contextExpr
	 *            the contextExpression to get the context
	 */
	public ChangeEdgeEventDescription(EventTime time, String contextExpr) {
		super(time, contextExpr);
	}

	/**
	 * Checks whether this EventDescription matches the Event and triggers the
	 * rules if it is so
	 * 
	 * @param element
	 *            the Edge that causes the Event
	 * @param oldVertex
	 *            the old Vertex of the Edge
	 * @param newVertex
	 *            the new Vertex of the Edge
	 */
	public void fire(Edge element, Vertex oldVertex, Vertex newVertex,
			EdgeEnd endOfEdge) {
		if (super.checkContext(element)) {
			int nested = getActiveECARules().get(0).getECARuleManager()
					.getNestedTriggerCalls();
			Graph graph = getActiveECARules().get(0).getECARuleManager()
					.getGraph();
			for (ECARule<EdgeClass> rule : activeRules) {
				rule.trigger(new ChangeEdgeEvent(nested, getTime(), graph,
						element, oldVertex, newVertex, endOfEdge));

			}
		}
	}

	/**
	 * @return if the ChangeEdgeEventDescription monitors the change of the
	 *         alpha end, the omega end or both
	 */
	public EdgeEnd getEdgeEnd() {
		return edgeEnd;
	}

}
