# -*- mode: prolog; -*-

factorial(N, R) :- factorial(N, R, 1).

factorial(0, R, R) :- !.
factorial(N, R, A) :-
    NN is N - 1,
    AA is A * N,
    factorial(NN, R, AA).
