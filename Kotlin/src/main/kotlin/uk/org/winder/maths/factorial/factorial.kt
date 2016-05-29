package uk.org.winder.maths.factorial

import java.math.BigInteger

val zero = BigInteger.ZERO
val one = BigInteger.ONE
val two = 2.bigint

fun validate(x:BigInteger):Unit {
  if (x < zero) { throw IllegalArgumentException() }
}

fun iterative(x:BigInteger):BigInteger {
  validate(x)
  var total = one
  (two rangeTo x).forEach{i -> total *= i}
  return total
}
fun iterative(x:Int):BigInteger = iterative(x.bigint)
fun iterative(x:Long):BigInteger = iterative(x.bigint)

fun reductive(x:BigInteger):BigInteger {
  validate(x)
  if (x < two) { return one }
  return (one rangeTo x).reduce{t, i -> t * i}
}
fun reductive(x:Int):BigInteger = reductive(x.bigint)
fun reductive(x:Long):BigInteger = reductive(x.bigint)

fun naïve_recursive(x:BigInteger):BigInteger {
  validate(x)
  if (x < two) { return one }
  return x * naïve_recursive(x - one)
}
fun naïve_recursive(x:Int):BigInteger = naïve_recursive(x.bigint)
fun naïve_recursive(x:Long):BigInteger = naïve_recursive(x.bigint)

// Use snake case to avoid conflict with Kotlin's tailRecursive function.
fun tail_recursive(x:BigInteger):BigInteger {
  validate(x)
  tailrec fun iterate(n:BigInteger, t:BigInteger=one):BigInteger {
    if (n < two) { return t }
    return iterate(n - one, n * t)
  }
  return iterate(x)
}
fun tail_recursive(x:Int):BigInteger = tail_recursive(x.bigint)
fun tail_recursive(x:Long):BigInteger = tail_recursive(x.bigint)

