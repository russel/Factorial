import std.algorithm: reduce;
import std.bigint: BigInt;
import std.range: iota;
import std.typecons: tuple;

immutable BigInt zero = BigInt(0);
immutable BigInt one = BigInt(1);
immutable BigInt two = BigInt(2);

BigInt iterative(immutable ulong n) { return iterative(BigInt(n)); }
BigInt iterative(immutable BigInt n) {
	if (n < two) { return one; }
	BigInt total = one;
	foreach (i; two .. n + one) { total *= i; }
	return total;
}

BigInt recursive(immutable ulong n) { return recursive(BigInt(n)); }
BigInt recursive(immutable BigInt n) {
	return (n < two) ? one : n * recursive(n - one);
}

BigInt tailRecursive(immutable ulong n) { return tailRecursive(BigInt(n)); }
BigInt tailRecursive(immutable BigInt n) {
	BigInt iterate(immutable BigInt n, immutable BigInt result) {
		//  Except, of course, that there is no tail recursion optimization in D
		//  so this takes serious stack space.
		return (n < two) ? result : iterate(n - one, result * n);
	}
	return iterate(n, one);
}

BigInt reductive(immutable ulong n) { return reductive(BigInt(n)); }
BigInt reductive(immutable BigInt n) {
	return reduce!"a * b"(one, iota(BigInt(one), n + one));
}

version(unittest) {
	import unit_threaded;
	import std.conv: to;

	immutable algorithms = [
							tuple(&iterative, "iterative"),
							tuple(&recursive, "recursive"),
							tuple(&tailRecursive, "tailRecursive"),
							tuple(&reductive, "reductive"),
							];

}

@("Traditional example-based testing.")
unittest {

	immutable data = [
					  tuple(0, one),
					  tuple(1, one),
					  tuple(2, two),
					  tuple(3, immutable BigInt(6)),
					  tuple(4, immutable BigInt(24)),
					  tuple(5, immutable BigInt(120)),
					  tuple(6, immutable BigInt(720)),
					  tuple(7, immutable BigInt(5040)),
					  tuple(8, immutable BigInt(40320)),
					  tuple(9, immutable BigInt(362880)),
					  tuple(10, immutable BigInt(3628800)),
					  tuple(11, immutable BigInt(39916800)),
					  tuple(12, immutable BigInt(479001600)),
					  tuple(13, immutable BigInt(6227020800)),
					  tuple(14, immutable BigInt(87178291200)),
					  tuple(20, immutable BigInt(2432902008176640000)),
					  tuple(30, immutable BigInt("265252859812191058636308480000000")),
					  tuple(40, immutable BigInt("815915283247897734345611269596115894272000000000")),
					  ];

	foreach (immutable a; algorithms) {
		foreach (immutable item; data) {
			immutable result = a[0](item[0]);
			assert(result == item[1], a[1] ~ "(" ~ to!string(item[0]) ~ ") = " ~ to!string(result) ~ " should be " ~ to!string(item[1]));
		}
	}
}

@("Check the base case for all algorithms.")
unittest {
	foreach(a; algorithms) {
		assert(a[0](0) == 1, a[1] ~ "(0) == 1 failed");
	}
}

@("Check the property for all algorithms.")
unittest {
	foreach(a; algorithms) {
		immutable f = a[0];
		check!((ubyte a) => f(a + 1) == (a + 1) * f(a));
	}
}
