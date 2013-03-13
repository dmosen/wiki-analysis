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
/**
 * 
 */
package de.uni_koblenz.jgralab.greql2.serialising;

import java.util.Iterator;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.greql2.exception.GreqlException;
import de.uni_koblenz.jgralab.greql2.schema.AggregationPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.AlternativePathDescription;
import de.uni_koblenz.jgralab.greql2.schema.BackwardVertexSet;
import de.uni_koblenz.jgralab.greql2.schema.BoolLiteral;
import de.uni_koblenz.jgralab.greql2.schema.Comprehension;
import de.uni_koblenz.jgralab.greql2.schema.ConditionalExpression;
import de.uni_koblenz.jgralab.greql2.schema.Declaration;
import de.uni_koblenz.jgralab.greql2.schema.Definition;
import de.uni_koblenz.jgralab.greql2.schema.DefinitionExpression;
import de.uni_koblenz.jgralab.greql2.schema.Direction;
import de.uni_koblenz.jgralab.greql2.schema.DoubleLiteral;
import de.uni_koblenz.jgralab.greql2.schema.EdgePathDescription;
import de.uni_koblenz.jgralab.greql2.schema.EdgeRestriction;
import de.uni_koblenz.jgralab.greql2.schema.EdgeSetExpression;
import de.uni_koblenz.jgralab.greql2.schema.EdgeTypeSubgraph;
import de.uni_koblenz.jgralab.greql2.schema.ElementSetExpression;
import de.uni_koblenz.jgralab.greql2.schema.ExponentiatedPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.Expression;
import de.uni_koblenz.jgralab.greql2.schema.ExpressionDefinedSubgraph;
import de.uni_koblenz.jgralab.greql2.schema.ForwardVertexSet;
import de.uni_koblenz.jgralab.greql2.schema.FunctionApplication;
import de.uni_koblenz.jgralab.greql2.schema.FunctionId;
import de.uni_koblenz.jgralab.greql2.schema.Greql2;
import de.uni_koblenz.jgralab.greql2.schema.Greql2Expression;
import de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex;
import de.uni_koblenz.jgralab.greql2.schema.Identifier;
import de.uni_koblenz.jgralab.greql2.schema.IntLiteral;
import de.uni_koblenz.jgralab.greql2.schema.IntermediateVertexPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.IsExpressionOnSubgraph;
import de.uni_koblenz.jgralab.greql2.schema.IsSubgraphDefiningExpression;
import de.uni_koblenz.jgralab.greql2.schema.IsSubgraphDefinitionOf;
import de.uni_koblenz.jgralab.greql2.schema.IteratedPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.IterationType;
import de.uni_koblenz.jgralab.greql2.schema.LetExpression;
import de.uni_koblenz.jgralab.greql2.schema.ListComprehension;
import de.uni_koblenz.jgralab.greql2.schema.ListConstruction;
import de.uni_koblenz.jgralab.greql2.schema.ListRangeConstruction;
import de.uni_koblenz.jgralab.greql2.schema.Literal;
import de.uni_koblenz.jgralab.greql2.schema.MapComprehension;
import de.uni_koblenz.jgralab.greql2.schema.MapConstruction;
import de.uni_koblenz.jgralab.greql2.schema.OptionalPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.PathDescription;
import de.uni_koblenz.jgralab.greql2.schema.PathExistence;
import de.uni_koblenz.jgralab.greql2.schema.PathExpression;
import de.uni_koblenz.jgralab.greql2.schema.PrimaryPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.QuantifiedExpression;
import de.uni_koblenz.jgralab.greql2.schema.Quantifier;
import de.uni_koblenz.jgralab.greql2.schema.RecordConstruction;
import de.uni_koblenz.jgralab.greql2.schema.RecordElement;
import de.uni_koblenz.jgralab.greql2.schema.RoleId;
import de.uni_koblenz.jgralab.greql2.schema.SequentialPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.SetComprehension;
import de.uni_koblenz.jgralab.greql2.schema.SetConstruction;
import de.uni_koblenz.jgralab.greql2.schema.SimpleDeclaration;
import de.uni_koblenz.jgralab.greql2.schema.SimplePathDescription;
import de.uni_koblenz.jgralab.greql2.schema.StringLiteral;
import de.uni_koblenz.jgralab.greql2.schema.SubgraphDefinition;
import de.uni_koblenz.jgralab.greql2.schema.SubgraphRestrictedExpression;
import de.uni_koblenz.jgralab.greql2.schema.TableComprehension;
import de.uni_koblenz.jgralab.greql2.schema.ThisEdge;
import de.uni_koblenz.jgralab.greql2.schema.ThisVertex;
import de.uni_koblenz.jgralab.greql2.schema.TransposedPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.TupleConstruction;
import de.uni_koblenz.jgralab.greql2.schema.TypeId;
import de.uni_koblenz.jgralab.greql2.schema.UndefinedLiteral;
import de.uni_koblenz.jgralab.greql2.schema.ValueConstruction;
import de.uni_koblenz.jgralab.greql2.schema.Variable;
import de.uni_koblenz.jgralab.greql2.schema.VertexSetExpression;
import de.uni_koblenz.jgralab.greql2.schema.VertexTypeSubgraph;
import de.uni_koblenz.jgralab.greql2.schema.WhereExpression;

