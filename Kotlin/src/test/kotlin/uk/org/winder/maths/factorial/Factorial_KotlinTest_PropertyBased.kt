package uk.org.winder.maths.factorial

import io.kotlintest.specs.StringSpec
import io.kotlintest.KTestJUnitRunner
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.properties.forAll

import org.junit.runner.RunWith

@RunWith(KTestJUnitRunner::class)
class Factorial_KotlinTest_PropertyBased : StringSpec({

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
          if (i in 0..500) { f(i) == (i.bigint * f(i - 1)) }
          else { true }
        }
      }

      (name + ": negative argument cause an exception") {
        forAll{i:Int ->
          if (i < 0) { shouldThrow<IllegalArgumentException> { f(i) } }
          true
        }
      }

    }

})
