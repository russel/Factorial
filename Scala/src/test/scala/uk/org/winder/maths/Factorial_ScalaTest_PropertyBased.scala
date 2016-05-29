package uk.org.winder.maths

import org.scalatest.PropSpec
import org.scalatest.prop.PropertyChecks

import org.scalacheck.Gen

class Factorial_ScalaTest_PropertyBased extends PropSpec with PropertyChecks {

  val algorithms = Table(
    ("algorithm", "name"),
    (Factorial.iterativeWhile _, "iterativeWhile"),
    (Factorial.iterativeFor _, "iterativeFor"),
    (Factorial.iterativeForeach _, "iterativeForeach"),
    (Factorial.productive _, "productive"),
    (Factorial.reductive _, "reductive"),
    (Factorial.foldLeftive _, "foldLeftive"),
    (Factorial.naïveRecursive _, "naïveRecursive"),
    (Factorial.tailRecursive _, "tailRecursive")
  )

  forAll(algorithms) { (f: Function1[Int, BigInt], name: String) =>
    property(name + " obeys the recurrence relation for non-negative integers") {
      val positiveIntSample = for (n <- Gen.choose(0, 1000)) yield n
      forAll (positiveIntSample) { (n: Int) => assert(f(n + 1) == (n + 1) * f(n) ) }
    }
    property(name + " throws IllegalArgumentException for negative argument") {
      forAll { (n:Int) => whenever (n < 0) { intercept [IllegalArgumentException] { f(n) } } }
    }
  }

}
