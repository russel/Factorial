project(
  'Factorial C++',
  'Cpp',
  default_options: ['cpp_std=c++2a', 'buildtype=release'],
)

compiler_flags = [
    '-W',
    '-Wall',
    '-Wundef',
    '-Wcast-align',
    '-Wno-unused-parameter',
    '-Wno-pmf-conversions',
]

# catch is available using Debian packages, rapidcheck is not. Must set up for finding
# rapidcheck.

local_built_directory = '/home/users/russel/Built/'
local_include_directory = local_built_directory + 'include'
local_lib_directory = local_built_directory + 'lib'

gmpxx_dependencies = [dependency('gmpxx'), dependency('gmp')]

factorial_test_check = executable(
  'factorial_test_check',
  ['factorial.cpp', 'factorial_test_catch.cpp'],
  cpp_args: compiler_flags,
  dependencies: gmpxx_dependencies,
)
test('factorial_test_check', factorial_test_check)

factorial_test_rapidcheck = executable(
  'factorial_test_rapidcheck',
  ['factorial.cpp', 'factorial_test_rapidcheck.cpp'],
  cpp_args: compiler_flags,
  dependencies: gmpxx_dependencies,
  include_directories: include_directories(local_include_directory),
  link_args: ['-L' + local_lib_directory, '-lrapidcheck'],
)
test('factorial_test_rapidcheck', factorial_test_rapidcheck)

mpz_class_iterator_test_rapidcheck = executable(
  'mpz_class_iterator_test_rapidcheck',
  'mpz_class_iterator_test_rapidcheck.cpp',
  cpp_args: compiler_flags,
  dependencies: gmpxx_dependencies,
  include_directories: include_directories(local_include_directory),
  link_args: ['-L' + local_lib_directory, '-lrapidcheck'],
)
test('factorial_test_rapidcheck', factorial_test_rapidcheck)
