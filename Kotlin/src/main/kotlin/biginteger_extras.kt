package uk.org.winder.maths.factorial

// The original code provided by Mike Hearn in response to a question on the Kotlin forum.
// cf. https://devnet.jetbrains.com/thread/460227?tstart=0.
// See http://kotlin-demo.jetbrains.com/?publicLink=61203304-132683114

import java.math.BigInteger

import kotlin.math.minus
import kotlin.math.plus

val Int.bigint: BigInteger get() = BigInteger.valueOf(this.toLong())
val Long.bigint: BigInteger get() = BigInteger.valueOf(this)

public open class BigIntegerProgression(
    override val start: BigInteger,
    override val end: BigInteger,
    override val increment: BigInteger
) : Progression<BigInteger> {

    // Class pre-conditions
    {
        if (increment == 0.bigint) throw IllegalArgumentException("Increment must not be zero")
    }

    override fun iterator(): Iterator<BigInteger> = object : Iterator<BigInteger> {
        private var next: BigInteger = start
        private var hasNext: Boolean = if (increment > 0.bigint) start <= end else start >= end
        private val finalElement = calculateFinalElement()
        override fun next(): BigInteger {
            val value = next
            if (next == finalElement) { hasNext = false }
            else { next += increment }
            return value
        }
        override fun hasNext(): Boolean = hasNext
        private fun diffMod(a: BigInteger, b: BigInteger, m: BigInteger) = ((a mod m) - (b mod m)) mod m
        private fun calculateFinalElement(): BigInteger =
                if (increment > 0.bigint) end - diffMod(end, start, increment) else end + diffMod(start, end, -increment)
    }
    fun step(step: BigInteger) = BigIntegerProgression(start, end, if (increment > 0.bigint) step else -step)
    fun step(step: Number) = step(step.toLong().bigint)
}

public class BigIntegerRange(override val start: BigInteger, override val end: BigInteger):
    BigIntegerProgression(start, end, 1.bigint), Range<BigInteger> {
    override fun contains(item: BigInteger): Boolean = start <= item && item <= end
}

fun BigInteger.rangeTo(that: BigInteger) = BigIntegerRange(this, that)
fun BigInteger.downTo(that: BigInteger) = BigIntegerProgression(this, that, -1.bigint)
