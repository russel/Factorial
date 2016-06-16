# -*- coding:utf-8; -*-

from functools import reduce
from operator import mul

'''
A collection of implementations of factorial, which is defined by the recurrence relation:
    f 0 = 1
    f n = n . f (n - 1), n > 0

The tail recursive implementation is only of limited usefulness since Python does not support tail recursion
optimization – so each tail recursive call uses a new stack frame just as the recursive implementation does
and the stack size is strictly limited.
'''

__author__ = 'Russel Winder'
__date__ = '2016-06-16'
__version__ = '1.6.0'
__copyright__ = 'Copyright © 2007, 2011–2013, 2015, 2016  Russel Winder'
__licence__ = 'GNU Public Licence (GPL) v3'


def _validate(x):
    if not isinstance(x, int):
        raise TypeError('Argument must be an integer.')
    if x < 0:
        raise ValueError('Argument must be a non-negative integer.')


def iterative(x):
    '''Iterative implementation using a simple loop.'''
    _validate(x)
    if x < 2:
        return 1
    total = 1
    for i in range(2, x + 1):
        total *= i
    return total


def recursive(x):
    '''Naïve recursive implementation.  Cannot calculate beyond the recursion depth.'''
    _validate(x)
    return 1 if x < 2 else x * recursive(x - 1)


def tail_recursive(x):
    '''
    A tail recursive implementation.  Python doesn't do tail call optimization
    so this suffers the same recursion depth problem as any recursive function.
    '''
    _validate(x)
    if x < 2:
        return 1
    else:
        def iterate(i, result=1):
            return result if i < 2 else iterate(i - 1, result * i)
        return iterate(x)


def using_reduce(x):
    '''Implementation using the reduce function.'''
    _validate(x)
    if x < 2:
        return 1
    return reduce(mul, range(2, x + 1))
