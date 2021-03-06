/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.varro;

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
 * The schema UMLSchema is implemented following the singleton pattern.
 * To get the instance, use the static method <code>instance()</code>.
 */
public class UMLSchema extends SchemaImpl {
	public final GraphClass gc_UMLGraph;
	public final VertexClass vc_uml_NamedElement;
	public final VertexClass vc_uml_General;
	public final VertexClass vc_uml_AssocEnd;
	public final VertexClass vc_uml_Package;
	public final VertexClass vc_uml_Assoc;
	public final VertexClass vc_uml_Class;
	public final VertexClass vc_uml_Attribute;
	public final EdgeClass ec_uml_HasAttribute;
	public final EdgeClass ec_uml_HasAssocEnd;
	public final EdgeClass ec_uml_ContainsGeneral;
	public final EdgeClass ec_uml_HasSub;
	public final EdgeClass ec_uml_ContainsAssoc;
	public final EdgeClass ec_uml_ContainsClass;
	public final EdgeClass ec_uml_HasSuper;
	public final EdgeClass ec_uml_HasType;

	/**
	 * the weak reference to the singleton instance
	 */
	static WeakReference<UMLSchema> theInstance = new WeakReference<UMLSchema>(null);
	
	/**
	 * @return the singleton instance of UMLSchema
	 */
	public static UMLSchema instance() {
		UMLSchema s = theInstance.get();
		if (s != null) {
			return s;
		}
		synchronized (UMLSchema.class) {
			s = theInstance.get();
			if (s != null) {
				return s;
			}
			s = new UMLSchema();
			theInstance = new WeakReference<UMLSchema>(s);
		}
		return s;
	}
	
