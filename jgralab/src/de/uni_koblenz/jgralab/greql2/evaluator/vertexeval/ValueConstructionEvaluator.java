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

import java.util.ArrayList;

import org.pcollections.PCollection;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;
import de.uni_koblenz.jgralab.greql2.schema.Expression;
import de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex;
import de.uni_koblenz.jgralab.greql2.schema.IsPartOf;
import de.uni_koblenz.jgralab.greql2.schema.ValueConstruction;

/**
 * This is the abstract base class for all ValueConstructions
 *
 * @author ist@uni-koblenz.de
 *
 */
abstract public class ValueConstructionEvaluator extends VertexEvaluator {

	protected ValueConstruction vertex;

	private ArrayList<VertexEvaluator> partEvaluators = null;

	/**
	 * returns the vertex this VertexEvaluator evaluates
	 */
	@Override
	public Greql2Vertex getVertex() {
		return vertex;
	}

	public ValueConstructionEvaluator(ValueConstruction vertex,
			GreqlEvaluator eval) {
		super(eval);
		this.vertex = vertex;
	}

	public final PCollection<Object> createValue(PCollection<Object> collection) {
		if (partEvaluators == null) {
			int partCount = 0;
			IsPartOf inc = vertex.getFirstIsPartOfIncidence(EdgeDirection.IN);
			while (inc != null) {
				partCount++;
				inc = inc.getNextIsPartOfIncidence(EdgeDirection.IN);
			}
			inc = vertex.getFirstIsPartOfIncidence(EdgeDirection.IN);
			partEvaluators = new ArrayList<VertexEvaluator>(partCount);
			while (inc != null) {
				Expression currentExpression = (Expression) inc.getAlpha();
				VertexEvaluator vertexEval = vertexEvalMarker
						.getMark(currentExpression);
				partEvaluators.add(vertexEval);
				inc = inc.getNextIsPartOfIncidence(EdgeDirection.IN);
			}
		}
		for (int i = 0; i < partEvaluators.size(); i++) {
			collection = collection.plus(partEvaluators.get(i).getResult());
		}
		return collection;
	}

}
