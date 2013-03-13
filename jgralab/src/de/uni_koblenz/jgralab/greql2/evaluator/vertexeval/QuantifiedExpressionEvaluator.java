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

package de.uni_koblenz.jgralab.greql2.evaluator.vertexeval;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;
import de.uni_koblenz.jgralab.greql2.evaluator.VariableDeclarationLayer;
import de.uni_koblenz.jgralab.greql2.evaluator.costmodel.GraphSize;
import de.uni_koblenz.jgralab.greql2.evaluator.costmodel.VertexCosts;
import de.uni_koblenz.jgralab.greql2.schema.Declaration;
import de.uni_koblenz.jgralab.greql2.schema.Expression;
import de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex;
import de.uni_koblenz.jgralab.greql2.schema.QuantificationType;
import de.uni_koblenz.jgralab.greql2.schema.QuantifiedExpression;
import de.uni_koblenz.jgralab.greql2.schema.Quantifier;

/**
 * Evaluates a QuantifiedExpression, a QuantifiedExpression is something like
 * "using FOO: exists s: FOO @ s = true".
 * 
 * @author ist@uni-koblenz.de
 * 
 */
public class QuantifiedExpressionEvaluator extends VertexEvaluator {

	private QuantifiedExpression vertex;

	private VariableDeclarationLayer declarationLayer = null;

	private QuantificationType quantificationType = null;

	private boolean initialized = false;

	private VertexEvaluator predicateEvaluator = null;

	/**
	 * returns the vertex this VertexEvaluator evaluates
	 */
	@Override
	public Greql2Vertex getVertex() {
		return vertex;
	}

	/**
	 * @param eval
	 *            the GreqlEvaluator this VertexEvaluator belongs to
	 * @param vertex
	 *            the vertex which gets evaluated by this VertexEvaluator
	 */
	public QuantifiedExpressionEvaluator(QuantifiedExpression vertex,
			GreqlEvaluator eval) {
		super(eval);
		this.vertex = vertex;
	}

	private void initialize() {
		Declaration d = (Declaration) vertex
				.getFirstIsQuantifiedDeclOfIncidence(EdgeDirection.IN)
				.getAlpha();
		DeclarationEvaluator declEval = (DeclarationEvaluator) vertexEvalMarker
				.getMark(d);
		declarationLayer = (VariableDeclarationLayer) declEval.getResult();
		Quantifier quantifier = (Quantifier) vertex
				.getFirstIsQuantifierOfIncidence(EdgeDirection.IN).getAlpha();
		quantificationType = quantifier.get_type();
		Expression b = (Expression) vertex.getFirstIsBoundExprOfIncidence(
				EdgeDirection.IN).getAlpha();
		predicateEvaluator = vertexEvalMarker.getMark(b);
		initialized = true;
	}

	/**
	 * evaluates the QuantifiedEx
	 */
	@Override
	public Boolean evaluate() {
		if (!initialized) {
			initialize();
		}

		boolean foundTrue = false;
		declarationLayer.reset();
		switch (quantificationType) {
		case EXISTS:
			while (declarationLayer.iterate()) {
				Object tempResult = predicateEvaluator.getResult();
				if (tempResult instanceof Boolean) {
					if ((Boolean) tempResult) {
						return Boolean.TRUE;
					}
				}
			}
			return Boolean.FALSE;
		case EXISTSONE:
			while (declarationLayer.iterate()) {
				Object tempResult = predicateEvaluator.getResult();
				if (tempResult instanceof Boolean) {
					if ((Boolean) tempResult) {
						if (foundTrue == true) {
							return Boolean.FALSE;
						} else {
							foundTrue = true;
						}
					}
				}
			}
			if (foundTrue) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		case FORALL:
			while (declarationLayer.iterate()) {
				Object tempResult = predicateEvaluator.getResult();
				if (tempResult instanceof Boolean) {
					if (!(Boolean) tempResult) {
						return Boolean.FALSE;
					}
				}
			}
			return Boolean.TRUE;
		default:
			throw new RuntimeException("FIXME: Unhandled quantification type "
					+ quantificationType);
		}
	}

	@Override
	public VertexCosts calculateSubtreeEvaluationCosts(GraphSize graphSize) {
		return greqlEvaluator.getCostModel()
				.calculateCostsQuantifiedExpression(this, graphSize);
	}

}
