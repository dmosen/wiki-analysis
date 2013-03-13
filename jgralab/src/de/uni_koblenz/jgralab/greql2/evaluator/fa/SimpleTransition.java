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

package de.uni_koblenz.jgralab.greql2.evaluator.fa;

import java.util.Set;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.graphmarker.GraphMarker;
import de.uni_koblenz.jgralab.greql2.evaluator.vertexeval.ThisEdgeEvaluator;
import de.uni_koblenz.jgralab.greql2.evaluator.vertexeval.VertexEvaluator;
import de.uni_koblenz.jgralab.greql2.schema.ThisEdge;
import de.uni_koblenz.jgralab.greql2.types.TypeCollection;
import de.uni_koblenz.jgralab.schema.AttributedElementClass;
import de.uni_koblenz.jgralab.schema.EdgeClass;

/**
 * This transition accepts a SimplePathDescription. A SimplePathDescription is
 * for instance something like v -->{isExprOf} w.
 * 
 * @author ist@uni-koblenz.de Summer 2006, Diploma Thesis
 * 
 */
public class SimpleTransition extends Transition {

	protected VertexEvaluator predicateEvaluator;

	protected ThisEdgeEvaluator thisEdgeEvaluator;

	/**
	 * The collection of types that are accepted by this transition
	 */
	protected TypeCollection typeCollection;

	/**
	 * an edge may have valid roles. This set holds the valid roles at the other
	 * end of an edge accepted by this transition. If the transition is valid
	 * for no explicit role, this set is null
	 */
	protected Set<String> validToEdgeRoles;

	/**
	 * an edge may have valid roles. This set holds the valid roles at the other
	 * end of an edge accepted by this transition. If the transition is valid
	 * for no explicit role, this set is null
	 */
	protected Set<String> validFromEdgeRoles;

	/**
	 * this transition may accept edges in direction in, out or any
	 */
	protected AllowedEdgeDirection validDirection;

