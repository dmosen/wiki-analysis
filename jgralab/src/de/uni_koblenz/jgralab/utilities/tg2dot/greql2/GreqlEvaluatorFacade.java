/*
 * JGraLab - The Java Graph Laboratory
 *
 * Copyright (C) 2006-2011 Institute for Software Technology
 *                         University of Koblenz-Landau, Germany
 *                         ist@uni-koblenz.de
 *
 * For bug reports, documentation and further information, visit
 *
 *                         http://jgralab.uni-koblenz.de
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or combining
 * it with Eclipse (or a modified version of that program or an Eclipse
 * plugin), containing parts covered by the terms of the Eclipse Public
 * License (EPL), the licensors of this Program grant you additional
 * permission to convey the resulting work.  Corresponding Source for a
 * non-source form of such a combination shall include the source code for
 * the parts of JGraLab used as well as that of the covered work.
 */
package de.uni_koblenz.jgralab.utilities.tg2dot.greql2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.uni_koblenz.jgralab.AttributedElement;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;
import de.uni_koblenz.jgralab.greql2.funlib.FunLib;
import de.uni_koblenz.jgralab.schema.AggregationKind;
import de.uni_koblenz.jgralab.schema.AttributedElementClass;
import de.uni_koblenz.jgralab.schema.EdgeClass;
import de.uni_koblenz.jgralab.schema.Schema;
import de.uni_koblenz.jgralab.schema.VertexClass;
import de.uni_koblenz.jgralab.utilities.tg2dot.greql2.funlib.AbbreviateString;
import de.uni_koblenz.jgralab.utilities.tg2dot.greql2.funlib.AlphaIncidenceNumber;
import de.uni_koblenz.jgralab.utilities.tg2dot.greql2.funlib.AlphaRolename;
import de.uni_koblenz.jgralab.utilities.tg2dot.greql2.funlib.AttributeType;
import de.uni_koblenz.jgralab.utilities.tg2dot.greql2.funlib.FormatString;
import de.uni_koblenz.jgralab.utilities.tg2dot.greql2.funlib.JoinWithCollection;
import de.uni_koblenz.jgralab.utilities.tg2dot.greql2.funlib.OmegaIncidenceNumber;
import de.uni_koblenz.jgralab.utilities.tg2dot.greql2.funlib.OmegaRolename;
import de.uni_koblenz.jgralab.utilities.tg2dot.greql2.funlib.ShortenString;
import de.uni_koblenz.jgralab.utilities.tg2dot.greql2.funlib.ToDotString;

/**
 * The {@link GreqlEvaluatorFacade} is just a facade for a
 * {@link GreqlEvaluator} and provides automatic generation of an
 * using-preamble, variable setting and simple GReQL-query evaluation.
 * 
 * @author ist@uni-koblenz.de
 */
public class GreqlEvaluatorFacade {

	public static final String COMPOSITE_THAT = "compositeThat";

	public static final String SHARED_THAT = "sharedThat";

	public static final String COMPOSITE_THIS = "compositeThis";

	public static final String SHARED_THIS = "sharedThis";

	public static final String ELEMENT_SEQUENCE_INDEX = "elementSequenceIndex";

	public static final String TYPE = "type";

	public static final String ELEMENT = "el";

	public static final String PRINT_DOMAIN_NAMES = "printDomainNames";

	public static final String SHORTEN_STRINGS = "shortenStrings";

	public static final String ABBREVIATE_EDGE_ATTRIBUTE_NAMES = "abbreviateEdgeAttributeNames";

	public static final String PRINT_EDGE_ATTRIBUTES = "printEdgeAttributes";

	public static final String PRINT_ELEMENT_SEQUENCE_INDICES = "printElementSequenceIndices";

	public static final String PRINT_INCIDENCE_INDICES = "printIncidenceIndices";

	public static final String PRINT_ROLENAMES = "printRolenames";

