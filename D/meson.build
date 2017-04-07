project('D Factorial', 'd')

unit_threaded_version = '0.7.11'

dub_directory = '/home/russel/.dub'
unit_threaded_directory = dub_directory + '/packages/unit-threaded-' +  unit_threaded_version + '/unit-threaded'
unit_threaded_source = unit_threaded_directory + '/source'
gen_ut_main = unit_threaded_directory + '/gen_ut_main'

factorial_test = executable('factorial_test',
                            ['factorial.d', 'ut_main.d'],
                            d_args: ['-unittest', '-I' + unit_threaded_source],
                            link_args: ['-L-L' + unit_threaded_directory, '-L-lunit-threaded'],
                           )

test('test_factorial', factorial_test)
