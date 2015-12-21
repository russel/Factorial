package uk.org.winder.maths;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.pholser.junit.quickcheck.Property;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import static uk.org.winder.maths.Factorial.iterative;
import static uk.org.winder.maths.Factorial.naïveRecursive;
import static uk.org.winder.maths.Factorial.tailRecursive;
import static uk.org.winder.maths.Factorial.reductive;


@RunWith(JUnitQuickcheck.class)
public class Factorial_QuickCheck_Java {

  // Use byte as a way of restricting the samples to the range [-128, 127].

  @Property public void iterative_positive(final Byte i) {
    if (i > 0) {
      assertEquals(iterative(i), BigInteger.valueOf(i).multiply(iterative(i - 1)));
    }
  }

  @Property public void reductive_positive(final Byte i) {
    if (i > 0) {
      assertEquals(reductive(i), BigInteger.valueOf(i).multiply(reductive(i - 1)));
    }
  }

  /*
   * naïveRecursion will generally run out of stack space for even moderate size arguments.
   * Do not test it.

  @Property public void naïveRecursive_positive(final Integer i) {
    if (i > 0) {
      assertEquals(naïveRecursive(i), BigInteger.valueOf(i).multiply(naïveRecursive(i - 1)));
    }
  }
  */

  /*
   * Java does not support tail call optimization (at least not as of Java 8) so will suffer
   * the same stack problems as naïveRecursive. Do not test it.

  @Property public void tailRecursive_positive(final Integer i) {
    if (i > 0) {
      assertEquals(tailRecursive(i), BigInteger.valueOf(i).multiply(tailRecursive(i - 1)));
    }
  }
  */

}
