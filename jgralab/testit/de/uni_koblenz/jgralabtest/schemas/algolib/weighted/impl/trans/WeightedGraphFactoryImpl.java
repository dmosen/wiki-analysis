/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.algolib.weighted.impl.trans;

import de.uni_koblenz.jgralab.impl.GraphFactoryImpl;

import de.uni_koblenz.jgralab.ImplementationType;

import de.uni_koblenz.jgralabtest.schemas.algolib.weighted.WeightedGraphFactory;

public class WeightedGraphFactoryImpl extends GraphFactoryImpl implements WeightedGraphFactory {

	public WeightedGraphFactoryImpl() {
		super(de.uni_koblenz.jgralabtest.schemas.algolib.weighted.WeightedSchema.instance(), ImplementationType.TRANSACTION);
		createMaps();
		setGraphImplementationClass(de.uni_koblenz.jgralabtest.schemas.algolib.weighted.WeightedGraph.GC, de.uni_koblenz.jgralabtest.schemas.algolib.weighted.impl.trans.WeightedGraphImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.algolib.weighted.Location.VC, de.uni_koblenz.jgralabtest.schemas.algolib.weighted.impl.trans.LocationImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.algolib.weighted.Way.EC, de.uni_koblenz.jgralabtest.schemas.algolib.weighted.impl.trans.WayImpl.class);
	}
}
