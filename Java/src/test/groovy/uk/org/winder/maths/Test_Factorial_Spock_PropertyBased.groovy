package uk.org.winder.maths

import spock.lang.Specification
import spock.lang.Unroll

import spock.genesis.Gen

class Test_Factorial_Spock_PropertyBased extends Specification {

    private static final algorithms = [
            [Factorial.&iterative, 'iterative'],
            [Factorial.&reductive, 'reductive'],
            [Factorial.&reductive, 'reductive_alt'],
            [Factorial.&naïveRecursive, 'naïveRecursive'],
            [Factorial.&tailRecursive, 'tailRecursive'],
    ]

    @Unroll
    def '#name(#n) obeys the factorial relation for positive integer arguments'() {
        expect:
        f.call(n + 1) == (n + 1) * f.call(n)
        where:
        [f, name, n] << algorithms.collectMany { a -> Gen.integer(0, 1000).take(20).collect { [*a, it] } }
    }

    @Unroll
    def '#name(#n) throws an exception due to negative integer argument'() {
        when:
        f.call(n)
        then:
        thrown(IllegalArgumentException)
        where:
        [f, name, n] << algorithms.collectMany { a -> Gen.integer(-1000, -1).take(10).collect { [*a, it] } }
    }

    @Unroll
    def '#name(#n) throws an exception due to double argument'() {
        when:
        f.call(n)
        then:
        thrown(MissingMethodException)
        where:
        [f, name, n] << algorithms.collectMany { a -> Gen.integer(-1000, 1000).take(10).collect { [*a, it + 0.5] } }
    }

}
