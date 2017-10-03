package uk.org.winder.maths

import scala.annotation.tailrec

object Factorial {
	val one = BigInt(1)
	val two = BigInt(2)

	private def validate(n:BigInt):Unit = {
		if (n < 0) throw new IllegalArgumentException("Parameter must be a non-negative integer.")
	}

	def iterativeWhile(n:BigInt):BigInt = {
		validate(n)
		var i = n
		var result = one
		while (i > 0) {
			result = result * i
			i = i - one
		}
		result
	}
	def iterativeWhile(n:Int):BigInt = iterativeWhile(BigInt(n))
	def iterativeWhile(n:Long):BigInt = iterativeWhile(BigInt(n))

	def iterativeFor(n:BigInt):BigInt = {
		validate(n)
		var result = one
		for (i <- one to n) { result *= i }
		result
	}
	def iterativeFor(n:Int):BigInt = iterativeFor(BigInt(n))
	def iterativeFor(n:Long):BigInt = iterativeFor(BigInt(n))

	def iterativeForeach(n:BigInt):BigInt = {
		validate(n)
		var result = one
		(two to n) foreach ((i:BigInt) => result *= i)
		result
	}
	def iterativeForeach(n:Int):BigInt = iterativeForeach(BigInt(n))
	def iterativeForeach(n:Long):BigInt = iterativeForeach(BigInt(n))

	def productive(n:BigInt):BigInt = {
		validate(n)
			(one to n).product
	}
	def productive(n:Int):BigInt = productive(BigInt(n))
	def productive(n:Long):BigInt = productive(BigInt(n))

	def reductive(n:BigInt):BigInt = {
		validate(n)
		if (n < two) one
		else two to n reduceLeft(_ * _)
	}
	def reductive(n:Int):BigInt = reductive(BigInt(n))
	def reductive(n:Long):BigInt = reductive(BigInt(n))

	def foldLeftive(n:BigInt):BigInt = {
		validate(n)
		(one /: (two to n))(_ * _)
	}
	def foldLeftive(n:Int):BigInt = foldLeftive(BigInt(n))
	def foldLeftive(n:Long):BigInt = foldLeftive(BigInt(n))

	def naïveRecursive(n:BigInt):BigInt = {
		validate(n)
		if (n < two) one
		else n * naïveRecursive(n - 1)
	}
	def naïveRecursive(n:Int):BigInt = naïveRecursive(BigInt(n))
	def naïveRecursive(n:Long):BigInt = naïveRecursive(BigInt(n))

	def tailRecursive(n:BigInt):BigInt = {
		validate(n)
		if (n < two) one
		else {
			@tailrec
			def iterate(x:BigInt = one, result:BigInt = one):BigInt =
				if (x > n) result
				else iterate(x + one, result * x)
			iterate()
		}
	}
	def tailRecursive(n:Int):BigInt = tailRecursive(BigInt(n))
	def tailRecursive(n:Long):BigInt = tailRecursive(BigInt(n))

}
