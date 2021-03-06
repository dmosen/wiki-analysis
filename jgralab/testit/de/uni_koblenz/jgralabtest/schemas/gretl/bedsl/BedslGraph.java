/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.bedsl;

public interface BedslGraph extends de.uni_koblenz.jgralab.Graph {

	public static final de.uni_koblenz.jgralab.schema.GraphClass GC = de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.BedslSchema.instance().gc_BedslGraph;

	// ------------------------ Code for Attribute ------------------------

	/**
	 * @return the first Attribute vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.Attribute getFirstAttribute();

	// ------------------------ Code for Entity ------------------------

	/**
	 * @return the first Entity vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.Entity getFirstEntity();

	/**
	 * Creates a new Entity vertex in this graph.
	 *
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.Entity createEntity();

	// ------------------------ Code for HasAttribute ------------------------

	/**
	 * @return the first HasAttribute edge in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.HasAttribute getFirstHasAttribute();

	/**
	 * Creates a new HasAttribute edge in this graph.
	 *
	 * @param alpha the start vertex of the edge
	 * @param omega the target vertex of the edge
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.HasAttribute createHasAttribute(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.Entity alpha, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.Attribute omega);

	// ------------------------ Code for HasSupertype ------------------------

	/**
	 * @return the first HasSupertype edge in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.HasSupertype getFirstHasSupertype();

	/**
	 * Creates a new HasSupertype edge in this graph.
	 *
	 * @param alpha the start vertex of the edge
	 * @param omega the target vertex of the edge
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.HasSupertype createHasSupertype(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.Entity alpha, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.Entity omega);

	// ------------------------ Code for ReferenceAttribute ------------------------

	/**
	 * @return the first ReferenceAttribute vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.ReferenceAttribute getFirstReferenceAttribute();

	/**
	 * Creates a new ReferenceAttribute vertex in this graph.
	 *
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.ReferenceAttribute createReferenceAttribute();

	// ------------------------ Code for References ------------------------

	/**
	 * @return the first References edge in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.References getFirstReferences();

	/**
	 * Creates a new References edge in this graph.
	 *
	 * @param alpha the start vertex of the edge
	 * @param omega the target vertex of the edge
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.References createReferences(de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.ReferenceAttribute alpha, de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.Entity omega);

	// ------------------------ Code for SimpleAttribute ------------------------

	/**
	 * @return the first SimpleAttribute vertex in this graph
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.SimpleAttribute getFirstSimpleAttribute();

	/**
	 * Creates a new SimpleAttribute vertex in this graph.
	 *
	*/
	public de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.SimpleAttribute createSimpleAttribute();

	/**
	 * @return an Iterable for all edges of this graph that are of type HasAttribute or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.HasAttribute> getHasAttributeEdges();
	

	/**
	 * @return an Iterable for all edges of this graph that are of type References or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.References> getReferencesEdges();
	

	/**
	 * @return an Iterable for all edges of this graph that are of type HasSupertype or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.HasSupertype> getHasSupertypeEdges();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type SimpleAttribute or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.SimpleAttribute> getSimpleAttributeVertices();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type Attribute or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.Attribute> getAttributeVertices();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type ReferenceAttribute or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.ReferenceAttribute> getReferenceAttributeVertices();
	

	/**
	 * @return an Iterable for all vertices of this graph that are of type Entity or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.bedsl.Entity> getEntityVertices();
	
}
