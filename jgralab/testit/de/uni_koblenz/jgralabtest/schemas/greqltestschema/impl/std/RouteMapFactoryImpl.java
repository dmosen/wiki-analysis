/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std;

import de.uni_koblenz.jgralab.impl.GraphFactoryImpl;

import de.uni_koblenz.jgralab.ImplementationType;

import de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteMapFactory;

public class RouteMapFactoryImpl extends GraphFactoryImpl implements RouteMapFactory {

	public RouteMapFactoryImpl() {
		super(de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteSchema.instance(), ImplementationType.STANDARD);
		createMaps();
		setGraphImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteMap.GC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.RouteMapImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County.VC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.localities.CountyImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad.VC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.junctions.CrossroadImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport.VC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.junctions.AirportImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town.VC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.localities.TownImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village.VC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.localities.VillageImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza.VC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.junctions.PlazaImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout.VC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.junctions.RoundaboutImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City.VC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.localities.CityImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.EC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.localities.ContainsCrossroadImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital.EC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.localities.HasCapitalImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality.EC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.localities.ContainsLocalityImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute.EC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.connections.AirRouteImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street.EC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.connections.StreetImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath.EC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.connections.FootpathImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway.EC, de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.std.connections.HighwayImpl.class);
	}
}
