package uk.org.winder.maths

import org.scalatest.PropSpec
import org.scalatest.prop.PropertyChecks

import org.scalacheck.Gen

class Factorial_ScalaTest_PropertyBased extends PropSpec with PropertyChecks {

  val algorithms = Table(
    ("algorithm", "name"),
    (Factorial.iterativeWhile _, "iterative"),
    (Factorial.iterativeFor _, "iterativeFor"),
    (Factorial.iterativeForeach _, "iterativeForeach"),
    (Factorial.productive _, "productive"),
    (Factorial.naïveRecursive _, "recursive"),
    (Factorial.tailRecursive _, "tailRecursive"),
    (Factorial.reductive _, "reductive"),
    (Factorial.foldLeftive _, "foldOperatorive")
  )

  forAll(algorithms) { (f: Function1[Int, BigInt], name: String) =>
    property("ForAll: Factorial using " + name + " obeys the recurrence relation for non-negative integers") {
      val positiveIntSample = for (n <- Gen.choose(0, 1000)) yield n
      forAll (positiveIntSample) { (n: Int) => assert(f(n + 1) == (n + 1) * f(n) ) }
    }
    property("Factorial using " + name + " throws an exception for negative integers") {
      forAll { (n:Int) => whenever (n < 0) { intercept [IllegalArgumentException] { f(n) } } }
    }
  }

}
