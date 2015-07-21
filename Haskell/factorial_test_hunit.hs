module Main where

import Test.HUnit

import Factorial

testData = [
        (0, 1),
        (1, 1),
        (2, 2),
        (3, 6),
        (4, 24),
        (5, 120),
        (6, 720),
        (7, 5040),
        (8, 40320),
        (9, 362880),
        (10, 3628800),
        (11, 39916800),
        (12, 479001600),
        (13, 6227020800),
        (14, 87178291200),
        (20, 2432902008176640000),
        (30, 265252859812191058636308480000000),
        (40, 815915283247897734345611269596115894272000000000)
       ]

testCorrect function comment = test [comment ++ " " ++ show i ~: "" ~: expected ~=? function i | (i, expected) <- testData]
--testCorrect function comment = TestList [TestLabel (comment ++ " " ++ show i)  (TestCase (assertEqual "" expected (function i))) | (i, expected) <- testData]

testNegative function comment = test [comment ++ " " ++ show i ~: "" ~: 0 ~=? function i  | i <- [-20..(-1)]]
--testNegative function comment = TestList [TestLabel (comment ++ " " ++ show i) (TestCase (assertEqual "" 0 (function i))) | i <- [-20..(-1)]]

main = do
 runTestTT (testCorrect Factorial.iterative "Iterative")
 runTestTT (testCorrect Factorial.naïveRecursive "Naïve Recursive")
 runTestTT (testCorrect Factorial.tailRecursive "Tail Recursive")
 runTestTT (testNegative Factorial.iterative "Iterative")
 runTestTT (testNegative Factorial.naïveRecursive "Naïve Recursive")
 runTestTT (testNegative Factorial.tailRecursive "Tail Recursive")
