package uk.org.winder.maths.factorial

import spock.lang.Specification
import spock.lang.Unroll

class Factorial_Spock extends Specification{
  private static final algorithms = [
      [FactorialPackage.&iterative, 'iterative'],
      [FactorialPackage.&naïve_recursive, 'recursive'],
      [FactorialPackage.&tail_recursive, 'tailRecursive'],
      [FactorialPackage.&reductive, 'reductive'],
  ]

  private static final positiveData = [
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
      [40, 815915283247897734345611269596115894272000000000G]
  ]

  private static final negativeData = [-1, -2, -5, -10, -20, -100]

  private static final floatData = [100.5, 20.5, 10.5, 5.5, 2.5, 0.5, -0.5, -2.5, -5.5, -10.5, -20.5, -100.5]

  @Unroll
  def '#name(#n) [positive argument] should give result #expected'() {
    expect: expected == f.call(n)
    where: [f, name, n, expected] << algorithms.collectMany{a -> positiveData.collect{pd -> [*a, *pd]}}
  }

  @Unroll
  def '#name(#n) [negative argument] should throw an IllegalArgumentException'() {
    when: f.call(n)
    then: thrown(IllegalArgumentException)
    where: [f, name, n] << algorithms.collectMany{a -> negativeData.collect{nd -> [*a, nd]}}
  }

  @Unroll
  def '#name(#n) [float argument] should throw a MissingMethodException'() {
    when: f.call(n)
    then: thrown(MissingMethodException)
    where: [f, name, n] << algorithms.collectMany{a -> floatData.collect{nd -> [*a, nd]}}
  }

  def 'iterative of a huge number succeeds'() {
    when: FactorialPackage.&iterative(26000)
    then: notThrown(StackOverflowError)
  }

  def 'reductive of a huge number succeeds'() {
    when: FactorialPackage.&reductive(26000)
    then: notThrown(StackOverflowError)
  }

  def 'recursive of a huge number fails with a stack overflow'() {
    when: FactorialPackage.&naïve_recursive(10000)
    then: thrown(StackOverflowError)
  }

  def 'tailRecursive of a huge number fails with a stack overflow'() {
    when: FactorialPackage.&tail_recursive(10000)
    then: thrown(StackOverflowError)
  }

}
