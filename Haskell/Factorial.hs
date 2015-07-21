{-|
Module      : Factorial
Description : Various implementation of the factorial function.
Copyright   : © Russel Winder  2015
License     : GPLv3
Maintainer  : russel@winder.org.uk
Stability   :  stable
Portability :  portable

This module contains various implementations of the factorial function to show various different Haskell
techniques and approaches.

Factorial is undefined for negative integers, but rather than use error or Maybe, use the return value 0.
-}
module Factorial(iterative, naïveRecursive, tailRecursive) where

-- | Factorial implemented using the product built-in function.
iterative :: Integer -> Integer
iterative n
    | n < 0 = 0
    | n >= 0 = product [1..n]

-- | Factorial implemented using a naïve recursive approach.
naïveRecursive :: Integer -> Integer
naïveRecursive n
    | n < 0 = 0
    | n == 0 = 1
    | n > 0 = n * naïveRecursive (n - 1)

-- | Factorial implemented using a classic tail recursive function approach.
tailRecursive :: Integer -> Integer
-- NB iterate is a function in the standard prelude, so we cannot use that name.
tailRecursive n
    | n < 0 = 0
    | n >= 0 = iteration 1  n  1
    where
      iteration n max result = if n > max then result else iteration (n + 1)  max (result * n)
