package uk.org.winder.maths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static uk.org.winder.maths.Factorial.iterative;
import static uk.org.winder.maths.Factorial.naïveRecursive;
import static uk.org.winder.maths.Factorial.tailRecursive;
import static uk.org.winder.maths.Factorial.reductive;

@RunWith(Parameterized.class)
public class Factorial_JUnit4_Java_Positive {

  @Parameters
  public static Collection data() {
    return Arrays.asList(new Object[][] {
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
      });
  }

  private final Integer n;
  private final BigInteger r;

  public Factorial_JUnit4_Java_Positive(final Integer n, final BigInteger r) {
    this.n = n;
    this.r = r;
  }

  @Test
  public void test_iterative() {
    assertEquals(iterative(n), r);
  }

  @Test
  public void test_naïveRecursive() {
    assertEquals(naïveRecursive(n), r);
  }

  @Test
  public void test_tailRecursive() {
    assertEquals(tailRecursive(n), r);
  }

  @Test
  public void test_reductive() {
    assertEquals(reductive(n), r);
  }

}
