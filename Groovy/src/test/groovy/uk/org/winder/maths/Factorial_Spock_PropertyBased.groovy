package uk.org.winder.maths

import spock.lang.Specification

import spock.genesis.Gen
import spock.lang.Unroll

class Factorial_Spock_PropertyBased extends Specification {

  static algorithms = [
      [Factorial.&iterative, 'iterative'],
      [Factorial.&reductive, 'reductive'],
      [Factorial.&recursive, 'recursive'],
      [Factorial.&tailRecursiveFunction, 'tailRecursiveFunction'],
      [Factorial.&tailRecursiveClosure, 'tailRecursiveClosure'],
      [Factorial.&tailRecursiveTrampoline, 'tailRecursiveTrampoline'],
      [Factorial.&continuation, 'continuation'],
  ]

  @Unroll def "#name(#i) obeys the factorial relation for positive arguments"() {
    expect: algorithm.call(i + 1) == (i + 1) * algorithm.call(i)
    where: [algorithm, name, i] << algorithms.collectMany{algorithm -> Gen.integer(0, 1000).take(20).collect{[*algorithm, it]}}
  }

  @Unroll def "#name(#i) throws an exception due to negative integer arguments"() {
    when: algorithm.call(i)
    then: thrown IllegalArgumentException
    where: [algorithm, name, i] << algorithms.collectMany{algorithm -> Gen.integer(-1000, -1).take(10).collect{[*algorithm, it]}}
  }

  @Unroll def "#name(#i) throws an exception due to double argument"() {
    when: algorithm.call(i)
    then: thrown MissingMethodException
    where: [algorithm, name, i] << algorithms.collectMany{algorithm -> Gen.integer(-1000, 1000).take(10).collect{[*algorithm, it + 0.5]}}
  }

}
