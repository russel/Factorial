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

Factorial is undefined for negative integers, raise an error for such uses. Whilst this seems a little
extreme, any program asking to evaluate factorial for a negative value is broken.
-}
module Factorial(iterative, naïveRecursive, tailRecursive) where

exceptionErrorMessage = "Factorial not defined for negative integers."

-- | Factorial implemented using the product built-in function.
iterative :: Integer -> Integer
iterative n
    | n < 0 = error exceptionErrorMessage
    | otherwise = product [1..n]

-- | Factorial implemented using a naïve recursive approach.
naïveRecursive :: Integer -> Integer
naïveRecursive n
    | n < 0 = error exceptionErrorMessage
    | n == 0 = 1
    | otherwise = n * naïveRecursive (n - 1)

-- | Factorial implemented using a classic tail recursive function approach.
tailRecursive :: Integer -> Integer
tailRecursive n
    | n < 0 = error exceptionErrorMessage
    | otherwise = iteration n 1
    where
      -- NB iterate is a function in the standard prelude, so we cannot use that name.
      iteration 0 result = result
      iteration i result = iteration (i - 1) (result * i)
