project('D Factorial', 'd')

factorial_test = executable('factorial_test', 'factorial.d', d_args: ['-unittest'], link_args: ['--main'])
test('test_factorial', factorial_test)
