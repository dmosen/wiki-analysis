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
package de.uni_koblenz.jgralabtest.greql2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.codegenerator.CodeGeneratorConfiguration;
import de.uni_koblenz.jgralab.greql2.exception.ParsingException;
import de.uni_koblenz.jgralab.greql2.exception.UndefinedVariableException;
import de.uni_koblenz.jgralab.greql2.funlib.FunLib;
import de.uni_koblenz.jgralab.greql2.parser.GreqlParser;
import de.uni_koblenz.jgralab.greql2.schema.AggregationPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.AlternativePathDescription;
import de.uni_koblenz.jgralab.greql2.schema.BoolLiteral;
import de.uni_koblenz.jgralab.greql2.schema.ConditionalExpression;
import de.uni_koblenz.jgralab.greql2.schema.Declaration;
import de.uni_koblenz.jgralab.greql2.schema.DoubleLiteral;
import de.uni_koblenz.jgralab.greql2.schema.EdgePathDescription;
import de.uni_koblenz.jgralab.greql2.schema.EdgeRestriction;
import de.uni_koblenz.jgralab.greql2.schema.EdgeSetExpression;
import de.uni_koblenz.jgralab.greql2.schema.ForwardVertexSet;
import de.uni_koblenz.jgralab.greql2.schema.FunctionApplication;
import de.uni_koblenz.jgralab.greql2.schema.FunctionId;
import de.uni_koblenz.jgralab.greql2.schema.Greql2;
import de.uni_koblenz.jgralab.greql2.schema.Greql2Expression;
import de.uni_koblenz.jgralab.greql2.schema.IntLiteral;
import de.uni_koblenz.jgralab.greql2.schema.IntermediateVertexPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.IsAlternativePathOf;
import de.uni_koblenz.jgralab.greql2.schema.IsArgumentOf;
import de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf;
import de.uni_koblenz.jgralab.greql2.schema.IsCompDeclOf;
import de.uni_koblenz.jgralab.greql2.schema.IsCompResultDefOf;
import de.uni_koblenz.jgralab.greql2.schema.IsConstraintOf;
import de.uni_koblenz.jgralab.greql2.schema.IsEdgeExprOf;
import de.uni_koblenz.jgralab.greql2.schema.IsEdgeRestrOf;
import de.uni_koblenz.jgralab.greql2.schema.IsFirstValueOf;
import de.uni_koblenz.jgralab.greql2.schema.IsFunctionIdOf;
import de.uni_koblenz.jgralab.greql2.schema.IsGoalRestrOf;
import de.uni_koblenz.jgralab.greql2.schema.IsIntermediateVertexOf;
import de.uni_koblenz.jgralab.greql2.schema.IsLastValueOf;
import de.uni_koblenz.jgralab.greql2.schema.IsPartOf;
import de.uni_koblenz.jgralab.greql2.schema.IsPathOf;
import de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf;
import de.uni_koblenz.jgralab.greql2.schema.IsRecordElementOf;
import de.uni_koblenz.jgralab.greql2.schema.IsSequenceElementOf;
import de.uni_koblenz.jgralab.greql2.schema.IsSimpleDeclOf;
import de.uni_koblenz.jgralab.greql2.schema.IsStartExprOf;
import de.uni_koblenz.jgralab.greql2.schema.IsStartRestrOf;
import de.uni_koblenz.jgralab.greql2.schema.IsSubPathOf;
import de.uni_koblenz.jgralab.greql2.schema.IsTargetExprOf;
import de.uni_koblenz.jgralab.greql2.schema.IsTypeIdOf;
import de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfExpression;
import de.uni_koblenz.jgralab.greql2.schema.IteratedPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.ListComprehension;
import de.uni_koblenz.jgralab.greql2.schema.ListConstruction;
import de.uni_koblenz.jgralab.greql2.schema.ListRangeConstruction;
import de.uni_koblenz.jgralab.greql2.schema.PathExistence;
import de.uni_koblenz.jgralab.greql2.schema.QuantificationType;
import de.uni_koblenz.jgralab.greql2.schema.QuantifiedExpression;
import de.uni_koblenz.jgralab.greql2.schema.Quantifier;
import de.uni_koblenz.jgralab.greql2.schema.RecordConstruction;
import de.uni_koblenz.jgralab.greql2.schema.RecordElement;
import de.uni_koblenz.jgralab.greql2.schema.RecordId;
import de.uni_koblenz.jgralab.greql2.schema.RoleId;
import de.uni_koblenz.jgralab.greql2.schema.SequentialPathDescription;
import de.uni_koblenz.jgralab.greql2.schema.SetConstruction;
import de.uni_koblenz.jgralab.greql2.schema.SimpleDeclaration;
import de.uni_koblenz.jgralab.greql2.schema.SimplePathDescription;
import de.uni_koblenz.jgralab.greql2.schema.StringLiteral;
import de.uni_koblenz.jgralab.greql2.schema.ThisVertex;
import de.uni_koblenz.jgralab.greql2.schema.TypeId;
import de.uni_koblenz.jgralab.greql2.schema.Variable;
import de.uni_koblenz.jgralab.greql2.schema.VertexSetExpression;
import de.uni_koblenz.jgralab.schema.GraphClass;
import de.uni_koblenz.jgralab.schema.Schema;
import de.uni_koblenz.jgralab.schema.impl.SchemaImpl;
import de.uni_koblenz.jgralabtest.greql2.testfunctions.IsPrime;

/*
 * GReQL Constructs where discussions are needed
 *
 * Start & GoalRestriction:
 * Syntax --> & {AType} und --> & {ABooleanVarialbe} is identical, currently the parser requests to put
 * expressions other than TypeIds in parantheses
 *
 *
 *
 *
 * 
 *
 */

public class ParserTest {
	static {
		FunLib.register(IsPrime.class);
	}

	private Greql2 parseQuery(String query) throws ParsingException {
		return parseQuery(query, null);
	}

	private Greql2 parseQuery(String query, String file)
			throws ParsingException {
		Greql2 graph = GreqlParser.parse(query);
		if (file != null) {
			try {
				graph.save(file);
			} catch (GraphIOException ex) {
				throw new RuntimeException(
						"Error saving graph to file " + file, ex);
			}
		}

		return graph;
	}

	@Test
	public void testGreql2TestGraph() throws ParsingException {
		parseQuery("from i:c report i end where d:=\"drölfundfünfzig\", c:=b, b:=a, a:=\"Mensaessen\"");
	}

