package uk.org.winder.maths;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.Property;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

import java.math.BigInteger;

import static uk.org.winder.maths.Factorial.iterative;
import static uk.org.winder.maths.Factorial.naïveRecursive;
import static uk.org.winder.maths.Factorial.tailRecursive;
import static uk.org.winder.maths.Factorial.reductive;


@RunWith(JUnitQuickcheck.class)
public final class Test_Factorial_JUnit4_QuickCheck_Java {

  // Keep the Integer argument range relatively small so that the tests run in reasonable time.
  // [1, 500] is seen as reasonable.

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Property public void iterative_positive(@InRange(min = "1", max = "500") final Integer i) {
    assertEquals(iterative(i), BigInteger.valueOf(i).multiply(iterative(i - 1)));
  }

  @Property public void iterative_negative(@InRange(min = "-500", max = "-1") final Integer i) {
    thrown.expect(RuntimeException.class);
    iterative(i);
  }

  @Property public void naïveRecursive_positive(@InRange(min="1", max="500") final Integer i) {
    assertEquals(naïveRecursive(i), BigInteger.valueOf(i).multiply(naïveRecursive(i - 1)));
  }

  @Property public void naïveRecursive_negative(@InRange(min="-500", max="-1") final Integer i) {
    thrown.expect(RuntimeException.class);
    naïveRecursive(i);
  }

  @Property public void tailRecursive_positive(@InRange(min="1", max="500") final Integer i) {
    assertEquals(tailRecursive(i), BigInteger.valueOf(i).multiply(tailRecursive(i - 1)));
  }

  @Property public void tailRecursive_negative(@InRange(min="-500", max="-1") final Integer i) {
    thrown.expect(RuntimeException.class);
    tailRecursive(i);
  }

  @Property public void reductive_positive(@InRange(min="1", max="500") final Integer i) {
    assertEquals(reductive(i), BigInteger.valueOf(i).multiply(reductive(i - 1)));
  }

  @Property public void reductive_negative(@InRange(min="-500", max="-1") final Integer i) {
    thrown.expect(RuntimeException.class);
    reductive(i);
  }

}
