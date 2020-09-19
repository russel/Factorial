package uk.org.winder.maths;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.Property;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigInteger;

import static uk.org.winder.maths.Factorial.iterative;
import static uk.org.winder.maths.Factorial.reductive;
import static uk.org.winder.maths.Factorial.reductive_alt;
import static uk.org.winder.maths.Factorial.naiveRecursive;
import static uk.org.winder.maths.Factorial.tailRecursive;

@RunWith(JUnitQuickcheck.class)
public final class Test_Factorial_JUnitQuickCheck {

  // Keep the Integer argument range relatively small so that the tests run in reasonable time.
  // [1, 500] is seen as reasonable.

  @Property public void iterativePositive(@InRange(min = "1", max = "500") final Long i) {
    assertEquals(iterative(i), BigInteger.valueOf(i).multiply(iterative(i - 1)));
  }

  @Property public void iterativeNegative(@InRange(min = "-500", max = "-1") final Long i) {
    assertThrows(RuntimeException.class, () -> iterative(i));
  }

  @Property public void reductivePositive(@InRange(min="1", max="500") final Long i) {
    assertEquals(reductive(i), BigInteger.valueOf(i).multiply(reductive(i - 1)));
  }

  @Property public void reductiveNegative(@InRange(min="-500", max="-1") final Long i) {
    assertThrows(RuntimeException.class, () -> reductive(i));
  }

  @Property public void reductiveAltPositive(@InRange(min="1", max="500") final Long i) {
	assertEquals(reductive_alt(i), BigInteger.valueOf(i).multiply(reductive_alt(i - 1)));
  }

  @Property public void reductiveAltNegative(@InRange(min="-500", max="-1") final Long i) {
    assertThrows(RuntimeException.class, () -> reductive_alt(i));
  }

  @Property public void naiveRecursivePositive(@InRange(min="1", max="500") final Long i) {
    assertEquals(naiveRecursive(i), BigInteger.valueOf(i).multiply(naiveRecursive(i - 1)));
  }

  @Property public void naiveRecursiveNegative(@InRange(min="-500", max="-1") final Long i) {
    assertThrows(RuntimeException.class, () -> naiveRecursive(i));
  }

  @Property public void tailRecursivePositive(@InRange(min="1", max="500") final Long i) {
    assertEquals(tailRecursive(i), BigInteger.valueOf(i).multiply(tailRecursive(i - 1)));
  }

  @Property public void tailRecursiveNegative(@InRange(min="-500", max="-1") final Long i) {
    assertThrows(RuntimeException.class, () -> tailRecursive(i));
  }

}
