/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.commenttest.impl.trans.pa.pc;

import de.uni_koblenz.jgralab.impl.trans.VertexImpl;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class WImpl extends VertexImpl implements de.uni_koblenz.jgralab.Vertex, de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W {

	public WImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W.class;
	}

	public <T> T getAttribute(String attributeName) {
		throw new NoSuchAttributeException("pa.pc.W doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		throw new NoSuchAttributeException("pa.pc.W doesn't contain an attribute " + attributeName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		throw new NoSuchAttributeException("pa.pc.W doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		throw new NoSuchAttributeException("pa.pc.W doesn't contain an attribute " + attributeName);
	}
	public java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes() {
		java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes = new java.util.HashSet<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>>();
		return attributes;
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W getNextW() {
		return (de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W)getNextVertex(de.uni_koblenz.jgralabtest.schemas.commenttest.pa.pc.W.class);
	}
}
