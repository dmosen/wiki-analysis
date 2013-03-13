/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.std.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class ForeignKeyImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement {

	protected java.lang.String _kind;

	protected java.lang.String _name;

	public ForeignKeyImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("kind")) return (T) get_kind();
		if (attributeName.equals("name")) return (T) get_name();
		throw new NoSuchAttributeException("ForeignKey doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("kind")) {
			set_kind((java.lang.String) data);
			return;
		}
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("ForeignKey doesn't contain an attribute " + attributeName);
	}

	public java.lang.String get_kind() {
		return _kind;
	}

	public void set_kind(java.lang.String _kind) {
		ecaAttributeChanging("kind", this._kind, _kind);
		Object oldValue = this._kind;
		this._kind = _kind;
		graphModified();
	ecaAttributeChanged("kind", oldValue, _kind);
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
		_kind = io.matchUtfString();
		set_kind(_kind);
		_name = io.matchUtfString();
		set_name(_name);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("kind")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_kind = io.matchUtfString();
			set_kind(_kind);
			return;
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		throw new NoSuchAttributeException("ForeignKey doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_kind);
		io.writeUtfString(_name);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("kind")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_kind);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("ForeignKey doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey getNextForeignKey() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey)getNextVertex(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement getNextRModelElement() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement)getNextVertex(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey getFirstHasForeignKeyIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey getFirstHasForeignKeyIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo getFirstRefersToIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo getFirstRefersToIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn getFirstIsInIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn getFirstIsInIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn add_column(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn.EC, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey) this, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column> remove_column() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column>();
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_column(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column get_column() {
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn.class, EdgeDirection.OUT);
			if (edge != null) {
				return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column) edge.getThat();
			}
			return null;
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo add_referred(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo.EC, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey) this, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key> remove_referred() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key>();
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_referred(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key get_referred() {
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo.class, EdgeDirection.OUT);
			if (edge != null) {
				return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key) edge.getThat();
			}
			return null;
		}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey> getHasForeignKeyIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey> getHasForeignKeyIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo> getRefersToIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo> getRefersToIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn> getIsInIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn> getIsInIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn.class, direction);
	}
}