/**
 * @author Tassilo Horn &lt;horn@uni-koblenz.de&gt;
 * 
 */
public class GreqlSerializer {

	private StringBuffer sb = null;

	public static String serializeGraph(Greql2 greqlGraph) {
		GreqlSerializer s = new GreqlSerializer();
		return s.serializeGreqlVertex(greqlGraph.getFirstGreql2Expression());
	}

	public static String serializeVertex(Greql2Vertex v) {
		GreqlSerializer s = new GreqlSerializer();
		return s.serializeGreqlVertex(v);
	}

	public String serializeGreqlVertex(Greql2Vertex v) {
		sb = new StringBuffer();
		serializeGreql2Vertex(v, false);
		return sb.toString();
	}

	private void serializeGreql2Vertex(Greql2Vertex v, boolean addSpace) {
		if (v instanceof Declaration) {
			Declaration d = (Declaration) v;
			if (d.getFirstIsQuantifiedDeclOfIncidence() != null) {
				serializeDeclaration((Declaration) v, false);
			} else {
				serializeDeclaration((Declaration) v, true);
			}
		} else if (v instanceof Definition) {
			serializeDefinition((Definition) v);
		} else if (v instanceof Direction) {
			serializeDirection((Direction) v);
		} else if (v instanceof EdgeRestriction) {
			serializeEdgeRestriction((EdgeRestriction) v);
		} else if (v instanceof Greql2Expression) {
			serializeGreql2Expression((Greql2Expression) v);
		} else if (v instanceof Quantifier) {
			serializeQuantifier((Quantifier) v);
		} else if (v instanceof RecordElement) {
			serializeRecordElement((RecordElement) v);
		} else if (v instanceof SimpleDeclaration) {
			serializeSimpleDeclaration((SimpleDeclaration) v);
		} else if (v instanceof Expression) {
			serializeExpression((Expression) v, false);
		} else {
			throw new GreqlException("Unknown Greql2Vertex " + v + ".");
		}
		if (addSpace) {
			sb.append(' ');
		}
	}

	private void serializeSimpleDeclaration(SimpleDeclaration v) {
		boolean first = true;
		for (Variable var : v.get_declaredVar()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			serializeVariable(var);
		}
		sb.append(": ");
		serializeExpression(v.get_typeExpr(), false);
	}

	private void serializeRecordElement(RecordElement v) {
		serializeIdentifier(v.get_recordId());
		sb.append(" : ");
		serializeExpression(v.get_recordExpr(), false);
	}

	private void serializeQuantifier(Quantifier v) {
		switch (v.get_type()) {
		case EXISTS:
			sb.append("exists");
			break;
		case EXISTSONE:
			sb.append("exists!");
			break;
		case FORALL:
			sb.append("forall");
			break;
		default:
			throw new RuntimeException("FIXME: Unhandled QuantificationType: "
					+ v.get_type());
		}
	}

