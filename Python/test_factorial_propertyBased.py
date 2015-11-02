#! /usr/bin/env python3
# -*- coding:utf-8; -*-

from pytest import mark, raises

from hypothesis import assume, given
from hypothesis.strategies import integers, floats

from factorial import iterative, recursive, tailRecursive, usingReduce

'''
Example-based tests, using pytest, for the various factorial implementations.
'''

__author__ = 'Russel Winder'
__date__ = '2015-11-01'
__version__ = '1.1'
__copyright__ = 'Copyright © 2015  Russel Winder'
__licence__ = 'GNU Public Licence (GPL) v3'

algorithms = (iterative, usingReduce, recursive, tailRecursive)


# Restrict the range of inputs to avoid excessive time in test. To avoid problems with the recursive
# implementations, ensure i is definitely less that about 950.
@mark.parametrize('a', algorithms)
@given(integers(min_value=0, max_value=900))
def test_correct_iterative_behaviour_with_non_negative_integers(a, i):
    assert a(i + 1) == (i + 1) * a(i)


@mark.parametrize('a', algorithms)
@given(integers())
def test_negative_integers_cause_exception(a, i):
    assume(i < 0)
    with raises(ValueError):
        a(i)


@mark.parametrize('a', algorithms)
@given(floats())
def test_all_floats_cause_exception(a, x):
    with raises(TypeError):
        a(x)


if __name__ == '__main__':
    from pytest import main
    main()
