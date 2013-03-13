/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.grumlschema.impl.std.structure;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.std.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class ConstraintImpl extends VertexImpl implements de.uni_koblenz.jgralab.Vertex, de.uni_koblenz.jgralab.grumlschema.structure.Constraint {

	protected java.lang.String _message;

	protected java.lang.String _offendingElementsQuery;

	protected java.lang.String _predicateQuery;

	public ConstraintImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralab.grumlschema.structure.Constraint.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralab.grumlschema.structure.Constraint.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("message")) return (T) get_message();
		if (attributeName.equals("offendingElementsQuery")) return (T) get_offendingElementsQuery();
		if (attributeName.equals("predicateQuery")) return (T) get_predicateQuery();
		throw new NoSuchAttributeException("structure.Constraint doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("message")) {
			set_message((java.lang.String) data);
			return;
		}
		if (attributeName.equals("offendingElementsQuery")) {
			set_offendingElementsQuery((java.lang.String) data);
			return;
		}
		if (attributeName.equals("predicateQuery")) {
			set_predicateQuery((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("structure.Constraint doesn't contain an attribute " + attributeName);
	}

	public java.lang.String get_message() {
		return _message;
	}

	public void set_message(java.lang.String _message) {
		ecaAttributeChanging("message", this._message, _message);
		Object oldValue = this._message;
		this._message = _message;
		graphModified();
	ecaAttributeChanged("message", oldValue, _message);
	}

	public java.lang.String get_offendingElementsQuery() {
		return _offendingElementsQuery;
	}

	public void set_offendingElementsQuery(java.lang.String _offendingElementsQuery) {
		ecaAttributeChanging("offendingElementsQuery", this._offendingElementsQuery, _offendingElementsQuery);
		Object oldValue = this._offendingElementsQuery;
		this._offendingElementsQuery = _offendingElementsQuery;
		graphModified();
	ecaAttributeChanged("offendingElementsQuery", oldValue, _offendingElementsQuery);
	}

	public java.lang.String get_predicateQuery() {
		return _predicateQuery;
	}

	public void set_predicateQuery(java.lang.String _predicateQuery) {
		ecaAttributeChanging("predicateQuery", this._predicateQuery, _predicateQuery);
		Object oldValue = this._predicateQuery;
		this._predicateQuery = _predicateQuery;
		graphModified();
	ecaAttributeChanged("predicateQuery", oldValue, _predicateQuery);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_message = io.matchUtfString();
		set_message(_message);
		_offendingElementsQuery = io.matchUtfString();
		set_offendingElementsQuery(_offendingElementsQuery);
		_predicateQuery = io.matchUtfString();
		set_predicateQuery(_predicateQuery);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("message")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_message = io.matchUtfString();
			set_message(_message);
			return;
		}
		if (attributeName.equals("offendingElementsQuery")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_offendingElementsQuery = io.matchUtfString();
			set_offendingElementsQuery(_offendingElementsQuery);
			return;
		}
		if (attributeName.equals("predicateQuery")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_predicateQuery = io.matchUtfString();
			set_predicateQuery(_predicateQuery);
			return;
		}
		throw new NoSuchAttributeException("structure.Constraint doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_message);
		io.writeUtfString(_offendingElementsQuery);
		io.writeUtfString(_predicateQuery);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("message")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_message);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("offendingElementsQuery")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_offendingElementsQuery);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("predicateQuery")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_predicateQuery);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("structure.Constraint doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.structure.Constraint getNextConstraint() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.Constraint)getNextVertex(de.uni_koblenz.jgralab.grumlschema.structure.Constraint.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint getFirstHasConstraintIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint getFirstHasConstraintIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint add_constrainedElement(de.uni_koblenz.jgralab.grumlschema.structure.AttributedElementClass vertex) {
			return ((de.uni_koblenz.jgralab.grumlschema.SchemaGraph)getGraph()).createEdge(de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint.EC, (de.uni_koblenz.jgralab.grumlschema.structure.AttributedElementClass) vertex, (de.uni_koblenz.jgralab.grumlschema.structure.Constraint) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralab.grumlschema.structure.AttributedElementClass> remove_constrainedElement() {
			java.util.List<de.uni_koblenz.jgralab.grumlschema.structure.AttributedElementClass> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralab.grumlschema.structure.AttributedElementClass>();
			de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint edge = (de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint) getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint next = (de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint) edge.getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralab.grumlschema.structure.AttributedElementClass) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_constrainedElement(de.uni_koblenz.jgralab.grumlschema.structure.AttributedElementClass vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint edge = (de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint) getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint next = (de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint) edge.getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public de.uni_koblenz.jgralab.grumlschema.structure.AttributedElementClass get_constrainedElement() {
			de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint edge = (de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint) getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint.class, EdgeDirection.IN);
			if (edge != null) {
				return (de.uni_koblenz.jgralab.grumlschema.structure.AttributedElementClass) edge.getThat();
			}
			return null;
		}

	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint> getHasConstraintIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint>(this, de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint> getHasConstraintIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint>(this, de.uni_koblenz.jgralab.grumlschema.structure.HasConstraint.class, direction);
	}
}
