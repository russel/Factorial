package uk.org.winder.maths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import static uk.org.winder.maths.Factorial.iterative;
import static uk.org.winder.maths.Factorial.naïveRecursive;
import static uk.org.winder.maths.Factorial.tailRecursive;
import static uk.org.winder.maths.Factorial.reductive;

@RunWith(Parameterized.class)
public class Factorial_JUnit4_Java_Negative {

  @Parameters
  public static Collection data() {
    return Arrays.asList(new Object[][] {
        {-1},
        {-2},
        {-5},
        {-10},
        {-20},
        {-100},
      });
  }

  private final Integer n;

  public Factorial_JUnit4_Java_Negative(final Integer n) {
    this.n = n;
  }

  @Test(expected=RuntimeException.class)
  public void test_iterative() {
    iterative(n);
  }

  @Test(expected=RuntimeException.class)
  public void test_naïveRecursive() {
    naïveRecursive(n);
  }

  @Test(expected=RuntimeException.class)
  public void test_tailRecursive() {
    tailRecursive(n);
  }

  @Test(expected=RuntimeException.class)
  public void test_reductive() {
    reductive(n);
  }

}