	@Test
	public void testWhereWithSameScope() throws ParsingException {
		Greql2 graph = parseQuery("from a,b:V with connected report a,b end where connected := a-->b");

		Map<String, Variable> map = new HashMap<String, Variable>();

		for (Variable v : graph.getVariableVertices()) {
			map.put(v.get_name(), v);
		}

		String[] validVariables = { "a", "b" };
		for (String validVariable : validVariables) {
			Variable variable = map.get(validVariable);
			assertNotNull(variable);
			map.remove(validVariable);
		}
		assertTrue(map.isEmpty());
	}

	@Test
	public void testExistsOne() throws Exception {
		Greql2 graph = parseQuery("exists! x:list(1..5) @ x = 5");
		Quantifier quantifier = graph.getFirstQuantifier();
		assertEquals(QuantificationType.EXISTSONE, quantifier.get_type());
		quantifier = quantifier.getNextQuantifier();
		assertNull(quantifier);
	}

	@Test
	public void testOrExpression() throws Exception {
		Greql2 graph = parseQuery("true or false");
		FunctionApplication funAp = graph.getFirstFunctionApplication();
		assertNotNull(funAp);
		IsFunctionIdOf isIdOf = funAp.getFirstIsFunctionIdOfIncidence();
		assertNotNull(isIdOf);
		FunctionId funId = isIdOf.getAlpha();
		assertEquals("or", funId.get_name());
	}

	@Test
	public void testAndExpression() throws Exception {
		Greql2 graph = parseQuery("true and false");
		FunctionApplication funAp = graph.getFirstFunctionApplication();
		assertNotNull(funAp);
		IsFunctionIdOf isIdOf = funAp.getFirstIsFunctionIdOfIncidence();
		assertNotNull(isIdOf);
		FunctionId funId = isIdOf.getAlpha();
		assertEquals("and", funId.get_name());
	}

	@Test
	public void testXorExpression() throws Exception {
		Greql2 graph = parseQuery("true xor false");
		FunctionApplication funAp = graph.getFirstFunctionApplication();
		assertNotNull(funAp);
		IsFunctionIdOf isIdOf = funAp.getFirstIsFunctionIdOfIncidence();
		assertNotNull(isIdOf);
		FunctionId funId = isIdOf.getAlpha();
		assertEquals("xor", funId.get_name());
	}

	@Test
	public void testEqualityExpression() throws Exception {
		Greql2 graph = parseQuery("true = false");
		FunctionApplication funAp = graph.getFirstFunctionApplication();
		assertNotNull(funAp);
		IsFunctionIdOf isIdOf = funAp.getFirstIsFunctionIdOfIncidence();
		assertNotNull(isIdOf);
		FunctionId funId = isIdOf.getAlpha();
		assertEquals("equals", funId.get_name());
	}

	@Test
	public void testMatchExpression() throws Exception {
		Greql2 graph = parseQuery("true =~ false");
		FunctionApplication funAp = graph.getFirstFunctionApplication();
		assertNotNull(funAp);
		IsFunctionIdOf isIdOf = funAp.getFirstIsFunctionIdOfIncidence();
		assertNotNull(isIdOf);
		FunctionId funId = isIdOf.getAlpha();
		assertEquals("reMatch", funId.get_name());
	}

	@Test
	public void testIdentifierWithDollar() throws Exception {
		Greql2 graph = parseQuery("from $i : V{} report $i end");
		Variable v = graph.getFirstVariable();
		assertNotNull(v);
		assertEquals("$i", v.get_name());
	}

	@Test
	public void testIdentifierWithDollar2() throws Exception {
		Greql2 graph = parseQuery("using $: from i:$ report i end");
		Variable v = graph.getFirstVariable();
		assertNotNull(v);
		assertEquals("$", v.get_name());
	}

	@Test
	public void testTypeId() throws Exception {
		Greql2 graph = parseQuery("V{Part!}");
		TypeId t = graph.getFirstTypeId();
		assertNotNull(t);
		assertEquals("Part", t.get_name());
		assertTrue(t.is_type());
		assertFalse(t.is_excluded());
	}

	@Test
	public void testTypeId2() throws Exception {
		Greql2 graph = parseQuery("V{^Part!}");
		TypeId t = graph.getFirstTypeId();
		assertNotNull(t);
		assertEquals("Part", t.get_name());
		assertTrue(t.is_type());
		assertTrue(t.is_excluded());
	}

	@Test
	public void testTypeId3() throws Exception {
		Greql2 graph = parseQuery("V{^Part}");
		TypeId t = graph.getFirstTypeId();
		assertNotNull(t);
		assertEquals("Part", t.get_name());
		assertFalse(t.is_type());
		assertTrue(t.is_excluded());
	}

	@Test
	public void testNotEqualExpression() throws Exception {
		Greql2 graph = parseQuery("true <> false");
		FunctionApplication funAp = graph.getFirstFunctionApplication();
		assertNotNull(funAp);
		IsFunctionIdOf isIdOf = funAp.getFirstIsFunctionIdOfIncidence();
		assertNotNull(isIdOf);
		FunctionId funId = isIdOf.getAlpha();
		assertEquals("nequals", funId.get_name());
	}

	@Test
	public void testLessThanExpression() throws Exception {
		Greql2 graph = parseQuery("5 < 6");
		FunctionApplication funAp = graph.getFirstFunctionApplication();
		assertNotNull(funAp);
		IsFunctionIdOf isIdOf = funAp.getFirstIsFunctionIdOfIncidence();
		assertNotNull(isIdOf);
		FunctionId funId = isIdOf.getAlpha();
		assertEquals("leThan", funId.get_name());
	}

	@Test
	public void testLessOrEqualExpression() throws Exception {
		Greql2 graph = parseQuery("5 <= 6");
		FunctionApplication funAp = graph.getFirstFunctionApplication();
		assertNotNull(funAp);
		IsFunctionIdOf isIdOf = funAp.getFirstIsFunctionIdOfIncidence();
		assertNotNull(isIdOf);
		FunctionId funId = isIdOf.getAlpha();
		assertEquals("leEqual", funId.get_name());
	}

	@Test
	public void testBooleanLiteral() throws Exception {
		Greql2 graph = parseQuery("true");
		BoolLiteral lit = graph.getFirstBoolLiteral();
		assertNotNull(lit);
		assertTrue(lit.is_boolValue());
		graph = parseQuery("false");
		lit = graph.getFirstBoolLiteral();
		assertNotNull(lit);
		assertFalse(lit.is_boolValue());
	}

	@Test
	public void testIntegerLiteral() throws Exception {
		Greql2 graph = parseQuery("5");
		IntLiteral lit = graph.getFirstIntLiteral();
		assertNotNull(lit);
		assertEquals(5, lit.get_intValue());
	}

