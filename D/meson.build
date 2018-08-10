project('D Factorial', 'd')

run_command('sh', '-c', 'cd $MESON_SOURCE_ROOT && LD_LIBRARY_PATH=$HOME/BuildArea/LDC/lib:$HOME/Built/lib gen-ut-main -f $MESON_BUILD_ROOT/ut_main.d source')

factorial_test = executable('factorial_test',
                            ['source/factorial.d', meson.current_build_dir() + '/ut_main.d'],
                            dependencies: [dependency('unit-threaded')],
                            d_args: ['-unittest'],
                            include_directories: [include_directories('source')],
                            link_args: ['-link-defaultlib-shared'],
                           )

test('test_factorial', factorial_test)
