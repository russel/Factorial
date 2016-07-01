#include "rapidcheck.h"

#include <string>
#include <utility>

#include "factorial.hpp"

std::vector<std::pair<mpz_class (*)(long const), std::string>> const algorithms {
	{Factorial::iterative, "iterative"},
	{Factorial::reductive, "reductive"},
	{Factorial::naive_recursive, "naïve recursive"},
	{Factorial::tail_recursive, "tail recursive"}
};

int main() {
	for (auto && a: algorithms) {
		auto f = a.first;

		rc::check(a.second + " applied to non-negative integer argument obeys the recurrence relation.", [f]() {
				// Cannot use auto generated integers as the numbers just get too big and computations too long.
				auto i = *rc::gen::inRange(0, 900);
				RC_ASSERT(f(i) == ((i == 0) ? mpz_class(1) : i * f(i - 1)));
			});

		rc::check(a.second + " applied to negative integer raises an exception.", [f]() {
				auto i = *rc::gen::inRange(-100000, -1);
				RC_ASSERT_THROWS_AS(f(i), std::invalid_argument);
			});

	}
	return 0;
}
