/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.algolib.simple.impl.db;

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

public class SimpleGraphImpl extends GraphImpl implements de.uni_koblenz.jgralab.Graph, de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleGraph {

	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use a GraphFactory
	**/
	public SimpleGraphImpl(java.lang.String id, GraphDatabase graphDatabase) {
		super(id, de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleGraph.GC, graphDatabase);
		initializeAttributesWithDefaultValues();
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use a GraphFactory
	**/
	public SimpleGraphImpl(java.lang.String id, int vMax, int eMax, GraphDatabase graphDatabase) {
		super(id, vMax, eMax, de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleGraph.GC, graphDatabase);
		initializeAttributesWithDefaultValues();
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.GraphClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleGraph.GC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Graph> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleGraph.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("SimpleGraph doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("SimpleGraph doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("SimpleGraph doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("SimpleGraph doesn't contain an attribute " + attributeName);
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

	// ------------------------ Code for SimpleEdge ------------------------

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge getFirstSimpleEdge() {
		return (de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge createSimpleEdge(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex alpha, de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge> createEdge(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge createSimpleEdge(int id, de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex alpha, de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge> createEdge(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for SimpleVertex ------------------------

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex getFirstSimpleVertex() {
		return (de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex createSimpleVertex() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex> createVertex(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex createSimpleVertex(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex> createVertex(de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex.VC, id, this);
	}

	public Iterable<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge> getSimpleEdgeEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge>(this, de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleEdge.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex> getSimpleVertexVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex>(this, de.uni_koblenz.jgralabtest.schemas.algolib.simple.SimpleVertex.class);
	}
	
}
