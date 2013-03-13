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
import de.uni_koblenz.jgralab.greql2.schema.Expression;
import de.uni_koblenz.jgralab.greql2.schema.IsTypeRestrOfExpression;
import de.uni_koblenz.jgralab.greql2.schema.TypeId;
import de.uni_koblenz.jgralab.greql2.types.TypeCollection;

/**
 * This class is the base class for all VertexEvaluators, that construct an
 * element collection, for instance EdgeSetExpressionEvaluator. But it is not
 * the base for Forward- or BackwardVertexSetEvaluator, because these are
 * PathSearchEvaluators.
 *
 * @author ist@uni-koblenz.de  
 *
 */
public abstract class AbstractGraphElementCollectionEvaluator extends
		VertexEvaluator {

	public AbstractGraphElementCollectionEvaluator(GreqlEvaluator eval) {
		super(eval);
	}

	private TypeCollection typeCollection = null;

	protected TypeCollection getTypeCollection() {
		if (typeCollection == null) {
			typeCollection = new TypeCollection();
			IsTypeRestrOfExpression inc = ((Expression) getVertex())
					.getFirstIsTypeRestrOfExpressionIncidence(EdgeDirection.IN);
			while (inc != null) {
				if (inc.getAlpha() instanceof TypeId) {
					TypeIdEvaluator typeEval = (TypeIdEvaluator) vertexEvalMarker
							.getMark(inc.getAlpha());
					typeCollection.addTypes((TypeCollection) typeEval
							.getResult());
				}
				inc = inc.getNextIsTypeRestrOfExpressionIncidence(EdgeDirection.IN);
			}
		}
		return typeCollection;
	}

}
