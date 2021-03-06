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

public class DerbyDb extends GraphDatabase {

	protected DerbyDb(String url) throws GraphDatabaseException {
		super(url);
	}

	@Override
	protected void connect() throws GraphDatabaseException {
		connection = getConnectionWithJdbcDriver("org.apache.derby.jdbc.ClientDriver");
		sqlStatementList = new DerbyStatementList(this);
	}

	@Override
	protected void applyVendorSpecificDbSchema() throws GraphDatabaseException,
			SQLException {
		addPrimaryKeyConstraints();
		// this.addStoredProcedures();
	}

	@Override
	public void clearAllTables() throws SQLException {
		DerbyStatementList derbyStatementList = (DerbyStatementList) sqlStatementList;

		PreparedStatement statement;

		statement = derbyStatementList.clearTableAttributeValue();
		statement.execute();

		statement = derbyStatementList.clearTableEdgeAttributeValue();
		statement.execute();

		statement = derbyStatementList.clearTableGraphAttributeValue();
		statement.execute();

		statement = derbyStatementList.clearTableIncidence();
		statement.execute();

		statement = derbyStatementList.clearTableEdge();
		statement.execute();

		statement = derbyStatementList.clearTableVertex();
		statement.execute();

		statement = derbyStatementList.clearTableGraph();
		statement.execute();

		statement = derbyStatementList.clearTableAttribute();
		statement.execute();

		statement = derbyStatementList.clearTableType();
		statement.execute();

		statement = derbyStatementList.clearTableGraphSchema();
		statement.execute();
	}

}