	/**
	 * returns a string which describes the edge
	 */
	@Override
	public String edgeString() {
		// String desc = "SimpleTransition";
		String desc = "SimpleTransition (Dir:" + validDirection.toString();
		if (typeCollection != null) {
			desc = desc + "\n " + typeCollection.toString() + "\n ";
		}
		desc += ")";
		return desc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * greql2.evaluator.fa.Transition#equalSymbol(greql2.evaluator.fa.EdgeTransition
	 * )
	 */
	@Override
	public boolean equalSymbol(Transition t) {
		if (!(t instanceof SimpleTransition)) {
			return false;
		}
		SimpleTransition et = (SimpleTransition) t;
		if (!typeCollection.equals(et.typeCollection)) {
			return false;
		}
		if (!validDirection.equals(et.validDirection)) {
			return false;
		}
		if (validToEdgeRoles != null) {
			if (et.validToEdgeRoles == null) {
				return false;
			}
			if (!validToEdgeRoles.equals(et.validToEdgeRoles)) {
				return false;
			}
		} else {
			if (et.validToEdgeRoles != null) {
				return false;
			}
		}
		if (validFromEdgeRoles == null) {
			if (et.validFromEdgeRoles != null) {
				return false;
			}
		} else {
			if (et.validFromEdgeRoles == null) {
				return false;
			}
			if (!validFromEdgeRoles.equals(et.validFromEdgeRoles)) {
				return false;
			}
		}

		if (predicateEvaluator != null) {
			if (et.predicateEvaluator == null) {
				return false;
			}
			if (!predicateEvaluator.equals(et.predicateEvaluator)) {
				return false;
			}
		} else {
			if (et.predicateEvaluator != null) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Copy-constructor, creates a copy of the given transition
	 */
	protected SimpleTransition(SimpleTransition t, boolean addToStates) {
		super(t, addToStates);
		validDirection = t.validDirection;
		typeCollection = new TypeCollection(t.typeCollection);
		predicateEvaluator = t.predicateEvaluator;
		thisEdgeEvaluator = t.thisEdgeEvaluator;
		validToEdgeRoles = t.validToEdgeRoles;
		validFromEdgeRoles = t.validFromEdgeRoles;
	}

	/**
	 * returns a copy of this transition
	 */
	@Override
	public Transition copy(boolean addToStates) {
		return new SimpleTransition(this, addToStates);
	}

	/**
	 * Creates a new transition from start state to end state. The Transition
	 * accepts all edges that have the right direction, role, startVertexType,
	 * endVertexType, edgeType and even it's possible to define a specific edge.
	 * 
	 * @param start
	 *            The state where this transition starts
	 * @param end
	 *            The state where this transition ends
	 * @param dir
	 *            The direction of the accepted edges, may be EdeDirection.IN,
	 *            EdgeDirection.OUT or EdgeDirection.ANY
	 */
	public SimpleTransition(State start, State end, AllowedEdgeDirection dir) {
		super(start, end);
		validDirection = dir;
		typeCollection = new TypeCollection();
	}

	/**
	 * Creates a new transition from start state to end state. The Transition
	 * accepts all edges that have the right direction, role, startVertexType,
	 * endVertexType, edgeType and even it's possible to define a specific edge.
	 * This constructor creates a transition to accept a simplePathDescription
	 * 
	 * @param start
	 *            The state where this transition starts
	 * @param end
	 *            The state where this transition ends
	 * @param dir
	 *            The direction of the accepted edges, may be EdeDirection.IN,
	 *            EdgeDirection.OUT or EdgeDirection.ANY
	 * @param typeCollection
	 *            The types which restrict the possible edges
	 * @param roles
	 *            The set of accepted edge role names, or null if any role is
	 *            accepted
	 */
	public SimpleTransition(State start, State end, AllowedEdgeDirection dir,
			TypeCollection typeCollection, Set<String> roles,
			VertexEvaluator predicateEvaluator,
			GraphMarker<VertexEvaluator> graphMarker) {
		super(start, end);
		validDirection = dir;
		validToEdgeRoles = roles;
		validFromEdgeRoles = null;
		this.typeCollection = typeCollection;
		this.predicateEvaluator = predicateEvaluator;
		Vertex v = graphMarker.getGraph().getFirstVertex(ThisEdge.class);
		if (v != null) {
			thisEdgeEvaluator = (ThisEdgeEvaluator) graphMarker.getMark(v);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see greql2.evaluator.fa.Transition#reverse()
	 */
	@Override
	public void reverse() {
		super.reverse();
		if (validDirection == AllowedEdgeDirection.IN) {
			validDirection = AllowedEdgeDirection.OUT;
		} else if (validDirection == AllowedEdgeDirection.OUT) {
			validDirection = AllowedEdgeDirection.IN;
		}
		Set<String> tempSet = validFromEdgeRoles;
		validFromEdgeRoles = validToEdgeRoles;
		validToEdgeRoles = tempSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see greql2.evaluator.fa.Transition#isEpsilon()
	 */
	@Override
	public boolean isEpsilon() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see greql2.evaluator.fa.Transition#accepts(jgralab.Vertex, jgralab.Edge)
	 */
	@Override
	public boolean accepts(Vertex v, Edge e) {
		if (e == null) {
			return false;
		}
		if (validDirection == AllowedEdgeDirection.OUT) {
			if (!e.isNormal()) {
				return false;
			}
		} else if (validDirection == AllowedEdgeDirection.IN) {
			if (e.isNormal()) {
				return false;
			}
		}

		Set<String> validEdgeRoles = validToEdgeRoles;
		boolean checkToEdgeRoles = true;
		if (validEdgeRoles == null) {
			validEdgeRoles = validFromEdgeRoles;
			checkToEdgeRoles = false;
		}

		boolean rolesOnly = (validEdgeRoles != null)
				&& (typeCollection.getAllowedTypes().size() == 0)
				&& (typeCollection.getForbiddenTypes().size() == 0);
		boolean acceptedByRole = false;

		// checks if a role restriction is set and if e has the right role
		if (validEdgeRoles != null) {
			EdgeClass ec = e.getAttributedElementClass();
			Set<String> roles = null;
			if (e.isNormal() == checkToEdgeRoles) {
				roles = ec.getTo().getAllRoles();
			} else {
				roles = ec.getFrom().getAllRoles();
			}
			for (String role : roles) {
				if (validEdgeRoles.contains(role)) {
					acceptedByRole = true;
					break;
				}
			}
		}
		if (rolesOnly) {
			if (!acceptedByRole) {
				return false;
			}
		} else {
			if (!acceptedByRole) {
				EdgeClass edgeClass = e.getAttributedElementClass();
				if (!typeCollection.acceptsType(edgeClass)) {
					return false;
				}
			}
		}

		// checks if a boolean expression exists and if it evaluates to true
		if (predicateEvaluator != null) {
			if (thisEdgeEvaluator != null) {
				thisEdgeEvaluator.setValue(e);
			}
			Object res = predicateEvaluator.getResult();
			if (res instanceof Boolean) {
				return (Boolean) res;
			}
			return false;
		}
		return true;
	}

	/**
	 * returns the vertex of the datagraph which can be visited after this
	 * transition has fired. This is the vertex at the end of the edge
	 */
	@Override
	public Vertex getNextVertex(Vertex v, Edge e) {
		return e.getThat();
	}

	@Override
	public String prettyPrint() {
		StringBuilder b = new StringBuilder();
		String delim = "";
		for (AttributedElementClass<?, ?> c : typeCollection.getAllowedTypes()) {
			b.append(delim);
			b.append(c.getSimpleName());
			delim = ",";
		}
		String symbol = "<->";
		if (validDirection == AllowedEdgeDirection.IN) {
			symbol = "<--";
		} else if (validDirection == AllowedEdgeDirection.OUT) {
			symbol = "-->";
		}
		return symbol + "{" + b + "}";
	}

	@Override
	public boolean consumesEdge() {
		return true;
	}

}
