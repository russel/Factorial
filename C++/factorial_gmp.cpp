#include "factorial_gmp.hpp"

#include <functional>
#include <iterator>
#include <numeric>

namespace Factorial {

static void validate(mpz_class const n) {
  if (n < 0) { throw std::invalid_argument("Parameter must be a non-negative integer."); }
}

auto const one = mpz_class(1);
auto const two = mpz_class(2);

// --------------------------------------------------------------------------------

mpz_class iterative(mpz_class const n) {
  validate(n);
  mpz_class total {1};
  for (unsigned int i = 2; i <= n; ++i) { total *= i; }
  return total;
}
mpz_class iterative(long const n) { return iterative(mpz_class(n)); }

// --------------------------------------------------------------------------------

class mpz_class_iterator : std::iterator<std::forward_iterator_tag, mpz_class> {
 private:
	mpz_class value;
 public:
	mpz_class_iterator(mpz_class const v) : value(v) { }
	mpz_class operator++() { value += 1; return value; }
  mpz_class operator++(int) { mpz_class tmp(value); operator++(); return tmp; }
  bool operator==(mpz_class_iterator const & other) { return value == other.value; }
  bool operator!=(mpz_class_iterator const & other) { return value != other.value; }
  mpz_class operator*() { return  value; }
};

mpz_class reductive(mpz_class const n) {
	validate(n);
	return (n < 2) ? one : std::accumulate(mpz_class_iterator(two), mpz_class_iterator(n + 1), one, std::multiplies<mpz_class>());
}
mpz_class reductive(long const n) { return reductive(mpz_class(n)); }

// --------------------------------------------------------------------------------

mpz_class recursive(mpz_class const n) {
  validate(n);
  return (n < 2) ? one : n * recursive(n - 1);
}
mpz_class recursive(long const n) { return recursive(mpz_class(n)); }

// --------------------------------------------------------------------------------

static mpz_class tailRecursive_iterate(mpz_class const n, mpz_class const result) {
  return (n < 2) ? result : tailRecursive_iterate(n - 1, result * n);
}

mpz_class tailRecursive(mpz_class const n) {
	validate(n);
  return (n < 2) ? one : tailRecursive_iterate(n, one);
}
mpz_class tailRecursive(long const n) { return tailRecursive(mpz_class(n)); }


} // namespace Factorial
