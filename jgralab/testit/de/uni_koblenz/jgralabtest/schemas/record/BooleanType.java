/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.record;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BooleanType implements de.uni_koblenz.jgralab.Record {
	private static List<String> componentNames = new ArrayList<String>(2);

	static {
		componentNames.add("isSet");
		componentNames.add("value");
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
	return 2;
}

	private final boolean _isSet;

	private final boolean _value;

	public BooleanType(boolean _isSet, boolean _value) {
		this._isSet = _isSet;
		this._value = _value;
	}
	
	public BooleanType(java.util.Map<String, Object> componentValues) {
		assert componentValues.size() == 2;
		assert componentValues.containsKey("isSet");
		_isSet = (java.lang.Boolean)componentValues.get("isSet");
		assert componentValues.containsKey("value");
		_value = (java.lang.Boolean)componentValues.get("value");
	}

	public BooleanType(GraphIO io) throws GraphIOException {
		io.match("(");
		_isSet = io.matchBoolean();
		_value = io.matchBoolean();
		io.match(")");
	}

	public boolean is_isSet() {
		return _isSet;
	}

	public boolean is_value() {
		return _value;
	}

	@Override
	public Object getComponent(String name) {
		if (name.equals("isSet")) {
			return _isSet;
		}
		if (name.equals("value")) {
			return _value;
		}
		throw new NoSuchAttributeException("BooleanType doesn't contain an attribute " + name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String isSetString;
		isSetString = String.valueOf(_isSet);;
		sb.append("[").append("isSet").append("=").append(isSetString);
		String valueString;
		valueString = String.valueOf(_value);;
		sb.append(", ").append("value").append("=").append(valueString);
		return sb.append("]").toString();
	}

	@Override
	public void writeComponentValues(GraphIO io) throws IOException, GraphIOException {
		io.writeSpace();
		io.write("(");
		io.noSpace();
		io.writeBoolean(_isSet);
		io.writeBoolean(_value);
		io.write(")");
	}
	public org.pcollections.PMap<String, Object> toPMap() {
		org.pcollections.PMap<String, Object> m = de.uni_koblenz.jgralab.JGraLab.map();
		m = m.plus("isSet", _isSet);
		m = m.plus("value", _value);
		return m;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof BooleanType) {
			BooleanType rec = (BooleanType) o;
			if (_isSet != rec._isSet) {
				return false;
			}
			if (_value != rec._value) {
				return false;
			}
			return true;
		}
		if (o instanceof de.uni_koblenz.jgralab.Record) {
			de.uni_koblenz.jgralab.Record rec = (de.uni_koblenz.jgralab.Record) o;
			if (rec.size() != 2) {
				return false;
			}
			try {
				if (!rec.getComponent("isSet").equals(_isSet)) {
					return false;
				}
				if (!rec.getComponent("value").equals(_value)) {
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
		h += ((java.lang.Boolean) _isSet).hashCode();
		h += ((java.lang.Boolean) _value).hashCode();
		return h;
	}
}
