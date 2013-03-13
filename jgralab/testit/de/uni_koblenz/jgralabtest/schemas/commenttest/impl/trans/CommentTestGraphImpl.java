/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.commenttest.impl.trans;

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

public class CommentTestGraphImpl extends GraphImpl implements de.uni_koblenz.jgralabtest.schemas.commenttest.CommentTestGraph, de.uni_koblenz.jgralab.Graph {

	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public CommentTestGraphImpl() {
		this(null);
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public CommentTestGraphImpl(int vMax, int eMax) {
		this(null, vMax, eMax);
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public CommentTestGraphImpl(java.lang.String id, int vMax, int eMax) {
		super(id, de.uni_koblenz.jgralabtest.schemas.commenttest.CommentTestGraph.GC, vMax, eMax);
		initializeAttributesWithDefaultValues();
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public CommentTestGraphImpl(java.lang.String id) {
		super(id, de.uni_koblenz.jgralabtest.schemas.commenttest.CommentTestGraph.GC);
		initializeAttributesWithDefaultValues();
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.GraphClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.commenttest.CommentTestGraph.GC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Graph> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.commenttest.CommentTestGraph.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("CommentTestGraph doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("CommentTestGraph doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("CommentTestGraph doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("CommentTestGraph doesn't contain an attribute " + attributeName);
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

	// ------------------------ Code for pa.E ------------------------

	public de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E getFirstE() {
		return (de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E createE(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V alpha, de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E> createEdge(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E createE(int id, de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V alpha, de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E> createEdge(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for pa.V ------------------------

	public de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V getFirstV() {
		return (de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V createV() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V> createVertex(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V createV(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V> createVertex(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V.VC, id, this);
	}

	// ------------------------ Code for pa.pc.W ------------------------

	public de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W getFirstW() {
		return (de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W createW() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W> createVertex(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W createW(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W> createVertex(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W.VC, id, this);
	}

	public Iterable<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E> getEEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E>(this, de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W> getWVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W>(this, de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V> getVVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V>(this, de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V.class);
	}
	
}
