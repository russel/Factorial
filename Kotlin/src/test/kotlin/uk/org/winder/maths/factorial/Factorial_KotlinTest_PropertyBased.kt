package uk.org.winder.maths.factorial

import io.kotlintest.properties.PropertyTesting

class Factorial_KotlinTest_PropertyBased : PropertyTesting() {
  init {

    val algorithms = listOf(
        "iterative" to {x:Int -> iterative(x)},
        "reductive" to {x:Int -> reductive(x)},
        "naïve_recursive" to {x:Int -> naïve_recursive(x)},
        "tail_recursive" to {x:Int -> tail_recursive(x)}
    )

    forAll(algorithms) { a ->
      val f = a.second
      /*
      property(a.first + ":recurrence relation is true for all non-negative integer values").
          forAll{i: Int ->
            if (i > 0) { f(i) == (i.bigint * f(i - 1)) }
            else { true }
          }
    */
    }

  }
}
