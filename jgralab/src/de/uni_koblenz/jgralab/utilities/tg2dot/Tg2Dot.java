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
package de.uni_koblenz.jgralab.utilities.tg2dot;

import static de.uni_koblenz.jgralab.utilities.tg2dot.greql2.GreqlEvaluatorFacade.ABBREVIATE_EDGE_ATTRIBUTE_NAMES;
import static de.uni_koblenz.jgralab.utilities.tg2dot.greql2.GreqlEvaluatorFacade.PRINT_DOMAIN_NAMES;
import static de.uni_koblenz.jgralab.utilities.tg2dot.greql2.GreqlEvaluatorFacade.PRINT_EDGE_ATTRIBUTES;
import static de.uni_koblenz.jgralab.utilities.tg2dot.greql2.GreqlEvaluatorFacade.PRINT_ELEMENT_SEQUENCE_INDICES;
import static de.uni_koblenz.jgralab.utilities.tg2dot.greql2.GreqlEvaluatorFacade.PRINT_INCIDENCE_INDICES;
import static de.uni_koblenz.jgralab.utilities.tg2dot.greql2.GreqlEvaluatorFacade.PRINT_ROLENAMES;
import static de.uni_koblenz.jgralab.utilities.tg2dot.greql2.GreqlEvaluatorFacade.SHORTEN_STRINGS;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;

import de.uni_koblenz.ist.utilities.option_handler.OptionHandler;
import de.uni_koblenz.jgralab.AttributedElement;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.JGraLab;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.graphmarker.BooleanGraphMarker;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;
import de.uni_koblenz.jgralab.schema.AttributedElementClass;
import de.uni_koblenz.jgralab.schema.EdgeClass;
import de.uni_koblenz.jgralab.schema.Schema;
import de.uni_koblenz.jgralab.utilities.tg2dot.dot.DotWriter;
import de.uni_koblenz.jgralab.utilities.tg2dot.dot.GraphType;
import de.uni_koblenz.jgralab.utilities.tg2dot.dot.GraphVizLayouter;
import de.uni_koblenz.jgralab.utilities.tg2dot.dot.GraphVizOutputFormat;
import de.uni_koblenz.jgralab.utilities.tg2dot.dot.GraphVizProgram;
import de.uni_koblenz.jgralab.utilities.tg2dot.graph_layout.GraphLayout;
import de.uni_koblenz.jgralab.utilities.tg2dot.graph_layout.GraphLayoutFactory;
import de.uni_koblenz.jgralab.utilities.tg2dot.graph_layout.definition.Definition;
import de.uni_koblenz.jgralab.utilities.tg2dot.graph_layout.definition.ElementDefinition;
import de.uni_koblenz.jgralab.utilities.tg2dot.graph_layout.definition.TypeDefinition;
import de.uni_koblenz.jgralab.utilities.tg2dot.graph_layout.writer.AbstractGraphLayoutWriter;
import de.uni_koblenz.jgralab.utilities.tg2dot.graph_layout.writer.json.JsonGraphLayoutWriter;
import de.uni_koblenz.jgralab.utilities.tg2dot.greql2.GreqlEvaluatorFacade;
import de.uni_koblenz.jgralab.utilities.tg2whatever.Tg2Whatever;

/**
 * Tg2Dot2 takes a graph layout and a JGraLab graph and transforms the graph
 * into a DOT-graph of GraphViz.
 * 
 * @author ist@uni-koblenz.de
 * 
 */
public class Tg2Dot extends Tg2Whatever {

	/**
	 * Indicates to abbreviate all edge attribute names.
	 */
	private boolean abbreviateEdgeAttributeNames = false;

	/**
	 * Indicates to print the incidence indices.
	 */
	private boolean printIncidenceIndices = false;

	/**
	 * Indicates to print the element sequence index number.
	 */
	private boolean printElementSequenceIndices = false;

	/**
	 * Name of the graph layout file.
	 */
	private String graphLayoutFilename;

	/**
	 * Holds the loaded graph layout.
	 */
	private GraphLayout layout;

