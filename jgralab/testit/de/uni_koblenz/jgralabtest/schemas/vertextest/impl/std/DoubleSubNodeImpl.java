/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest.impl.std;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.std.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class DoubleSubNodeImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode, de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode, de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode {

	protected java.lang.String _name;

	protected org.pcollections.PMap<java.lang.Integer, java.lang.String> _nodeMap;

	protected int _number;

	public DoubleSubNodeImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("name")) return (T) get_name();
		if (attributeName.equals("nodeMap")) return (T) get_nodeMap();
		if (attributeName.equals("number")) return (T) (java.lang.Integer) get_number();
		throw new NoSuchAttributeException("DoubleSubNode doesn't contain an attribute " + attributeName);
	}

	@SuppressWarnings("unchecked")
	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		if (attributeName.equals("nodeMap")) {
			set_nodeMap((org.pcollections.PMap<java.lang.Integer, java.lang.String>) data);
			return;
		}
		if (attributeName.equals("number")) {
			set_number((java.lang.Integer) data);
			return;
		}
		throw new NoSuchAttributeException("DoubleSubNode doesn't contain an attribute " + attributeName);
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

	public org.pcollections.PMap<java.lang.Integer, java.lang.String> get_nodeMap() {
		return _nodeMap;
	}

	public void set_nodeMap(org.pcollections.PMap<java.lang.Integer, java.lang.String> _nodeMap) {
		ecaAttributeChanging("nodeMap", this._nodeMap, _nodeMap);
		Object oldValue = this._nodeMap;
		this._nodeMap = _nodeMap;
		graphModified();
	ecaAttributeChanged("nodeMap", oldValue, _nodeMap);
	}

	public int get_number() {
		return _number;
	}

	public void set_number(int _number) {
		ecaAttributeChanging("number", this._number, _number);
		Object oldValue = this._number;
		this._number = _number;
		graphModified();
	ecaAttributeChanged("number", oldValue, _number);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_name = io.matchUtfString();
		set_name(_name);
		
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
		_number = io.matchInteger();
		set_number(_number);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
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
		if (attributeName.equals("number")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_number = io.matchInteger();
			set_number(_number);
			return;
		}
		throw new NoSuchAttributeException("DoubleSubNode doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_name);
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
		io.writeInteger(_number);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
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
		if (attributeName.equals("number")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeInteger(_number);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("DoubleSubNode doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode getNextAbstractSuperNode() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode)getNextVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode getNextDoubleSubNode() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode)getNextVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode getNextSubNode() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode)getNextVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode getNextSuperNode() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode)getNextVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink getFirstSubLinkIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink getFirstSubLinkIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack getFirstLinkBackIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack getFirstLinkBackIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getFirstLinkIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.Link)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getFirstLinkIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.Link)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink add_sourcec(de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode) vertex, (de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode> remove_sourcec() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink next = (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_sourcec(de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink next = (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode> get_sourcec() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink, de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, EdgeDirection.IN);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink add_targetc(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode) this, (de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> remove_targetc() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink next = (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_targetc(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink next = (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> get_targetc() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink, de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.Link add_target(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode) this, (de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> remove_target() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.Link edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.Link) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.Link next = (de.uni_koblenz.jgralabtest.schemas.vertextest.Link) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_target(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.Link edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.Link) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.Link next = (de.uni_koblenz.jgralabtest.schemas.vertextest.Link) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> get_target() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.Link, de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack add_targetb(de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode) this, (de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode) vertex);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode> remove_targetb() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack next = (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, EdgeDirection.OUT);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_targetb(de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, EdgeDirection.OUT);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack next = (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, EdgeDirection.OUT);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode> get_targetb() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack, de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, EdgeDirection.OUT);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.Link add_source(de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode) vertex, (de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode> remove_source() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.Link edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.Link) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.Link next = (de.uni_koblenz.jgralabtest.schemas.vertextest.Link) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_source(de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.Link edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.Link) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.Link next = (de.uni_koblenz.jgralabtest.schemas.vertextest.Link) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode> get_source() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.Link, de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, EdgeDirection.IN);
		}
		@Override
		public de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack add_sourceb(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode vertex) {
			return ((de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph)getGraph()).createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.EC, (de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode) vertex, (de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> remove_sourceb() {
			java.util.List<de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode>();
			de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack next = (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_sourceb(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack edge = (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack) getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack next = (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack) edge.getNextIncidence(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> get_sourceb() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack, de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, EdgeDirection.IN);
		}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink> getSubLinkIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink> getSubLinkIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack> getLinkBackIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack> getLinkBackIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.Link> getLinkIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.Link>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.Link> getLinkIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.Link>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class, direction);
	}
}
