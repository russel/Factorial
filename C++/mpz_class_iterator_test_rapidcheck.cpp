/**
 * @file
 *
 * This file contains [RapidCheck](https://github.com/emil-e/rapidcheck) tests for
 * the [mpz_class_iterator](@ref mpz_class_iterator) class defined in the
 * `factorial.cpp` file.
 *
 * @copyright © 2016 Russel Winder
 * @author Russel Winder (<russel@winder.org.uk>)
 */

#include "rapidcheck.h"

#include "factorial.cpp"

int main() {
	using namespace Factorial;

	rc::check("value of operator delivers the right value", [](int i) {
			RC_ASSERT(*mpz_class_iterator{i} == i);
		});

	rc::check("pointer operator delivers the right value", [](int i) {
			RC_ASSERT(mpz_class_iterator{i}->get_si() == i);
		});

	rc::check("equality is value not identity.", [](int i) {
			RC_ASSERT(mpz_class_iterator{i} == mpz_class_iterator{i});
		});

	rc::check("inequality is value not identity.", [](int i, int j) {
			RC_PRE(j != 0);
			RC_ASSERT(mpz_class_iterator{i} != mpz_class_iterator{i + j});
		});

	rc::check("preincrement does in fact increment", [](int i) {
			RC_ASSERT(++mpz_class_iterator{i} == mpz_class_iterator{i + 1});
		});

	rc::check("postincrement does in fact increment", [](int i) {
			RC_ASSERT(mpz_class_iterator{i}++ == mpz_class_iterator{i});
		});

	rc::check("value of preincrement returns correct value",  [](int i) {
			RC_ASSERT(*++mpz_class_iterator{i} == i + 1);
	});

	rc::check("value of postincrement returns correct value",  [](int i) {
			RC_ASSERT(*mpz_class_iterator{i}++ == i);
	});

}
