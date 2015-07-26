package uk.org.winder.maths

import spock.lang.Specification
import spock.lang.Unroll

class Factorial_Spock_ExampleBased extends Specification {

  static algorithms = [
      [Factorial.&iterative, 'iterative'],
      [Factorial.&reductive, 'reductive'],
      [Factorial.&naïveRecursive, 'naïveRecursive'],
      [Factorial.&tailRecursiveFunction, 'tailRecursiveFunction'],
      [Factorial.&tailRecursiveClosure, 'tailRecursiveClosure'],
      [Factorial.&tailRecursiveTrampoline, 'tailRecursiveTrampoline'],
      [Factorial.&continuation, 'continuation'],
  ]

  static positiveData = [
    [0, 1],
    [1, 1],
    [2, 2],
    [3, 6],
    [4, 24],
    [5, 120],
    [6, 720],
    [7, 5040],
    [8, 40320],
    [9, 362880],
    [10, 3628800],
    [11, 39916800],
    [12, 479001600],
    [13, 6227020800G],  // Have to force the correct type here for some reason.
    [14, 87178291200G],  // Have to force the correct type here for some reason.
    [20, 2432902008176640000G],  // Have to force the correct type here for some reason.
    [30, 265252859812191058636308480000000],
    [40, 815915283247897734345611269596115894272000000000],
  ]

  static negativeData = [-1, -2, -5, -10, -20, -100]

  static floatData = [100.5, 20.5, 10.5, 5.5, 2.5, 0.5, -0.5, -2.5, -5.5, -10.5, -20.5, -100.5]


  @Unroll def "#name(#i) [positive argument] should result in #result"() {
    expect: algorithm.call(i) == result
    where: [algorithm, name, i, result] << algorithms.collectMany{algorithm -> positiveData.collect{datum -> [*algorithm, *datum]}}
  }

  @Unroll def "#name(#i) [negative argument] should throw an exception"() {
    when: algorithm.call(i)
    then: thrown IllegalArgumentException
    where: [algorithm, name, i] << algorithms.collectMany{algorithm -> negativeData.collect{[*algorithm, it]}}
  }

  @Unroll def "#name(#i) [non-integer argument] should throw an exception"() {
    when: algorithm.call(i)
    then: thrown MissingMethodException
    where: [algorithm, name, i] << algorithms.collectMany{algorithm -> floatData.collect{[*algorithm, it]}}
  }

  @Unroll
  def "#name succeeds for a big argument"() {
    when: algorithm.call(1000)
    then: notThrown StackOverflowError
    where: [algorithm, name] << algorithms.findAll{algorithm -> (algorithm[1] as String) in [
        'iterative',
        'reductive',
        'naïveRecursive',
        'tailRecursiveFunction',
        //'tailRecursiveClosure', // Throws a StackOverflowError: no tail call optimization.
        'tailRecursiveTrampoline',
        'continuation',
    ]}
  }

  @Unroll def "#name succeeds for an huge argument"() {
    when: algorithm.call(26000)
    then: notThrown StackOverflowError
    where: [algorithm, name] << algorithms.findAll{algorithm -> (algorithm[1] as String) in [
        'iterative',
        //'reductive', // Takes a VERY long time.
        //'naïveRecursive', // Throws a StackOverflowError: expected.
        'tailRecursiveFunction',
        //'tailRecursiveClosure', // Throws a StackOverflowError: no tail call optimization.
        //'tailRecursiveTrampoline', // Takes a VERY long time.
        //'continuation', // Takes a VERY long time.
    ]}
  }

  @Unroll
  def "#name call for large parameter fails due to stack overflow"() {
    when: algorithm.call(13000)
    then: thrown StackOverflowError
    where: [algorithm, name] << algorithms.findAll{algorithm -> (algorithm[1] as String) in [
        //'iterative', // Will never cause StackOverflowError.
        //'reductive', // Should never cause StackOverflowError. Takes a VERY long time.
        'naïveRecursive',
        //'tailRecursiveFunction', // Should never cause StackOverflowError.
        'tailRecursiveClosure', // No tail call optimization.
        //'tailRecursiveTrampoline', // Should never cause StackOverflowError. Takes a VERY long time.
        //'continuation', // Should never cause StackOverflowError. Takes a VERY long time.
    ]}
  }
}
