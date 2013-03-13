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

import org.pcollections.PSet;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.JGraLab;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;
import de.uni_koblenz.jgralab.greql2.evaluator.costmodel.GraphSize;
import de.uni_koblenz.jgralab.greql2.evaluator.costmodel.VertexCosts;
import de.uni_koblenz.jgralab.greql2.schema.VertexSetExpression;
import de.uni_koblenz.jgralab.greql2.types.TypeCollection;

/**
 * construct a subset of the datagraph vertices. For instance, the expression
 * V:{Department} will be evaluated by this evaluator, it will construct the set
 * of vertices in the datagraph that have the type Department or a type that is
 * derived from Department
 *
 * @author ist@uni-koblenz.de
 *
 */
public class VertexSetExpressionEvaluator extends ElementSetExpressionEvaluator {

	/**
	 * Creates a new ElementSetExpressionEvaluator for the given vertex
	 *
	 * @param eval
	 *            the GreqlEvaluator instance this VertexEvaluator belong to
	 * @param vertex
	 *            the vertex this VertexEvaluator evaluates
	 */
	public VertexSetExpressionEvaluator(VertexSetExpression vertex,
			GreqlEvaluator eval) {
		super(vertex, eval);
	}

	@Override
	public Object evaluate() {
		Graph datagraph = greqlEvaluator.getDatagraph();
		TypeCollection typeCollection = getTypeCollection();
		PSet<Vertex> resultSet = null;
		String indexKey = null;
		if (GreqlEvaluator.VERTEX_INDEXING) {
			indexKey = typeCollection.toString();
			resultSet = GreqlEvaluator.getVertexIndex(datagraph, indexKey);
		}
		if (resultSet == null) {
			long startTime = System.currentTimeMillis();
			resultSet = JGraLab.set();
			Vertex currentVertex = datagraph.getFirstVertex();
			while (currentVertex != null) {
				if (typeCollection.acceptsType(currentVertex
						.getAttributedElementClass())) {
					resultSet = resultSet.plus(currentVertex);
				}
				currentVertex = currentVertex.getNextVertex();
			}
			if (GreqlEvaluator.VERTEX_INDEXING) {
				if ((System.currentTimeMillis() - startTime) > greqlEvaluator
						.getIndexTimeBarrier()) {
					GreqlEvaluator.addVertexIndex(datagraph, indexKey,
							resultSet);
				}
			}
		}
		return resultSet;
	}

	@Override
	public VertexCosts calculateSubtreeEvaluationCosts(GraphSize graphSize) {
		return this.greqlEvaluator.getCostModel()
				.calculateCostsVertexSetExpression(this, graphSize);
	}

	@Override
	public long calculateEstimatedCardinality(GraphSize graphSize) {
		return greqlEvaluator.getCostModel()
				.calculateCardinalityVertexSetExpression(this, graphSize);
	}

}
