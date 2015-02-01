package uk.org.winder.maths

import scala.annotation.tailrec

object Factorial {
  val one = new BigInt(java.math.BigInteger.ONE)

  private def validate(n:BigInt):Unit = {
    if (n < 0) throw new IllegalArgumentException("Parameter must be a non-negative integer.")
  }

  def iterative(n:Int):BigInt = iterative(BigInt(n))
  def iterative(n:BigInt):BigInt = {
    validate(n)
    var i = n
    var result = one
    while (i > 0) {
      result = result * i
      i = i - 1
    }
    result
  }

  def recursive(n:Int):BigInt = recursive(BigInt(n))
  def recursive(n:BigInt):BigInt = {
    validate(n)
    if (n < 2) 1
    else n * recursive(n - 1)
  }

  def tailRecursive(n:Int):BigInt = tailRecursive(BigInt(n))
  def tailRecursive(n:BigInt):BigInt = {
    validate(n)
    if (n < 2) 1
    else {
      @tailrec def iterate(x:BigInt, result:BigInt):BigInt =
        if (x > n) result
        else iterate(x + 1, result * x)
      iterate(1, 1)
    }
  }

  def reductive(n:Int):BigInt = reductive(BigInt(n))
  def reductive(n:BigInt):BigInt = {
    validate(n)
    if (n < 2) 1
    else one to n reduceLeft(_ * _)
  }
}