	/**
	 * Registers all known GReQL functions and disables the JGraLab log.
	 */
	static {
		FunLib.register(ToDotString.class);
		FunLib.register(ShortenString.class);
		FunLib.register(AlphaRolename.class);
		FunLib.register(AlphaIncidenceNumber.class);
		FunLib.register(OmegaRolename.class);
		FunLib.register(OmegaIncidenceNumber.class);
		FunLib.register(AbbreviateString.class);
		FunLib.register(JoinWithCollection.class);
		FunLib.register(FormatString.class);
		FunLib.register(AttributeType.class);
	}

	/**
	 * The actual GreqlEvaluator used for evaluations.
	 */
	private final GreqlEvaluator evaluator;

	/**
	 * Current known variable set used by the
	 * {@link GreqlEvaluatorFacade#evaluator}.
	 */
	private int knownVariableHashCode;

	/**
	 * Currently used using-preamble.
	 */
	private String usingString = "";

	/**
	 * Constructs a GreqlEvaluatorFacade for a given {@link Graph} and creates
	 * its own {@link GreqlEvaluator}.
	 * 
	 * @param graph
	 *            Graph for which this GreqlEvaluatorFacade is used for.
	 */
	public GreqlEvaluatorFacade(Graph graph) {
		evaluator = new GreqlEvaluator((String) null, graph, null);
		knownVariableHashCode = 0;
		evaluator.setVariables(new HashMap<String, Object>());
	}

	/**
	 * Set for a provided {@link AttributedElementClass} the statically known
	 * variables in the {@link GreqlEvaluator}.
	 * 
	 * @param typeClass
	 *            A AttributedElementClass.
	 */
	public void setStaticVariablesOfGreqlEvaluator(
			AttributedElementClass<?, ?> typeClass) {

		if (typeClass instanceof EdgeClass) {
			setStaticVariablesOfGreqlEvaluator((EdgeClass) typeClass);
		} else if (typeClass instanceof VertexClass) {
			setStaticVariablesOfGreqlEvaluator((VertexClass) typeClass);
		} else {
			throw new RuntimeException("The class "
					+ typeClass.getClass().getSimpleName()
					+ " hasn' been expected");
		}
	}

	/**
	 * Set for a provided {@link VertexClass} the statically known variables in
	 * the {@link GreqlEvaluator}.
	 * 
	 * @param vertexClass
	 *            A VertexClass.
	 */
	private void setStaticVariablesOfGreqlEvaluator(VertexClass vertexClass) {

	}

	/**
	 * Set for a provided {@link EdgeClass} the statically known variables in
	 * the {@link GreqlEvaluatorFacade#evaluator}.
	 * 
	 * @param vertexClass
	 *            A VertexClass.
	 */
	private void setStaticVariablesOfGreqlEvaluator(EdgeClass edgeClass) {

		AggregationKind semanticType = edgeClass.getFrom().getAggregationKind();
		setVariable(SHARED_THIS, semanticType == AggregationKind.SHARED);
		setVariable(COMPOSITE_THIS, semanticType == AggregationKind.COMPOSITE);

		semanticType = edgeClass.getTo().getAggregationKind();
		setVariable(SHARED_THAT, semanticType == AggregationKind.SHARED);
		setVariable(COMPOSITE_THAT, semanticType == AggregationKind.COMPOSITE);
	}

	/**
	 * Sets the type specific attributes of the {@link AttributedElement} as
	 * variable in the {@link GreqlEvaluatorFacade#evaluator}.
	 * 
	 * @param attributedElement
	 *            The provided {@link AttributedElement}.
	 * @param graphSequenceIndex
	 *            The current sequence index in graph for the provided
	 *            AttributedElement.
	 */
	protected void setTypeSpecificVariablesOfGreqlEvaluator(
			AttributedElement<?, ?> attributedElement, int graphSequenceIndex) {

		// Sets the common attributes a variables in the evaluator.
		setVariablesOfGreqlEvaluator(attributedElement, graphSequenceIndex);

		if (attributedElement instanceof Vertex) {
			setVariablesOfGreqlEvaluator((Vertex) attributedElement);
		} else if (attributedElement instanceof Edge) {
			setVariablesOfGreqlEvaluator((Edge) attributedElement);
		} else {
			throw new RuntimeException("The class "
					+ attributedElement.getClass().getSimpleName()
					+ " hasn' been expected");
		}
	}

