package uk.org.winder.maths

import org.testng.annotations.Test
import org.testng.annotations.DataProvider

import static org.testng.Assert.assertEquals

class Test_Factorial_TestNG_Groovy {

  private final algorithms = [
      Factorial.&iterative,
      Factorial.&naïveRecursive,
      Factorial.&tailRecursive,
      Factorial.&reductive,
  ]

  private final positiveData = [
      [0L, 1G],
      [1L, 1G],
      [2L, 2G],
      [3L, 6G],
      [4L, 24G],
      [5L, 120G],
      [6L, 720G],
      [7L, 5040G],
      [8L, 40320G],
      [9L, 362880G],
      [10L, 3628800G],
      [11L, 39916800G],
      [12L, 479001600G],
      [13L, 6227020800G],
      [14L, 87178291200G],
      [20L, 2432902008176640000G],
      [30L, 265252859812191058636308480000000G],
      [40L, 815915283247897734345611269596115894272000000000G]
  ]

  private final negativeData = [-1L, -2L, -5L, -10L, -20L, -100L]

  private final floatData = [100.5D, 20.5D, 10.5D, 5.5D, 2.5D, 0.5D, -0.5D, -2.5D, -5.5D, -10.5D, -20.5D, -100.5D]

  @DataProvider
  private Object[][] algorithmsAndPositiveData() {
    algorithms.collectMany{a -> positiveData.collect{[a, *it]}} as Object[][]
  }

  @DataProvider
  private Object[][] algorithmsAndNegativeData() {
    algorithms.collectMany{a -> negativeData.collect{[a, it]}} as Object[][]
  }

  @DataProvider
  private Object[][] algorithmsAndFloatData() {
    algorithms.collectMany{a -> floatData.collect{[a, it]}} as Object[][]
  }

  @Test(dataProvider = "algorithmsAndPositiveData")
  public void positiveArgumentShouldWork(Closure f, long n, BigInteger expected) {
    assertEquals(f(n), expected)
  }

  @Test(dataProvider = "algorithmsAndNegativeData", expectedExceptions = [IllegalArgumentException])
  public void negativeArgumentShouldThrowException(Closure f, long n) { f(n) }

  @Test(dataProvider = "algorithmsAndFloatData", expectedExceptions = [MissingMethodException])
  public void floatArgumentShouldThrowException(Closure f, double n) { f(n) }

  @Test
  public void iterativeEnormousSucceeds() { Factorial.iterative(26000) }

  @Test
  public void reductiveEnormousSucceeds() { Factorial.reductive(26000) }

  @Test(expectedExceptions = [StackOverflowError])
  public void recursiveEnormousFails() { Factorial.naïveRecursive(13000) }

  @Test(expectedExceptions = [StackOverflowError])
  public void tailRecursiveEnormousFails() { Factorial.tailRecursive(26000) }
}
