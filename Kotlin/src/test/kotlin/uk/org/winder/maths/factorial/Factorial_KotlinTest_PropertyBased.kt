package uk.org.winder.maths.factorial

import io.kotlintest.specs.StringSpec

class Factorial_KotlinTest_PropertyBased : StringSpec() {
  init {

    val algorithms = listOf(
        "iterative" to {x:Int -> iterative(x)},
        "reductive" to {x:Int -> reductive(x)},
        "naïve_recursive" to {x:Int -> naïve_recursive(x)},
        "tail_recursive" to {x:Int -> tail_recursive(x)}
    )

    algorithms.forEach{a ->
      val f = a.second
      (a.first + ":recurrence relation is true for all non-negative integer values") {
        forAll{i: Int ->
          if (0 < i && i < 500) { f(i) == (i.bigint * f(i - 1)) }
          else { true }
        }
      }
    }

  }
}
