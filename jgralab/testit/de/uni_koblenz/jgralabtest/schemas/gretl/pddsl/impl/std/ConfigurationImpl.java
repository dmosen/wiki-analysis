/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.impl.std;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.std.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class ConfigurationImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Configuration, de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Element {

	protected java.lang.String _name;

	public ConfigurationImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Configuration.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Configuration.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("name")) return (T) get_name();
		throw new NoSuchAttributeException("Configuration doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("Configuration doesn't contain an attribute " + attributeName);
	}

	public java.lang.String get_name() {
		return _name;
	}

	public void set_name(java.lang.String _name) {
		ecaAttributeChanging("name", this._name, _name);
		Object oldValue = this._name;
		this._name = _name;
		graphModified();
	ecaAttributeChanged("name", oldValue, _name);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_name = io.matchUtfString();
		set_name(_name);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		throw new NoSuchAttributeException("Configuration doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_name);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("Configuration doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Configuration getNextConfiguration() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Configuration)getNextVertex(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Configuration.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Element getNextElement() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Element)getNextVertex(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Element.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot getFirstHasSlotIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot getFirstHasSlotIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig getFirstHasConfigIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig getFirstHasConfigIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot add_slot(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.PddslGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot.EC, (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Configuration) this, (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot> remove_slot() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot>();
			de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot edge = (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot next = (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_slot(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot edge = (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot next = (de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot> get_slot() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot, de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.Slot>(this, de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot.class, EdgeDirection.OUT);
		}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot> getHasSlotIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot>(this, de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot> getHasSlotIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot>(this, de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasSlot.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig> getHasConfigIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig>(this, de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig> getHasConfigIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig>(this, de.uni_koblenz.jgralabtest.schemas.gretl.pddsl.HasConfig.class, direction);
	}
}
