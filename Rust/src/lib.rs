use num::{BigUint, One, Zero};
use num::bigint::ToBigUint;

use std::ops::Mul;

pub fn iterative_biguint(n: &BigUint) -> BigUint {
    let big_one: BigUint = BigUint::one();
    let mut total: BigUint = BigUint::one();
    let mut i: BigUint = BigUint::one();
    while i <= *n {
        total = &total * &i;
        i = &i + &big_one
    }
    total
}

pub fn iterative_usize(n: usize) -> BigUint {
    iterative_biguint(&(n.to_biguint().unwrap()))
}

/*
 * Higher order functions not realised for std::ops::Range<num::BigUint>.
 *
pub fn fold_biguint(n: &BigUint) -> BigUint {
    (BigUint::one() .. *n).fold(BigUint::one(), Mul::mul)
}
 */

pub fn fold_usize(n: usize) -> BigUint {
    (1 .. n + 1).map(BigUint::from).fold(BigUint::one(), Mul::mul)
}


pub fn naive_recursive_biguint(n: &BigUint) -> BigUint {
    let big_one: BigUint = BigUint::one();
    if *n == BigUint::zero() || *n == big_one { big_one }
    else { n * naive_recursive_biguint(&(n - big_one)) }
}

pub fn naive_recursive_usize(n: usize) -> BigUint {
    naive_recursive_biguint(&(n.to_biguint().unwrap()))
}

pub fn tail_recursive_biguint(n: &BigUint) -> BigUint {
    fn iterate(i: &BigUint, t: &BigUint) -> BigUint {
        let big_one: BigUint = One::one();
        if *i == Zero::zero() || *i == big_one { t.clone() }
        else { iterate(&(i - big_one), &(t * i)) }
    }
    iterate(n, &(1.to_biguint().unwrap()))
}

pub fn tail_recursive_usize(n: usize) -> BigUint {
    tail_recursive_biguint(&(n.to_biguint().unwrap()))
}

#[cfg(test)]
mod tests {

    use super::{
        iterative_usize,
        fold_usize,
        naive_recursive_usize,
        tail_recursive_usize,
    };

    use num::BigUint;
    use num::bigint::ToBigUint;
    use quickcheck::quickcheck;
    use proptest::{prop_assert_eq, proptest};

    fn functions() -> Vec<(fn(usize)->BigUint, &'static str)> {
        vec!(
            (iterative_usize, "iterative"),
            (fold_usize, "fold"),
            (naive_recursive_usize, "naïve_recursive"),
            (tail_recursive_usize, "tail_recursive"),
        )
    }

    #[test]
    fn positive_numbers_by_example() {

        // This way of doing data-driven testing is only good when there are no errors.
        // If there are errors then  the loops are terminated and no later tests are executed.
        // This is just wrong.

        let values = [
            (0, 1.to_biguint().unwrap()),
            (1, 1.to_biguint().unwrap()),
            (2, 2.to_biguint().unwrap()),
            (3, 6.to_biguint().unwrap()),
            (4, 24.to_biguint().unwrap()),
            (5, 120.to_biguint().unwrap()),
            (6, 720.to_biguint().unwrap()),
            (7, 5040.to_biguint().unwrap()),
            (8, 40320.to_biguint().unwrap()),
            (9, 362880.to_biguint().unwrap()),
            (10, 3628800.to_biguint().unwrap()),
            (11, 39916800.to_biguint().unwrap()),
            (12, 479001600.to_biguint().unwrap()),
            (13, 6227020800u64.to_biguint().unwrap()),
            (14, 87178291200u64.to_biguint().unwrap()),
            (20, 2432902008176640000u64.to_biguint().unwrap()),
            (30, BigUint::parse_bytes(b"265252859812191058636308480000000", 10).unwrap()),
            (40, BigUint::parse_bytes(b"815915283247897734345611269596115894272000000000", 10).unwrap())
        ];

        for f in functions() {
            for v in values.iter() {
                assert!(f.0(v.0) == v.1, "{}({}) != {}", f.1, v.0, v.1)
            }
        }
    }

    quickcheck!{
        fn quickcheck_iterative_usize(n: usize) -> bool {
            // only values < 100 are tested.
            iterative_usize(n + 1) == (n + 1).to_biguint().unwrap() * iterative_usize(n)
        }
    }

    quickcheck!{
        fn quickcheck_fold_usize(n: usize) -> bool {
            // only values < 100 are tested.
            fold_usize(n + 1) == (n + 1).to_biguint().unwrap() * fold_usize(n)
        }
    }

    quickcheck!{
        fn quickcheck_naive_recursive_usize(n: usize) -> bool {
            // only values < 100 are tested.
            naive_recursive_usize(n + 1) == (n + 1).to_biguint().unwrap() * naive_recursive_usize(n)
        }
    }

    quickcheck!{
        fn quickcheck_tail_recursive_usize(n: usize) -> bool {
            // only values < 100 are tested.
            tail_recursive_usize(n + 1) == (n + 1).to_biguint().unwrap() * tail_recursive_usize(n)
        }
    }

    proptest!{
        #[test]
        fn proptest_iterative_usize(n in 0usize..500) {
            // Only test for small n so the tests run in reasonable time.
            prop_assert_eq!(iterative_usize(n + 1), (n + 1).to_biguint().unwrap() * iterative_usize(n))
        }
    }

    proptest!{
        #[test]
        fn proptest_fold_usize(n in 0usize..500) {
            // Only test for small n so the tests run in reasonable time.
            prop_assert_eq!(fold_usize(n + 1), (n + 1).to_biguint().unwrap() * fold_usize(n))
        }
    }

    proptest!{
        #[test]
        fn proptest_naive_recursive_usize(n in 0usize..500) {
            // Only test for small n so the tests run in reasonable time.
            prop_assert_eq!(naive_recursive_usize(n + 1), (n + 1).to_biguint().unwrap() * naive_recursive_usize(n))
        }
    }

    proptest!{
        #[test]
        fn proptest_tail_recursive_usize(n in 0usize..500) {
            // Only test for small n so the tests run in reasonable time.
            prop_assert_eq!(tail_recursive_usize(n + 1), (n + 1).to_biguint().unwrap() * tail_recursive_usize(n))
        }
    }

}
