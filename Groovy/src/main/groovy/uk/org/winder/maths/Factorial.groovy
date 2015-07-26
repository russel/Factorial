package uk.org.winder.maths

import groovy.transform.CompileStatic
import groovy.transform.TailRecursive
import groovy.transform.TypeCheckingMode

@CompileStatic
class Factorial {

  private static void validateParameter(final BigInteger n) {
    if (n < 0G) throw new IllegalArgumentException('Argument must be non-negative.')
  }

  //------------------------------------------------------------------------------------------------------------------------

  static BigInteger iterative(final BigInteger n) {
    validateParameter(n)
    BigInteger total = 1G
    if (n > 1G) { (2G..n).each{BigInteger i -> total *= i} }
    total
  }
  static BigInteger iterative(final Integer n) { iterative(n as BigInteger) }
  static BigInteger iterative(final Long n) { iterative(n as BigInteger) }

  //------------------------------------------------------------------------------------------------------------------------

  static BigInteger reductive(final BigInteger n) {
    validateParameter(n)
    (BigInteger)(n < 2G ? 1G : (2G .. n).inject(1G){BigInteger t, BigInteger i -> t * i})
  }
  static BigInteger reductive(final Integer n) { reductive(n as BigInteger) }
  static BigInteger reductive(final Long n) { reductive(n as BigInteger) }

  //------------------------------------------------------------------------------------------------------------------------

  static BigInteger recursive(final BigInteger n) {
    validateParameter(n)
    n < 2G ? 1G : n * recursive(n - 1G)
  }
  static BigInteger recursive(Integer n) { recursive(n as BigInteger) }
  static BigInteger recursive(Long n) { recursive(n as BigInteger) }

  //------------------------------------------------------------------------------------------------------------------------

  @TailRecursive
  static BigInteger iterate(final BigInteger i, final BigInteger result) {
    i < 2G ? result : iterate(i - 1G, result * i)
  }
  static BigInteger tailRecursiveFunction(BigInteger n) {
    validateParameter(n)
    iterate(n, 1G)
  }
  static BigInteger tailRecursiveFunction(Integer n) { tailRecursiveFunction(n as BigInteger) }
  static BigInteger tailRecursiveFunction(Long n) { tailRecursiveFunction(n as BigInteger) }

  //------------------------------------------------------------------------------------------------------------------------

  // Cannot get tail recursion optimization using closures.

  static BigInteger tailRecursiveClosure(BigInteger n) {
    validateParameter(n)
    /*final*/ Closure<BigInteger> iterate
    iterate = {BigInteger i, BigInteger result -> i < 2G ? result : iterate(i - 1G, result * i)}
    iterate(n, 1G)
  }
  static BigInteger tailRecursiveClosure(Integer n) { tailRecursiveClosure(n as BigInteger) }
  static BigInteger tailRecursiveClosure(Long n) { tailRecursiveClosure(n as BigInteger) }

  //------------------------------------------------------------------------------------------------------------------------

  // Trampolines returns a Serializable which is not compatible with a Closure so we cannot get compile time
  // type checking on this one :-(
  @CompileStatic(TypeCheckingMode.SKIP)
  static BigInteger tailRecursiveTrampoline(BigInteger n) {
    validateParameter(n)
    /*final*/ Closure<BigInteger> iterate
    iterate = {BigInteger i, BigInteger result -> i < 2G ? result : iterate.trampoline(i - 1G, result * i)}.trampoline()
    (BigInteger)iterate(n, 1G)
  }
  static BigInteger tailRecursiveTrampoline(Integer n) { tailRecursiveTrampoline(n as BigInteger) }
  static BigInteger tailRecursiveTrampoline(Long n) { tailRecursiveTrampoline(n as BigInteger) }

  //------------------------------------------------------------------------------------------------------------------------

  // This version based on the "continuations" idea by Jochen Theoderou
  // http://groovy.329449.n5.nabble.com/Why-fails-tail-recursion-without-trampoline-method-tt4791852.html#a4792328

  @CompileStatic(TypeCheckingMode.SKIP)
  static BigInteger continuation(final BigInteger n) {
    validateParameter(n)
    final executeTillResultReturned = {x -> while (x instanceof Closure) { x = x() }; x}
    // factorial.curry returns Closure<Serializable> which is not compatible with Closure<BigInteger> so we
    // cannot do compile time type checking with generics just raw types on this one.
    /*final*/ Closure factorial
    factorial = {BigInteger i, BigInteger result = 1G -> i < 2G ? result : factorial.curry(i - 1G, i * result)}
    (BigInteger)executeTillResultReturned(factorial.curry(n))
  }
  static BigInteger continuation(final Integer n) { continuation(n as BigInteger) }
  static BigInteger continuation(final Long n) { continuation(n as BigInteger) }
}
