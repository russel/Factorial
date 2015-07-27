package uk.org.winder.maths

import org.scalatest.PropSpec
import org.scalatest.prop.PropertyChecks
import org.scalatest.Matchers

import org.scalacheck.Gen

class Factorial_ScalaTest_PropertyChecks extends PropSpec with PropertyChecks with Matchers {

  val algorithms = Table(
    ("algorithm", "name"),
    (Factorial.iterative _, "iterative"),
    (Factorial.naïveRecursive _, "recursive"),
    (Factorial.tailRecursive _, "tailRecursive"),
    (Factorial.reductive _, "reductive")
  )

  forAll(algorithms) { (f: Function1[Int, BigInt], name: String) =>
    property("ForAll: Factorial using " + name + " obeys the recurrence relation for non-negative integers") {
      val positiveIntSample = for (n <- Gen.choose(0, 1000)) yield n
      forAll (positiveIntSample) { (n: Int) => f(n + 1) should equal ( (n + 1) * f(n) ) }
    }
    property("Factorial using " + name + " throws an exception for negative integers") {
      forAll { (n:Int) => whenever (n < 0) { an [IllegalArgumentException] should be thrownBy { f(n) } } }
    }
  }

}
