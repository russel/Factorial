package uk.org.winder.maths.factorial

import java.math.BigInteger
import kotlin.math.times
import kotlin.math.minus
import kotlin.math.plus

val zero = BigInteger.ZERO
val one = BigInteger.ONE
val two = BigInteger.valueOf(2)

fun validate(x:BigInteger):Unit {
  if (x < zero) { throw IllegalArgumentException() }
}

fun iterative(x:Long):BigInteger = iterative(BigInteger.valueOf(x))
fun iterative(x:BigInteger):BigInteger {
  validate(x)
  var total = one
  //(two..x).forEach{i -> total *= i}
  //for (i in two..x) { total *= i }
  return total
}

fun recursive(x:Long):BigInteger = recursive(BigInteger.valueOf(x))
fun recursive(x:BigInteger):BigInteger {
  validate(x)
  if (x < two) { return one }
  return x * recursive(x - one)
}

// Use snake case to avoid conflict with Kotlin's tailRecursive function.
fun tail_recursive(x:Long):BigInteger = tail_recursive(BigInteger.valueOf(x))
fun tail_recursive(x:BigInteger):BigInteger {
  validate(x)
  fun iterate(n:BigInteger, t:BigInteger=one):BigInteger {
    if (n < two) { return t }
    return iterate(n - one, n * t)
  }
  return iterate(x)
}

fun reductive(x:Long):BigInteger = reductive(BigInteger.valueOf(x))
fun reductive(x:BigInteger):BigInteger {
  validate(x)
  if (x < two) { return one }
  //return (one..x).reduce({(t:BigInteger, i:BigInteger) -> t * i})
  return one
}

