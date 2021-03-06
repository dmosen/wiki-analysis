/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.minimal.impl.trans;

import de.uni_koblenz.jgralab.impl.GraphFactoryImpl;

import de.uni_koblenz.jgralab.ImplementationType;

import de.uni_koblenz.jgralabtest.schemas.minimal.MinimalGraphFactory;

public class MinimalGraphFactoryImpl extends GraphFactoryImpl implements MinimalGraphFactory {

	public MinimalGraphFactoryImpl() {
		super(de.uni_koblenz.jgralabtest.schemas.minimal.MinimalSchema.instance(), ImplementationType.TRANSACTION);
		createMaps();
		setGraphImplementationClass(de.uni_koblenz.jgralabtest.schemas.minimal.MinimalGraph.GC, de.uni_koblenz.jgralabtest.schemas.minimal.impl.trans.MinimalGraphImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.minimal.Node.VC, de.uni_koblenz.jgralabtest.schemas.minimal.impl.trans.NodeImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.minimal.Link.EC, de.uni_koblenz.jgralabtest.schemas.minimal.impl.trans.LinkImpl.class);
	}
}
