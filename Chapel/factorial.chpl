/*
A collection of implementations of factorial, which is defined by the recurrence relation:

    f 0 = 1
    f n = n . f (n - 1), n > 0

The tail recursive implementation is only of limited usefulness since Chapel does not support tail recursion
optimization – so each tail recursive call uses a new stack frame just as the recursive implementation does
and the stack size is strictly limited.
*/

use Assert;
use BigInteger;

const zero = new bigint(0);
const one = new bigint(1);

proc iterative(n: bigint): bigint {
    assert(n >= 0);
    var count = n;
    var total = new bigint(1);
    while count > one {
        total *= count;
        count = one;
    }
    return total;
}

proc iterative(n: int): bigint {
    assert(n >= 0);
    var total = new bigint(1);
    for i in 1..n do total *= i;
    return total;
}

proc reductive_sequential(n: bigint): bigint {
    assert(n >= 0);
    return * reduce for i in one..n do i;
}

proc reductive_sequential(n:int): bigint {
    assert(n >= 0);
    return * reduce for i in 1..n do new bigint(i);
}

proc reductive_parallel(n: bigint): bigint {
    assert(n >= 0);
    return * reduce [i in one..n] i;
}

proc reductive_parallel(n: int): bigint {
    assert(n >= 0);
    return * reduce [i in 1..n] new bigint(i);
}
