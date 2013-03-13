package de.uni_koblenz.jgralab.greql2.funlib.relations;

import de.uni_koblenz.jgralab.greql2.funlib.Function;

public class LeEqual extends Function {

	public LeEqual() {
		super("Determines if $a \\leq b$. Alternative: a <= b", 2, 1, 0.5,
				Category.RELATIONS);
	}

	public <T extends Comparable<? super T>> Boolean evaluate(T a, T b) {
		return a.compareTo(b) <= 0;
	}
}
