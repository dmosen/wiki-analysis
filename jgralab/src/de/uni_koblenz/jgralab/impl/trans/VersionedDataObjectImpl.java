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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

import de.uni_koblenz.jgralab.AttributedElement;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphElement;
import de.uni_koblenz.jgralab.GraphException;
import de.uni_koblenz.jgralab.JGraLab;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.impl.InternalGraph;
import de.uni_koblenz.jgralab.trans.Transaction;
import de.uni_koblenz.jgralab.trans.TransactionManager;
import de.uni_koblenz.jgralab.trans.TransactionState;
import de.uni_koblenz.jgralab.trans.VersionedDataObject;

/**
 * Implementation for versioning of a data-object.
 * 
 * @author Jose Monte(monte@uni-koblenz.de)
 * 
 * @param <E>
 *            the type of the data-object to be versioned
 */
public abstract class VersionedDataObjectImpl<E> implements
		VersionedDataObject<E> {

	private static Logger logger = JGraLab
			.getLogger("de.uni_koblenz.jgralab.impl.trans");

	protected AttributedElement<?, ?> attributedElement;

	/**
	 * Needed for attributes and validation.
	 * 
	 * TODO good for validation - but maybe remove this to save memory?!
	 */
	protected String name;

	@Override
	public int hashCode() {
		return attributedElement.hashCode() + name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof VersionedDataObjectImpl)) {
			return false;
		}
		VersionedDataObjectImpl<?> o = (VersionedDataObjectImpl<?>) obj;
		return attributedElement.equals(o.attributedElement)
				&& name.equals(o.name);
	}

	/**
	 * 
	 * @return the <code>Graph</code> to which this versioned data-object
	 *         belongs to
	 */
	public InternalGraph getGraph() {
		if (attributedElement == null) {
			return null;
		}
		if (attributedElement instanceof Graph) {
			return (InternalGraph) attributedElement;
		}
		return (InternalGraph) ((GraphElement<?, ?>) attributedElement)
				.getGraph();
	}

	/**
	 * A sorted map (by version number) of persistent values. This map should be
	 * used if at least two different persistent values are referenced and
	 * relevant at a time.
	 */
	protected SortedMap<Long, E> persistentVersionMap;

	/**
	 * This field is used, if only one persistent value is needed. If more than
	 * one persistent value is needed at a time (than
	 * <code>persistentVersionMap</code> must be != <code>null</code>),
	 * <code>persistentValue</code> should be set to <code>null</code>.
	 */
	private E persistentValue;

	/**
	 * This map is needed, to assign the "real" version number to a versioned
	 * data-object, which differs from the last assigned version number in
	 * persistentVersionMap because of implicit changes.
	 */
	private static Map<VersionedDataObject<?>, Long> dataObjectPersistentVersionMap;

	/**
	 * 
	 * @param graph
	 *            the <code>Graph</code> to which this versioned data-object
	 *            belongs to
	 * @param initialPersistentValue
	 *            the initial persistent value
	 * @param name
	 *            the name of the attribute
	 */
	protected VersionedDataObjectImpl(
			AttributedElement<?, ?> attributedElement,
			E initialPersistentValue, String name) {
		this.attributedElement = attributedElement;
		this.persistentValue = initialPersistentValue;
		this.name = name;
	}

	@Override
	public void createNewTemporaryValue(Transaction transaction) {
		if (logger != null) {
			logger.finer("tx id=" + transaction.getID() + ", do=" + this);
		}
		// assure that current transaction is valid for creating a new
		// temporary value...
		if (transaction.getGraph() != getGraph()) {
			throw new GraphException(
					"Invalid transaction for the current graph.");
		}
		// temporary versions only make sense while transaction is running
		assert transaction.getState() == TransactionState.RUNNING : "TransactionState should be RUNNING, but it's "
				+ transaction.getState() + ".";
		if (transaction.isReadOnly()) {
			throw new GraphException(
					"Read-only-transactions are not allowed to create temporary values.");
		}
		TransactionImpl trans = (TransactionImpl) transaction;

		// increase the temporary version counter
		long temporaryVersion = trans.incrTemporaryVersionCounter();

		// create the copy
		E copy = null;

		// if transaction already has temporary value then copy this...
		if (hasTemporaryValue(transaction)) {
			copy = copyOf(getTemporaryValue(transaction));
		} else {
			// otherwise make copy of persistent value at BOT of transaction
			copy = copyOf(getPersistentValueAtBot(transaction));
		}

		if (logger != null) {
			logger.finest("  old value: " + copy);
		}

		// no save-point has been defined...
		if (trans.latestDefinedSavepoint == null
				&& (trans.temporaryVersionMap == null || !trans.temporaryVersionMap
						.containsKey(this))) {
			// as long as no save-points are defined, use
			// temporaryValueMap...
			if (logger != null) {
				logger.finest("  case1");
			}
			if (trans.temporaryValueMap == null) {
				trans.temporaryValueMap = new HashMap<VersionedDataObject<?>, Object>(
						1, TransactionManagerImpl.LOAD_FACTOR);
			}
			synchronized (trans.temporaryValueMap) {
				if (logger != null) {
					logger.finest("temporaryValueMap.put(" + this + ", " + copy);
				}
				trans.temporaryValueMap.put(this, copy);
			}
		} else {
			if (logger != null) {
				logger.finest("  case2");
			}
			if (trans.temporaryVersionMap == null) {
				trans.temporaryVersionMap = new HashMap<VersionedDataObject<?>, SortedMap<Long, Object>>(
						1, TransactionManagerImpl.LOAD_FACTOR);
			}
			synchronized (trans.temporaryVersionMap) {
				SortedMap<Long, Object> transactionMap = trans.temporaryVersionMap
						.get(this);
				if (transactionMap == null) {
					transactionMap = new TreeMap<Long, Object>();
					trans.temporaryVersionMap.put(this, transactionMap);
					if (logger != null) {
						logger.finest("  temporaryVersionMap.put(" + this
								+ ", " + transactionMap + ")");
					}
				}

				if (logger != null) {
					logger.finest("transactionMao.put(" + temporaryVersion
							+ ", " + copy + ")");
				}
				transactionMap.put(temporaryVersion, copy);
			}
		}
	}

	/**
	 * 
	 * 
	 * @param trans
	 * @return
	 */
	protected void moveTemporaryVersion(TransactionImpl trans) {
		if (logger != null) {
			logger.finer("tx id=" + trans.getID() + ", do=" + this);
		}
		if (trans.temporaryVersionMap == null) {
			trans.temporaryVersionMap = new HashMap<VersionedDataObject<?>, SortedMap<Long, Object>>(
					1, TransactionManagerImpl.LOAD_FACTOR);
		}
		SortedMap<Long, Object> transactionMap = trans.temporaryVersionMap
				.get(this);
		if (transactionMap == null) {
			transactionMap = new TreeMap<Long, Object>();
			trans.temporaryVersionMap.put(this, transactionMap);
		}
		// if there already exists one temporary value with no
		// version number assigned...
		if (trans.temporaryValueMap != null) {
			synchronized (trans.temporaryValueMap) {
				if (trans.temporaryValueMap.containsKey(this)) {
					// move single existing temporary value to
					// <code>temporaryVersionMap</code>, assigning it
					// the stored version number of
					// <code>latestDefinedSavepoint</code>
					if (logger != null) {
						logger.finest("  transactionMap.put("
								+ trans.latestDefinedSavepoint.versionAtSavepoint
								+ ", " + trans.temporaryValueMap.get(this)
								+ ")");
					}
					transactionMap.put(
							trans.latestDefinedSavepoint.versionAtSavepoint,
							trans.temporaryValueMap.get(this));
					// clear <code>temporaryValue</code> for current
					// transaction "garbage collection" -
					// temporaryValueMap
					// synchronized (trans.temporaryValueMap) {
					trans.temporaryValueMap.remove(this);
					if (trans.temporaryValueMap.isEmpty()) {
						trans.temporaryValueMap = null;
					}
				}
			}
		}
	}

	/**
	 * Needed for graph loading, validation and writing.
	 */
	@Override
	public E getLatestPersistentValue() {
		if (!getGraph().isLoading()) {
			Transaction transaction = getGraph().getCurrentTransaction();
			assert transaction.getState() == TransactionState.VALIDATING
					|| transaction.getState() == TransactionState.WRITING : "TransactionState should be WRITING or VALIDATING, but it's "
					+ transaction.getState() + ".";
			if (transaction.isReadOnly()) {
				throw new GraphException(
						"Read-only-transactions are only allowed to access the persistent values valid at BOT-time.");
			}
		}
		// if there is a single persistent value, return it...
		if (persistentVersionMap == null) {
			return persistentValue;
		}

		// otherwise return the latest stored persistent value...
		synchronized (persistentVersionMap) {
			Long lastKey = persistentVersionMap.lastKey();
			E latestPersistentValue = persistentVersionMap.get(lastKey);
			return latestPersistentValue;
		}
	}

	/**
	 * No synchronization needed here, because maps shouldn't be modified
	 * concurrently. Needed in validation- and writing-phase.
	 * 
	 * @return the current persistent version
	 */
	private long getPersistentVersion() {
		// has there been an implicit change?
		if (dataObjectPersistentVersionMap != null) {
			if (dataObjectPersistentVersionMap.containsKey(this)) {
				// then return the assigned version number
				return dataObjectPersistentVersionMap.get(this);
			}
		}

		// if there is only one persistent value, then return the persistent
		// version number at BOT of the current transaction
		if (persistentVersionMap == null) {
			return ((TransactionImpl) getGraph().getCurrentTransaction()).persistentVersionAtBot;
		}

		synchronized (persistentVersionMap) {
			assert persistentVersionMap.lastKey() != null;
			// return the last assigned version number
			return persistentVersionMap.lastKey();
		}
	}

	@Override
	public long getLatestPersistentVersion() {
		Transaction transaction = getGraph().getCurrentTransaction();
		if (transaction.isReadOnly()) {
			throw new GraphException(
					"Read-only-transactions are only allowed to access the persistent version valid at BOT-time.");
		}
		// this information is only relevant in validation-phase
		assert transaction.getState() == TransactionState.VALIDATING
				|| transaction.getState() == TransactionState.WRITING : "TransactionState should be VALIDATION or WRITING, but it's "
				+ transaction.getState() + ".";
		return getPersistentVersion();
	}

	@Override
	public E getPersistentValueAtBot(Transaction transaction) {
		if (logger != null) {
			logger.finest("tx id=" + transaction.getID() + " of " + this);
		}
		TransactionImpl trans = (TransactionImpl) transaction;
		synchronized (this) {
			if (persistentVersionMap == null) {
				return persistentValue;
			}

			// cut, so that only relevant persistent values are extracted
			synchronized (persistentVersionMap) {
				long firstKey = persistentVersionMap.firstKey();
				long lastRelevantKey = trans.persistentVersionAtBot + 1;
				if (firstKey > lastRelevantKey) {
					return null;
				}
				if (lastRelevantKey > persistentVersionMap.lastKey()) {
					return persistentVersionMap.get(persistentVersionMap
							.lastKey());
				}
				SortedMap<Long, E> subMap = persistentVersionMap.subMap(
						firstKey, lastRelevantKey);
				// if for some reason firstKey and lastRelevantKey are equal,
				// then an empty subMap is returned...
				if (subMap.isEmpty()) {
					return persistentVersionMap.get(persistentVersionMap
							.firstKey());
				}
				return subMap.get(subMap.lastKey());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getTemporaryValue(Transaction transaction) {
		if (logger != null) {
			logger.finest("tx id=" + transaction.getID() + " of " + this);
		}
		if (transaction.isReadOnly()) {
			throw new GraphException(
					"Read-only-transactions don't have temporary values.");
		}
		// TODO could this cause errors?!
		if (!hasTemporaryValue(transaction)) {
			logger.finest("  case1");
			return getPersistentValueAtBot(transaction);
		}
		TransactionImpl trans = (TransactionImpl) transaction;
		// if there exists only a single temporary value, then return it...
		if (trans.temporaryValueMap != null
				&& trans.temporaryValueMap.containsKey(this)) {
			assert trans.temporaryVersionMap == null
					|| !trans.temporaryVersionMap.containsKey(this);
			synchronized (trans.temporaryValueMap) {
				logger.finest("  case2");
				return (E) trans.temporaryValueMap.get(this);
			}
		}
		assert trans.temporaryVersionMap != null;
		SortedMap<Long, Object> transactionMap = trans.temporaryVersionMap
				.get(this);
		assert trans.temporaryVersionMap != null;
		if (trans.latestRestoredSavepoint != null) {
			logger.finest("  case3");
			Long fromKey = transactionMap.firstKey();
			long toKey = trans.latestRestoredSavepoint.versionAtSavepoint + 1;
			// make sure firstKey is always < toKey
			if (fromKey > toKey) {
				fromKey = 0L;
			}
			logger.finest("  fromKey=" + fromKey + ", toKey=" + toKey);
			SortedMap<Long, Object> subMap = transactionMap.subMap(fromKey,
					toKey);
			logger.finest("  subMap=" + subMap);
			// it may happen that subMap is empty, then return null!
			if (subMap.isEmpty()) {
				logger.finest("  case4");
				return null;
			}
			// ...otherwise return the version with the highest version number
			// which is
			// valid for the current savepoint
			logger.finest("  case5");
			return (E) subMap.get(subMap.lastKey());
		}
		logger.finest("  case6");
		return (E) transactionMap.get(transactionMap.lastKey());
	}

	@Override
	public boolean hasTemporaryValue(Transaction transaction) {
		if (transaction.isReadOnly()) {
			return false;
		}
		// no temporary version for transaction?
		TransactionImpl trans = (TransactionImpl) transaction;
		if ((trans.temporaryVersionMap == null
				|| !trans.temporaryVersionMap.containsKey(this) || trans.temporaryVersionMap
				.get(this).size() == 0)
				&& (trans.temporaryValueMap == null || !trans.temporaryValueMap
						.containsKey(this))) {
			return false;
		}
		return true;
	}

	@Override
	public void removeAllTemporaryValues(Transaction transaction) {
		if (logger != null) {
			logger.finer("tx id=" + transaction.getID() + ", do=" + this);
		}
		if (!transaction.isReadOnly()) {
			TransactionImpl trans = (TransactionImpl) transaction;
			if (trans.temporaryVersionMap != null) {
				trans.temporaryVersionMap.remove(this);
				if (trans.temporaryVersionMap.size() == 0) {
					trans.temporaryVersionMap = null;
				}
			}
			if (trans.temporaryValueMap != null) {
				synchronized (trans.temporaryValueMap) {
					trans.temporaryValueMap.remove(this);
					if (trans.temporaryValueMap.size() == 0) {
						trans.temporaryValueMap = null;
					}
				}
			}
		}
	}

	@Override
	public void removePersistentValues(long maxVersionNumber) {
		if (logger != null) {
			logger.finer("maxVersionNumber=" + maxVersionNumber + ", do="
					+ this);
		}
		TransactionManager transactionManager = TransactionManagerImpl
				.getInstance((GraphImpl) getGraph());
		TransactionImpl oldestTransaction = (TransactionImpl) transactionManager
				.getTransactions().get(0);
		if (oldestTransaction.persistentVersionAtBot < maxVersionNumber
				&& oldestTransaction != getGraph().getCurrentTransaction()
				&& transactionManager.getTransactions().size() > 1) {
			throw new GraphException(
					"Cannot remove persistent versions with version number < "
							+ maxVersionNumber
							+ ", because there is at least one transaction which needs a reference.");
		}
		synchronized (this) {
			// there is only one persistent value for this versioned data
			// object? then return...
			if (persistentVersionMap == null
					|| persistentVersionMap.size() == 0) {
				return;
			}

			// this can happen, if this versioned data object is part of a newly
			// added GraphElement within a transaction, which at initialization
			// point don't have any persistent values at all
			if (persistentVersionMap.firstKey() > maxVersionNumber) {
				return;
			}

			// find the maximum version number which still is referenced by the
			// oldest transaction
			long firstKey = persistentVersionMap.firstKey();
			if (persistentVersionMap.size() > 1
					&& transactionManager.getTransactions().size() == 1
					&& maxVersionNumber == 0) {
				firstKey++;
			}
			long lastKey = maxVersionNumber + 1;
			SortedMap<Long, E> subMap = persistentVersionMap.subMap(firstKey,
					lastKey);
			// this is the minimum version number which is still referenced by
			// the oldest currently running transaction
			if (!subMap.isEmpty()) {
				long minReferencedVersion = subMap.lastKey();

				SortedMap<Long, E> tmp = new TreeMap<Long, E>();
				tmp.putAll(persistentVersionMap.tailMap(minReferencedVersion));
				persistentVersionMap.clear();
				persistentVersionMap = tmp;
				// -----------------------------------------------------------//
				if (dataObjectPersistentVersionMap != null) {
					if (dataObjectPersistentVersionMap.containsKey(this)
							&& dataObjectPersistentVersionMap.get(this) < maxVersionNumber) {
						dataObjectPersistentVersionMap.remove(this);
					}
				}
				movePersistentValue();
			}
		}
	}

	@Override
	public synchronized void removePersistentValues(long minRange, long maxRange) {
		if (logger != null) {
			logger.finer("minRange=" + minRange + ", maxRange=" + maxRange);
		}
		if (minRange > maxRange) {
			throw new GraphException("minRange should always be <= maxRange.");
		}
		// no real range here...
		if (minRange == maxRange) {
			return;
		}
		// so there is only one persistent value...
		if (persistentVersionMap == null || persistentVersionMap.size() == 0) {
			return;
		}
		// all version numbers are less than minRange
		if (persistentVersionMap.lastKey() < minRange) {
			return;
		}
		if (persistentVersionMap.lastKey() < maxRange) {
			maxRange = persistentVersionMap.lastKey() - 1;
		}
		if (minRange < maxRange) {
			// persistentVersionMap = new TreeMap<Long,
			// E>(persistentVersionMap);
			SortedMap<Long, E> rangeVersions = persistentVersionMap.subMap(
					minRange, (maxRange + 1));
			if (rangeVersions.size() > 0) {
				maxRange = rangeVersions.lastKey();
				if (minRange + 1 <= maxRange) {
					// these is the map containing all mappings which are not
					// yneeded
					SortedMap<Long, E> nonReferencedVersions = persistentVersionMap
							.subMap(minRange + 1, maxRange);
					// Set of non-referenced version numbers
					Set<Long> versionsSet = new HashSet<Long>(
							nonReferencedVersions.keySet());
					nonReferencedVersions = null;
					// remove from map
					for (Long version : versionsSet) {
						persistentVersionMap.remove(version);
					}
					// TODO "garbage collection" with
					// dataObjectPersistentVersionMap.get(this)?!
				}
			}
			movePersistentValue();
		}
	}

	/**
	 * Moves persistent value from persistentVersionMap to persistentValue if
	 * there is only 1 persistent value left.
	 */
	private synchronized void movePersistentValue() {
		if (logger != null) {
			logger.finer("");
		}
		synchronized (persistentVersionMap) {
			if (persistentVersionMap.size() == 1) {
				E value = persistentVersionMap.get(persistentVersionMap
						.firstKey());

				persistentValue = value;

				persistentVersionMap.clear();
				persistentVersionMap = null;
				if (dataObjectPersistentVersionMap != null) {
					if (dataObjectPersistentVersionMap.containsKey(this)) {
						dataObjectPersistentVersionMap.remove(this);
					}
					if (dataObjectPersistentVersionMap.size() == 0) {
						dataObjectPersistentVersionMap = null;
					}
				}
			}
		}
	}

	@Override
	public void removeTemporaryValues(long minVersion, Transaction transaction) {
		if (logger != null) {
			logger.finer("tx id=" + transaction.getID() + ", minVersion="
					+ minVersion);
		}
		synchronized (transaction) {
			if (!transaction.isReadOnly() && hasTemporaryValue(transaction)) {
				TransactionImpl trans = (TransactionImpl) transaction;
				SavepointImpl latestDefinedSavepoint = trans.latestDefinedSavepoint;
				if (latestDefinedSavepoint != null) {
					assert latestDefinedSavepoint.isValid();
					if (latestDefinedSavepoint.versionAtSavepoint > minVersion) {
						throw new GraphException(
								"Trying to remove temporary versions which are still needed.");
					}
				}
				if (trans.temporaryVersionMap != null) {
					SortedMap<Long, Object> transactionMap = trans.temporaryVersionMap
							.get(this);
					transactionMap = transactionMap.headMap(minVersion + 1);
					trans.temporaryVersionMap.put(this, transactionMap);
				}
			}
		}
	}

	@Override
	public void setNewPersistentValue(E dataObject, boolean explicitChange) {
		if (logger != null) {
			logger.finer("do=" + dataObject + ", explicitChange="
					+ explicitChange);
		}
		TransactionImpl transaction = (TransactionImpl) getGraph()
				.getCurrentTransaction();
		assert transaction.getState() == TransactionState.WRITING : "TransactionState should be WRITING, but it's "
				+ transaction.getState() + ".";
		synchronized (this) {
			if (persistentVersionMap == null) {
				persistentVersionMap = new TreeMap<Long, E>();
			}
			long version = ((GraphImpl) getGraph())
					.incrPersistentVersionCounter();

			if (persistentVersionMap.size() == 0) {
				// TODO check if this changes anything!!!
				TransactionImpl oldestTransaction = (TransactionImpl) ((TransactionManagerImpl) TransactionManagerImpl
						.getInstance((GraphImpl) getGraph()))
						.getOldestTransaction();
				persistentVersionMap.put(
						oldestTransaction.persistentVersionAtBot,
						persistentValue);
				persistentValue = null;
			}

			// the new version which is created because of the COMMIT of the
			// current transaction
			persistentVersionMap.put(version, dataObject);
			changedPersistently();
		}
	}

	/**
	 * Handles needed steps concerning implicit/ explicit change.
	 * 
	 * @param explicitChange
	 */
	private synchronized void handlePersistentChange(boolean explicitChange) {
		if (logger != null) {
			logger.finer("explicitChange=" + explicitChange);
		}
		// implicit change
		if (!explicitChange) {
			if (isImplicitChangeMarkNeeded()) {
				if (dataObjectPersistentVersionMap == null) {
					dataObjectPersistentVersionMap = new HashMap<VersionedDataObject<?>, Long>(
							1, TransactionManagerImpl.LOAD_FACTOR);
				}
				// only remark implicit change if there is no entry
				if (!dataObjectPersistentVersionMap.containsKey(this)) {
					long version = 0;
					version = getLatestPersistentVersion();
					dataObjectPersistentVersionMap.put(this, version);
				}
			}
			// explicit change
		} else {
			if (dataObjectPersistentVersionMap != null) {
				dataObjectPersistentVersionMap.remove(this);
				if (dataObjectPersistentVersionMap.size() == 0) {
					dataObjectPersistentVersionMap = null;
				}
			}
		}
	}

	@Override
	public void setPersistentValue(E dataObject) {
		if (logger != null) {
			logger.finer("do=" + dataObject);
		}
		if (!getGraph().isLoading()) {
			Transaction transaction = getGraph().getCurrentTransaction();
			if (transaction == null) {
				throw new GraphException("Current transaction is null.");
			}
			assert transaction.getState() == TransactionState.WRITING : "TransactionState should be WRITING, but it's "
					+ transaction.getState() + ".";
		}
		synchronized (this) {
			if (persistentVersionMap == null) {
				persistentValue = dataObject;
			} else {
				persistentVersionMap.put(persistentVersionMap.lastKey(),
						dataObject);
			}
			changedPersistently();
		}
	}

	@Override
	public void setTemporaryValue(E dataObject, Transaction transaction) {
		if (logger != null) {
			logger.finer("tx id=" + transaction.getID() + ", do=" + dataObject);
		}
		assert transaction.getState() == TransactionState.RUNNING : "TransactionState should be RUNNING, but it's "
				+ transaction.getState() + ".";
		if (transaction.isReadOnly()) {
			throw new GraphException(
					"Read-only-transactions are not allowed to own temporary values.");
		}
		TransactionImpl trans = (TransactionImpl) transaction;
		// decide where to put the temporary value...
		if (trans.temporaryValueMap != null
				&& trans.temporaryValueMap.keySet().contains(this)
				|| trans.temporaryVersionMap == null) {
			assert trans.temporaryVersionMap == null
					|| !trans.temporaryVersionMap.containsKey(this);
			synchronized (trans.temporaryValueMap) {
				trans.temporaryValueMap.put(this, dataObject);
			}
		} else {
			synchronized (trans.temporaryVersionMap) {
				assert trans.temporaryValueMap == null
						|| !trans.temporaryValueMap.containsKey(this);
				SortedMap<Long, Object> transactionMap = trans.temporaryVersionMap
						.get(this);
				assert transactionMap != null;
				transactionMap.put(transactionMap.lastKey(), dataObject);
			}
		}
	}

	/**
	 * Returns the valid value for the given <code>transaction</code> depending
	 * on his current state and depending on the result of
	 * {@link TransactionImpl#isReadOnly() <code>transaction</code>
	 * .isReadOnly()}
	 * 
	 * @param transaction
	 * @return the valid value for <code>transaction</code>
	 */
	public E getValidValue(Transaction transaction) {
		if (getGraph().isLoading()) {
			return getLatestPersistentValue();
		}
		if (!transaction.isValid()) {
			throw new GraphException("Current transaction is no longer valid.");
		}
		assert transaction.getState() != TransactionState.NOTRUNNING : "TransactionState should be NOTRUNNING, but it's "
				+ transaction.getState() + ".";
		// read-only-transactions only have access to persistent version
		// valid at BOT-time
		if (transaction.isReadOnly()) {
			assert transaction.getState() == TransactionState.RUNNING : "TransactionState should be RUNNING, but it's "
					+ transaction.getState() + ".";
			return getPersistentValueAtBot(transaction);
		}
		// if transaction has executed commit and now wants to store the
		// changes persistently
		if (transaction.getState() == TransactionState.WRITING) {
			return getLatestPersistentValue();
		}
		assert transaction.getState() == TransactionState.RUNNING
				|| transaction.getState() == TransactionState.VALIDATING : "TransactionState should be RUNNING or VALIDATING, but it's "
				+ transaction.getState() + ".";
		// if transaction has no temporary versions and is RUNNING, just
		// return the persistent version valid at BOT-time
		if (!hasTemporaryValue(transaction)) {
			assert transaction.getState() == TransactionState.RUNNING : "TransactionState should be RUNNING, but it's "
					+ transaction.getState() + ".";
			return getPersistentValueAtBot(transaction);
		}
		return getTemporaryValue(transaction);
	}

	/**
	 * Sets a new value for the currently valid temporary or persistent version
	 * of <code>transaction</code>.
	 * 
	 * @param dataObject
	 * @param transaction
	 */
	public void setValidValue(E dataObject, Transaction transaction) {
		if (logger != null) {
			if (transaction == null) {
				logger.finer("tx id=null, do=" + dataObject);
			} else {
				logger.finer("tx id=" + transaction.getID() + ", do="
						+ dataObject);

			}
		}
		internalSetValidValue(dataObject, transaction, true);
	}

	/**
	 * Sets a new value for the currently valid temporary or persistent version
	 * of a <code>transaction</code>.
	 * 
	 * Should only be used for versioned data-objects for which a distinction
	 * between implicit and explicit change is needed.
	 * 
	 * @param dataObject
	 * @param transaction
	 * @param explicitChange
	 */
	protected void setValidValue(E dataObject, Transaction transaction,
			boolean explicitChange) {
		if (logger != null) {
			logger.finer("tx id=" + transaction.getID() + ", do=" + dataObject
					+ ", explicitChange=" + explicitChange);
		}
		internalSetValidValue(dataObject, transaction, explicitChange);
	}

	/**
	 * Sets the valid value for the given <code>transaction</code> depending on
	 * his current state and depending on the result of
	 * {@link TransactionImpl#isReadOnly() <code>transaction</code>
	 * .isReadOnly()}
	 * 
	 * Not allowed for read-only-transactions
	 * 
	 * @param dataObject
	 * @param transaction
	 * @param incrVersionNumber
	 */
	private void internalSetValidValue(E dataObject, Transaction transaction,
			boolean explicitChange) {
		if (getGraph().isLoading()) {
			setPersistentValue(dataObject);
		} else {
			if (!transaction.isValid()) {
				throw new GraphException(
						"Current transaction is no longer valid.");
			}
			assert transaction.getState() != TransactionState.NOTRUNNING : "TransactionState should be NOTRUNNING, but it's "
					+ transaction.getState() + ".";
			// read-only
			if (transaction.isReadOnly()) {
				throw new GraphException(
						"Read-only transactions are not allowed to execute write-operations.");
			}
			TransactionImpl trans = (TransactionImpl) transaction;
			// read-write TransactionState.RUNNING
			if (transaction.getState() == TransactionState.RUNNING) {
				// TODO move this all to setTemporaryValue?
				// TODO only create new temporary value, if it doesn't exist any
				// temporary value and the given value to the persistent value
				// at BOT for transaction
				if (!hasTemporaryValue(transaction)) {
					createNewTemporaryValue(transaction);
				} else {
					handleSavepoint(trans);
				}
				assert hasTemporaryValue(transaction);
				// set new value for current temporary version
				setTemporaryValue(dataObject, transaction);
			}
			if (transaction.getState() == TransactionState.WRITING) {
				// if(trans.persistentVersionAtCommit <= getPersistentVersion())
				handlePersistentChange(explicitChange);
				// if there has been already created a new persistent value...
				if (getPersistentVersion() > trans.persistentVersionAtCommit
						|| !isLatestPersistentValueReferenced()) {
					setPersistentValue(dataObject);
				} else {
					// otherwise create new persistent value.
					setNewPersistentValue(dataObject, explicitChange);
				}
			}
		}
	}

	/**
	 * Mark persistent change for current transaction.
	 */
	private void changedPersistently() {
		// not needed, if graph is loading...
		if (getGraph().isLoading()) {
			return;
		}
		if (logger != null) {
			logger.finer(this.toString());
		}
		TransactionImpl transaction = (TransactionImpl) getGraph()
				.getCurrentTransaction();
		if (transaction.changedDuringCommit == null) {
			transaction.changedDuringCommit = new HashSet<VersionedDataObjectImpl<?>>(
					1, TransactionManagerImpl.LOAD_FACTOR);
		}
		transaction.changedDuringCommit.add(this);
	}

	/**
	 * Makes sure that a new temporary version is created if a save-point has
	 * been defined. Also assured that unneeded and invalid save-points are
	 * removed after restoring a defined save-point.
	 * 
	 * @param transaction
	 */
	public void handleSavepoint(TransactionImpl transaction) {
		if (transaction.isReadOnly()) {
			return;
		}
		if (logger != null) {
			logger.finer("tx id=" + transaction.getID());
		}
		SavepointImpl latestDefinedSavepoint = transaction.latestDefinedSavepoint;
		if (latestDefinedSavepoint != null) {
			// remove all invalid save-points after first change
			if (transaction.latestRestoredSavepoint != null) {
				transaction.removeInvalidSavepoints();
			}
			// new temporary value for ensuring save-point validity
			long temporaryVersion = getTemporaryVersion(transaction);
			if (temporaryVersion <= latestDefinedSavepoint.versionAtSavepoint) {
				createNewTemporaryValue(transaction);
			}
		}
	}

	@Override
	public long getTemporaryVersion(Transaction transaction) {
		assert transaction.getState() == TransactionState.RUNNING
				&& !transaction.isReadOnly() : "TransactionState should be RUNNING or is read only, but it's "
				+ transaction.getState()
				+ " and readOnly: "
				+ transaction.isReadOnly() + ".";
		synchronized (transaction) {
			if (!hasTemporaryValue(transaction)) {
				return 0;
			}
			TransactionImpl trans = (TransactionImpl) transaction;
			if (trans.temporaryVersionMap != null) {
				synchronized (trans.temporaryVersionMap) {
					SortedMap<Long, Object> transactionMap = trans.temporaryVersionMap
							.get(this);
					if (transactionMap != null && transactionMap.size() > 0) {
						return transactionMap.lastKey();
					}
				}
			}
			return 0;
		}
	}

	/**
	 * Generalizes the expanding of arrays.
	 * 
	 * @param newSize
	 *            the new size of the array
	 * @param initExpandedArray
	 *            the initial expanded array which is cloned for every array to
	 *            be extended
	 */
	@SuppressWarnings("unchecked")
	private void expandArray(int newSize,
			AttributedElement<?, ?>[] initExpandedArray) {
		List<Transaction> transactionList = TransactionManagerImpl.getInstance(
				(GraphImpl) getGraph()).getTransactions();
		synchronized (transactionList) {
			for (Transaction transaction : transactionList) {
				TransactionImpl trans = (TransactionImpl) transaction;
				if (trans.temporaryVersionMap != null) {
					synchronized (trans.temporaryVersionMap) {
						SortedMap<Long, Object> temporaryValues = trans.temporaryVersionMap
								.get(this);
						SortedMap<Long, Object> copyTemporaryValues = new TreeMap<Long, Object>(
								temporaryValues);
						// expand all temporary values if available
						if (trans.isValid() && copyTemporaryValues != null) {
							for (Entry<Long, Object> e : copyTemporaryValues
									.entrySet()) {
								AttributedElement<?, ?>[] expandedArray = null;
								expandedArray = initExpandedArray.clone();
								AttributedElement<?, ?>[] oldVertex = (AttributedElement[]) e
										.getValue();
								System.arraycopy(oldVertex, 0, expandedArray,
										0, oldVertex.length);
								temporaryValues.put(e.getKey(), expandedArray);
							}
						}
					}
				}
			}
			for (Transaction transaction : transactionList) {
				TransactionImpl trans = (TransactionImpl) transaction;
				if (trans.temporaryValueMap != null) {
					synchronized (trans.temporaryValueMap) {
						Map<VersionedDataObject<?>, Object> copyTemporaryValueMap = new HashMap<VersionedDataObject<?>, Object>(
								trans.temporaryValueMap);
						if (trans.isValid()
								&& copyTemporaryValueMap.containsKey(this)) {
							AttributedElement<?, ?>[] oldVertex = (AttributedElement[]) copyTemporaryValueMap
									.get(this);
							AttributedElement<?, ?>[] expandedArray = null;
							expandedArray = initExpandedArray.clone();
							System.arraycopy(oldVertex, 0, expandedArray, 0,
									oldVertex.length);
							trans.temporaryValueMap.put(this, expandedArray);
						}
					}
				}
			}
		}
		if (persistentVersionMap != null) {
			Set<Long> persistentVersions = persistentVersionMap.keySet();
			if (persistentVersions != null) {
				for (Long version : persistentVersions) {
					AttributedElement<?, ?>[] oldVertex = (AttributedElement[]) persistentVersionMap
							.get(version);
					AttributedElement<?, ?>[] expandedArray = null;
					expandedArray = initExpandedArray.clone();
					System.arraycopy(oldVertex, 0, expandedArray, 0,
							oldVertex.length);
					persistentVersionMap.put(version, (E) expandedArray);
				}
			}
		}
		if (persistentValue != null) {
			AttributedElement<?, ?>[] oldVertex = (AttributedElement[]) persistentValue;

			AttributedElement<?, ?>[] expandedArray = null;
			expandedArray = initExpandedArray.clone();
			System.arraycopy(oldVertex, 0, expandedArray, 0, oldVertex.length);

			persistentValue = (E) expandedArray;
		}
	}

	/**
	 * Needed to expand ALL currently existing values of Array vertex.
	 * 
	 * @param newSize
	 */
	protected synchronized void expandVertexArrays(int newSize) {
		((GraphImpl) getGraph()).vertexSync.writeLock().lock();
		expandArray(newSize, new VertexImpl[newSize + 1]);
		((GraphImpl) getGraph()).vertexSync.writeLock().unlock();
	}

	/**
	 * Needed to expand ALL currently existing values of Array edge.
	 * 
	 * @param newSize
	 */
	protected synchronized void expandEdgeArrays(int newSize) {
		((GraphImpl) getGraph()).edgeSync.writeLock().lock();
		expandArray(newSize, new EdgeImpl[newSize + 1]);
		((GraphImpl) getGraph()).edgeSync.writeLock().unlock();
	}

	/**
	 * Needed to expand ALL currently existing values of Array revEdge.
	 * 
	 * @param transaction
	 * @param newSize
	 */
	protected synchronized void expandRevEdgeArrays(int newSize) {
		((GraphImpl) getGraph()).edgeSync.writeLock().lock();
		expandArray(newSize, new ReversedEdgeImpl[newSize + 1]);
		((GraphImpl) getGraph()).edgeSync.writeLock().unlock();
	}

	/**
	 * @return the name of the versioned attribute.
	 */
	@Override
	public String toString() {
		if (name != null) {
			return name + " of " + attributedElement;
		}
		return "hash " + hashCode() + " of " + attributedElement;
	}

	/**
	 * Needed for attributes.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Needed for writing phase only.
	 * 
	 * @return if the latest persistent value of this versioned data-object is
	 *         referenced by another transaction; if so a new persistent version
	 *         has to be created for this versioned data-object
	 */
	protected boolean isLatestPersistentValueReferenced() {
		if (belongsToNewAddedGraphElement()) {
			return false;
		}
		List<Transaction> transactionList = TransactionManagerImpl.getInstance(
				(GraphImpl) getGraph()).getTransactions();
		// if (persistentVersionMap == null && transactionList.size() > 1)
		// return true;
		long latestPersistentVersion = getLatestPersistentVersion();
		synchronized (transactionList) {
			Transaction currentTransaction = getGraph().getCurrentTransaction();
			for (Transaction transaction : transactionList) {
				TransactionImpl trans = (TransactionImpl) transaction;
				if (trans != currentTransaction) {
					// if another transaction is referencing the latest
					// persistent value
					if (trans.persistentVersionAtBot >= latestPersistentVersion) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/**
	 * Needed for writing-phase only.
	 * 
	 * @return if it is necessary to mark an implicit change of this versioned
	 *         data-object.
	 */
	private boolean isImplicitChangeMarkNeeded() {
		if (belongsToNewAddedGraphElement()) {
			return false;
		}
		List<Transaction> transactionList = TransactionManagerImpl.getInstance(
				(GraphImpl) getGraph()).getTransactions();
		synchronized (transactionList) {
			Transaction currentTransaction = getGraph().getCurrentTransaction();
			for (Transaction transaction : transactionList) {
				TransactionImpl trans = (TransactionImpl) transaction;
				if (trans != currentTransaction) {
					// latest persistent version of data-object <= trans.pvAtBot
					// < new persistent version of data-object
					if (getLatestPersistentVersion() <= trans.persistentVersionAtBot
							&& trans.persistentVersionAtBot < ((GraphImpl) getGraph())
									.getPersistentVersionCounter() + 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Check whether this versioned data object belongs to a graph element which
	 * has been added within the current transaction. If so, this versioned data
	 * object isn't referenced by any other transaction.
	 * 
	 * @return if this versioned data object has been added within the current
	 *         transaction.
	 */
	private boolean belongsToNewAddedGraphElement() {
		TransactionImpl currentTransaction = (TransactionImpl) getGraph()
				.getCurrentTransaction();
		if (attributedElement != null) {
			if (attributedElement instanceof Vertex) {
				VertexImpl[] vertexArray = ((GraphImpl) getGraph()).vertex
						.getPersistentValueAtBot(currentTransaction);
				if (vertexArray[((Vertex) attributedElement).getId()] == null) {
					return true;
				}
			}
			if (attributedElement instanceof Edge) {
				EdgeImpl[] edgeArray = ((GraphImpl) getGraph()).edge
						.getPersistentValueAtBot(currentTransaction);
				if (edgeArray[((Edge) attributedElement).getId()] == null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param transaction
	 * @param changeValueAfterwards
	 * @return
	 */
	protected E getValidValueBeforeValueChange(Transaction transaction) {
		prepareValueChangeAfterReference(transaction);
		return getValidValue(transaction);
	}

	/**
	 * 
	 * @param transaction
	 */
	protected void prepareValueChangeAfterReference(Transaction transaction) {
		if (!getGraph().isLoading()) {
			if (transaction.getState() == TransactionState.RUNNING) {
				handleSavepoint((TransactionImpl) transaction);
				if (!hasTemporaryValue(transaction)) {
					createNewTemporaryValue(transaction);
				}
			}
		}
	}
}
