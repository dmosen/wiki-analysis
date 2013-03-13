/*
 * JGraLab - The Java Graph Laboratory
 *
 * Copyright (C) 2006-2011 Institute for Software Technology
 *                         University of Koblenz-Landau, Germany
 *                         ist@uni-koblenz.de
 *
 * For bug reports, documentation and further information, visit
 *
 *                         http://jgralab.uni-koblenz.de
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or combining
 * it with Eclipse (or a modified version of that program or an Eclipse
 * plugin), containing parts covered by the terms of the Eclipse Public
 * License (EPL), the licensors of this Program grant you additional
 * permission to convey the resulting work.  Corresponding Source for a
 * non-source form of such a combination shall include the source code for
 * the parts of JGraLab used as well as that of the covered work.
 */
package de.uni_koblenz.jgralab.impl.db;

import java.util.List;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.GraphException;
import de.uni_koblenz.jgralab.TraversalContext;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.impl.FreeIndexList;
import de.uni_koblenz.jgralab.impl.GraphBaseImpl;
import de.uni_koblenz.jgralab.impl.IncidenceImpl;
import de.uni_koblenz.jgralab.impl.InternalEdge;
import de.uni_koblenz.jgralab.impl.InternalVertex;
import de.uni_koblenz.jgralab.impl.ReversedEdgeBaseImpl;
import de.uni_koblenz.jgralab.schema.EdgeClass;
import de.uni_koblenz.jgralab.schema.GraphClass;
import de.uni_koblenz.jgralab.schema.VertexClass;
import de.uni_koblenz.jgralab.trans.CommitFailedException;
import de.uni_koblenz.jgralab.trans.InvalidSavepointException;
import de.uni_koblenz.jgralab.trans.Savepoint;
import de.uni_koblenz.jgralab.trans.Transaction;

/**
 * Graph which can be persisted into a graph database.
 * 
 * @author ultbreit@uni-koblenz.de
 */
