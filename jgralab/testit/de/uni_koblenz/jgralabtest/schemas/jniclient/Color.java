/*
 * This code was generated automatically.
 * Do NOT edit this file, changes will be lost.
 * Instead, change and commit the underlying schema.
 */

package de.uni_koblenz.jgralabtest.schemas.jniclient;

public enum Color {

	RED, GREEN, BLUE, YELLOW, BLACK, WHITE;

	public static Color valueOfPermitNull(String val) {
		if (val.equals(de.uni_koblenz.jgralab.GraphIO.NULL_LITERAL)) {
			return null;
		}
		return valueOf(val);
	}
}
