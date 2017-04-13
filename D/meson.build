project('D Factorial', 'd')

unit_threaded_version = '0.7.13'

home_directory_process = run_command('python3', ['-c',  'import os; print(os.environ["HOME"])'])
home_directory = home_directory_process.stdout().strip()

unit_threaded_directory = join_paths(home_directory, '.dub', 'packages', 'unit-threaded-' +  unit_threaded_version, 'unit-threaded')
unit_threaded_source_directory = join_paths(unit_threaded_directory, 'source')
unit_threaded_ldc_library_directory = join_paths(unit_threaded_directory, '.dub', 'build', 'library-release-linux.posix-x86_64-ldc_2071-B9968E589EC82218E5BC3814938C922A')
gen_ut_main = join_paths(unit_threaded_directory, 'gen_ut_main')

factorial_test = executable('factorial_test',
                            ['factorial.d', 'ut_main.d'],
                            d_args: ['-unittest', '-I' + unit_threaded_source_directory],
                            link_args: ['-L-L' + unit_threaded_ldc_library_directory, '-L-lunit-threaded'],
                           )

test('test_factorial', factorial_test)
