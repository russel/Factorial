package uk.org.winder.maths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.function.LongFunction;
import java.math.BigInteger;

import static uk.org.winder.maths.Test_Factorial_JUnit4_Java_Positive.algorithms;

@RunWith(Parameterized.class)
public final class Test_Factorial_JUnit4_Java_Negative {

  private static final Object[] values = {-1, -2, -5, -10, -20, -100};

  @Parameters(name = "{1}({2})")
  public static final Iterable<Object[]> data() {
    final ArrayList<Object[]> data = new ArrayList<>();
       for (Object[] a: algorithms) {
         if (a.length != 2) { throw new RuntimeException("algorithms array borked."); }
         for (Object v: values) {
           data.add(new Object[] {a[0], a[1], v});
         }
       }
       return data;
  }

  private final LongFunction<BigInteger> a;
  private final String name;
  private final Integer n;

  public Test_Factorial_JUnit4_Java_Negative(final LongFunction<BigInteger> a, final String name, final Integer n) {
    this.a = a;
    this.name = name;
    this.n = n;
  }

  @Test(expected=RuntimeException.class)
  public void test() {
    a.apply(n);
  }

}
