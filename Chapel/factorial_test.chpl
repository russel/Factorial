use BigInteger;

//const algorithms = (iterative, reductive_sequential, reductive_parallel);
const positiveData = (
                      (0, new bigint(1)),
                      (1, new bigint(1)),
                      (2, new bigint(2)),
                      (3, new bigint(6)),
                      (4, new bigint(24)),
                      (5, new bigint(120)),
                      (6, new bigint(720)),
                      (7, new bigint(5040)),
                      (8, new bigint(40320)),
                      (9, new bigint(362880)),
                      (10, new bigint(3628800)),
                      (11, new bigint(39916800)),
                      (12, new bigint(479001600)),
                      (13, new bigint(6227020800)),
                      (14, new bigint(87178291200)),
                      (20, new bigint(2432902008176640000)),
                      (30, new bigint("265252859812191058636308480000000")),
                      (40, new bigint("815915283247897734345611269596115894272000000000"))
                      );
//const negativeData = (-1, -2, -5, -10, -20, -100);

var assertCount = 0;
var assertFail = 0;

proc assertEqual(s: string, x: bigint, y: bigint) {
  assertCount += 1;
  if x != y {
    assertFail += 1;
    writeln(s, ": assertion failed: ", x, " != ", y);
  }
}

proc main() {

  for (v, r) in positiveData {
    assertEqual("iterative_int", factorial.iterative(v), r);
    assertEqual("iterative_bigint", factorial.iterative(new bigint(v)), r);
    assertEqual("reductive_sequential_int", factorial.reductive_sequential(v), r);
    //assertEqual("reductive_sequential_bigint", factorial.reductive_sequential(new bigint(v)), r);
    assertEqual("reductive_parallel_int", factorial.reductive_parallel(v), r);
    //assertEqual("reductive_parallel_bigint", factorial.reductive_parallel(new bigint(v)), r);
  }

  /*
  for i in negativeData {
    try {
      assertCount += 1;
      factorial.iterative(i);
      assertFail += 1;
    }
    catch (assert_error ae) {}
    catch (error e) { assertFail = 1;}
  }
  */

  writeln("\n#### ", assertCount, " assertions, ", assertFail, " failures.");
}
