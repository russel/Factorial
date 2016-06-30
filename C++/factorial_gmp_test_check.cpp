#define CATCH_CONFIG_MAIN
#include "catch.hpp"

#include "factorial_gmp.hpp"

#include <vector>
#include <utility>

std::vector<mpz_class (*)(long const)> const algorithms {
	Factorial::iterative,
	Factorial::reductive,
	Factorial::naive_recursive,
	Factorial::tail_recursive
};

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

TEST_CASE("Positive arguments succeed.") {
	for (auto && f: algorithms) {
		for (auto  && p: positiveData) {
			REQUIRE(f(p.first) == mpz_class(p.second) );
		}
	}
}

TEST_CASE("Negative arguments throw exceptions.") {
	for (auto && f: algorithms) {
		for (auto && i: negativeData) {
			REQUIRE_THROWS_AS(f(i), std::invalid_argument);
		}
	}
}

TEST_CASE("Iterative knows no bounds.") {
	Factorial::iterative(60000);
	REQUIRE(true);
}

TEST_CASE("Reductive knows no bounds.") {
	Factorial::reductive(60000);
	REQUIRE(true);
}

TEST_CASE("Recursive runs out of stack, eventually.") {
	Factorial::naive_recursive(50000); // 60000 causes bus error.
	REQUIRE(true);
}

TEST_CASE("Tail recursive runs out of stack, eventually.") {
	Factorial::tail_recursive(50000); // 60000 causes bus error.
	REQUIRE(true);
}
