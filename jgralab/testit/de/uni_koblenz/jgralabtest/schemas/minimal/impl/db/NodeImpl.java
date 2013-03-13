/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.minimal.impl.db;

import de.uni_koblenz.jgralab.impl.db.VertexImpl;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class NodeImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.minimal.Node, de.uni_koblenz.jgralab.Vertex {

	protected org.pcollections.PMap<java.lang.Integer, java.lang.String> _nodeMap;

	public NodeImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.minimal.Node.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.minimal.Node.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("nodeMap")) return (T) get_nodeMap();
		throw new NoSuchAttributeException("Node doesn't contain an attribute " + attributeName);
	}

	@SuppressWarnings("unchecked")
	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("nodeMap")) {
			set_nodeMap((org.pcollections.PMap<java.lang.Integer, java.lang.String>) data);
			return;
		}
		throw new NoSuchAttributeException("Node doesn't contain an attribute " + attributeName);
	}

	public org.pcollections.PMap<java.lang.Integer, java.lang.String> get_nodeMap() {
		return _nodeMap;
	}

	public void set_nodeMap(org.pcollections.PMap<java.lang.Integer, java.lang.String> _nodeMap) {
		this._nodeMap = _nodeMap;
		attributeChanged("nodeMap");
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		
		if (io.isNextToken("{")) {
			org.pcollections.PMap<java.lang.Integer, java.lang.String> $_nodeMap = de.uni_koblenz.jgralab.JGraLab.map();
			io.match("{");
			while (!io.isNextToken("}")) {
				int _nodeMapKey;
					java.lang.String _nodeMapValue;
				_nodeMapKey = io.matchInteger();
				io.match("-");
				_nodeMapValue = io.matchUtfString();
				$_nodeMap = $_nodeMap.plus(_nodeMapKey, _nodeMapValue);
			}
			io.match("}");
			_nodeMap = $_nodeMap;
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match();
			_nodeMap = null;
		} else {
			_nodeMap = null;
		}
		set_nodeMap(_nodeMap);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("nodeMap")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			
			if (io.isNextToken("{")) {
				org.pcollections.PMap<java.lang.Integer, java.lang.String> $_nodeMap = de.uni_koblenz.jgralab.JGraLab.map();
				io.match("{");
				while (!io.isNextToken("}")) {
					int _nodeMapKey;
						java.lang.String _nodeMapValue;
					_nodeMapKey = io.matchInteger();
					io.match("-");
					_nodeMapValue = io.matchUtfString();
					$_nodeMap = $_nodeMap.plus(_nodeMapKey, _nodeMapValue);
				}
				io.match("}");
				_nodeMap = $_nodeMap;
			} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
				io.match();
				_nodeMap = null;
			} else {
				_nodeMap = null;
			}
			set_nodeMap(_nodeMap);
			return;
		}
		throw new NoSuchAttributeException("Node doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		if (_nodeMap != null) {
			io.writeSpace();
			io.write("{");
			io.noSpace();
			for (int key: _nodeMap.keySet()) {
				java.lang.String value = _nodeMap.get(key);
				io.writeInteger(key);
				io.write(" -");
				io.writeUtfString(value);
			}
			io.write("}");
			io.space();
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("nodeMap")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (_nodeMap != null) {
				io.writeSpace();
				io.write("{");
				io.noSpace();
				for (int key: _nodeMap.keySet()) {
					java.lang.String value = _nodeMap.get(key);
					io.writeInteger(key);
					io.write(" -");
					io.writeUtfString(value);
				}
				io.write("}");
				io.space();
			} else {
				io.writeIdentifier(GraphIO.NULL_LITERAL);
			}
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("Node doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.minimal.Node getNextNode() {
		return (de.uni_koblenz.jgralabtest.schemas.minimal.Node)getNextVertex(de.uni_koblenz.jgralabtest.schemas.minimal.Node.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.minimal.Link getFirstLinkIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.minimal.Link)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.minimal.Link.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.minimal.Link getFirstLinkIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.minimal.Link)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.minimal.Link add_target(de.uni_koblenz.jgralabtest.schemas.minimal.Node vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.minimal.MinimalGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.minimal.Link.EC, (de.uni_koblenz.jgralabtest.schemas.minimal.Node) this, (de.uni_koblenz.jgralabtest.schemas.minimal.Node) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.minimal.Node> remove_target() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.minimal.Node> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.minimal.Node>();
			de.uni_koblenz.jgralabtest.schemas.minimal.Link edge = (de.uni_koblenz.jgralabtest.schemas.minimal.Link) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.minimal.Link next = (de.uni_koblenz.jgralabtest.schemas.minimal.Link) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.minimal.Node) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_target(de.uni_koblenz.jgralabtest.schemas.minimal.Node vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.minimal.Link edge = (de.uni_koblenz.jgralabtest.schemas.minimal.Link) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.minimal.Link next = (de.uni_koblenz.jgralabtest.schemas.minimal.Link) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.minimal.Node> get_target() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.minimal.Link, de.uni_koblenz.jgralabtest.schemas.minimal.Node>(this, de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.minimal.Link add_source(de.uni_koblenz.jgralabtest.schemas.minimal.Node vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.minimal.MinimalGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.minimal.Link.EC, (de.uni_koblenz.jgralabtest.schemas.minimal.Node) vertex, (de.uni_koblenz.jgralabtest.schemas.minimal.Node) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.minimal.Node> remove_source() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.minimal.Node> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.minimal.Node>();
			de.uni_koblenz.jgralabtest.schemas.minimal.Link edge = (de.uni_koblenz.jgralabtest.schemas.minimal.Link) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.minimal.Link next = (de.uni_koblenz.jgralabtest.schemas.minimal.Link) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.minimal.Node) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_source(de.uni_koblenz.jgralabtest.schemas.minimal.Node vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.minimal.Link edge = (de.uni_koblenz.jgralabtest.schemas.minimal.Link) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.minimal.Link next = (de.uni_koblenz.jgralabtest.schemas.minimal.Link) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.minimal.Node> get_source() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.minimal.Link, de.uni_koblenz.jgralabtest.schemas.minimal.Node>(this, de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, EdgeDirection.IN);
		}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.minimal.Link> getLinkIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.minimal.Link>(this, de.uni_koblenz.jgralabtest.schemas.minimal.Link.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.minimal.Link> getLinkIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.minimal.Link>(this, de.uni_koblenz.jgralabtest.schemas.minimal.Link.class, direction);
	}
}
