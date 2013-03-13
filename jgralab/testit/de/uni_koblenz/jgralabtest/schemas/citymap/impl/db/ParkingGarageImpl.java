/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.citymap.impl.db;

import de.uni_koblenz.jgralab.impl.db.VertexImpl;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class ParkingGarageImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.citymap.CarPark, de.uni_koblenz.jgralabtest.schemas.citymap.ParkingGarage {

	protected int _capacity;

	protected java.lang.String _name;

	public ParkingGarageImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.citymap.ParkingGarage.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.citymap.ParkingGarage.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("capacity")) return (T) (java.lang.Integer) get_capacity();
		if (attributeName.equals("name")) return (T) get_name();
		throw new NoSuchAttributeException("ParkingGarage doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("capacity")) {
			set_capacity((java.lang.Integer) data);
			return;
		}
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("ParkingGarage doesn't contain an attribute " + attributeName);
	}

	public int get_capacity() {
		return _capacity;
	}

	public void set_capacity(int _capacity) {
		this._capacity = _capacity;
		attributeChanged("capacity");
	}

	public java.lang.String get_name() {
		return _name;
	}

	public void set_name(java.lang.String _name) {
		this._name = _name;
		attributeChanged("name");
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_capacity = io.matchInteger();
		set_capacity(_capacity);
		_name = io.matchUtfString();
		set_name(_name);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("capacity")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_capacity = io.matchInteger();
			set_capacity(_capacity);
			return;
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		throw new NoSuchAttributeException("ParkingGarage doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeInteger(_capacity);
		io.writeUtfString(_name);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("capacity")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeInteger(_capacity);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("ParkingGarage doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.CarPark getNextCarPark() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.CarPark)getNextVertex(de.uni_koblenz.jgralabtest.schemas.citymap.CarPark.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.Junction getNextJunction() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Junction)getNextVertex(de.uni_koblenz.jgralabtest.schemas.citymap.Junction.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.ParkingGarage getNextParkingGarage() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.ParkingGarage)getNextVertex(de.uni_koblenz.jgralabtest.schemas.citymap.ParkingGarage.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.Way getFirstWayIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Way)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.Way getFirstWayIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Way)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.Bridge getFirstBridgeIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Bridge)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Bridge.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.Bridge getFirstBridgeIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Bridge)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Bridge.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.Street getFirstStreetIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Street)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Street.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.Street getFirstStreetIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Street)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Street.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.Footpath getFirstFootpathIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Footpath)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Footpath.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.Footpath getFirstFootpathIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Footpath)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Footpath.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.citymap.Way add_to(de.uni_koblenz.jgralabtest.schemas.citymap.Junction vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.citymap.CityMap)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.citymap.Way.EC, (de.uni_koblenz.jgralabtest.schemas.citymap.ParkingGarage) this, (de.uni_koblenz.jgralabtest.schemas.citymap.Junction) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.citymap.Junction> remove_to() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.citymap.Junction> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.citymap.Junction>();
			de.uni_koblenz.jgralabtest.schemas.citymap.Way edge = (de.uni_koblenz.jgralabtest.schemas.citymap.Way) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.citymap.Way next = (de.uni_koblenz.jgralabtest.schemas.citymap.Way) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.citymap.Junction) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_to(de.uni_koblenz.jgralabtest.schemas.citymap.Junction vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.citymap.Way edge = (de.uni_koblenz.jgralabtest.schemas.citymap.Way) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.citymap.Way next = (de.uni_koblenz.jgralabtest.schemas.citymap.Way) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.citymap.Junction> get_to() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.citymap.Way, de.uni_koblenz.jgralabtest.schemas.citymap.Junction>(this, de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.citymap.Way add_from(de.uni_koblenz.jgralabtest.schemas.citymap.Junction vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.citymap.CityMap)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.citymap.Way.EC, (de.uni_koblenz.jgralabtest.schemas.citymap.Junction) vertex, (de.uni_koblenz.jgralabtest.schemas.citymap.ParkingGarage) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.citymap.Junction> remove_from() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.citymap.Junction> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.citymap.Junction>();
			de.uni_koblenz.jgralabtest.schemas.citymap.Way edge = (de.uni_koblenz.jgralabtest.schemas.citymap.Way) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.citymap.Way next = (de.uni_koblenz.jgralabtest.schemas.citymap.Way) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.citymap.Junction) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_from(de.uni_koblenz.jgralabtest.schemas.citymap.Junction vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.citymap.Way edge = (de.uni_koblenz.jgralabtest.schemas.citymap.Way) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.citymap.Way next = (de.uni_koblenz.jgralabtest.schemas.citymap.Way) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.citymap.Junction> get_from() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.citymap.Way, de.uni_koblenz.jgralabtest.schemas.citymap.Junction>(this, de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, EdgeDirection.IN);
		}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.citymap.Way> getWayIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.citymap.Way>(this, de.uni_koblenz.jgralabtest.schemas.citymap.Way.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.citymap.Way> getWayIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.citymap.Way>(this, de.uni_koblenz.jgralabtest.schemas.citymap.Way.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.citymap.Bridge> getBridgeIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.citymap.Bridge>(this, de.uni_koblenz.jgralabtest.schemas.citymap.Bridge.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.citymap.Bridge> getBridgeIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.citymap.Bridge>(this, de.uni_koblenz.jgralabtest.schemas.citymap.Bridge.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.citymap.Street> getStreetIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.citymap.Street>(this, de.uni_koblenz.jgralabtest.schemas.citymap.Street.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.citymap.Street> getStreetIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.citymap.Street>(this, de.uni_koblenz.jgralabtest.schemas.citymap.Street.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.citymap.Footpath> getFootpathIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.citymap.Footpath>(this, de.uni_koblenz.jgralabtest.schemas.citymap.Footpath.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.citymap.Footpath> getFootpathIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.citymap.Footpath>(this, de.uni_koblenz.jgralabtest.schemas.citymap.Footpath.class, direction);
	}
}