	@Test
	public void testHexLiteral() throws Exception {
		Greql2 graph = parseQuery("0x5");
		IntLiteral lit = graph.getFirstIntLiteral();
		assertNotNull(lit);
		assertEquals(5, lit.get_intValue());
		graph = parseQuery("0xA");
		lit = graph.getFirstIntLiteral();
		assertNotNull(lit);
		assertEquals(10, lit.get_intValue());
	}

	@Test
	public void testOctLiteral() throws Exception {
		Greql2 graph = parseQuery("05");
		IntLiteral lit = graph.getFirstIntLiteral();
		assertNotNull(lit);
		assertEquals(5, lit.get_intValue());
		graph = parseQuery("011");
		lit = graph.getFirstIntLiteral();
		assertNotNull(lit);
		assertEquals(9, lit.get_intValue());
	}

	@Test
	public void testDoubleLiteral() throws Exception {
		assertDoubleLiteralEquals("5.0", 5.0);
		assertDoubleLiteralEquals("0.5", 0.5);
	}

	static final double DELTA = 0.00000001;

	public void assertDoubleLiteralEquals(String literal, double expectedValue) {
		Greql2 graph = parseQuery(literal);
		DoubleLiteral lit = graph.getFirstDoubleLiteral();
		Double value = lit.get_doubleValue();
		assertNotNull(value);
		assertEquals(expectedValue, value, DELTA);
	}

	@Test
	public void testUnaryExpressionNeg() throws Exception {
		Greql2 graph = parseQuery("-5");
		FunctionApplication funAp = graph.getFirstFunctionApplication();
		assertNotNull(funAp);
		IsFunctionIdOf isIdOf = funAp.getFirstIsFunctionIdOfIncidence();
		assertNotNull(isIdOf);
		FunctionId funId = isIdOf.getAlpha();
		assertEquals("neg", funId.get_name());
	}

	@Test
	public void testUnaryExpressionNot() throws Exception {
		Greql2 graph = parseQuery("not true");
		FunctionApplication funAp = graph.getFirstFunctionApplication();
		assertNotNull(funAp);
		IsFunctionIdOf isIdOf = funAp.getFirstIsFunctionIdOfIncidence();
		assertNotNull(isIdOf);
		FunctionId funId = isIdOf.getAlpha();
		assertEquals("not", funId.get_name());
	}

	@Test
	public void testListConstruction() throws Exception {
		Greql2 graph = parseQuery("list(10,11,12,13)");
		ListConstruction constr = graph.getFirstListConstruction();
		assertNotNull(constr);
		assertEquals(4, constr.getDegree(IsPartOf.class));
	}

	@Test
	public void testGreTLQuery() throws Exception {
		String query = "from t : V{Vertex}    " + "report t --> "
				+ "     & {@hasType(thisVertex, \"MyType\")} " + "end";
		Greql2 graph = parseQuery(query);
		assertNotNull(graph);
		ThisVertex tv = graph.getFirstThisVertex();
		assertNotNull(tv);
		IsArgumentOf argOf = tv
				.getFirstIsArgumentOfIncidence(EdgeDirection.OUT);
		assertNotNull(argOf);
		assertEquals(graph.getFirstFunctionApplication(), argOf.getOmega());
		StringLiteral sl = graph.getFirstStringLiteral();
		assertNotNull(sl);
		assertEquals("MyType", sl.get_stringValue());
		argOf = sl.getFirstIsArgumentOfIncidence(EdgeDirection.OUT);
		assertNotNull(argOf);
		assertEquals(graph.getFirstFunctionApplication(), argOf.getOmega());
		tv = tv.getNextThisVertex();
		assertNull(tv);
	}

	@Test
	public void testRole() throws Exception {
		String queryString = "from var: V{Variable} report <>--{undefinedRole} end";
		Greql2 graph = parseQuery(queryString);
		assertNotNull(graph);
		RoleId id = graph.getFirstRoleId();
		assertNotNull(id);
		assertEquals("undefinedRole", id.get_name());
		AggregationPathDescription agg = graph
				.getFirstAggregationPathDescription();
		assertNotNull(agg);
		Edge e = id.getFirstIsRoleIdOfIncidence();
		assertNotNull(e);
		EdgeRestriction er = (EdgeRestriction) e.getOmega();
		IsEdgeRestrOf erof = er.getFirstIsEdgeRestrOfIncidence();
		assertNotNull(erof);
		assertEquals(agg, erof.getOmega());
	}

	@Test
	public void testListRangeConstruction() throws Exception {
		Greql2 graph = parseQuery("list(10..13)");
		ListRangeConstruction constr = graph.getFirstListRangeConstruction();
		assertNotNull(constr);
		IsFirstValueOf firstValueEdge = constr
				.getFirstIsFirstValueOfIncidence(EdgeDirection.IN);
		assertNotNull(firstValueEdge);
		IntLiteral firstValue = (IntLiteral) firstValueEdge.getAlpha();
		assertEquals(10, firstValue.get_intValue());
		IsLastValueOf lastValueEdge = constr
				.getFirstIsLastValueOfIncidence(EdgeDirection.IN);
		assertNotNull(lastValueEdge);
		IntLiteral lastValue = (IntLiteral) lastValueEdge.getAlpha();
		assertEquals(13, lastValue.get_intValue());
	}

	@Test
	public void testSetConstruction() throws Exception {
		Greql2 graph = parseQuery("set(10,11,12,13)");
		SetConstruction constr = graph.getFirstSetConstruction();
		assertNotNull(constr);
		assertEquals(4, constr.getDegree(IsPartOf.class));
	}

	@Test
	public void testRecordConstruction() throws Exception {
		Greql2 graph = parseQuery("rec(a:5,b:\"Yes\")");
		RecordConstruction constr = graph.getFirstRecordConstruction();
		assertNotNull(constr);
		IsRecordElementOf recElemEdge = constr
				.getFirstIsRecordElementOfIncidence();
		RecordElement elem = recElemEdge.getAlpha();
		RecordId recId = elem.getFirstIsRecordIdOfIncidence().getAlpha();
		assertEquals("a", recId.get_name());
		Vertex recExpr = elem.getFirstIsRecordExprOfIncidence().getAlpha();
		assertEquals(5, ((IntLiteral) recExpr).get_intValue());
	}

	@Test
	public void testForallExpression() throws Exception {
		Greql2 graph = parseQuery("forall v:set(1,2,3) @ v < 7");
		Variable var = graph.getFirstVariable();
		assertNotNull(var);
		assertEquals("v", var.get_name());
		QuantifiedExpression expr = graph.getFirstQuantifiedExpression();
		assertNotNull(expr);
		Quantifier quantifier = expr.getFirstIsQuantifierOfIncidence()
				.getAlpha();
		assertNotNull(quantifier);
		assertEquals(QuantificationType.FORALL, quantifier.get_type());
	}

