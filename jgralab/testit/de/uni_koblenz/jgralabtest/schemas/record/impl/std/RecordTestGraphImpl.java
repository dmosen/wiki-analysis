/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.record.impl.std;

import de.uni_koblenz.jgralab.impl.EdgeIterable;
import de.uni_koblenz.jgralab.impl.VertexIterable;

import de.uni_koblenz.jgralab.impl.std.GraphImpl;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;

import java.io.IOException;

import org.pcollections.POrderedSet;

public class RecordTestGraphImpl extends GraphImpl implements de.uni_koblenz.jgralab.Graph, de.uni_koblenz.jgralabtest.schemas.record.RecordTestGraph {

	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public RecordTestGraphImpl() {
		this(null);
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public RecordTestGraphImpl(int vMax, int eMax) {
		this(null, vMax, eMax);
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public RecordTestGraphImpl(java.lang.String id, int vMax, int eMax) {
		super(id, de.uni_koblenz.jgralabtest.schemas.record.RecordTestGraph.GC, vMax, eMax);
		initializeAttributesWithDefaultValues();
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use the Schema and a GraphFactory
	**/
	public RecordTestGraphImpl(java.lang.String id) {
		super(id, de.uni_koblenz.jgralabtest.schemas.record.RecordTestGraph.GC);
		initializeAttributesWithDefaultValues();
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.GraphClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.record.RecordTestGraph.GC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Graph> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.record.RecordTestGraph.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("RecordTestGraph doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("RecordTestGraph doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("RecordTestGraph doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("RecordTestGraph doesn't contain an attribute " + attributeName);
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

	public de.uni_koblenz.jgralabtest.schemas.record.Link getFirstLink() {
		return (de.uni_koblenz.jgralabtest.schemas.record.Link)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.record.Link.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.record.Link createLink(de.uni_koblenz.jgralabtest.schemas.record.Node alpha, de.uni_koblenz.jgralabtest.schemas.record.Node omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.record.Link> createEdge(de.uni_koblenz.jgralabtest.schemas.record.Link.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.record.Link createLink(int id, de.uni_koblenz.jgralabtest.schemas.record.Node alpha, de.uni_koblenz.jgralabtest.schemas.record.Node omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.record.Link> createEdge(de.uni_koblenz.jgralabtest.schemas.record.Link.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for Node ------------------------

	public de.uni_koblenz.jgralabtest.schemas.record.Node getFirstNode() {
		return (de.uni_koblenz.jgralabtest.schemas.record.Node)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.record.Node.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.record.Node createNode() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.record.Node> createVertex(de.uni_koblenz.jgralabtest.schemas.record.Node.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.record.Node createNode(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.record.Node> createVertex(de.uni_koblenz.jgralabtest.schemas.record.Node.VC, id, this);
	}

	public Iterable<de.uni_koblenz.jgralabtest.schemas.record.Link> getLinkEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.record.Link>(this, de.uni_koblenz.jgralabtest.schemas.record.Link.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.record.Node> getNodeVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.record.Node>(this, de.uni_koblenz.jgralabtest.schemas.record.Node.class);
	}
	
}
