/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms;

import de.uni_koblenz.jgralab.impl.db.GraphDatabase;
import de.uni_koblenz.jgralab.impl.db.GraphDatabaseException;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralab.schema.impl.SchemaImpl;

import de.uni_koblenz.jgralab.schema.EdgeClass;
import de.uni_koblenz.jgralab.schema.GraphClass;
import de.uni_koblenz.jgralab.schema.VertexClass;

import java.lang.ref.WeakReference;

/**
 * The schema SimpleRDBMSSchema is implemented following the singleton pattern.
 * To get the instance, use the static method <code>instance()</code>.
 */
public class SimpleRDBMSSchema extends SchemaImpl {
	public final GraphClass gc_SimpleRDBMSGraph;
	public final VertexClass vc_RModelElement;
	public final VertexClass vc_Key;
	public final VertexClass vc_Schema;
	public final VertexClass vc_Column;
	public final VertexClass vc_Table;
	public final VertexClass vc_ForeignKey;
	public final EdgeClass ec_IsIdentifiedBy;
	public final EdgeClass ec_HasTable;
	public final EdgeClass ec_HasColumn;
	public final EdgeClass ec_HasPrimaryKey;
	public final EdgeClass ec_HasForeignKey;
	public final EdgeClass ec_IsIn;
	public final EdgeClass ec_RefersTo;

	/**
	 * the weak reference to the singleton instance
	 */
	static WeakReference<SimpleRDBMSSchema> theInstance = new WeakReference<SimpleRDBMSSchema>(null);
	
	/**
	 * @return the singleton instance of SimpleRDBMSSchema
	 */
	public static SimpleRDBMSSchema instance() {
		SimpleRDBMSSchema s = theInstance.get();
		if (s != null) {
			return s;
		}
		synchronized (SimpleRDBMSSchema.class) {
			s = theInstance.get();
			if (s != null) {
				return s;
			}
			s = new SimpleRDBMSSchema();
			theInstance = new WeakReference<SimpleRDBMSSchema>(s);
		}
		return s;
	}
	
