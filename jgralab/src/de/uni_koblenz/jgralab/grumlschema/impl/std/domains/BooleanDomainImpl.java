/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.grumlschema.impl.std.domains;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.std.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class BooleanDomainImpl extends VertexImpl implements de.uni_koblenz.jgralab.grumlschema.domains.BasicDomain, de.uni_koblenz.jgralab.grumlschema.domains.BooleanDomain {

	protected java.lang.String _qualifiedName;

	public BooleanDomainImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralab.grumlschema.domains.BooleanDomain.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralab.grumlschema.domains.BooleanDomain.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("qualifiedName")) return (T) get_qualifiedName();
		throw new NoSuchAttributeException("domains.BooleanDomain doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("qualifiedName")) {
			set_qualifiedName((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("domains.BooleanDomain doesn't contain an attribute " + attributeName);
	}

	public java.lang.String get_qualifiedName() {
		return _qualifiedName;
	}

	public void set_qualifiedName(java.lang.String _qualifiedName) {
		ecaAttributeChanging("qualifiedName", this._qualifiedName, _qualifiedName);
		Object oldValue = this._qualifiedName;
		this._qualifiedName = _qualifiedName;
		graphModified();
	ecaAttributeChanged("qualifiedName", oldValue, _qualifiedName);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_qualifiedName = io.matchUtfString();
		set_qualifiedName(_qualifiedName);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("qualifiedName")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_qualifiedName = io.matchUtfString();
			set_qualifiedName(_qualifiedName);
			return;
		}
		throw new NoSuchAttributeException("domains.BooleanDomain doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_qualifiedName);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("qualifiedName")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_qualifiedName);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("domains.BooleanDomain doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.domains.BasicDomain getNextBasicDomain() {
		return (de.uni_koblenz.jgralab.grumlschema.domains.BasicDomain)getNextVertex(de.uni_koblenz.jgralab.grumlschema.domains.BasicDomain.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.domains.BooleanDomain getNextBooleanDomain() {
		return (de.uni_koblenz.jgralab.grumlschema.domains.BooleanDomain)getNextVertex(de.uni_koblenz.jgralab.grumlschema.domains.BooleanDomain.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.domains.Domain getNextDomain() {
		return (de.uni_koblenz.jgralab.grumlschema.domains.Domain)getNextVertex(de.uni_koblenz.jgralab.grumlschema.domains.Domain.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.structure.NamedElement getNextNamedElement() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.NamedElement)getNextVertex(de.uni_koblenz.jgralab.grumlschema.structure.NamedElement.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.structure.Annotates getFirstAnnotatesIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.Annotates)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.Annotates.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.structure.Annotates getFirstAnnotatesIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.structure.Annotates)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.Annotates.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain getFirstHasBaseDomainIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain getFirstHasBaseDomainIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain getFirstHasValueDomainIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain getFirstHasValueDomainIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent getFirstHasRecordDomainComponentIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent getFirstHasRecordDomainComponentIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain getFirstContainsDomainIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain getFirstContainsDomainIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain getFirstHasKeyDomainIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain getFirstHasKeyDomainIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.structure.HasDomain getFirstHasDomainIncidence() {
		return (de.uni_koblenz.jgralab.grumlschema.structure.HasDomain)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.HasDomain.class);
	}

	@Override
	public de.uni_koblenz.jgralab.grumlschema.structure.HasDomain getFirstHasDomainIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.grumlschema.structure.HasDomain)getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.HasDomain.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralab.grumlschema.structure.Annotates add_comment(de.uni_koblenz.jgralab.grumlschema.structure.Comment vertex) {
			return ((de.uni_koblenz.jgralab.grumlschema.SchemaGraph)getGraph()).createEdge(de.uni_koblenz.jgralab.grumlschema.structure.Annotates.EC, (de.uni_koblenz.jgralab.grumlschema.structure.Comment) vertex, (de.uni_koblenz.jgralab.grumlschema.domains.BooleanDomain) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralab.grumlschema.structure.Comment> remove_comment() {
			java.util.List<de.uni_koblenz.jgralab.grumlschema.structure.Comment> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralab.grumlschema.structure.Comment>();
			de.uni_koblenz.jgralab.grumlschema.structure.Annotates edge = (de.uni_koblenz.jgralab.grumlschema.structure.Annotates) getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.Annotates.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralab.grumlschema.structure.Annotates next = (de.uni_koblenz.jgralab.grumlschema.structure.Annotates) edge.getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.Annotates.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralab.grumlschema.structure.Comment) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_comment(de.uni_koblenz.jgralab.grumlschema.structure.Comment vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralab.grumlschema.structure.Annotates edge = (de.uni_koblenz.jgralab.grumlschema.structure.Annotates) getFirstIncidence(de.uni_koblenz.jgralab.grumlschema.structure.Annotates.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralab.grumlschema.structure.Annotates next = (de.uni_koblenz.jgralab.grumlschema.structure.Annotates) edge.getNextIncidence(de.uni_koblenz.jgralab.grumlschema.structure.Annotates.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralab.grumlschema.structure.Comment> get_comment() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralab.grumlschema.structure.Annotates, de.uni_koblenz.jgralab.grumlschema.structure.Comment>(this, de.uni_koblenz.jgralab.grumlschema.structure.Annotates.class, EdgeDirection.IN);
		}

	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.Annotates> getAnnotatesIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.structure.Annotates>(this, de.uni_koblenz.jgralab.grumlschema.structure.Annotates.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.Annotates> getAnnotatesIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.structure.Annotates>(this, de.uni_koblenz.jgralab.grumlschema.structure.Annotates.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain> getHasBaseDomainIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain>(this, de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain> getHasBaseDomainIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain>(this, de.uni_koblenz.jgralab.grumlschema.domains.HasBaseDomain.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain> getHasValueDomainIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain>(this, de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain> getHasValueDomainIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain>(this, de.uni_koblenz.jgralab.grumlschema.domains.HasValueDomain.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent> getHasRecordDomainComponentIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent>(this, de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent> getHasRecordDomainComponentIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent>(this, de.uni_koblenz.jgralab.grumlschema.domains.HasRecordDomainComponent.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain> getContainsDomainIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain>(this, de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain> getContainsDomainIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain>(this, de.uni_koblenz.jgralab.grumlschema.structure.ContainsDomain.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain> getHasKeyDomainIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain>(this, de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain> getHasKeyDomainIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain>(this, de.uni_koblenz.jgralab.grumlschema.domains.HasKeyDomain.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.HasDomain> getHasDomainIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.structure.HasDomain>(this, de.uni_koblenz.jgralab.grumlschema.structure.HasDomain.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.grumlschema.structure.HasDomain> getHasDomainIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.grumlschema.structure.HasDomain>(this, de.uni_koblenz.jgralab.grumlschema.structure.HasDomain.class, direction);
	}
}
