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

package de.uni_koblenz.jgralab.greql2.evaluator;

import java.io.File;
import java.util.logging.Logger;

import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.greql2.evaluator.costmodel.CostModel;
import de.uni_koblenz.jgralab.greql2.optimizer.Optimizer;
import de.uni_koblenz.jgralab.greql2.schema.Greql2;
import de.uni_koblenz.jgralab.greql2.schema.Greql2Expression;
import de.uni_koblenz.jgralab.greql2.schema.Greql2Schema;

/**
 * This class is one entry in the Map from Query+CostModel+Optimizer to
 * List<OptimizedSyntaxGraph + UsedFlag>
 */
public class SyntaxGraphEntry {

	/**
	 * the SyntaxGraph this entry represents
	 */
	private Greql2 syntaxGraph;

	/**
	 * @return the SyntaxGraph this entry represents
	 */
	public Greql2 getSyntaxGraph() {
		return syntaxGraph;
	}

	/**
	 * is the graph in use by an evaluator object or not
	 */
	private boolean locked;

	private static Logger logger = Logger.getLogger(SyntaxGraphEntry.class
			.getName());

	/**
	 * @return is the graph is in use by an evaluator object or not
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Locks this graph,
	 * 
	 * @return true on success, false otherwise
	 */
	public boolean lock() {
		return lockGraph(this);
	}

	/**
	 * the costmodel that is used to calculate the evaluation costs of this
	 * syntaxgraph
	 */
	private CostModel costModel;

	/**
	 * @return the CostModel that is used to calculate the evaluation costs of
	 *         this syntaxgraph
	 */
	public CostModel getCostModel() {
		return costModel;
	}

	/**
	 * the optimizer that ist used to optimize this syntaxgraph
	 */
	private Optimizer optimizer;

	/**
	 * The GReQL2 query in text form.
	 */
	private String queryText;

	/**
	 * @return the optimzer that is used to optimize this syntaxgraph
	 */
	public Optimizer getOptimizer() {
		return optimizer;
	}

	/**
	 * Locks the given SyntaxGraphEntry
	 * 
	 * @param entry
	 * @return
	 */
	private static synchronized boolean lockGraph(SyntaxGraphEntry entry) {
		if (entry.locked) {
			return false;
		}
		entry.locked = true;
		return true;
	}

	/**
	 * Releases the lock of this syntaxGraph
	 * 
	 * @return true on success, false otherwise
	 */
	public boolean release() {
		return releaseGraph(this);
	}

	/**
	 * Releases the given SyntaxGraphEntry
	 * 
	 * @param entry
	 * @return
	 */
	private static synchronized boolean releaseGraph(SyntaxGraphEntry entry) {
		if (!entry.locked) {
			return false;
		}
		entry.locked = false;
		return true;
	}

	/**
	 * Creates a new SyntaxGraphEntry
	 * 
	 * @param graph
	 *            the Greql2 Syntaxgraph to store in this entry
	 * @param optimizer
	 *            the Optimizer that was used to optimize this syntaxgraph
	 * @param costModel
	 *            the CostModel that was used to calculate teh evaluation costs
	 * @param locked
	 *            specifies, wether the graph should be locked or not
	 */
	public SyntaxGraphEntry(String queryText, Greql2 graph,
			Optimizer optimizer, CostModel costModel, boolean locked) {
		this.queryText = queryText;
		syntaxGraph = graph;
		this.optimizer = optimizer;
		this.costModel = costModel;
		this.locked = locked;
	}

