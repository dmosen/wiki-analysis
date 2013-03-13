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

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;
import de.uni_koblenz.jgralab.greql2.evaluator.costmodel.GraphSize;
import de.uni_koblenz.jgralab.greql2.evaluator.costmodel.VertexCosts;
import de.uni_koblenz.jgralab.greql2.evaluator.fa.NFA;
import de.uni_koblenz.jgralab.greql2.schema.EdgePathDescription;
import de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfExpression;
import de.uni_koblenz.jgralab.greql2.types.TypeCollection;

/**
 * Creates a NFA wich accepts a single edge out of the --edge-> - clause
 * 
 * @author ist@uni-koblenz.de
 * 
 */
public class EdgePathDescriptionEvaluator extends
		PrimaryPathDescriptionEvaluator {

	public EdgePathDescriptionEvaluator(EdgePathDescription vertex,
			GreqlEvaluator eval) {
		super(vertex, eval);
	}

	@Override
	public NFA evaluate() {
		Edge evalEdge = vertex.getFirstIsEdgeExprOfIncidence();
		VertexEvaluator edgeEval = null;
		if (evalEdge != null) {
			edgeEval = vertexEvalMarker.getMark(evalEdge.getAlpha());
		}
		TypeCollection typeCollection = new TypeCollection();
		IsTypeRestrOfExpression inc = vertex
				.getFirstIsTypeRestrOfExpressionIncidence(EdgeDirection.IN);
		EdgeRestrictionEvaluator edgeRestEval = null;
		VertexEvaluator predicateEvaluator = null;
		if (inc != null) {
			edgeRestEval = (EdgeRestrictionEvaluator) vertexEvalMarker
					.getMark(inc.getAlpha());
			typeCollection.addTypes(edgeRestEval.getTypeCollection());
			predicateEvaluator = edgeRestEval.getPredicateEvaluator();
		}
		createdNFA = NFA.createEdgePathDescriptionNFA(getEdgeDirection(vertex),
				typeCollection, getEdgeRoles(edgeRestEval), edgeEval,
				predicateEvaluator, vertexEvalMarker);
		return createdNFA;
	}

	@Override
	public VertexCosts calculateSubtreeEvaluationCosts(GraphSize graphSize) {
		return this.greqlEvaluator.getCostModel()
				.calculateCostsEdgePathDescription(this, graphSize);
	}

}
