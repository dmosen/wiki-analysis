/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.commenttest.impl.trans;

import de.uni_koblenz.jgralab.impl.GraphFactoryImpl;

import de.uni_koblenz.jgralab.ImplementationType;

import de.uni_koblenz.jgralabtest.schemas.commenttest.CommentTestGraphFactory;

public class CommentTestGraphFactoryImpl extends GraphFactoryImpl implements CommentTestGraphFactory {

	public CommentTestGraphFactoryImpl() {
		super(de.uni_koblenz.jgralabtest.schemas.commenttest.CommentTestSchema.instance(), ImplementationType.TRANSACTION);
		createMaps();
		setGraphImplementationClass(de.uni_koblenz.jgralabtest.schemas.commenttest.CommentTestGraph.GC, de.uni_koblenz.jgralabtest.schemas.commenttest.impl.trans.CommentTestGraphImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.V.VC, de.uni_koblenz.jgralabtest.schemas.commenttest.impl.trans.pa.VImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W.VC, de.uni_koblenz.jgralabtest.schemas.commenttest.impl.trans.pa.pc.WImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.E.EC, de.uni_koblenz.jgralabtest.schemas.commenttest.impl.trans.pa.EImpl.class);
	}
}
