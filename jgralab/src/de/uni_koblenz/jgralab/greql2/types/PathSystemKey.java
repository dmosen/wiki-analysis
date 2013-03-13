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

package de.uni_koblenz.jgralab.greql2.types;

import de.uni_koblenz.jgralab.Vertex;

/**
 * This is the key of the hashmap which stores the references to the parent
 * vertices. This class is _not_ a JValue
 */
public class PathSystemKey {
	private Vertex vertex;
	private int stateNumber;

	@Override
	public int hashCode() {
		return vertex.hashCode() + stateNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof PathSystemKey)) {
			return false;
		}
		PathSystemKey foreignKey = (PathSystemKey) o;
		return foreignKey.vertex == vertex
				&& foreignKey.stateNumber == stateNumber;
	}

	/**
	 * creates a new PathSystemKey
	 * 
	 * @param v
	 * @param s
	 */
	public PathSystemKey(Vertex v, int s) {
		vertex = v;
		stateNumber = s;
	}

	/**
	 * returns the string representation of this key
	 */
	@Override
	public String toString() {
		return "(V: " + vertex.getId() + ", S: " + stateNumber + ")";
	}

	public Vertex getVertex() {
		return vertex;
	}

	public int getStateNumber() {
		return stateNumber;
	}
}
