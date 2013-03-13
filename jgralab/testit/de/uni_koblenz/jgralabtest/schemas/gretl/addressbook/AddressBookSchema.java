/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.addressbook;

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
 * The schema AddressBookSchema is implemented following the singleton pattern.
 * To get the instance, use the static method <code>instance()</code>.
 */
public class AddressBookSchema extends SchemaImpl {
	public final GraphClass gc_AddressBookGraph;
	public final VertexClass vc_Contact;
	public final VertexClass vc_AddressBook;
	public final EdgeClass ec_Contains;

	/**
	 * the weak reference to the singleton instance
	 */
	static WeakReference<AddressBookSchema> theInstance = new WeakReference<AddressBookSchema>(null);
	
	/**
	 * @return the singleton instance of AddressBookSchema
	 */
	public static AddressBookSchema instance() {
		AddressBookSchema s = theInstance.get();
		if (s != null) {
			return s;
		}
		synchronized (AddressBookSchema.class) {
			s = theInstance.get();
			if (s != null) {
				return s;
			}
			s = new AddressBookSchema();
			theInstance = new WeakReference<AddressBookSchema>(s);
		}
		return s;
	}
	
	/**
	 * Creates a AddressBookSchema and builds its schema classes.
	 * This constructor is private. Use the <code>instance()</code> method
	 * to acess the schema.
	 */
	private AddressBookSchema() {
		super("AddressBookSchema", "de.uni_koblenz.jgralabtest.schemas.gretl.addressbook");

		{
			GraphClass gc = gc_AddressBookGraph = createGraphClass("AddressBookGraph");
			gc.setAbstract(false);
			gc.addAttribute(createAttribute("name", getDomain("String"), getAttributedElementClass("AddressBookGraph"), null));
			@SuppressWarnings("unused")
			VertexClass vc_Vertex = getDefaultVertexClass();

			{
				VertexClass vc = vc_Contact = gc.createVertexClass("Contact");
				vc.setAbstract(false);
				vc.addAttribute(createAttribute("address", getDomain("String"), getAttributedElementClass("Contact"), null));
				vc.addAttribute(createAttribute("name", getDomain("String"), getAttributedElementClass("Contact"), null));
			}

			{
				VertexClass vc = vc_AddressBook = gc.createVertexClass("AddressBook");
				vc.setAbstract(false);
				vc.addAttribute(createAttribute("name", getDomain("String"), getAttributedElementClass("AddressBook"), null));
			}

			{
				EdgeClass ec = ec_Contains = gc.createEdgeClass("Contains",
					vc_AddressBook, 1, 1, "", de.uni_koblenz.jgralab.schema.AggregationKind.NONE,
					vc_Contact, 0, 2147483647, "", de.uni_koblenz.jgralab.schema.AggregationKind.COMPOSITE);
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
				return new de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.impl.std.AddressBookGraphFactoryImpl();
		}
		throw new UnsupportedOperationException("No " + implementationType + " support compiled.");
	}

	/**
	 * Creates a new AddressBookGraph graph.
	*/
	public AddressBookGraph createAddressBookGraph(de.uni_koblenz.jgralab.ImplementationType implType) {
		return createAddressBookGraph(implType, null, 100, 100);
	}

	/**
	 * Creates a new AddressBookGraph graph with initial vertex and edge counts <code>vMax</code>, <code>eMax</code>.
	 *
	 * @param vMax initial vertex count
	 * @param eMax initial edge count
	*/
	public AddressBookGraph createAddressBookGraph(de.uni_koblenz.jgralab.ImplementationType implType, String id, int vMax, int eMax) {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return factory.createGraph(gc_AddressBookGraph, id, vMax, eMax);
	}

	/**
	 * Creates a new AddressBookGraph graph.
	*/
	public AddressBookGraph createAddressBookGraph(de.uni_koblenz.jgralab.GraphFactory factory) {
		return factory.createGraph(gc_AddressBookGraph, null, 100, 100);
	}

	/**
	 * Creates a new AddressBookGraph graph.
	*/
	public AddressBookGraph createAddressBookGraph(de.uni_koblenz.jgralab.GraphFactory factory, String id, int vMax, int eMax) {
		return factory.createGraph(gc_AddressBookGraph, id, vMax, eMax);
	}

	/**
	 * Creates a new AddressBookGraph graph in a database with given <code>id</code>.
	 *
	 * @param id Identifier of new graph
	 * @param graphDatabase Database which should contain graph
	 */
	public AddressBookGraph createAddressBookGraph(String id, GraphDatabase graphDatabase) throws GraphDatabaseException{
		return createAddressBookGraph(id, 100, 100, graphDatabase);
	}

	/**
	 * Creates a new AddressBookGraph graph in a database with given <code>id</code>.
	 *
	 * @param id Identifier of new graph
	 * @param vMax Maximum initial count of vertices that can be held in graph.
	 * @param eMax Maximum initial count of edges that can be held in graph.
	 * @param graphDatabase Database which should contain graph
	 */
	public AddressBookGraph createAddressBookGraph(String id, int vMax, int eMax, GraphDatabase graphDatabase) throws GraphDatabaseException{
		throw new UnsupportedOperationException("No DATABASE support compiled.");
	}

	public AddressBookGraph loadAddressBookGraph(String filename) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.STANDARD);
		return loadAddressBookGraph(filename, factory, null);
	}

	public AddressBookGraph loadAddressBookGraph(String filename, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(de.uni_koblenz.jgralab.ImplementationType.STANDARD);
		return loadAddressBookGraph(filename, factory, pf);
	}

	public AddressBookGraph loadAddressBookGraph(String filename, de.uni_koblenz.jgralab.ImplementationType implType) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return loadAddressBookGraph(filename, factory, null);
	}

	
	public AddressBookGraph loadAddressBookGraph(String filename, de.uni_koblenz.jgralab.ImplementationType implType, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
		de.uni_koblenz.jgralab.GraphFactory factory = createDefaultGraphFactory(implType);
		return loadAddressBookGraph(filename, factory, pf);
	}

	public AddressBookGraph loadAddressBookGraph(String filename, de.uni_koblenz.jgralab.GraphFactory factory) throws GraphIOException {
		return GraphIO.loadGraphFromFile(filename, factory, null);
	}

	public AddressBookGraph loadAddressBookGraph(String filename, de.uni_koblenz.jgralab.GraphFactory factory, de.uni_koblenz.jgralab.ProgressFunction pf) throws GraphIOException {
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
