/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.constrained.impl.db;

import de.uni_koblenz.jgralab.impl.GraphFactoryImpl;

import de.uni_koblenz.jgralab.ImplementationType;

import de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedGraphFactory;

public class ConstrainedGraphFactoryImpl extends GraphFactoryImpl implements ConstrainedGraphFactory {

	public ConstrainedGraphFactoryImpl() {
		super(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedSchema.instance(), ImplementationType.DATABASE);
		createMaps();
		setGraphImplementationClass(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedGraph.GC, de.uni_koblenz.jgralabtest.schemas.constrained.impl.db.ConstrainedGraphImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedNode.VC, de.uni_koblenz.jgralabtest.schemas.constrained.impl.db.ConstrainedNodeImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.constrained.OtherConstrainedNode.VC, de.uni_koblenz.jgralabtest.schemas.constrained.impl.db.OtherConstrainedNodeImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.constrained.ConstrainedLink.EC, de.uni_koblenz.jgralabtest.schemas.constrained.impl.db.ConstrainedLinkImpl.class);
	}
}
