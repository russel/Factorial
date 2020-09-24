package uk.org.winder.maths.factorial

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.property.Arb
import io.kotest.property.arbitrary.negativeInts
import io.kotest.property.arbitrary.positiveInts
import io.kotest.property.checkAll
import io.kotest.property.forAll

class Factorial_Kotest_PropertyBased: StringSpec({
    // Cannot use ::iterative, etc. here as each is an overloaded function and there
    // is no way of disambiguating. So create lambdas, to force correct types and
    // hence function selection.

	val functions = table(
			headers("name", "f"),
			row("iterative", {x: Int -> iterative(x)}),
			row("reductive", {x: Int -> reductive(x)}),
			row("foldive", {x: Int -> foldive(x)}),
			row("naïve_recursive", {x: Int -> naive_recursive(x)}),
			row("tail_recursive", {x: Int -> tail_recursive(x)}),
	)

	"base case holds, f(0) == 1" {
		forAll(functions) { _, f ->
			f(0) == 1.bigint
		}
	}

	"factorial recurrence relation is true for non-negative integer arguments" {
		forAll(functions) { _, f ->
			forAll(Arb.positiveInts(2000)) { i ->
				f(i + 1) == (i + 1).bigint * f(i)
			}
		}
	}

	"negative arguments cause IllegalArgumentException" {
		forAll(functions) { _, f ->
			checkAll(Arb.negativeInts()) { i ->
				shouldThrow<IllegalArgumentException>{
					f(i)
				}
			}
		}
	}

})
