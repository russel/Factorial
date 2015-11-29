import ceylon.math.whole { Whole, wholeNumber, zero, one, two }

"Type of exception thrown due to incorrect parameter type."
shared class ValueException() extends Exception("Value must be a non-negative integer", null) { }

"Validate that the value is reasonable for the functions.
 Converts the parameter to Whole if necessary."
by("Russel Winder")
Whole validate(Integer|Whole? x) {
  switch (x)
  case (is Null) { throw ValueException() ; }
  case (is Whole) {
    if (x < zero) { throw ValueException(); }
    return x;
  }
  case (is Integer) {
    if (x < 0) { throw ValueException(); }
    return wholeNumber(x);
  }
}

"Classic iterative implementation."
by("Russel Winder")
shared Whole factorial_iterative(Integer|Whole? x) {
  value n = validate(x);
  if (n < two) { return one; }
  variable Whole total = one;
  for (i in two..n) { total *= i; }
  return total;
}

"Naïve recursive implementation."
by("Russel Winder")
shared Whole factorial_recursive(Integer|Whole? x) {
  value n = validate(x);
  if (n < two) { return one; }
  return n * factorial_recursive(n - one);
}

"Tail recursive implementation."
by("russel Winder")
shared Whole factorial_tailRecursive(Integer|Whole? x) {
  Whole iterate(Whole n = validate(x), Whole t = one) {
    if (n < two) { return t; }
    return iterate(n - one, t * n);
  }
  return iterate();
}

"Implementation using higher-order function for implicit iteration."
by("Russel Winder")
shared Whole factorial_reductive(Integer|Whole? x) {
  value n = validate(x);
  if (n < two) { return one; }
  return (two..n).reduce(times<Whole>);
}