package uk.org.winder.maths.factorial

import io.kotest.core.spec.style.StringSpec

class BigInteger_Extras_Kotest: StringSpec() {
	init {

		"a value is in a range it is supposed to be in" {
			5.bigint in 0.bigint .. 10.bigint
		}

		"a negative value is not in a positive value range" {
			-5.bigint !in 0.bigint .. 10.bigint
		}

		"a positive value above the positive value range is not in the range" {
			15.bigint !in 0.bigint .. 10.bigint
		}

		"an upward range generates the expected values" {
			(0.bigint .. 5.bigint).toList() == listOf(0, 1, 2, 3, 4, 5).map { it.bigint }
		}

		"an upward strided range generates the expected values" {
			(0.bigint .. 5.bigint step 2).toList() == listOf(0, 2, 4).map { it.bigint }
		}

		"a downward range generates the expected values" {
			(5.bigint downTo 0.bigint).toList() == listOf(5, 4, 3, 2, 1, 0).map { it.bigint }
		}

		"a downward strided range generates the expected values" {
			(5.bigint downTo 0.bigint step 2).toList() == listOf(5, 3, 1).map { it.bigint }
		}

	}
}