	private void serializeEdgeRestriction(EdgeRestriction v) {
		String delim = "";
		for (TypeId tid : v.get_typeId()) {
			sb.append(delim);
			serializeIdentifier(tid);
			delim = ",";
		}
		for (RoleId rid : v.get_roleId()) {
			sb.append(delim);
			delim = ",";
			serializeIdentifier(rid);
		}
		Expression predicate = v.get_booleanPredicate();
		if (predicate != null) {
			sb.append(" @ ");
			serializeExpression(predicate, false);
		}
	}

	private void serializeDirection(Direction v) {
		sb.append(v.get_dirValue());
	}

	private void serializeDefinition(Definition v) {
		serializeVariable(v.get_var());
		sb.append(" := ");
		serializeExpression(v.get_expr(), false);
	}

	private void serializeDeclaration(Declaration v, boolean declOfFWR) {
		boolean first = true;
		for (SimpleDeclaration sd : v.get_simpleDecl()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			serializeSimpleDeclaration(sd);
		}

		first = true;
		for (Expression constraint : v.get_constraint()) {
			if (declOfFWR) {
				sb.append(" with ");
			} else {
				// in QuantifiedExpressions, the constraints are separated with
				// comma
				sb.append(", ");
			}
			serializeExpression(constraint, false);
		}
	}

	private void serializeGreql2Expression(Greql2Expression greql2Expression) {
		Iterable<? extends Variable> boundVars = greql2Expression
				.get_boundVar();
		if (boundVars.iterator().hasNext()) {
			sb.append("using ");
			boolean first = true;
			for (Variable v : boundVars) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				serializeVariable(v);
			}
			sb.append(':');
		}

		// TODO: What's the Identifier which may be at the IsIdOf edge???