	/**
	 * Provides a GreqlEvaluator with convenient functionality.
	 */
	private GreqlEvaluatorFacade evaluator;

	/**
	 * Provides a dot file writer with a simple interface.
	 */
	private DotWriter writer;

	/**
	 * A set of AttributedElementClasses of Edges, which should be printed as
	 * reversed dot edges. This will not affect the appearance in dot, but will
	 * affect the layout process of GraphViz.
	 */
	private Set<EdgeClass> reversedEdgeClasses;

	/**
	 * Specifies the type of file, which will be passed to dot in order to
	 * generate an output. Defaults to DOT.
	 */
	private GraphVizLayouter graphVizLayouter;

	private GraphVizOutputFormat graphVizOutputFormat;

	private boolean useJsonGraphLayoutReader;

	private boolean debugIterations;

	private boolean debugOptimization;

	private Level jGraLabLogLevel;

	/**
	 * @param args
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws GraphIOException
	 */
	public static void main(String[] args) {
		Tg2Dot converter = new Tg2Dot();
		converter.getOptions(args);
		System.out.print("Starting processing of graph...");
		try {
			converter.convert();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished Processing.");
	}

	@Override
	public void convert() throws IOException {
		if (graphVizOutputFormat == null) {
			super.convert();
			return;
		}
		// write dot output into pipe to GraphViz layouter and store result as
		// file
		GraphVizProgram prog = new GraphVizProgram().layouter(graphVizLayouter)
				.outputFormat(graphVizOutputFormat);
		pipeToGraphViz(prog);
	}

	public static Tg2Dot createConverterAndSetAttributes(Graph graph,
			boolean reversedEdges, EdgeClass... reversedEdgeTypes) {

		Tg2Dot converter = new Tg2Dot();
		converter.setGraph(graph);
		converter.setReversedEdges(reversedEdges);
		converter.setPrintEdgeAttributes(true);

		if (reversedEdgeTypes != null) {
			HashSet<EdgeClass> revEdgeTypes = new HashSet<EdgeClass>();
			Collections.addAll(revEdgeTypes, reversedEdgeTypes);
			converter.setReversedEdgeClasses(revEdgeTypes);
		}

		return converter;
	}

	public static void convertGraph(Graph graph, String outputFileName)
			throws IOException {
		convertGraph(graph, outputFileName, false, GraphVizOutputFormat.XDOT,
				(EdgeClass[]) null);
	}

	public static void convertGraph(Graph graph, String outputFileName,
			boolean reversedEdges) throws IOException {
		convertGraph(graph, outputFileName, reversedEdges,
				GraphVizOutputFormat.XDOT, (EdgeClass[]) null);
	}

	public static void convertGraph(Graph graph, String outputFileName,
			GraphVizOutputFormat format) throws IOException {
		convertGraph(graph, outputFileName, false, format, (EdgeClass[]) null);
	}

	public static void convertGraph(Graph graph, String outputFileName,
			boolean reversedEdges, GraphVizOutputFormat format,
			Class<? extends Edge>... reversedEdgeTypes) throws IOException {
		convertGraph(graph, outputFileName, reversedEdges, format,
				toAttrElemClassArray(graph.getSchema(), reversedEdgeTypes));
	}

	public static void convertGraph(Graph graph, String outputFileName,
			boolean reversedEdges, GraphVizOutputFormat format,
			EdgeClass... reversedEdgeTypes) throws IOException {

		Tg2Dot converter = createConverterAndSetAttributes(graph,
				reversedEdges, reversedEdgeTypes);
		converter.setOutputFile(outputFileName);
		converter.setGraphVizOutputFormat(format);
		converter.convert();
	}

	public static void convertGraph(BooleanGraphMarker marker,
			String outputFileName) throws IOException {
		convertGraph(marker, outputFileName, false, (EdgeClass[]) null);
	}

	public static void convertGraph(BooleanGraphMarker marker,
			String outputFileName, boolean reversedEdges,
			Class<? extends Edge>... reversedEdgeTypes) throws IOException {
		convertGraph(
				marker,
				outputFileName,
				GraphVizOutputFormat.PDF,
				reversedEdges,
				toAttrElemClassArray(marker.getGraph().getSchema(),
						reversedEdgeTypes));
	}

	public static void convertGraph(BooleanGraphMarker marker,
			String outputFileName, boolean reversedEdges,
			EdgeClass... reversedEdgeTypes) throws IOException {
		convertGraph(marker, outputFileName, GraphVizOutputFormat.PDF,
				reversedEdges, reversedEdgeTypes);
	}

	public static void convertGraph(BooleanGraphMarker marker,
			String outputFileName, GraphVizOutputFormat format,
			boolean reversedEdges, Class<? extends Edge>... reversedEdgeTypes)
			throws IOException {
		convertGraph(
				marker,
				outputFileName,
				format,
				reversedEdges,
				toAttrElemClassArray(marker.getGraph().getSchema(),
						reversedEdgeTypes));
	}

	private static EdgeClass[] toAttrElemClassArray(Schema s,
			Class<? extends Edge>... reversedEdgeTypes) {
		if (reversedEdgeTypes == null) {
			return null;
		}
		EdgeClass[] aecs = new EdgeClass[reversedEdgeTypes.length];
		for (int i = 0; i < aecs.length; i++) {
			Class<? extends AttributedElement<?, ?>> cls = reversedEdgeTypes[i];
			String qname = cls.getName()
					.replace(s.getPackagePrefix() + ".", "");
			aecs[i] = s.getAttributedElementClass(qname);
			if (aecs[i] == null) {
				throw new RuntimeException("No such class " + qname);
			}
		}
		return aecs;
	}

	public static void convertGraph(BooleanGraphMarker marker,
			String outputFileName, GraphVizOutputFormat format,
			boolean reversedEdges, EdgeClass... reversedEdgeTypes)
			throws IOException {
		Tg2Dot converter = createConverterAndSetAttributes(marker.getGraph(),
				reversedEdges, reversedEdgeTypes);
		converter.setOutputFile(outputFileName);
		converter.setGraphMarker(marker);
		converter.convert();
	}

	public void pipeToGraphViz(GraphVizProgram prog) throws IOException {
		String executionString = String.format("%s%s -T%s -o%s", prog.path,
				prog.layouter, prog.outputFormat, outputName);
		final Process process = Runtime.getRuntime().exec(executionString);
		new Thread() {
			@Override
			public void run() {
				PrintStream ps = new PrintStream(process.getOutputStream());
				convert(ps);
				ps.flush();
				ps.close();
			};
		}.start();
		try {
			int retVal = process.waitFor();
			if (retVal != 0) {
				throw new RuntimeException(
						"GraphViz process failed! Error code = " + retVal);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public InputStream convertToGraphVizStream(GraphVizProgram prog)
			throws IOException {
		String executionString = String.format("%s%s -T%s", prog.path,
				prog.layouter, prog.outputFormat);
		final Process process = Runtime.getRuntime().exec(executionString);
		InputStream inputStream = new BufferedInputStream(
				process.getInputStream());
		new Thread() {
			@Override
			public void run() {
				PrintStream ps = new PrintStream(process.getOutputStream());
				convert(ps);
				ps.flush();
				ps.close();
			};
		}.start();
		return inputStream;
	}

	public ImageIcon convertToGraphVizImageIcon(GraphVizProgram prog)
			throws IOException {
		BufferedInputStream imageStream = new BufferedInputStream(
				convertToGraphVizStream(prog));
		return new ImageIcon(ImageIO.read(imageStream));
	}

	/**
	 * Initializes all data structures.
	 */
	public Tg2Dot() {
		reversedEdgeClasses = new HashSet<EdgeClass>();
	}

	@Override
	protected void getAdditionalOptions(CommandLine comLine) {
		initializeGraphAndSchema();

		if (comLine.hasOption('j') && comLine.hasOption('p')) {
			throw new RuntimeException(
					"Only a JSON- or a PList-layout file can be declared. Not both!");
		}
		useJsonGraphLayoutReader = comLine.hasOption('j');
		graphLayoutFilename = useJsonGraphLayoutReader ? comLine
				.getOptionValue('j') : comLine.getOptionValue('p');

		printIncidenceIndices = comLine.hasOption('i');
		printElementSequenceIndices = comLine.hasOption('m');

		String gvLayouter = comLine.getOptionValue('l');

		if (gvLayouter != null) {
			try {
				graphVizLayouter = GraphVizLayouter.valueOf(gvLayouter);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException("Unknown layouter '" + gvLayouter
						+ "'. Possible values are "
						+ GraphVizLayouter.describeValues());
			}
		}

		String gvOutputFormat = comLine.getOptionValue('t');
		if (gvOutputFormat != null) {
			try {
				graphVizOutputFormat = GraphVizOutputFormat
						.valueOf(gvOutputFormat);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException("Unknown output format  '"
						+ gvOutputFormat + "'. Possible values are "
						+ GraphVizOutputFormat.describeValues());
			}
		}
	}

	@Override
	protected void addAdditionalOptions(OptionHandler optionHandler) {

		Option pListLayout = new Option(
				"p",
				"pListLayout",
				true,
				"(optional): declares a PList-layout file, which should be used to lay out the given graph.");
		pListLayout.setRequired(false);
		optionHandler.addOption(pListLayout);

		Option jsonLayout = new Option(
				"j",
				"jsonLayout",
				true,
				"(optional): declares a JSON-layout file, which should be used to lay out the given graph.");
		jsonLayout.setRequired(false);
		optionHandler.addOption(jsonLayout);

		Option incidenceIndices = new Option("i", "incidenceIndices", false,
				"(optional): prints the incidence index to every edge.");
		incidenceIndices.setRequired(false);
		optionHandler.addOption(incidenceIndices);

		Option elementSequenceIndices = new Option("m",
				"elementSequenceIndices", false,
				"(optional): prints the element sequence index of every vertex and edge.");
		elementSequenceIndices.setRequired(false);
		optionHandler.addOption(elementSequenceIndices);

		Option gvFormat = new Option("t", "graphVizFormat", true,
				"(optional): determines the GraphViz output format");
		gvFormat.setRequired(false);
		optionHandler.addOption(gvFormat);

		Option gvLayouter = new Option("l", "graphVizLayouter", true,
				"(optional): determines the GraphViz layout program (default: 'dot')");
		gvLayouter.setRequired(false);
		optionHandler.addOption(gvLayouter);
	}

	@Override
	protected void graphStart(PrintStream out) {
		// Disable debugging to prevent recursive execution and restore it in
		// graphEnd()
		debugIterations = GreqlEvaluator.DEBUG_DECLARATION_ITERATIONS;
		debugOptimization = GreqlEvaluator.DEBUG_OPTIMIZATION;
		GreqlEvaluator.DEBUG_DECLARATION_ITERATIONS = false;
		GreqlEvaluator.DEBUG_OPTIMIZATION = false;
		jGraLabLogLevel = JGraLab.getRootLogger().getLevel();
		JGraLab.setLogLevel(Level.OFF);

		initializeEvaluator();
		initializeGraphLayout();

		setGlobalVariables();
		setCommandLineVariables();

		createDotWriter(out);
		startDotGraph();
	}

	/**
	 * Initializes the GreqlEvaluator and sets all known variables.
	 */
	private void initializeEvaluator() {
		evaluator = new GreqlEvaluatorFacade(graph);
	}

	/**
	 * Creates a GraphLayoutFactory and loads the GraphLayout.
	 */
	private void initializeGraphLayout() {
		GraphLayoutFactory factory = new GraphLayoutFactory(evaluator);

		if (graphLayoutFilename != null) {
			File layoutFile = new File(graphLayoutFilename);
			if (useJsonGraphLayoutReader) {
				factory.setJsonGraphLayoutFilename(layoutFile);
			} else {
				factory.setPListGraphLayoutFilename(layoutFile);
			}
		}

		layout = factory.createGraphLayout();
	}

	/**
	 * Sets all known global variables in the {@link GreqlEvaluator}.
	 */
	private void setGlobalVariables() {
		evaluator.setVariablesWithGreqlValues(layout.getGlobalVariables());
	}

	/**
	 * Sets all provided command line switch in the {@link GreqlEvaluator}.
	 */
	private void setCommandLineVariables() {
		evaluator.setVariable(PRINT_ROLENAMES, roleNames);
		evaluator.setVariable(PRINT_INCIDENCE_INDICES, printIncidenceIndices);
		evaluator.setVariable(PRINT_ELEMENT_SEQUENCE_INDICES,
				printElementSequenceIndices);
		evaluator.setVariable(PRINT_DOMAIN_NAMES, domainNames);
		evaluator.setVariable(SHORTEN_STRINGS, shortenStrings);
		evaluator.setVariable(ABBREVIATE_EDGE_ATTRIBUTE_NAMES,
				abbreviateEdgeAttributeNames);
		evaluator.setVariable(PRINT_EDGE_ATTRIBUTES, edgeAttributes);
	}

	/**
	 * Creates a {@link DotWriter}.
	 * 
	 * @param out
	 *            Provides stream, the DotWriter will use.
	 */
	private void createDotWriter(PrintStream out) {
		writer = new DotWriter(out);
	}

	/**
	 * Starts the Graph in the output file.
	 */
	private void startDotGraph() {
		writer.startGraph(GraphType.DIRECTED, graph.getAttributedElementClass()
				.getQualifiedName(),
				graph.getId() + " / " + graph.getGraphVersion());
	}

	@Override
	protected void printVertex(PrintStream out, Vertex vertex) {

		Definition definition = getCorrespondingDefinition(vertex);
		evaluator.setStaticVariablesOfGreqlEvaluator(vertex
				.getAttributedElementClass());
		writeLayoutedVertex(vertex, definition);
	}

	/**
	 * Returns the responsible {@link TypeDefinition} or
	 * {@link ElementDefinition} for the specified {@link AttributedElement}.
	 * 
	 * @param attributedElement
	 *            Given {@link AttributedElement}.
	 * @return Responsible {@link Definition}.
	 */
	private Definition getCorrespondingDefinition(
			AttributedElement<?, ?> attributedElement) {
		if (layout.isDefinedbyElementDefinitions(attributedElement)) {
			return constructSpecificElementDefinition(attributedElement);
		} else {
			TypeDefinition definition = layout
					.getTypeDefinition(attributedElement);
			return definition;
		}
	}

	/**
	 * Constructs an {@link ElementDefinition} for the given
	 * {@link AttributedElement}.
	 * 
	 * @param element
	 *            Given AttributedElement.
	 * @return {@link ElementDefinition} for the provided
	 *         {@link AttributedElement}.
	 */
	private Definition constructSpecificElementDefinition(
			AttributedElement<?, ?> element) {

		// Retrieves corresponding underlying TypeDefinition
		Definition definition = layout.getTypeDefinition(element);
		definition = definition.clone();

		// Overwrites all Attributes redefined by ElementDefinitions in the
		// order of declaration
		for (ElementDefinition elementDefinition : layout
				.getElementDefinitions()) {
			if (elementDefinition.hasElement(element)) {
				definition.overwriteAttributes(elementDefinition);
			}
		}
		return definition;
	}

	/**
	 * Evaluates all attributes of a given {@link Vertex} and prints via the
	 * {@link DotWriter}.
	 * 
	 * @param vertex
	 *            Provides Vertex.
	 * @param definition
	 *            Definition object used to layout the given Vertex.
	 */
	private void writeLayoutedVertex(Vertex vertex, Definition definition) {

		// Resets changed variables in the GreqlEvaluator
		evaluator.setVariablesOfGreqlEvaluator(vertex,
				getCurrentElementSequenceIndex());

		// Retrieves the unified vertex name ("v1", "v2", ... ) and the
		// evaluated style attribute list.
		String name = getVertexName(vertex);
		Map<String, String> evalutatedList = createEvaluatedStyleAttributeList(definition);
		if (!evalutatedList.containsKey("id")) {
			evalutatedList.put("id", name);
		}
		writer.writeNode(name, evalutatedList);
	}

	/**
	 * Returns unified vertex names. A vertex name has the prefix 'v' followed
	 * by the {@link Vertex} id.
	 * 
	 * @param vertex
	 *            Given {@link Vertex}
	 * @return Unified vertex name.
	 */
	private String getVertexName(Vertex vertex) {
		return "v" + vertex.getId();
	}

	/**
	 * Creates a evaluated style attribute list of the given {@link Definition}.
	 * <b>Note:</b><br>
	 * The current element has been set to the {@link GreqlEvaluator} via the
	 * method
	 * {@link GreqlEvaluatorFacade#setVariablesOfGreqlEvaluator(AttributedElement, int)}
	 * .
	 * 
	 * @param spec
	 *            Given {@link Definition} with the style attributes and their
	 *            queries.
	 * @return Evaluated style attribute list with attribute names as key and
	 *         the evaluated value string as value.
	 */
	private Map<String, String> createEvaluatedStyleAttributeList(
			Definition spec) {

		Map<String, String> evaluatedList = new HashMap<String, String>();

		for (String attributeName : spec.getAttributeNames()) {
			String query = spec.getAttributeValue(attributeName);
			String result = evaluator.evaluateToString(query);
			evaluatedList.put(attributeName, result);
		}
		return evaluatedList;
	}

	@Override
	protected void printEdge(PrintStream out, Edge edge) {
		Definition definition = getCorrespondingDefinition(edge);
		evaluator.setStaticVariablesOfGreqlEvaluator(edge
				.getAttributedElementClass());
		writeLayoutedEdge(edge, definition);
	}

	/**
	 * Evaluates all attributes of a given {@link Edge} and prints via the
	 * {@link DotWriter}.
	 * 
	 * @param edge
	 *            Provides Edge.
	 * @param definition
	 *            Definition object used to layout the given Vertex.
	 */
	private void writeLayoutedEdge(Edge edge, Definition definition) {

		// Resets changed variables in the GreqlEvaluator
		evaluator.setVariablesOfGreqlEvaluator(edge,
				getCurrentElementSequenceIndex());

		// Reverts the direction of the if isReversedEdge is true
		// This will not change the style, but will change the layout process in
		// GraphViz
		boolean isReversedEdge = isReversedEdge(edge);

		// Simple swap
		Vertex alpha = !isReversedEdge ? edge.getAlpha() : edge.getOmega();
		Vertex omega = !isReversedEdge ? edge.getOmega() : edge.getAlpha();

		// Retrieves the unified names of the alpha and omega edges.
		String alphaVertex = getVertexName(alpha);
		String omegaVertex = getVertexName(omega);

		// Retrieves the evaluated style attribute list
		Map<String, String> evaluatedList = createEvaluatedStyleAttributeList(definition);
		// Swaps all reversible style attributes.
		if (isReversedEdge) {
			reverseEdgeAttributes(evaluatedList);
		}
		if (!evaluatedList.containsKey("id")) {
			evaluatedList.put("id", "e" + edge.getNormalEdge().getId());
		}
		writer.writeEdge(alphaVertex, omegaVertex, evaluatedList);
	}

	/**
	 * Checks whether or not the given {@link Edge} belongs to a
	 * {@link EdgeClass}, which should be reversed.
	 * 
	 * @param e
	 *            Given Edge, which should be checked.
	 * @return Return true, if the given Edge should be reversed.
	 */
	private boolean isReversedEdge(Edge e) {
		Boolean isReversed = reversedEdgeClasses.contains(e
				.getAttributedElementClass());
		return isReversedEdges() ^ isReversed;
	}

	/**
	 * Reverses all reversible style attributes in the provided evaluted style
	 * attribute list.
	 * 
	 * @param evaluatedList
	 *            Given evaluated style attribute list as {@link Map}.
	 */
	private void reverseEdgeAttributes(Map<String, String> evaluatedList) {

		for (Entry<String, String> entry : DotWriter.reversableEdgeAttributePairs
				.entrySet()) {
			swapAttributes(entry.getKey(), entry.getValue(), evaluatedList);
		}
	}

	/**
	 * Swaps two key-value pairs in a map.
	 * 
	 * @param head
	 *            Key of the first key-value pair.
	 * @param tail
	 *            Key of the second key-value pair.
	 * @param evaluatedList
	 *            Evaluated style attribute list.
	 */
	private void swapAttributes(String head, String tail,
			Map<String, String> evaluatedList) {
		String headValue = evaluatedList.remove(head);
		String tailValue = evaluatedList.remove(tail);

		if (headValue != null) {
			evaluatedList.put(tail, headValue);
		}
		if (tailValue != null) {
			evaluatedList.put(head, tailValue);
		}
	}

	@Override
	protected void graphEnd(PrintStream out) {
		writer.close();
		writer = null;
		GreqlEvaluator.DEBUG_DECLARATION_ITERATIONS = debugIterations;
		GreqlEvaluator.DEBUG_OPTIMIZATION = debugOptimization;
		JGraLab.setLogLevel(jGraLabLogLevel);
	}

	/**
	 * Executes the Dot program of GraphViz.
	 */
	// @Deprecated
	// private void executeDot() {
	//
	// // TODO if dotBuildOutputType is set, the outputName is mangled.
	//
	// // If the outputname was set by the user, e.g. "foo.svg" for output type
	// // "svg", this file is used twice (at first as output file for tg2dot
	// // and then as output file of the dot program). This results into a call
	// // to "dot" with same input and output filenames.
	//
	// if (graphVizOutputFormat == null) {
	// return;
	// }
	//
	// if (graphVizLayouter == null) {
	// graphVizLayouter = GraphVizLayouter.DOT;
	// }
	//
	// System.out.print("Creating " + graphVizOutputFormat + " with "
	// + graphVizLayouter + "...");
	//
	// try {
	// File outputFile = new File(outputName);
	// String dotFile = outputFile.getAbsolutePath();
	// String formatedFile = dotFile + "." + dotBuildOutputType;
	// int lastIntPosition = dotFile.lastIndexOf('.');
	//
	// if (lastIntPosition != -1) {
	// formatedFile = dotFile.substring(0, lastIntPosition) + "."
	// + dotBuildOutputType;
	// }
	//
	// String executionString = "dot -T" + dotBuildOutputType + " "
	// + dotFile + " -o" + formatedFile;
	// Process p = Runtime.getRuntime().exec(executionString);
	// p.waitFor();
	// if (p.exitValue() == EXIT_ALL_FINE) {
	// System.out.println(" done.");
	// } else {
	// System.out.println(" error " + p.exitValue()
	// + " ocurred while executing DOT!");
	// }
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }

	@Override
	protected String stringQuote(String s) {
		throw new RuntimeException("This method should have been called!");
	}

	/**
	 * Writes the current GraphLayout to a JsonFile. <b>Note:</b><br>
	 * The written file will not be identical to the read graph layout.
	 */
	public void writeGraphLayoutToJsonFile() {
		if (layout == null) {
			throw new RuntimeException("There is no graph layout present.");
		}

		AbstractGraphLayoutWriter writer = new JsonGraphLayoutWriter();
		try {
			writer.startProcessing(graphLayoutFilename + ".parsed", layout);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * All edge instances of an edge type contained in the given set
	 * <code>reversedEdgeTypes</code> (or subtypes) will be printed reversed.
	 * This is especially useful when certain conceptual edges are modeled as
	 * nodes, like: State <--{ComesFrom} Transition -->{GoesTo}. Here, reversing
	 * the direction of either ComesFrom or GoesTo results in much nicer
	 * layouts.
	 * 
	 * @param reversedEdgeTypes
	 *            the set of edge types whose instances should be printed
	 *            reversed
	 */
	public void setReversedEdgeClasses(Set<EdgeClass> reversedEdgeTypes) {
		// Copies the current set in order to manipulate it.
		reversedEdgeTypes = new HashSet<EdgeClass>(reversedEdgeTypes);

		buildReversedEdgeClassSet(reversedEdgeTypes);
		if (!reversedEdgeTypes.isEmpty()) {
			throw new RuntimeException(
					"Those edge classes should be reversed but are not contained in the schema! "
							+ reversedEdgeTypes);
		}
		// apply hierarchy
		addAllSubClassesOfAllReversedEdgeClasses();
	}

	/**
	 * Converts the existing set of classes into a set of
	 * {@link AttributedElementClass}es.
	 * 
	 * @param reversedEdgeTypes
	 *            Set of classes of Edges, which should be reversed.
	 */
	private void buildReversedEdgeClassSet(Set<EdgeClass> reversedEdgeTypes) {
		reversedEdgeClasses = new HashSet<EdgeClass>();
		for (EdgeClass edgeClass : graph.getGraphClass().getEdgeClasses()) {
			if (reversedEdgeTypes.remove(edgeClass)) {
				reversedEdgeClasses.add(edgeClass);
			}
		}

		// apply hierarchy
		addAllSubClassesOfAllReversedEdgeClasses();
	}

	/**
	 * Adds sub classes of reversed Edges.
	 */
	private void addAllSubClassesOfAllReversedEdgeClasses() {
		Set<EdgeClass> classes = new HashSet<EdgeClass>(reversedEdgeClasses);
		for (EdgeClass attr : classes) {
			reversedEdgeClasses.addAll(attr.getAllSubClasses());
		}
	}

	/**
	 * Return a flag indicating that incidence numbers should be included in the
	 * graph layout process.
	 * 
	 * @return True, if incidence numbers should be be printed.
	 */
	public boolean printsIncidenceNumbers() {
		return printIncidenceIndices;
	}

	/**
	 * @param printIncidenceNumbers
	 *            If true, then the incidence numbers will be printed near the
	 *            start and end points of edges.
	 */
	public void setPrintIncidenceNumbers(boolean printIncidenceNumbers) {
		printIncidenceIndices = printIncidenceNumbers;
	}

	public boolean isAbbreviateAttributeNames() {
		return abbreviateEdgeAttributeNames;
	}

	public void setAbbreviateAttributeNames(boolean abbreviateAttributeNames) {
		abbreviateEdgeAttributeNames = abbreviateAttributeNames;
	}

	public GraphLayout getGraphLayout() {
		return layout;
	}

	public void setGraphLayout(GraphLayout layout) {
		this.layout = layout;
	}

	public boolean isAbbreviateEdgeAttributeNames() {
		return abbreviateEdgeAttributeNames;
	}

	public void setAbbreviateEdgeAttributeNames(
			boolean abbreviateEdgeAttributeNames) {
		this.abbreviateEdgeAttributeNames = abbreviateEdgeAttributeNames;
	}

	public boolean isPrintElementSequenceIndices() {
		return printElementSequenceIndices;
	}

	public void setPrintElementSequenceIndices(
			boolean printElementSequenceIndices) {
		this.printElementSequenceIndices = printElementSequenceIndices;
	}

	public String getGraphLayoutFilename() {
		return graphLayoutFilename;
	}

	public void setJsonGraphLayoutFilename(String graphLayoutFilename) {
		useJsonGraphLayoutReader = true;
		this.graphLayoutFilename = graphLayoutFilename;
	}

	public void setPListGraphLayoutFilename(String graphLayoutFilename) {
		useJsonGraphLayoutReader = false;
		this.graphLayoutFilename = graphLayoutFilename;
	}

	public Set<EdgeClass> getReversedEdgeClasses() {
		return reversedEdgeClasses;
	}

	public GraphVizLayouter getGraphVizLayouter() {
		return graphVizLayouter;
	}

	public void setGraphVizLayouter(GraphVizLayouter graphVizLayouter) {
		this.graphVizLayouter = graphVizLayouter;
	}

	public GraphVizOutputFormat getGraphVizOutputFormat() {
		return graphVizOutputFormat;
	}

	public void setGraphVizOutputFormat(
			GraphVizOutputFormat graphVizOutputFormat) {
		this.graphVizOutputFormat = graphVizOutputFormat;
	}
}
