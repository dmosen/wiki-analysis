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

import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.uni_koblenz.jgralab.GraphIOException;

// TODO Check what is faster: multidimensional index or several single indices on fks
// TODO Test with nvarchar instead of text
// TODO index on FK is good practice
public class PostgreSqlDb extends GraphDatabase {

	/**
	 * Creates a new <code>PostgreSqlDb</code>.
	 * 
	 * @param url
	 *            Url to connect to database.
	 * @throws GraphDatabaseException
	 *             TODO
	 */
	protected PostgreSqlDb(String url) throws GraphDatabaseException {
		super(url);
	}

	@Override
	protected void connect() throws GraphDatabaseException {
		connection = getConnectionWithJdbcDriver("org.postgresql.Driver");
		sqlStatementList = new PostgreSqlStatementList(this);
	}

	@Override
	protected void applyVendorSpecificDbSchema() throws GraphDatabaseException,
			SQLException {
		addPrimaryKeyConstraints();
		addStoredProcedures();
	}

	/**
	 * Rearranges the table storage according to the indices for optimized table
	 * access.
	 * 
	 * @throws SQLException
	 */
	private void cluster() throws SQLException {
		PreparedStatement statement = ((PostgreSqlStatementList) sqlStatementList)
				.clusterIncidenceTable();
		statement.execute();
	}

	@Override
	protected void insertVertex(DatabasePersistableVertex vertex)
			throws SQLException, GraphIOException {
		PreparedStatement insertStatement = ((PostgreSqlStatementList) sqlStatementList)
				.insertVertex(vertex);
		insertStatement.executeUpdate();
	}

	@Override
	protected void insertEdge(DatabasePersistableEdge edge,
			DatabasePersistableVertex alpha, DatabasePersistableVertex omega)
			throws SQLException, GraphIOException {
		assert edge.isNormal();
		PreparedStatement insertStatement = ((PostgreSqlStatementList) sqlStatementList)
				.insertEdge(edge, alpha, omega);
		insertStatement.executeUpdate();
	}

	@Override
	public void addIndices() throws SQLException {
		super.addIndices();
		cluster();
	}

	@Override
	public void clearAllTables() throws SQLException {
		PreparedStatement statement = sqlStatementList.clearAllTables();
		statement.execute();
	}
}
