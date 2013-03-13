package de.uni_koblenz.jgralab.greql2.funlib.artithmetics;

import de.uni_koblenz.jgralab.greql2.funlib.Function;

public class Sqrt extends Function {
	public Sqrt() {
		super("Returns the square root of the given number.", 4, 1, 1.0,
				Category.ARITHMETICS);
	}

	public Number evaluate(Number a) {
		return Math.sqrt(a.doubleValue());
	}
}
