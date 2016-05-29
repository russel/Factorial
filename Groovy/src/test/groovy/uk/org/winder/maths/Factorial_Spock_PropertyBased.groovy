package uk.org.winder.maths

import spock.lang.Specification
import spock.lang.Unroll

import spock.genesis.Gen

class Factorial_Spock_PropertyBased extends Specification {

  static algorithms = [
      [Factorial.&iterative, 'iterative'],
      [Factorial.&reductive, 'reductive'],
      [Factorial.&naïveRecursive, 'naïveRecursive'],
      [Factorial.&tailRecursive, 'tailRecursiveFunction'],
  ]

  @Unroll def '#name(#i) obeys the factorial relation for positive integer arguments'() {
    expect: f.call(i + 1) == (i + 1) * f.call(i)
    where: [f, name, i] << algorithms.collectMany{a -> Gen.integer(0, 1000).take(20).collect{[*a, it]}}
  }

  @Unroll def '#name(#i) throws an exception due to negative integer arguments'() {
    when: f.call(i)
    then: thrown IllegalArgumentException
    where: [f, name, i] << algorithms.collectMany{a -> Gen.integer(-1000, -1).take(10).collect{[*a, it]}}
  }

  @Unroll def '#name(#i) throws an exception due to double argument'() {
    when: f.call(i)
    then: thrown MissingMethodException
    where: [f, name, i] << algorithms.collectMany{a -> Gen.integer(-1000, 1000).take(10).collect{[*a, it + 0.5]}}
  }

}
