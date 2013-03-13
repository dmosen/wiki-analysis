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
package de.uni_koblenz.jgralab.trans;

import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.TraversalContext;

/**
 * This interface represents a transaction within JGraLab.
 * 
 * @author Jose Monte(monte@uni-koblenz.de)
 */
public interface Transaction {
	/**
	 * Begin of transaction. <code>Transaction</code> passes first to state
	 * {@link TransactionState#STARTING STARTING}.
	 * 
	 * After finishing some initialization issues the transaction passes to
	 * state {@link TransactionState#RUNNING RUNNING}.
	 * 
	 * This method is invoked implicitly when instantiating a
	 * <code>Transaction</code>-instance, so there is no need to call it
	 * afterwards.
	 * 
	 * Calling {@link #bot() bot()} on a <code>Transaction</code> in a state !=
	 * {@link TransactionState#NOTRUNNING NOTRUNNING} will have no effect.
	 * 
	 * Note that you can only use {@link #bot() bot()}, if the transaction is
	 * active in a thread!!!
	 */
	public void bot();

	/**
	 * Indicates that changes made within a (read-write-)
	 * <code>Transaction</code> should be stored persistently.
	 * <code>Transaction</code> passes first to state
	 * {@link TransactionState#COMMITTING COMMITTING}.
	 * 
	 * The COMMIT of a read-write-transaction consists of two phases:
	 * 
	 * - validation phase: <code>Transaction</code> passes to state
	 * {@link TransactionState#VALIDATING VALIDATING}. Check for conflicts; if
	 * at least one conflict is detected, the <code>Transaction</code> returns
	 * to state {@link TransactionState#RUNNING RUNNING}.
	 * 
	 * - writing phase: <code>Transaction</code> passes to state
	 * {@link TransactionState#WRITING WRITING}. If no conflicts are detected in
	 * validation phase, the changes made within the <code>Transaction</code>
	 * are stored persistently (new persistent versions of versioned data
	 * objects are created). The <code>Transaction</code> passes to state
	 * {@link TransactionState#COMMITTED COMMITTED}.
	 * 
	 * If <code>Transaction</code> is read-only, the <code>Transaction</code>
	 * passes directly to state {@link TransactionState#COMMITTED COMMITTED}
	 * without executing validation or writing phase.
	 * 
	 * COMMIT is synchronized, so that at a particular time at most one
	 * <code>Transaction</code> can execute {@link #commit() commit()} at the
	 * same time.
	 * 
	 * IDs of deleted vertices and edges are freed for read-write-
	 * <code>Transaction</code>. (synchronized access to
	 * <code>FreeIndexList</code> of vertices and edges).
	 * 
	 * Note that you can only use {@link #commit() commit()}, if the transaction
	 * is active in a thread!!!
	 * 
	 * @throws CommitFailedException
	 *             if at least one conflict could be detected in the validation
	 *             phase; <code>Transaction</code> returns the state
	 *             {@link TransactionState#RUNNING RUNNING}
	 */
	public void commit() throws CommitFailedException;

	/**
	 * Indicates that the <code>Transaction</code> should be aborted.
	 * <code>Transaction</code> passes first to state
	 * {@link TransactionState#ABORTING ABORTING}.
	 * 
	 * Allocated vertex- and edge-IDs are freed (synchronized) for read-write-
	 * <code>Transaction</code> (synchronized access to
	 * <code>FreeIndexList</code> of vertices and edges).
	 * 
	 * <code>Transaction</code> ends in state {@link TransactionState#ABORTED
	 * ABORTED}
	 * 
	 * Note that you can only use {@link #abort() abort()}, if the transaction
	 * is active in a thread!!!
	 */
	public void abort();

	/**
	 * 
	 * @return the ID of the transaction.
	 */
	public int getID();

	/**
	 * 
	 * @return indicates whether the transaction is a read-only- (true) or a
	 *         read-write-transaction (false).
	 */
	public boolean isReadOnly();

	/**
	 * 
	 * @return the current state of the transaction.
	 */
	public TransactionState getState();

	/**
	 * Stores the current state of the <code>Transaction</code> and of the
	 * (changed) data objects.
	 * 
	 * To restore a save-point use method {@link #restoreSavepoint(Savepoint)}.
	 * 
	 * Note that you can only use {@link #defineSavepoint()}, if the transaction
	 * is active in a thread!!!
	 * 
	 * @return a save-point which represents the current state of the
	 *         transaction
	 */
	public Savepoint defineSavepoint();

	/**
	 * Restores a save-point for the <code>Transaction</code>.
	 * 
	 * @param savepoint
	 *            the save-point to be restored
	 * 
	 *            Note that you can only use
	 *            {@link #restoreSavepoint(Savepoint)}, if the transaction is
	 *            active in a thread!!!
	 * 
	 * @throws InvalidSavepointException
	 *             if <code>savepoint</code>.{@link Savepoint#getTransaction()}
	 *             getTransaction() != <code>this</code>
	 */
	public void restoreSavepoint(Savepoint savepoint)
			throws InvalidSavepointException;

	/**
	 * Removes a save-point for the <code>Transaction</code>.
	 * 
	 * Note that you can only use {@link #removeSavepoint(Savepoint)}, if the
	 * transaction is active in a thread!!!
	 */
	public void removeSavepoint(Savepoint savepoint);

	/**
	 * 
	 * @return a list of all save-points for the <code>Transaction</code>
	 * 
	 *         Note that you can only use {@link #getSavepoints()}, if the
	 *         transaction is active in a thread!!!
	 * 
	 *         Only a copy of save-point-list is returned, so removing and
	 *         adding save-points won't affects original list.
	 */
	public List<Savepoint> getSavepoints();

	/**
	 * Executes validation (check for conflicts) for the transaction.
	 * 
	 * This enables to execute conflict-checking for a transaction, which is in
	 * state {@link TransactionState#RUNNING RUNNING}.
	 * 
	 * Note that while the validation of a <code>Transaction</code>, the
	 * parallel execution of {@link #commit() commit()} (writing-phase) by other
	 * <code>Transaction</code>s is delayed.
	 * 
	 * Note that you can only use {@link #isInConflict() isInConflict()}, if the
	 * transaction is active in a thread!!!
	 * 
	 * @return <code>true</code>, if at least one conflict could be detected
	 *         <code>false</code>, if no conflicts could be detected
	 */
	public boolean isInConflict();

	/**
	 * 
	 * @return the <code>Graph</code>-instance the <code>Transaction</code>
	 *         belongs to
	 */
	public Graph getGraph();

	/**
	 * Note that at a particular time a <code>Transaction</code>can only be
	 * active in at the most one thread at the same time.
	 * 
	 * @return the thread in which the <code>Transaction</code> is currently
	 *         active
	 */
	public Thread getThread();

	/**
	 * An invalid <code>Transaction</code> has state
	 * {@link TransactionState#COMMITTED COMMITTED} or
	 * {@link TransactionState#ABORTED ABORTED}.
	 * 
	 * @return if the <code>Transaction</code> is still valid
	 */
	public boolean isValid();

	/**
	 * Sets the new traversal context for this graph in this transaction.
	 * 
	 * @param tc
	 *            the new traversal context
	 * @return the old traversal context
	 */
	public TraversalContext setTraversalContext(TraversalContext tc);

	/**
	 * Returns the current traversal context of this graph in this transaction.
	 * 
	 * @return the current traversal context of this graph in tihs transaction.
	 */
	public TraversalContext getTraversalContext();
}
