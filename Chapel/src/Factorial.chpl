/*
 * A collection of implementations of factorial, which is defined by the recurrence relation:
 *
 *  f_0 = 1
 *  f_n = n . f_{n - 1}, n > 0
 *
 * The tail recursive implementation is only of limited usefulness since Chapel does not support
 * tail recursion optimization – so each tail recursive call uses a new stack frame just as the
 * recursive implementation does and the stack size is strictly limited.
 */

use BigInteger;

const zero = new bigint(0);
const one = new bigint(1);

private proc validate(n: bigint) throws {
  if n < zero { throw new IllegalArgumentError(); }
}

private proc validate(n) throws where n.type == int || n.type == uint {
  if n < 0 { throw new IllegalArgumentError(); }
}

proc iterative(n: bigint): bigint throws {
  validate(n);
  var total = new bigint(1);
  for i in one..n do total *= i;
  return total;
}

proc iterative(n): bigint throws where n.type == int || n.type == uint {
  validate(n);
  var total = new bigint(1);
  for i in 1..n do total *= i;
  return total;
}

proc reductive_sequential(n: bigint): bigint throws {
  validate(n);
  return * reduce for i in one..n do i;
}

proc reductive_sequential(n): bigint throws where n.type == int || n.type == uint {
  validate(n);
  return * reduce for i in 1..n do new bigint(i);
}

proc reductive_parallel(n: bigint): bigint throws {
  validate(n);
  return * reduce [i in one..n] i;
}

proc reductive_parallel(n): bigint throws where n.type == int || n.type == uint {
  validate(n);
  return * reduce [i in 1..n] new bigint(i);
}

proc naive_recursive(n: bigint): bigint throws {
  validate(n);
  return if n < 2 then one else n * naive_recursive(n - one);
}

proc naive_recursive(n): bigint throws where n.type == int || n.type == uint return naive_recursive(new bigint(n));

proc tail_recursive(n: bigint): bigint throws {
  validate(n);
  proc iterate(n:bigint, total: bigint): bigint throws {
    return if n < 2 then total else iterate(n - one, total * n);
  }
  return iterate(n, one);
}

proc tail_recursive(n): bigint throws where n.type == int || n.type == uint return tail_recursive(new bigint(n));

proc library_fac(n): bigint throws where n.type == bigint || n.type == int || n.type == uint {
  validate(n);
  var x = new bigint();
  x.fac(n);  // Casts n to C ulong.
  return x;
}
