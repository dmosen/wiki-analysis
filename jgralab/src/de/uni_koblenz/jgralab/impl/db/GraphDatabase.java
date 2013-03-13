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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.SortedSet;

import de.uni_koblenz.jgralab.AttributedElement;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphException;
import de.uni_koblenz.jgralab.GraphFactory;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.impl.GraphFactoryImpl;
import de.uni_koblenz.jgralab.schema.Attribute;
import de.uni_koblenz.jgralab.schema.AttributedElementClass;
import de.uni_koblenz.jgralab.schema.EdgeClass;
import de.uni_koblenz.jgralab.schema.GraphClass;
import de.uni_koblenz.jgralab.schema.Schema;
import de.uni_koblenz.jgralab.schema.VertexClass;

/**
 * Database holding graphs which can be located on a PostgreSql, MySQL or Apache
 * Derby/JavaDB server. When a graph database has been created it will only work
 * with one DBMS. To change DBMS create a new graph database with
 * <code>openGraphDatabase(...)</code>. Keep in mind that a database is always
 * specific to it's schema.
 * 
 * @author ultbreit@uni-koblenz.de
 */
public abstract class GraphDatabase {

	/**
	 * Holds graph databases which are still open.
	 */
	private static HashMap<String, GraphDatabase> openGraphDatabases = new HashMap<String, GraphDatabase>();

	/**
	 * Opens a graph database at location specified by an url with given
	 * credentials. Factory method churning open graph databases. This method
	 * takes the url in jdbc format for including the user name and the password
	 * in a single string.
	 * 
	 * @param url
	 *            URL to the graph database in the format
	 *            <code>driver://username:password@host:port/database</code>
	 * @return An open graph database.
	 * @throws GraphDatabaseException
	 *             if the URL is not properly formatted
	 */
	public static GraphDatabase openGraphDatabase(String url)
			throws GraphDatabaseException {
		if (url == null) {
			throw new GraphDatabaseException("The given url may not be null.");
		}
		url = url.trim();

		if (openGraphDatabases.containsKey(url)) {
			return openGraphDatabases.get(url);
		} else {
			GraphDatabase out = createVendorSpecificDb(url);
			out.connect();
			out.setAutoCommit(false);
			openGraphDatabases.put(url, out);
			return out;
		}
	}

	/**
	 * Opens a graph database. Factory method churning open graph databases.
	 * This method retrieves the connection information (url, user name and
	 * password) from a system property <code>jgralab_dbconnection</code>, which
	 * has to be set in order to use this method. The format of the url is the
	 * following: <code>driver://username:password@host:port/database</code>
	 * 
	 * @return An open graph database.
	 * @throws GraphDatabaseException
	 *             if there was no URL provided by the system property
	 *             <code>jgralab_dbconnection</code> or if the provided URL is
	 *             malformed.
	 */

	private static GraphDatabase createVendorSpecificDb(String url)
			throws GraphDatabaseException {
		if (url.startsWith("jdbc:postgresql:")) {
			return new PostgreSqlDb(url);
		} else if (url.startsWith("jdbc:derby:")) {
			return new DerbyDb(url);
		} else if (url.startsWith("jdbc:mysql:")) {
			return new MySqlDb(url);
		} else {
			throw new GraphDatabaseException("Database vendor not supported.");
		}
	}

	/**
	 * Holds url to graph database.
	 */
	private String url;

	/**
	 * JDBC connection to database.
	 */
	protected Connection connection;

	/**
	 * SQL statements written in database vendor specific SQL dialect.
	 */
	protected SqlStatementList sqlStatementList;

	/**
	 * Collects graphs which were loaded from this graph database.
	 */
	private final Hashtable<String, GraphImpl> loadedGraphs = new Hashtable<String, GraphImpl>();

	/**
	 * Caches primary keys of types and attributes to save table joins.
	 */
	private final HashMap<Schema, PrimaryKeyCache> internalCache = new HashMap<Schema, PrimaryKeyCache>();

	/**
	 * Creates and initializes a new <code>GraphDatabase</code>.
	 * 
	 * @param url
	 *            Location of graph database.
	 * @throws GraphDatabaseException
	 *             Given url is malformed.
	 */
	protected GraphDatabase(String url) throws GraphDatabaseException {
		this.url = url;
	}

	/**
	 * Creates tables in database to persist graphs.
	 * 
	 * @throws GraphDatabaseException
	 *             Creation of tables not successful.
	 */
	// public abstract void applyDbSchema() throws GraphDatabaseException;

	/**
	 * Optimizes database for import of graphs.
	 */
	// public abstract void optimizeForBulkImport() throws
	// GraphDatabaseException;

	/**
	 * Optimizes database for traversal of graphs.
	 */
	// public abstract void optimizeForGraphTraversal() throws
	// GraphDatabaseException;

	/**
	 * Optimizes database for creation of graphs.
	 */
	// public abstract void optimizeForGraphCreation() throws
	// GraphDatabaseException;

	/**
	 * Cache for primary keys of attributes and types defined in a graph schema.
	 */
	private class PrimaryKeyCache {

		/**
		 * Maps type's id to it's name.
		 */
		private final HashMap<Integer, String> typeNameMap = new HashMap<Integer, String>();

		/**
		 * Maps type's name to it's id.
		 */
		private final HashMap<String, Integer> typeIdMap = new HashMap<String, Integer>();

		/**
		 * Maps attribute's id to it's name.
		 */
		private final HashMap<Integer, String> attributeNameMap = new HashMap<Integer, String>();

		/**
		 * Maps type's name to it's id.
		 */
		private final HashMap<String, Integer> attributeIdMap = new HashMap<String, Integer>();

		void addType(int typeId, String name) {
			typeNameMap.put(typeId, name);
			typeIdMap.put(name, typeId);
		}

		void addAttribute(int attributeId, String name) {
			attributeNameMap.put(attributeId, name);
			attributeIdMap.put(name, attributeId);
		}

		int getTypeId(String name) {
			return typeIdMap.get(name);
		}

		int getAttributeId(String name) {
			return attributeIdMap.get(name);
		}

		String getTypeName(int typeId) {
			return typeNameMap.get(typeId);
		}

		String getAttributeName(int attributeId) {
			return attributeNameMap.get(attributeId);
		}
	}

	/**
	 * Connects to graph database located at url specified in constructor.
	 * 
	 * @throws GraphDatabaseException
	 *             Database vendor not supported or identified. JDBC driver not
	 *             found. Connection attempt failed.
	 */
	protected abstract void connect() throws GraphDatabaseException;

	protected Connection getConnectionWithJdbcDriver(String jdbcDriverName)
			throws GraphDatabaseException {
		try {
			Class.forName(jdbcDriverName);
			// return DriverManager.getConnection(url, userName, password);
			return DriverManager.getConnection(url);
		} catch (ClassNotFoundException exception) {
			throw new GraphDatabaseException(
					"JDBC driver to connect to database not found: "
							+ jdbcDriverName, exception);
		} catch (SQLException exception) {
			throw new GraphDatabaseException(
					"Could not connect to graph database at " + url
							+ "\nThe url has to be in jdbc-format for "
							+ "including the username and the password.\n"
							+ "The correct pattern is: 'jdbc:<driver>:"
							+ "[[host][:port]][/]<db_name>"
							+ "[?user=<user_name>&password=<password>]'",
					exception);
		}
	}

	/**
	 * Gets JDBC connection to database.
	 * 
	 * @return JDBC connection to database.
	 */
	protected Connection getConnection() {
		return connection;
	}

