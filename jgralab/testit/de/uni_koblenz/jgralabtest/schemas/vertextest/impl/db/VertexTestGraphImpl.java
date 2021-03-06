/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.vertextest.impl.db;

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

public class VertexTestGraphImpl extends GraphImpl implements de.uni_koblenz.jgralab.Graph, de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph {

	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use a GraphFactory
	**/
	public VertexTestGraphImpl(java.lang.String id, GraphDatabase graphDatabase) {
		super(id, de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph.GC, graphDatabase);
		initializeAttributesWithDefaultValues();
	}
	
	/**
	 * DON'T USE THE CONSTRUCTOR
	 * For instantiating a Graph, use a GraphFactory
	**/
	public VertexTestGraphImpl(java.lang.String id, int vMax, int eMax, GraphDatabase graphDatabase) {
		super(id, vMax, eMax, de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph.GC, graphDatabase);
		initializeAttributesWithDefaultValues();
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.GraphClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph.GC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Graph> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.vertextest.VertexTestGraph.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("VertexTestGraph doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("VertexTestGraph doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("VertexTestGraph doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("VertexTestGraph doesn't contain an attribute " + attributeName);
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

	// ------------------------ Code for A ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.A getFirstA() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.A)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.A.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.A createA() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.A> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.A.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.A createA(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.A> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.A.VC, id, this);
	}

	// ------------------------ Code for AbstractSuperNode ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode getFirstAbstractSuperNode() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode.VC);
	}

	// ------------------------ Code for B ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.B getFirstB() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.B)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.B.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.B createB() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.B> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.B.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.B createB(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.B> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.B.VC, id, this);
	}

	// ------------------------ Code for C ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.C getFirstC() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.C)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.C.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.C createC() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.C> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.C.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.C createC(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.C> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.C.VC, id, this);
	}

	// ------------------------ Code for C2 ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.C2 getFirstC2() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.C2)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.C2.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.C2 createC2() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.C2> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.C2.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.C2 createC2(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.C2> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.C2.VC, id, this);
	}

	// ------------------------ Code for D ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.D getFirstD() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.D)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.D.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.D createD() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.D> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.D.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.D createD(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.D> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.D.VC, id, this);
	}

	// ------------------------ Code for D2 ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.D2 getFirstD2() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.D2)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.D2.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.D2 createD2() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.D2> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.D2.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.D2 createD2(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.D2> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.D2.VC, id, this);
	}

	// ------------------------ Code for DoubleSubNode ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode getFirstDoubleSubNode() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode createDoubleSubNode() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode createDoubleSubNode(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode.VC, id, this);
	}

	// ------------------------ Code for E ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E getFirstE() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.E)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.E.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E createE(de.uni_koblenz.jgralabtest.schemas.vertextest.A alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.B omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.E> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.E.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.E createE(int id, de.uni_koblenz.jgralabtest.schemas.vertextest.A alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.B omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.E> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.E.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for F ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.F getFirstF() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.F)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.F.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.F createF(de.uni_koblenz.jgralabtest.schemas.vertextest.C alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.D omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.F> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.F.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.F createF(int id, de.uni_koblenz.jgralabtest.schemas.vertextest.C alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.D omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.F> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.F.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for G ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.G getFirstG() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.G)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.G.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.G createG(de.uni_koblenz.jgralabtest.schemas.vertextest.C alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.D omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.G> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.G.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.G createG(int id, de.uni_koblenz.jgralabtest.schemas.vertextest.C alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.D omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.G> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.G.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for H ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.H getFirstH() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.H)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.H.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.H createH(de.uni_koblenz.jgralabtest.schemas.vertextest.A alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.B omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.H> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.H.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.H createH(int id, de.uni_koblenz.jgralabtest.schemas.vertextest.A alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.B omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.H> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.H.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for I ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.I getFirstI() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.I)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.I.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.I createI(de.uni_koblenz.jgralabtest.schemas.vertextest.A alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.A omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.I> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.I.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.I createI(int id, de.uni_koblenz.jgralabtest.schemas.vertextest.A alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.A omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.I> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.I.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for J ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.J getFirstJ() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.J)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.J.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.J createJ(de.uni_koblenz.jgralabtest.schemas.vertextest.C2 alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.D2 omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.J> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.J.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.J createJ(int id, de.uni_koblenz.jgralabtest.schemas.vertextest.C2 alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.D2 omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.J> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.J.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for K ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.K getFirstK() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.K)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.K.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.K createK(de.uni_koblenz.jgralabtest.schemas.vertextest.A alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.B omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.K> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.K.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.K createK(int id, de.uni_koblenz.jgralabtest.schemas.vertextest.A alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.B omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.K> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.K.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for Link ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link getFirstLink() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.Link)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link createLink(de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.Link> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.Link createLink(int id, de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.Link> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.Link.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for LinkBack ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack getFirstLinkBack() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack createLinkBack(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack createLinkBack(int id, de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for SubLink ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink getFirstSubLink() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink)getFirstEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.EC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink createSubLink(de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.EC, 0, this, alpha, omega);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink createSubLink(int id, de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode alpha, de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode omega) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink> createEdge(de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.EC, id, this, alpha, omega);
	}

	// ------------------------ Code for SubNode ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode getFirstSubNode() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode createSubNode() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode createSubNode(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode.VC, id, this);
	}

	// ------------------------ Code for SuperNode ------------------------

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode getFirstSuperNode() {
		return (de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode)getFirstVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode.VC);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode createSuperNode() {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode.VC, 0, this);
	}

	public de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode createSuperNode(int id) {
		return graphFactory.<de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> createVertex(de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode.VC, id, this);
	}

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.F> getFEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.F>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.F.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink> getSubLinkEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.SubLink.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.E> getEEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.E>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.E.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack> getLinkBackEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.LinkBack.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.Link> getLinkEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.Link>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.Link.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.K> getKEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.K>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.K.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.H> getHEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.H>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.H.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.G> getGEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.G>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.G.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.J> getJEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.J>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.J.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.I> getIEdges() {
		return new EdgeIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.I>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.I.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.D> getDVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.D>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.D.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.C> getCVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.C>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.C.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode> getSuperNodeVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.SuperNode.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.C2> getC2Vertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.C2>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.C2.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.B> getBVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.B>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.B.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.A> getAVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.A>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.A.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.D2> getD2Vertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.D2>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.D2.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode> getSubNodeVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.SubNode.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode> getDoubleSubNodeVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.DoubleSubNode.class);
	}
	

	public Iterable<de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode> getAbstractSuperNodeVertices() {
		return new VertexIterable<de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode>(this, de.uni_koblenz.jgralabtest.schemas.vertextest.AbstractSuperNode.class);
	}
	
}