public abstract class GraphImpl extends GraphBaseImpl implements
		DatabasePersistableGraph {

	/**
	 * Flag indicating persistence state of graph.
	 */
	private boolean persistent = false;

	/**
	 * Primary key of graph in database.
	 */
	private int gId = -1;

	/**
	 * Global vertex sequence.
	 */
	private VertexList vSeq;

	/**
	 * Global edge sequence.
	 */
	private EdgeList eSeq;

	/**
	 * Cache holding primary keys of elements in database to speed up access to
	 * them.
	 */
	private GraphCache graphCache;

	/**
	 * Database containing this graph.
	 */
	private GraphDatabase containingDatabase;

	private TraversalContext tc;

	/**
	 * Creates a new <code>GraphImpl</code> persistent in database.
	 * 
	 * @param id
	 *            Identifier of graph.
	 * @param graphClass
	 *            Type of graph.
	 * @param graphDatabase
	 *            Database in which graph is persistent.
	 */
	protected GraphImpl(String id, GraphClass graphClass,
			GraphDatabase graphDatabase) {
		this(id, 1000, 1000, graphClass, graphDatabase);
	}

	/**
	 * Creates a new <code>GraphImpl</code> persistent in database.
	 * 
	 * @param id
	 *            Identifier of graph.
	 * @param graphClass
	 *            Type of graph.
	 * @param graphDatabase
	 *            Database in which graph is persistent.
	 */
	protected GraphImpl(String id, int vMax, int eMax, GraphClass graphClass,
			GraphDatabase graphDatabase) {
		this(id, graphClass, vMax, eMax);
		if (graphDatabase != null) {
			containingDatabase = graphDatabase;
		} else {
			throw new GraphException(
					"Cannot create a graph with database support with no database given.");
		}
	}

	/**
	 * Creates a new <code>GraphImpl</code> not persistent in database.
	 * 
	 * @param id
	 *            Identifier of graph.
	 * @param graphClass
	 *            Type of graph.
	 * @param vMax
	 *            Maximum count of vertices in graph.
	 * @param eMax
	 *            Maximum count of edges in graph.
	 */
	private GraphImpl(String id, GraphClass graphClass, int vMax, int eMax) {
		super(id, graphClass, vMax, eMax);
		vSeq = new VertexList(this);
		eSeq = new EdgeList(this);
		graphCache = new SoftCache();
	}

	@Override
	public boolean isPersistent() {
		return persistent;
	}

	@Override
	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	@Override
	public boolean isInitialized() {
		return !isLoading();
	}

	@Override
	public void setInitialized(boolean initialized) {
		super.setLoading(!initialized);
	}

	@Override
	public int getGId() {
		return gId;
	}

	@Override
	public void setGId(int gId) {
		this.gId = gId;
	}

	@Override
	public void setGraphVersion(long graphVersion) {
		if (super.getGraphVersion() != graphVersion) {
			updateGraphVersionInMemory(graphVersion);
		}
	}

	private void updateGraphVersionInMemory(long graphVersion) {
		super.setGraphVersion(graphVersion);
	}

	/**
	 * Notifies graph that it has been modified.
	 */
	protected void internalGraphModified() {
		updateGraphVersionInMemory(super.getGraphVersion() + 1);
	}

	@Override
	public void addVertex(int vId, long sequenceNumber) {
		vSeq.add(sequenceNumber, vId);
	}

	@Override
	public void addVertex(Vertex newVertex) {
		if (newVertex.getId() == 0) {
			addFreshlyCreatedVertex(newVertex);
		} else {
			testValidityOfLoadedVertex(newVertex);
		}
	}

	private void testValidityOfLoadedVertex(Vertex vertex) {
		int vId = vertex.getId();
		if (vId <= 0) {
			throw new GraphException("Cannot load a vertex with id <= 0.");
			// else if (vId > vMax)
			// throw new GraphException("Vertex id " + vId +
			// " is bigger than vSize.");
		}
	}

	private void addFreshlyCreatedVertex(Vertex vertex) {
		this.allocateValidIdTo(vertex);
		vSeq.append((DatabasePersistableVertex) vertex);
		vSeq.modified();
		insertVertexIntoDatabase((DatabasePersistableVertex) vertex);
		graphCache.addVertex((DatabasePersistableVertex) vertex);
		internalGraphModified();

		internalVertexAdded((InternalVertex) vertex);
	}

	private void allocateValidIdTo(Vertex vertex) {
		int vId = allocateVertexIndex(vertex.getId());
		assert vId != 0;
		((VertexImpl) vertex).setId(vId);
	}

	@Override
	public void appendVertexToVSeq(InternalVertex v) {
		vSeq.append((DatabasePersistableVertex) v);
		vertexListModified();
	}

	@Override
	public void addEdge(int eId, long sequenceNumber) {
		eSeq.add(sequenceNumber, eId);
	}

	/**
	 * Only use it to add an edge internally to graph. It means that edge has
	 * just been created by this graph with createEdge or is loaded from
	 * storage. Not meant to add an edge from another graph.
	 */
	@Override
	public void addEdge(Edge newEdge, Vertex alpha, Vertex omega) {
		assertPreConditionOfAddEdge(newEdge, alpha, omega);
		testEdgeSuitingVertices((InternalEdge) newEdge, (InternalVertex) alpha,
				(InternalVertex) omega);
		proceedWithAdditionOf(newEdge, alpha, omega);
	}

	private void assertPreConditionOfAddEdge(Edge newEdge, Vertex alpha,
			Vertex omega) {
		assert newEdge != null;
		assert isVertexValid(alpha) : "Alpha vertex is invalid";
		assert isVertexValid(omega) : "Omega vertex is invalid";
		assert newEdge.isNormal() : "Cannot add reversed edge";
		assert isSameSchemaAsGraph(newEdge, alpha, omega) : "Schemas of alpha, omega, edge and the graph do not match!";
		assert isGraphMatching(newEdge, alpha, omega) : "Graphs of alpha, omega, edge and this graph do not match!";
	}

	private boolean isVertexValid(Vertex vertex) {
		return (vertex != null) && vertex.isValid()
				&& vSeqContainsVertex(vertex);
	}

	private boolean isSameSchemaAsGraph(Edge edge, Vertex alpha, Vertex omega) {
		return (alpha.getSchema() == omega.getSchema())
				&& (alpha.getSchema() == getSchema())
				&& (edge.getSchema() == getSchema());
	}

	private boolean isGraphMatching(Edge edge, Vertex alpha, Vertex omega) {
		return (alpha.getGraph() == omega.getGraph())
				&& (alpha.getGraph() == this) && (edge.getGraph() == this);
	}

	private void testEdgeSuitingVertices(InternalEdge edge,
			InternalVertex alpha, InternalVertex omega) {
		if (!(alpha.getAttributedElementClass()).isValidFromFor(edge
				.getAttributedElementClass())) {
			throw new GraphException("Edges of class "
					+ edge.getAttributedElementClass().getQualifiedName()
					+ " may not start at vertices of class "
					+ alpha.getAttributedElementClass().getQualifiedName());
		}
		if (!(omega.getAttributedElementClass()).isValidToFor(edge
				.getAttributedElementClass())) {
			throw new GraphException("Edges of class "
					+ edge.getAttributedElementClass().getQualifiedName()
					+ " may not end at vertices of class "
					+ omega.getAttributedElementClass().getQualifiedName());
		}
	}

	private void proceedWithAdditionOf(Edge edge, Vertex alpha, Vertex omega) {
		assert edge.getId() >= 0;
		if (edge.getId() == 0) {
			addFreshlyCreatedEdge(edge, alpha, omega);
		} else {
			testValidityOfLoadedEdge(edge);
		}
	}

	private void testValidityOfLoadedEdge(Edge edge) {
		int eId = edge.getId();
		if (eId <= 0) {
			throw new GraphException("Cannot load an edge with id <= 0");
		} else if (eId > eMax) {
			throw new GraphException("Edge's id " + eId
					+ " is bigger than maximum capacity of edges allowed.");
		}
	}

	private void addFreshlyCreatedEdge(Edge edge, Vertex alpha, Vertex omega) {

		EdgeImpl edgeImpl = (EdgeImpl) edge;
		this.allocateValidIdTo(edgeImpl);

		VertexImpl alphaImpl = (VertexImpl) alpha;
		VertexImpl omegaImpl = (VertexImpl) omega;

		addEdgeToIncidenceLists(edgeImpl, alphaImpl, omegaImpl);

		insertEdgeIntoDatabase(edgeImpl, alphaImpl, omegaImpl);

		appendEdgeToESeq(edgeImpl);

		graphCache.addEdge(edgeImpl);

		internalGraphModified();
		internalEdgeAdded(edgeImpl);
	}

	private void allocateValidIdTo(EdgeImpl edge) {
		int eId = allocateEdgeIndex(edge.getId());
		assert eId != 0;
		edge.setId(eId);
	}

	private void addEdgeToIncidenceLists(EdgeImpl edge, VertexImpl alpha,
			VertexImpl omega) {
		alpha.appendIncidenceToISeq(edge);
		alpha.incidenceListModifiedAtClient();
		omega.appendIncidenceToISeq((IncidenceImpl) edge.getReversedEdge());
		omega.incidenceListModifiedAtClient();
	}

	@Override
	public void appendEdgeToESeq(InternalEdge edge) {
		eSeq.append((DatabasePersistableEdge) edge);
		eSeq.modified();
	}

	private void insertVertexIntoDatabase(DatabasePersistableVertex vertex) {
		try {
			containingDatabase.insert(vertex);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("Cannot persist vertex.", exception);
		}
	}

	@Override
	public <T extends Edge> T createEdge(EdgeClass ec, Vertex alpha,
			Vertex omega) {
		T edge = graphFactory.createEdge(ec, 0, this, alpha, omega);
		edge.initializeAttributesWithDefaultValues();
		graphCache.addEdge((DatabasePersistableEdge) edge);
		return edge;
	}

	@Override
	public <T extends Vertex> T createVertex(VertexClass vc) {
		T vertex = graphFactory.createVertex(vc, 0, this);
		vertex.initializeAttributesWithDefaultValues();
		graphCache.addVertex((DatabasePersistableVertex) vertex);
		return vertex;
	}

	private void insertEdgeIntoDatabase(DatabasePersistableEdge edge,
			DatabasePersistableVertex alpha, DatabasePersistableVertex omega) {
		try {
			containingDatabase.insert(edge, alpha, omega);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("Cannot persist edge.", exception);
		}
	}

	/**
	 * Notifies graph that one of his attributes has changed. Called from
	 * generated classes implementation when an attribute is changed.
	 * 
	 * @param attributeName
	 *            Name of attribute that has been changed.
	 */
	protected void attributeChanged(String attributeName) {
		if (isPersistent() && isInitialized()) {
			writeBackGraphAttribute(attributeName);
			internalGraphModified();
		}
	}

	private void writeBackGraphAttribute(String attributeName) {
		try {
			containingDatabase.updateAttributeValueOf(this, attributeName);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException(
					"Cannot write back graph attribute value.", exception);
		}
	}

	@Override
	public long getVertexListVersion() {
		return vSeq.getVersion();
	}

	@Override
	public long getEdgeListVersion() {
		return eSeq.getVersion();
	}

	@Override
	public void setVertexListVersion(long vertexListVersion) {
		vSeq.setVersion(vertexListVersion);
	}

	@Override
	public void setEdgeListVersion(long edgeListVersion) {
		eSeq.setVersion(edgeListVersion);
	}

	@Override
	public int getVCountInVSeq() {
		return vSeq.size();
	}

	@Override
	public int getECountInESeq() {
		return eSeq.size();
	}

	@Override
	public void setVCount(int count) {
		if (count > super.vMax) {
			expandVertexArray(count);
		}
	}

	@Override
	public void setECount(int count) {
		if (count > super.eMax) {
			expandEdgeArray(count);
		}
	}

	@Override
	public InternalVertex getFirstVertexInVSeq() {
		return vSeq.getFirst();
	}

	@Override
	public InternalEdge getFirstEdgeInESeq() {
		return eSeq.getFirst();
	}

	@Override
	public InternalVertex getLastVertexInVSeq() {
		return vSeq.getLast();
	}

	@Override
	public InternalEdge getLastEdgeInESeq() {
		return eSeq.getLast();
	}

	private DatabasePersistableVertex getVertexFromDatabase(int vId) {
		try {
			setLoading(true);
			DatabasePersistableVertex vertex = containingDatabase.getVertex(
					vId, this);
			setLoading(false);
			return vertex;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new GraphException("Cannot get vertex.", exception);
		}
	}

	@Override
	public void setFirstVertex(InternalVertex vertex) {
		if (vertex != null) { // because of super constructor
			DatabasePersistableVertex persistentVertex = (DatabasePersistableVertex) vertex;
			vSeq.prepend(persistentVertex);
		}
	}

	@Override
	public void setFirstEdgeInGraph(InternalEdge edge) {
		// assert edge != null;
		if (edge != null) { // because of super constructor
			DatabasePersistableEdge persistentEdge = (DatabasePersistableEdge) edge;
			eSeq.prepend(persistentEdge);
		}
	}

	@Override
	public void setLastVertex(InternalVertex vertex) {
		if (vertex != null) { // because of super constructor
			DatabasePersistableVertex persistentVertex = (DatabasePersistableVertex) vertex;
			vSeq.append(persistentVertex);
		}
	}

	@Override
	public void setLastEdgeInGraph(InternalEdge edge) {
		if (edge != null) { // because of super constructor
			DatabasePersistableEdge persistentEdge = (DatabasePersistableEdge) edge;
			eSeq.append(persistentEdge);
		}
	}

	@Override
	public void setId(String id) {
		if (super.getId() != id) {
			updateId(id);
		}
	}

	private void updateId(String id) {
		super.setId(id);
		if (isPersistent() && isInitialized()) {
			writeBackGraphId();
		}
	}

	private void writeBackGraphId() {
		try {
			containingDatabase.updateIdOf(this);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("Cannot write back id of graph.",
					exception);
		}
	}

	@Override
	public Vertex getVertex(int vId) {
		if (isValidVertexId(vId)) {
			return internalGetVertexFromCacheOrDatabase(vId);
		} else {
			throw new GraphException("Id of vertex must be > 0.");
		}
	}

	private boolean isValidVertexId(int vId) {
		return vId > 0;
	}

	private Vertex internalGetVertexFromCacheOrDatabase(int vId) {
		if (vSeq.containsVertex(vId)) {
			return getVertexFromCacheOrDatabase(vId);
		} else {
			return null;
		}
	}

	private DatabasePersistableVertex getVertexFromCacheOrDatabase(int vId) {
		if (graphCache.containsVertex(this, vId)) {
			return graphCache.getVertex(this, vId);
		} else {
			return getAndCacheVertexFromDatabase(vId);
		}
	}

	private DatabasePersistableVertex getAndCacheVertexFromDatabase(int vId) {
		DatabasePersistableVertex vertex = getVertexFromDatabase(vId);
		graphCache.addVertex(vertex);
		return vertex;
	}

	@Override
	public Edge getEdge(int eId) {
		if (isValidEdgeId(eId)) {
			return internalGetOrientedEdge(eId);
		} else {
			throw new GraphException("Edge id must be != 0.");
		}
	}

	private boolean isValidEdgeId(int eId) {
		return eId != 0;
	}

	private Edge internalGetOrientedEdge(int eId) {
		if (eSeq.containsEdge(Math.abs(eId))) {
			return getOrientedEdge(eId);
		} else {
			return null;
		}
	}

	private Edge getOrientedEdge(int eId) {
		assert eId != 0;
		if (eId > 0) {
			return getEdgeFromCacheOrDatabase(eId);
		} else {
			return getEdgeFromCacheOrDatabase(-eId).getReversedEdge();
		}
	}

	private DatabasePersistableEdge getEdgeFromCacheOrDatabase(int eId) {
		if (graphCache.containsEdge(this, eId)) {
			return graphCache.getEdge(this, eId);
		} else {
			return getAndCacheEdgeFromDatabase(eId);
		}
	}

	private DatabasePersistableEdge getAndCacheEdgeFromDatabase(int eId) {
		DatabasePersistableEdge edge = getEdgeFromDatabase(eId);
		graphCache.addEdge(edge);
		return edge;
	}

	private DatabasePersistableEdge getEdgeFromDatabase(int eId) {
		try {
			setLoading(true);
			DatabasePersistableEdge edge = containingDatabase
					.getEdge(eId, this);
			setLoading(false);
			return edge;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new GraphException("Cannot get edge.", exception);
		}
	}

	@Override
	public void removeVertexFromVSeq(InternalVertex v) {
		assert v != null;
		vSeq.remove((DatabasePersistableVertex) v);
		freeVertexIndex(v.getId());
	}

	@Override
	public void deleteVertex(Vertex v) {
		super.deleteVertex(v);
		vertexAfterDeleted(v);
	}

	/**
	 * Deletes vertex from database, removes it from cache and sets it to
	 * deleted.
	 * 
	 * @param vertexToBeDeleted
	 *            The vertex to delete from database.
	 * 
	 *            Precondition: Vertex is not part of VSeq and persistent in
	 *            database.
	 * 
	 *            Postcondition: Vertex is not in cache, no longer persistent in
	 *            database and set to deleted.
	 */
	@Override
	public void vertexAfterDeleted(Vertex vertexToBeDeleted) {
		assert vertexToBeDeleted != null;
		DatabasePersistableVertex vertex = (DatabasePersistableVertex) vertexToBeDeleted;
		graphCache.removeVertex(this, vertex.getId());
		if (vertex.isPersistent()) {
			deleteVertexAndIncidentEdgesFromDatabase(vertex);
		}
		vertex.deleted();
	}

	private void deleteVertexAndIncidentEdgesFromDatabase(
			DatabasePersistableVertex vertex) {
		try {
			containingDatabase.delete(vertex);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("Cannot delete vertex.", exception);
		}
	}

	@Override
	public void removeEdgeFromESeq(InternalEdge e) {
		assert e != null;
		eSeq.remove((DatabasePersistableEdge) e);
		freeEdgeIndex(e.getId());
	}

	@Override
	public void deleteEdge(Edge e) {
		super.deleteEdge(e);
	}

	@Override
	public void edgeAfterDeleted(Edge e, Vertex oldAlpha, Vertex oldOmega) {
		assert e != null;
		DatabasePersistableEdge edge = (DatabasePersistableEdge) e;
		if (edge.isPersistent()) {
			deleteEdgeFromDatabase(edge);
		}
		graphCache.removeEdge(this, edge.getId());
		edge.deleted();
	}

	private void deleteEdgeFromDatabase(DatabasePersistableEdge edge) {
		try {
			containingDatabase.delete(edge);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new GraphException("Cannot delete edge " + edge.getId()
					+ " in graph " + getId(), exception);
		}
	}

	@Override
	public void vertexListModified() {
		if (!isLoading()) {
			vSeq.modified();
			graphModified();
		}
	}

	@Override
	public void edgeListModified() {
		if (!isLoading()) {
			eSeq.modified();
			graphModified();
		}
	}

	@Override
	public void putVertexAfter(InternalVertex targetVertex,
			InternalVertex movedVertex) {
		DatabasePersistableVertex targetVertexImpl = (DatabasePersistableVertex) targetVertex;
		DatabasePersistableVertex movedVertexImpl = (DatabasePersistableVertex) movedVertex;
		vSeq.putAfter(targetVertexImpl, movedVertexImpl);
		// this.vertexListModified();
	}

	@Override
	public void putEdgeAfterInGraph(InternalEdge targetEdge,
			InternalEdge movedEdge) {
		assertEdges(movedEdge, targetEdge);
		DatabasePersistableEdge dbTargetEdge = (DatabasePersistableEdge) targetEdge;
		DatabasePersistableEdge dbMovedEdge = (DatabasePersistableEdge) movedEdge;
		eSeq.putAfter(dbTargetEdge, dbMovedEdge);
	}

	private void assertEdges(InternalEdge movedEdge, InternalEdge targetEdge) {
		assert (targetEdge != null) && targetEdge.isValid()
				&& eSeqContainsEdge(targetEdge);
		assert (movedEdge != null) && movedEdge.isValid()
				&& eSeqContainsEdge(movedEdge);
		assert !((DatabasePersistableEdge) targetEdge).equals(movedEdge);
	}

	@Override
	public void putVertexBefore(InternalVertex targetVertex,
			InternalVertex movedVertex) {
		DatabasePersistableVertex targetVertexImpl = (DatabasePersistableVertex) targetVertex;
		DatabasePersistableVertex movedVertexImpl = (DatabasePersistableVertex) movedVertex;
		vSeq.putBefore(targetVertexImpl, movedVertexImpl);
	}

	@Override
	public void putEdgeBeforeInGraph(InternalEdge targetEdge,
			InternalEdge movedEdge) {
		assertEdges(movedEdge, targetEdge);
		DatabasePersistableEdge dbTargetEdge = (DatabasePersistableEdge) targetEdge;
		DatabasePersistableEdge dbMovedEdge = (DatabasePersistableEdge) movedEdge;
		eSeq.putBefore(dbTargetEdge, dbMovedEdge);
	}

	@Override
	public boolean vSeqContainsVertex(Vertex v) {
		return (v != null) && (v.getGraph() == this)
				&& vSeq.contains((DatabasePersistableVertex) v);
	}

	@Override
	public boolean eSeqContainsEdge(Edge e) {
		return (e != null) && (e.getGraph() == this)
				&& eSeq.contains((DatabasePersistableEdge) e);
	}

	@Override
	public void expandVertexArray(int newSize) {
		if (newSize > vMax) {
			expandFreeVertexList(newSize);
		} else {
			throw new GraphException("newSize must > vSize: vSize=" + vMax
					+ ", newSize=" + newSize);
		}
	}

	private void expandFreeVertexList(int newSize) {
		if (getFreeVertexList() == null) {
			setFreeVertexList(new FreeIndexList(newSize));
		} else {
			getFreeVertexList().expandBy(newSize - vMax);
		}
		vMax = newSize;
		notifyMaxVertexCountIncreased(newSize);
	}

	@Override
	public void expandEdgeArray(int newSize) {
		if (newSize > eMax) {
			expandFreeEdgeList(newSize);
		} else {
			throw new GraphException("newSize must be > eSize: eSize=" + eMax
					+ ", newSize=" + newSize);
		}
	}

	private void expandFreeEdgeList(int newSize) {
		if (getFreeEdgeList() == null) {
			setFreeEdgeList(new FreeIndexList(newSize));
		} else {
			getFreeEdgeList().expandBy(newSize - eMax);
		}
		eMax = newSize;
		super.notifyMaxEdgeCountIncreased(newSize);
	}

	private void writeBackVertexListVersion() {
		try {
			containingDatabase.updateVertexListVersionOf(this);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException(
					"Could not write back vertex list version.", exception);
		}
	}

	private void writeBackEdgeListVersion() {
		try {
			containingDatabase.updateEdgeListVersionOf(this);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("Could not write back edge list version.",
					exception);
		}
	}

	/**
	 * Updates a vertex's attribute value in database.
	 * 
	 * @param edge
	 *            Vertex with attribute value to update.
	 * @param attributeName
	 *            Name of attribute.
	 */
	protected void updateVertexAttributeValueInDatabase(
			DatabasePersistableVertex vertex, String attributeName) {
		try {
			containingDatabase.updateAttributeValueOf(vertex, attributeName);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException(
					"Could write back vertex attribute value.", exception);
		}
	}

	/**
	 * Updates an edge's attribute value in database.
	 * 
	 * @param edge
	 *            Edge with attribute value to update.
	 * @param attributeName
	 *            Name of attribute.
	 */
	protected void updateEdgeAttributeValueInDatabase(
			DatabasePersistableEdge edge, String attributeName) {
		try {
			containingDatabase.updateAttributeValueOf(edge, attributeName);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("Could write back edge attribute value.",
					exception);
		}
	}

	/**
	 * Updates incidence list of a vertex in database.
	 * 
	 * @param vertex
	 *            Vertex with incidence list to update.
	 */
	protected void updateIncidenceListVersionInDatabase(
			DatabasePersistableVertex vertex) {
		try {
			containingDatabase.updateIncidenceListVersionOf(vertex);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException(
					"Could not write back incidence list of vertex.", exception);
		}
	}

	/**
	 * Updates sequence number of vertex in database.
	 * 
	 * @param vertex
	 *            Vertex with sequence number to update.
	 */
	protected void updateSequenceNumberInDatabase(
			DatabasePersistableVertex vertex) {
		try {
			containingDatabase.updateSequenceNumberInVSeqOf(vertex);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("", exception);
		}
	}

	/**
	 * Updates id of a vertex in database.
	 * 
	 * @param oldVId
	 *            Old id of vertex.
	 * @param vertex
	 *            Vertex with new id.
	 */
	protected void updateVertexIdInDatabase(int oldVId,
			DatabasePersistableVertex vertex) {
		try {
			containingDatabase.updateIdOf(oldVId, vertex);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("Could not write back new id of vertex.",
					exception);
		}
	}

	/**
	 * Write id of incident vertex back.
	 * 
	 * @param incidence
	 *            Incidence to write back id of it's incident vertex.
	 */
	protected void writeIncidentVIdBack(DatabasePersistableIncidence incidence) {
		try {
			containingDatabase.updateIncidentVIdOf(incidence);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException(
					"Cannot write id of incident vertex back.", exception);
		}
	}

	/**
	 * Write edge number mapping it's sequence in ESeq back.
	 * 
	 * @param incidence
	 *            Edge to write back it's number mapping it's sequence in ESeq.
	 */
	protected void writeSequenceNumberInESeqBack(DatabasePersistableEdge edge) {
		try {
			containingDatabase.updateSequenceNumberInESeqOf(edge);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException(
					"Cannot write number mapping edge's sequence in ESeq back.",
					exception);
		}
	}

	/**
	 * Write incidence's number mapping it's sequence in LambdaSeq back.
	 * 
	 * @param incidence
	 *            Incidence to write back it's number mapping it's sequence in
	 *            LambdaSeq.
	 */
	protected void writeSequenceNumberInLambdaSeqBack(
			DatabasePersistableIncidence incidence) {
		try {
			containingDatabase.updateSequenceNumberInLambdaSeqOf(incidence);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException(
					"Cannot write incidence's number mapping it's sequence in LambdaSeq back.",
					exception);
		}
	}

	/**
	 * Write edge's new id back to database.
	 * 
	 * @param edge
	 *            Edge to write back id.
	 * @param newEId
	 *            The new edge id.
	 */
	protected void writeBackEdgeId(DatabasePersistableEdge edge, int newEId) {
		try {
			containingDatabase.updateIdOf(edge, newEId);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("Cannot write edge id back.", exception);
		}
	}

	/**
	 * Gets previous vertex in VSeq before given one.
	 * 
	 * @param vertex
	 *            Vertex to get it's predecessor in VSeq.
	 * @return Predecessor in VSeq of edge or null if given one is first vertex
	 *         in VSeq.
	 */
	protected InternalVertex getPrevVertex(DatabasePersistableVertex vertex) {
		assert vertex.isValid();
		assert this == vertex.getGraph();
		return vSeq.getPrev(vertex);
	}

	/**
	 * Gets previous vertex in VSeq before given one.
	 * 
	 * @param vertex
	 *            Vertex to get it's successor in VSeq.
	 * @return Successor in VSeq of edge or null if given one is last vertex in
	 *         VSeq.
	 */
	protected InternalVertex getNextVertex(DatabasePersistableVertex vertex) {
		assert vertex.isValid();
		assert this == vertex.getGraph();
		return vSeq.getNext(vertex);
	}

	/**
	 * Gets previous edge in ESeq before given one.
	 * 
	 * @param edge
	 *            Edge to get it's predecessor in ESeq.
	 * @return Predecessor in ESeq of edge or null if given one is first edge in
	 *         ESeq.
	 */
	protected InternalEdge getPrevEdge(DatabasePersistableEdge edge) {
		assert edge.isValid();
		assert this == edge.getGraph();
		return eSeq.getPrev(edge);
	}

	/**
	 * Gets next edge in ESeq after given one.
	 * 
	 * @param edge
	 *            Edge to get it's successor in ESeq.
	 * @return Successor in ESeq of edge or null if given one is last edge in
	 *         ESeq.
	 */
	protected InternalEdge getNextEdge(DatabasePersistableEdge edge) {
		assert edge.isValid();
		assert this == edge.getGraph();
		return eSeq.getNext(edge);
	}

	@Override
	public void deleted() {
		setGId(-1);
		setPersistent(false);
		setLoading(false);
		containingDatabase = null;
		vSeq.clear();
		vSeq = null;
		eSeq.clear();
		eSeq = null;
	}

	@Override
	public void loadingCompleted() {
		setLoading(false);
	}

	/**
	 * Checks by it's id whether a vertex is cached or not.
	 * 
	 * @param vId
	 *            Id of vertex.
	 * @return true if vertex is cached, false otherwise.
	 */
	protected boolean isVertexCached(int vId) {
		return graphCache.containsVertex(this, vId);
	}

	/**
	 * Checks by it's id whether an edge is cached or not.
	 * 
	 * @param eId
	 *            Id of edge.
	 * @return true if edge is cached, false otherwise.
	 */
	protected boolean isEdgeCached(int eId) {
		return graphCache.containsEdge(this, eId);
	}

	/**
	 * Reorganizes sequence numbers in vertex list at database only.
	 * 
	 * Precondition: Vertex list must have been reorganized in memory just
	 * before.
	 */
	public void reorganizeVertexListInGraphDatabase() {
		try {
			long start = vSeq.getFirst().getSequenceNumberInVSeq();
			containingDatabase.reorganizeVertexList(this, start);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("VSeq of graph " + getId()
					+ " could not be reorganized in database.", exception);
		}
	}

	/**
	 * Reorganizes sequence numbers in edge list at database only.
	 * 
	 * Precondition: Edge list must have been reorganized in memory just before.
	 */
	public void reorganizeEdgeListInGraphDatabase() {
		try {
			long start = eSeq.getFirst().getSequenceNumberInESeq();
			containingDatabase.reorganizeEdgeList(this, start);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("ESeq of graph " + getId()
					+ " could not be reorganized in database.", exception);
		}
	}

	/**
	 * Reorganizes sequence numbers in incidence list of a vertex at database
	 * only.
	 * 
	 * @param vertex
	 *            Vertex with incidence list to reorganize.
	 * 
	 *            Precondition: Incidence list must have been reorganized in
	 *            memory just before.
	 */
	public void reorganizeIncidenceListInDatabaseOf(
			DatabasePersistableVertex vertex) {
		try {
			Edge firstIncidence = vertex.getFirstIncidence();
			long start = ((DatabasePersistableIncidence) firstIncidence)
					.getSequenceNumberInLambdaSeq();
			containingDatabase.reorganizeIncidenceList(vertex, start);
		} catch (GraphDatabaseException exception) {
			exception.printStackTrace();
			throw new GraphException("Incidence list of vertex "
					+ vertex.getId() + " in graph " + vertex.getGraph().getId()
					+ " could not be reorganized in database.", exception);
		}
	}

	@Override
	public void delete() {
		try {
			containingDatabase.delete(this);
		} catch (GraphDatabaseException exception) {
			throw new GraphException(
					"Graph could not be deleted from database.", exception);
		}
	}

	/**
	 * Writes version of graph and global sequences back to database.
	 */
	protected void writeBackVersions() {
		if (isPersistent()) {
			writeBackGraphVersion(getGraphVersion());
			writeBackEdgeListVersion();
			writeBackVertexListVersion();
		}
	}

	/**
	 * Writes version of graph back to database.
	 */
	private void writeBackGraphVersion(long graphVersion) {
		try {
			containingDatabase.updateVersionOf(this);
		} catch (GraphDatabaseException e) {
			e.printStackTrace();
		}
	}

	// --------------- copied from impl.std.GraphImpl --------------

	/**
	 * List of vertices to be deleted by a cascading delete caused by deletion
	 * of a composition "parent".
	 */
	private List<InternalVertex> deleteVertexList;

	@Override
	public FreeIndexList getFreeEdgeList() {
		return super.freeEdgeList;
	}

	@Override
	public FreeIndexList getFreeVertexList() {
		return super.freeVertexList;
	}

	@Override
	public void freeEdgeIndex(int index) {
		super.freeEdgeList.freeIndex(index);
	}

	@Override
	public void freeVertexIndex(int index) {
		super.freeVertexList.freeIndex(index);
	}

	@Override
	public int allocateEdgeIndex(int currentId) {
		int eId = freeEdgeList.allocateIndex();
		if (eId == 0) {
			expandEdgeArray(getExpandedEdgeCount());
			eId = freeEdgeList.allocateIndex();
		}
		return eId;
	}

	@Override
	public int allocateVertexIndex(int currentId) {
		int vId = freeVertexList.allocateIndex();
		if (vId == 0) {
			expandVertexArray(getExpandedVertexCount());
			vId = freeVertexList.allocateIndex();
		}
		return vId;
	}

	@Override
	public List<InternalVertex> getDeleteVertexList() {
		return deleteVertexList;
	}

	@Override
	public void setDeleteVertexList(List<InternalVertex> deleteVertexList) {
		this.deleteVertexList = deleteVertexList;
	}

	@Override
	public final boolean hasTransactionSupport() {
		return false;
	}

	public void clearCache() {
		graphCache.clear();
	}

	// --------- Unsupported operations ------------------------------------

	@Override
	public boolean isInConflict() {
		throw new UnsupportedOperationException(
				"Transactions are not supported for this graph.");
	}

	@Override
	public Transaction newReadOnlyTransaction() {
		throw new UnsupportedOperationException(
				"Creation of read-only-transactions is not supported for this graph.");
	}

	@Override
	public Transaction newTransaction() {
		throw new UnsupportedOperationException(
				"Creation of read-write-transactions is not supported for this graph.");
	}

	@Override
	public void restoreSavepoint(Savepoint savepoint)
			throws InvalidSavepointException {
		throw new UnsupportedOperationException(
				"Definition of save-points is not supported for this graph.");
	}

	@Override
	public void setCurrentTransaction(Transaction transaction) {
		throw new UnsupportedOperationException(
				"Transactions are not supported for this graph.");
	}

	@Override
	public Savepoint defineSavepoint() {
		throw new UnsupportedOperationException(
				"Definition of save-points is not supported for this graph.");
	}

	@Override
	public Transaction getCurrentTransaction() {
		throw new UnsupportedOperationException(
				"Transactions are not supported for this graph.");
	}

	@Override
	public InternalVertex[] getVertex() {
		throw new UnsupportedOperationException(
				"Operation not supported by this graph.");
	}

	@Override
	public InternalEdge[] getEdge() {
		throw new UnsupportedOperationException(
				"Operation not supported by this graph.");
	}

	@Override
	public ReversedEdgeBaseImpl[] getRevEdge() {
		throw new UnsupportedOperationException(
				"Operation not supported by this graph.");
	}

	@Override
	public void setVertex(InternalVertex[] vertex) {
		throw new UnsupportedOperationException(
				"Operation not supported by this graph.");
	}

	@Override
	public void setEdge(InternalEdge[] edge) {
		throw new UnsupportedOperationException(
				"Operation not supported by this graph.");
	}

	@Override
	public void setRevEdge(InternalEdge[] revEdge) {
		throw new UnsupportedOperationException(
				"Operation not supported by this graph.");
	}

	@Override
	public void abort() {
		throw new UnsupportedOperationException(
				"Abort is not supported for this graph.");
	}

	@Override
	public void commit() throws CommitFailedException {
		throw new UnsupportedOperationException(
				"Commit is not supported for this graph.");
	}

	@Override
	public void defragment() {
		throw new UnsupportedOperationException(
				"Defragment is not supported by this graph.");
	}

	@Override
	public void internalLoadingCompleted(int[] firstIncidence,
			int[] nextIncidence) {
		throw new UnsupportedOperationException(
				"Operation is not supported by this graph.");
	}

	@Override
	public TraversalContext getTraversalContext() {
		return tc;
	}

	@Override
	public TraversalContext setTraversalContext(TraversalContext tc) {
		TraversalContext oldTc = this.tc;
		this.tc = tc;
		return oldTc;
	}
}
