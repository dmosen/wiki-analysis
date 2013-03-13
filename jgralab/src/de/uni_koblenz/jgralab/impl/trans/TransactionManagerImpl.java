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
package de.uni_koblenz.jgralab.impl.trans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import de.uni_koblenz.jgralab.GraphException;
import de.uni_koblenz.jgralab.trans.Transaction;
import de.uni_koblenz.jgralab.trans.TransactionManager;

/**
 * The implementation of the <code>TransactionManager</code>.
 * 
 * @author Jose Monte(monte@uni-koblenz.de)
 */
public class TransactionManagerImpl implements TransactionManager {
	// load factor for maps - only for testing regarding memory usage!!!
	protected static float LOAD_FACTOR = 0.75f;

	// for each <code>Graph</code> instance there should only exist one instance
	// of <code>TransactionManager</code>
	private static Map<GraphImpl, TransactionManager> graphTransactionManagerMap;
	static {
		graphTransactionManagerMap = new HashMap<GraphImpl, TransactionManager>(
				1, LOAD_FACTOR);
	}

	// the <code>Graph</code> instance for which this instance manages the
	// transactions
	private GraphImpl graph;
	// the list of active transactions
	protected List<Transaction> transactionList;
	// every thread should only have at most one transaction t assigned, for
	// which t.getGraph() == graph is valid
	private Map<Thread, Transaction> threadTransactionMap;
	// id-counter for transactions
	private int idCounter;

	// synchronization variables
	protected ReadWriteLock botWritingSync;
	protected ReadWriteLock commitValidatingSync;
	protected ReadWriteLock commitSync;
	// needed to assure ascending order of by persistentVersionAtBot of
	// transactions in transaction list
	protected Object transactionOrderSync;

	@Override
	public List<Transaction> getTransactions() {
		return transactionList;
	}

	/**
	 * 
	 * @return increases the id counter and returns the value
	 */
	private synchronized int incrIdCounter() {
		// first increase then return!!!
		return ++idCounter;
	}

	/**
	 * 
	 * @param graph
	 *            the
	 *            <code>Graph</(code> to which the <code>TransactionManager</code>
	 *            belongs to
	 */
	private TransactionManagerImpl(GraphImpl graph) {
		this.graph = graph;
		idCounter = 0;
		transactionList = new ArrayList<Transaction>(1);
		threadTransactionMap = new HashMap<Thread, Transaction>(1, LOAD_FACTOR);

		botWritingSync = new ReentrantReadWriteLock(true);
		commitValidatingSync = new ReentrantReadWriteLock(true);
		commitSync = new ReentrantReadWriteLock(true);
		transactionOrderSync = new Object();
	}

	/**
	 * 
	 * @param graph
	 *            the graph to which the <code>TransactionManager</code> belongs
	 *            to
	 * @return the instance for the given <code>graph</code>
	 */
	protected static TransactionManager getInstance(GraphImpl graph) {
		assert (graphTransactionManagerMap != null);
		TransactionManager transactionManager = graphTransactionManagerMap
				.get(graph);
		if (transactionManager == null) {
			transactionManager = new TransactionManagerImpl(graph);
			graphTransactionManagerMap.put(graph, transactionManager);
		}
		return transactionManager;
	}

	@Override
	public Transaction createReadOnlyTransaction() {
		return this.internalCreateTransaction(true);
	}

	@Override
	public Transaction createTransaction() {
		return this.internalCreateTransaction(false);
	}

	/**
	 * 
	 * @param readOnly
	 * @return a read-only- or read-write-<code>Transaction</code> depending on
	 *         the value of <code>readOnly</code>
	 */
	private Transaction internalCreateTransaction(boolean readOnly) {
		// important to avoid deadlocks
		synchronized (transactionOrderSync) {
			int id = incrIdCounter();
			TransactionImpl transaction = new TransactionImpl(this, graph, id,
					readOnly);
			synchronized (transactionList) {
				// Dont' bot here - deadlock occurences
				// transaction.bot();
				// adding new transaction to transactionList should be atomic
				transactionList.add(transaction);
				int indexOf = transactionList.indexOf(transaction);
				if (indexOf > 0) {
					TransactionImpl prevTransaction = (TransactionImpl) transactionList
							.get(indexOf - 1);
					if (prevTransaction.persistentVersionAtBot > transaction.persistentVersionAtBot) {
						throw new GraphException("This should not happen!");
					}
				}
				setTransactionForThread(transaction, Thread.currentThread());
				return transaction;
			}
		}
	}

	@Override
	public synchronized Transaction getTransactionForThread(Thread thread) {
		if (thread == null) {
			return null;
		}
		return threadTransactionMap.get(thread);
	}

	@Override
	public synchronized void removeTransactionForThread(Thread thread) {
		if (thread != null) {
			TransactionImpl transaction = (TransactionImpl) threadTransactionMap
					.get(thread);
			transaction.setThread(null);
			synchronized (transaction) {
				threadTransactionMap.remove(thread);
			}
		}
	}

	@Override
	public synchronized void setTransactionForThread(Transaction transaction,
			Thread thread) {
		if (transaction != null && thread != null) {
			if (transaction.getGraph() != graph) {
				throw new GraphException(
						"The given transaction is not valid for the current graph.");
			}
			synchronized (transaction) {
				TransactionImpl oldTransaction = (TransactionImpl) threadTransactionMap
						.get(thread);
				// if there was a transaction active in <code>thread</code>...
				if (oldTransaction != null) {
					synchronized (oldTransaction) {
						oldTransaction.setThread(null);
					}
				}
				threadTransactionMap.put(thread, (TransactionImpl) transaction);
				TransactionImpl trans = (TransactionImpl) transaction;
				Thread oldThread = trans.getThread();
				if (oldThread != thread) {
					removeTransactionForThread(oldThread);
				}
				trans.setThread(thread);
			}
		}
	}

	/**
	 * Removes given transaction.
	 * 
	 * @param transaction
	 */
	protected void removeTransaction(Transaction transaction) {
		synchronized (transactionList) {
			transactionList.remove(transaction);
		}
	}

	/**
	 * 
	 * @return the oldest transaction
	 */
	protected Transaction getOldestTransaction() {
		// oldest transaction is always the first transaction in transactionList
		synchronized (transactionList) {
			return transactionList.get(0);
		}
	}
}
