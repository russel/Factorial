module Main where

import Test.QuickCheck

import Factorial

factorial_property :: (Integer -> Integer) -> Integer -> Bool
factorial_property f n
    | n < 0 = f n == 0
    | n == 0 = f n == 1
    | otherwise = f n == n * f (n - 1)

main :: IO()
main = do
  quickCheck (factorial_property iterative)
  quickCheck (factorial_property naïveRecursive)
  quickCheck (factorial_property tailRecursive)
