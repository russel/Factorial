#include <check.h>

#include "factorial_gmp.h"

char * positiveData[][2] = {
  {"0", "1"},
  {"1", "1"},
  {"2", "2"},
  {"3", "6"},
  {"4", "24"},
  {"5", "120"},
  {"6", "720"},
  {"7", "5040"},
  {"8", "40320"},
  {"9", "362880"},
  {"10", "3628800"},
  {"11", "39916800"},
  {"12", "479001600"},
  {"13", "6227020800"},
  {"14", "87178291200"},
 
};

int positiveDataCount = 18;

START_TEST(correctIterative) {
  int i;
  for (i = 0; i < positiveDataCount; ++i) {
    mpz_t datum, expected, result;
    mpz_init_set_str(datum, positiveData[i][0], 10);
    mpz_init_set_str(expected, positiveData[i][1], 10);
    factorial_iterative(result, datum);
    fail_unless(mpz_cmp(result, expected) == 0, "iterative");
  }
} END_TEST

START_TEST(correctRecursive) {
  int i;
  for (i = 0; i < positiveDataCount; ++i) {
    mpz_t datum, expected, result;
    mpz_init_set_str(datum, positiveData[i][0], 10);
    mpz_init_set_str(expected, positiveData[i][1], 10);
    factorial_recursive(result, datum);
    fail_unless(mpz_cmp(result, expected) == 0, "recursive");
  }
} END_TEST

START_TEST(correctTailRecursive) {
  int i;
  for (i = 0; i < positiveDataCount; ++i) {
    mpz_t datum, expected, result;
    mpz_init_set_str(datum, positiveData[i][0], 10);
    mpz_init_set_str(expected, positiveData[i][1], 10);
    factorial_tailRecursive(result, datum);
    fail_unless(mpz_cmp(result, expected) == 0, "tailRecursive");
  }
} END_TEST

Suite * testSuite() {
  Suite * suite = suite_create("Factorial");
  TCase * tests = tcase_create("Tests");
  tcase_add_test(tests, correctIterative);
  tcase_add_test(tests, correctRecursive);
  tcase_add_test(tests, correctTailRecursive);
  suite_add_tcase(suite, tests);
  return suite;
}

int main(void) {
  int numberFailed;
  Suite * suite = testSuite();
  SRunner * runner = srunner_create(suite);
  srunner_run_all(runner, CK_NORMAL);
  numberFailed = srunner_ntests_failed(runner);
  srunner_free(runner);
  return numberFailed;
}
