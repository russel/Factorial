package uk.org.winder.maths.factorial

// The original code provided by Mike Hearn in response to a question on the Kotlin forum.
// cf. https://devnet.jetbrains.com/thread/460227?tstart=0.
// See http://kotlin-demo.jetbrains.com/?publicLink=61203304-132683114

import java.math.BigInteger

import kotlin.math.div
import kotlin.math.minus
import kotlin.math.plus
import kotlin.math.times
import kotlin.math.unaryMinus

val Int.bigint: BigInteger get() = BigInteger.valueOf(this.toLong())
val Long.bigint: BigInteger get() = BigInteger.valueOf(this)

public open class BigIntegerProgression(
    override val start: BigInteger,
    override val end: BigInteger,
    override val increment: BigInteger
): Progression<BigInteger>, Iterable<BigInteger> {

  init {
    if (increment == BigInteger.ZERO) throw IllegalArgumentException("Increment must not be zero")
  }

  override fun iterator(): Iterator<BigInteger> = object: Iterator<BigInteger> {
    private var next: BigInteger = start
    private var hasNext: Boolean = if (increment > BigInteger.ZERO) start <= end else start >= end
    private val finalElement = calculateFinalElement()
    override fun next(): BigInteger {
      val value = next
      if (next == finalElement) { hasNext = false }
      else { next += increment }
      return value
    }
    override fun hasNext(): Boolean = hasNext
    private fun diffMod(a: BigInteger, b: BigInteger, m: BigInteger) = ((a.mod(m)) - (b.mod(m))).mod(m)
    private fun calculateFinalElement(): BigInteger =
        if (increment > BigInteger.ZERO) end - diffMod(end, start, increment) else end + diffMod(start, end, -increment)
  }
  infix fun step(step: BigInteger) = BigIntegerProgression(start, end, if (increment > 0.bigint) step else -step)
  infix fun step(step: Number) = step(step.toLong().bigint)
}

public class BigIntegerRange(override val start: BigInteger, override val end: BigInteger):
    BigIntegerProgression(start, end, 1.bigint), ClosedRange<BigInteger> {
  override fun contains(value: BigInteger): Boolean = start <= value && value <= end
  override val endInclusive: BigInteger = BigInteger.ZERO
}

infix fun BigInteger.rangeTo(that: BigInteger) = BigIntegerRange(this, that)
infix fun BigInteger.downTo(that: BigInteger) = BigIntegerProgression(this, that, -1.bigint)