package uk.org.winder.maths

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

import java.util.function.LongFunction

import static org.junit.Assert.assertEquals

final class Factorial_JUnit4_Groovy {
  static final algorithms = [
      [Factorial.&iterative, 'iterative'],
      [Factorial.&naïveRecursive, 'naïveRecursive'],
      [Factorial.&tailRecursive, 'tailRecursive'],
      [Factorial.&reductive, 'reductive'],
  ]
}

@RunWith(Parameterized.class)
final class Factorial_JUnit4_Groovy_Positive {

   private static values = [
      [0, 1G],
      [1, 1G],
      [2, 2G],
      [3, 6G],
      [4, 24G],
      [5, 120G],
      [6, 720G],
      [7, 5040G],
      [8, 40320G],
      [9, 362880G],
      [10, 3628800G],
      [11, 39916800G],
      [12, 479001600G],
      [13, 6227020800G],
      [14, 87178291200G],
      [20, 2432902008176640000G],
      [30, 265252859812191058636308480000000G],
      [40, 815915283247897734345611269596115894272000000000G],
  ]

  @Parameters(name = "{1}({2})")
  static Collection<Object[]> data() {
     return Factorial_JUnit4_Groovy.algorithms.collectMany{a -> values.collect{[*a, *it] as Object[]}}
  }

  private final Closure a
  private final String name
  private final Integer n
  private final BigInteger r

  Factorial_JUnit4_Groovy_Positive(final Closure a, final String name, final Integer n, final BigInteger r) {
    this.a = a
    this.name = name
    this.n = n
    this.r = r
  }

  @Test
  void test() { assertEquals(a(n), r) }

}

@RunWith(Parameterized.class)
final class Factorial_JUnit4_Groovy_Negative {

  private static final values = [-1, -2, -5, -10, -20, -100]

  @Parameters(name = "{1}({2})")
  static Collection<Object[]> data() {
    return Factorial_JUnit4_Groovy.algorithms.collectMany{ a -> values.collect{[*a, it] as Object[]}}
  }

  private final Closure a
  private final String name
  private final Integer n

  Factorial_JUnit4_Groovy_Negative(final Closure a, final String name, final Integer n) {
    this.a = a
    this.name = name
    this.n = n
  }

  @Test(expected=RuntimeException.class)
  void test() { a(n) }

}
