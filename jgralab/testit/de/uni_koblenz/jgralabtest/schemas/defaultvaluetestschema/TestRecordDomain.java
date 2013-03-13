/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestRecordDomain implements de.uni_koblenz.jgralab.Record {
	private static List<String> componentNames = new ArrayList<String>(9);

	static {
		componentNames.add("boolRecord");
		componentNames.add("doubleRecord");
		componentNames.add("enumRecord");
		componentNames.add("intRecord");
		componentNames.add("listRecord");
		componentNames.add("longRecord");
		componentNames.add("mapRecord");
		componentNames.add("setRecord");
		componentNames.add("stringRecord");
		componentNames = Collections.unmodifiableList(componentNames);
	}

	@Override
	public List<String> getComponentNames() {
		return componentNames;
	}

	@Override
	public boolean hasComponent(String name) {
		return componentNames.contains(name);
	}

	@Override
public int size() {
	return 9;
}

	private final boolean _boolRecord;

	private final double _doubleRecord;

	private final de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEnumDomain _enumRecord;

	private final int _intRecord;

	private final org.pcollections.PVector<java.lang.Boolean> _listRecord;

	private final long _longRecord;

	private final org.pcollections.PMap<java.lang.Integer, java.lang.Boolean> _mapRecord;

	private final org.pcollections.PSet<java.lang.Boolean> _setRecord;

	private final java.lang.String _stringRecord;

	public TestRecordDomain(boolean _boolRecord, double _doubleRecord, de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEnumDomain _enumRecord, int _intRecord, org.pcollections.PVector<java.lang.Boolean> _listRecord, long _longRecord, org.pcollections.PMap<java.lang.Integer, java.lang.Boolean> _mapRecord, org.pcollections.PSet<java.lang.Boolean> _setRecord, java.lang.String _stringRecord) {
		this._boolRecord = _boolRecord;
		this._doubleRecord = _doubleRecord;
		this._enumRecord = _enumRecord;
		this._intRecord = _intRecord;
		this._listRecord = _listRecord;
		this._longRecord = _longRecord;
		this._mapRecord = _mapRecord;
		this._setRecord = _setRecord;
		this._stringRecord = _stringRecord;
	}
	
	@SuppressWarnings("unchecked")
	public TestRecordDomain(java.util.Map<String, Object> componentValues) {
		assert componentValues.size() == 9;
		assert componentValues.containsKey("boolRecord");
		_boolRecord = (java.lang.Boolean)componentValues.get("boolRecord");
		assert componentValues.containsKey("doubleRecord");
		_doubleRecord = (java.lang.Double)componentValues.get("doubleRecord");
		assert componentValues.containsKey("enumRecord");
		_enumRecord = (de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEnumDomain)componentValues.get("enumRecord");
		assert componentValues.containsKey("intRecord");
		_intRecord = (java.lang.Integer)componentValues.get("intRecord");
		assert componentValues.containsKey("listRecord");
		_listRecord = (org.pcollections.PVector<java.lang.Boolean>)componentValues.get("listRecord");
		assert componentValues.containsKey("longRecord");
		_longRecord = (java.lang.Long)componentValues.get("longRecord");
		assert componentValues.containsKey("mapRecord");
		_mapRecord = (org.pcollections.PMap<java.lang.Integer, java.lang.Boolean>)componentValues.get("mapRecord");
		assert componentValues.containsKey("setRecord");
		_setRecord = (org.pcollections.PSet<java.lang.Boolean>)componentValues.get("setRecord");
		assert componentValues.containsKey("stringRecord");
		_stringRecord = (java.lang.String)componentValues.get("stringRecord");
	}

	public TestRecordDomain(GraphIO io) throws GraphIOException {
		io.match("(");
		_boolRecord = io.matchBoolean();
		_doubleRecord = io.matchDouble();
		_enumRecord = de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEnumDomain.valueOfPermitNull(io.matchEnumConstant());
		_intRecord = io.matchInteger();
		
		if (io.isNextToken("[")) {
			org.pcollections.PVector<java.lang.Boolean> $_listRecord = de.uni_koblenz.jgralab.JGraLab.vector();
			io.match("[");
			while (!io.isNextToken("]")) {
				boolean $_listRecordElement;
				$_listRecordElement = io.matchBoolean();
				$_listRecord = $_listRecord.plus($_listRecordElement);
			}
			io.match("]");
			_listRecord = $_listRecord;
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match(); 
			_listRecord = null;
		} else {
			_listRecord = null;
		}
		_longRecord = io.matchLong();
		
		if (io.isNextToken("{")) {
			org.pcollections.PMap<java.lang.Integer, java.lang.Boolean> $_mapRecord = de.uni_koblenz.jgralab.JGraLab.map();
			io.match("{");
			while (!io.isNextToken("}")) {
				int _mapRecordKey;
					boolean _mapRecordValue;
				_mapRecordKey = io.matchInteger();
				io.match("-");
				_mapRecordValue = io.matchBoolean();
				$_mapRecord = $_mapRecord.plus(_mapRecordKey, _mapRecordValue);
			}
			io.match("}");
			_mapRecord = $_mapRecord;
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match();
			_mapRecord = null;
		} else {
			_mapRecord = null;
		}
		
		if (io.isNextToken("{")) {
			org.pcollections.PSet<java.lang.Boolean> $_setRecord = de.uni_koblenz.jgralab.JGraLab.set();
			io.match("{");
			while (!io.isNextToken("}")) {
				boolean $_setRecordElement;
				$_setRecordElement = io.matchBoolean();
				$_setRecord = $_setRecord.plus($_setRecordElement);
			}
			io.match("}");
			_setRecord = $_setRecord;
		} else if (io.isNextToken(GraphIO.NULL_LITERAL)) {
			io.match();
			_setRecord = null;
		} else {
			_setRecord = null;
		}
		_stringRecord = io.matchUtfString();
		io.match(")");
	}

	public boolean is_boolRecord() {
		return _boolRecord;
	}

	public double get_doubleRecord() {
		return _doubleRecord;
	}

	public de.uni_koblenz.jgralabtest.schemas.defaultvaluetestschema.TestEnumDomain get_enumRecord() {
		return _enumRecord;
	}

	public int get_intRecord() {
		return _intRecord;
	}

	public org.pcollections.PVector<java.lang.Boolean> get_listRecord() {
		return _listRecord;
	}

	public long get_longRecord() {
		return _longRecord;
	}

	public org.pcollections.PMap<java.lang.Integer, java.lang.Boolean> get_mapRecord() {
		return _mapRecord;
	}

	public org.pcollections.PSet<java.lang.Boolean> get_setRecord() {
		return _setRecord;
	}

	public java.lang.String get_stringRecord() {
		return _stringRecord;
	}

	@Override
	public Object getComponent(String name) {
		if (name.equals("boolRecord")) {
			return _boolRecord;
		}
		if (name.equals("doubleRecord")) {
			return _doubleRecord;
		}
		if (name.equals("enumRecord")) {
			return _enumRecord;
		}
		if (name.equals("intRecord")) {
			return _intRecord;
		}
		if (name.equals("listRecord")) {
			return _listRecord;
		}
		if (name.equals("longRecord")) {
			return _longRecord;
		}
		if (name.equals("mapRecord")) {
			return _mapRecord;
		}
		if (name.equals("setRecord")) {
			return _setRecord;
		}
		if (name.equals("stringRecord")) {
			return _stringRecord;
		}
		throw new NoSuchAttributeException("TestRecordDomain doesn't contain an attribute " + name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String boolRecordString;
		boolRecordString = String.valueOf(_boolRecord);;
		sb.append("[").append("boolRecord").append("=").append(boolRecordString);
		String doubleRecordString;
		doubleRecordString = String.valueOf(_doubleRecord);;
		sb.append(", ").append("doubleRecord").append("=").append(doubleRecordString);
		String enumRecordString;
		enumRecordString = String.valueOf(_enumRecord);;
		sb.append(", ").append("enumRecord").append("=").append(enumRecordString);
		String intRecordString;
		intRecordString = String.valueOf(_intRecord);;
		sb.append(", ").append("intRecord").append("=").append(intRecordString);
		String listRecordString;
		if (_listRecord == null) listRecordString = "null";
		else listRecordString = _listRecord.toString();
		sb.append(", ").append("listRecord").append("=").append(listRecordString);
		String longRecordString;
		longRecordString = String.valueOf(_longRecord);;
		sb.append(", ").append("longRecord").append("=").append(longRecordString);
		String mapRecordString;
		if (_mapRecord == null) mapRecordString = "null";
		else mapRecordString = _mapRecord.toString();
		sb.append(", ").append("mapRecord").append("=").append(mapRecordString);
		String setRecordString;
		if (_setRecord == null) setRecordString = "null";
		else setRecordString = _setRecord.toString();
		sb.append(", ").append("setRecord").append("=").append(setRecordString);
		String stringRecordString;
		if (_stringRecord == null) stringRecordString = "null";
		else stringRecordString = String.valueOf(_stringRecord);;
		sb.append(", ").append("stringRecord").append("=").append(stringRecordString);
		return sb.append("]").toString();
	}

	@Override
	public void writeComponentValues(GraphIO io) throws IOException, GraphIOException {
		io.writeSpace();
		io.write("(");
		io.noSpace();
		io.writeBoolean(_boolRecord);
		io.writeDouble(_doubleRecord);
		if (_enumRecord != null) {
			io.writeIdentifier(_enumRecord.toString());
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		io.writeInteger(_intRecord);
		if (_listRecord != null) {
			io.writeSpace();
			io.write("[");
			io.noSpace();
			for (boolean _listRecordElement : _listRecord) {
				io.writeBoolean(_listRecordElement);
			}
			io.write("]");
			io.space();
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		io.writeLong(_longRecord);
		if (_mapRecord != null) {
			io.writeSpace();
			io.write("{");
			io.noSpace();
			for (int key: _mapRecord.keySet()) {
				boolean value = _mapRecord.get(key);
				io.writeInteger(key);
				io.write(" -");
				io.writeBoolean(value);
			}
			io.write("}");
			io.space();
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		if (_setRecord != null) {
			io.writeSpace();
			io.write("{");
			io.noSpace();
			for (boolean _setRecordElement : _setRecord) {
				io.writeBoolean(_setRecordElement);
			}
			io.write("}");
			io.space();
		} else {
			io.writeIdentifier(GraphIO.NULL_LITERAL);
		}
		io.writeUtfString(_stringRecord);
		io.write(")");
	}
	public org.pcollections.PMap<String, Object> toPMap() {
		org.pcollections.PMap<String, Object> m = de.uni_koblenz.jgralab.JGraLab.map();
		m = m.plus("boolRecord", _boolRecord);
		m = m.plus("doubleRecord", _doubleRecord);
		m = m.plus("enumRecord", _enumRecord);
		m = m.plus("intRecord", _intRecord);
		m = m.plus("listRecord", _listRecord);
		m = m.plus("longRecord", _longRecord);
		m = m.plus("mapRecord", _mapRecord);
		m = m.plus("setRecord", _setRecord);
		m = m.plus("stringRecord", _stringRecord);
		return m;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof TestRecordDomain) {
			TestRecordDomain rec = (TestRecordDomain) o;
			if (_boolRecord != rec._boolRecord) {
				return false;
			}
			if (_doubleRecord != rec._doubleRecord) {
				return false;
			}
			if (!(_enumRecord.equals(rec._enumRecord))) {
				return false;
				}
			if (_intRecord != rec._intRecord) {
				return false;
			}
			if (!(_listRecord.equals(rec._listRecord))) {
				return false;
				}
			if (_longRecord != rec._longRecord) {
				return false;
			}
			if (!(_mapRecord.equals(rec._mapRecord))) {
				return false;
				}
			if (!(_setRecord.equals(rec._setRecord))) {
				return false;
				}
			if (!(_stringRecord.equals(rec._stringRecord))) {
				return false;
				}
			return true;
		}
		if (o instanceof de.uni_koblenz.jgralab.Record) {
			de.uni_koblenz.jgralab.Record rec = (de.uni_koblenz.jgralab.Record) o;
			if (rec.size() != 9) {
				return false;
			}
			try {
				if (!rec.getComponent("boolRecord").equals(_boolRecord)) {
					return false;
				}
				if (!rec.getComponent("doubleRecord").equals(_doubleRecord)) {
					return false;
				}
				if (!rec.getComponent("enumRecord").equals(_enumRecord)) {
					return false;
				}
				if (!rec.getComponent("intRecord").equals(_intRecord)) {
					return false;
				}
				if (!rec.getComponent("listRecord").equals(_listRecord)) {
					return false;
				}
				if (!rec.getComponent("longRecord").equals(_longRecord)) {
					return false;
				}
				if (!rec.getComponent("mapRecord").equals(_mapRecord)) {
					return false;
				}
				if (!rec.getComponent("setRecord").equals(_setRecord)) {
					return false;
				}
				if (!rec.getComponent("stringRecord").equals(_stringRecord)) {
					return false;
				}
				return true;
			} catch (NoSuchAttributeException e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int h = 0;
		h += ((java.lang.Boolean) _boolRecord).hashCode();
		h += ((java.lang.Double) _doubleRecord).hashCode();
		h += _enumRecord.hashCode();
		h += ((java.lang.Integer) _intRecord).hashCode();
		h += _listRecord.hashCode();
		h += ((java.lang.Long) _longRecord).hashCode();
		h += _mapRecord.hashCode();
		h += _setRecord.hashCode();
		h += _stringRecord.hashCode();
		return h;
	}
}
