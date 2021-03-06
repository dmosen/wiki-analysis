/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std;

import de.uni_koblenz.jgralab.impl.GraphFactoryImpl;

import de.uni_koblenz.jgralab.ImplementationType;

import de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSGraphFactory;

public class SimpleRDBMSGraphFactoryImpl extends GraphFactoryImpl implements SimpleRDBMSGraphFactory {

	public SimpleRDBMSGraphFactoryImpl() {
		super(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSSchema.instance(), ImplementationType.STANDARD);
		createMaps();
		setGraphImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.SimpleRDBMSGraph.GC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.SimpleRDBMSGraphImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RModelElement.VC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.RModelElementImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Key.VC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.KeyImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Schema.VC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.SchemaImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Column.VC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.ColumnImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.Table.VC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.TableImpl.class);
		setVertexImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.ForeignKey.VC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.ForeignKeyImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIdentifiedBy.EC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.IsIdentifiedByImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasTable.EC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.HasTableImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasColumn.EC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.HasColumnImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasPrimaryKey.EC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.HasPrimaryKeyImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.HasForeignKey.EC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.HasForeignKeyImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.IsIn.EC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.IsInImpl.class);
		setEdgeImplementationClass(de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.RefersTo.EC, de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.RefersToImpl.class);
	}
}
