#! /usr/bin/env python3
# -*- coding:utf-8; -*-

from pytest import mark, raises

from hypothesis import assume, given
from hypothesis.strategies import integers, floats, text

from factorial import iterative, recursive, tail_recursive, using_reduce

'''
Property-based tests, using Hypothesis and pytest, for the various factorial implementations.
'''

__author__ = 'Russel Winder'
__date__ = '2016-06-16'
__version__ = '1.2.0'
__copyright__ = 'Copyright © 2015, 2016  Russel Winder'
__licence__ = 'GNU Public Licence (GPL) v3'

algorithms = (iterative, using_reduce, recursive, tail_recursive)


# Restrict the range of inputs to avoid excessive time in test: to avoid problems with the recursive
# implementations, ensure i is definitely less that about 950.
@mark.parametrize('a', algorithms)
@given(integers(min_value=0, max_value=900))
def test_correct_behaviour_with_non_negative_integer(a, x):
    assert a(x + 1) == (x + 1) * a(x)


@mark.parametrize('a', algorithms)
@given(integers(max_value=-1))
def test_negative_integer_causes_ValueError(a, x):
    with raises(ValueError):
        a(x)


@mark.parametrize('a', algorithms)
@given(floats())
def test_float_causes_TypeError(a, x):
    with raises(TypeError):
        a(x)


@mark.parametrize('a', algorithms)
def test_none_causes_TypeError(a):
    with raises(TypeError):
        a(None)


@mark.parametrize('a', algorithms)
@given(text())
def test_string_causes_TypeError(a, x):
    with raises(TypeError):
        a(x)


if __name__ == '__main__':
    from pytest import main
    main()
