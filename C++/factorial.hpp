/**
 * @file
 *
 * This file contains signatures of various factorial functions using
 * various algorithms.
 *
 * @copyright © 2016 Russel Winder
 * @author Russel Winder (<russel@winder.org.uk>)
 */

#include <gmpxx.h>

namespace Factorial {

mpz_class iterative(mpz_class const n);
mpz_class iterative(long const n);
mpz_class reductive(mpz_class const n);
mpz_class reductive(long const n);
mpz_class naive_recursive(mpz_class const n);
mpz_class naive_recursive(long const n);
mpz_class tail_recursive(mpz_class const n);
mpz_class tail_recursive(long const n);

} // namespace Factorial
