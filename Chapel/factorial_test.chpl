use GMP;

//const algorithms = (iterative, reductive_sequential, reductive_parallel);
const positiveData = (
                      (0, new BigInt(1)),
                      (1, new BigInt(1)),
                      (2, new BigInt(2)),
                      (3, new BigInt(6)),
                      (4, new BigInt(24)),
                      (5, new BigInt(120)),
                      (6, new BigInt(720)),
                      (7, new BigInt(5040)),
                      (8, new BigInt(40320)),
                      (9, new BigInt(362880)),
                      (10, new BigInt(3628800)),
                      (11, new BigInt(39916800)),
                      (12, new BigInt(479001600)),
                      (13, new BigInt(6227020800)),
                      (14, new BigInt(87178291200)),
                      (20, new BigInt(2432902008176640000)),
                      (30, new BigInt("265252859812191058636308480000000")),
                      (40, new BigInt("815915283247897734345611269596115894272000000000"))
                      );
//const negativeData = (-1, -2, -5, -10, -20, -100);

var assertCount = 0;
var assertFail = 0;

proc assertEqual(s: string, x:BigInt, y:BigInt) {
  assertCount += 1;
  if x.cmp(y) != 0 {
    assertFail += 1;
    writeln(s, ": assertion failed: ", x, " != ", y);
  }
}

proc main() {

  for (v, r) in positiveData {
    assertEqual("iterative_int", factorial.iterative(v), r);
    assertEqual("iterative_BigInt", factorial.iterative(new BigInt(v)), r);
    assertEqual("reductive_sequential_int", factorial.reductive_sequential(v), r);
    //assertEqual("reductive_sequential_BigInt", factorial.reductive_sequential(new BigInt(v)), r);
    assertEqual("reductive_parallel_int", factorial.reductive_parallel(v), r);
    //assertEqual("reductive_parallel_BigInt", factorial.reductive_parallel(new BigInt(v)), r);
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
