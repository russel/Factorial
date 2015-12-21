package uk.org.winder.maths;

import java.math.BigInteger;
import java.util.stream.Stream;

public final class Factorial {

  private static void validate(final BigInteger n) {
    if (n.compareTo(BigInteger.ZERO) < 0) { throw new IllegalArgumentException("Parameter must be a non-negative integer."); }
  }

  public static BigInteger iterative(final BigInteger n) {
    validate(n);
    BigInteger total = BigInteger.ONE;
    if (n.compareTo(BigInteger.ONE)  > 0) {
      BigInteger i = BigInteger.ONE;
      while (i.compareTo(n) <= 0) {
        total = total.multiply(i);
        i = i.add(BigInteger.ONE);
      }
    }
    return total;
  }
  public static BigInteger iterative(final long n) { return iterative(new BigInteger(Long.toString(n))); }

  public static BigInteger naïveRecursive(final BigInteger n) {
    validate(n);
    return n.compareTo(BigInteger.ONE) <= 0 ? BigInteger.ONE : n.multiply(naïveRecursive(n.subtract(BigInteger.ONE)));
  }
  public static BigInteger naïveRecursive(final long n) { return naïveRecursive(new BigInteger(Long.toString(n))); }

  private static BigInteger iterate(final BigInteger n , final BigInteger result) {
    return n.compareTo(BigInteger.ONE) <= 0 ? result : iterate(n.subtract(BigInteger.ONE) , result.multiply(n));
  }

  public static BigInteger tailRecursive(final BigInteger n) {
    validate(n);
    return n.compareTo(BigInteger.ONE) <= 0 ? BigInteger.ONE : iterate(n , BigInteger.ONE);
  }
  public static BigInteger tailRecursive(final long n) { return tailRecursive(BigInteger.valueOf(n)); }

  public static BigInteger reductive(final BigInteger n) {
    validate(n);
    return n.compareTo(BigInteger.ONE) <= 0 ?
        BigInteger.ONE :
        Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE)).limit(n.longValue()).reduce(BigInteger::multiply).get();
  }
  public static BigInteger reductive(final long n) { return reductive(BigInteger.valueOf(n)); }
}
