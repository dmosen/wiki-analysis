package de.uni_koblenz.jgralab.greql2.funlib.bitops;

import de.uni_koblenz.jgralab.greql2.funlib.Function;

public class BitUnsignedShr extends Function {
	public BitUnsignedShr() {
		super(
				"Shifts the first number by the second argument's number of bits to the right (unsigned).",
				4, 1, 1.0, Category.ARITHMETICS);
	}

	public Integer evaluate(Integer a, Integer n) {
		return a.intValue() >>> n.intValue();
	}

	public Long evaluate(Long a, Integer n) {
		return a.longValue() >>> n.intValue();
	}
}
