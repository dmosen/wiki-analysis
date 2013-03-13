/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.services;

public interface ServiceGraph extends de.uni_koblenz.jgralab.Graph {

	public static final de.uni_koblenz.jgralab.schema.GraphClass GC = de.uni_koblenz.jgralabtest.schemas.gretl.services.ServiceSchema.instance().gc_ServiceGraph;

	// ------------------------ Code for Accesses ------------------------

	/**
	 * @return the first Accesses edge in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Accesses getFirstAccesses();

	/**
	 * Creates a new Accesses edge in this graph.
	 *
	 * @param alpha the start vertex of the edge
	 * @param omega the target vertex of the edge
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Accesses createAccesses(de.uni_koblenz.jgralabtest.schemas.gretl.services.BasicService alpha, de.uni_koblenz.jgralabtest.schemas.gretl.services.Database omega);

	// ------------------------ Code for BasicService ------------------------

	/**
	 * @return the first BasicService vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.BasicService getFirstBasicService();

	/**
	 * Creates a new BasicService vertex in this graph.
	 *
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.BasicService createBasicService();

	// ------------------------ Code for Calls ------------------------

	/**
	 * @return the first Calls edge in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls getFirstCalls();

	/**
	 * Creates a new Calls edge in this graph.
	 *
	 * @param alpha the start vertex of the edge
	 * @param omega the target vertex of the edge
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls createCalls(de.uni_koblenz.jgralabtest.schemas.gretl.services.Service alpha, de.uni_koblenz.jgralabtest.schemas.gretl.services.Service omega);

	// ------------------------ Code for ComposedService ------------------------

	/**
	 * @return the first ComposedService vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.ComposedService getFirstComposedService();

	/**
	 * Creates a new ComposedService vertex in this graph.
	 *
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.ComposedService createComposedService();

	// ------------------------ Code for Database ------------------------

	/**
	 * @return the first Database vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Database getFirstDatabase();

	/**
	 * Creates a new Database vertex in this graph.
	 *
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Database createDatabase();

	// ------------------------ Code for ProcessService ------------------------

	/**
	 * @return the first ProcessService vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.ProcessService getFirstProcessService();

	/**
	 * Creates a new ProcessService vertex in this graph.
	 *
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.ProcessService createProcessService();

	// ------------------------ Code for Service ------------------------

	/**
	 * @return the first Service vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.services.Service getFirstService();

	/**
	 * @return an Iterable for all edges of this graph that are of type Accesses or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.services.Accesses> getAccessesEdges();
	

	/**
	 * @return an Iterable for all edges of this graph that are of type Calls or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.services.Calls> getCallsEdges();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type ProcessService or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.services.ProcessService> getProcessServiceVertices();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type Database or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.services.Database> getDatabaseVertices();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type ComposedService or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.services.ComposedService> getComposedServiceVertices();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type BasicService or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.services.BasicService> getBasicServiceVertices();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type Service or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.services.Service> getServiceVertices();
	
}
