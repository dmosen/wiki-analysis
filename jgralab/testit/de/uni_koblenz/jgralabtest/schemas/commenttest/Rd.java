/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.commenttest;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import de.uni_koblenz.jgralab.NoSuchAttributeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rd implements de.uni_koblenz.jgralab.Record {
	private static List<String> componentNames = new ArrayList<String>(2);

	static {
		componentNames.add("x");
		componentNames.add("y");
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

	private final int _x;

	private final int _y;

	public Rd(int _x, int _y) {
		this._x = _x;
		this._y = _y;
	}
	
	public Rd(java.util.Map<String, Object> componentValues) {
		assert componentValues.size() == 2;
		assert componentValues.containsKey("x");
		_x = (java.lang.Integer)componentValues.get("x");
		assert componentValues.containsKey("y");
		_y = (java.lang.Integer)componentValues.get("y");
	}

	public Rd(GraphIO io) throws GraphIOException {
		io.match("(");
		_x = io.matchInteger();
		_y = io.matchInteger();
		io.match(")");
	}

	public int get_x() {
		return _x;
	}

	public int get_y() {
		return _y;
	}

	@Override
	public Object getComponent(String name) {
		if (name.equals("x")) {
			return _x;
		}
		if (name.equals("y")) {
			return _y;
		}
		throw new NoSuchAttributeException("Rd doesn't contain an attribute " + name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String xString;
		xString = String.valueOf(_x);;
		sb.append("[").append("x").append("=").append(xString);
		String yString;
		yString = String.valueOf(_y);;
		sb.append(", ").append("y").append("=").append(yString);
		return sb.append("]").toString();
	}

	@Override
	public void writeComponentValues(GraphIO io) throws IOException, GraphIOException {
		io.writeSpace();
		io.write("(");
		io.noSpace();
		io.writeInteger(_x);
		io.writeInteger(_y);
		io.write(")");
	}
	public org.pcollections.PMap<String, Object> toPMap() {
		org.pcollections.PMap<String, Object> m = de.uni_koblenz.jgralab.JGraLab.map();
		m = m.plus("x", _x);
		m = m.plus("y", _y);
		return m;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof Rd) {
			Rd rec = (Rd) o;
			if (_x != rec._x) {
				return false;
			}
			if (_y != rec._y) {
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
				if (!rec.getComponent("x").equals(_x)) {
					return false;
				}
				if (!rec.getComponent("y").equals(_y)) {
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
		h += ((java.lang.Integer) _x).hashCode();
		h += ((java.lang.Integer) _y).hashCode();
		return h;
	}
}
