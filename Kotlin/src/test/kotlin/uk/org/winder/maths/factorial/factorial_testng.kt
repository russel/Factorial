package uk.org.winder.maths.factorial

import java.math.BigInteger

import org.testng.annotations.Test
import org.testng.annotations.DataProvider

import org.testng.Assert.assertEquals

class Factorial_TestNG {

  // Cannot use ::iterative, ::recursive, ::tail_recursive, ::reductive here as each is an overloaded
  // function and there is (as at 2015-12-22) no way of disambiguating.
  // So create lambdas, to force correct types and hence function selection

  val algorithms = arrayOf(
          "iterative" to {x:Long -> iterative(x)},
          "naïve_recursive" to {x:Long -> naïve_recursive(x)},
          "tail_recursive" to {x:Long -> tail_recursive(x)},
          "reductive" to {x:Long -> reductive(x)}
  )

  val positiveData = arrayOf(
          0L to BigInteger.ONE,
          1L to BigInteger.ONE,
          2L to  2.bigint,
          3L to 6.bigint,
          4L to 24.bigint,
          5L to 120.bigint,
          6L to 720.bigint,
          7L to 5040.bigint,
          8L to 40320.bigint,
          9L to 362880.bigint,
          10L to 3628800.bigint,
          11L to 39916800.bigint,
          12L to 479001600.bigint,
          13L to 6227020800.bigint,
          14L to 87178291200.bigint,
          20L to 2432902008176640000.bigint,
          30L to BigInteger("265252859812191058636308480000000"),
          40L to BigInteger("815915283247897734345611269596115894272000000000")
  )

  val negativeData = arrayOf(-1L, -2L, -5L, -10L, -20L, -100L)

  @DataProvider
  fun algorithmsAndPositiveData() = algorithms.flatMap({a -> positiveData.map({d -> arrayOf(a.first, a.second, d.first, d.second)})}).toTypedArray()


  @Test(dataProvider="algorithmsAndPositiveData")
  fun nonNegativeArgument(name:String, algorithm:(Long)->BigInteger, value:Long, expected:BigInteger) {
      assertEquals(algorithm(value), expected, "$name($value)")
  }

  @DataProvider
  fun algorithmsAndNegativeData() = algorithms.flatMap({a -> negativeData.map({d -> arrayOf(a.first, a.second, d)})}).toTypedArray()

  @Test(dataProvider="algorithmsAndNegativeData", expectedExceptions=arrayOf(IllegalArgumentException::class))
  fun negativeArgument(name:String, algorithm:(Long)->BigInteger, value:Long) {
      algorithm(value)
  }

  @Test
  fun iterativeOfAHugeNumberSucceeds() { iterative(26000) }

  @Test
  fun reductiveOfAHugeNumberSucceeds() { reductive(26000) }

  @Test(expectedExceptions=arrayOf(StackOverflowError::class))
  fun recursiveOfAHugeNumberFailsWithAStackOverflow() { naïve_recursive(13000) }

  @Test
  fun tailRecursiveOfAHugeNumberFailsWithAStackOverflow() { tail_recursive(26000) }

}