	@Test
	public void testExistsExpression() throws Exception {
		Greql2 graph = parseQuery("exists v:set(1,2,3) @ v < 7");
		Variable var = graph.getFirstVariable();
		assertNotNull(var);
		assertEquals("v", var.get_name());
		QuantifiedExpression expr = graph.getFirstQuantifiedExpression();
		assertNotNull(expr);
		Quantifier quantifier = expr.getFirstIsQuantifierOfIncidence()
				.getAlpha();
		assertNotNull(quantifier);
		assertEquals(QuantificationType.EXISTS, quantifier.get_type());
	}

	@Test
	public void testExistsOneExpression() throws Exception {
		Greql2 graph = parseQuery("exists! v:set(1,2,3) @ v < 7");
		Variable var = graph.getFirstVariable();
		assertNotNull(var);
		assertEquals("v", var.get_name());
		QuantifiedExpression expr = graph.getFirstQuantifiedExpression();
		assertNotNull(expr);
		Quantifier quantifier = expr.getFirstIsQuantifierOfIncidence()
				.getAlpha();
		assertNotNull(quantifier);
		assertEquals(QuantificationType.EXISTSONE, quantifier.get_type());
	}

	// @Test
	// public void testExistsOneQuantifier() throws Exception {
	// ManualGreql2Parser parser = getParser("exists!");
	// parser.initialize();
	// try {
	// Quantifier q = parser.quantifier();
	// assertNotNull(q);
	// } catch (RecognitionException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	@Test
	public void testVariableList() throws Exception {
		Greql2 graph = parseQuery("from a,b,c,d:V report a end");
		Variable var = graph.getFirstVariable();
		assertNotNull(var);
		assertEquals("a", var.get_name());
		var = var.getNextVariable();
		assertNotNull(var);
		assertEquals("b", var.get_name());
		var = var.getNextVariable();
		assertNotNull(var);
		assertEquals("c", var.get_name());
		var = var.getNextVariable();
		assertNotNull(var);
		assertEquals("d", var.get_name());
	}

	@Test
	public void testSimpleQueryWithConstraint() throws Exception {
		Greql2 graph = parseQuery("from var: V with isPrime(var) report var end");
		Variable var = graph.getFirstVariable();
		assertNotNull(var);
		assertEquals("var", var.get_name());
		ListComprehension comp = graph.getFirstListComprehension();
		assertNotNull(comp);
		IsCompDeclOf declEdge = comp.getFirstIsCompDeclOfIncidence();
		assertNotNull(declEdge);
		Declaration decl = declEdge.getAlpha();
		IsConstraintOf constraintEdge = decl.getFirstIsConstraintOfIncidence();
		assertNotNull(constraintEdge);
		FunctionApplication funAp = (FunctionApplication) constraintEdge
				.getAlpha();
		FunctionId funId = funAp.getFirstIsFunctionIdOfIncidence().getAlpha();
		assertEquals("isPrime", funId.get_name());
	}

	@Test
	public void testSimpleQueryWithMultipleDeclarations() throws Exception {
		Greql2 graph = parseQuery("from var: V{Definition}, def: V{WhereExpression} report var end");
		Variable var = graph.getFirstVariable();
		assertNotNull(var);
		assertEquals("var", var.get_name());
		var = var.getNextVariable();
		assertNotNull(var);
		assertEquals("def", var.get_name());
		ListComprehension comp = graph.getFirstListComprehension();
		assertNotNull(comp);
		IsCompDeclOf declEdge = comp.getFirstIsCompDeclOfIncidence();
		assertNotNull(declEdge);
		Declaration decl = declEdge.getAlpha();
		/* testing first simple declaration var:V{Definition} */
		IsSimpleDeclOf simpleDeclEdge = decl.getFirstIsSimpleDeclOfIncidence();
		assertNotNull(simpleDeclEdge);
		SimpleDeclaration simpleDecl = simpleDeclEdge.getAlpha();
		var = simpleDecl.getFirstIsDeclaredVarOfIncidence().getAlpha();
		assertEquals("var", var.get_name());
		VertexSetExpression vset = (VertexSetExpression) simpleDecl
				.getFirstIsTypeExprOfIncidence().getAlpha();
		IsTypeRestrOfExpression typeRestrEdge = vset
				.getFirstIsTypeRestrOfExpressionIncidence();
		assertNotNull(typeRestrEdge);
		TypeId typeId = typeRestrEdge.getAlpha();
		assertEquals("Definition", typeId.get_name());
		/* testing second simple declaration def:V{WhereExpression} */
		simpleDeclEdge = simpleDeclEdge.getNextIsSimpleDeclOfIncidence();
		assertNotNull(simpleDeclEdge);
		simpleDecl = simpleDeclEdge.getAlpha();
		var = simpleDecl.getFirstIsDeclaredVarOfIncidence().getAlpha();
		assertEquals("def", var.get_name());
		vset = (VertexSetExpression) simpleDecl.getFirstIsTypeExprOfIncidence()
				.getAlpha();
		typeRestrEdge = vset.getFirstIsTypeRestrOfExpressionIncidence();
		assertNotNull(typeRestrEdge);
		typeId = typeRestrEdge.getAlpha();
		assertEquals("WhereExpression", typeId.get_name());
	}

