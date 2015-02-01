#define CATCH_CONFIG_MAIN
#include "catch.hpp"

#include "factorial_gmp.hpp"

#include <vector>
#include <utility>

std::vector<std::pair<int, std::string>> const positiveData {
  {0, "1"},
  {1, "1"},
  {2, "2"},
  {3, "6"},
  {4, "24"},
  {5, "120"},
  {6, "720"},
  {7, "5040"},
  {8, "40320"},
  {9, "362880"},
  {10, "3628800"},
  {11, "39916800"},
  {12, "479001600"},
  {13, "6227020800"},
  {14, "87178291200"},
  {20, "2432902008176640000"},
  {30, "265252859812191058636308480000000"},
  {40, "815915283247897734345611269596115894272000000000"}
};

std::vector<int> const negativeData {-1, -2, -5, -10, -20, -100};

static void positiveTest(mpz_class f(mpz_class const)) {
	for (auto const & p: positiveData) { REQUIRE(f(p.first) == mpz_class(p.second) ); }
}

TEST_CASE("Factorial of positive integers should compute correctly using iterative implementation") {
  positiveTest(Factorial::iterative);
}

TEST_CASE("Factorial of positive integers should compute correctly using recursive implementation") {
  positiveTest(Factorial::recursive);
}

TEST_CASE("Factorial of positive integers should compute correctly using tail recursive implementation") {
  positiveTest(Factorial::tailRecursive);
}

static void negativeTest(mpz_class f(mpz_class const)) {
	for (auto const & i: negativeData) { REQUIRE_THROWS_AS(f(i), std::invalid_argument); }
}

TEST_CASE("Factorial iterative should throw exception on negative argument") {
  negativeTest(Factorial::iterative);
}

TEST_CASE("Factorial iterative should throw exception on recursive argument") {
  negativeTest(Factorial::recursive);
}

TEST_CASE("Factorial iterative should throw exception on tailRecursive argument") {
  negativeTest(Factorial::tailRecursive);
}
