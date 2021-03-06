/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema;

import de.uni_koblenz.jgralab.impl.db.GraphDatabase;
import de.uni_koblenz.jgralab.impl.db.GraphDatabaseException;

import de.uni_koblenz.jgralab.GraphException;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralab.schema.impl.SchemaImpl;

import de.uni_koblenz.jgralab.schema.EdgeClass;
import de.uni_koblenz.jgralab.schema.EnumDomain;
import de.uni_koblenz.jgralab.schema.GraphClass;
import de.uni_koblenz.jgralab.schema.RecordDomain;
import de.uni_koblenz.jgralab.schema.VertexClass;

import java.lang.ref.WeakReference;

/**
 * The schema RouteSchema is implemented following the singleton pattern.
 * To get the instance, use the static method <code>instance()</code>.
 */
public class RouteSchema extends SchemaImpl {
	public final GraphClass gc_RouteMap;
	public final VertexClass vc_NamedElement;
	public final VertexClass vc_junctions_Junction;
	public final VertexClass vc_localities_County;
	public final VertexClass vc_localities_Locality;
	public final VertexClass vc_junctions_Crossroad;
	public final VertexClass vc_junctions_Airport;
	public final VertexClass vc_localities_Town;
	public final VertexClass vc_localities_Village;
	public final VertexClass vc_junctions_Plaza;
	public final VertexClass vc_junctions_Roundabout;
	public final VertexClass vc_localities_City;
	public final EdgeClass ec_connections_Connection;
	public final EdgeClass ec_localities_ContainsCrossroad;
	public final EdgeClass ec_localities_HasCapital;
	public final EdgeClass ec_localities_ContainsLocality;
	public final EdgeClass ec_connections_Way;
	public final EdgeClass ec_connections_AirRoute;
	public final EdgeClass ec_connections_Street;
	public final EdgeClass ec_connections_Footpath;
	public final EdgeClass ec_connections_Highway;

	/**
	 * the weak reference to the singleton instance
	 */
	static WeakReference<RouteSchema> theInstance = new WeakReference<RouteSchema>(null);
	
	/**
	 * @return the singleton instance of RouteSchema
	 */
	public static RouteSchema instance() {
		RouteSchema s = theInstance.get();
		if (s != null) {
			return s;
		}
		synchronized (RouteSchema.class) {
			s = theInstance.get();
			if (s != null) {
				return s;
			}
			s = new RouteSchema();
			theInstance = new WeakReference<RouteSchema>(s);
		}
		return s;
	}
	
