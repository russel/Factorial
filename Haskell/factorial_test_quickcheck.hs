module Main where

import Numeric.Natural
import Test.QuickCheck

import Factorial

f_p :: (Integer -> Integer) -> Integer -> Bool
f_p f n
    | n == 0 = f n == 1
    | otherwise = f n == n * f (n - 1)

factorial_property :: (Integer -> Integer) -> Natural -> Bool
factorial_property f n = f_p f (fromIntegral n)

main :: IO()
main = do
  quickCheck (factorial_property iterative)
  quickCheck (factorial_property naïveRecursive)
  quickCheck (factorial_property tailRecursive)