	@Test
	public void testSimpleQuery1() throws Exception {
		Greql2 graph = parseQuery("from var: V{Definition}, def: V{WhereExpression} with var -->{IsDefinitionOf} | -->{IsVarOf}  def report var end");
		ListComprehension comp = graph.getFirstListComprehension();
		assertNotNull(comp);
		IsCompDeclOf declEdge = comp.getFirstIsCompDeclOfIncidence();
		assertNotNull(declEdge);
		Declaration decl = declEdge.getAlpha();
		/* testing first simple declaration var:V{Definition} */
		IsSimpleDeclOf simpleDeclEdge = decl.getFirstIsSimpleDeclOfIncidence();
		assertNotNull(simpleDeclEdge);
		SimpleDeclaration simpleDecl = simpleDeclEdge.getAlpha();
		Variable var = simpleDecl.getFirstIsDeclaredVarOfIncidence().getAlpha();
		assertEquals("var", var.get_name());
		VertexSetExpression vset = (VertexSetExpression) simpleDecl
				.getFirstIsTypeExprOfIncidence().getAlpha();
		IsTypeRestrOfExpression typeRestrEdge = vset
				.getFirstIsTypeRestrOfExpressionIncidence();
		assertNotNull(typeRestrEdge);
		TypeId typeId = typeRestrEdge.getAlpha();
		assertEquals("Definition", typeId.get_name());
		/* testing second simple declaration def:V{WhereExpression} */
		simpleDeclEdge = simpleDeclEdge.getNextIsSimpleDeclOfIncidence();
		assertNotNull(simpleDeclEdge);
		simpleDecl = simpleDeclEdge.getAlpha();
		var = simpleDecl.getFirstIsDeclaredVarOfIncidence().getAlpha();
		assertEquals("def", var.get_name());
		vset = (VertexSetExpression) simpleDecl.getFirstIsTypeExprOfIncidence()
				.getAlpha();
		typeRestrEdge = vset.getFirstIsTypeRestrOfExpressionIncidence();
		assertNotNull(typeRestrEdge);
		typeId = typeRestrEdge.getAlpha();
		assertEquals("WhereExpression", typeId.get_name());
		IsConstraintOf constraintEdge = decl.getFirstIsConstraintOfIncidence();
		assertNotNull(constraintEdge);
		PathExistence constraint = (PathExistence) constraintEdge.getAlpha();
		IsStartExprOf startEdge = constraint.getFirstIsStartExprOfIncidence();
		assertNotNull(startEdge);
		var = (Variable) startEdge.getAlpha();
		assertEquals("var", var.get_name());
		IsTargetExprOf targetEdge = constraint
				.getFirstIsTargetExprOfIncidence();
		assertNotNull(targetEdge);
		var = (Variable) targetEdge.getAlpha();
		IsPathOf pathOfEdge = constraint.getFirstIsPathOfIncidence();
		assertNotNull(pathOfEdge);
		AlternativePathDescription pathDescr = (AlternativePathDescription) pathOfEdge
				.getAlpha();
		IsAlternativePathOf altEdge = pathDescr
				.getFirstIsAlternativePathOfIncidence();
		assertNotNull(altEdge);
		SimplePathDescription simplePath = (SimplePathDescription) altEdge
				.getAlpha();
		EdgeRestriction edgeRestr = simplePath.getFirstIsEdgeRestrOfIncidence()
				.getAlpha();
		typeId = edgeRestr.getFirstIsTypeIdOfIncidence().getAlpha();
		assertEquals("IsDefinitionOf", typeId.get_name());
		altEdge = altEdge.getNextIsAlternativePathOfIncidence();
		assertNotNull(altEdge);
		simplePath = (SimplePathDescription) altEdge.getAlpha();
		edgeRestr = simplePath.getFirstIsEdgeRestrOfIncidence().getAlpha();
		typeId = edgeRestr.getFirstIsTypeIdOfIncidence().getAlpha();
		assertEquals("IsVarOf", typeId.get_name());
		IsCompResultDefOf resultEdge = comp
				.getFirstIsCompResultDefOfIncidence(EdgeDirection.IN);
		var = (Variable) resultEdge.getAlpha();
		assertEquals("var", var.get_name());

	}

	@Test
	public void testSimpleQuery2() throws Exception {
		Greql2 graph = parseQuery("using FOO: from i: toSet(FOO) report i end");
		Greql2Expression root = graph.getFirstGreql2Expression();
		assertNotNull(root);
		IsBoundVarOf boundVarEdge = root.getFirstIsBoundVarOfIncidence();
		assertNotNull(boundVarEdge);
		Variable boundVar = boundVarEdge.getAlpha();
		assertEquals("FOO", boundVar.get_name());

		ListComprehension comp = (ListComprehension) graph
				.getFirstIsQueryExprOf().getAlpha();
		assertNotNull(comp);
		IsCompDeclOf declEdge = comp.getFirstIsCompDeclOfIncidence();
		assertNotNull(declEdge);
		Declaration decl = declEdge.getAlpha();
		/* testing first simple declaration var:V{Definition} */
		IsSimpleDeclOf simpleDeclEdge = decl.getFirstIsSimpleDeclOfIncidence();
		assertNotNull(simpleDeclEdge);
		SimpleDeclaration simpleDecl = simpleDeclEdge.getAlpha();
		Variable var = simpleDecl.getFirstIsDeclaredVarOfIncidence().getAlpha();
		assertEquals("i", var.get_name());
		FunctionApplication funAp = (FunctionApplication) simpleDecl
				.getFirstIsTypeExprOfIncidence().getAlpha();
		FunctionId funId = funAp.getFirstIsFunctionIdOfIncidence().getAlpha();
		assertEquals("toSet", funId.get_name());
		var = (Variable) funAp.getFirstIsArgumentOfIncidence().getAlpha();
		assertEquals("FOO", var.get_name());
		assertEquals(boundVar, var);
	}

	@Test
	public void testUsing() throws Exception {
		Greql2 graph = parseQuery("using A: from b:A report b end");
		Greql2Expression root = graph.getFirstGreql2Expression();
		assertNotNull(root);
		IsBoundVarOf boundVarEdge = root.getFirstIsBoundVarOfIncidence();
		assertNotNull(boundVarEdge);
		Variable var = boundVarEdge.getAlpha();
		assertEquals("A", var.get_name());
	}

	@Test
	public void testDefinitionList() throws Exception {
		parseQuery("let a:=7, b:=5 in a+b");
	}

	@Test
	public void testEvaluateListAccess() throws Exception {
		String queryString = "let x := list ( \"bratwurst\", \"currywurst\") in from i:V{Identifier} report x[3] end";
		parseQuery(queryString);

	}

	@Test
	public void testDoubleIdentifiers() throws Exception {
		String queryString = "let COL := list(1..10) in " + "( "
				+ " from i:COL report 'a' end " + " ++"
				+ " from i:COL report 'b' end " + ")";
		parseQuery(queryString);
	}

	@Test
	public void testDoubleIdentifiers2() throws Exception {
		String queryString = "from i:list(1..10) report 'a' end " + "++"
				+ "from i:list(1..10) report 'b' end";
		parseQuery(queryString);
	}

	@Test
	public void testLetExpression2() throws Exception {
		Greql2 graph = parseQuery("let x:= list (5..13) in count(x)");
		assertNotNull(graph);
	}

	@Test
	public void testFRQuery() throws Exception {
		Greql2 graph = parseQuery("from v:V report v end");
		Variable var = graph.getFirstVariable();
		assertNotNull(var);
		assertEquals("v", var.get_name());
		VertexSetExpression expr = graph.getFirstVertexSetExpression();
		assertNotNull(expr);
	}

