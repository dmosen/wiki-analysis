/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.jniclient.impl.trans;

import de.uni_koblenz.jgralab.impl.EdgeIterable;
import de.uni_koblenz.jgralab.impl.VertexIterable;

import de.uni_koblenz.jgralab.impl.trans.GraphImpl;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;

import java.io.IOException;

import org.pcollections.POrderedSet;

public class JniTestGraphImpl extends GraphImpl implements de.uni_koblenz.jgralab.Graph, de.uni_koblenz.jgralabtest.schemas.jniclient.JniTestGraph {

	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public JniTestGraphImpl() {
		this(null);
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public JniTestGraphImpl(int vMax, int eMax) {
		this(null, vMax, eMax);
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public JniTestGraphImpl(java.lang.String id, int vMax, int eMax) {
		super(id, de.uni_koblenz.jgralabtest.schemas.jniclient.JniTestGraph.GC, vMax, eMax);
		initializeAttributesWithDefaultValues();
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public JniTestGraphImpl(java.lang.String id) {
		super(id, de.uni_koblenz.jgralabtest.schemas.jniclient.JniTestGraph.GC);
		initializeAttributesWithDefaultValues();
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.GraphClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.jniclient.JniTestGraph.GC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Graph> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.jniclient.JniTestGraph.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("JniTestGraph doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("JniTestGraph doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("JniTestGraph doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("JniTestGraph doesn't contain an attribute " + attributeName);
	}
	public java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes() {
		java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes = new java.util.HashSet<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>>();
		return attributes;
	}
	
	protected GreqlEvaluator greqlEvaluator;

	@Override
	public synchronized <T extends Vertex> POrderedSet<T> reachableVertices(Vertex startVertex, String pathDescription, Class<T> vertexType) {
		if (greqlEvaluator == null) {
			greqlEvaluator = new GreqlEvaluator((String) null, this, null);
		}
		greqlEvaluator.setVariable("v", startVertex);
		greqlEvaluator.setQuery("using v: v " + pathDescription);
		greqlEvaluator.startEvaluation();
		return greqlEvaluator.getResultSet();
	}

	// ------------------------ Code for Link ------------------------

	public de.uni_koblenz.jgralabtest.schemas.jniclient.Link getFirstLink() {
		return (de.uni_koblenz.jgralabtest.schemas.jniclient.Link)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.jniclient.Link.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.jniclient.Link createLink(de.uni_koblenz.jgralabtest.schemas.jniclient.Node alpha, de.uni_koblenz.jgralabtest.schemas.jniclient.Node omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.jniclient.Link> createEdge(de.uni_koblenz.jgralabtest.schemas.jniclient.Link.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.jniclient.Link createLink(int id, de.uni_koblenz.jgralabtest.schemas.jniclient.Node alpha, de.uni_koblenz.jgralabtest.schemas.jniclient.Node omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.jniclient.Link> createEdge(de.uni_koblenz.jgralabtest.schemas.jniclient.Link.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for Node ------------------------

	public de.uni_koblenz.jgralabtest.schemas.jniclient.Node getFirstNode() {
		return (de.uni_koblenz.jgralabtest.schemas.jniclient.Node)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.jniclient.Node.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.jniclient.Node createNode() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.jniclient.Node> createVertex(de.uni_koblenz.jgralabtest.schemas.jniclient.Node.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.jniclient.Node createNode(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.jniclient.Node> createVertex(de.uni_koblenz.jgralabtest.schemas.jniclient.Node.VC, id, this);
	}

	// ------------------------ Code for SimpleEdge ------------------------

	public de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge getFirstSimpleEdge() {
		return (de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge createSimpleEdge(de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode alpha, de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge> createEdge(de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge createSimpleEdge(int id, de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode alpha, de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge> createEdge(de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for SimpleNode ------------------------

	public de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode getFirstSimpleNode() {
		return (de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode createSimpleNode() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode> createVertex(de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode createSimpleNode(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode> createVertex(de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode.VC, id, this);
	}

	public Iterable<de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge> getSimpleEdgeEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge>(this, de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleEdge.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.jniclient.Link> getLinkEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.jniclient.Link>(this, de.uni_koblenz.jgralabtest.schemas.jniclient.Link.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode> getSimpleNodeVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode>(this, de.uni_koblenz.jgralabtest.schemas.jniclient.SimpleNode.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.jniclient.Node> getNodeVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.jniclient.Node>(this, de.uni_koblenz.jgralabtest.schemas.jniclient.Node.class);
	}
	
}
