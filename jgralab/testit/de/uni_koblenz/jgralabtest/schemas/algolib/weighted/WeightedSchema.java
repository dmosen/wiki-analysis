/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.algolib.weighted;

import de.uni_koblenz.jgralab.impl.db.GraphDatabase;
import de.uni_koblenz.jgralab.impl.db.GraphDatabaseException;

import de.uni_koblenz.jgralab.GraphException;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralab.schema.impl.SchemaImpl;

import de.uni_koblenz.jgralab.schema.EdgeClass;
import de.uni_koblenz.jgralab.schema.GraphClass;
import de.uni_koblenz.jgralab.schema.VertexClass;

import java.lang.ref.WeakReference;

/**
 * The schema WeightedSchema is implemented following the singleton pattern.
 * To get the instance, use the static method <code>instance()</code>.
 */
public class WeightedSchema extends SchemaImpl {
	public final GraphClass gc_WeightedGraph;
	public final VertexClass vc_Location;
	public final EdgeClass ec_Way;

	/**
	 * the weak reference to the singleton instance
	 */
	static WeakReference<WeightedSchema> theInstance = new WeakReference<WeightedSchema>(null);
	
	/**
	 * @return the singleton instance of WeightedSchema
	 */
	public static WeightedSchema instance() {
		WeightedSchema s = theInstance.get();
		if (s != null) {
			return s;
		}
		synchronized (WeightedSchema.class) {
			s = theInstance.get();
			if (s != null) {
				return s;
			}
			s = new WeightedSchema();
			theInstance = new WeakReference<WeightedSchema>(s);
		}
		return s;
	}
	
	/**
	 * Creates a WeightedSchema and builds its schema classes.
	 * This constructor is private. Use the <code>instance()</code> method
	 * to acess the schema.
	 */
	private WeightedSchema() {
		super("WeightedSchema", "de.uni_koblenz.jgralabtest.schemas.algolib.weighted");

		{
			GraphClass gc = gc_WeightedGraph = createGraphClass("WeightedGraph");
			gc.setAbstract(false);
			@SuppressWarnings("unused")
			VertexClass vc_Vertex = getDefaultVertexClass();

			{
				VertexClass vc = vc_Location = gc.createVertexClass("Location");
				vc.setAbstract(false);
				vc.addAttribute(createAttribute("name", getDomain("String"), getAttributedElementClass("Location"), null));
				vc.addAttribute(createAttribute("x", getDomain("Double"), getAttributedElementClass("Location"), null));
				vc.addAttribute(createAttribute("y", getDomain("Double"), getAttributedElementClass("Location"), null));
			}

			{
				EdgeClass ec = ec_Way = gc.createEdgeClass("Way",
					vc_Location, 0, 2147483647, "alphaVertex", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_Location, 0, 2147483647, "omegaVertex", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(false);
				ec.addAttribute(createAttribute("weight", getDomain("Double"), getAttributedElementClass("Way"), null));
			}
		}

		finish();
	}

	@Override
	public de.uni_koblenz.jgralab.GraphFactory createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType implementationType) {
		switch(implementationType) {
			case GENERIC:
				return new de.uni_koblenz.jgralab.impl.generic.GenericGraphFactoryImpl(this);
			case STANDARD:
				return new de.uni_koblenz.jgralabtest.schemas.algolib.weighted.impl.std.WeightedGraphFactoryImpl();
			case TRANSACTION:
				return new de.uni_koblenz.jgralabtest.schemas.algolib.weighted.impl.trans.WeightedGraphFactoryImpl();
			case DATABASE:
				return new de.uni_koblenz.jgralabtest.schemas.algolib.weighted.impl.db.WeightedGraphFactoryImpl();
		}
		throw new UnsupportedOperationException("No " + implementationType + " support compiled.");
	}

	/**
	 * Creates a new WeightedGraph graph.
	*/
	public WeightedGraph createWeightedGraph(de.uni_koblenz.jgralab.ImplementationType implType) {
		return createWeightedGraph(implType, null, 100, 100);
	}

	/**
	 * Creates a new WeightedGraph graph with initial vertex and edge counts <code>vMax</code>, <code>eMax</code>.
	 *
	 * @param vMax initial vertex count
	 * @param eMax initial edge count
	*/
	public WeightedGraph createWeightedGraph(de.uni_koblenz.jgralab.ImplementationType implType, String id, int vMax, int eMax) {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return factory.createGraph(gc_WeightedGraph, id, vMax, eMax);
	}

	/**
	 * Creates a new WeightedGraph graph.
	*/
	public WeightedGraph createWeightedGraph(de.uni_koblenz.jgralab.GraphFactory factory) {
		return factory.createGraph(gc_WeightedGraph, null, 100, 100);
	}

	/**
	 * Creates a new WeightedGraph graph.
	*/
	public WeightedGraph createWeightedGraph(de.uni_koblenz.jgralab.GraphFactory factory, String id, int vMax, int eMax) {
		return factory.createGraph(gc_WeightedGraph, id, vMax, eMax);
	}

	/**
	 * Creates a new WeightedGraph graph in a database with given <code>id</code>.
	 *
	 * @param id Identifier of new graph
	 * @param graphDatabase Database which should contain graph
	 */
	public WeightedGraph createWeightedGraph(String id, GraphDatabase graphDatabase) throws GraphDatabaseException{
		return createWeightedGraph(id, 100, 100, graphDatabase);
	}

	/**
	 * Creates a new WeightedGraph graph in a database with given <code>id</code>.
	 *
	 * @param id Identifier of new graph
	 * @param vMax Maximum initial count of vertices that can be held in graph.
	 * @param eMax Maximum initial count of edges that can be held in graph.
	 * @param graphDatabase Database which should contain graph
	 */
	public WeightedGraph createWeightedGraph(String id, int vMax, int eMax, GraphDatabase graphDatabase) throws GraphDatabaseException{
		de.uni_koblenz.jgralab.impl.GraphFactoryImpl graphFactory = (de.uni_koblenz.jgralab.impl.GraphFactoryImpl) createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.DATABASE);
		graphFactory.setGraphDatabase(graphDatabase);
		WeightedGraph graph = graphFactory.createGraph(gc_WeightedGraph, id, vMax, eMax);
		if (!graphDatabase.containsGraph(id)) {
			graphDatabase.insert((de.uni_koblenz.jgralab.impl.db.GraphImpl)graph);
			return graph;
		} else {
			throw new GraphException("Graph with identifier " + id + " already exists in database.");
		}
	}

	public WeightedGraph loadWeightedGraph(String filename) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.STANDARD);
		return loadWeightedGraph(filename, factory, null);
	}

	public WeightedGraph loadWeightedGraph(String filename, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.STANDARD);
		return loadWeightedGraph(filename, factory, pf);
	}

	public WeightedGraph loadWeightedGraph(String filename, de.uni_koblenz.jgralab.ImplementationType implType) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return loadWeightedGraph(filename, factory, null);
	}

	
	public WeightedGraph loadWeightedGraph(String filename, de.uni_koblenz.jgralab.ImplementationType implType, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return loadWeightedGraph(filename, factory, pf);
	}

	public WeightedGraph loadWeightedGraph(String filename, de.uni_koblenz.jgralab.GraphFactory factory) throws GraphIOException {
		return GraphIO.loadGraphFromFile(filename, factory, null);
	}

	public WeightedGraph loadWeightedGraph(String filename, de.uni_koblenz.jgralab.GraphFactory factory, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		return GraphIO.loadGraphFromFile(filename, factory, pf);
	}
	
	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
