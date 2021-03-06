/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.trans;

import de.uni_koblenz.jgralab.impl.EdgeIterable;
import de.uni_koblenz.jgralab.impl.VertexIterable;

import de.uni_koblenz.jgralab.impl.trans.GraphImpl;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;

import java.io.IOException;

import org.pcollections.POrderedSet;

public class RouteMapImpl extends GraphImpl implements de.uni_koblenz.jgralab.Graph, de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteMap {

	protected de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date> _creationDate;

	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public RouteMapImpl() {
		this(null);
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public RouteMapImpl(int vMax, int eMax) {
		this(null, vMax, eMax);
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public RouteMapImpl(java.lang.String id, int vMax, int eMax) {
		super(id, de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteMap.GC, vMax, eMax);
		initializeAttributesWithDefaultValues();
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public RouteMapImpl(java.lang.String id) {
		super(id, de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteMap.GC);
		initializeAttributesWithDefaultValues();
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.GraphClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteMap.GC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Graph> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteMap.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("creationDate")) return (T) get_creationDate();
		throw new NoSuchAttributeException("RouteMap doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("creationDate")) {
			set_creationDate((de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date) data);
			return;
		}
		throw new NoSuchAttributeException("RouteMap doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date get_creationDate() {
		if (_creationDate == null)
			return null;
		de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date value = _creationDate.getValidValue(this.getCurrentTransaction());
		return (value == null) ? null : value;
	}

	public void set_creationDate(de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date _creationDate) {
		if (this.isLoading()) {
			this._creationDate = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date>(this, _creationDate, "creationDate");
		}
		if (this._creationDate == null) {
			this._creationDate = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date>(this, null, "creationDate");
		}
		this._creationDate.setValidValue((de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date) _creationDate, this.getCurrentTransaction());
		attributeChanged(this._creationDate);
		graphModified();
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date _creationDate = null;
		if (io.isNextToken("(")) {
			_creationDate = new de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date(io);
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match();
			_creationDate = null;
		} else {
			throw new GraphIOException("This is no record!");
		}
		set_creationDate(_creationDate);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("creationDate")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date tmpVar = null;
			
			if (io.isNextToken("(")) {
				tmpVar = new de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date(io);
			} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
				io.match();
				tmpVar = null;
			} else {
				throw new GraphIOException("This is no record!");
			}
			set_creationDate(tmpVar);
			return;
		}
		throw new NoSuchAttributeException("RouteMap doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		if (get_creationDate() != null) {
			get_creationDate().writeComponentValues(io);
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("creationDate")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (get_creationDate() != null) {
				get_creationDate().writeComponentValues(io);
			} else {
				io.writeIdentifier(GraphIO.NULL_LITERAL);
			}
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("RouteMap doesn't contain an attribute " + attributeName);
	}
	public java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes() {
		java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes = new java.util.HashSet<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>>();
		attributes.add(_creationDate);
		return attributes;
	}
	
	protected GreqlEvaluator greqlEvaluator;

	@Override
	public synchronized <T extends Vertex> POrderedSet<T> reachableVertices(Vertex startVertex, String pathDescription, Class<T> vertexType) {
		if (greqlEvaluator == null) {
			greqlEvaluator = new GreqlEvaluator((String) null, this, null);
		}
		greqlEvaluator.setVariable("v", startVertex);
		greqlEvaluator.setQuery("using v: v " + pathDescription);
		greqlEvaluator.startEvaluation();
		return greqlEvaluator.getResultSet();
	}

	// ------------------------ Code for NamedElement ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement getFirstNamedElement() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement.VC);
	}

	// ------------------------ Code for connections.AirRoute ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute getFirstAirRoute() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute createAirRoute(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute createAirRoute(int id, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for connections.Connection ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection getFirstConnection() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection.EC);
	}

	// ------------------------ Code for connections.Footpath ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath getFirstFootpath() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath createFootpath(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath createFootpath(int id, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for connections.Highway ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway getFirstHighway() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway createHighway(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway createHighway(int id, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for connections.Street ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street getFirstStreet() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street createStreet(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street createStreet(int id, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for connections.Way ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way getFirstWay() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way.EC);
	}

	// ------------------------ Code for junctions.Airport ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport getFirstAirport() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport createAirport() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport createAirport(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport.VC, id, this);
	}

	// ------------------------ Code for junctions.Crossroad ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad getFirstCrossroad() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad createCrossroad() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad createCrossroad(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad.VC, id, this);
	}

	// ------------------------ Code for junctions.Junction ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction getFirstJunction() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction.VC);
	}

	// ------------------------ Code for junctions.Plaza ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza getFirstPlaza() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza createPlaza() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza createPlaza(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza.VC, id, this);
	}

	// ------------------------ Code for junctions.Roundabout ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout getFirstRoundabout() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout createRoundabout() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout createRoundabout(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout.VC, id, this);
	}

	// ------------------------ Code for localities.City ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City getFirstCity() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City createCity() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City createCity(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City.VC, id, this);
	}

	// ------------------------ Code for localities.ContainsCrossroad ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad getFirstContainsCrossroad() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad createContainsCrossroad(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad createContainsCrossroad(int id, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for localities.ContainsLocality ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality getFirstContainsLocality() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality createContainsLocality(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality createContainsLocality(int id, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for localities.County ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County getFirstCounty() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County createCounty() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County createCounty(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County.VC, id, this);
	}

	// ------------------------ Code for localities.HasCapital ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital getFirstHasCapital() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital createHasCapital(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital createHasCapital(int id, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County alpha, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital> createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for localities.Locality ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality getFirstLocality() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality.VC);
	}

	// ------------------------ Code for localities.Town ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town getFirstTown() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town createTown() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town createTown(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town.VC, id, this);
	}

	// ------------------------ Code for localities.Village ------------------------

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village getFirstVillage() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village createVillage() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village createVillage(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village> createVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village.VC, id, this);
	}

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute> getAirRouteEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.AirRoute.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath> getFootpathEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Footpath.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway> getHighwayEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Highway.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection> getConnectionEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Connection.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality> getContainsLocalityEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street> getStreetEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Street.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital> getHasCapitalEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad> getContainsCrossroadEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way> getWayEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.connections.Way.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality> getLocalityVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout> getRoundaboutVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Roundabout.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction> getJunctionVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Junction.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad> getCrossroadVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport> getAirportVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Airport.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement> getNamedElementVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County> getCountyVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.County.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City> getCityVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town> getTownVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza> getPlazaVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Plaza.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village> getVillageVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Village.class);
	}
	
}