	/**
	 * Creates a RouteSchema and builds its schema classes.
	 * This constructor is private. Use the <code>instance()</code> method
	 * to acess the schema.
	 */
	private RouteSchema() {
		super("RouteSchema", "de.uni_koblenz.jgralabtest.schemas.greqltestschema");

		{
			EnumDomain dom = createEnumDomain("localities.CountyTags");
			dom.addConst("HIGHEST_PEAK");
			dom.addConst("AREA");
		}

		{
			EnumDomain dom = createEnumDomain("Month");
			dom.addConst("JAN");
			dom.addConst("FEB");
			dom.addConst("MAR");
			dom.addConst("APR");
			dom.addConst("MAY");
			dom.addConst("JUN");
			dom.addConst("JUL");
			dom.addConst("AUG");
			dom.addConst("SEP");
			dom.addConst("OCT");
			dom.addConst("NOV");
			dom.addConst("DEC");
		}

		{
			RecordDomain dom = createRecordDomain("Date");
			dom.addComponent("day", getDomain("Integer"));
			dom.addComponent("month", getDomain("Month"));
			dom.addComponent("year", getDomain("Integer"));
		}

		createMapDomain(getDomain("localities.CountyTags"), getDomain("Double"));

		{
			GraphClass gc = gc_RouteMap = createGraphClass("RouteMap");
			gc.setAbstract(false);
			gc.addAttribute(createAttribute("creationDate", getDomain("Date"), getAttributedElementClass("RouteMap"), null));
			@SuppressWarnings("unused")
			VertexClass vc_Vertex = getDefaultVertexClass();

			{
				VertexClass vc = vc_NamedElement = gc.createVertexClass("NamedElement");
				vc.setAbstract(true);
				vc.addAttribute(createAttribute("name", getDomain("String"), getAttributedElementClass("NamedElement"), null));
			}

			{
				VertexClass vc = vc_junctions_Junction = gc.createVertexClass("junctions.Junction");
				vc.setAbstract(true);
			}

			{
				VertexClass vc = vc_localities_County = gc.createVertexClass("localities.County");
				vc.setAbstract(false);
				vc.addSuperClass(vc_NamedElement);
				vc.addAttribute(createAttribute("tags", getDomain("Map<localities.CountyTags, Double>"), getAttributedElementClass("localities.County"), null));
			}

			{
				VertexClass vc = vc_localities_Locality = gc.createVertexClass("localities.Locality");
				vc.setAbstract(true);
				vc.addSuperClass(vc_NamedElement);
				vc.addAttribute(createAttribute("foundingDate", getDomain("Date"), getAttributedElementClass("localities.Locality"), null));
				vc.addAttribute(createAttribute("inhabitants", getDomain("Integer"), getAttributedElementClass("localities.Locality"), null));
			}

			{
				VertexClass vc = vc_junctions_Crossroad = gc.createVertexClass("junctions.Crossroad");
				vc.setAbstract(false);
				vc.addSuperClass(vc_junctions_Junction);
			}

			{
				VertexClass vc = vc_junctions_Airport = gc.createVertexClass("junctions.Airport");
				vc.setAbstract(false);
				vc.addSuperClass(vc_localities_Locality);
				vc.addSuperClass(vc_junctions_Junction);
			}

			{
				VertexClass vc = vc_localities_Town = gc.createVertexClass("localities.Town");
				vc.setAbstract(false);
				vc.addSuperClass(vc_localities_Locality);
			}

			{
				VertexClass vc = vc_localities_Village = gc.createVertexClass("localities.Village");
				vc.setAbstract(false);
				vc.addSuperClass(vc_localities_Locality);
			}

			{
				VertexClass vc = vc_junctions_Plaza = gc.createVertexClass("junctions.Plaza");
				vc.setAbstract(false);
				vc.addSuperClass(vc_junctions_Crossroad);
				vc.addAttribute(createAttribute("name", getDomain("String"), getAttributedElementClass("junctions.Plaza"), null));
			}

			{
				VertexClass vc = vc_junctions_Roundabout = gc.createVertexClass("junctions.Roundabout");
				vc.setAbstract(false);
				vc.addSuperClass(vc_junctions_Crossroad);
			}

			{
				VertexClass vc = vc_localities_City = gc.createVertexClass("localities.City");
				vc.setAbstract(false);
				vc.addSuperClass(vc_localities_Town);
			}

			{
				EdgeClass ec = ec_connections_Connection = gc.createEdgeClass("connections.Connection",
					vc_junctions_Junction, 0, 2147483647, "src", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_junctions_Junction, 0, 2147483647, "dst", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(true);
				ec.addAttribute(createAttribute("length", getDomain("Double"), getAttributedElementClass("connections.Connection"), null));
			}

			{
				EdgeClass ec = ec_localities_ContainsCrossroad = gc.createEdgeClass("localities.ContainsCrossroad",
					vc_localities_Locality, 0, 1, "locality", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_junctions_Crossroad, 0, 2147483647, "crossroads", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_localities_HasCapital = gc.createEdgeClass("localities.HasCapital",
					vc_localities_County, 0, 1, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_localities_City, 1, 1, "capital", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_localities_ContainsLocality = gc.createEdgeClass("localities.ContainsLocality",
					vc_localities_County, 1, 1, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_localities_Locality, 0, 2147483647, "localities", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_connections_Way = gc.createEdgeClass("connections.Way",
					vc_junctions_Crossroad, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_junctions_Crossroad, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(true);
				ec.addSuperClass(ec_connections_Connection);
			}

			{
				EdgeClass ec = ec_connections_AirRoute = gc.createEdgeClass("connections.AirRoute",
					vc_junctions_Airport, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_junctions_Airport, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(false);
				ec.addSuperClass(ec_connections_Connection);
				ec.addAttribute(createAttribute("routeName", getDomain("String"), getAttributedElementClass("connections.AirRoute"), null));
			}

			{
				EdgeClass ec = ec_connections_Street = gc.createEdgeClass("connections.Street",
					vc_junctions_Crossroad, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_junctions_Crossroad, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(false);
				ec.addSuperClass(ec_connections_Way);
				ec.addAttribute(createAttribute("name", getDomain("String"), getAttributedElementClass("connections.Street"), null));
				ec.addAttribute(createAttribute("oneway", getDomain("Boolean"), getAttributedElementClass("connections.Street"), null));
			}

			{
				EdgeClass ec = ec_connections_Footpath = gc.createEdgeClass("connections.Footpath",
					vc_junctions_Crossroad, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_junctions_Crossroad, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(false);
				ec.addSuperClass(ec_connections_Way);
			}

			{
				EdgeClass ec = ec_connections_Highway = gc.createEdgeClass("connections.Highway",
					vc_junctions_Crossroad, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_junctions_Crossroad, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(false);
				ec.addSuperClass(ec_connections_Street);
				ec.addAttribute(createAttribute("lanes", getDomain("Integer"), getAttributedElementClass("connections.Highway"), null));
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
				return new de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.RouteMapFactoryImpl();
			case TRANSACTION:
				return new de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.trans.RouteMapFactoryImpl();
			case DATABASE:
				return new de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.db.RouteMapFactoryImpl();
		}
		throw new UnsupportedOperationException("No " + implementationType + " support compiled.");
	}

	/**
	 * Creates a new RouteMap graph.
	*/
	public RouteMap createRouteMap(de.uni_koblenz.jgralab.ImplementationType implType) {
		return createRouteMap(implType, null, 100, 100);
	}

	/**
	 * Creates a new RouteMap graph with initial vertex and edge counts <code>vMax</code>, <code>eMax</code>.
	 *
	 * @param vMax initial vertex count
	 * @param eMax initial edge count
	*/
	public RouteMap createRouteMap(de.uni_koblenz.jgralab.ImplementationType implType, String id, int vMax, int eMax) {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return factory.createGraph(gc_RouteMap, id, vMax, eMax);
	}

	/**
	 * Creates a new RouteMap graph.
	*/
	public RouteMap createRouteMap(de.uni_koblenz.jgralab.GraphFactory factory) {
		return factory.createGraph(gc_RouteMap, null, 100, 100);
	}

	/**
	 * Creates a new RouteMap graph.
	*/
	public RouteMap createRouteMap(de.uni_koblenz.jgralab.GraphFactory factory, String id, int vMax, int eMax) {
		return factory.createGraph(gc_RouteMap, id, vMax, eMax);
	}

	/**
	 * Creates a new RouteMap graph in a database with given <code>id</code>.
	 *
	 * @param id Identifier of new graph
	 * @param graphDatabase Database which should contain graph
	 */
	public RouteMap createRouteMap(String id, GraphDatabase graphDatabase) throws GraphDatabaseException{
		return createRouteMap(id, 100, 100, graphDatabase);
	}

	/**
	 * Creates a new RouteMap graph in a database with given <code>id</code>.
	 *
	 * @param id Identifier of new graph
	 * @param vMax Maximum initial count of vertices that can be held in graph.
	 * @param eMax Maximum initial count of edges that can be held in graph.
	 * @param graphDatabase Database which should contain graph
	 */
	public RouteMap createRouteMap(String id, int vMax, int eMax, GraphDatabase graphDatabase) throws GraphDatabaseException{
		de.uni_koblenz.jgralab.impl.GraphFactoryImpl graphFactory = (de.uni_koblenz.jgralab.impl.GraphFactoryImpl) createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.DATABASE);
		graphFactory.setGraphDatabase(graphDatabase);
		RouteMap graph = graphFactory.createGraph(gc_RouteMap, id, vMax, eMax);
		if (!graphDatabase.containsGraph(id)) {
			graphDatabase.insert((de.uni_koblenz.jgralab.impl.db.GraphImpl)graph);
			return graph;
		} else {
			throw new GraphException("Graph with identifier " + id + " already exists in database.");
		}
	}

	public RouteMap loadRouteMap(String filename) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.STANDARD);
		return loadRouteMap(filename, factory, null);
	}

	public RouteMap loadRouteMap(String filename, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.STANDARD);
		return loadRouteMap(filename, factory, pf);
	}

	public RouteMap loadRouteMap(String filename, de.uni_koblenz.jgralab.ImplementationType implType) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return loadRouteMap(filename, factory, null);
	}

	
	public RouteMap loadRouteMap(String filename, de.uni_koblenz.jgralab.ImplementationType implType, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return loadRouteMap(filename, factory, pf);
	}

	public RouteMap loadRouteMap(String filename, de.uni_koblenz.jgralab.GraphFactory factory) throws GraphIOException {
		return GraphIO.loadGraphFromFile(filename, factory, null);
	}

	public RouteMap loadRouteMap(String filename, de.uni_koblenz.jgralab.GraphFactory factory, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
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