		serializeExpression(greql2Expression.get_queryExpr(), false);
	}

	private void serializeExpression(Expression exp, boolean addSpace) {
		if (exp instanceof ConditionalExpression) {
			serializeConditionalExpression((ConditionalExpression) exp);
		} else if (exp instanceof FunctionApplication) {
			serializeFunctionApplication((FunctionApplication) exp);
		} else if (exp instanceof Literal) {
			serializeLiteral((Literal) exp);
		} else if (exp instanceof Variable) {
			serializeVariable((Variable) exp);
		} else if (exp instanceof Identifier) {
			serializeIdentifier((Identifier) exp);
		} else if (exp instanceof QuantifiedExpression) {
			serializeQuantifiedExpression((QuantifiedExpression) exp);
		} else if (exp instanceof Comprehension) {
			serializeComprehension((Comprehension) exp);
		} else if (exp instanceof DefinitionExpression) {
			serializeDefinitionExpression((DefinitionExpression) exp);
		} else if (exp instanceof ElementSetExpression) {
			serializeElementSetExpression((ElementSetExpression) exp);
		} else if (exp instanceof PathDescription) {
			serializePathDescription((PathDescription) exp);
		} else if (exp instanceof PathExpression) {
			serializePathExpression((PathExpression) exp);
		} else if (exp instanceof SubgraphRestrictedExpression) {
			serializeSubgraphRestrictedExpression((SubgraphRestrictedExpression) exp);
		} else if (exp instanceof ValueConstruction) {
			serializeValueConstruction((ValueConstruction) exp);
		} else {
			throw new GreqlException("Unknown Expression " + exp
					+ ". Serialization so far: " + sb.toString());
		}
		if (addSpace) {
			sb.append(' ');
		}
	}

	private void serializeValueConstruction(ValueConstruction exp) {
		if (exp instanceof ListConstruction) {
			serializeListConstruction((ListConstruction) exp);
		} else if (exp instanceof MapConstruction) {
			serializeMapConstruction((MapConstruction) exp);
		} else if (exp instanceof RecordConstruction) {
			serializeRecordConstruction((RecordConstruction) exp);
		} else if (exp instanceof SetConstruction) {
			serializeSetConstruction((SetConstruction) exp);
		} else if (exp instanceof TupleConstruction) {
			serializeTupleConstruction((TupleConstruction) exp, false);
		} else {
			throw new GreqlException("Unknown ValueConstruction " + exp + ".");
		}
	}

	private void serializeTupleConstruction(TupleConstruction exp,
			boolean implicit) {
		if (!implicit) {
			sb.append("tup(");
		}
		boolean first = true;
		for (Expression val : exp.get_part()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			serializeExpression(val, false);
		}
		if (!implicit) {
			sb.append(")");
		}
	}

	private void serializeSetConstruction(SetConstruction exp) {
		sb.append("set(");
		boolean first = true;
		for (Expression val : exp.get_part()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			serializeExpression(val, false);
		}
		sb.append(")");
	}

	private void serializeRecordConstruction(RecordConstruction exp) {
		sb.append("rec(");
		boolean first = true;
		for (RecordElement re : exp.get_recordElement()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			serializeRecordElement(re);
		}
		sb.append(')');
	}

	private void serializeMapConstruction(MapConstruction exp) {
		sb.append("map(");
		Iterator<? extends Expression> vals = exp.get_valueExpr().iterator();
		String sep = "";
		for (Expression key : exp.get_keyExpr()) {
			sb.append(sep);
			sep = ", ";
			serializeExpression(key, true);
			sb.append("-> ");
			serializeExpression(vals.next(), false);
		}

		sb.append(")");
	}

	private void serializeListConstruction(ListConstruction exp) {
		sb.append("list(");
		if (exp instanceof ListRangeConstruction) {
			ListRangeConstruction lrc = (ListRangeConstruction) exp;
			serializeExpression(lrc.get_firstValue(), false);
			sb.append("..");
			serializeExpression(lrc.get_lastValue(), false);
		} else {
			boolean first = true;
			for (Expression val : exp.get_part()) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				serializeExpression(val, false);
			}
		}
		sb.append(")");
	}

	private void serializeSubgraphRestrictedExpression(
			SubgraphRestrictedExpression exp) {
		sb.append("on");
		// serialize left
		IsSubgraphDefinitionOf isSubgraphDefOf = exp
				.getFirstIsSubgraphDefinitionOfIncidence(EdgeDirection.IN);
		serializeSubgraphDefinition((SubgraphDefinition) isSubgraphDefOf
				.getThat());
		sb.append(":");
		// serialize right
		IsExpressionOnSubgraph isExprOnSubgraph = exp
				.getFirstIsExpressionOnSubgraphIncidence(EdgeDirection.IN);
		serializeExpression((Expression) isExprOnSubgraph.getThat(), true);
	}

	private void serializeSubgraphDefinition(SubgraphDefinition def) {

		if ((def instanceof EdgeTypeSubgraph)
				|| (def instanceof VertexTypeSubgraph)) {
			if (def instanceof EdgeTypeSubgraph) {
				sb.append("e");
			} else {
				sb.append("v");
			}
			sb.append("Subgraph{");
			boolean first = true;
			for (TypeId t : def.get_typeRestr()) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				serializeIdentifier(t);
			}
			sb.append('}');
		} else {
			// subgraph expression defined by arbitrary expression
			IsSubgraphDefiningExpression isDefExpr = ((ExpressionDefinedSubgraph) def)
					.getFirstIsSubgraphDefiningExpressionIncidence(EdgeDirection.IN);
			serializeExpression(isDefExpr.getAlpha(), true);
		}
	}

	private void serializePathExpression(PathExpression exp) {
		if (exp instanceof BackwardVertexSet) {
			serializeBackwardVertexSet((BackwardVertexSet) exp);
		} else if (exp instanceof ForwardVertexSet) {
			serializeForwardVertexSet((ForwardVertexSet) exp);
		} else if (exp instanceof PathExistence) {
			serializePathExistence((PathExistence) exp);
		} else {
			throw new GreqlException("Unknown PathExpression " + exp + ".");
		}
	}

	private void serializePathExistence(PathExistence exp) {
		serializeExpression(exp.get_startExpr(), true);
		serializeExpression(exp.get_path(), true);
		serializeExpression(exp.get_targetExpr(), false);
	}

	private void serializeForwardVertexSet(ForwardVertexSet exp) {
		serializeExpression(exp.get_startExpr(), true);
		serializeExpression(exp.get_path(), false);
	}

	private void serializeBackwardVertexSet(BackwardVertexSet exp) {
		serializeExpression(exp.get_path(), true);
		serializeExpression(exp.get_targetExpr(), false);
	}

	private void serializePathDescription(PathDescription exp) {
		if (!((exp instanceof PrimaryPathDescription) || (exp instanceof OptionalPathDescription))) {
			sb.append('(');
		}
		if (exp.get_startRestr() != null) {
			sb.append("{");
			serializeExpression(exp.get_startRestr(), false);
			sb.append("} & ");
		}

		if (exp instanceof AlternativePathDescription) {
			serializeAlternativePathDescription((AlternativePathDescription) exp);
		} else if (exp instanceof ExponentiatedPathDescription) {
			serializeExponentiatedPathDescription((ExponentiatedPathDescription) exp);
		} else if (exp instanceof IntermediateVertexPathDescription) {
			serializeIntermediateVertexPathDescription((IntermediateVertexPathDescription) exp);
		} else if (exp instanceof IteratedPathDescription) {
			serializeIteratedPathDescription((IteratedPathDescription) exp);
		} else if (exp instanceof OptionalPathDescription) {
			serializeOptionalPathDescription((OptionalPathDescription) exp);
		} else if (exp instanceof SequentialPathDescription) {
			serializeSequentialPathDescription((SequentialPathDescription) exp);
		} else if (exp instanceof TransposedPathDescription) {
			serializeTransposedPathDescription((TransposedPathDescription) exp);
		} else if (exp instanceof PrimaryPathDescription) {
			serializePrimaryPathDescription((PrimaryPathDescription) exp);
		} else {
			throw new GreqlException("Unknown PathDescription " + exp + ".");
		}

		if (exp.get_goalRestr() != null) {
			sb.append(" & {");
			serializeExpression(exp.get_goalRestr(), false);
			sb.append("}");
		}
		if (!((exp instanceof PrimaryPathDescription) || (exp instanceof OptionalPathDescription))) {
			sb.append(')');
		}
	}

	private void serializePrimaryPathDescription(PrimaryPathDescription exp) {
		if (exp instanceof EdgePathDescription) {
			serializeEdgePathDescription((EdgePathDescription) exp);
		} else if (exp instanceof SimplePathDescription) {
			serializeSimplePathDescription((SimplePathDescription) exp);
		} else if (exp instanceof AggregationPathDescription) {
			serializeAggregationPathDescription((AggregationPathDescription) exp);
		} else {
			throw new GreqlException("Unknown PrimaryPathDescription " + exp
					+ ".");
		}

		if (exp.get_edgeRestr().iterator().hasNext()) {
			sb.append("{");
			boolean first = true;
			for (EdgeRestriction er : exp.get_edgeRestr()) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				serializeEdgeRestriction(er);
			}
			sb.append("}");
		}
	}

	private void serializeAggregationPathDescription(
			AggregationPathDescription exp) {
		if (exp.is_outAggregation()) {
			sb.append("<>--");
		} else {
			sb.append("--<>");
		}
	}

	private void serializeSimplePathDescription(SimplePathDescription exp) {
		String dir = exp.get_direction().get_dirValue();
		if (dir.equals("out")) {
			sb.append("-->");
		} else if (dir.equals("in")) {
			sb.append("<--");
		} else {
			sb.append("<->");
		}
	}

	private void serializeEdgePathDescription(EdgePathDescription exp) {
		String dir = exp.get_direction().get_dirValue();
		if (dir.equals("out")) {
			sb.append("--");
			serializeExpression(exp.get_edgeExpr(), false);
			sb.append("->");
		} else if (dir.equals("in")) {
			sb.append("<-");
			serializeExpression(exp.get_edgeExpr(), false);
			sb.append("--");
		} else {
			sb.append("<-");
			serializeExpression(exp.get_edgeExpr(), false);
			sb.append("->");
		}
	}

	private void serializeTransposedPathDescription(
			TransposedPathDescription exp) {
		serializePathDescription(exp.get_transposedPath());
		sb.append("^T");
	}

	private void serializeSequentialPathDescription(
			SequentialPathDescription exp) {
		for (PathDescription pd : exp.get_sequenceElement()) {
			serializePathDescription(pd);
			sb.append(' ');
		}
	}

	private void serializeOptionalPathDescription(OptionalPathDescription exp) {
		sb.append('[');
		serializePathDescription(exp.get_optionalPath());
		sb.append(']');
	}

	private void serializeIteratedPathDescription(IteratedPathDescription exp) {
		serializePathDescription(exp.get_iteratedPath());
		sb.append(exp.get_times() == IterationType.STAR ? '*' : '+');
	}

	private void serializeIntermediateVertexPathDescription(
			IntermediateVertexPathDescription exp) {
		Iterator<? extends PathDescription> sub = exp.get_subPath().iterator();
		serializePathDescription(sub.next());
		serializeExpression(exp.get_intermediateVertex(), false);
		serializePathDescription(sub.next());
	}

	private void serializeExponentiatedPathDescription(
			ExponentiatedPathDescription exp) {
		serializePathDescription(exp.get_exponentiatedPath());
		sb.append('^');
		serializeLiteral(exp.get_exponent());
	}

	private void serializeAlternativePathDescription(
			AlternativePathDescription exp) {
		boolean first = true;
		for (PathDescription a : exp.get_alternatePath()) {
			if (first) {
				first = false;
			} else {
				sb.append(" | ");
			}
			serializePathDescription(a);
		}
	}

	private void serializeElementSetExpression(ElementSetExpression exp) {
		if (exp instanceof VertexSetExpression) {
			sb.append("V");
		} else if (exp instanceof EdgeSetExpression) {
			sb.append("E");
		} else {
			throw new GreqlException("Unknown ElementSetExpression " + exp
					+ ".");
		}

		Iterable<? extends TypeId> typeRestrictions = exp.get_typeRestr();

		if (!typeRestrictions.iterator().hasNext()) {
			return;
		}

		sb.append("{");
		String sep = "";
		for (TypeId t : typeRestrictions) {
			sb.append(sep);
			sep = ", ";
			serializeIdentifier(t);
		}
		sb.append("}");
	}

	private void serializeDefinitionExpression(DefinitionExpression exp) {
		if (exp instanceof LetExpression) {
			serializeLetExpression((LetExpression) exp);
		} else if (exp instanceof WhereExpression) {
			serializeWhereExpression((WhereExpression) exp);
		} else {
			throw new GreqlException("Unknown DefinitionExpression " + exp
					+ ".");
		}
	}

	private void serializeWhereExpression(WhereExpression exp) {
		serializeExpression(exp.get_boundExprOfDefinition(), true);
		sb.append("where ");
		boolean first = true;
		for (Definition def : exp.get_definition()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			serializeDefinition(def);
		}
	}

	private void serializeLetExpression(LetExpression exp) {
		sb.append("let ");
		boolean first = true;
		for (Definition def : exp.get_definition()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			serializeDefinition(def);
		}
		sb.append(" in ");
		serializeExpression(exp.get_boundExprOfDefinition(), true);
	}

	private void serializeComprehension(Comprehension exp) {
		sb.append("from ");
		serializeDeclaration(exp.get_compDecl(), true);
		if (exp instanceof SetComprehension) {
			sb.append(" reportSet ");
		} else if (exp instanceof ListComprehension) {
			sb.append(" report ");
		} else if (exp instanceof TableComprehension) {
			sb.append(" reportTable ");
		} else if (exp instanceof MapComprehension) {
			sb.append(" reportMap ");
			MapComprehension mc = (MapComprehension) exp;
			// MapComprehensions have no compResultDef, but key and valueExprs
			serializeExpression(mc.get_keyExpr(), false);
			sb.append(", ");
			serializeExpression(mc.get_valueExpr(), true);
			sb.append("end");
			return;
		} else {
			throw new GreqlException("Unknown Comprehension " + exp + ".");
		}

		Expression result = exp.get_compResultDef();

		if (result instanceof TupleConstruction) {
			// here the tup() can be omitted
			serializeTupleConstruction((TupleConstruction) result, true);
			sb.append(' ');
		} else {
			serializeExpression(result, true);
		}
		sb.append("end");
	}

	private void serializeQuantifiedExpression(QuantifiedExpression exp) {
		sb.append('(');
		serializeQuantifier(exp.get_quantifier());
		sb.append(' ');
		serializeDeclaration(exp.get_quantifiedDecl(), false);
		sb.append(" @ ");
		serializeExpression(exp.get_boundExprOfQuantifier(), false);
		sb.append(')');
	}

	private void serializeLiteral(Literal exp) {
		if (exp instanceof BoolLiteral) {
			sb.append(((BoolLiteral) exp).is_boolValue());
		} else if (exp instanceof IntLiteral) {
			sb.append(((IntLiteral) exp).get_intValue());
		} else if (exp instanceof UndefinedLiteral) {
			sb.append("undefined");
		} else if (exp instanceof DoubleLiteral) {
			sb.append(((DoubleLiteral) exp).get_doubleValue());
		} else if (exp instanceof StringLiteral) {
			sb.append("\"");
			sb.append(((StringLiteral) exp).get_stringValue());
			sb.append("\"");
		} else if (exp instanceof ThisEdge) {
			sb.append("thisEdge");
		} else if (exp instanceof ThisVertex) {
			sb.append("thisVertex");
		} else {
			throw new GreqlException("Unknown Literal " + exp + ".");
		}
	}

	private void serializeIdentifier(Identifier exp) {
		sb.append(exp.get_name());
	}

	private void serializeFunctionApplication(FunctionApplication exp) {
		FunctionId fid = exp.get_functionId();
		String id = fid.get_name();

		if (id.equals("add")) {
			serializeFunctionApplicationInfix(exp, "+");
			return;
		} else if (id.equals("sub")) {
			serializeFunctionApplicationInfix(exp, "-");
			return;
		} else if (id.equals("mul")) {
			serializeFunctionApplicationInfix(exp, "*");
			return;
		} else if (id.equals("div")) {
			serializeFunctionApplicationInfix(exp, "/");
			return;
		} else if (id.equals("equals")) {
			serializeFunctionApplicationInfix(exp, "=");
			return;
		} else if (id.equals("nequals")) {
			serializeFunctionApplicationInfix(exp, "<>");
			return;
		} else if (id.equals("grEqual")) {
			serializeFunctionApplicationInfix(exp, ">=");
			return;
		} else if (id.equals("grThan")) {
			serializeFunctionApplicationInfix(exp, ">");
			return;
		} else if (id.equals("leEqual")) {
			serializeFunctionApplicationInfix(exp, "<=");
			return;
		} else if (id.equals("leThan")) {
			serializeFunctionApplicationInfix(exp, "<");
			return;
		} else if (id.equals("reMatch")) {
			serializeFunctionApplicationInfix(exp, "=~");
			return;
		} else if (id.equals("mod")) {
			serializeFunctionApplicationInfix(exp, "%");
			return;
		} else if (id.equals("and")) {
			serializeFunctionApplicationInfix(exp, "and");
			return;
		} else if (id.equals("or")) {
			serializeFunctionApplicationInfix(exp, "or");
			return;
		} else if (id.equals("xor")) {
			serializeFunctionApplicationInfix(exp, "xor");
			return;
		} else if (id.equals("concat")) {
			serializeFunctionApplicationInfix(exp, "++");
			return;
		} else if (id.equals("getValue")) {
			serializeFunctionApplicationInfix(exp, ".");
			return;
		}

		serializeIdentifier(fid);
		sb.append('(');
		boolean first = true;
		for (Expression arg : exp.get_argument()) {
			if (first) {
				first = false;
			} else {
				sb.append(", ");
			}
			serializeExpression(arg, false);
		}
		sb.append(")");
	}

	private void serializeFunctionApplicationInfix(FunctionApplication exp,
			String operator) {
		sb.append("(");
		boolean first = true;
		for (Expression arg : exp.get_argument()) {
			if (first) {
				first = false;
			} else {
				// The DOT operator (myElem.myAttr) shouldn't have spaces
				// arround.
				if (operator.equals(".")) {
					sb.append(operator);
				} else {
					sb.append(' ').append(operator).append(' ');
				}
			}
			serializeExpression(arg, false);
		}
		sb.append(")");
	}

	private void serializeConditionalExpression(ConditionalExpression expression) {
		serializeExpression(expression.get_condition(), true);
		sb.append("? ");
		serializeExpression(expression.get_trueExpr(), true);
		sb.append(": ");
		serializeExpression(expression.get_falseExpr(), true);
	}

	private void serializeVariable(Variable v) {
		sb.append(v.get_name());
	}
}