	/**
	 * Sets the commonly known attributes for a {@link AttributedElement} as
	 * variables in the {@link #evaluator}.
	 * 
	 * @param attributedElement
	 *            A {@link AttributedElement}.
	 * @param graphSequenceIndex
	 *            The current sequence index in graph for the provided
	 *            AttributedElement.
	 */
	public void setVariablesOfGreqlEvaluator(
			AttributedElement<?, ?> attributedElement, int graphSequenceIndex) {

		setVariable(ELEMENT, attributedElement);
		setVariable(TYPE, attributedElement.getAttributedElementClass());
		setVariable(ELEMENT_SEQUENCE_INDEX, graphSequenceIndex);
	}

	/**
	 * Sets the commonly known attributes for a {@link Vertex} as variables in
	 * the {@link #evaluator}.
	 * 
	 * @param vertex
	 *            A {@link Vertex}.
	 */
	private void setVariablesOfGreqlEvaluator(Vertex vertex) {
	}

	/**
	 * Sets the commonly known attributes for a {@link Edge} as variables in the
	 * {@link #evaluator}.
	 * 
	 * @param vertex
	 *            A {@link Edge}.
	 */
	private void setVariablesOfGreqlEvaluator(Edge edge) {
	}

	/**
	 * Retrieves the stored using-preamble as String or generates a new one if
	 * necessary.
	 * 
	 * @return Using String.
	 */
	public String getUsingString() {
		// Warning! This could be a source of errors.
		int currentVariablesHashCode = evaluator.getVariables().hashCode();

		boolean areEqual = currentVariablesHashCode == knownVariableHashCode;

		if (!areEqual) {
			generateUsingString();
			knownVariableHashCode = currentVariablesHashCode;
		}

		return usingString;
	}

	/**
	 * Generates a using-preamble from the variable names of the
	 * {@link #evaluator}.
	 */
	private void generateUsingString() {

		StringBuilder sb = new StringBuilder();
		String delimiter = "using ";
		for (String knownVariable : evaluator.getVariables().keySet()) {
			sb.append(delimiter);
			delimiter = ", ";
			sb.append(knownVariable);
		}
		if (sb.length() != 0) {
			sb.append(':');
		}
		usingString = sb.toString();
	}

	/**
	 * Evaluates a given GReQL-query to a JValue and returns it.
	 * 
	 * @param query
	 *            A GReQL-query as String.
	 * @return A JValue.
	 */
	public Object evaluate(String query) {
		query = getUsingString() + query;
		evaluator.setQuery(query);
		try {
			evaluator.startEvaluation();
		} catch (RuntimeException parse) {
			parse.printStackTrace();
			throw parse;
		}
		Object result = evaluator.getResult();
		GreqlEvaluator.DEBUG_DECLARATION_ITERATIONS = false;
		GreqlEvaluator.DEBUG_OPTIMIZATION = false;
		return result;
	}

	/**
	 * Evaluates a given GReQL-query to a JValue and returns it.
	 * 
	 * @param query
	 *            A GReQL-query as String.
	 * @return The String from a query result.
	 */
	public String evaluateToString(String query) {
		return evaluate(query).toString();
	}

	/**
	 * Sets the given value as variable of the {@link #evaluator}.
	 * 
	 * @param name
	 *            Name of the variable.
	 * @param value
	 *            Object as value.
	 */
	public void setVariable(String name, Object value) {
		evaluator.setVariable(name, value);
	}

	/**
	 * Sets the given value as variable of the {@link #evaluator}.
	 * 
	 * @param variables
	 *            Map with variable names and GReQL-queries as values.
	 */
	public void setVariablesWithGreqlValues(Map<String, String> variables) {

		for (Entry<String, String> variableEntry : variables.entrySet()) {
			Object result = evaluate(variableEntry.getValue());
			evaluator.setVariable(variableEntry.getKey(), result);
		}
	}

	public Schema getSchema() {
		return evaluator.getDatagraph().getSchema();
	}
}
