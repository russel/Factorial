package uk.org.winder.maths;

import java.math.BigInteger;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public final class Factorial {

	private static void validate(final BigInteger n) {
		if (n.compareTo(BigInteger.ZERO) < 0) {
			throw new IllegalArgumentException("Parameter must be a non-negative integer.");
		}
	}

	public static BigInteger iterative(final BigInteger n) {
		validate(n);
		BigInteger total = BigInteger.ONE;
		if (n.compareTo(BigInteger.ONE) > 0) {
			BigInteger i = BigInteger.ONE;
			while (i.compareTo(n) <= 0) {
				total = total.multiply(i);
				i = i.add(BigInteger.ONE);
			}
		}
		return total;
	}
	public static BigInteger iterative(final Integer n) { return iterative(BigInteger.valueOf(n)); }
	public static BigInteger iterative(final Long n) { return iterative(BigInteger.valueOf(n)); }

	public static BigInteger reductive(final BigInteger n) {
		// There is a hidden problem here, we are implicitly restricting the argument to
		// Long values since the limit function cannot take a BigInteger.
		validate(n);
		return n.compareTo(BigInteger.ONE) <= 0 ?
				BigInteger.ONE :
				Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE)).limit(n.longValue()).reduce(BigInteger::multiply).get();
	}
	public static BigInteger reductive(final Integer n) { return reductive(BigInteger.valueOf(n)); }
	public static BigInteger reductive(final Long n) { return reductive(BigInteger.valueOf(n)); }

	public static BigInteger reductive_alt(final Long n) {
		// This version based on the idea proposed by an attender of the DevoxxUK 2017
		// workshop on "Declarative".
		validate(BigInteger.valueOf(n));
		return n <= 1 ? BigInteger.ONE : LongStream.range(2, n + 1).mapToObj(BigInteger::valueOf).reduce(BigInteger::multiply).get();
	}
	public static BigInteger reductive_alt(final Integer n) { return reductive(Long.valueOf(n)); }
	public static BigInteger reductive_alt(final BigInteger n) { return reductive(n.longValue()); }

	public static BigInteger naïveRecursive(final BigInteger n) {
		validate(n);
		return n.compareTo(BigInteger.ONE) <= 0 ? BigInteger.ONE : n.multiply(naïveRecursive(n.subtract(BigInteger.ONE)));
	}
	public static BigInteger naïveRecursive(final Integer n) { return naïveRecursive(BigInteger.valueOf(n)); }
	public static BigInteger naïveRecursive(final Long n) { return naïveRecursive(BigInteger.valueOf(n)); }

	private static BigInteger iterate(final BigInteger n, final BigInteger result) {
		return n.compareTo(BigInteger.ONE) <= 0 ? result : iterate(n.subtract(BigInteger.ONE), result.multiply(n));
	}

	public static BigInteger tailRecursive(final BigInteger n) {
		validate(n);
		return n.compareTo(BigInteger.ONE) <= 0 ? BigInteger.ONE : iterate(n, BigInteger.ONE);
	}
	public static BigInteger tailRecursive(final Integer n) { return tailRecursive(BigInteger.valueOf(n)); }
	public static BigInteger tailRecursive(final Long n) { return tailRecursive(BigInteger.valueOf(n)); }

}