	/**
	 * Begins a database transaction.
	 * 
	 * @throws GraphDatabaseException
	 *             Transaction could not be begun.
	 */
	public void beginTransaction() throws GraphDatabaseException {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException("Could not begin transaction.",
					exception);
		}
	}

	/**
	 * Commits a database transaction.
	 * 
	 * @throws GraphDatabaseException
	 *             Transaction could not be committed.
	 */
	public void commitTransaction() throws GraphDatabaseException {
		try {
			connection.commit();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException("Could not commit transaction.",
					exception);
		}

	}

	/**
	 * Sets auto commit mode of database.
	 * 
	 * @param autoCommitMode
	 *            Auto commit mode to set.
	 * @throws GraphDatabaseException
	 *             Auto commit mode could not be set.
	 */
	public void setAutoCommit(boolean autoCommitMode)
			throws GraphDatabaseException {
		try {
			connection.setAutoCommit(autoCommitMode);
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException("Could not set auto commit mode.",
					exception);
		}
	}

	/**
	 * Closes database, writes back graph version and commits any open
	 * transactions.
	 * 
	 * @throws GraphDatabaseException
	 *             An error occurred on close.
	 */
	public void close() throws GraphDatabaseException {
		try {
			writeBackVersionOfLoadedGraphs();
			loadedGraphs.clear();
			commitAnyTransactions();
			openGraphDatabases.remove(url);
			connection.close();
		} catch (SQLException exception) {
			throw new GraphDatabaseException(
					"An error occured on closing database " + url, exception);
		}
	}

	private void commitAnyTransactions() throws SQLException {
		if (!connection.getAutoCommit()) {
			connection.commit();
		}
	}

	private void writeBackVersionOfLoadedGraphs() {
		for (String id : loadedGraphs.keySet()) {
			GraphImpl graph = loadedGraphs.get(id);
			graph.writeBackVersions();
		}
	}

	/**
	 * Deletes a graph from database.
	 * 
	 * @param id
	 *            Id of graph to delete.
	 * @throws GraphDatabaseException
	 *             Deletion was not successful.
	 */
	public void deleteGraph(String id) throws GraphDatabaseException {
		DatabasePersistableGraph graph = getGraph(id);
		delete(graph);
	}

	/**
	 * Deletes a graph from database.
	 * 
	 * @param graph
	 *            Graph to delete.
	 * @throws GraphDatabaseException
	 *             Delete not successful.
	 */
	public void delete(DatabasePersistableGraph graph)
			throws GraphDatabaseException {
		try {
			this.deleteGraph(graph);
			loadedGraphs.remove(graph.getId());
		} catch (SQLException exception) {
			throw new GraphDatabaseException("Graph " + graph.getId()
					+ " could not be deleted from database.", exception);
		}
	}

	private void deleteGraph(DatabasePersistableGraph graph)
			throws SQLException {
		deleteAllAttributeValuesIn(graph);
		deleteAllGraphElementsIn(graph);
		this.deleteGraph(graph.getGId());
	}

	private void deleteAllAttributeValuesIn(DatabasePersistableGraph graph)
			throws SQLException {
		PreparedStatement deleteVertexAttributesStatement = sqlStatementList
				.deleteVertexAttributeValuesOfGraph(graph.getGId());
		deleteVertexAttributesStatement.executeUpdate();
		PreparedStatement deleteEdgeAttributesStatement = sqlStatementList
				.deleteEdgeAttributeValuesOfGraph(graph.getGId());
		deleteEdgeAttributesStatement.executeUpdate();
		PreparedStatement deleteGraphAttributesStatement = sqlStatementList
				.deleteAttributeValuesOfGraph(graph.getGId());
		deleteGraphAttributesStatement.executeUpdate();
	}

	private void deleteAllGraphElementsIn(DatabasePersistableGraph graph)
			throws SQLException {
		PreparedStatement deleteIncidencesStatement = sqlStatementList
				.deleteIncidencesOfGraph(graph.getGId());
		deleteIncidencesStatement.executeUpdate();
		PreparedStatement deleteEdgeStatement = sqlStatementList
				.deleteEdgesOfGraph(graph.getGId());
		deleteEdgeStatement.executeUpdate();
		PreparedStatement deleteVerticesStatement = sqlStatementList
				.deleteVerticesOfGraph(graph.getGId());
		deleteVerticesStatement.executeUpdate();
	}

	private void deleteGraph(int gId) throws SQLException {
		PreparedStatement statement = sqlStatementList.deleteGraph(gId);
		statement.executeUpdate();
	}

	/**
	 * Deletes a vertex from database.
	 * 
	 * @param vertex
	 *            Vertex to delete.
	 * @throws GraphDatabaseException
	 *             Delete not successful.
	 */
	public void delete(DatabasePersistableVertex vertex)
			throws GraphDatabaseException {
		try {
			this.deleteVertex(vertex);
		} catch (SQLException exception) {
			throw new GraphDatabaseException("Vertex " + vertex.getId()
					+ " in graph " + vertex.getGraph().getId()
					+ " could not be deleted from database.", exception);
		}
	}

	private void deleteVertex(DatabasePersistableVertex vertex)
			throws SQLException {
		// OPTIMIZE Can be done in one roundtrip.
		deleteAttributesOf(vertex);
		deleteIncidentEdgesOf(vertex);
		this.deleteVertex(vertex.getId(), vertex.getGId());
	}

	private void deleteAttributesOf(DatabasePersistableVertex vertex)
			throws SQLException {
		PreparedStatement deleteAttributesStatement = sqlStatementList
				.deleteAttributeValuesOfVertex(vertex.getId(), vertex.getGId());
		deleteAttributesStatement.executeUpdate();
	}

	private void deleteIncidentEdgesOf(DatabasePersistableVertex vertex)
			throws SQLException {
		ArrayList<Integer> edgeIds = getIncidentEIdsOf(vertex);
		for (Integer eId : edgeIds) {
			deleteIncidencesOfEdge(eId, vertex.getGId());
			deleteAttributeValuesOfEdge(eId, vertex.getGId());
			this.deleteEdge(eId, vertex.getGId());
		}
	}

	private ArrayList<Integer> getIncidentEIdsOf(
			DatabasePersistableVertex vertex) throws SQLException {
		PreparedStatement statement = sqlStatementList
				.selectIncidentEIdsOfVertex(vertex.getId(), vertex.getGId());
		ResultSet result = statement.executeQuery();
		ArrayList<Integer> edgeIds = new ArrayList<Integer>();
		while (result.next()) {
			edgeIds.add(result.getInt(1));
		}
		result.close();
		return edgeIds;
	}

	private void deleteIncidencesOfEdge(Integer eId, int gId)
			throws SQLException {
		PreparedStatement deleteAttributesStatement = sqlStatementList
				.deleteIncidencesOfEdge(eId, gId);
		deleteAttributesStatement.executeUpdate();
	}

	private void deleteVertex(int vId, int gId) throws SQLException {
		PreparedStatement deleteVertexStatement = sqlStatementList
				.deleteVertex(vId, gId);
		deleteVertexStatement.executeUpdate();
	}

	/**
	 * Deletes an edge from database.
	 * 
	 * @param edge
	 *            Edge to delete.
	 * @throws GraphException
	 *             Delete not successful.
	 */
	public void delete(DatabasePersistableEdge edge) throws GraphException {
		try {
			this.deleteEdge(edge);
		} catch (SQLException exception) {
			throw new GraphException(
					"Edge could not be deleted from database.", exception);
		}
	}

	private void deleteEdge(DatabasePersistableEdge edge) throws SQLException {
		deleteIncidencesOfEdge(edge.getId(), edge.getGId());
		deleteAttributeValuesOf(edge);
		this.deleteEdge(edge.getId(), edge.getGId());
	}

	private void deleteAttributeValuesOf(DatabasePersistableEdge edge)
			throws SQLException {
		deleteAttributeValuesOfEdge(edge.getId(), edge.getGId());
	}

	private void deleteAttributeValuesOfEdge(int eId, int gId)
			throws SQLException {
		PreparedStatement deleteAttributesStatement = sqlStatementList
				.deleteAttributeValuesOfEdge(eId, gId);
		deleteAttributesStatement.executeUpdate();
	}

	private void deleteEdge(int eId, int gId) throws SQLException {
		PreparedStatement deleteEdgeStatement = sqlStatementList.deleteEdge(
				eId, gId);
		deleteEdgeStatement.executeUpdate();
	}

	/**
	 * Inserts a new vertex without any incidences into database.
	 * 
	 * @param vertex
	 *            Vertex to insert.
	 * @throws GraphDatabaseException
	 *             Insert not successful.
	 */
	public void insert(DatabasePersistableVertex vertex)
			throws GraphDatabaseException {
		assert vertex.getIncidenceListVersion() == 0;
		try {
			insertVertex(vertex);
			vertex.setInitialized(true);
			vertex.setPersistent(true);
		} catch (Exception exception) {
			throw new GraphDatabaseException(
					"Vertex could not be inserted into database.", exception);
		}
	}

	protected void insertVertex(DatabasePersistableVertex vertex)
			throws SQLException, GraphIOException {
		int typeId = this.getTypeIdOf(vertex);
		PreparedStatement insertStatement = sqlStatementList.insertVertex(
				vertex.getId(), typeId, vertex.getGId(),
				vertex.getIncidenceListVersion(),
				vertex.getSequenceNumberInVSeq());
		insertStatement.executeUpdate();
		SortedSet<Attribute> attributes = vertex.getAttributedElementClass()
				.getAttributeList();
		for (Attribute attribute : attributes) {
			int attributeId = getAttributeId(vertex.getGraph(),
					attribute.getName());
			String value = convertToString(vertex, attribute.getName());
			insertStatement = sqlStatementList.insertVertexAttributeValue(
					vertex.getId(), vertex.getGId(), attributeId, value);
			insertStatement.executeUpdate();
		}
	}

	/**
	 * Inserts an edge into database.
	 * 
	 * @param edge
	 *            Edge to insert.
	 * @param alpha
	 *            Start vertex of edge.
	 * @param omega
	 *            End vertex of edge.
	 * @throws GraphDatabaseException
	 *             Insert not successful.
	 */
	public void insert(DatabasePersistableEdge edge,
			DatabasePersistableVertex alpha, DatabasePersistableVertex omega)
			throws GraphDatabaseException {
		try {
			insertEdge(edge, alpha, omega);
			edge.setInitialized(true);
			edge.setPersistent(true);
		} catch (Exception exception) {
			throw new GraphDatabaseException("Edge " + edge.getId()
					+ " could not be inserted into database.", exception);
		}
	}

	protected void insertEdge(DatabasePersistableEdge edge,
			DatabasePersistableVertex alpha, DatabasePersistableVertex omega)
			throws SQLException, GraphIOException {
		assert edge.isNormal();
		int typeId = this.getTypeIdOf(edge);
		PreparedStatement insertStatement = sqlStatementList.insertEdge(
				edge.getId(), edge.getGId(), typeId,
				edge.getSequenceNumberInESeq());
		insertStatement.executeUpdate();

		insertStatement = sqlStatementList.insertIncidence(edge.getId(),
				alpha.getId(), edge.getGId(),
				edge.getSequenceNumberInLambdaSeq());
		insertStatement.executeUpdate();

		DatabasePersistableEdge reversedEdge = (DatabasePersistableEdge) edge
				.getReversedEdge();
		insertStatement = sqlStatementList.insertIncidence(
				reversedEdge.getId(), omega.getId(), reversedEdge.getGId(),
				reversedEdge.getSequenceNumberInLambdaSeq());
		insertStatement.executeUpdate();

		SortedSet<Attribute> attributes = edge.getAttributedElementClass()
				.getAttributeList();
		for (Attribute attribute : attributes) {
			int attributeId = getAttributeId(edge.getGraph(),
					attribute.getName());
			String value = convertToString(edge, attribute.getName());
			insertStatement = sqlStatementList.insertEdgeAttributeValue(
					edge.getId(), edge.getGId(), attributeId, value);
			insertStatement.executeUpdate();
		}
	}

	/**
	 * Gets type id of an edge.
	 * 
	 * @param edge
	 *            Edge to get it's type id.
	 * @return Type id of edge.
	 */
	protected int getTypeIdOf(DatabasePersistableEdge edge) {
		String edgeTypeName = edge.getAttributedElementClass()
				.getQualifiedName();
		return getTypeIdOfGraphElement(edge.getGraph(), edgeTypeName);
	}

	/**
	 * Updates version of a graph.
	 * 
	 * @param graph
	 *            Graph to update it's version.
	 * @throws GraphDatabaseException
	 *             Update was not successful.
	 */
	public void updateVersionOf(DatabasePersistableGraph graph)
			throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList.updateGraphVersion(
					graph.getGId(), graph.getGraphVersion());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not update version of graph " + graph.getId(),
					exception);
		}
	}

	/**
	 * Updates vertex list version of a graph.
	 * 
	 * @param graph
	 *            Graph to update it's vertex list version.
	 * @throws GraphDatabaseException
	 *             Update was not successful.
	 */
	public void updateVertexListVersionOf(DatabasePersistableGraph graph)
			throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList
					.updateVertexListVersionOfGraph(graph.getGId(),
							graph.getVertexListVersion());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not update vertex list version of graph "
							+ graph.getId(), exception);
		}
	}

	/**
	 * Updates edge list version of a graph.
	 * 
	 * @param graph
	 *            Graph to update it's edge list version.
	 * @throws GraphDatabaseException
	 *             Update was not successful.
	 */
	public void updateEdgeListVersionOf(DatabasePersistableGraph graph)
			throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList
					.updateEdgeListVersionOfGraph(graph.getGId(),
							graph.getEdgeListVersion());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not update edge list version of graph "
							+ graph.getId(), exception);
		}
	}

	/**
	 * Updates attribute value of a graph.
	 * 
	 * @param graph
	 *            Graph with attribute value to update.
	 * @param attributeName
	 *            Name of attribute.
	 * @throws GraphDatabaseException
	 *             Update not successful.
	 */
	public void updateAttributeValueOf(DatabasePersistableGraph graph,
			String attributeName) throws GraphDatabaseException {
		int attributeId = getAttributeId(graph, attributeName);
		try {
			String value = convertToString(graph, attributeName);
			PreparedStatement statement = sqlStatementList
					.updateAttributeValueOfGraph(graph.getGId(), attributeId,
							value);
			statement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException("Could not update attribute "
					+ attributeName + " of graph " + graph.getId(), exception);
		}
	}

	/**
	 * Updates attribute value of a vertex.
	 * 
	 * @param vertex
	 *            Vertex with attribute value to update.
	 * @param attributeName
	 *            Name of attribute
	 * @throws GraphDatabaseException
	 *             Update not successful.
	 */
	public void updateAttributeValueOf(DatabasePersistableVertex vertex,
			String attributeName) throws GraphDatabaseException {
		try {
			int attributeId = getAttributeId(vertex.getGraph(), attributeName);
			String value = convertToString(vertex, attributeName);
			PreparedStatement statement = sqlStatementList
					.updateAttributeValueOfVertex(vertex.getId(),
							vertex.getGId(), attributeId, value);
			statement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not update value of attribute " + attributeName
							+ " of vertex " + vertex.getId(), exception);
		}
	}

	/**
	 * Updates attribute value of an edge.
	 * 
	 * @param edge
	 *            Edge with attribute value to update.
	 * @param attributeName
	 *            Name of attribute
	 * @throws GraphDatabaseException
	 *             Update not successful.
	 */
	public void updateAttributeValueOf(DatabasePersistableEdge edge,
			String attributeName) throws GraphDatabaseException {
		try {
			String value = convertToString(edge, attributeName);
			int attributeId = getAttributeId(edge.getGraph(), attributeName);
			PreparedStatement statement = sqlStatementList
					.updateAttributeValueOfEdge(edge.getId(), edge.getGId(),
							attributeId, value);
			statement.executeUpdate();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not update value of attribute " + attributeName
							+ " of edge " + edge.getId(), exception);
		}
	}

	/**
	 * Updates incidence list of a vertex.
	 * 
	 * @param vertex
	 *            Vertex with incidence list to update.
	 * @throws GraphDatabaseException
	 *             Update not successful.
	 */
	public void updateIncidenceListVersionOf(DatabasePersistableVertex vertex)
			throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList
					.updateLambdaSeqVersionOfVertex(vertex.getId(),
							vertex.getGId(), vertex.getIncidenceListVersion());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not update incidence list version of vertex "
							+ vertex.getId(), exception);
		}
	}

	/**
	 * Updates number mapping vertex's sequence in VSeq.
	 * 
	 * @param vertex
	 *            Vertex to update.
	 * @throws GraphDatabaseException
	 *             Update not successful.
	 */
	public void updateSequenceNumberInVSeqOf(DatabasePersistableVertex vertex)
			throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList
					.updateSequenceNumberInVSeqOfVertex(vertex.getId(),
							vertex.getGId(), vertex.getSequenceNumberInVSeq());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not update sequence number of vertex "
							+ vertex.getId(), exception);
		}
	}

	/**
	 * Updates id of vertex in graph to database.
	 * 
	 * @param vertex
	 *            Vertex to update it's id.
	 * @throws GraphDatabaseException
	 *             Update not successful.
	 */
	public void updateIdOf(int oldVId, DatabasePersistableVertex vertex)
			throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList.updateIdOfVertex(
					oldVId, vertex.getId(), vertex.getId());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not update vertex of old id " + oldVId
							+ " with new id " + vertex.getId(), exception);
		}
	}

	/**
	 * Updates id of edge in graph in database.
	 * 
	 * @param edge
	 *            Edge to update.
	 * @throws GraphDatabaseException
	 *             Update not successful.
	 */
	public void updateIdOf(DatabasePersistableEdge edge, int newEId)
			throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList.updateIdOfEdge(
					edge.getId(), edge.getGId(), newEId);
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException("Cannot update edge id "
					+ edge.getId() + " in graph " + edge.getGraph().getId()
					+ " to database.", exception);
		}
	}

	/**
	 * Updates id of a graph.
	 * 
	 * @param graph
	 *            Graph to update.
	 * @throws GraphDatabaseException
	 *             Update not successful.
	 */
	public void updateIdOf(DatabasePersistableGraph graph)
			throws GraphDatabaseException {
		PreparedStatement statement;
		try {
			statement = sqlStatementList.updateGraphId(graph.getGId(),
					graph.getId());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException("", exception);
		}
	}

	/**
	 * Updates incident vertex of an edge.
	 * 
	 * @param incidence
	 *            Incidence to update.
	 * @throws GraphDatabaseException
	 *             Update not successful.
	 */
	public void updateIncidentVIdOf(DatabasePersistableIncidence incidence)
			throws GraphDatabaseException {
		PreparedStatement statement;
		try {
			statement = sqlStatementList.updateIncidentVIdOfIncidence(
					incidence.getIncidentEId(), incidence.getIncidentVId(),
					incidence.getGId());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Cannot update id of incident vertex "
							+ incidence.getIncidentVId()
							+ " with edge "
							+ incidence.getIncidentEId()
							+ " in graph "
							+ ((DatabasePersistableEdge) incidence).getGraph()
									.getId() + " to database.", exception);
		}
	}

	/**
	 * Updates number mapping edge's sequence in ESeq.
	 * 
	 * @param edge
	 *            Edge to update.
	 * @throws GraphDatabaseException
	 *             Could not update sequence number.
	 */
	public void updateSequenceNumberInESeqOf(DatabasePersistableEdge edge)
			throws GraphDatabaseException {
		PreparedStatement statement;
		try {
			statement = sqlStatementList
					.updateSequenceNumberInESeqOfEdge(edge.getId(),
							edge.getGId(), edge.getSequenceNumberInESeq());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Cannot update sequence number of edge " + edge.getId()
							+ " in graph " + edge.getGraph().getId()
							+ " to database.", exception);
		}
	}

	/**
	 * Updates number mapping edge's sequence in LambdaSeq of incident vertex.
	 * 
	 * @param incidence
	 *            Incidence to update.
	 * @throws GraphDatabaseException
	 *             Could not update sequence number.
	 */
	public void updateSequenceNumberInLambdaSeqOf(
			DatabasePersistableIncidence incidence)
			throws GraphDatabaseException {
		PreparedStatement statement;
		try {
			statement = sqlStatementList
					.updateSequenceNumberInLambdaSeqOfIncidence(
							Math.abs(incidence.getIncidentEId()),
							incidence.getIncidentVId(), incidence.getGId(),
							incidence.getSequenceNumberInLambdaSeq());
			statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Cannot update sequence number of incidence with vertex "
							+ incidence.getIncidentVId() + " and edge "
							+ incidence.getIncidentEId() + " in graph "
							+ ((DatabasePersistableEdge) incidence).getId()
							+ " to database.", exception);
		}
	}

	/**
	 * Gets a vertex.
	 * 
	 * @param vId
	 *            Identifier of vertex to get.
	 * @return A vertex.
	 * @throws GraphDatabaseException
	 *             Could not get vertex.
	 */
	public DatabasePersistableVertex getVertex(int vId,
			DatabasePersistableGraph graph) throws GraphDatabaseException {
		try {
			return getVertexWithLessRoundTrips(graph, vId);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException("Cannot get vertex " + vId
					+ " in graph " + graph.getId() + " from database.",
					exception);
		}
	}

	private DatabasePersistableVertex getVertexWithLessRoundTrips(
			DatabasePersistableGraph graph, int vId) throws Exception {
		ResultSet vertexData = getVertexAndIncidenceData(graph.getGId(), vId);
		VertexClass vertexClass = getVertexClassFrom(graph, vertexData);
		GraphFactory graphFactory = graph.getGraphFactory();
		DatabasePersistableVertex vertex = (DatabasePersistableVertex) graphFactory
				.createVertex(vertexClass, vId, graph);
		long incidenceListVersion = vertexData.getLong(2);
		vertex.setIncidenceListVersion(incidenceListVersion);
		long sequenceNumber = vertexData.getLong(3);
		vertex.setSequenceNumberInVSeq(sequenceNumber);
		if (vertexData.getString(5) != null) {
			do {
				long sequenceNumberInLambdaSeq = vertexData.getLong(4);
				EdgeDirection direction = EdgeDirection.parse(vertexData
						.getString(5));
				int eId = vertexData.getInt(6);
				if (direction == EdgeDirection.OUT) {
					vertex.addIncidence(eId, sequenceNumberInLambdaSeq);
				} else {
					vertex.addIncidence(-eId, sequenceNumberInLambdaSeq);
				}
			} while (vertexData.next());
		}
		vertexData.close();
		if (vertex.getAttributedElementClass().hasAttributes()) {
			setAttributesOf(vertex);
		}
		vertex.setInitialized(true);
		return vertex;
	}

	private VertexClass getVertexClassFrom(DatabasePersistableGraph graph,
			ResultSet vertexData) throws SQLException {
		int typeId = vertexData.getInt(1);
		String qualifiedTypeName = getTypeName(graph, typeId);
		Schema schema = graph.getSchema();
		return (VertexClass) schema
				.getAttributedElementClass(qualifiedTypeName);
	}

	private ResultSet getVertexAndIncidenceData(int gId, int vId)
			throws GraphDatabaseException, SQLException {
		PreparedStatement statement = sqlStatementList
				.selectVertexWithIncidences(vId, gId);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet;
		} else {
			throw new GraphDatabaseException("No vertex " + vId + " for graph "
					+ gId + " stored in database.");
		}
	}

	private void setAttributesOf(DatabasePersistableVertex vertex)
			throws SQLException, NoSuchFieldException {
		ResultSet attributeData = this.getAttributeDataOf(vertex);
		while (attributeData.next()) {
			int attributeId = attributeData.getInt(1);
			String attributeName = getAttributeName(vertex.getSchema(),
					attributeId);
			String serializedAttributeValue = attributeData.getString(2);
			try {
				if (vertex.getAttributedElementClass().containsAttribute(
						attributeName)) {
					vertex.readAttributeValueFromString(attributeName,
							serializedAttributeValue);
				}
			} catch (GraphIOException e) {
				e.printStackTrace();
			}
		}
		attributeData.close();
	}

	private String getAttributeName(Schema schema, int attributeId) {
		PrimaryKeyCache cache = internalCache.get(schema);
		return cache.getAttributeName(attributeId);
	}

	private ResultSet getAttributeDataOf(DatabasePersistableVertex vertex)
			throws SQLException {
		PreparedStatement statement = sqlStatementList
				.selectAttributeValuesOfVertex(vertex.getId(), vertex.getGId());
		return statement.executeQuery();
	}

	/**
	 * Use it to get prev/next edge in graph as no incident vertices have to be
	 * known.
	 * 
	 * @param eId
	 *            Id of edge to get.
	 * @param graph
	 *            Graph edge belongs to.
	 * @return An edge.
	 * @throws GraphDatabaseException
	 *             Getting edge not succesful.
	 */
	public DatabasePersistableEdge getEdge(int eId,
			DatabasePersistableGraph graph) throws GraphDatabaseException {
		try {
			ResultSet edgeData = getEdgeAndIncidenceData(graph, eId);
			DatabasePersistableEdge edge = instanceEdgeFrom(graph, edgeData,
					eId);
			edgeData.close();
			if (edge.getAttributedElementClass().hasAttributes()) {
				this.setAttributesOf(edge);
			}
			edge.setInitialized(true);
			return edge;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException("Could not get edge with id "
					+ eId + " of graph " + graph.getId(), exception);
		}
	}

	private ResultSet getEdgeAndIncidenceData(DatabasePersistableGraph graph,
			int eId) throws SQLException {
		PreparedStatement statement = sqlStatementList
				.selectEdgeWithIncidences(eId, graph.getGId());
		ResultSet edgeData = statement.executeQuery();
		if (edgeData.next()) {
			return edgeData;
		} else {
			throw new GraphException("No edge with id " + eId + " of graph "
					+ graph.getId() + " in database.");
		}
	}

	private DatabasePersistableEdge instanceEdgeFrom(
			DatabasePersistableGraph graph, ResultSet edgeData, int eId)
			throws Exception {
		EdgeClass edgeClass = getEdgeClassFrom(graph, edgeData.getInt(1));

		long sequenceNumberInESeq = edgeData.getLong(2);
		Vertex alpha = null;
		long alphaSeqNumber = 0;
		Vertex omega = null;
		long omegaSeqNumber = 0;
		do {
			EdgeDirection direction = EdgeDirection
					.parse(edgeData.getString(3));
			if (direction == EdgeDirection.OUT) {
				alpha = graph.getVertex(edgeData.getInt(4));
				alphaSeqNumber = edgeData.getLong(5);
			} else {
				omega = graph.getVertex(edgeData.getInt(4));
				omegaSeqNumber = edgeData.getLong(5);
			}
		} while (edgeData.next());
		GraphFactory graphFactory = graph.getGraphFactory();
		DatabasePersistableEdge edge = (DatabasePersistableEdge) graphFactory
				.createEdge(edgeClass, eId, graph, alpha, omega);
		edge.setSequenceNumberInESeq(sequenceNumberInESeq);
		((DatabasePersistableEdge) edge.getNormalEdge())
				.setSequenceNumberInLambdaSeq(alphaSeqNumber);
		((DatabasePersistableEdge) edge.getNormalEdge()).setIncidentVId(alpha
				.getId());
		((DatabasePersistableEdge) edge.getReversedEdge())
				.setSequenceNumberInLambdaSeq(omegaSeqNumber);
		((DatabasePersistableEdge) edge.getReversedEdge()).setIncidentVId(omega
				.getId());
		return edge;
	}

	private EdgeClass getEdgeClassFrom(DatabasePersistableGraph graph,
			int typeId) throws SQLException {
		String qualifiedTypeName = getTypeName(graph, typeId);
		Schema schema = graph.getSchema();
		return (EdgeClass) schema.getAttributedElementClass(qualifiedTypeName);
	}

	private void setAttributesOf(DatabasePersistableEdge edge)
			throws SQLException, NoSuchFieldException {
		ResultSet attributeData = this.getAttributeDataOf(edge);
		while (attributeData.next()) {
			String attributeName = getAttributeName(
					edge.getGraph().getSchema(), attributeData.getInt(1));
			String serializedAttributeValue = attributeData.getString(2);
			try {
				if (edge.getAttributedElementClass().containsAttribute(
						attributeName)) {
					edge.readAttributeValueFromString(attributeName,
							serializedAttributeValue);
				}
			} catch (GraphIOException e) {
				e.printStackTrace();
			}
		}
		attributeData.close();
	}

	private ResultSet getAttributeDataOf(DatabasePersistableEdge edge)
			throws SQLException {
		PreparedStatement statement = sqlStatementList
				.selectAttributeValuesOfEdge(edge.getId(), edge.getGId());
		return statement.executeQuery();
	}

	/**
	 * Counts vertices of a graph in database.
	 * 
	 * @param graph
	 *            Graph to count it's vertices.
	 * @return Amount of vertices in graph.
	 * @throws GraphDatabaseException
	 *             Count not successful.
	 */
	public int countVerticesOf(DatabasePersistableGraph graph)
			throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList
					.countVerticesOfGraph(graph.getGId());
			ResultSet result = statement.executeQuery();
			result.next();
			int vertexCount = result.getInt(1);
			result.close();
			return vertexCount;
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not count vertices in graph " + graph.getId(),
					exception);
		}
	}

	/**
	 * Counts edges of a graph in database.
	 * 
	 * @param graph
	 *            Graph to count it's edges.
	 * @return Amount of edges in graph.
	 * @throws GraphDatabaseException
	 *             Count not successful.
	 */
	public int countEdgesOf(DatabasePersistableGraph graph)
			throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList
					.countEdgesOfGraph(graph.getGId());
			ResultSet result = statement.executeQuery();
			result.next();
			int edgeCount = result.getInt(1);
			result.close();
			return edgeCount;
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException("Could not count edges in graph "
					+ graph.getId(), exception);
		}
	}

	/**
	 * Gets a graph from database.
	 * 
	 * @param id
	 *            Identifier of graph to get.
	 * @return Graph with given identifier.
	 * @throws GraphDatabaseException
	 *             Getting graph not successful.
	 */
	@SuppressWarnings("unchecked")
	public <G extends Graph> G getGraph(String id)
			throws GraphDatabaseException {
		if (loadedGraphs.containsKey(id)) {
			return (G) loadedGraphs.get(id);
		} else {
			return (G) loadAndCacheGraph(id);
		}
	}

	private DatabasePersistableGraph loadAndCacheGraph(String id)
			throws GraphDatabaseException {
		DatabasePersistableGraph graph = loadGraph(id);
		loadedGraphs.put(id, (GraphImpl) graph);
		return graph;
	}

	private DatabasePersistableGraph loadGraph(String id)
			throws GraphDatabaseException {
		try {
			GraphDAO graphDAO = new GraphDAO(id);
			GraphImpl graph = getEmptyGraphInstance(id);
			graphDAO.restoreStateInto(graph);
			if (!haveTypesAndAttributesBeenPreloaded(graph)) {
				preloadTypesAndAttributes(graph);
			}
			return graph;
		} catch (Exception exception) {
			throw new GraphDatabaseException("Could not get graph " + id
					+ " from database.", exception);
		}
	}

	/**
	 * Access to a persisted graph state in database.
	 */
	private class GraphDAO {

		int graphId;
		long graphVersion;
		long vertexListVersion;
		long edgeListVersion;

		GraphDAO(String id) throws SQLException {
			ResultSet result = getGraphRecord(id);
			graphId = result.getInt(1);
			graphVersion = result.getLong(2);
			vertexListVersion = result.getLong(3);
			edgeListVersion = result.getLong(4);
			result.close();
		}

		private ResultSet getGraphRecord(String uid) throws SQLException {
			PreparedStatement statement = sqlStatementList.selectGraph(uid);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet;
			} else {
				throw new GraphException("No graph with id " + uid
						+ " in database.");
			}
		}

		private int getGraphId() throws SQLException {
			return graphId;
		}

		private long getGraphVersion() throws SQLException {
			return graphVersion;
		}

		private long getVertexListVersion() throws SQLException {
			return vertexListVersion;
		}

		private long getEdgeListVersion() throws SQLException {
			return edgeListVersion;
		}

		void restoreStateInto(GraphImpl graph) throws SQLException,
				NoSuchFieldException, GraphDatabaseException {
			graph.setLoading(true);
			graph.setPersistent(true);
			setInstanceVariables(graph);
			restoreVertexList(graph);
			restoreEdgeList(graph);
			graph.setVCount(countVerticesOf(graph));
			graph.setECount(countEdgesOf(graph));
			restoreAttributes(graph);
			graph.setLoading(false);
			graph.loadingCompleted();
		}

		private void setInstanceVariables(GraphImpl graph) throws SQLException {
			graph.setGId(getGraphId());
			graph.setGraphVersion(getGraphVersion());
			graph.setVertexListVersion(getVertexListVersion());
			graph.setEdgeListVersion(getEdgeListVersion());
		}

		private void restoreAttributes(DatabasePersistableGraph graph)
				throws SQLException, NoSuchFieldException {
			ResultSet graphAttributes = getGraphAttributes(getGraphId());
			while (graphAttributes.next()) {
				String attributeName = graphAttributes.getString(1);
				String serializedValue = graphAttributes.getString(2);
				try {
					graph.readAttributeValueFromString(attributeName,
							serializedValue);
				} catch (GraphIOException e) {
					e.printStackTrace();
				}
			}
			graphAttributes.close();
		}

		private ResultSet getGraphAttributes(int gId) throws SQLException {
			PreparedStatement statement = sqlStatementList
					.selectAttributeValuesOfGraph(gId);
			return statement.executeQuery();
		}

		private void restoreVertexList(DatabasePersistableGraph graph)
				throws SQLException {
			PreparedStatement statement = sqlStatementList
					.selectVerticesOfGraph(graph.getGId());
			ResultSet vertexRecords = statement.executeQuery();
			while (vertexRecords.next()) {
				int vId = vertexRecords.getInt(1);
				long sequenceNumber = vertexRecords.getLong(2);
				graph.addVertex(vId, sequenceNumber);
			}
			vertexRecords.close();
		}

		private void restoreEdgeList(DatabasePersistableGraph graph)
				throws SQLException {
			PreparedStatement statement = sqlStatementList
					.selectEdgesOfGraph(graph.getGId());
			ResultSet edgeRecords = statement.executeQuery();
			while (edgeRecords.next()) {
				int eId = edgeRecords.getInt(1);
				long sequenceNumber = edgeRecords.getLong(2);
				graph.addEdge(eId, sequenceNumber);
			}
			edgeRecords.close();
		}

	}

	@SuppressWarnings("unchecked")
	private GraphImpl getEmptyGraphInstance(String id) throws SQLException,
			GraphIOException {
		Schema schema = getSchemaForGraph(id);
		// GraphFactory graphFactory = schema.getGraphFactory();
		GraphClass graphClass = schema.getGraphClass();
		try {
			Class<? extends GraphFactory> c = (Class<? extends GraphFactory>) Class
					.forName(schema.getPackagePrefix() + ".impl.db."
							+ graphClass.getSimpleName() + "FactoryImpl");
			Constructor<? extends GraphFactory> cons = c.getConstructor();
			GraphFactory graphFactory = cons.newInstance();
			((GraphFactoryImpl) graphFactory).setGraphDatabase(this);
			return (GraphImpl) graphFactory.createGraph(schema.getGraphClass(),
					id, 100, 100);
		} catch (Exception e) {
			throw new GraphIOException("Could not create an instance of "
					+ graphClass.getSchemaClass().getName());
		}
	}

	private Schema getSchemaForGraph(String uid) throws SQLException,
			GraphIOException {
		PreparedStatement statement = sqlStatementList
				.selectSchemaNameForGraph(uid);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			Schema schema = createSchema(resultSet.getString(1),
					resultSet.getString(2));
			resultSet.close();
			return schema;
		} else {
			throw new GraphIOException("No schema for graph in database.");
		}
	}

	@SuppressWarnings("unchecked")
	private Schema createSchema(String packagePrefix, String name)
			throws GraphIOException {
		try {
			Class<Schema> schemaClass = (Class<Schema>) Class
					.forName(packagePrefix + "." + name);
			Class<?>[] params = {};
			Method instanceMethod = schemaClass.getMethod("instance", params);
			Object[] args = {};
			return (Schema) instanceMethod.invoke(this, args);
		} catch (Exception exception) {
			throw new GraphIOException("Could not create schema "
					+ packagePrefix + "." + name, exception);
		}
	}

	/**
	 * Checks if database contains a graph.
	 * 
	 * @param id
	 *            Id of graph to check for.
	 * @throws GraphDatabaseException
	 *             Check could not be performed.
	 */
	public boolean containsGraph(String id) throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList.selectGraph(id);
			ResultSet result = statement.executeQuery();
			boolean containsGraph = result.next();
			result.close();
			return containsGraph;
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not determine if database contains graph " + id,
					exception);
		}
	}

	/**
	 * Inserts a freshly created graph that has no vertices and edges into
	 * database.
	 * 
	 * Precondition: A suiting schema exists in database.
	 * 
	 * Postcondition: State of graph has been persisted to database.
	 * 
	 * @throws GraphDatabaseException
	 */
	public void insert(GraphImpl graph) throws GraphDatabaseException {
		if (contains(graph.getSchema())) {
			insertAndCacheGraph(graph);
		} else {
			throw new GraphException(
					"No schema stored in database for graph. First persist one with GraphIO.loadSchemaIntoGraphDatabase(...)");
		}
	}

	/**
	 * Checks if database contains schema.
	 * 
	 * @param schema
	 *            Schema to check for.
	 * @return true if graph contains schema, otherwise false.
	 * @throws GraphDatabaseException
	 *             Check not successful.
	 */
	public boolean contains(Schema schema) throws GraphDatabaseException {
		return containsSchema(schema.getPackagePrefix(), schema.getName());
	}

	/**
	 * Checks if database contains schema.
	 * 
	 * @param packagePrefix
	 *            Package prefix of schema.
	 * @param name
	 *            Name of schema.
	 * @return true if graph contains schema, otherwise false.
	 * @throws GraphDatabaseException
	 *             Check not successful.
	 */
	public boolean containsSchema(String packagePrefix, String name)
			throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList.selectSchemaId(
					packagePrefix, name);
			ResultSet result = statement.executeQuery();
			boolean containsSchema = result.next();
			result.close();
			return containsSchema;
		} catch (SQLException exception) {
			throw new GraphDatabaseException(
					"Could not determine if database contains schema "
							+ packagePrefix + "." + name, exception);
		}
	}

	private void insertAndCacheGraph(GraphImpl graph)
			throws GraphDatabaseException {
		try {
			insertGraph(graph);
			cacheGraph(graph);
		} catch (Exception exception) {
			throw new GraphDatabaseException("Graph " + graph.getId()
					+ " could not be inserted into database.", exception);
		}
	}

	private void insertGraph(DatabasePersistableGraph graph)
			throws SQLException, GraphIOException {
		if (!haveTypesAndAttributesBeenPreloaded(graph)) {
			preloadTypesAndAttributes(graph);
		}
		getTypeIdAndInsertGraph(graph);
		insertAttributeValuesOf(graph);
		graph.setPersistent(true);
		graph.setInitialized(true);
	}

	private void cacheGraph(GraphImpl graph) {
		loadedGraphs.put(graph.getId(), graph);
	}

	private boolean haveTypesAndAttributesBeenPreloaded(
			DatabasePersistableGraph graph) {
		return internalCache.containsKey(graph.getSchema());
	}

	private void preloadTypesAndAttributes(DatabasePersistableGraph graph)
			throws SQLException {
		PrimaryKeyCache preloadedTypesAndAttributes = new PrimaryKeyCache();
		preloadTypesOf(graph.getSchema(), preloadedTypesAndAttributes);
		preloadAttributesOf(graph.getSchema(), preloadedTypesAndAttributes);
		internalCache.put(graph.getSchema(), preloadedTypesAndAttributes);
	}

	private void preloadTypesOf(Schema schema, PrimaryKeyCache typeCollector)
			throws SQLException {
		PreparedStatement statement = sqlStatementList.selectTypesOfSchema(
				schema.getPackagePrefix(), schema.getName());
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			typeCollector.addType(result.getInt(2), result.getString(1));
		}
		result.close();
	}

	private void preloadAttributesOf(Schema schema,
			PrimaryKeyCache attributeCollector) throws SQLException {
		PreparedStatement statement = sqlStatementList
				.selectAttributesOfSchema(schema.getPackagePrefix(),
						schema.getName());
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			attributeCollector.addAttribute(result.getInt(2),
					result.getString(1));
		}
		result.close();
	}

	private void getTypeIdAndInsertGraph(DatabasePersistableGraph graph)
			throws SQLException {
		int typeId = getTypeIdOf(graph);
		int gId = insertGraphRecord(graph, typeId);
		graph.setGId(gId);
	}

	private int getTypeIdOf(Graph graph) {
		PrimaryKeyCache cache = internalCache.get(graph.getSchema());
		return cache.getTypeId(graph.getGraphClass().getQualifiedName());
	}

	private String getTypeName(Graph graph, int typeId) {
		PrimaryKeyCache cache = internalCache.get(graph.getSchema());
		return cache.getTypeName(typeId);
	}

	/**
	 * Gets type id of a vertex.
	 * 
	 * @param vertex
	 *            Vertex to get type id for.
	 * @return Type id of vertex.
	 */
	protected int getTypeIdOf(DatabasePersistableVertex vertex) {
		String vertexTypeName = vertex.getAttributedElementClass()
				.getQualifiedName();
		return getTypeIdOfGraphElement(vertex.getGraph(), vertexTypeName);
	}

	private int getTypeIdOfGraphElement(Graph graph, String name) {
		PrimaryKeyCache cache = internalCache.get(graph.getSchema());
		return cache.getTypeId(name);
	}

	private void insertAttributeValuesOf(DatabasePersistableGraph graph)
			throws SQLException, GraphIOException {
		SortedSet<Attribute> attributes = graph.getAttributedElementClass()
				.getAttributeList();
		for (Attribute attribute : attributes) {
			insertAttributeValue(graph, attribute.getName());
		}
	}

	private void insertAttributeValue(DatabasePersistableGraph graph,
			String attributeName) throws SQLException, GraphIOException {
		int attributeId = getAttributeId(graph, attributeName);
		String value = convertToString(graph, attributeName);
		PreparedStatement statement = sqlStatementList
				.insertGraphAttributeValue(graph.getGId(), attributeId, value);
		statement.executeUpdate();
	}

	/**
	 * Gets if of a graph attribute.
	 * 
	 * @param graph
	 *            Graph to get id of attribute.
	 * @param attributeName
	 *            Name of attribute.
	 * @return id of graph attribute.
	 */
	protected int getAttributeId(Graph graph, String attributeName) {
		PrimaryKeyCache cache = internalCache.get(graph.getSchema());
		return cache.getAttributeId(attributeName);
	}

	/**
	 * Converts an attribute value to string.
	 * 
	 * @param attributedElement
	 *            Element with attribute to convert.
	 * @param attributeName
	 *            Name of attribute.
	 * @return Serialized value of attribute.
	 * @throws GraphDatabaseException
	 *             Conversion not successful.
	 */
	protected String convertToString(AttributedElement<?, ?> attributedElement,
			String attributeName) throws GraphDatabaseException {
		try {
			return attributedElement.writeAttributeValueToString(attributeName);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not convert value of attribute " + attributeName
							+ " to string.");
		}
	}

	private int insertGraphRecord(DatabasePersistableGraph graph, int typeId)
			throws SQLException {
		PreparedStatement statement = sqlStatementList.insertGraph(
				graph.getId(), graph.getGraphVersion(),
				graph.getVertexListVersion(), graph.getEdgeListVersion(),
				typeId);
		statement.executeUpdate();
		return getGeneratedGId(statement);
	}

	private int getGeneratedGId(PreparedStatement statement)
			throws SQLException {
		ResultSet result = statement.getGeneratedKeys();
		if (result.next()) {
			return result.getInt(1);
		} else {
			throw new GraphException("Graph could not be stored to database.");
		}
	}

	// ----------------- INSERT SCHEMA ---------------------------

	/**
	 * Inserts a schema.
	 * 
	 * @param schema
	 *            Schema to insert.
	 */
	public void insertSchema(Schema schema) throws GraphDatabaseException {
		if (!containsSchema(schema.getPackagePrefix(), schema.getName())) {
			insertSchemaInTransaction(schema);
		} else {
			throw new GraphDatabaseException("A schema with name "
					+ schema.getPackagePrefix() + "." + schema.getName()
					+ " already exists in database");
		}
	}

	private void insertSchemaInTransaction(Schema schema)
			throws GraphDatabaseException {
		try {
			int schemaId = insertSchemaRecord(schema);
			insertDefinedTypesOf(schema, schemaId);
		} catch (Exception exception) {
			throw new GraphDatabaseException(
					"Schema could not be inserted into database.", exception);
		}
	}

	private int insertSchemaRecord(Schema schema) throws SQLException {
		PreparedStatement statement = sqlStatementList.insertSchema(schema,
				schema.toTGString());
		statement.executeUpdate();
		ResultSet result = statement.getGeneratedKeys();
		if (result.next()) {
			return result.getInt(1);
		} else {
			throw new GraphException("No key generated for inserted schema.");
		}
	}

	private final HashSet<String> attributeNames = new HashSet<String>();

	private void insertDefinedTypesOf(Schema schema, int schemaId)
			throws SQLException {
		insertGraphClass(schema.getGraphClass(), schemaId);
		insertVertexClasses(schema.getVertexClasses(), schemaId);
		insertEdgeClasses(schema.getEdgeClasses(), schemaId);
		insertAttributes(schemaId);
	}

	private void insertGraphClass(GraphClass graphClass, int schemaId)
			throws SQLException {
		insertType(graphClass, schemaId);
		collectAttributeNamesOf(graphClass);
	}

	private void insertType(
			AttributedElementClass<?, ?> attributedElementClass, int schemaId)
			throws SQLException {
		PreparedStatement statement = sqlStatementList.insertType(
				attributedElementClass.getQualifiedName(), schemaId);
		statement.executeUpdate();
	}

	private void collectAttributeNamesOf(
			AttributedElementClass<?, ?> attributedElementClass) {
		for (Attribute attribute : attributedElementClass.getAttributeList()) {
			attributeNames.add(attribute.getName());
		}
	}

	private void insertAttribute(String attributeName, int schemaId)
			throws SQLException {
		PreparedStatement statement = sqlStatementList.insertAttribute(
				attributeName, schemaId);
		statement.executeUpdate();
	}

	private void insertVertexClasses(List<VertexClass> vertexClasses,
			int schemaId) throws SQLException {
		for (VertexClass vertexClass : vertexClasses) {
			insertType(vertexClass, schemaId);
			collectAttributeNamesOf(vertexClass);
		}
	}

	private void insertEdgeClasses(List<EdgeClass> edgeClasses, int schemaId)
			throws SQLException {
		for (EdgeClass edgeClass : edgeClasses) {
			insertType(edgeClass, schemaId);
			collectAttributeNamesOf(edgeClass);
		}
	}

	private void insertAttributes(int schemaId) throws SQLException {
		for (String attributeName : attributeNames) {
			insertAttribute(attributeName, schemaId);
		}
	}

	/**
	 * Reorganizes a vertex list.
	 * 
	 * @param graph
	 *            Graph with vertex list to reorganize.
	 * @param start
	 *            Sequence number at which reorganized list will start.
	 * @throws GraphDatabaseException
	 *             Reorganization not successful.
	 */
	public void reorganizeVertexList(GraphImpl graph, long start)
			throws GraphDatabaseException {
		try {
			CallableStatement statement = sqlStatementList
					.createReorganizeVertexListCall(graph.getGId(), start);
			statement.execute();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not call stored procedure to reorganize vertex list in database.",
					exception);
		}
	}

	/**
	 * Reorganizes an edge list.
	 * 
	 * @param graph
	 *            Graph with edge list to reorganize.
	 * @param start
	 *            Sequence number at which reorganized list will start.
	 * @throws GraphDatabaseException
	 * @throws GraphDatabaseException
	 *             Reorganization not successful.
	 */
	public void reorganizeEdgeList(GraphImpl graph, long start)
			throws GraphDatabaseException {
		try {
			CallableStatement statement = sqlStatementList
					.createReorganizeEdgeListCall(graph.getGId(), start);
			statement.execute();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not call stored procedure to reorganize edge list in database.",
					exception);
		}

	}

	/**
	 * Reorganizes a vertex list.
	 * 
	 * @param vertex
	 *            Vertex with incidence list to reorganize.
	 * @param start
	 *            Sequence number at which reorganized list will start.
	 * @throws GraphDatabaseException
	 *             Reorganization not successful.
	 */
	public void reorganizeIncidenceList(DatabasePersistableVertex vertex,
			long start) throws GraphDatabaseException {
		try {
			CallableStatement statement = sqlStatementList
					.createReorganizeIncidenceListCall(vertex.getId(),
							vertex.getGId(), start);
			statement.execute();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not call stored procedure to reorganize incidence list in database.",
					exception);
		}
	}

	/**
	 * Checks if connection to database is still upheld.
	 * 
	 * @return true if connection is upheld, otherwise false.
	 * @throws GraphDatabaseException
	 *             Check could not performed.
	 */
	public boolean isConnected() throws GraphDatabaseException {
		try {
			return !connection.isClosed();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not check if connection is still open.", exception);
		}
	}

	/**
	 * Deletes a schema from database.
	 * 
	 * @param prefix
	 *            Package prefix of schema.
	 * @param name
	 *            Name of schema to delete.
	 * @throws GraphDatabaseException
	 *             Deletion not successful.
	 */
	public void deleteSchema(String prefix, String name)
			throws GraphDatabaseException {
		try {
			// TODO
			// get graphs of this schema
			// delete graphs
			// then delete types
			// delete them from memory and from database
			// then attributes
			// delete them from memory and from database
			deleteSchemaRecord(prefix, name);
		} catch (SQLException exception) {
			throw new GraphDatabaseException("Graph schema " + prefix + "."
					+ name + " could not be deleted.", exception);
		}
	}

	private void deleteSchemaRecord(String prefix, String name)
			throws SQLException {
		PreparedStatement statement = sqlStatementList.deleteSchema(prefix,
				name);
		statement.executeUpdate();
	}

	/**
	 * Gets list of ids of contained graphs.
	 * 
	 * @return A list of ids which can be empty if no graphs have been persisted
	 *         in database.
	 * @throws GraphDatabaseException
	 *             Getting list not successful.
	 */
	public ArrayList<String> getIdsOfContainedGraphs()
			throws GraphDatabaseException {
		try {
			ArrayList<String> ids = new ArrayList<String>();
			PreparedStatement statement = sqlStatementList.selectIdOfGraphs();
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ids.add(result.getString(1));
			}
			result.close();
			return ids;
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not get list of graph ids.", exception);
		}
	}

	/**
	 * Gets schema definition.
	 * 
	 * @param packagePrefix
	 *            Package prefix of schema.
	 * @param schemaName
	 *            Name of schema
	 * @return Schema definition.
	 * @throws GraphDatabaseException
	 */
	public String getSchemaDefinition(String packagePrefix, String schemaName)
			throws GraphDatabaseException {
		try {
			PreparedStatement statement = sqlStatementList
					.selectSchemaDefinition(packagePrefix, schemaName);
			statement.executeQuery();
			ResultSet result = statement.getResultSet();

			String schemaDefinition = result.next() ? result.getString(1)
					: null;
			result.close();
			return schemaDefinition;

		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Could not get definition of schema " + packagePrefix + "."
							+ schemaName, exception);
		}
	}

	public String getUrl() {
		return url;
	}

	public void rollback() throws GraphDatabaseException {
		try {
			connection.rollback();
		} catch (SQLException exception) {
			exception.printStackTrace();
			throw new GraphDatabaseException(
					"Transaction could not be rolled back.", exception);
		}
	}

	protected void addPrimaryKeyConstraints() throws SQLException {
		PreparedStatement statement = sqlStatementList
				.addPrimaryKeyConstraintOnVertexTable();
		statement.execute();
		statement = sqlStatementList.addPrimaryKeyConstraintOnEdgeTable();
		statement.execute();
		statement = sqlStatementList.addPrimaryKeyConstraintOnIncidenceTable();
		statement.execute();
		statement = sqlStatementList
				.addPrimaryKeyConstraintOnVertexAttributeValueTable();
		statement.execute();
		statement = sqlStatementList
				.addPrimaryKeyConstraintOnEdgeAttributeValueTable();
		statement.execute();
	}

	public void addIndices() throws SQLException {
		PreparedStatement statement = sqlStatementList.addIndexOnLambdaSeq();
		statement.execute();
	}

	public void applyDbSchema() throws GraphDatabaseException {
		try {
			applyDbSchemaInTransaction();
		} catch (SQLException exception) {
			exception.printStackTrace();
			rollback();
			throw new GraphDatabaseException(
					"Generic database schema could not be applied to database "
							+ getUrl(), exception);
		}
	}

	private void applyDbSchemaInTransaction() throws GraphDatabaseException,
			SQLException {
		beginTransaction();
		createTables();
		applyVendorSpecificDbSchema();
		commitTransaction();
	}

	protected void createTables() throws SQLException {
		PreparedStatement statement = sqlStatementList
				.createGraphSchemaTableWithConstraints();
		statement.execute();
		statement = sqlStatementList.createTypeTableWithConstraints();
		statement.execute();
		statement = sqlStatementList.createGraphTableWithConstraints();
		statement.execute();
		statement = sqlStatementList.createVertexTable();
		statement.execute();
		statement = sqlStatementList.createEdgeTable();
		statement.execute();
		statement = sqlStatementList.createIncidenceTable();
		statement.execute();
		statement = sqlStatementList.createAttributeTableWithConstraints();
		statement.execute();
		statement = sqlStatementList
				.createGraphAttributeValueTableWithConstraints();
		statement.execute();
		statement = sqlStatementList.createVertexAttributeValueTable();
		statement.execute();
		statement = sqlStatementList.createEdgeAttributeValueTable();
		statement.execute();
	}

	protected abstract void applyVendorSpecificDbSchema()
			throws GraphDatabaseException, SQLException;

	protected void addStoredProcedures() throws SQLException {
		sqlStatementList.createStoredProcedureToReorganizeEdgeList();
		sqlStatementList.createStoredProcedureToReorganizeVertexList();
		sqlStatementList.createStoredProcedureToReorganizeIncidenceList();
	}

	public void dropIndices() throws SQLException {
		PreparedStatement statement = sqlStatementList.dropIndexOnLambdaSeq();
		statement.execute();
	}

	public abstract void clearAllTables() throws SQLException;

}
