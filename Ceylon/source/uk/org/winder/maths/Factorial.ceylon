import ceylon.math.whole {Whole, wholeNumber, zero, one, two}

"Type of exception thrown due to incorrect parameter type."
shared class ValueException() extends Exception("Value must be a non-negative integer", null) {}

"Validate that the value is reasonable for the functions.
 Converts the parameter to Whole if necessary."
by("Russel Winder")
Whole validate(Integer|Whole x) {
	switch (x)
	case (is Whole) {
		if (x < zero) { throw ValueException();}
		return x;
	}
	case (is Integer) {
		if (x < 0) { throw ValueException();}
		return wholeNumber(x);
	}
}

"Classic iterative implementation."
by("Russel Winder")
shared Whole factorial_iterative(Integer|Whole x) {
	value n = validate(x);
	if (n < two) { return one; }
	variable Whole total = one;
	for (i in two..n) {total *= i;}
	return total;
}

"Implementation using the higher-order function reduce for implicit iteration."
by("Russel Winder")
shared Whole factorial_reductive(Integer|Whole x) {
	value n = validate(x);
	return if (n < two) then one else (two..n).reduce(times<Whole>);
}

"Implementation using the higher-order function fold for implicit iteration."
by("Russel Winder")
shared Whole factorial_foldive(Integer|Whole x) {
	value n = validate(x);
	// return (two..n).fold(one)(times<Whole>); doesn't work as 2..0 isn't not empty.
	return if (n < two) then one else (two..n).fold(one)(times<Whole>);
}

"Naïve recursive implementation."
by("Russel Winder")
shared Whole factorial_recursive(Integer|Whole x) {
	value n = validate(x);
	return if (n < two) then one else n * factorial_recursive(n - one);
}

"Tail recursive implementation."
by("russel Winder")
shared Whole factorial_tailRecursive(Integer|Whole x) {
	Whole iterate(Whole n, Whole t = one) {
		return if (n < two) then t else iterate(n - one, t * n);
	}
	return iterate(validate(x));
}