	/**
	 * Creates a SimpleRDBMSSchema and builds its schema classes.
	 * This constructor is private. Use the <code>instance()</code> method
	 * to acess the schema.
	 */
	private SimpleRDBMSSchema() {
		super("SimpleRDBMSSchema", "de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms");

		{
			GraphClass gc = gc_SimpleRDBMSGraph = createGraphClass("SimpleRDBMSGraph");
			gc.setAbstract(false);
			@SuppressWarnings("unused")
			VertexClass vc_Vertex = getDefaultVertexClass();

			{
				VertexClass vc = vc_RModelElement = gc.createVertexClass("RModelElement");
				vc.setAbstract(false);
				vc.addAttribute(createAttribute("kind", getDomain("String"), getAttributedElementClass("RModelElement"), null));
				vc.addAttribute(createAttribute("name", getDomain("String"), getAttributedElementClass("RModelElement"), null));
			}

			{
				VertexClass vc = vc_Key = gc.createVertexClass("Key");
				vc.setAbstract(false);
				vc.addSuperClass(vc_RModelElement);
			}

			{
				VertexClass vc = vc_Schema = gc.createVertexClass("Schema");
				vc.setAbstract(false);
				vc.addSuperClass(vc_RModelElement);
			}

			{
				VertexClass vc = vc_Column = gc.createVertexClass("Column");
				vc.setAbstract(false);
				vc.addSuperClass(vc_RModelElement);
				vc.addAttribute(createAttribute("type", getDomain("String"), getAttributedElementClass("Column"), null));
			}

			{
				VertexClass vc = vc_Table = gc.createVertexClass("Table");
				vc.setAbstract(false);
				vc.addSuperClass(vc_RModelElement);
			}

			{
				VertexClass vc = vc_ForeignKey = gc.createVertexClass("ForeignKey");
				vc.setAbstract(false);
				vc.addSuperClass(vc_RModelElement);
			}

			{
				EdgeClass ec = ec_IsIdentifiedBy = gc.createEdgeClass("IsIdentifiedBy",
					vc_Key, 0, 1, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_Column, 1, 2147483647, "column", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_HasTable = gc.createEdgeClass("HasTable",
					vc_Schema, 1, 1, "schema", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_Table, 0, 2147483647, "table", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_HasColumn = gc.createEdgeClass("HasColumn",
					vc_Table, 1, 1, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_Column, 0, 2147483647, "column", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_HasPrimaryKey = gc.createEdgeClass("HasPrimaryKey",
					vc_Table, 1, 1, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_Key, 0, 1, "key", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_HasForeignKey = gc.createEdgeClass("HasForeignKey",
					vc_Table, 1, 1, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_ForeignKey, 0, 2147483647, "foreignKey", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_IsIn = gc.createEdgeClass("IsIn",
					vc_ForeignKey, 0, 1, "foreignKey", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_Column, 1, 1, "column", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_RefersTo = gc.createEdgeClass("RefersTo",
					vc_ForeignKey, 0, 2147483647, "referrer", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_Key, 1, 1, "referred", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(false);
			}
		}

		finish();
	}

	@Override
	public de.uni_koblenz.jgralab.GraphFactory createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType implementationType) {
		switch(implementationType) {
			case GENERIC:
				return new de.uni_koblenz.jgralab.impl.generic.GenericGraphFactoryImpl(this);
			case STANDARD:
				return new de.uni_koblenz.jgralabtest.schemas.gretl.qvt.simplerdbms.impl.std.SimpleRDBMSGraphFactoryImpl();
		}
		throw new UnsupportedOperationException("No " + implementationType + " support compiled.");
	}

	/**
	 * Creates a new SimpleRDBMSGraph graph.
	*/
	public SimpleRDBMSGraph createSimpleRDBMSGraph(de.uni_koblenz.jgralab.ImplementationType implType) {
		return createSimpleRDBMSGraph(implType, null, 100, 100);
	}

	/**
	 * Creates a new SimpleRDBMSGraph graph with initial vertex and edge counts <code>vMax</code>, <code>eMax</code>.
	 *
	 * @param vMax initial vertex count
	 * @param eMax initial edge count
	*/
	public SimpleRDBMSGraph createSimpleRDBMSGraph(de.uni_koblenz.jgralab.ImplementationType implType, String id, int vMax, int eMax) {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return factory.createGraph(gc_SimpleRDBMSGraph, id, vMax, eMax);
	}

	/**
	 * Creates a new SimpleRDBMSGraph graph.
	*/
	public SimpleRDBMSGraph createSimpleRDBMSGraph(de.uni_koblenz.jgralab.GraphFactory factory) {
		return factory.createGraph(gc_SimpleRDBMSGraph, null, 100, 100);
	}

	/**
	 * Creates a new SimpleRDBMSGraph graph.
	*/
	public SimpleRDBMSGraph createSimpleRDBMSGraph(de.uni_koblenz.jgralab.GraphFactory factory, String id, int vMax, int eMax) {
		return factory.createGraph(gc_SimpleRDBMSGraph, id, vMax, eMax);
	}

	/**
	 * Creates a new SimpleRDBMSGraph graph in a database with given <code>id</code>.
	 *
	 * @param id Identifier of new graph
	 * @param graphDatabase Database which should contain graph
	 */
	public SimpleRDBMSGraph createSimpleRDBMSGraph(String id, GraphDatabase graphDatabase) throws GraphDatabaseException{
		return createSimpleRDBMSGraph(id, 100, 100, graphDatabase);
	}

	/**
	 * Creates a new SimpleRDBMSGraph graph in a database with given <code>id</code>.
	 *
	 * @param id Identifier of new graph
	 * @param vMax Maximum initial count of vertices that can be held in graph.
	 * @param eMax Maximum initial count of edges that can be held in graph.
	 * @param graphDatabase Database which should contain graph
	 */
	public SimpleRDBMSGraph createSimpleRDBMSGraph(String id, int vMax, int eMax, GraphDatabase graphDatabase) throws GraphDatabaseException{
		throw new UnsupportedOperationException("No DATABASE support compiled.");
	}

	public SimpleRDBMSGraph loadSimpleRDBMSGraph(String filename) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.STANDARD);
		return loadSimpleRDBMSGraph(filename, factory, null);
	}

	public SimpleRDBMSGraph loadSimpleRDBMSGraph(String filename, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.STANDARD);
		return loadSimpleRDBMSGraph(filename, factory, pf);
	}

	public SimpleRDBMSGraph loadSimpleRDBMSGraph(String filename, de.uni_koblenz.jgralab.ImplementationType implType) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return loadSimpleRDBMSGraph(filename, factory, null);
	}

	
	public SimpleRDBMSGraph loadSimpleRDBMSGraph(String filename, de.uni_koblenz.jgralab.ImplementationType implType, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return loadSimpleRDBMSGraph(filename, factory, pf);
	}

	public SimpleRDBMSGraph loadSimpleRDBMSGraph(String filename, de.uni_koblenz.jgralab.GraphFactory factory) throws GraphIOException {
		return GraphIO.loadGraphFromFile(filename, factory, null);
	}

	public SimpleRDBMSGraph loadSimpleRDBMSGraph(String filename, de.uni_koblenz.jgralab.GraphFactory factory, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		return GraphIO.loadGraphFromFile(filename, factory, pf);
	}
	
	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
