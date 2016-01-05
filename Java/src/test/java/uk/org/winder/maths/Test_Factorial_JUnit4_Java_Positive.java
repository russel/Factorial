package uk.org.winder.maths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.function.LongFunction;

@RunWith(Parameterized.class)
public final class Test_Factorial_JUnit4_Java_Positive {

  // Used in Factorial_JUnit4_Java_Negative as well.
  static final Object[][] algorithms = new Object[][] {
      {(LongFunction<BigInteger>)Factorial::iterative, "iterative"},
      {(LongFunction<BigInteger>)Factorial::naïveRecursive, "naïveRecursive"},
      {(LongFunction<BigInteger>) Factorial::tailRecursive, "tailRecursive"},
      {(LongFunction<BigInteger>)Factorial::reductive, "reductive"}
  };

  private static Object[][] values = new Object[][] {
      {0, BigInteger.valueOf(1)},
      {1, BigInteger.valueOf(1)},
      {2, BigInteger.valueOf(2)},
      {3, BigInteger.valueOf(6)},
      {4, BigInteger.valueOf(24)},
      {5, BigInteger.valueOf(120)},
      {6, BigInteger.valueOf(720)},
      {7, BigInteger.valueOf(5040)},
      {8, BigInteger.valueOf(40320)},
      {9, BigInteger.valueOf(362880)},
      {10, BigInteger.valueOf(3628800)},
      {11, BigInteger.valueOf(39916800)},
      {12, BigInteger.valueOf(479001600)},
      {13, new BigInteger("6227020800")},
      {14, new BigInteger("87178291200")},
      {20, new BigInteger("2432902008176640000")},
      {30, new BigInteger("265252859812191058636308480000000")},
      {40, new BigInteger("815915283247897734345611269596115894272000000000")}
  };

  @Parameters(name = "{1}({2})")
  public static final Iterable<Object[]> data() {
    final ArrayList<Object[]> data = new ArrayList<>();
    for (Object[] a: algorithms) {
      if (a.length != 2) { throw new RuntimeException("algorithms array borked."); }
      for (Object[] v: values) {
        if (v.length != 2) { throw new RuntimeException("values array borked."); }
        data.add(new Object[] {a[0], a[1], v[0], v[1]});
      }
    }
    return data;
  }

  private final LongFunction<BigInteger> a;
  private final String name;
  private final Integer n;
  private final BigInteger r;

  public Test_Factorial_JUnit4_Java_Positive(final LongFunction<BigInteger> a, final String name, final Integer n, final BigInteger r) {
    this.a = a;
    this.name = name;
    this.n = n;
    this.r = r;
  }

  @Test
  public void test() {
    assertEquals(a.apply(n), r);
  }

}
