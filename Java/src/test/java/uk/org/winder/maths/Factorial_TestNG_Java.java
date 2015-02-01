package uk.org.winder.maths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.function.LongFunction;

public class Factorial_TestNG_Java {

  private final Object[] algorithms = new Object[] {
      (LongFunction<BigInteger>)Factorial::iterative,
      (LongFunction<BigInteger>)Factorial::recursive,
      (LongFunction<BigInteger>)Factorial::tailRecursive,
      (LongFunction<BigInteger>)Factorial::reductive
  };

  private final Object[][] positiveData = new Object[][] {
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

  private final Object[] negativeData = new Object[] { -1, -2, -5, -10, -20, -100};

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
    return values.toArray(new Object[3][0]);
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
  public void recursiveEnormousFails() { Factorial.recursive(13000); }

  @Test(expectedExceptions = {StackOverflowError.class})
  public void tailRecursiveEnormousFails() { Factorial.tailRecursive(26000); }
}
