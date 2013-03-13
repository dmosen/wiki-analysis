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

public class IntersectionImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.citymap.Intersection, de.uni_koblenz.jgralabtest.schemas.citymap.Junction {

	protected java.lang.String _name;

	protected boolean _roundabout;

	public IntersectionImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.citymap.Intersection.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.citymap.Intersection.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("name")) return (T) get_name();
		if (attributeName.equals("roundabout")) return (T) (java.lang.Boolean) is_roundabout();
		throw new NoSuchAttributeException("Intersection doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		if (attributeName.equals("roundabout")) {
			set_roundabout((java.lang.Boolean) data);
			return;
		}
		throw new NoSuchAttributeException("Intersection doesn't contain an attribute " + attributeName);
	}

	public java.lang.String get_name() {
		return _name;
	}

	public void set_name(java.lang.String _name) {
		this._name = _name;
		attributeChanged("name");
	}

	public boolean is_roundabout() {
		return _roundabout;
	}

	public void set_roundabout(boolean _roundabout) {
		this._roundabout = _roundabout;
		attributeChanged("roundabout");
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_name = io.matchUtfString();
		set_name(_name);
		_roundabout = io.matchBoolean();
		set_roundabout(_roundabout);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		if (attributeName.equals("roundabout")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_roundabout = io.matchBoolean();
			set_roundabout(_roundabout);
			return;
		}
		throw new NoSuchAttributeException("Intersection doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_name);
		io.writeBoolean(_roundabout);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("roundabout")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeBoolean(_roundabout);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("Intersection doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.Intersection getNextIntersection() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Intersection)getNextVertex(de.uni_koblenz.jgralabtest.schemas.citymap.Intersection.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.citymap.Junction getNextJunction() {
		return (de.uni_koblenz.jgralabtest.schemas.citymap.Junction)getNextVertex(de.uni_koblenz.jgralabtest.schemas.citymap.Junction.class);
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
			return ((de.uni_koblenz.jgralabtest.schemas.citymap.CityMap)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.citymap.Way.EC, (de.uni_koblenz.jgralabtest.schemas.citymap.Intersection) this, (de.uni_koblenz.jgralabtest.schemas.citymap.Junction) vertex);
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
			return ((de.uni_koblenz.jgralabtest.schemas.citymap.CityMap)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.citymap.Way.EC, (de.uni_koblenz.jgralabtest.schemas.citymap.Junction) vertex, (de.uni_koblenz.jgralabtest.schemas.citymap.Intersection) this);
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
