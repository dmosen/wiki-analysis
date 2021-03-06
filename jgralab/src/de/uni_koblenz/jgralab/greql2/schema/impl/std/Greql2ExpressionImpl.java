/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema.impl.std;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.std.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class Greql2ExpressionImpl extends VertexImpl implements de.uni_koblenz.jgralab.greql2.schema.Greql2Expression, de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex {

	protected java.lang.String _costModel;

	protected org.pcollections.PSet<java.lang.String> _importedTypes;

	protected java.lang.String _optimizer;

	protected java.lang.String _queryText;

	public Greql2ExpressionImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralab.greql2.schema.Greql2Expression.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralab.greql2.schema.Greql2Expression.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("costModel")) return (T) get_costModel();
		if (attributeName.equals("importedTypes")) return (T) get_importedTypes();
		if (attributeName.equals("optimizer")) return (T) get_optimizer();
		if (attributeName.equals("queryText")) return (T) get_queryText();
		throw new NoSuchAttributeException("Greql2Expression doesn't contain an attribute " + attributeName);
	}

	@SuppressWarnings("unchecked")
	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("costModel")) {
			set_costModel((java.lang.String) data);
			return;
		}
		if (attributeName.equals("importedTypes")) {
			set_importedTypes((org.pcollections.PSet<java.lang.String>) data);
			return;
		}
		if (attributeName.equals("optimizer")) {
			set_optimizer((java.lang.String) data);
			return;
		}
		if (attributeName.equals("queryText")) {
			set_queryText((java.lang.String) data);
			return;
		}
		throw new NoSuchAttributeException("Greql2Expression doesn't contain an attribute " + attributeName);
	}

	public java.lang.String get_costModel() {
		return _costModel;
	}

	public void set_costModel(java.lang.String _costModel) {
		ecaAttributeChanging("costModel", this._costModel, _costModel);
		Object oldValue = this._costModel;
		this._costModel = _costModel;
		graphModified();
	ecaAttributeChanged("costModel", oldValue, _costModel);
	}

	public org.pcollections.PSet<java.lang.String> get_importedTypes() {
		return _importedTypes;
	}

	public void set_importedTypes(org.pcollections.PSet<java.lang.String> _importedTypes) {
		ecaAttributeChanging("importedTypes", this._importedTypes, _importedTypes);
		Object oldValue = this._importedTypes;
		this._importedTypes = _importedTypes;
		graphModified();
	ecaAttributeChanged("importedTypes", oldValue, _importedTypes);
	}

	public java.lang.String get_optimizer() {
		return _optimizer;
	}

	public void set_optimizer(java.lang.String _optimizer) {
		ecaAttributeChanging("optimizer", this._optimizer, _optimizer);
		Object oldValue = this._optimizer;
		this._optimizer = _optimizer;
		graphModified();
	ecaAttributeChanged("optimizer", oldValue, _optimizer);
	}

	public java.lang.String get_queryText() {
		return _queryText;
	}

	public void set_queryText(java.lang.String _queryText) {
		ecaAttributeChanging("queryText", this._queryText, _queryText);
		Object oldValue = this._queryText;
		this._queryText = _queryText;
		graphModified();
	ecaAttributeChanged("queryText", oldValue, _queryText);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_costModel = io.matchUtfString();
		set_costModel(_costModel);
		
		if (io.isNextToken("{")) {
			org.pcollections.PSet<java.lang.String> $_importedTypes = de.uni_koblenz.jgralab.JGraLab.set();
			io.match("{");
			while (!io.isNextToken("}")) {
				java.lang.String $_importedTypesElement;
				$_importedTypesElement = io.matchUtfString();
				$_importedTypes = $_importedTypes.plus($_importedTypesElement);
			}
			io.match("}");
			_importedTypes = $_importedTypes;
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match();
			_importedTypes = null;
		} else {
			_importedTypes = null;
		}
		set_importedTypes(_importedTypes);
		_optimizer = io.matchUtfString();
		set_optimizer(_optimizer);
		_queryText = io.matchUtfString();
		set_queryText(_queryText);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("costModel")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_costModel = io.matchUtfString();
			set_costModel(_costModel);
			return;
		}
		if (attributeName.equals("importedTypes")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			
			if (io.isNextToken("{")) {
				org.pcollections.PSet<java.lang.String> $_importedTypes = de.uni_koblenz.jgralab.JGraLab.set();
				io.match("{");
				while (!io.isNextToken("}")) {
					java.lang.String $_importedTypesElement;
					$_importedTypesElement = io.matchUtfString();
					$_importedTypes = $_importedTypes.plus($_importedTypesElement);
				}
				io.match("}");
				_importedTypes = $_importedTypes;
			} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
				io.match();
				_importedTypes = null;
			} else {
				_importedTypes = null;
			}
			set_importedTypes(_importedTypes);
			return;
		}
		if (attributeName.equals("optimizer")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_optimizer = io.matchUtfString();
			set_optimizer(_optimizer);
			return;
		}
		if (attributeName.equals("queryText")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_queryText = io.matchUtfString();
			set_queryText(_queryText);
			return;
		}
		throw new NoSuchAttributeException("Greql2Expression doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_costModel);
		if (_importedTypes != null) {
			io.writeSpace();
			io.write("{");
			io.noSpace();
			for (java.lang.String _importedTypesElement : _importedTypes) {
				io.writeUtfString(_importedTypesElement);
			}
			io.write("}");
			io.space();
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		io.writeUtfString(_optimizer);
		io.writeUtfString(_queryText);
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("costModel")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_costModel);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("importedTypes")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (_importedTypes != null) {
				io.writeSpace();
				io.write("{");
				io.noSpace();
				for (java.lang.String _importedTypesElement : _importedTypes) {
					io.writeUtfString(_importedTypesElement);
				}
				io.write("}");
				io.space();
			} else {
				io.writeIdentifier(GraphIO.NULL_LITERAL);
			}
			return io.getStringWriterResult();
		}
		if (attributeName.equals("optimizer")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_optimizer);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("queryText")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(_queryText);
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("Greql2Expression doesn't contain an attribute " + attributeName);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Expression getNextGreql2Expression() {
		return (de.uni_koblenz.jgralab.greql2.schema.Greql2Expression)getNextVertex(de.uni_koblenz.jgralab.greql2.schema.Greql2Expression.class);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex getNextGreql2Vertex() {
		return (de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex)getNextVertex(de.uni_koblenz.jgralab.greql2.schema.Greql2Vertex.class);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.IsIdOf getFirstIsIdOfIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.IsIdOf)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsIdOf.class);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.IsIdOf getFirstIsIdOfIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.IsIdOf)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsIdOf.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf getFirstIsTypeExprOfIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf.class);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf getFirstIsTypeExprOfIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf getFirstIsQueryExprOfIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf.class);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf getFirstIsQueryExprOfIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf getFirstIsBoundExprOfIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf.class);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf getFirstIsBoundExprOfIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf getFirstIsBoundVarOfIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf.class);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf getFirstIsBoundVarOfIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf.class, orientation);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getFirstGreql2AggregationIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation.class);
	}

	@Override
	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getFirstGreql2AggregationIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation)getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation.class, orientation);
	}
		@Override
		public de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf add_boundVar(de.uni_koblenz.jgralab.greql2.schema.Variable vertex) {
			return ((de.uni_koblenz.jgralab.greql2.schema.Greql2)getGraph()).createEdge(de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf.EC, (de.uni_koblenz.jgralab.greql2.schema.Variable) vertex, (de.uni_koblenz.jgralab.greql2.schema.Greql2Expression) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Variable> remove_boundVar() {
			java.util.List<de.uni_koblenz.jgralab.greql2.schema.Variable> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralab.greql2.schema.Variable>();
			de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf edge = (de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf) getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf next = (de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf) edge.getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralab.greql2.schema.Variable) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_boundVar(de.uni_koblenz.jgralab.greql2.schema.Variable vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf edge = (de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf) getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf next = (de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf) edge.getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public Iterable<? extends de.uni_koblenz.jgralab.greql2.schema.Variable> get_boundVar() {
			return new de.uni_koblenz.jgralab.impl.NeighbourIterable<de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf, de.uni_koblenz.jgralab.greql2.schema.Variable>(this, de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf.class, EdgeDirection.IN);
		}
		@Override
		public de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf add_queryExpr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex) {
			return ((de.uni_koblenz.jgralab.greql2.schema.Greql2)getGraph()).createEdge(de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf.EC, (de.uni_koblenz.jgralab.greql2.schema.Expression) vertex, (de.uni_koblenz.jgralab.greql2.schema.Greql2Expression) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Expression> remove_queryExpr() {
			java.util.List<de.uni_koblenz.jgralab.greql2.schema.Expression> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralab.greql2.schema.Expression>();
			de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf edge = (de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf) getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf next = (de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf) edge.getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralab.greql2.schema.Expression) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_queryExpr(de.uni_koblenz.jgralab.greql2.schema.Expression vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf edge = (de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf) getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf next = (de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf) edge.getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public de.uni_koblenz.jgralab.greql2.schema.Expression get_queryExpr() {
			de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf edge = (de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf) getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf.class, EdgeDirection.IN);
			if (edge != null) {
				return (de.uni_koblenz.jgralab.greql2.schema.Expression) edge.getThat();
			}
			return null;
		}
		@Override
		public de.uni_koblenz.jgralab.greql2.schema.IsIdOf add_identifier(de.uni_koblenz.jgralab.greql2.schema.Identifier vertex) {
			return ((de.uni_koblenz.jgralab.greql2.schema.Greql2)getGraph()).createEdge(de.uni_koblenz.jgralab.greql2.schema.IsIdOf.EC, (de.uni_koblenz.jgralab.greql2.schema.Identifier) vertex, (de.uni_koblenz.jgralab.greql2.schema.Greql2Expression) this);
		}
		@Override
		public java.util.List<? extends de.uni_koblenz.jgralab.greql2.schema.Identifier> remove_identifier() {
			java.util.List<de.uni_koblenz.jgralab.greql2.schema.Identifier> adjacences = new java.util.ArrayList<de.uni_koblenz.jgralab.greql2.schema.Identifier>();
			de.uni_koblenz.jgralab.greql2.schema.IsIdOf edge = (de.uni_koblenz.jgralab.greql2.schema.IsIdOf) getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsIdOf.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralab.greql2.schema.IsIdOf next = (de.uni_koblenz.jgralab.greql2.schema.IsIdOf) edge.getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsIdOf.class, EdgeDirection.IN);
				adjacences.add((de.uni_koblenz.jgralab.greql2.schema.Identifier) edge.getThat());
				edge.delete();
				edge = next;
			}
			return adjacences;
		}
		@Override
		public boolean remove_identifier(de.uni_koblenz.jgralab.greql2.schema.Identifier vertex) {
			boolean elementRemoved = false;
			de.uni_koblenz.jgralab.greql2.schema.IsIdOf edge = (de.uni_koblenz.jgralab.greql2.schema.IsIdOf) getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsIdOf.class, EdgeDirection.IN);
			while (edge != null) {
				de.uni_koblenz.jgralab.greql2.schema.IsIdOf next = (de.uni_koblenz.jgralab.greql2.schema.IsIdOf) edge.getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsIdOf.class, EdgeDirection.IN);
				if (edge.getThat().equals(vertex)) {			edge.delete();
					elementRemoved = true;
				}
				edge = next;
			}
			return elementRemoved;
		}
		@Override
		public de.uni_koblenz.jgralab.greql2.schema.Identifier get_identifier() {
			de.uni_koblenz.jgralab.greql2.schema.IsIdOf edge = (de.uni_koblenz.jgralab.greql2.schema.IsIdOf) getFirstIncidence(de.uni_koblenz.jgralab.greql2.schema.IsIdOf.class, EdgeDirection.IN);
			if (edge != null) {
				return (de.uni_koblenz.jgralab.greql2.schema.Identifier) edge.getThat();
			}
			return null;
		}

	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsIdOf> getIsIdOfIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.IsIdOf>(this, de.uni_koblenz.jgralab.greql2.schema.IsIdOf.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsIdOf> getIsIdOfIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.IsIdOf>(this, de.uni_koblenz.jgralab.greql2.schema.IsIdOf.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf> getIsTypeExprOfIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf>(this, de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf> getIsTypeExprOfIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf>(this, de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf> getIsQueryExprOfIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf>(this, de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf> getIsQueryExprOfIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf>(this, de.uni_koblenz.jgralab.greql2.schema.IsQueryExprOf.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf> getIsBoundExprOfIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf>(this, de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf> getIsBoundExprOfIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf>(this, de.uni_koblenz.jgralab.greql2.schema.IsBoundExprOf.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf> getIsBoundVarOfIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf>(this, de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf> getIsBoundVarOfIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf>(this, de.uni_koblenz.jgralab.greql2.schema.IsBoundVarOf.class, direction);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation> getGreql2AggregationIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation>(this, de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation> getGreql2AggregationIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation>(this, de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation.class, direction);
	}
}
