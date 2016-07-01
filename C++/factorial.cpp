/**
 * @file
 *
 * This file contains various implementation of the factorial function using
 * various algorithms.
 *
 * @copyright © 2016 Russel Winder
 * @author Russel Winder (<russel@winder.org.uk>)
 */

/**
 * @mainpage
 *
 * There are here various implementation of the factorial function using
 * various algorithms. Factorial is defined by the recurrence relation:
 *
 * f(0) = 1
 *
 * f(n) = n f(n) , n > 0
 *
 * The factorial functions themselves are not really that interesting.
 * What is interesting is the tests. [Catch](https://github.com/philsquared/Catch)
 * is used for doing some example-based testing whilst
 * [RapidCheck](https://github.com/emil-e/rapidcheck) is used for
 * property-based testing
 *
 * @copyright © 2016 Russel Winder
 * @author Russel Winder (<russel@winder.org.uk>)
 */

#include "factorial.hpp"

#include <functional>
#include <iterator>
#include <numeric>

namespace Factorial {

/**
 *  Ensures that the argument is a valid argument for factorial functions.
 *
 * @param n  the putative valid value.
 */
void validate(mpz_class const n) {
  if (n < 0) { throw std::invalid_argument("Parameter must be a non-negative integer."); }
}

auto const one = mpz_class(1);
auto const two = mpz_class(2);

/**
 *  Loop-realized iterative implementation of factorial function.
 *
 * @param n  value to calculate factorial of.
 * @return  factorial of argument.
 */
mpz_class iterative(mpz_class const n) {
  validate(n);
  mpz_class total {1};
  for (unsigned int i = 2; i <= n; ++i) { total *= i; }
  return total;
}

/**
 *  Loop-realized iterative implementation of factorial function.
 *
 * @param n  value to calculate factorial of.
 * @return  factorial of argument.
 */
mpz_class iterative(long const n) { return iterative(mpz_class(n)); }

/**
 * An input iterator over `mpz_class` values. Can be used with `std::accumulate`
 * and such like to provide ranges over which to operate.
 */
class mpz_class_iterator: std::iterator<std::input_iterator_tag, mpz_class> {
 private:
	mpz_class value;
 public:
	mpz_class_iterator(mpz_class const v) : value(v) { }
	mpz_class_iterator& operator++() { value += 1; return *this; }
	mpz_class_iterator operator++(int) { mpz_class_iterator tmp {*this}; value += 1; return tmp; }
	bool operator==(mpz_class_iterator const & other) const { return value == other.value; }
	bool operator!=(mpz_class_iterator const & other) const { return value != other.value; }
	mpz_class operator*() const { return  value; }
	mpz_class const * operator->() const { return &value; }
};

/**
 *  Loop-realized iterative implementation of factorial function.
 *
 * @param n  value to calculate factorial of.
 * @return  factorial of argument.
 */
mpz_class reductive(mpz_class const n) {
	validate(n);
	return (n < 2)
	 ? one
	 : std::accumulate(mpz_class_iterator(two), mpz_class_iterator(n + 1), one, std::multiplies<>());
}

/**
 *  Implementation of factorial function employing accumulate function
 *  from the standard library.
 *
 * @param n  value to calculate factorial of.
 * @return  factorial of argument.
 */
mpz_class reductive(long const n) { return reductive(mpz_class(n)); }

/**
 *  Naïve recursive implementation of factorial function.
 *
 * @param n  value to calculate factorial of.
 * @return  factorial of argument.
 */
mpz_class naive_recursive(mpz_class const n) {
  validate(n);
  return (n < 2) ? one : n * naive_recursive(n - 1);
}

/**
 *  Naïve recursive implementation of factorial function.
 *
 * @param n  value to calculate factorial of.
 * @return  factorial of argument.
 */
mpz_class naive_recursive(long const n) { return naive_recursive(mpz_class(n)); }

/**
 *  Tail recursive "iteration" function for calculating factorial of a value.
 *
 * @param n the value to calcualte the factorial of.
 * @param result  the factorial value calculated to date.
 * @return  factorial of argument.
 */
mpz_class tail_recursive_iterate(mpz_class const n, mpz_class const result) {
  return (n < 2) ? result : tail_recursive_iterate(n - 1, result * n);
}

/**
 *  Tail recursive implementation of factorial function.
 *
 * @param n  value to calculate factorial of.
 * @return  factorial of argument.
 */
mpz_class tail_recursive(mpz_class const n) {
	validate(n);
  return (n < 2) ? one : tail_recursive_iterate(n, one);
}

/**
 *  Tail recursive implementation of factorial function.
 *
 * @param n  value to calculate factorial of.
 * @return  factorial of argument.
 */
mpz_class tail_recursive(long const n) { return tail_recursive(mpz_class(n)); }

} // namespace Factorial
