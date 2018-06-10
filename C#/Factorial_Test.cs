using NUnit.Framework;

//  No data providers as TestNG has.

[TestFixture]
public class Factorial_Test {
    private ulong[,] data = {
        {0, 1},
        {1, 1},
        {2, 2},
        {3, 6},
        {4, 24},
        {5, 120},
        {6, 720},
        {7, 5040},
        {8, 40320},
        {9, 362880},
        {10, 3628800},
        {11, 39916800},
        {12, 479001600},
        {13, 6227020800L},
        {14, 87178291200L},
        {20, 2432902008176640000L},
        //{30, 265252859812191058636308480000000L},
        //{40, 815915283247897734345611269596115894272000000000L}
    };

    private const int dataCount = 16;

    [Test]
    public void correctIterative() {
        for (int i = 0; i < dataCount; ++i) { Assert.AreEqual(data[i, 1], Factorial.iterative(data[i, 0])); }
    }

    [Test]
    public void correctRecursive() {
        for (int i = 0; i < dataCount; ++i) { Assert.AreEqual(data[i, 1], Factorial.recursive(data[i, 0])); }
    }

    [Test]
    public void correctTailRecursive() {
        for (int i = 0; i < dataCount; ++i) { Assert.AreEqual(data[i, 1], Factorial.tailRecursive(data[i, 0])); }
    }

    //  Negative values are just very big numbers when cast to a ulong, so testing is not sensible.

    //[Test]
    public void negativeIterative() {
        for (int i = -20; i < 0; ++i) { Factorial.iterative ((ulong)i); }
    }

    //[Test]
    public void negativeRecursive ( ) {
        for (int i = -20; i < 0; ++i) { Factorial.recursive ((ulong)i); }
    }

    //[ Test ]
    public void negativeTailRecursive ( ) {
        for (int i = -20; i < 0; ++i) { Factorial.tailRecursive((ulong)i); }
    }

    //  Dummy main to keep the compiler from issuing an error.
    public static int Main(string[] args) { return 0; }
}
