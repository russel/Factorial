package uk.org.winder.maths.factorial

import java.math.BigInteger

import org.testng.annotations.Test
import org.testng.annotations.DataProvider

import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue

class Factorial_TestNG {

  // Cannot use ::iterative, ::recursive, ::tail_recursive, ::reductive here as each is an overloaded
  // function and there is (as at 2015-01-31 Kotlin 0.10.694) no way of disambiguating.
  // So create lambdas, to force correct types and hence function selection

  val algorithms = array(
      array({(x:Long) -> iterative(x)}, "iterative"),
      array({(x:Long) -> recursive(x)}, "recursive"),
      array({(x:Long) -> tail_recursive(x)}, "tail_recursive"),
      array({(x:Long) -> reductive(x)}, "reductive")
  )

  val positiveData = array(
      array(0, BigInteger.ONE),
      array(1, BigInteger.ONE),
      array(2, BigInteger.valueOf(2)),
      array(3, BigInteger.valueOf(6)),
      array(4, BigInteger.valueOf(24)),
      array(5, BigInteger.valueOf(120)),
      array(6, BigInteger.valueOf(720)),
      array(7, BigInteger.valueOf(5040)),
      array(8, BigInteger.valueOf(40320)),
      array(9, BigInteger.valueOf(362880)),
      array(10, BigInteger.valueOf(3628800)),
      array(11, BigInteger.valueOf(39916800)),
      array(12, BigInteger.valueOf(479001600)),
      array(13, BigInteger.valueOf(6227020800)),
      array(14, BigInteger.valueOf(87178291200)),
      array(20, BigInteger.valueOf(2432902008176640000)),
      array(30, BigInteger("265252859812191058636308480000000")),
      array(40, BigInteger("815915283247897734345611269596115894272000000000"))
  )

  val negativeData = array(-1, -2, -5, -10, -20, -100)

  DataProvider
  fun algorithmsAndPositiveData() = algorithms.flatMap({a -> positiveData.map({d -> array(*a, *d)})}).copyToArray()


  Test(dataProvider="algorithmsAndPositiveData")
  fun nonNegativeArgument(algorithm: (Long)->BigInteger, name:String, value:Long, expected:BigInteger) {
    assertEquals(algorithm(value), expected)
  }

  DataProvider
  fun algorithmsAndNegativeData() = algorithms.flatMap({a -> negativeData.map({d -> array(*a, d)})}).copyToArray()

  Test(dataProvider="algorithmsAndNegativeData", expectedExceptions=array(javaClass<IllegalArgumentException>()))
  fun negativeArgument(algorithm: (Long)->BigInteger, name:String, value:Long) {
    algorithm(value)
  }

  Test
  fun iterativeOfAHugeNumberSucceeds() { iterative(26000) }

  Test
  fun reductiveOfAHugeNumberSucceeds() { reductive(26000) }

  Test(expectedExceptions=array(javaClass<StackOverflowError>()))
  fun recursiveOfAHugeNumberFailsWithAStackOverflow() { recursive(13000) }

  Test(expectedExceptions=array(javaClass<StackOverflowError>()))
  fun tailRecursiveOfAHugeNumberFailsWithAStackOverflow() { tail_recursive(13000) }

}
