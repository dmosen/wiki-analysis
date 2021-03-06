/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.greqltestschema.impl.db.localities;

import de.uni_koblenz.jgralab.impl.db.VertexImpl;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class CityImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town {

	protected de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date _foundingDate;

	protected int _inhabitants;

	protected java.lang.String _name;

	public CityImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("foundingDate")) return (T) get_foundingDate();
		if (attributeName.equals("inhabitants")) return (T) (java.lang.Integer) get_inhabitants();
		if (attributeName.equals("name")) return (T) get_name();
		throw new NoSuchAttributeException("localities.City doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("foundingDate")) {
			set_foundingDate((de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date) data);
			return;
		}
		if (attributeName.equals("inhabitants")) {
			set_inhabitants((java.lang.Integer) data);
			return;
		}
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("localities.City doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date get_foundingDate() {
		return _foundingDate;
	}

	public void set_foundingDate(de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date _foundingDate) {
		this._foundingDate = _foundingDate;
		attributeChanged("foundingDate");
	}

	public int get_inhabitants() {
		return _inhabitants;
	}

	public void set_inhabitants(int _inhabitants) {
		this._inhabitants = _inhabitants;
		attributeChanged("inhabitants");
	}

	public java.lang.String get_name() {
		return _name;
	}

	public void set_name(java.lang.String _name) {
		this._name = _name;
		attributeChanged("name");
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		
		if (io.isNextToken("(")) {
			_foundingDate = new de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date(io);
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match();
			_foundingDate = null;
		} else {
			throw new GraphIOException("This is no record!");
		}
		set_foundingDate(_foundingDate);
		_inhabitants = io.matchInteger();
		set_inhabitants(_inhabitants);
		_name = io.matchUtfString();
		set_name(_name);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("foundingDate")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			
			if (io.isNextToken("(")) {
				_foundingDate = new de.uni_koblenz.jgralabtest.schemas.greqltestschema.Date(io);
			} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
				io.match();
				_foundingDate = null;
			} else {
				throw new GraphIOException("This is no record!");
			}
			set_foundingDate(_foundingDate);
			return;
		}
		if (attributeName.equals("inhabitants")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_inhabitants = io.matchInteger();
			set_inhabitants(_inhabitants);
			return;
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		throw new NoSuchAttributeException("localities.City doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		if (_foundingDate != null) {
			_foundingDate.writeComponentValues(io);
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		io.writeInteger(_inhabitants);
		io.writeUtfString(_name);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("foundingDate")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (_foundingDate != null) {
				_foundingDate.writeComponentValues(io);
			} else {
				io.writeIdentifier(GraphIO.NULL_LITERAL);
			}
			return io.getStringWriterResult();
		}
		if (attributeName.equals("inhabitants")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeInteger(_inhabitants);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("localities.City doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement getNextNamedElement() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement)getNextVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.NamedElement.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City getNextCity() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City)getNextVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality getNextLocality() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality)getNextVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Locality.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town getNextTown() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town)getNextVertex(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.Town.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality getFirstContainsLocalityIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality getFirstContainsLocalityIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital getFirstHasCapitalIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital getFirstHasCapitalIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad getFirstContainsCrossroadIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad getFirstContainsCrossroadIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad add_crossroads(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.greqltestschema.RouteMap)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.EC, (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.City) this, (de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad> remove_crossroads() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad>();
			de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad edge = (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad next = (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_crossroads(de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad edge = (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad next = (de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad> get_crossroads() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad, de.uni_koblenz.jgralabtest.schemas.greqltestschema.junctions.Crossroad>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.class, EdgeDirection.OUT);
		}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality> getContainsLocalityIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality> getContainsLocalityIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsLocality.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital> getHasCapitalIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital> getHasCapitalIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.HasCapital.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad> getContainsCrossroadIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad> getContainsCrossroadIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad>(this, de.uni_koblenz.jgralabtest.schemas.greqltestschema.localities.ContainsCrossroad.class, direction);
	}
}
