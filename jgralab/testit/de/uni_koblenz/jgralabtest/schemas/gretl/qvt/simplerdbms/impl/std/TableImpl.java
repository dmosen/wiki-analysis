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

public class TableImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table {

	protected java.lang.String _kind;

	protected java.lang.String _name;

	public TableImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("kind")) return (T) get_kind();
		if (attributeName.equals("name")) return (T) get_name();
		throw new NoSuchAttributeException("Table doesn't contain an attribute " + attributeName);
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
		throw new NoSuchAttributeException("Table doesn't contain an attribute " + attributeName);
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
		throw new NoSuchAttributeException("Table doesn't contain an attribute " + attributeName);
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
		throw new NoSuchAttributeException("Table doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement getNextRModelElement() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement)getNextVertex(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table getNextTable() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table)getNextVertex(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey getFirstHasPrimaryKeyIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey getFirstHasPrimaryKeyIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable getFirstHasTableIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable getFirstHasTableIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable.class, orientation);
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
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn getFirstHasColumnIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn getFirstHasColumnIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey add_key(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey.EC, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table) this, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key> remove_key() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key>();
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_key(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key get_key() {
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey.class, EdgeDirection.OUT);
			if (edge != null) {
				return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key) edge.getThat();
			}
			return null;
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn add_column(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn.EC, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table) this, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column> remove_column() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column>();
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_column(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column> get_column() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey add_foreignKey(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.EC, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table) this, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey> remove_foreignKey() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey>();
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_foreignKey(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey> get_foreignKey() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable add_schema(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Schema vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable.EC, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Schema) vertex, (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Schema> remove_schema() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Schema> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Schema>();
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Schema) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_schema(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Schema vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable next = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Schema get_schema() {
			de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable edge = (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable.class, EdgeDirection.IN);
			if (edge != null) {
				return (de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Schema) edge.getThat();
			}
			return null;
		}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey> getHasPrimaryKeyIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey> getHasPrimaryKeyIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable> getHasTableIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable> getHasTableIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable.class, direction);
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
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn> getHasColumnIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn> getHasColumnIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn>(this, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn.class, direction);
	}
}