	/**
	 * Creates a UMLSchema and builds its schema classes.
	 * This constructor is private. Use the <code>instance()</code> method
	 * to acess the schema.
	 */
	private UMLSchema() {
		super("UMLSchema", "de.uni_koblenz.jgralabtest.schemas.gretl.varro");

		{
			GraphClass gc = gc_UMLGraph = createGraphClass("UMLGraph");
			gc.setAbstract(false);
			@SuppressWarnings("unused")
			VertexClass vc_Vertex = getDefaultVertexClass();

			{
				VertexClass vc = vc_uml_NamedElement = gc.createVertexClass("uml.NamedElement");
				vc.setAbstract(true);
				vc.addAttribute(createAttribute("name", getDomain("String"), getAttributedElementClass("uml.NamedElement"), null));
			}

			{
				VertexClass vc = vc_uml_General = gc.createVertexClass("uml.General");
				vc.setAbstract(false);
				vc.addSuperClass(vc_uml_NamedElement);
			}

			{
				VertexClass vc = vc_uml_AssocEnd = gc.createVertexClass("uml.AssocEnd");
				vc.setAbstract(false);
				vc.addSuperClass(vc_uml_NamedElement);
			}

			{
				VertexClass vc = vc_uml_Package = gc.createVertexClass("uml.Package");
				vc.setAbstract(false);
				vc.addSuperClass(vc_uml_NamedElement);
			}

			{
				VertexClass vc = vc_uml_Assoc = gc.createVertexClass("uml.Assoc");
				vc.setAbstract(false);
				vc.addSuperClass(vc_uml_NamedElement);
			}

			{
				VertexClass vc = vc_uml_Class = gc.createVertexClass("uml.Class");
				vc.setAbstract(false);
				vc.addSuperClass(vc_uml_NamedElement);
			}

			{
				VertexClass vc = vc_uml_Attribute = gc.createVertexClass("uml.Attribute");
				vc.setAbstract(false);
				vc.addSuperClass(vc_uml_NamedElement);
			}

			{
				EdgeClass ec = ec_uml_HasAttribute = gc.createEdgeClass("uml.HasAttribute",
					vc_uml_Class, 0, 1, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_uml_Attribute, 0, 2147483647, "feature", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_uml_HasAssocEnd = gc.createEdgeClass("uml.HasAssocEnd",
					vc_uml_Assoc, 1, 1, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_uml_AssocEnd, 2, 2, "feature", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_uml_ContainsGeneral = gc.createEdgeClass("uml.ContainsGeneral",
					vc_uml_Package, 0, 1, "elemOwner", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_uml_General, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_uml_HasSub = gc.createEdgeClass("uml.HasSub",
					vc_uml_General, 1, 1, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_uml_Class, 1, 1, "childElem", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_uml_ContainsAssoc = gc.createEdgeClass("uml.ContainsAssoc",
					vc_uml_Package, 0, 1, "elemOwner", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_uml_Assoc, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_uml_ContainsClass = gc.createEdgeClass("uml.ContainsClass",
					vc_uml_Package, 0, 1, "elemOwner", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_uml_Class, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_uml_HasSuper = gc.createEdgeClass("uml.HasSuper",
					vc_uml_General, 1, 1, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_uml_Class, 1, 1, "parentElem", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
				ec.setAbstract(false);
			}

			{
				EdgeClass ec = ec_uml_HasType = gc.createEdgeClass("uml.HasType",
					vc_uml_AssocEnd, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_uml_Class, 1, 1, "featureType", de.uni_koblenz.jgralab.schema.AggregationKind.NONE);
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
				return new de.uni_koblenz.jgralabtest.schemas.gretl.varro.impl.std.UMLGraphFactoryImpl();
		}
		throw new UnsupportedOperationException("No " + implementationType + " support compiled.");
	}

	/**
	 * Creates a new UMLGraph graph.
	*/
	public UMLGraph createUMLGraph(de.uni_koblenz.jgralab.ImplementationType implType) {
		return createUMLGraph(implType, null, 100, 100);
	}

	/**
	 * Creates a new UMLGraph graph with initial vertex and edge counts <code>vMax</code>, <code>eMax</code>.
	 *
	 * @param vMax initial vertex count
	 * @param eMax initial edge count
	*/
	public UMLGraph createUMLGraph(de.uni_koblenz.jgralab.ImplementationType implType, String id, int vMax, int eMax) {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return factory.createGraph(gc_UMLGraph, id, vMax, eMax);
	}

	/**
	 * Creates a new UMLGraph graph.
	*/
	public UMLGraph createUMLGraph(de.uni_koblenz.jgralab.GraphFactory factory) {
		return factory.createGraph(gc_UMLGraph, null, 100, 100);
	}

	/**
	 * Creates a new UMLGraph graph.
	*/
	public UMLGraph createUMLGraph(de.uni_koblenz.jgralab.GraphFactory factory, String id, int vMax, int eMax) {
		return factory.createGraph(gc_UMLGraph, id, vMax, eMax);
	}

	/**
	 * Creates a new UMLGraph graph in a database with given <code>id</code>.
	 *
	 * @param id Identifier of new graph
	 * @param graphDatabase Database which should contain graph
	 */
	public UMLGraph createUMLGraph(String id, GraphDatabase graphDatabase) throws GraphDatabaseException{
		return createUMLGraph(id, 100, 100, graphDatabase);
	}

	/**
	 * Creates a new UMLGraph graph in a database with given <code>id</code>.
	 *
	 * @param id Identifier of new graph
	 * @param vMax Maximum initial count of vertices that can be held in graph.
	 * @param eMax Maximum initial count of edges that can be held in graph.
	 * @param graphDatabase Database which should contain graph
	 */
	public UMLGraph createUMLGraph(String id, int vMax, int eMax, GraphDatabase graphDatabase) throws GraphDatabaseException{
		throw new UnsupportedOperationException("No DATABASE support compiled.");
	}

	public UMLGraph loadUMLGraph(String filename) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.STANDARD);
		return loadUMLGraph(filename, factory, null);
	}

	public UMLGraph loadUMLGraph(String filename, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.STANDARD);
		return loadUMLGraph(filename, factory, pf);
	}

	public UMLGraph loadUMLGraph(String filename, de.uni_koblenz.jgralab.ImplementationType implType) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return loadUMLGraph(filename, factory, null);
	}

	
	public UMLGraph loadUMLGraph(String filename, de.uni_koblenz.jgralab.ImplementationType implType, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return loadUMLGraph(filename, factory, pf);
	}

	public UMLGraph loadUMLGraph(String filename, de.uni_koblenz.jgralab.GraphFactory factory) throws GraphIOException {
		return GraphIO.loadGraphFromFile(filename, factory, null);
	}

	public UMLGraph loadUMLGraph(String filename, de.uni_koblenz.jgralab.GraphFactory factory, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
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
