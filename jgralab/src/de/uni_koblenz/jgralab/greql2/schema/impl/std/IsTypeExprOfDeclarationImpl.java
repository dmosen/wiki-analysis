/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralab.greql2.schema.impl.std;

import de.uni_koblenz.jgralab.impl.std.EdgeImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;
import de.uni_koblenz.jgralab.Vertex;

import de.uni_koblenz.jgralab.greql2.schema.impl.std.ReversedIsTypeExprOfDeclarationImpl;

import de.uni_koblenz.jgralab.greql2.schema.Expression;
import de.uni_koblenz.jgralab.greql2.schema.SimpleDeclaration;

import java.io.IOException;
/**
 * FromVertexClass: Expression
 * FromRoleName : typeExpr
 * ToVertexClass: SimpleDeclaration
 * ToRoleName : 
 */

public class IsTypeExprOfDeclarationImpl extends EdgeImpl implements de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf, de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration {

	protected org.pcollections.PVector<de.uni_koblenz.jgralab.greql2.schema.SourcePosition> _sourcePositions;

	public IsTypeExprOfDeclarationImpl(int id, de.uni_koblenz.jgralab.Graph g, Vertex alpha, Vertex omega) {
		super(id, g, alpha, omega);
		((de.uni_koblenz.jgralab.impl.InternalGraph) graph).addEdge(this, alpha, omega);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.EdgeClass getAttributedElementClass() {
		return de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration.EC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Edge> getSchemaClass() {
		return de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("sourcePositions")) return (T) get_sourcePositions();
		throw new NoSuchAttributeException("IsTypeExprOfDeclaration doesn't contain an attribute " + attributeName);
	}

	@SuppressWarnings("unchecked")
	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("sourcePositions")) {
			set_sourcePositions((org.pcollections.PVector<de.uni_koblenz.jgralab.greql2.schema.SourcePosition>) data);
			return;
		}
		throw new NoSuchAttributeException("IsTypeExprOfDeclaration doesn't contain an attribute " + attributeName);
	}

	public org.pcollections.PVector<de.uni_koblenz.jgralab.greql2.schema.SourcePosition> get_sourcePositions() {
		return _sourcePositions;
	}

	public void set_sourcePositions(org.pcollections.PVector<de.uni_koblenz.jgralab.greql2.schema.SourcePosition> _sourcePositions) {
		ecaAttributeChanging("sourcePositions", this._sourcePositions, _sourcePositions);
		Object oldValue = this._sourcePositions;
		this._sourcePositions = _sourcePositions;
		graphModified();
	ecaAttributeChanged("sourcePositions", oldValue, _sourcePositions);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		
		if (io.isNextToken("[")) {
			org.pcollections.PVector<de.uni_koblenz.jgralab.greql2.schema.SourcePosition> $_sourcePositions = de.uni_koblenz.jgralab.JGraLab.vector();
			io.match("[");
			while (!io.isNextToken("]")) {
				de.uni_koblenz.jgralab.greql2.schema.SourcePosition $_sourcePositionsElement = null;
				
				if (io.isNextToken("(")) {
					$_sourcePositionsElement = new de.uni_koblenz.jgralab.greql2.schema.SourcePosition(io);
				} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
					io.match();
					$_sourcePositionsElement = null;
				} else {
					throw new GraphIOException("This is no record!");
				}
				$_sourcePositions = $_sourcePositions.plus($_sourcePositionsElement);
			}
			io.match("]");
			_sourcePositions = $_sourcePositions;
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match(); 
			_sourcePositions = null;
		} else {
			_sourcePositions = null;
		}
		set_sourcePositions(_sourcePositions);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("sourcePositions")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			
			if (io.isNextToken("[")) {
				org.pcollections.PVector<de.uni_koblenz.jgralab.greql2.schema.SourcePosition> $_sourcePositions = de.uni_koblenz.jgralab.JGraLab.vector();
				io.match("[");
				while (!io.isNextToken("]")) {
					de.uni_koblenz.jgralab.greql2.schema.SourcePosition $_sourcePositionsElement = null;
					
					if (io.isNextToken("(")) {
						$_sourcePositionsElement = new de.uni_koblenz.jgralab.greql2.schema.SourcePosition(io);
					} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
						io.match();
						$_sourcePositionsElement = null;
					} else {
						throw new GraphIOException("This is no record!");
					}
					$_sourcePositions = $_sourcePositions.plus($_sourcePositionsElement);
				}
				io.match("]");
				_sourcePositions = $_sourcePositions;
			} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
				io.match(); 
				_sourcePositions = null;
			} else {
				_sourcePositions = null;
			}
			set_sourcePositions(_sourcePositions);
			return;
		}
		throw new NoSuchAttributeException("IsTypeExprOfDeclaration doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		if (_sourcePositions != null) {
			io.writeSpace();
			io.write("[");
			io.noSpace();
			for (de.uni_koblenz.jgralab.greql2.schema.SourcePosition _sourcePositionsElement : _sourcePositions) {
				if (_sourcePositionsElement != null) {
					_sourcePositionsElement.writeComponentValues(io);
				} else {
					io.writeIdentifier(GraphIO.NULL_LITERAL);
				}
			}
			io.write("]");
			io.space();
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("sourcePositions")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (_sourcePositions != null) {
				io.writeSpace();
				io.write("[");
				io.noSpace();
				for (de.uni_koblenz.jgralab.greql2.schema.SourcePosition _sourcePositionsElement : _sourcePositions) {
					if (_sourcePositionsElement != null) {
						_sourcePositionsElement.writeComponentValues(io);
					} else {
						io.writeIdentifier(GraphIO.NULL_LITERAL);
					}
				}
				io.write("]");
				io.space();
			} else {
				io.writeIdentifier(GraphIO.NULL_LITERAL);
			}
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("IsTypeExprOfDeclaration doesn't contain an attribute " + attributeName);
	}

	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationInGraph() {
		return (de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation)getNextEdge(de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation.class);
	}

	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf getNextIsTypeExprOfInGraph() {
		return (de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf)getNextEdge(de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf.class);
	}

	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration getNextIsTypeExprOfDeclarationInGraph() {
		return (de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration)getNextEdge(de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration.class);
	}

	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation.class);
	}

	public de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation getNextGreql2AggregationIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.Greql2Aggregation.class, orientation);
	}

	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf getNextIsTypeExprOfIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf.class);
	}

	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf getNextIsTypeExprOfIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOf.class, orientation);
	}

	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration getNextIsTypeExprOfDeclarationIncidence() {
		return (de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration.class);
	}

	public de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration getNextIsTypeExprOfDeclarationIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration)getNextIncidence(de.uni_koblenz.jgralab.greql2.schema.IsTypeExprOfDeclaration.class, orientation);
	}

	public de.uni_koblenz.jgralab.schema.AggregationKind getAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.SHARED;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getAlphaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.SHARED;
	}

	@Override
	public de.uni_koblenz.jgralab.schema.AggregationKind getOmegaAggregationKind() {
		return de.uni_koblenz.jgralab.schema.AggregationKind.NONE;
	}

	protected de.uni_koblenz.jgralab.impl.ReversedEdgeBaseImpl createReversedEdge() {
		return new ReversedIsTypeExprOfDeclarationImpl(this, graph);
	}
	public Expression getAlpha() {
		return (Expression) super.getAlpha();
	}
	public SimpleDeclaration getOmega() {
		return (SimpleDeclaration) super.getOmega();
	}
}
