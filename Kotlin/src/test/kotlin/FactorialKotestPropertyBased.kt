package uk.org.winder.maths.factorial

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.negativeInts
import io.kotest.property.checkAll
import io.kotest.property.forAll

class FactorialKotestPropertyBased: FreeSpec({
    // Cannot use ::iterative, etc. here as each is an overloaded function and there
    // is no way of disambiguating. So create lambdas, to force correct types and
    // hence function selection.

	val functions = table(
			headers("name", "f"),
			row("iterative", {x: Int -> iterative(x)}),
			row("reductive", {x: Int -> reductive(x)}),
			row("foldive", {x: Int -> foldive(x)}),
			row("naiveRecursive", {x: Int -> naiveRecursive(x)}),
			row("tailRecursive", {x: Int -> tailRecursive(x)}),
	)

	"base case" - {
		forAll(functions) { name, f ->
			"$name(0) == 1" {
				f(0) == 1.bigint
			}
		}
	}

	"factorial recurrence relation is true for non-negative integer arguments" - {
		forAll(functions) { name, f ->
			name - {
				forAll(Arb.int(1, 2000)) { i ->
					f(i + 1) == (i + 1).bigint * f(i)
				}
			}
		}
	}

	"negative arguments result in IllegalArgumentException" - {
		forAll(functions) { name, f ->
			name - {
				checkAll(Arb.negativeInts()) { n ->
					shouldThrow<IllegalArgumentException> {
						f(n)
					}
				}
			}
		}
	}

})
