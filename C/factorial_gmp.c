#include "factorial_gmp.h"

void factorial_iterative(mpz_t result, mpz_t const n) {
  mpz_t i;
  mpz_init_set_ui(result, 1);
  mpz_init_set_ui(i, 2);
  while (mpz_cmp(i, n) <= 0) {
    mpz_mul(result, result, i);
    mpz_add_ui(i, i, 1);
  }
}

void factorial_recursive(mpz_t result, mpz_t const n) {
  if (mpz_cmp_ui(n, 1) <= 0) { mpz_init_set_ui(result, 1); }
  else {
    mpz_t i;
    mpz_init_set(i, n);
    mpz_sub_ui(i, i, 1);
    factorial_recursive(i, i);
    mpz_mul(result, n, i);
	}
}

/*
 * factorial_tailRecursive_iterate is an internal support function which has no units tests of its own. It
 * is only tested because factorial_tailRecursive is.
 */

static void factorial_tailRecursive_iterate(mpz_t result, mpz_t n) {
  if (mpz_cmp_ui(n, 1) <= 0) { return; }
  else {
    mpz_mul(result, result, n);
    mpz_sub_ui(n, n, 1);
    factorial_tailRecursive_iterate(result, n);
  }
}

void factorial_tailRecursive(mpz_t result, mpz_t const n) {
  if (mpz_cmp_ui(n, 1) <= 0) { mpz_init_set_ui(result, 1); }
  else {
    mpz_t x;
    mpz_init_set_ui(result, 1);
		mpz_init_set(x, n);
    factorial_tailRecursive_iterate(result, x);
  }
}
