/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest.impl.trans;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.trans.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class DImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.vertextest.B, de.uni_koblenz.jgralabtest.schemas.vertextest.D {

	public DImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.D.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.D.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("D doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("D doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("D doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("D doesn't contain an attribute " + attributeName);
	}
	public java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes() {
		java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes = new java.util.HashSet<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>>();
		return attributes;
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.B getNextB() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.B)getNextVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.B.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.D getNextD() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.D)getNextVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.D.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.F getFirstFIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.F)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.F getFirstFIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.F)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getFirstEIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.E)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getFirstEIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.E)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.K getFirstKIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.K)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.K.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.K getFirstKIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.K)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.H getFirstHIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.H)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.H getFirstHIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.H)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.G getFirstGIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.G)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.G getFirstGIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.G)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.H add_sourceH(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.H.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.A) vertex, (de.uni_koblenz.jgralabtest.schemas.vertextest.D) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> remove_sourceH() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.A> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.A>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.H edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.H) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.H next = (de.uni_koblenz.jgralabtest.schemas.vertextest.H) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.A) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_sourceH(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.H edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.H) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.H next = (de.uni_koblenz.jgralabtest.schemas.vertextest.H) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> get_sourceH() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.H, de.uni_koblenz.jgralabtest.schemas.vertextest.A>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, EdgeDirection.IN);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.K add_sourceK(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.K.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.A) vertex, (de.uni_koblenz.jgralabtest.schemas.vertextest.D) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> remove_sourceK() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.A> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.A>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.K edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.K) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.K next = (de.uni_koblenz.jgralabtest.schemas.vertextest.K) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.A) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_sourceK(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.K edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.K) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.K next = (de.uni_koblenz.jgralabtest.schemas.vertextest.K) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> get_sourceK() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.K, de.uni_koblenz.jgralabtest.schemas.vertextest.A>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, EdgeDirection.IN);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.F add_sourceF(de.uni_koblenz.jgralabtest.schemas.vertextest.C vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.F.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.C) vertex, (de.uni_koblenz.jgralabtest.schemas.vertextest.D) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.C> remove_sourceF() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.C> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.C>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.F edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.F next = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.C) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_sourceF(de.uni_koblenz.jgralabtest.schemas.vertextest.C vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.F edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.F next = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.C> get_sourceF() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.F, de.uni_koblenz.jgralabtest.schemas.vertextest.C>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.IN);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.G add_sourceG(de.uni_koblenz.jgralabtest.schemas.vertextest.C vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.G.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.C) vertex, (de.uni_koblenz.jgralabtest.schemas.vertextest.D) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.C> remove_sourceG() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.C> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.C>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.G edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.G) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.G next = (de.uni_koblenz.jgralabtest.schemas.vertextest.G) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.C) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_sourceG(de.uni_koblenz.jgralabtest.schemas.vertextest.C vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.G edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.G) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.G next = (de.uni_koblenz.jgralabtest.schemas.vertextest.G) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.C> get_sourceG() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.G, de.uni_koblenz.jgralabtest.schemas.vertextest.C>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, EdgeDirection.IN);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.E add_sourceE(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.E.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.A) vertex, (de.uni_koblenz.jgralabtest.schemas.vertextest.D) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> remove_sourceE() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.A> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.A>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.E edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.E) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.E next = (de.uni_koblenz.jgralabtest.schemas.vertextest.E) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.A) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_sourceE(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.E edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.E) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.E next = (de.uni_koblenz.jgralabtest.schemas.vertextest.E) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.E.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> get_sourceE() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.E, de.uni_koblenz.jgralabtest.schemas.vertextest.A>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.E.class, EdgeDirection.IN);
		}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.F> getFIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.F>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.F.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.F> getFIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.F>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.E> getEIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.E>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.E.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.E> getEIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.E>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.E.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.K> getKIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.K>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.K.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.K> getKIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.K>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.H> getHIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.H>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.H.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.H> getHIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.H>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.G> getGIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.G>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.G.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.G> getGIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.G>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, direction);
	}
}
