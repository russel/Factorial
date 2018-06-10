use "ponytest"

primitive GlobalData

        fun algorithms(): Array[({(I128): I128} val, String)] => [
                ({(n: I128): I128 => Factorial.iterative(n)}, "iterative")
                ({(n: I128): I128 => Factorial.naive_recursive(n)}, "naive_recursive")
                ({(n: I128): I128 => Factorial.tail_recursive(n)}, "tail_recursive")
        ]

        fun positive_data(): Array[(I128, I128)] => [
                (0, 1)
                (1, 1)
                (2, 2)
                (3, 6)
                (4, 24)
                (5, 120)
                (6, 720)
                (7, 5040)
                (8, 40320)
                (9, 362880)
                (10, 3628800)
                (11, 39916800)
                (12, 479001600)
                (13, 6227020800)
                (14, 87178291200)
                (20, 2432902008176640000)
                (30, 265252859812191058636308480000000)
                // Value too big  (40, 815915283247897734345611269596115894272000000000)
        ]

        fun negative_data(): Array[I128] => [-1 -2 -5 -10 -20 -100]


actor Main is TestList

        new create(env: Env) =>
                PonyTest(env, this)

        new make() =>
                None

        fun tag tests(test: PonyTest) =>
                test(_TestPositive)
                test(_TestNegative)


class iso _TestPositive is UnitTest

        fun name(): String => "positive"

        fun apply(h: TestHelper) =>
                for f in GlobalData.algorithms().values() do
                        for data in GlobalData.positive_data().values() do
                                h.assert_eq[I128](data._2, f._1(data._1), name() + ": " + f._2)
                        end
                end


class iso _TestNegative is UnitTest

        fun name(): String => "negative"

        fun apply(h: TestHelper) =>
                for f in GlobalData.algorithms().values() do
                        for datum in GlobalData.negative_data().values() do
                                h.assert_eq[I128](0, f._1(datum), name() + ": " + f._2)
                        end
                end
