package uk.org.winder.maths;

import java.math.BigInteger;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static uk.org.winder.maths.Factorial.iterative;
import static uk.org.winder.maths.Factorial.reductive;
import static uk.org.winder.maths.Factorial.reductive_alt;
import static uk.org.winder.maths.Factorial.naiveRecursive;
import static uk.org.winder.maths.Factorial.tailRecursive;

final class Factorial_Jqwik {

  // Keep the Integer argument range relatively small so that the tests run in reasonable time.
  // [1, 500] is seen as reasonable.

  @Provide("1 to 500")
  Arbitrary<Long> positiveNumbers() {
    return Arbitraries.longs().between(1, 500);
  }

  @Provide("-500 to -1")
  Arbitrary<Long> negativeNumbers() {
    return Arbitraries.longs().between(-500, -1);
  }

  @Property
  void iterativePositive(@ForAll("1 to 500") final Long i) {
    assertEquals(iterative(i), BigInteger.valueOf(i).multiply(iterative(i - 1)));
  }

  @Property
  void iterativeNegative(@ForAll("-500 to -1") final Long i) {
    assertThrows(RuntimeException.class, () -> iterative(i));
  }

  @Property
  void reductivePositive(@ForAll("1 to 500") final Long i) {
    assertEquals(reductive(i), BigInteger.valueOf(i).multiply(reductive(i - 1)));
  }

  @Property
  void reductiveNegative(@ForAll("-500 to -1") final Long i) {
    assertThrows(RuntimeException.class, () -> reductive(i));
  }

  @Property
  void reductiveAltPositive(@ForAll("1 to 500") final Long i) {
	assertEquals(reductive_alt(i), BigInteger.valueOf(i).multiply(reductive_alt(i - 1)));
  }

  @Property
  void reductiveAltNegative(@ForAll("-500 to -1") final Long i) {
    assertThrows(RuntimeException.class, () -> reductive_alt(i));
  }

  @Property
  void naiveRecursivePositive(@ForAll("1 to 500") final Long i) {
    assertEquals(naiveRecursive(i), BigInteger.valueOf(i).multiply(naiveRecursive(i - 1)));
  }

  @Property
  void naiveRecursiveNegative(@ForAll("-500 to -1") final Long i) {
    assertThrows(RuntimeException.class, () -> naiveRecursive(i));
  }

  @Property
  void tailRecursivePositive(@ForAll("1 to 500") final Long i) {
    assertEquals(tailRecursive(i), BigInteger.valueOf(i).multiply(tailRecursive(i - 1)));
  }

  @Property
  void tailRecursiveNegative(@ForAll("-500 to -1") final Long i) {
    assertThrows(RuntimeException.class, () -> tailRecursive(i));
  }

}
