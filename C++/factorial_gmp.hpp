#include <gmpxx.h>

namespace Factorial {

mpz_class iterative(mpz_class const n);
mpz_class iterative(long const n);
mpz_class reductive(mpz_class const n);
mpz_class reductive(long const n);
mpz_class recursive(mpz_class const n);
mpz_class recursive(long const n);
mpz_class tailRecursive(mpz_class const n);
mpz_class tailRecursive(long const n);

} // namespace Factorial
