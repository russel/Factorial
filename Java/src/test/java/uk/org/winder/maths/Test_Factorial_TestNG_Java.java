package uk.org.winder.maths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.function.LongFunction;

public final class Test_Factorial_TestNG_Java {

  private final Object[] algorithms = new Object[] {
      (LongFunction<BigInteger>)Factorial::iterative,
      (LongFunction<BigInteger>)Factorial::naïveRecursive,
      (LongFunction<BigInteger>)Factorial::tailRecursive,
      (LongFunction<BigInteger>)Factorial::reductive
  };

  private final Object[][] positiveData = new Object[][] {
      {0L, BigInteger.valueOf(1)},
      {1L, BigInteger.valueOf(1)},
      {2L, BigInteger.valueOf(2)},
      {3L, BigInteger.valueOf(6)},
      {4L, BigInteger.valueOf(24)},
      {5L, BigInteger.valueOf(120)},
      {6L, BigInteger.valueOf(720)},
      {7L, BigInteger.valueOf(5040)},
      {8L, BigInteger.valueOf(40320)},
      {9L, BigInteger.valueOf(362880)},
      {10L, BigInteger.valueOf(3628800)},
      {11L, BigInteger.valueOf(39916800)},
      {12L, BigInteger.valueOf(479001600)},
      {13L, new BigInteger("6227020800")},
      {14L, new BigInteger("87178291200")},
      {20L, new BigInteger("2432902008176640000")},
      {30L, new BigInteger("265252859812191058636308480000000")},
      {40L, new BigInteger("815915283247897734345611269596115894272000000000")}
  };

  private final Object[] negativeData = new Object[] { -1L, -2L, -5L, -10L, -20L, -100L};

  @DataProvider
  private Object[][] algorithmsAndPositiveData() {
    final ArrayList<Object[]> values = new ArrayList<>();
       for (Object a: algorithms) {
         for (Object[] v: positiveData) {
           if (v.length != 2) { throw new RuntimeException("positiveData array borked."); }
           values.add(new Object[] {a, v[0], v[1]});
         }
       }
       return values.toArray(new Object[3][0]);
  }

  @DataProvider
  private Object[][] algorithmsAndNegativeData() {
    final ArrayList<Object[]> values = new ArrayList<>();
    for (Object a: algorithms) {
      for (Object item: negativeData) {
        values.add(new Object[] {a, item});
      }
    }
    return values.toArray(new Object[2][0]);
  }

  @Test(dataProvider = "algorithmsAndPositiveData")
  public void positiveArgumentShouldWork(final LongFunction<BigInteger> f, final long n, final BigInteger expected) {
    assertEquals(f.apply(n), expected);
  }

  @Test(dataProvider = "algorithmsAndNegativeData", expectedExceptions = {IllegalArgumentException.class})
  public void negativeArgumentShouldThrowException(final LongFunction<BigInteger> f, final long n) { f.apply(n); }

  @Test
  public void iterativeEnormousSucceeds() { Factorial.iterative(26000); }

  @Test
  public void reductiveEnormousSucceeds() { Factorial.reductive(26000); }

  @Test(expectedExceptions = {StackOverflowError.class})
  public void recursiveEnormousFails() { Factorial.naïveRecursive(13000); }

  @Test(expectedExceptions = {StackOverflowError.class})
  public void tailRecursiveEnormousFails() { Factorial.tailRecursive(26000); }
}
