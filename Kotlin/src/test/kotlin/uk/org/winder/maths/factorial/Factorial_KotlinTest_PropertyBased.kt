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
      val name = a.first
      val f = a.second

      (name + ": recurrence relation is true for all non-negative integer values") {
        forAll{i: Int ->
          if (0 < i && i < 500) { f(i) == (i.bigint * f(i - 1)) }
          else { true }
        }
      }

      (name + ": negative argument cause an exception") {
        // See https://github.com/kotlintest/kotlintest/issues/70
        listOf(-100, -20, -2, -1).forEach {
          shouldThrow<IllegalArgumentException> { f(it) }
        }
/*
        forAll{i:Int ->
          if (i < 0) { shouldThrow<IllegalArgumentException> { f(i) } }
          else { true }
        }
*/
      }

    }

  }
}
