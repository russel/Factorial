#include "factorial_gmp.hpp"

namespace Factorial {

static void validate(mpz_class const n) {
  if (n < 0) { throw std::invalid_argument("Parameter must be a non-negative integer."); }
}

// --------------------------------------------------------------------------------

mpz_class iterative(mpz_class const n) {
  validate(n);
  mpz_class total {1};
  for (unsigned int i = 2; i <= n; ++i) { total *= i; }
  return total;
}
mpz_class iterative(long const n) { return iterative(mpz_class(n)); }

// --------------------------------------------------------------------------------

mpz_class recursive(mpz_class const n) {
  validate(n);
  return (n < 2) ? mpz_class(1) : n * recursive(n - 1);
}
mpz_class recursive(long const n) { return recursive(mpz_class(n)); }

// --------------------------------------------------------------------------------

static mpz_class tailRecursive_iterate(mpz_class const n, mpz_class const result) {
  return (n < 2) ? result : tailRecursive_iterate(n - 1, result * n);
}

mpz_class tailRecursive(mpz_class const n) {
	validate(n);
  return (n < 2) ? mpz_class(1) : tailRecursive_iterate(n, mpz_class(1));
}
mpz_class tailRecursive(long const n) { return tailRecursive(mpz_class(n)); }


} // namespace Factorial
