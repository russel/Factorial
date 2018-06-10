using NUnit.Framework;
//using Mono.Math;
using System.Numerics;

[TestFixture]
public class Factorial_BigInteger_Test {
    class DataPair {
        public ulong i;
        public BigInteger expected;
        public DataPair(ulong i , BigInteger expected) { this.i = i; this.expected = expected; }
    };

    private DataPair[] data = {
        new DataPair(0 , new BigInteger(1)) ,
        new DataPair(1 , new BigInteger(1)) ,
        new DataPair(2 , new BigInteger(2)) ,
        new DataPair(3 , new BigInteger(6)) ,
        new DataPair(4 , new BigInteger(24)) ,
        new DataPair(5 , new BigInteger(120)) ,
        new DataPair(6 , new BigInteger(720)) ,
        new DataPair(7 , new BigInteger(5040)) ,
        new DataPair(8 , new BigInteger(40320)) ,
        new DataPair(9 , new BigInteger(362880)) ,
        new DataPair(10 , new BigInteger(3628800)) ,
        new DataPair(11 , new BigInteger(39916800)) ,
        new DataPair(12 , new BigInteger(479001600)) ,
        new DataPair(13 , new BigInteger(6227020800)) ,
        new DataPair(14 , new BigInteger(87178291200)) ,
        new DataPair(20 , new BigInteger(2432902008176640000)) ,
        //new DataPair(30 , new BigInteger(265252859812191058636308480000000)) ,
        //new DataPair(40 , new BigInteger(815915283247897734345611269596115894272000000000)) ,
    };

    [Test]
    public void correctIterative() {
        for (int i = 0; i < data.Length; ++i) { Assert.AreEqual(data[i].expected , Factorial_BigInteger.iterative(data[i].i)); }
    }

    [Test]
    public void correctRecursive() {
        for (int i = 0; i < data.Length; ++i) { Assert.AreEqual(data[i].expected , Factorial_BigInteger.recursive(data[i].i)); }
    }

    //[Test]
    public void negativeIterative() {
        for (int i = -20; i < 0; ++i) { Factorial_BigInteger.iterative((ulong) i); }
    }

    //[Test]
    public void negativeRecursive() {
        for (int i = -20; i < 0; ++i) { Factorial_BigInteger.recursive((ulong) i); }
    }

    //  Dummy main to keep the compiler from issuing an error.
    public static int Main(string[] args) { return 0; }
}
