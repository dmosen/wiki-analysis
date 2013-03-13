/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.gretl.addressbook;

import de.uni_koblenz.jgralab.EdgeDirection;

public interface Contact extends de.uni_koblenz.jgralab.Vertex {

	public static final de.uni_koblenz.jgralab.schema.VertexClass VC = de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.AddressBookSchema.instance().vc_Contact;

	public java.lang.String get_address();

	public void set_address(java.lang.String _address);

	public java.lang.String get_name();

	public void set_name(java.lang.String _name);

	/**
	 * @return the next de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact vertex in the global vertex sequence
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contact getNextContact();

	/**
	 * @return the first edge of class Contains at this vertex
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains getFirstContainsIncidence();

	/**
	 * @return the first edge of class Contains at this vertex
	 * @param orientation the orientation of the edge
	 */
	public de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains getFirstContainsIncidence(EdgeDirection orientation);

	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Contains or subtypes.
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains> getContainsIncidences();
	
	/**
	 * Returns an Iterable for all incidence edges of this vertex that are of type Contains.
	 * @param direction EdgeDirection.IN or EdgeDirection.OUT, only edges of this direction will be included in the Iterable
	 */
	public Iterable<de.uni_koblenz.jgralabtest.schemas.gretl.addressbook.Contains> getContainsIncidences(EdgeDirection direction);
}
