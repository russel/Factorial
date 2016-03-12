import ceylon.test{test, testExecutor, assertEquals, assertThatException}
import ceylon.math.whole{Whole, parseWhole, wholeNumber}
import ceylon.language.meta.model{IncompatibleTypeException}

import ceylon.random{DefaultRandom}

import com.athaydes.specks{errorCheck, feature,  propertyCheck, Specification, SpecksTestExecutor }
import com.athaydes.specks.assertion{expect, expectToThrow}
import com.athaydes.specks.matcher{equalTo}

import uk.org.winder.maths{
  factorial_iterative,
  factorial_recursive,
  factorial_tailRecursive,
  factorial_reductive,
  ValueException
}

Whole(Integer|Whole)[] algorithms = [
  factorial_iterative,
  factorial_recursive,
  factorial_tailRecursive,
  factorial_reductive
];

Whole parseStringToWhole(String s) {
	value x = parseWhole(s);
	if (exists x) { return x; }
	throw IncompatibleTypeException("Unexpected Null value.");
}

[Integer|Whole, Whole][] positiveValues = [
  [0, wholeNumber(1)],
  [1, wholeNumber(1)],
  [2, wholeNumber(2)],
  [3, wholeNumber(6)],
  [4, wholeNumber(24)],
  [5, wholeNumber(120)],
  [6, wholeNumber(720)],
  [7, wholeNumber(5040)],
  [8, wholeNumber(40320)],
  [9, wholeNumber(362880)],
  [10, wholeNumber(3628800)],
  [11, wholeNumber(39916800)],
  [12, wholeNumber(479001600)],
  [13, wholeNumber(6227020800)],
  [14, wholeNumber(87178291200)],
  [20, wholeNumber(2432902008176640000)],
  [30, parseStringToWhole("265252859812191058636308480000000")],
  [40, parseStringToWhole("815915283247897734345611269596115894272000000000")]
];

Integer[] negativeValues = [-1, -2, -5, -10, -20, -100];

test
void factorial_positiveValues() {
	// This way of testing exits on the first error, later tests are not run.
  for (algorithm in algorithms) {
    for (item in positiveValues) {
      assertEquals(algorithm(item[0]), item[1], "executing ``algorithm``(``item[0]``)");
    }
  }
}

test
void factorial_negativeValues() {
 	// This way of testing exits on the first error, later tests are not run.
  for (algorithm in algorithms) {
    for (val in negativeValues) {
      assertThatException(() => algorithm(val)).hasType(`ValueException`);
    }
  }
}

testExecutor(`class SpecksTestExecutor`)
test shared Specification factorial_specks() {
	
	value upperBound = 200;
	
	class IntegerInRange(shared Integer val){}
	value random = DefaultRandom();
	function generateIntegerInRange() => IntegerInRange(random.nextElement(1..upperBound));
	
	return Specification{
  	feature{
    	description = "Positive arguments give correct result";
    	when(Whole(Integer|Whole) a, Integer|Whole i, Whole r) => [a(i), r];
    	examples = [for (a in algorithms) for (x in positiveValues) [a, *x]];
    	(Whole v, Whole r) => expect(v, equalTo(r))
    },
 		errorCheck{
    	description = "Negative arguments throw exception";
 			when(Whole(Integer|Whole) a, Integer|Whole i) => a(i); 
			examples = [for (a in algorithms) for (x in negativeValues) [a, x]];
			expectToThrow(`ValueException`)
    },
    propertyCheck{
    	description = "Implementations obey the recurrence relation for input in the range [1, ``upperBound``].";
    	sampleCount = 20;
    	generators = [generateIntegerInRange];
    	when(IntegerInRange n) => [n.val];
    	(Integer n) => expect(factorial_iterative(n), equalTo(factorial_iterative(n - 1) * wholeNumber(n))),
    	(Integer n) => expect(factorial_recursive(n), equalTo(factorial_recursive(n - 1) * wholeNumber(n))),
    	(Integer n) => expect(factorial_tailRecursive(n), equalTo(factorial_tailRecursive(n - 1) * wholeNumber(n))),
    	(Integer n) => expect(factorial_reductive(n), equalTo(factorial_reductive(n - 1) * wholeNumber(n)))
    }
 	};
}