	@Test
	public void testFunctionApplication() throws Exception {
		Greql2 graph = parseQuery("from v:V report degree(v) end");
		Variable var = graph.getFirstVariable();
		assertNotNull(var);
		assertEquals("v", var.get_name());
		VertexSetExpression expr = graph.getFirstVertexSetExpression();
		assertNotNull(expr);
		FunctionApplication funAp = graph.getFirstFunctionApplication();
		assertNotNull(funAp);
		FunctionId funId = funAp.getFirstIsFunctionIdOfIncidence().getAlpha();
		assertEquals("degree", funId.get_name());
	}

	@Test
	public void testConditionalExpression() throws Exception {
		Greql2 graph = parseQuery("1=2 ? true : false");
		ConditionalExpression condExpr = graph.getFirstConditionalExpression();
		assertNotNull(condExpr);
		FunctionApplication condition = (FunctionApplication) condExpr
				.getFirstIsConditionOfIncidence().getAlpha();
		assertNotNull(condition);
		FunctionId conditionId = condition.getFirstIsFunctionIdOfIncidence()
				.getAlpha();
		assertNotNull(conditionId);
		assertEquals("equals", conditionId.get_name());
		IntLiteral arg1 = (IntLiteral) condition
				.getFirstIsArgumentOfIncidence().getAlpha();
		IntLiteral arg2 = (IntLiteral) condition
				.getFirstIsArgumentOfIncidence().getNextIsArgumentOfIncidence()
				.getAlpha();
		assertEquals(1, arg1.get_intValue());
		assertEquals(2, arg2.get_intValue());
		BoolLiteral trueExpression = (BoolLiteral) condExpr
				.getFirstIsTrueExprOfIncidence().getAlpha();
		assertNotNull(trueExpression);
		assertTrue(trueExpression.is_boolValue());
		BoolLiteral falseExpression = (BoolLiteral) condExpr
				.getFirstIsFalseExprOfIncidence().getAlpha();
		assertNotNull(falseExpression);
		assertFalse(falseExpression.is_boolValue());
	}

	@Test
	public void testConditionalExpression2() throws Exception {
		Greql2 graph = parseQuery("1=1?1:2");
		Greql2Expression root = graph.getFirstGreql2Expression();
		assertNotNull(root);
		IsQueryExprOf queryEdge = root
				.getFirstIsQueryExprOfIncidence(EdgeDirection.IN);
		assertNotNull(queryEdge);
		ConditionalExpression condExpr = (ConditionalExpression) queryEdge
				.getAlpha();
		assertNotNull(condExpr);
		FunctionApplication condition = (FunctionApplication) condExpr
				.getFirstIsConditionOfIncidence().getAlpha();
		assertNotNull(condition);
		FunctionId conditionId = condition.getFirstIsFunctionIdOfIncidence()
				.getAlpha();
		assertNotNull(conditionId);
		assertEquals("equals", conditionId.get_name());
		IntLiteral arg1 = (IntLiteral) condition
				.getFirstIsArgumentOfIncidence().getAlpha();
		IntLiteral arg2 = (IntLiteral) condition
				.getFirstIsArgumentOfIncidence().getNextIsArgumentOfIncidence()
				.getAlpha();
		assertEquals(1, arg1.get_intValue());
		assertEquals(1, arg2.get_intValue());
		IntLiteral trueExpression = (IntLiteral) condExpr
				.getFirstIsTrueExprOfIncidence().getAlpha();
		assertEquals(1, trueExpression.get_intValue());
		IntLiteral falseExpression = (IntLiteral) condExpr
				.getFirstIsFalseExprOfIncidence().getAlpha();
		assertEquals(2, falseExpression.get_intValue());
	}

	@Test
	public void testVertexSetExpression() throws Exception {
		Greql2 graph = parseQuery("V{FirstType, SecondType, ^ThirdType}");
		VertexSetExpression vset = graph.getFirstVertexSetExpression();
		assertNotNull(vset);
		assertEquals(3, vset.getDegree(IsTypeRestrOfExpression.class));
		IsTypeRestrOfExpression typeEdge = vset
				.getFirstIsTypeRestrOfExpressionIncidence();
		TypeId typeId = typeEdge.getAlpha();
		assertEquals("FirstType", typeId.get_name());
		assertFalse(typeId.is_excluded());
		typeEdge = typeEdge.getNextIsTypeRestrOfExpressionIncidence();
		typeId = typeEdge.getAlpha();
		assertEquals("SecondType", typeId.get_name());
		assertFalse(typeId.is_excluded());
		typeEdge = typeEdge.getNextIsTypeRestrOfExpressionIncidence();
		typeId = typeEdge.getAlpha();
		assertEquals("ThirdType", typeId.get_name());
		assertTrue(typeId.is_excluded());
	}

	@Test
	public void testEdgeSetExpression() throws Exception {
		Greql2 graph = parseQuery("E{^FirstType, ^SecondType, ThirdType}");
		EdgeSetExpression vset = graph.getFirstEdgeSetExpression();
		assertNotNull(vset);
		assertEquals(3, vset.getDegree(IsTypeRestrOfExpression.class));
		IsTypeRestrOfExpression typeEdge = vset
				.getFirstIsTypeRestrOfExpressionIncidence();
		TypeId typeId = typeEdge.getAlpha();
		assertEquals("FirstType", typeId.get_name());
		assertTrue(typeId.is_excluded());
		typeEdge = typeEdge.getNextIsTypeRestrOfExpressionIncidence();
		typeId = typeEdge.getAlpha();
		assertEquals("SecondType", typeId.get_name());
		assertTrue(typeId.is_excluded());
		typeEdge = typeEdge.getNextIsTypeRestrOfExpressionIncidence();
		typeId = typeEdge.getAlpha();
		assertEquals("ThirdType", typeId.get_name());
		assertFalse(typeId.is_excluded());
	}

	@Test
	public void testReportAsQuery() throws Exception {
		parseQuery("from v:V report v as \"Vertex\" end");
	}

	@Test
	public void testSimplePathDescription() throws Exception {
		Greql2 graph = parseQuery("using v: v --> ");
		SimplePathDescription pathDescr = graph.getFirstSimplePathDescription();
		// TODO test seriously
		// for (Vertex v : graph.vertices()) {
		// System.out.println("VErtex: " + v);
		// }
		assertNotNull(pathDescr);
	}

