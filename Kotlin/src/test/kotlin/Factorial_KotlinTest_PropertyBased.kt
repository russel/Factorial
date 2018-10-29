package uk.org.winder.maths.factorial

import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import io.kotlintest.properties.Gen
import io.kotlintest.properties.verifyAll
import io.kotlintest.tables.forAll
import io.kotlintest.tables.table
import io.kotlintest.tables.headers
import io.kotlintest.tables.row

val random = java.util.Random()

val smallishWholeNumbers = object: Gen<Int> {
	override fun constants() = Iterable<Int> { iterator { yield(0) } }
	override fun random() = generateSequence { random.nextInt(700) }
}

class Factorial_KotlinTest_PropertyBased: StringSpec() {
	init {

		val algorithms = table(
				headers("name", "f"),
				row("iterative", {x: Int -> iterative(x)}),
				row("reductive", {x: Int -> reductive(x)}),
				row("foldive", {x: Int -> foldive(x)}),
				row("naïve_recursive", {x: Int -> naïve_recursive(x)}),
				row("tail_recursive", {x: Int -> tail_recursive(x)})
		)

		forAll(algorithms) {name, f ->

			"$name: base case holds."  {
				f(0) == 1.bigint
			}

			"$name: recurrence relation is true for non-negative integer values" {
				verifyAll(smallishWholeNumbers) {i ->
					f(i + 1) == (i + 1).bigint * f(i)
				}
			}

			"$name: negative argument cause an exception" {
				verifyAll(Gen.negativeIntegers()) {i ->
					shouldThrow<IllegalArgumentException>{f(i)}
					true
				}
			}

		}

	}
}