	/**
	 * Load a {@link SyntaxGraphEntry} from the given file.
	 * 
	 * @param fileName
	 *            the tg file where the {@link SyntaxGraphEntry} should be
	 *            loaded from
	 * @throws GraphIOException
	 *             if an error occurs while loading the {@link SyntaxGraphEntry}
	 *             from the given file
	 * @throws ClassNotFoundException
	 *             if the {@link SyntaxGraphEntry} saved in the file uses an
	 *             unknown {@link Optimizer} or {@link CostModel}
	 * @throws IllegalAccessException
	 *             if the {@link Optimizer} or {@link CostModel} of the saved
	 *             {@link SyntaxGraphEntry} is not accessible for some reason
	 * @throws InstantiationException
	 *             if instantiation of the file's {@link SyntaxGraphEntry}
	 *             fails. If that's the case check if their class definition
	 *             contain a constructor with zero parameters.
	 */
	public SyntaxGraphEntry(File fileName) throws GraphIOException {
		syntaxGraph = Greql2Schema.instance().loadGreql2(fileName.getPath());
		Greql2Expression g2e = syntaxGraph.getFirstGreql2Expression();
		try {
			queryText = (String) g2e.getAttribute("_queryText");
			String optimizerClass = (String) g2e.getAttribute("_optimizer");
			if (!optimizerClass.isEmpty()) {
				optimizer = (Optimizer) Class.forName(optimizerClass)
						.newInstance();
			}
			String costModelClass = (String) g2e.getAttribute("_costModel");
			if (!costModelClass.isEmpty()) {
				costModel = (CostModel) Class.forName(costModelClass)
						.newInstance();
			}
			locked = false;
			// Now delete the attribute values. They're not needed anymore.
			g2e.setAttribute("_queryText", null);
			g2e.setAttribute("_optimizer", null);
			g2e.setAttribute("_costModel", null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GraphIOException(e.getMessage());
		}
	}

	/**
	 * Save this {@link SyntaxGraphEntry} to the given directory in the form
	 * '"queryText.hashCode()" + ".tg"'.
	 * 
	 * @param directory
	 *            the directory where this {@link SyntaxGraphEntry} should be
	 *            stored
	 * @throws GraphIOException
	 *             if an error occurs while saving this {@link SyntaxGraphEntry}
	 */
	public void saveToDirectory(File directory) throws GraphIOException {
		String optimizerClass = "";
		String optimizerClassSimple = "";
		String costModelClass = "";
		String costModelClassSimple = "";

		Greql2Expression g2e = syntaxGraph.getFirstGreql2Expression();
		g2e.set_queryText(queryText);

		if (optimizer != null) {
			optimizerClass = optimizer.getClass().getName();
			optimizerClassSimple = optimizer.getClass().getSimpleName();
		}
		g2e.set_optimizer(optimizerClass);

		if (costModel != null) {
			costModelClass = costModel.getClass().getName();
			costModelClassSimple = costModel.getClass().getSimpleName();
		}
		g2e.set_costModel(costModelClass);

		String fileName = directory.getPath() + File.separator
				+ queryText.hashCode() + "-" + costModelClassSimple + "-"
				+ optimizerClassSimple + ".tg";
		syntaxGraph.save(fileName);
		logger.info("Saved SyntaxGraphEntry to \"" + fileName + "\".");
	}

	/**
	 * This {@link SyntaxGraphEntry} equals the given object, if the given
	 * object's type is {@link SyntaxGraphEntry}, it has the same queryText, and
	 * its optimizer and costModel have the same type this
	 * {@link SyntaxGraphEntry}'s have.
	 * 
	 * (@see java.lang.Object#equals(java.lang.Object))
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof SyntaxGraphEntry) {
			SyntaxGraphEntry e = (SyntaxGraphEntry) o;
			return queryText.equals(e.queryText)
					&& optimizer.getClass().equals(e.optimizer.getClass())
					&& costModel.getClass().equals(e.costModel.getClass());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return queryText.hashCode() + optimizer.getClass().hashCode()
				+ costModel.getClass().hashCode();
	}

	public String getQueryText() {
		return queryText;
	}

	@Override
	public String toString() {
		return "{SyntaxGraphEntry@" + syntaxGraph.hashCode() + ":"
				+ costModel.getClass().getSimpleName() + ":"
				+ optimizer.getClass().getSimpleName() + "}";
	}
}
