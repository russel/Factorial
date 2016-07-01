cmake_minimum_required(VERSION 3.5)
project(Factorial_C++)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++14 -I$ENV{HOME}/include")
set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -L$ENV{HOME}/Built/lib")

add_executable(factorial_test_catch
    factorial.cpp
    factorial_test_catch.cpp)
target_link_libraries(factorial_test_catch gmpxx gmp)

add_executable(factorial_test_rapidcheck
    factorial.cpp
    factorial_test_rapidcheck.cpp)
target_link_libraries(factorial_test_rapidcheck rapidcheck gmpxx gmp)

add_executable(mpz_class_iterator_test_rapidcheck
    mpz_class_iterator_test_rapidcheck.cpp)
target_link_libraries(mpz_class_iterator_test_rapidcheck rapidcheck gmpxx gmp)
