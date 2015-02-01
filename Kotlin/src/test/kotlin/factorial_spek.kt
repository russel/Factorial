package uk.org.winder.maths.factorial

import java.math.BigInteger

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.givenData
import org.jetbrains.spek.api.shouldEqual

import kotlin.test.failsWith

class Factorial_Spek : Spek() {{

  // Cannot use ::iterative, ::recursive, ::tail_recursive, ::reductive here as each is an overloaded
  // function and there is (as at 2015-01-31 Kotlin 0.10.694) no way of disambiguating.
  // So create lambdas, to force correct types and hence function selection

  val algorithms = array(
      "iterative" to {(x: Long) -> iterative(x) },
      "recursive" to {(x: Long) -> recursive(x) },
      "tail_recursive"to {(x: Long) -> tail_recursive(x) },
      "reductive" to {(x: Long) -> reductive(x) }
  )

  val positiveData = array(
      0 to BigInteger.ONE,
      1 to BigInteger.ONE,
      2 to BigInteger.valueOf(2),
      3 to BigInteger.valueOf(6),
      4 to BigInteger.valueOf(24),
      5 to BigInteger.valueOf(120),
      6 to BigInteger.valueOf(720),
      7 to BigInteger.valueOf(5040),
      8 to BigInteger.valueOf(40320),
      9 to BigInteger.valueOf(362880),
      10 to BigInteger.valueOf(3628800),
      11 to BigInteger.valueOf(39916800),
      12 to BigInteger.valueOf(479001600),
      13 to BigInteger.valueOf(6227020800),
      14 to BigInteger.valueOf(87178291200),
      20 to BigInteger.valueOf(2432902008176640000),
      30 to BigInteger("265252859812191058636308480000000"),
      40 to BigInteger("815915283247897734345611269596115894272000000000")
  )

  val negativeData = array(-1, -2, -5, -10, -20, -100)

  givenData(algorithms.flatMap({ a -> positiveData.map({ d -> array(a.first, a.second, d.first, d.second) }) })) {
    val name = it[0] as String
    val algorithm = it[1] as (Long) -> BigInteger
    val value = (it[2] as Int).toLong()
    val expected = it[3] as BigInteger
    on ("executing ${name}(${value})") {
      it("should result in ${expected}") {
        shouldEqual(expected, algorithm(value))
      }
    }
  }

  givenData(algorithms.flatMap({ a -> negativeData.map({ d -> array(a.first, a.second, d) }) })) {
    val name = it[0] as String
    val algorithm = it[1] as (Long) -> BigInteger
    val value = (it[2] as Int).toLong()
    on("executing ${name}(${value})") {
      it("should thrown an exception") {
        failsWith(javaClass<IllegalArgumentException>(), { algorithm(value) })
      }
    }
  }

  given("iterative 26000") {
    on("executing") {
      it("succeeds") {
        iterative(26000)
      }
    }
  }

 given("reductive 26000") {
    on("executing") {
      it("succeeds") {
        reductive(26000)
      }
    }
  }

 given("recursive 8000") {
    on("executing") {
      it("throws StackOverflowError") {
        failsWith(javaClass<StackOverflowError>(), {recursive(8000)})
      }
    }
  }

 given("tail_recursive 8000") {
    on("executing") {
      it("throws StackOverflowError") {
        failsWith(javaClass<StackOverflowError>(), {tail_recursive(8000)})
      }
    }
  }

}}