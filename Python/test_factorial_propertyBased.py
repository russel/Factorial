#! /usr/bin/env python3

from pytest import mark, raises

from hypothesis import assume, given
from hypothesis.strategies import integers, floats, text

from factorial import iterative, recursive, tail_recursive, using_reduce

'''
Property-based tests, using Hypothesis and pytest, for the various factorial implementations.
'''

__author__ = 'Russel Winder'
__date__ = '2016-06-30'
__version__ = '1.2.1'
__copyright__ = 'Copyright © 2015, 2016  Russel Winder'
__licence__ = 'GNU Public Licence (GPL) v3'

algorithms = (iterative, using_reduce, recursive, tail_recursive)


# To avoid problems (infinite recursion check) with the recursive implementations, ensure i is definitely
# less that about 950.
@mark.parametrize('a', algorithms)
@given(integers(min_value=0, max_value=900))
def test_recurrence_relation_holds_for_non_negative_integer_argument(a, x):
    assert a(x) == (1 if x == 0 else x * a(x - 1))


@mark.parametrize('a', algorithms)
@given(integers(max_value=-1))
def test_negative_integer_argument_causes_ValueError(a, x):
    with raises(ValueError):
        a(x)


@mark.parametrize('a', algorithms)
@given(floats())
def test_float_argument_causes_TypeError(a, x):
    with raises(TypeError):
        a(x)


@mark.parametrize('a', algorithms)
def test_none_argument_causes_TypeError(a):
    with raises(TypeError):
        a(None)


@mark.parametrize('a', algorithms)
@given(text())
def test_string_argument_causes_TypeError(a, x):
    with raises(TypeError):
        a(x)


if __name__ == '__main__':
    from pytest import main
    main()
