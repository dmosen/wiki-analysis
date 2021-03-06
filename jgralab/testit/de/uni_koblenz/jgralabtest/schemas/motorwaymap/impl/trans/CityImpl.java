/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.motorwaymap.impl.trans;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.trans.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class CityImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.motorwaymap.City, de.uni_koblenz.jgralab.Vertex {

	protected de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.String> _name;

	protected de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum> _testEnum;

	protected de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord>> _testList;

	protected de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<org.pcollections.PMap<java.lang.String, java.lang.String>> _testMap;

	protected de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> _testRecord;

	protected de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<org.pcollections.PSet<java.lang.String>> _testSet;

	public CityImpl(int id, de.uni_koblenz.jgralab.Graph g) {
		super(id, g);
	}

	@Override
	public final de.uni_koblenz.jgralab.schema.VertexClass getAttributedElementClass() {
		return de.uni_koblenz.jgralabtest.schemas.motorwaymap.City.VC;
	}

	@Override
	public final java.lang.Class<? extends de.uni_koblenz.jgralab.Vertex> getSchemaClass() {
		return de.uni_koblenz.jgralabtest.schemas.motorwaymap.City.class;
	}

	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String attributeName) {
		if (attributeName.equals("name")) return (T) get_name();
		if (attributeName.equals("testEnum")) return (T) get_testEnum();
		if (attributeName.equals("testList")) return (T) get_testList();
		if (attributeName.equals("testMap")) return (T) get_testMap();
		if (attributeName.equals("testRecord")) return (T) get_testRecord();
		if (attributeName.equals("testSet")) return (T) get_testSet();
		throw new NoSuchAttributeException("City doesn't contain an attribute " + attributeName);
	}

	@SuppressWarnings("unchecked")
	public <T> void setAttribute(String attributeName, T data) {
		if (attributeName.equals("name")) {
			set_name((java.lang.String) data);
			return;
		}
		if (attributeName.equals("testEnum")) {
			if (data instanceof String) {
				set_testEnum(de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum.valueOfPermitNull((String) data));
			} else {
				set_testEnum((de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum) data);
			}
			return;
		}
		if (attributeName.equals("testList")) {
			set_testList((org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord>) data);
			return;
		}
		if (attributeName.equals("testMap")) {
			set_testMap((org.pcollections.PMap<java.lang.String, java.lang.String>) data);
			return;
		}
		if (attributeName.equals("testRecord")) {
			set_testRecord((de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord) data);
			return;
		}
		if (attributeName.equals("testSet")) {
			set_testSet((org.pcollections.PSet<java.lang.String>) data);
			return;
		}
		throw new NoSuchAttributeException("City doesn't contain an attribute " + attributeName);
	}

	public java.lang.String get_name() {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'name', because " + this + " isn't valid in current transaction.");
		if (_name == null)
			return null;
		java.lang.String value = _name.getValidValue(graph.getCurrentTransaction());
		return (value == null) ? null : value;
	}

	public void set_name(java.lang.String _name) {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'name', because " + this + " isn't valid in current transaction.");
		if (graph.isLoading()) {
			this._name = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.String>(this, _name, "name");
		}
		if (this._name == null) {
			this._name = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<java.lang.String>(this, null, "name");
		}
		this._name.setValidValue((java.lang.String) _name, graph.getCurrentTransaction());
		attributeChanged(this._name);
		graphModified();
	}

	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum get_testEnum() {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'testEnum', because " + this + " isn't valid in current transaction.");
		if (_testEnum == null)
			return null;
		de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum value = _testEnum.getValidValue(graph.getCurrentTransaction());
		return (value == null) ? null : value;
	}

	public void set_testEnum(de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum _testEnum) {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'testEnum', because " + this + " isn't valid in current transaction.");
		if (graph.isLoading()) {
			this._testEnum = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum>(this, _testEnum, "testEnum");
		}
		if (this._testEnum == null) {
			this._testEnum = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum>(this, null, "testEnum");
		}
		this._testEnum.setValidValue((de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum) _testEnum, graph.getCurrentTransaction());
		attributeChanged(this._testEnum);
		graphModified();
	}

	public org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> get_testList() {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'testList', because " + this + " isn't valid in current transaction.");
		if (_testList == null)
			return null;
		org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> value = _testList.getValidValue(graph.getCurrentTransaction());
		return (value == null) ? null : value;
	}

	public void set_testList(org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> _testList) {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'testList', because " + this + " isn't valid in current transaction.");
		if (graph.isLoading()) {
			this._testList = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord>>(this, _testList, "testList");
		}
		if (this._testList == null) {
			this._testList = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord>>(this, null, "testList");
		}
		this._testList.setValidValue((org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord>) _testList, graph.getCurrentTransaction());
		attributeChanged(this._testList);
		graphModified();
	}

	public org.pcollections.PMap<java.lang.String, java.lang.String> get_testMap() {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'testMap', because " + this + " isn't valid in current transaction.");
		if (_testMap == null)
			return null;
		org.pcollections.PMap<java.lang.String, java.lang.String> value = _testMap.getValidValue(graph.getCurrentTransaction());
		return (value == null) ? null : value;
	}

	public void set_testMap(org.pcollections.PMap<java.lang.String, java.lang.String> _testMap) {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'testMap', because " + this + " isn't valid in current transaction.");
		if (graph.isLoading()) {
			this._testMap = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<org.pcollections.PMap<java.lang.String, java.lang.String>>(this, _testMap, "testMap");
		}
		if (this._testMap == null) {
			this._testMap = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<org.pcollections.PMap<java.lang.String, java.lang.String>>(this, null, "testMap");
		}
		this._testMap.setValidValue((org.pcollections.PMap<java.lang.String, java.lang.String>) _testMap, graph.getCurrentTransaction());
		attributeChanged(this._testMap);
		graphModified();
	}

	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord get_testRecord() {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'testRecord', because " + this + " isn't valid in current transaction.");
		if (_testRecord == null)
			return null;
		de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord value = _testRecord.getValidValue(graph.getCurrentTransaction());
		return (value == null) ? null : value;
	}

	public void set_testRecord(de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord _testRecord) {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'testRecord', because " + this + " isn't valid in current transaction.");
		if (graph.isLoading()) {
			this._testRecord = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord>(this, _testRecord, "testRecord");
		}
		if (this._testRecord == null) {
			this._testRecord = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord>(this, null, "testRecord");
		}
		this._testRecord.setValidValue((de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord) _testRecord, graph.getCurrentTransaction());
		attributeChanged(this._testRecord);
		graphModified();
	}

	public org.pcollections.PSet<java.lang.String> get_testSet() {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'testSet', because " + this + " isn't valid in current transaction.");
		if (_testSet == null)
			return null;
		org.pcollections.PSet<java.lang.String> value = _testSet.getValidValue(graph.getCurrentTransaction());
		return (value == null) ? null : value;
	}

	public void set_testSet(org.pcollections.PSet<java.lang.String> _testSet) {
		if (!isValid())
			throw new de.uni_koblenz.jgralab.GraphException("Cannot access attribute 'testSet', because " + this + " isn't valid in current transaction.");
		if (graph.isLoading()) {
			this._testSet = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<org.pcollections.PSet<java.lang.String>>(this, _testSet, "testSet");
		}
		if (this._testSet == null) {
			this._testSet = new de.uni_koblenz.jgralab.impl.trans.VersionedReferenceImpl<org.pcollections.PSet<java.lang.String>>(this, null, "testSet");
		}
		this._testSet.setValidValue((org.pcollections.PSet<java.lang.String>) _testSet, graph.getCurrentTransaction());
		attributeChanged(this._testSet);
		graphModified();
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		java.lang.String _name = io.matchUtfString();
		set_name(_name);
		de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum _testEnum = de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum.valueOfPermitNull(io.matchEnumConstant());
		set_testEnum(_testEnum);
		org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> _testList = null;
		if (io.isNextToken("[")) {
			org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> $_testList = de.uni_koblenz.jgralab.JGraLab.vector();
			io.match("[");
			while (!io.isNextToken("]")) {
				de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord $_testListElement = null;
				
				if (io.isNextToken("(")) {
					$_testListElement = new de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord(io);
				} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
					io.match();
					$_testListElement = null;
				} else {
					throw new GraphIOException("This is no record!");
				}
				$_testList = $_testList.plus($_testListElement);
			}
			io.match("]");
			_testList = $_testList;
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match(); 
			_testList = null;
		} else {
			_testList = null;
		}
		set_testList(_testList);
		org.pcollections.PMap<java.lang.String, java.lang.String> _testMap = null;
		if (io.isNextToken("{")) {
			org.pcollections.PMap<java.lang.String, java.lang.String> $_testMap = de.uni_koblenz.jgralab.JGraLab.map();
			io.match("{");
			while (!io.isNextToken("}")) {
				java.lang.String _testMapKey;
					java.lang.String _testMapValue;
				_testMapKey = io.matchUtfString();
				io.match("-");
				_testMapValue = io.matchUtfString();
				$_testMap = $_testMap.plus(_testMapKey, _testMapValue);
			}
			io.match("}");
			_testMap = $_testMap;
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match();
			_testMap = null;
		} else {
			_testMap = null;
		}
		set_testMap(_testMap);
		de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord _testRecord = null;
		if (io.isNextToken("(")) {
			_testRecord = new de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord(io);
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match();
			_testRecord = null;
		} else {
			throw new GraphIOException("This is no record!");
		}
		set_testRecord(_testRecord);
		org.pcollections.PSet<java.lang.String> _testSet = null;
		if (io.isNextToken("{")) {
			org.pcollections.PSet<java.lang.String> $_testSet = de.uni_koblenz.jgralab.JGraLab.set();
			io.match("{");
			while (!io.isNextToken("}")) {
				java.lang.String $_testSetElement;
				$_testSetElement = io.matchUtfString();
				$_testSet = $_testSet.plus($_testSetElement);
			}
			io.match("}");
			_testSet = $_testSet;
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match();
			_testSet = null;
		} else {
			_testSet = null;
		}
		set_testSet(_testSet);
	}

	public void readAttributeValueFromString(String attributeName, String value) throws GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			java.lang.String tmpVar = null;
			tmpVar = io.matchUtfString();
			set_name(tmpVar);
			return;
		}
		if (attributeName.equals("testEnum")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum tmpVar = null;
			tmpVar = de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum.valueOfPermitNull(io.matchEnumConstant());
			set_testEnum(tmpVar);
			return;
		}
		if (attributeName.equals("testList")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> tmpVar = null;
			
			if (io.isNextToken("[")) {
				org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> $tmpVar = de.uni_koblenz.jgralab.JGraLab.vector();
				io.match("[");
				while (!io.isNextToken("]")) {
					de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord $tmpVarElement = null;
					
					if (io.isNextToken("(")) {
						$tmpVarElement = new de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord(io);
					} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
						io.match();
						$tmpVarElement = null;
					} else {
						throw new GraphIOException("This is no record!");
					}
					$tmpVar = $tmpVar.plus($tmpVarElement);
				}
				io.match("]");
				tmpVar = $tmpVar;
			} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
				io.match(); 
				tmpVar = null;
			} else {
				tmpVar = null;
			}
			set_testList(tmpVar);
			return;
		}
		if (attributeName.equals("testMap")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			org.pcollections.PMap<java.lang.String, java.lang.String> tmpVar = null;
			
			if (io.isNextToken("{")) {
				org.pcollections.PMap<java.lang.String, java.lang.String> $tmpVar = de.uni_koblenz.jgralab.JGraLab.map();
				io.match("{");
				while (!io.isNextToken("}")) {
					java.lang.String tmpVarKey;
						java.lang.String tmpVarValue;
					tmpVarKey = io.matchUtfString();
					io.match("-");
					tmpVarValue = io.matchUtfString();
					$tmpVar = $tmpVar.plus(tmpVarKey, tmpVarValue);
				}
				io.match("}");
				tmpVar = $tmpVar;
			} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
				io.match();
				tmpVar = null;
			} else {
				tmpVar = null;
			}
			set_testMap(tmpVar);
			return;
		}
		if (attributeName.equals("testRecord")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord tmpVar = null;
			
			if (io.isNextToken("(")) {
				tmpVar = new de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord(io);
			} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
				io.match();
				tmpVar = null;
			} else {
				throw new GraphIOException("This is no record!");
			}
			set_testRecord(tmpVar);
			return;
		}
		if (attributeName.equals("testSet")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			org.pcollections.PSet<java.lang.String> tmpVar = null;
			
			if (io.isNextToken("{")) {
				org.pcollections.PSet<java.lang.String> $tmpVar = de.uni_koblenz.jgralab.JGraLab.set();
				io.match("{");
				while (!io.isNextToken("}")) {
					java.lang.String $tmpVarElement;
					$tmpVarElement = io.matchUtfString();
					$tmpVar = $tmpVar.plus($tmpVarElement);
				}
				io.match("}");
				tmpVar = $tmpVar;
			} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
				io.match();
				tmpVar = null;
			} else {
				tmpVar = null;
			}
			set_testSet(tmpVar);
			return;
		}
		throw new NoSuchAttributeException("City doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(get_name());
		if (get_testEnum() != null) {
			io.writeIdentifier(get_testEnum().toString());
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		if (get_testList() != null) {
			io.writeSpace();
			io.write("[");
			io.noSpace();
			for (de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord _testListElement : get_testList()) {
				if (_testListElement != null) {
					_testListElement.writeComponentValues(io);
				} else {
					io.writeIdentifier(GraphIO.NULL_LITERAL);
				}
			}
			io.write("]");
			io.space();
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		if (get_testMap() != null) {
			io.writeSpace();
			io.write("{");
			io.noSpace();
			for (java.lang.String key: get_testMap().keySet()) {
				java.lang.String value = get_testMap().get(key);
				io.writeUtfString(key);
				io.write(" -");
				io.writeUtfString(value);
			}
			io.write("}");
			io.space();
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		if (get_testRecord() != null) {
			get_testRecord().writeComponentValues(io);
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		if (get_testSet() != null) {
			io.writeSpace();
			io.write("{");
			io.noSpace();
			for (java.lang.String _testSetElement : get_testSet()) {
				io.writeUtfString(_testSetElement);
			}
			io.write("}");
			io.space();
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
	}

	public String writeAttributeValueToString(String attributeName) throws IOException, GraphIOException {
		if (attributeName.equals("name")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			io.writeUtfString(get_name());
			return io.getStringWriterResult();
		}
		if (attributeName.equals("testEnum")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (get_testEnum() != null) {
				io.writeIdentifier(get_testEnum().toString());
			} else {
				io.writeIdentifier(GraphIO.NULL_LITERAL);
			}
			return io.getStringWriterResult();
		}
		if (attributeName.equals("testList")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (get_testList() != null) {
				io.writeSpace();
				io.write("[");
				io.noSpace();
				for (de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord _testListElement : get_testList()) {
					if (_testListElement != null) {
						_testListElement.writeComponentValues(io);
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
		if (attributeName.equals("testMap")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (get_testMap() != null) {
				io.writeSpace();
				io.write("{");
				io.noSpace();
				for (java.lang.String key: get_testMap().keySet()) {
					java.lang.String value = get_testMap().get(key);
					io.writeUtfString(key);
					io.write(" -");
					io.writeUtfString(value);
				}
				io.write("}");
				io.space();
			} else {
				io.writeIdentifier(GraphIO.NULL_LITERAL);
			}
			return io.getStringWriterResult();
		}
		if (attributeName.equals("testRecord")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (get_testRecord() != null) {
				get_testRecord().writeComponentValues(io);
			} else {
				io.writeIdentifier(GraphIO.NULL_LITERAL);
			}
			return io.getStringWriterResult();
		}
		if (attributeName.equals("testSet")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (get_testSet() != null) {
				io.writeSpace();
				io.write("{");
				io.noSpace();
				for (java.lang.String _testSetElement : get_testSet()) {
					io.writeUtfString(_testSetElement);
				}
				io.write("}");
				io.space();
			} else {
				io.writeIdentifier(GraphIO.NULL_LITERAL);
			}
			return io.getStringWriterResult();
		}
		throw new NoSuchAttributeException("City doesn't contain an attribute " + attributeName);
	}
	public java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes() {
		java.util.Set<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>> attributes = new java.util.HashSet<de.uni_koblenz.jgralab.trans.VersionedDataObject<?>>();
		attributes.add(_name);
		attributes.add(_testEnum);
		attributes.add(_testList);
		attributes.add(_testMap);
		attributes.add(_testRecord);
		attributes.add(_testSet);
		return attributes;
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.City getNextCity() {
		return (de.uni_koblenz.jgralabtest.schemas.motorwaymap.City)getNextVertex(de.uni_koblenz.jgralabtest.schemas.motorwaymap.City.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit getFirstExitIncidence() {
		return (de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit.class);
	}

	@Override
	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit getFirstExitIncidence(EdgeDirection orientation) {
		return (de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit)getFirstIncidence(de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit.class, orientation);
	}

	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit> getExitIncidences() {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit>(this, de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit.class);
	}
	
	@Override
	public Iterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit> getExitIncidences(EdgeDirection direction) {
		return new IncidenceIterable<de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit>(this, de.uni_koblenz.jgralabtest.schemas.motorwaymap.Exit.class, direction);
	}
}