	@Test
	public void testAlternativePathDescriptionWithTypes() throws Exception {
		Greql2 graph = parseQuery("using v: v -->{AType} | <--{AnotherType}");
		Variable var = graph.getFirstVariable();
		assertNotNull(var);
		assertEquals("v", var.get_name());
		ForwardVertexSet vset = graph.getFirstForwardVertexSet();
		assertNotNull(vset);

		AlternativePathDescription apd = graph
				.getFirstAlternativePathDescription();
		assertNotNull(apd);
		IsAlternativePathOf edge = apd
				.getFirstIsAlternativePathOfIncidence(EdgeDirection.IN);
		assertNotNull(edge);
		SimplePathDescription spd = (SimplePathDescription) edge.getAlpha();

		IsEdgeRestrOf restrEdge = spd.getFirstIsEdgeRestrOfIncidence();
		assertNotNull(restrEdge);
		EdgeRestriction edgeRestriction = restrEdge.getAlpha();
		IsTypeIdOf typeEdge = edgeRestriction.getFirstIsTypeIdOfIncidence();
		assertNotNull(typeEdge);
		TypeId type = typeEdge.getAlpha();
		assertEquals("AType", type.get_name());
		assertFalse(type.is_type());
		assertFalse(type.is_excluded());

		edge = edge.getNextIsAlternativePathOfIncidence();
		assertNotNull(edge);
		spd = (SimplePathDescription) edge.getAlpha();
		restrEdge = spd.getFirstIsEdgeRestrOfIncidence();
		assertNotNull(restrEdge);
		edgeRestriction = restrEdge.getAlpha();
		typeEdge = edgeRestriction.getFirstIsTypeIdOfIncidence();
		assertNotNull(typeEdge);
		type = typeEdge.getAlpha();
		assertEquals("AnotherType", type.get_name());
		assertFalse(type.is_type());
		assertFalse(type.is_excluded());
	}

	@Test
	public void testPathDescriptionWithParantheses1() throws Exception {
		Greql2 graph = parseQuery("(--> | <--)");
		AlternativePathDescription apd = graph
				.getFirstAlternativePathDescription();
		assertNotNull(apd);
		IsAlternativePathOf edge = apd
				.getFirstIsAlternativePathOfIncidence(EdgeDirection.IN);
		assertNotNull(edge);
		assertTrue(edge.getAlpha() instanceof SimplePathDescription);
		edge = edge.getNextIsAlternativePathOfIncidence();
		assertNotNull(edge);
		edge.getAlpha();
		assertTrue(edge.getAlpha() instanceof SimplePathDescription);
	}

	@Test
	public void testTypedPathDescription() throws Exception {
		Greql2 graph = parseQuery("using v: v -->{AType} ");
		Variable var = graph.getFirstVariable();
		assertNotNull(var);
		assertEquals("v", var.get_name());
		ForwardVertexSet vset = graph.getFirstForwardVertexSet();
		assertNotNull(vset);

		SimplePathDescription simplepd = graph.getFirstSimplePathDescription();
		assertNotNull(simplepd);
		IsEdgeRestrOf restrEdge = simplepd.getFirstIsEdgeRestrOfIncidence();
		assertNotNull(restrEdge);
		EdgeRestriction edgeRestriction = restrEdge.getAlpha();
		IsTypeIdOf typeEdge = edgeRestriction.getFirstIsTypeIdOfIncidence();
		assertNotNull(typeEdge);
		TypeId type = typeEdge.getAlpha();
		assertEquals("AType", type.get_name());
		assertFalse(type.is_type());
		assertFalse(type.is_excluded());
	}

	@Test
	public void testAlternativePathDescription() throws Exception {
		Greql2 graph = parseQuery("using v: v --> | <-- ");
		Variable var = graph.getFirstVariable();
		assertNotNull(var);
		assertEquals("v", var.get_name());
		ForwardVertexSet vset = graph.getFirstForwardVertexSet();
		assertNotNull(vset);

		AlternativePathDescription apd = graph
				.getFirstAlternativePathDescription();
		assertNotNull(apd);
		IsAlternativePathOf edge = apd
				.getFirstIsAlternativePathOfIncidence(EdgeDirection.IN);
		assertNotNull(edge);
		assertTrue(edge.getAlpha() instanceof SimplePathDescription);
		edge = edge.getNextIsAlternativePathOfIncidence();
		assertNotNull(edge);
		assertTrue(edge.getAlpha() instanceof SimplePathDescription);
	}

	@Test
	public void testIntermediateVertexPathDescription() throws Exception {
		Greql2 graph = parseQuery("using v: v --> v <-- ");
		ForwardVertexSet vset = graph.getFirstForwardVertexSet();
		assertNotNull(vset);

		IntermediateVertexPathDescription ipd = graph
				.getFirstIntermediateVertexPathDescription();
		assertNotNull(ipd);
		IsSubPathOf edge = ipd.getFirstIsSubPathOfIncidence(EdgeDirection.IN);
		assertNotNull(edge);
		assertTrue(edge.getAlpha() instanceof SimplePathDescription);
		edge = edge.getNextIsSubPathOfIncidence();
		assertNotNull(edge);
		assertTrue(edge.getAlpha() instanceof SimplePathDescription);
		IsIntermediateVertexOf intEdge = ipd
				.getFirstIsIntermediateVertexOfIncidence();
		assertNotNull(intEdge);
		assertEquals(graph.getFirstVariable(), intEdge.getAlpha());
	}

	@Test
	public void testSequentialPathDescription() throws Exception {
		Greql2 graph = parseQuery("using v: v --> <-- ");
		ForwardVertexSet vset = graph.getFirstForwardVertexSet();
		assertNotNull(vset);

		SequentialPathDescription sepd = graph
				.getFirstSequentialPathDescription();
		assertNotNull(sepd);
		IsSequenceElementOf edge = sepd
				.getFirstIsSequenceElementOfIncidence(EdgeDirection.IN);
		assertNotNull(edge);
		assertTrue(edge.getAlpha() instanceof SimplePathDescription);
		edge = edge.getNextIsSequenceElementOfIncidence();
		assertNotNull(edge);
		assertTrue(edge.getAlpha() instanceof SimplePathDescription);
	}

	@Test
	public void testStartRestrictedPathDescriptionWithExpression()
			throws Exception {
		Greql2 graph = parseQuery("using v: v {@v.a=3} & --> ");

		SimplePathDescription srpd = graph.getFirstSimplePathDescription();
		assertNotNull(srpd);
		IsStartRestrOf restrEdge = srpd
				.getFirstIsStartRestrOfIncidence(EdgeDirection.IN);
		assertNotNull(restrEdge);
		FunctionApplication restr = (FunctionApplication) restrEdge.getAlpha();
		FunctionId funId = restr.getFirstIsFunctionIdOfIncidence().getAlpha();
		assertEquals("equals", funId.get_name());

		ForwardVertexSet vset = graph.getFirstForwardVertexSet();
		assertNotNull(vset);
	}

