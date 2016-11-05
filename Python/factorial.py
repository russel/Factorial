'''
A collection of implementations of factorial, which is defined by the recurrence relation:

    f_0 = 1
    f_n = n f_{n-1}, n > 0

The tail recursive implementation is only of limited usefulness since Python does not support tail recursion
optimization – so each tail recursive call uses a new stack frame just as the recursive implementation does
and the stack size is strictly limited.
'''

from functools import reduce
from operator import mul

__author__ = 'Russel Winder'
__date__ = '2016-06-28'
__version__ = '1.7.1'
__copyright__ = 'Copyright © 2007, 2011–2013, 2015, 2016  Russel Winder'
__licence__ = 'GNU Public Licence (GPL) v3'


def _validate(x: int) -> None:
    '''
    Check that the value x really is an integer, raising TypeError if it is not.
    Check that the integer is non-negative, raising ValueError if it is not.
    '''
    if not isinstance(x, int):
        raise TypeError('Argument must be an integer.')
    if x < 0:
        raise ValueError('Argument must be a non-negative integer.')


def iterative(x: int) -> int:
    '''Iterative implementation using a simple loop.'''
    _validate(x)
    total = 1
    for i in range(2, x + 1):
        total *= i
    return total


def recursive(x: int) -> int:
    '''Naïve recursive implementation.  Cannot calculate beyond the recursion depth.'''
    _validate(x)
    return 1 if x < 2 else x * recursive(x - 1)


def tail_recursive(x: int) -> int:
    '''
    A tail recursive implementation.  Python doesn't do tail call optimization
    so this suffers the same recursion depth problem as any recursive function.
    '''
    _validate(x)

    def iterate(i: int, result: int=1) -> int:
        return result if i < 2 else iterate(i - 1, result * i)

    return iterate(x)


def using_reduce(x: int) -> int:
    '''Implementation using the reduce function.'''
    _validate(x)
    return reduce(mul, range(2, x + 1), 1)
