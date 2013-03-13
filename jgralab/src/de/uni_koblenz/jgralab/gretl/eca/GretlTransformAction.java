package de.uni_koblenz.jgralab.gretl.eca;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.eca.Action;
import de.uni_koblenz.jgralab.eca.events.Event;
import de.uni_koblenz.jgralab.gretl.Context;
import de.uni_koblenz.jgralab.gretl.Transformation;
import de.uni_koblenz.jgralab.schema.AttributedElementClass;

public class GretlTransformAction<AEC extends AttributedElementClass<AEC, ?>>
		implements Action<AEC> {

	/**
	 * Class of Transformation
	 */
	Class<? extends Transformation<Graph>> transformationClass;

	// +++++++++++++++++++++++++++++++++++++++++++++++++

	/**
	 * Creates a new GretlTransformAction with the given Transformation Class
	 * 
	 * @param transformationClass
	 *            the Transformation Class
	 */
	public GretlTransformAction(
			Class<? extends Transformation<Graph>> transformationClass) {
		this.transformationClass = transformationClass;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++

	/**
	 * Executes the action
	 */
	@Override
	public void doAction(Event<AEC> event) {

		try {
			Graph graph = event.getGraph();

			Context context = new Context(graph.getSchema());
			context.setSourceGraph(graph);
			context.setTargetGraph(graph);

			Constructor<? extends Transformation<Graph>> constr = transformationClass
					.getConstructor(Context.class);
			Transformation<Graph> transform = constr.newInstance(context);

			transform.execute();

		} catch (SecurityException e) {
			System.err.println("Gretl transformation "
					+ this.transformationClass.getName() + " failed!");
		} catch (NoSuchMethodException e) {
			System.err.println("Gretl transformation "
					+ this.transformationClass.getName() + " failed!");
		} catch (IllegalArgumentException e) {
			System.err.println("Gretl transformation "
					+ this.transformationClass.getName() + " failed!");
		} catch (InstantiationException e) {
			System.err.println("Gretl transformation "
					+ this.transformationClass.getName() + " failed!");
		} catch (IllegalAccessException e) {
			System.err.println("Gretl transformation "
					+ this.transformationClass.getName() + " failed!");
		} catch (InvocationTargetException e) {
			System.err.println("Gretl transformation "
					+ this.transformationClass.getName() + " failed!");
		}

	}

	/**
	 * @return the Gretl Transformation
	 */
	public Class<? extends Transformation<Graph>> getTransformationClass() {
		return transformationClass;
	}

}
