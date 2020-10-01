cmake_minimum_required(VERSION 3.5) # Is this a good minimum version?

project(Factorial_C++)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++20 -I$ENV{HOME}/Built/include")
set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -L$ENV{HOME}/Built/lib")

enable_testing()

add_executable(factorial_test_catch
  factorial.cpp
  factorial_test_catch.cpp
)
target_link_libraries(factorial_test_catch gmpxx gmp)
add_test(NAME factorial_test_catch COMMAND factorial_test_catch)

add_executable(factorial_test_rapidcheck
  factorial.cpp
  factorial_test_rapidcheck.cpp
)
target_link_libraries(factorial_test_rapidcheck rapidcheck gmpxx gmp)
add_test(NAME factorial_test_rapidcheck COMMAND factorial_test_rapidcheck)

add_executable(mpz_class_iterator_test_rapidcheck
  mpz_class_iterator_test_rapidcheck.cpp
)
target_link_libraries(mpz_class_iterator_test_rapidcheck rapidcheck gmpxx gmp)
add_test(NAME mpz_class_iterator_test_rapidcheck COMMAND mpz_class_iterator_test_rapidcheck)
