module Main where

import Test.QuickCheck

import Factorial

prop_relation :: (Integer -> Integer) -> Integer -> Bool
prop_relation f n
    | n < 0 = f n == 0
    | n == 0 = f n == 1
    | otherwise = f n == n * f (n - 1)

main :: IO()
main = do
  quickCheck (prop_relation iterative)
  quickCheck (prop_relation naïveRecursive)
  quickCheck (prop_relation tailRecursive)
