//using Mono.Math;
using System.Numerics;

public class Factorial_BigInteger {
    public static BigInteger iterative(BigInteger n) {
        BigInteger total = 1;
        if (n <= 1) { return total; }
        for (BigInteger i = 2; i <= n; i = BigInteger.Add(i, 1)) { total = BigInteger.Multiply(total, i); }
        return total;
    }

    public static BigInteger iterative(ulong n) { return iterative (new BigInteger(n)); }

    public static BigInteger recursive(BigInteger n) {
        return n <= 1 ? 1 : n * recursive(BigInteger.Subtract(n, 1));
    }

    public static BigInteger recursive(ulong n) { return recursive(new BigInteger(n)); }
}
