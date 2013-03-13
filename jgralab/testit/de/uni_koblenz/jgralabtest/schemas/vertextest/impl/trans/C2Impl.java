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

public class C2Impl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.vertextest.C, de.uni_koblenz.jgralabtest.schemas.vertextest.C2 {

	public C2Impl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.C2.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.C2.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("C2 doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("C2 doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("C2 doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("C2 doesn't contain an attribute " + attributeName);
	}
	public java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes() {
		java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes = new java.util.HashSet<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>>();
		return attributes;
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.A getNextA() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.A)getNextVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.A.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.C getNextC() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.C)getNextVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.C.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.C2 getNextC2() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.C2)getNextVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.C2.class);
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
	public de.uni_koblenz.jgralabtest.schemas.vertextest.J getFirstJIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.J)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.J.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.J getFirstJIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.J)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.J.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getFirstIIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.I)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getFirstIIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.I)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.I add_sourceI(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.I.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.A) vertex, (de.uni_koblenz.jgralabtest.schemas.vertextest.C2) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> remove_sourceI() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.A> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.A>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.I edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.I) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.I next = (de.uni_koblenz.jgralabtest.schemas.vertextest.I) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.A) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_sourceI(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.I edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.I) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.I next = (de.uni_koblenz.jgralabtest.schemas.vertextest.I) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> get_sourceI() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.I, de.uni_koblenz.jgralabtest.schemas.vertextest.A>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, EdgeDirection.IN);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.J add_u(de.uni_koblenz.jgralabtest.schemas.vertextest.D2 vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.J.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.C2) this, (de.uni_koblenz.jgralabtest.schemas.vertextest.D2) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D2> remove_u() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.D2> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.D2>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.J edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.J) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.J.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.J next = (de.uni_koblenz.jgralabtest.schemas.vertextest.J) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.J.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.D2) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_u(de.uni_koblenz.jgralabtest.schemas.vertextest.D2 vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.J edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.J) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.J.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.J next = (de.uni_koblenz.jgralabtest.schemas.vertextest.J) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.J.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D2> get_u() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.J, de.uni_koblenz.jgralabtest.schemas.vertextest.D2>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.J.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.G add_z(de.uni_koblenz.jgralabtest.schemas.vertextest.D vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.G.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.C2) this, (de.uni_koblenz.jgralabtest.schemas.vertextest.D) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> remove_z() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.D> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.D>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.G edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.G) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.G next = (de.uni_koblenz.jgralabtest.schemas.vertextest.G) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.D) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_z(de.uni_koblenz.jgralabtest.schemas.vertextest.D vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.G edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.G) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.G next = (de.uni_koblenz.jgralabtest.schemas.vertextest.G) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> get_z() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.G, de.uni_koblenz.jgralabtest.schemas.vertextest.D>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.G.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.H add_w(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.H.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.C2) this, (de.uni_koblenz.jgralabtest.schemas.vertextest.B) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.B> remove_w() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.B> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.B>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.H edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.H) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.H next = (de.uni_koblenz.jgralabtest.schemas.vertextest.H) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.B) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_w(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.H edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.H) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.H next = (de.uni_koblenz.jgralabtest.schemas.vertextest.H) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.B> get_w() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.H, de.uni_koblenz.jgralabtest.schemas.vertextest.B>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.H.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.I add_v(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.I.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.C2) this, (de.uni_koblenz.jgralabtest.schemas.vertextest.A) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> remove_v() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.A> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.A>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.I edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.I) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.I next = (de.uni_koblenz.jgralabtest.schemas.vertextest.I) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.A) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_v(de.uni_koblenz.jgralabtest.schemas.vertextest.A vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.I edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.I) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.I next = (de.uni_koblenz.jgralabtest.schemas.vertextest.I) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.A> get_v() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.I, de.uni_koblenz.jgralabtest.schemas.vertextest.A>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.F add_y(de.uni_koblenz.jgralabtest.schemas.vertextest.D vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.F.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.C2) this, (de.uni_koblenz.jgralabtest.schemas.vertextest.D) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> remove_y() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.D> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.D>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.F edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.F next = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.D) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_y(de.uni_koblenz.jgralabtest.schemas.vertextest.D vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.F edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.F next = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> get_y() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.F, de.uni_koblenz.jgralabtest.schemas.vertextest.D>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.F add_x(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex) {
			if (!(vertex instanceof de.uni_koblenz.jgralabtest.schemas.vertextest.D)) {
				throw new de.uni_koblenz.jgralab.GraphException("The rolename x was redefined at the vertex class de.uni_koblenz.jgralabtest.schemas.vertextest.C2. Only vertices of de.uni_koblenz.jgralabtest.schemas.vertextest.D are allowed."); 
			}
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.F.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.C2) this, (de.uni_koblenz.jgralabtest.schemas.vertextest.D) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> remove_x() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.D> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.D>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.F edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.F next = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.D) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_x(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.F edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.F next = (de.uni_koblenz.jgralabtest.schemas.vertextest.F) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.F.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.D> get_x() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.E, de.uni_koblenz.jgralabtest.schemas.vertextest.D>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.E.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.K add_targetK(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.K.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.C2) this, (de.uni_koblenz.jgralabtest.schemas.vertextest.B) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.B> remove_targetK() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.B> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.B>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.K edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.K) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.K next = (de.uni_koblenz.jgralabtest.schemas.vertextest.K) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.B) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_targetK(de.uni_koblenz.jgralabtest.schemas.vertextest.B vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.K edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.K) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.K next = (de.uni_koblenz.jgralabtest.schemas.vertextest.K) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.B> get_targetK() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.K, de.uni_koblenz.jgralabtest.schemas.vertextest.B>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.K.class, EdgeDirection.OUT);
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

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.J> getJIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.J>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.J.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.J> getJIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.J>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.J.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.I> getIIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.I>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.I.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.I> getIIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.I>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.I.class, direction);
	}
}
