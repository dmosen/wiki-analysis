/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.constrained.impl.db;

import de.uni_koblenz.jgralab.impl.db.GraphImpl;
import de.uni_koblenz.jgralab.impl.db.GraphDatabase;

import de.uni_koblenz.jgralab.impl.EdgeIterable;
import de.uni_koblenz.jgralab.impl.VertexIterable;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;

import java.io.IOException;

import org.pcollections.POrderedSet;

public class ConstrainedGraphImpl extends GraphImpl implements de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedGraph, de.uni_koblenz.jgralab.Graph {

	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use a GraphFactory
	**/
	public ConstrainedGraphImpl(java.lang.String id, GraphDatabase graphDatabase) {
		super(id, de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedGraph.GC, graphDatabase);
		initializeAttributesWithDefaultValues();
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use a GraphFactory
	**/
	public ConstrainedGraphImpl(java.lang.String id, int vMax, int eMax, GraphDatabase graphDatabase) {
		super(id, vMax, eMax, de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedGraph.GC, graphDatabase);
		initializeAttributesWithDefaultValues();
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.GraphClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedGraph.GC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Graph> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedGraph.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("ConstrainedGraph doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("ConstrainedGraph doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("ConstrainedGraph doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("ConstrainedGraph doesn't contain an attribute " + attributeName);
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

	// ------------------------ Code for ConstrainedLink ------------------------

	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink getFirstConstrainedLink() {
		return (de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink createConstrainedLink(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode alpha, de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink> createEdge(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink createConstrainedLink(int id, de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode alpha, de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink> createEdge(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for ConstrainedNode ------------------------

	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode getFirstConstrainedNode() {
		return (de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode createConstrainedNode() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode> createVertex(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode createConstrainedNode(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode> createVertex(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode.VC, id, this);
	}

	// ------------------------ Code for OtherConstrainedNode ------------------------

	public de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode getFirstOtherConstrainedNode() {
		return (de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode createOtherConstrainedNode() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode> createVertex(de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode createOtherConstrainedNode(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode> createVertex(de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode.VC, id, this);
	}

	public Iterable<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink> getConstrainedLinkEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink>(this, de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode> getOtherConstrainedNodeVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode>(this, de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode> getConstrainedNodeVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode>(this, de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode.class);
	}
	
}
