-module(factorial).
-author('Russel Winder').
-export([recursive/1, tailRecursive/1]).

-ifdef(TEST).
-include_lib("eunit/include/eunit.hrl").
-endif.

recursive(0) -> 1;
recursive(N) when N > 0 -> N * recursive(N - 1);
recursive(N) when N < 0 -> erlang:error({factorialRecursiveNegativeArgument, N}).

tailRecursive(0) -> 1;
tailRecursive(N) when N > 0 -> iterate(1, N, 1);
tailRecursive(N) when N < 0 -> erlang:error({factorialTailRecursiveNegativeArgument, N}).

iterate(N, Max, Result) when N > Max ->  Result;
iterate(N, Max, Result) -> iterate(N + 1, Max, Result * N).

-ifdef(TEST).

positiveData () ->  [
                     {0, 1},
                     {1, 1},
                     {2, 2},
                     {3, 6},
                     {4, 24},
                     {5, 120},
                     {6, 720},
                     {7, 5040},
                     {8, 40320},
                     {9, 362880},
                     {10, 3628800},
                     {11, 39916800},
                     {12, 479001600},
                     {13, 6227020800},
                     {14, 87178291200},
                     {20, 2432902008176640000},
                     {30, 265252859812191058636308480000000},
                     {40, 815915283247897734345611269596115894272000000000}
                    ].

negativeData() -> [-1, -2, -5, -10, -20, -100].

postiiveArgumentRecursive_test_() -> [?_assertEqual(R, recursive(I)) || {I, R} <- positiveData()].
postiiveArgumentTailRecursive_test_() -> [?_assertEqual(R, tailRecursive(I)) || {I, R} <- positiveData()].

negativeArgumentRecursive_test_() -> [?_assertError({factorialRecursiveNegativeArgument, N}, recursive(N)) || N <- negativeData()].
negativeArgumentTailRecursive_test_() -> [?_assertError({factorialTailRecursiveNegativeArgument, N}, tailRecursive(N)) || N <- negativeData()].

-endif.
