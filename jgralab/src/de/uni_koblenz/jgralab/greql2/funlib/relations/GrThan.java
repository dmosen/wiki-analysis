package de.uni_koblenz.jgralab.greql2.funlib.relations;

import de.uni_koblenz.jgralab.greql2.funlib.Function;

public class GrThan extends Function {

	public GrThan() {
		super("Determines if $a > b$. Alternative: a > b", 2, 1, 0.5,
				Category.RELATIONS);
	}

	public <T extends Comparable<? super T>> Boolean evaluate(T a, T b) {
		return a.compareTo(b) > 0;
	}
}