	@Test
	public void testStartRestrictedPathDescriptionWithType() throws Exception {
		Greql2 graph = parseQuery("using v: v {MyType} & --> ");

		SimplePathDescription srpd = graph.getFirstSimplePathDescription();
		assertNotNull(srpd);
		IsStartRestrOf restrEdge = srpd
				.getFirstIsStartRestrOfIncidence(EdgeDirection.IN);
		assertNotNull(restrEdge);
		TypeId typeId = (TypeId) restrEdge.getAlpha();
		assertEquals("MyType", typeId.get_name());
		assertFalse(typeId.is_excluded());
		assertFalse(typeId.is_type());

		ForwardVertexSet vset = graph.getFirstForwardVertexSet();
		assertNotNull(vset);
	}

	@Test
	public void testGoalRestrictedPathDescription() throws Exception {
		Greql2 graph = parseQuery("using v: v --> & {@false} ");
		ForwardVertexSet vset = graph.getFirstForwardVertexSet();
		assertNotNull(vset);

		SimplePathDescription gpd = graph.getFirstSimplePathDescription();
		assertNotNull(gpd);
		IsGoalRestrOf restrEdge = gpd
				.getFirstIsGoalRestrOfIncidence(EdgeDirection.IN);
		assertNotNull(restrEdge);
	}

	@Test
	public void testIteratedPathDescription() throws Exception {
		Greql2 graph = parseQuery("using v: v -->* ");
		ForwardVertexSet vset = graph.getFirstForwardVertexSet();
		assertNotNull(vset);

		IteratedPathDescription ipd = graph.getFirstIteratedPathDescription();
		assertNotNull(ipd);

	}

	@Test
	public void testPathExistence() throws Exception {
		Greql2 graph = parseQuery("using v,w: v --> w ");
		PathExistence pathExistence = graph.getFirstPathExistence();
		assertNotNull(pathExistence);
		IsStartExprOf startEdge = pathExistence
				.getFirstIsStartExprOfIncidence();
		assertNotNull(startEdge);
		Variable startVar = (Variable) startEdge.getAlpha();
		assertEquals("v", startVar.get_name());

		IsTargetExprOf endEdge = pathExistence
				.getFirstIsTargetExprOfIncidence();
		assertNotNull(endEdge);
		Variable endVar = (Variable) endEdge.getAlpha();
		assertEquals("w", endVar.get_name());

		IsPathOf pathEdge = pathExistence.getFirstIsPathOfIncidence();
		assertNotNull(pathEdge);
		assertTrue(pathEdge.getAlpha() instanceof SimplePathDescription);
	}

	@Test
	public void testEdgePathDescription() throws Exception {
		Greql2 graph = parseQuery("using e,v : v --e-> ");
		ForwardVertexSet vset = graph.getFirstForwardVertexSet();
		assertNotNull(vset);

		EdgePathDescription ipd = graph.getFirstEdgePathDescription();
		assertNotNull(ipd);
		IsEdgeExprOf edge = ipd.getFirstIsEdgeExprOfIncidence();
		assertNotNull(edge);
	}

	@Test
	public void testErrorInTypeExpression() throws Exception {
		String query = "from v:V{Greql2Expression, ^TypeExpression, ^Quantifier} report v end";
		parseQuery(query);
	}

	@Test(timeout = 5000)
	public void testParsingSpeed2() throws Exception {
		parseQuery("3 + (((3) + (((3)) - 3) * (((((((((9 - 6))) + 3 - 6)) + 3))))) - 3)");
	}

	@Test(timeout = 5000)
	public void testParsingSpeed3() throws Exception {
		parseQuery("((((((((((((9))))))))))))");
	}

	@Test(timeout = 5000)
	public void testParsingSpeed4() throws Exception {
		parseQuery("(((((((((((((((((((((((((((((((((((((((9)))))))))))))))))))))))))))))))))))))))");
	}

	@Test
	public void testStringWithoutEscapes() {
		Greql2 graph = parseQuery("\"my simple string\"");
		assertNotNull(graph);
		StringLiteral lit = graph.getFirstStringLiteral();
		assertNotNull(lit);
		assertEquals("my simple string", lit.get_stringValue());
	}

	@Test
	public void testStringWithEscape1() {
		String queryString = "\"my simple \\\"string\"";
		Greql2 graph = parseQuery(queryString);
		assertNotNull(graph);
		StringLiteral lit = graph.getFirstStringLiteral();
		assertNotNull(lit);
		assertEquals("my simple \"string", lit.get_stringValue());
	}

	@Test
	public void testLexer() {
		String queryString = "let a:= 7 in a";
		Greql2 graph = parseQuery(queryString);
		assertNotNull(graph);
	}

	@Test
	public void testStringWithEscape2() {
		String queryString = "\"my simple \nstring\"";
		Greql2 graph = parseQuery(queryString);
		assertNotNull(graph);
		StringLiteral lit = graph.getFirstStringLiteral();
		assertNotNull(lit);
		assertEquals("my simple \nstring", lit.get_stringValue());
	}

	@Test
	public void testParsingError() {
		try {
			parseQuery("from v:V report v --> --< end");
			fail("Expected ParsingException at offset 24");
		} catch (ParsingException ex) {
			assertEquals(24, ex.getOffset());
		}
	}

	@Test
	public void testParsingError2() {
		try {
			parseQuery("from v:X report v --> --> end");
			fail("Expected ParsingException at offset 7");
		} catch (UndefinedVariableException ex) {
			assertEquals(7, ex.getOffset());
		}
	}

	@Test
	public void testGetKnownVariables() {
		String query = "//test \n \n from var:V{} \n with \n var ";
		GreqlParser parser = new GreqlParser(query);
		try {
			parser.parse();
		} catch (ParsingException ex) {
			assertEquals(1, parser.getValidVariables().size());
			for (String vs : parser.getValidVariables()) {
				assertEquals("var", vs);
			}
		}
	}

	@Test
	public void testKeywordsInPackageNames() {
		Schema s = new SchemaImpl("SampleSchema",
				"de.uni_koblenz.jgralab.sampleschema");
		GraphClass gc = s.createGraphClass("SampleGraph");
		gc.createVertexClass("map.SampleVertex");
		s.finish();
		s.commit(CodeGeneratorConfiguration.MINIMAL);
		String query = "import map.SampleVertex;  true ";
		parseQuery(query);
	}

	@Test
	public void testWhereExpressionComplicated() {
		String query = "from c: V{JavaType} " + "with c.kind = \"CLASS\""
				+ "and count(deps)>0 "
				+ "report c.qualifiedName as \"Class\", "
				+ "deps as \"depends on\" " + "end " + "where "
				+ "deps := from d: V{JavaType} "
				+ "with c <--{Defines} -->{Imports} d "
				+ "report d.qualifiedName as \"Class\" " + "end";
		parseQuery(query);
	}
}
