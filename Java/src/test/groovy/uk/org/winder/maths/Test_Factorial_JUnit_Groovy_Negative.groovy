package uk.org.winder.maths

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized.class)
final class Test_Factorial_JUnit_Groovy_Negative {
    static final algorithms = [
            [Factorial.&iterative, 'iterative'],
            [Factorial.&reductive, 'reductive'],
            [Factorial.&reductive_alt, 'reductive_alt'],
            [Factorial.&naïveRecursive, 'naïveRecursive'],
            [Factorial.&tailRecursive, 'tailRecursive'],
    ]

    private static final values = [-1, -2, -5, -10, -20, -100]

    @Parameters(name = "{1}({2})")
    static Collection<Object[]> data() {
        return algorithms.collectMany { a -> values.collect { [*a, it] as Object[] } }
    }

    private final Closure a
    private final String name
    private final Integer n

    Test_Factorial_JUnit_Groovy_Negative(final Closure a, final String name, final Integer n) {
        this.a = a
        this.name = name
        this.n = n
    }

    @Test(expected = RuntimeException.class)
    void test() { a(n) }

}
