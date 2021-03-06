/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.impl.std;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.std.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class UserImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.User, de.uni_koblenz.jgralab.Vertex {

	protected java.lang.String _name;

	public UserImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.User.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.User.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("name")) return (T) get_name();
		throw new NoSuchAttributeException("User doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("User doesn't contain an attribute " + attributeName);
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
		throw new NoSuchAttributeException("User doesn't contain an attribute " + attributeName);
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
		throw new NoSuchAttributeException("User doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.User getNextUser() {
		return (de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.User)getNextVertex(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.User.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans getFirstLoansIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans getFirstLoansIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans add_item(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Medium vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.SimpleLibraryGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans.EC, (de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.User) this, (de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Medium) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Medium> remove_item() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Medium> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Medium>();
			de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans edge = (de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans next = (de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Medium) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_item(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Medium vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans edge = (de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans next = (de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Medium> get_item() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans, de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Medium>(this, de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans.class, EdgeDirection.OUT);
		}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans> getLoansIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans>(this, de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans> getLoansIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans>(this, de.uni_koblenz.jgralabtest.schemas.eca.simplelibrary.Loans.class, direction);
	}
}
