/*
A collection of implementations of factorial, which is defined by the recurrence relation:

    f 0 = 1
    f n = n . f (n - 1), n > 0

The tail recursive implementation is only of limited usefulness since Python does not support tail recursion
optimization – so each tail recursive call uses a new stack frame just as the recursive implementation does
and the stack size is strictly limited.
*/

use GMP;
use Assert;

const zero = new BigInt(0);
const one = new BigInt(1);

proc iterative(n:BigInt):BigInt {
  assert(n.cmp(zero) >= 0);
  var count = n;
  var total = new BigInt(1);
  while count.cmp(one) > 0 {
    total.mul(total, count);
    count.sub(count, one);
  }
  return total;
}

proc iterative(n:int): BigInt {
  assert(n >= 0);
  var total = new BigInt(1);
  for i in 1..n do total.mul_si(total, i);
  return total;
}


// Create a reduction operation for multiplying BigInts
class MulBigInt: ReduceScanOp {
  type eltType;
  var sum:BigInt = new BigInt(1);
  proc accumulate(value:eltType) { sum.mul(sum, value); }
  proc combine(other:MulBigInt) { sum.mul(sum, other.sum); }
  proc generate() { return sum; }
}


proc reductive_sequential(n: BigInt):BigInt {
  assert(n.cmp(zero) >= 0);
  return MulBitInt reduce for i in one..n do i;
}

proc reductive_sequential(n:int): BigInt {
  assert(n >= 0);
  return MulBigInt reduce for i in 1..n do new BigInt(i);
}

proc reductive_parallel(n: BigInt):BigInt {
  assert(n.cmp(zero) >= 0);
  return MulBigInt reduce [i in one..n] i;
}

proc reductive_parallel(n:int): BigInt {
  assert(n >= 0);
  return MulBigInt reduce [i in 1..n] new BigInt(i);
}
