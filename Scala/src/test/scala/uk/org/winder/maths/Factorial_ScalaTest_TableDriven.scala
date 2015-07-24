package uk.org.winder.maths

import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks

class Factorial_ScalaTest_TableDriven extends FunSuite with Matchers with TableDrivenPropertyChecks {
  val algorithms = Table(
    ("algorithm", "name"),
    (Factorial.iterative _, "iterative"),
    (Factorial.naïveRecursive _, "recursive"),
    (Factorial.tailRecursive _, "tailRecursive"),
    (Factorial.reductive _, "reductive")
  )

  val positiveData = Table(
    ( "n", "f"),
    (0, BigInt(1)),
    (1, BigInt(1)),
    (2, BigInt(2)),
    (3, BigInt(6)),
    (4, BigInt(24)),
    (5, BigInt(120)),
    (6, BigInt(720)),
    (7, BigInt(5040)),
    (8, BigInt(40320)),
    (9, BigInt(362880)),
    (10, BigInt(3628800)),
    (11, BigInt(39916800)),
    (12, BigInt(479001600)),
    (13, BigInt("6227020800")),
    (14, BigInt("87178291200")),
    (20, BigInt("2432902008176640000")),
    (30, BigInt("265252859812191058636308480000000")),
    (40, BigInt("815915283247897734345611269596115894272000000000"))
  )

  val negativeData = Table("n", -1, -2, -5, -10, -20, -100)

  forAll (algorithms) {(algorithm:Function[Int, BigInt], name:String) =>
    forAll (positiveData) {(n:Int, f:BigInt) =>
      test(name + " " + n) { algorithm(n) should equal (f) }
    }
    forAll (negativeData) {(n:Int) =>
      test(name + " " + n) { an [IllegalArgumentException] should be thrownBy { algorithm(n) } }
    }
  }

  test("iterative 26000") { Factorial.iterative(26000) }
  test("reductive 26000") { Factorial.reductive(26000) }
  test("recursive 14000") { an [StackOverflowError] should be thrownBy { Factorial.naïveRecursive(14000) } }
  test("tailRecursive 26000") { Factorial.tailRecursive(26000) }

}
