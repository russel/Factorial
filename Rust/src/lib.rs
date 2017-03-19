extern crate num;

#[cfg(test)]
#[macro_use]
extern crate quickcheck;

use num::{BigUint, One, Zero};
use num::bigint::ToBigUint;

use std::ops::Mul;

pub fn iterative_for_biguint(n: &BigUint) -> BigUint {
    let big_one: BigUint = BigUint::one();
    let mut total: BigUint = BigUint::one();
    let mut i: BigUint = BigUint::one();
    while i <= *n {
        total = &total * &i;
        i = &i + &big_one
    }
    total
}

pub fn iterative_for_usize(n: usize) -> BigUint {
    iterative_for_biguint(&(n.to_biguint().unwrap()))
}

/*
pub fn iterative_fold_biguint(n: &BigUint) -> BigUint {
    (BigUint::one() .. *n).fold(BigUint::one(), Mul::mul)
}
 */

pub fn iterative_fold_usize(n: usize) -> BigUint {
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

/*
pub fn tail_recursive_biguint(n: &BigUint) -> BigUint {
    fn iterate<'a>(i: &BigUint, t: &'a BigUint) -> &'a BigUint {
        let big_one: BigUint = One::one();
        if *i == Zero::zero() || *i == big_one { t }
        else { &iterate(&(i - big_one), &(t * i)) }
    }
    *iterate(n, &(1.to_biguint().unwrap()))
}

pub fn tail_recursive_usize(n: usize) -> BigUint {
    tail_recursive_biguint(&(n.to_biguint().unwrap()))
}
 */

#[cfg(test)]
mod tests {

    use super::{
        iterative_for_usize,
        iterative_fold_usize,
        naive_recursive_usize,
        //tail_recursive_usize,
    };

    use num::BigUint;
    use num::bigint::ToBigUint;

    fn functions() -> Vec<(fn(usize)->BigUint, &'static str)> {
        vec!(
            (iterative_for_usize, "iterative_for"),
            (iterative_fold_usize, "iterative_fold"),
            (naive_recursive_usize, "naïve_recursive"),
            //(tail_recursive_usize, "tail_recursive"),
        )
    }

    #[test]
    fn positive_numbers_by_example() {

        // This was of doing fata-driven testing is only good when there are no errors. If there are errors then
        // the loops are terminated and no later tests are executed. This is just wrong.

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

    quickcheck! {
        fn prop_iterative_for_usize(n: usize) -> bool {
            iterative_for_usize(n + 1) == (n + 1).to_biguint().unwrap() * iterative_for_usize(n)
        }
    }

    quickcheck! {
        fn prop_iterative_fold_usize(n: usize) -> bool {
            iterative_fold_usize(n + 1) == (n + 1).to_biguint().unwrap() * iterative_fold_usize(n)
        }
    }

    quickcheck! {
        fn prop_naive_recursive_usize(n: usize) -> bool {
            naive_recursive_usize(n + 1) == (n + 1).to_biguint().unwrap() * naive_recursive_usize(n)
        }
    }

}
