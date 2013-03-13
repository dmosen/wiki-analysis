package de.uni_koblenz.jgralab.eca.events;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.eca.ECAException;
import de.uni_koblenz.jgralab.eca.ECARule;
import de.uni_koblenz.jgralab.schema.VertexClass;

public class CreateVertexEventDescription extends EventDescription<VertexClass> {

	/**
	 * Creates an CreateVertexEventDescription with the given parameters
	 * 
	 * @param time
	 *            the EventTime, BEFORE or AFTER
	 * @param type
	 *            the Class of elements, this Event monitors
	 */
	public CreateVertexEventDescription(EventTime time, VertexClass type) {
		super(time, type);
	}

	/**
	 * Creates an CreateVertexEventDescription with the given parameters
	 * 
	 * @param time
	 *            the EventTime, BEFORE or AFTER
	 * @param contextExpr
	 *            the contextExpression to get the context
	 */
	public CreateVertexEventDescription(EventTime time, String contextExpr) {
		super(time, contextExpr);
		if (time.equals(EventTime.BEFORE)) {
			throw new ECAException(
					"Event \"before create Vertex\" can not match a context expression"
							+ " because there is no element.");
		}
	}

	// ---------------------------------------------------------------------

	/**
	 * Triggers the rules if this EventDescription matches the Event
	 * 
	 * @param element
	 *            the created Vertex
	 */
	public void fire(Vertex element) {
		if (super.checkContext(element)) {
			int nested = getActiveECARules().get(0).getECARuleManager()
					.getNestedTriggerCalls();
			Graph graph = getActiveECARules().get(0).getECARuleManager()
					.getGraph();
			for (ECARule<VertexClass> rule : activeRules) {
				rule.trigger(new CreateVertexEvent(nested, graph, element));
			}
		}
	}

	/**
	 * Triggers the rule if this EventDescription matches the Event
	 * 
	 * @param type
	 *            the type of the Vertex that will become created
	 */
	public void fire(VertexClass type) {
		if (super.checkContext(type)) {
			int nested = getActiveECARules().get(0).getECARuleManager()
					.getNestedTriggerCalls();
			Graph graph = getActiveECARules().get(0).getECARuleManager()
					.getGraph();
			for (ECARule<VertexClass> rule : activeRules) {
				rule.trigger(new CreateVertexEvent(nested, graph, getType()));
			}
		}
	}

}
