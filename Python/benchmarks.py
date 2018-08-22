"""
Benchmark the iterative factorial implementations to show there isn't much performance difference
between iterative and using_reduce.

Use pytest and the benchmark fixture. https://github.com/ionelmc/pytest-benchmark
"""

from pytest import mark

from factorial import iterative, using_reduce

__author__ = 'Russel Winder'
__date__ = '2018-08-22'
__version__ = '1.1'
__copyright__ = 'Copyright © 2016, 2018  Russel Winder'
__licence__ = 'GNU Public Licence (GPL) v3'


size: int = 10000


def test_iterative(benchmark):
    benchmark(iterative, size)


def test_using_reduce(benchmark):
    benchmark(using_reduce, size)
