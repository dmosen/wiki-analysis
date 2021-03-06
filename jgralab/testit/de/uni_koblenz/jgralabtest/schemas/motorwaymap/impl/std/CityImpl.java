/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.motorwaymap.impl.std;

import de.uni_koblenz.jgralab.impl.IncidenceIterable;

import de.uni_koblenz.jgralab.impl.std.VertexImpl;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;

public class CityImpl extends VertexImpl implements de.uni_koblenz.jgralabtest.schemas.motorwaymap.City, de.uni_koblenz.jgralab.Vertex {

	protected java.lang.String _name;

	protected de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum _testEnum;

	protected org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> _testList;

	protected org.pcollections.PMap<java.lang.String, java.lang.String> _testMap;

	protected de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord _testRecord;

	protected org.pcollections.PSet<java.lang.String> _testSet;

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
		return _name;
	}

	public void set_name(java.lang.String _name) {
		ecaAttributeChanging("name", this._name, _name);
		Object oldValue = this._name;
		this._name = _name;
		graphModified();
	ecaAttributeChanged("name", oldValue, _name);
	}

	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum get_testEnum() {
		return _testEnum;
	}

	public void set_testEnum(de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum _testEnum) {
		ecaAttributeChanging("testEnum", this._testEnum, _testEnum);
		Object oldValue = this._testEnum;
		this._testEnum = _testEnum;
		graphModified();
	ecaAttributeChanged("testEnum", oldValue, _testEnum);
	}

	public org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> get_testList() {
		return _testList;
	}

	public void set_testList(org.pcollections.PVector<de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord> _testList) {
		ecaAttributeChanging("testList", this._testList, _testList);
		Object oldValue = this._testList;
		this._testList = _testList;
		graphModified();
	ecaAttributeChanged("testList", oldValue, _testList);
	}

	public org.pcollections.PMap<java.lang.String, java.lang.String> get_testMap() {
		return _testMap;
	}

	public void set_testMap(org.pcollections.PMap<java.lang.String, java.lang.String> _testMap) {
		ecaAttributeChanging("testMap", this._testMap, _testMap);
		Object oldValue = this._testMap;
		this._testMap = _testMap;
		graphModified();
	ecaAttributeChanged("testMap", oldValue, _testMap);
	}

	public de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord get_testRecord() {
		return _testRecord;
	}

	public void set_testRecord(de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord _testRecord) {
		ecaAttributeChanging("testRecord", this._testRecord, _testRecord);
		Object oldValue = this._testRecord;
		this._testRecord = _testRecord;
		graphModified();
	ecaAttributeChanged("testRecord", oldValue, _testRecord);
	}

	public org.pcollections.PSet<java.lang.String> get_testSet() {
		return _testSet;
	}

	public void set_testSet(org.pcollections.PSet<java.lang.String> _testSet) {
		ecaAttributeChanging("testSet", this._testSet, _testSet);
		Object oldValue = this._testSet;
		this._testSet = _testSet;
		graphModified();
	ecaAttributeChanged("testSet", oldValue, _testSet);
	}

	public void readAttributeValues(GraphIO io) throws GraphIOException {
		_name = io.matchUtfString();
		set_name(_name);
		_testEnum = de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum.valueOfPermitNull(io.matchEnumConstant());
		set_testEnum(_testEnum);
		
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
		
		if (io.isNextToken("(")) {
			_testRecord = new de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord(io);
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match();
			_testRecord = null;
		} else {
			throw new GraphIOException("This is no record!");
		}
		set_testRecord(_testRecord);
		
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
			_name = io.matchUtfString();
			set_name(_name);
			return;
		}
		if (attributeName.equals("testEnum")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			_testEnum = de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestEnum.valueOfPermitNull(io.matchEnumConstant());
			set_testEnum(_testEnum);
			return;
		}
		if (attributeName.equals("testList")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			
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
			return;
		}
		if (attributeName.equals("testMap")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			
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
			return;
		}
		if (attributeName.equals("testRecord")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			
			if (io.isNextToken("(")) {
				_testRecord = new de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord(io);
			} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
				io.match();
				_testRecord = null;
			} else {
				throw new GraphIOException("This is no record!");
			}
			set_testRecord(_testRecord);
			return;
		}
		if (attributeName.equals("testSet")) {
			GraphIO io = GraphIO.createStringReader(value, getSchema());
			
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
			return;
		}
		throw new NoSuchAttributeException("City doesn't contain an attribute " + attributeName);
	}

	public void writeAttributeValues(GraphIO io) throws GraphIOException, IOException {
		io.space();
		io.writeUtfString(_name);
		if (_testEnum != null) {
			io.writeIdentifier(_testEnum.toString());
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		if (_testList != null) {
			io.writeSpace();
			io.write("[");
			io.noSpace();
			for (de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord _testListElement : _testList) {
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
		if (_testMap != null) {
			io.writeSpace();
			io.write("{");
			io.noSpace();
			for (java.lang.String key: _testMap.keySet()) {
				java.lang.String value = _testMap.get(key);
				io.writeUtfString(key);
				io.write(" -");
				io.writeUtfString(value);
			}
			io.write("}");
			io.space();
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		if (_testRecord != null) {
			_testRecord.writeComponentValues(io);
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		if (_testSet != null) {
			io.writeSpace();
			io.write("{");
			io.noSpace();
			for (java.lang.String _testSetElement : _testSet) {
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
			io.writeUtfString(_name);
			return io.getStringWriterResult();
		}
		if (attributeName.equals("testEnum")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (_testEnum != null) {
				io.writeIdentifier(_testEnum.toString());
			} else {
				io.writeIdentifier(GraphIO.NULL_LITERAL);
			}
			return io.getStringWriterResult();
		}
		if (attributeName.equals("testList")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (_testList != null) {
				io.writeSpace();
				io.write("[");
				io.noSpace();
				for (de.uni_koblenz.jgralabtest.schemas.motorwaymap.TestRecord _testListElement : _testList) {
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
			if (_testMap != null) {
				io.writeSpace();
				io.write("{");
				io.noSpace();
				for (java.lang.String key: _testMap.keySet()) {
					java.lang.String value = _testMap.get(key);
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
			if (_testRecord != null) {
				_testRecord.writeComponentValues(io);
			} else {
				io.writeIdentifier(GraphIO.NULL_LITERAL);
			}
			return io.getStringWriterResult();
		}
		if (attributeName.equals("testSet")) {
			GraphIO io = GraphIO.createStringWriter(getSchema());
			if (_testSet != null) {
				io.writeSpace();
				io.write("{");
				io.noSpace();
				for (java.lang.String _testSetElement : _testSet) {
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
