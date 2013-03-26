/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package schemas.categoryschema.impl.std;

import java.io.IOException;

import schemas.categoryschema.Category;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

/**
 * FromVertexClass: Category FromRoleName : category ToVertexClass: Category
 * ToRoleName : subcategory
 */

public class SubcategoryImpl extends EdgeImpl implements
		de.uni_koblenz.jgralab.Edge, schemas.categoryschema.Subcategory {

	protected boolean _backwardArc;

	protected boolean _blacklisted;

	protected java.lang.String _comment;

	public SubcategoryImpl(int id, de.uni_koblenz.jgralab.Graph g,
			Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
		((de.uni_koblenz.jgralab.impl.InternalGraph) graph).addEdge(this,
				alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return schemas.categoryschema.Subcategory.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return schemas.categoryschema.Subcategory.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("backwardArc"))
			return (T) (java.lang.Boolean) is_backwardArc();
		if (attributeName.equals("blacklisted"))
			return (T) (java.lang.Boolean) is_blacklisted();
		if (attributeName.equals("comment"))
			return (T) get_comment();
		throw new NoSuchAttributeException(
				"Subcategory doesn't contain an attribute " + attributeName);
	}

	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("backwardArc")) {
			set_backwardArc((java.lang.Boolean) data);
			return;
		}
		if (attributeName.equals("blacklisted")) {
			set_blacklisted((java.lang.Boolean) data);
			return;
		}
		if (attributeName.equals("comment")) {
			set_comment((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException(
				"Subcategory doesn't contain an attribute " + attributeName);
	}

	public boolean is_backwardArc() {
		return _backwardArc;
	}

	public void set_backwardArc(boolean _backwardArc) {
		ecaAttributeChanging("backwardArc", this._backwardArc, _backwardArc);
		Object oldValue = this._backwardArc;
		this._backwardArc = _backwardArc;
		graphModified();
		ecaAttributeChanged("backwardArc", oldValue, _backwardArc);
	}

	public boolean is_blacklisted() {
		return _blacklisted;
	}

	public void set_blacklisted(boolean _blacklisted) {
		ecaAttributeChanging("blacklisted", this._blacklisted, _blacklisted);
		Object oldValue = this._blacklisted;
		this._blacklisted = _blacklisted;
		graphModified();
		ecaAttributeChanged("blacklisted", oldValue, _blacklisted);
	}

	public java.lang.String get_comment() {
		return _comment;
	}

	public void set_comment(java.lang.String _comment) {
		ecaAttributeChanging("comment", this._comment, _comment);
		Object oldValue = this._comment;
		this._comment = _comment;
		graphModified();
		ecaAttributeChanged("comment", oldValue, _comment);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_backwardArc = io.matchBoolean();
		set_backwardArc(_backwardArc);
		_blacklisted = io.matchBoolean();
		set_blacklisted(_blacklisted);
		_comment = io.matchUtfString();
		set_comment(_comment);
	}

	public void readAttributeValueFromString(String attributeName, String value)
			throws GraphIOException {
		if (attributeName.equals("backwardArc")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_backwardArc = io.matchBoolean();
			set_backwardArc(_backwardArc);
			return;
		}
		if (attributeName.equals("blacklisted")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_blacklisted = io.matchBoolean();
			set_blacklisted(_blacklisted);
			return;
		}
		if (attributeName.equals("comment")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_comment = io.matchUtfString();
			set_comment(_comment);
			return;
		}
		throw new NoSuchAttributeException(
				"Subcategory doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException,
			IOException {
		io.space();
		io.writeBoolean(_backwardArc);
		io.writeBoolean(_blacklisted);
		io.writeUtfString(_comment);
	}

	public String writeAttributeValueToString(String attributeName)
			throws IOException, GraphIOException {
		if (attributeName.equals("backwardArc")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeBoolean(_backwardArc);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("blacklisted")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeBoolean(_blacklisted);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("comment")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_comment);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException(
				"Subcategory doesn't contain an attribute " + attributeName);
	}

	public schemas.categoryschema.Subcategory getNextSubcategoryInGraph() {
		return (schemas.categoryschema.Subcategory) getNextEdge(schemas.categoryschema.Subcategory.class);
	}

	public schemas.categoryschema.Subcategory getNextSubcategoryIncidence() {
		return (schemas.categoryschema.Subcategory) getNextIncidence(schemas.categoryschema.Subcategory.class);
	}

	public schemas.categoryschema.Subcategory getNextSubcategoryIncidence(
			EdgeDirection orientation) {
		return (schemas.categoryschema.Subcategory) getNextIncidence(
				schemas.categoryschema.Subcategory.class, orientation);
	}

	public de.uni_koblenz.jgralab.schema.AggregationKind getAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.NONE;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getAlphaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.NONE;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getOmegaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.NONE;
	}

	protected de.uni_koblenz.jgralab.impl.ReversedEdgeBaseImpl createReversedEdge() {
		return new ReversedSubcategoryImpl(this, graph);
	}

	public Category getAlpha() {
		return (Category) super.getAlpha();
	}

	public Category getOmega() {
		return (Category) super.getOmega();
	}
}
