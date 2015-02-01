package uk.org.winder.maths

import org.testng.annotations.Test
import org.testng.annotations.DataProvider

import static org.testng.Assert.assertEquals

class Factorial_TestNG_Groovy {

  private final algorithms = [
      Factorial.&iterative,
      Factorial.&recursive,
      Factorial.&tailRecursive,
      Factorial.&reductive,
  ]

  private final positiveData = [
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

  private final negativeData = [-1, -2, -5, -10, -20, -100]

  private final floatData = [100.5, 20.5, 10.5, 5.5, 2.5, 0.5, -0.5, -2.5, -5.5, -10.5, -20.5, -100.5]

  @DataProvider
  private Object[][] algorithmsAndPositiveData() {
    algorithms.collectMany{a -> positiveData.collect{pd -> [a, *pd]}} as Object[][]
  }

  @DataProvider
  private Object[][] algorithmsAndNegativeData() {
    algorithms.collectMany{a -> negativeData.collect{pd -> [a, pd]}} as Object[][]
  }

  @DataProvider
  private Object[][] algorithmsAndFloatData() {
    algorithms.collectMany{a -> floatData.collect{pd -> [a, pd]}} as Object[][]
  }

  @Test(dataProvider = "algorithmsAndPositiveData")
  public void positiveArgumentShouldWork(Closure f, long n, BigInteger expected) {
    assertEquals(f(n), expected)
  }

  @Test(dataProvider = "algorithmsAndNegativeData", expectedExceptions = [IllegalArgumentException])
  public void negativeArgumentShouldThrowException(Closure f, long n) { f(n) }

  @Test(dataProvider = "algorithmsAndFloatData", expectedExceptions = [IllegalArgumentException])
  public void floatArgumentShouldThrowException(Closure f, double n) { f(n) }

  @Test
  public void iterativeEnormousSucceeds() { Factorial.iterative(26000) }

  @Test
  public void reductiveEnormousSucceeds() { Factorial.reductive(26000) }

  @Test(expectedExceptions = [StackOverflowError])
  public void recursiveEnormousFails() { Factorial.recursive(13000) }

  @Test(expectedExceptions = [StackOverflowError])
  public void tailRecursiveEnormousFails() { Factorial.tailRecursive(26000) }
}
