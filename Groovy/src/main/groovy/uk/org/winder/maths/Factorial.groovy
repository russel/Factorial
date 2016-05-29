package uk.org.winder.maths

import groovy.transform.CompileStatic
import groovy.transform.TailRecursive

@CompileStatic
class Factorial {

  private static void validateParameter(final BigInteger n) {
    if (n < 0G) throw new IllegalArgumentException('Argument must be non-negative.')
  }

  static BigInteger iterative(final BigInteger n) {
    validateParameter(n)
    BigInteger total = 1G
    if (n > 1G) {
      (2G..n).each { BigInteger i -> total *= i }
    }
    total
  }
  static BigInteger iterative(final Integer n) { iterative(n as BigInteger) }
  static BigInteger iterative(final Long n) { iterative(n as BigInteger) }

  static BigInteger reductive(final BigInteger n) {
    validateParameter(n)
    (BigInteger) (n < 2G ? 1G : (2G..n).inject(1G) { BigInteger t, BigInteger i -> t * i })
  }
  static BigInteger reductive(final Integer n) { reductive(n as BigInteger) }
  static BigInteger reductive(final Long n) { reductive(n as BigInteger) }

  static BigInteger naïveRecursive(final BigInteger n) {
    validateParameter(n)
    n < 2G ? 1G : n * naïveRecursive(n - 1G)
  }
  static BigInteger naïveRecursive(Integer n) { naïveRecursive(n as BigInteger) }
  static BigInteger naïveRecursive(Long n) { naïveRecursive(n as BigInteger) }

  @TailRecursive
  static private BigInteger iterate(final BigInteger i, final BigInteger result) {
    i < 2G ? result : iterate(i - 1G, result * i)
  }

  static BigInteger tailRecursive(BigInteger n) {
    validateParameter(n)
    iterate(n, 1G)
  }
  static BigInteger tailRecursive(Integer n) { tailRecursive(n as BigInteger) }
  static BigInteger tailRecursive(Long n) { tailRecursive(n as BigInteger) }

}