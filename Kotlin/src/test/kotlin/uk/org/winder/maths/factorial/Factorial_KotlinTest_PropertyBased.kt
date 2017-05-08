package uk.org.winder.maths.factorial

import io.kotlintest.specs.StringSpec
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.properties.table
import io.kotlintest.properties.headers
import io.kotlintest.properties.row

val random = java.util.Random()

val smallishWholeNumbers = object: Gen<Int> {
    override fun generate() = random.nextInt(700)
}

class Factorial_KotlinTest_PropertyBased : StringSpec({

    val algorithms = table(
        headers("name", "f"),
        row("iterative", {x:Int -> iterative(x)}),
        row("reductive", {x:Int -> reductive(x)}),
        row("naïve_recursive", {x:Int -> naïve_recursive(x)}),
        row("tail_recursive", {x:Int -> tail_recursive(x)})
    )

    forAll(algorithms){name, f ->

      "$name: base case holds."  {
          f(0) == 1.bigint
      }

      "$name: recurrence relation is true for non-negative integer values" {
        forAll(smallishWholeNumbers){i ->
          f(i + 1) == (i + 1).bigint * f(i)
        }
      }

      "$name: negative argument cause an exception" {
        forAll(Gen.negativeIntegers()){i:Int ->
          shouldThrow<IllegalArgumentException> { f(i) }
          true
        }
      }

    }

})
