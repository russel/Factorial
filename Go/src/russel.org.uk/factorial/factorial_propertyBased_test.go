package factorial

import (
	"math/big"
	"math/rand"
	"reflect"
	"testing"
	"testing/quick"
)

func checkCorrectFactorialUintValues(t *testing.T, function func(uint) *big.Int) {
	property := func(i uint) bool {
		v := big.NewInt(int64(i + 1))
		return function(i+1).Cmp(v.Mul(v, function(i))) == 0
	}
	values := func(slice []reflect.Value, rand *rand.Rand) {
		for i := 0; i < len(slice); i++ {
			slice[i] = reflect.ValueOf(uint(rand.Int63n(1000)))
		}
	}
	if err := quick.Check(property, &quick.Config{100, 0.0, nil, values}); err != nil {
		t.Error(err)
	}
}

func Test_check_factorial_iterative_uint(t *testing.T) {
	checkCorrectFactorialUintValues(t, Factorial_iterative_uint)
}

func Test_check_factorial_recursive_uint(t *testing.T) {
	checkCorrectFactorialUintValues(t, Factorial_recursive_uint)
}

func Test_check_factorial_tailRecursive_uint(t *testing.T) {
	checkCorrectFactorialUintValues(t, Factorial_tailRecursive_uint)
}

func checkCorrectFactorialBigIntValues(t *testing.T, function func(*big.Int) *big.Int) {
	property := func(i *big.Int) bool {
		ip1 := big.NewInt(1)
		ip1.Add(i, ip1)
		v := function(i)
		v.Mul(ip1, v)
		return function(ip1).Cmp(v) == 0
	}
	values := func(slice []reflect.Value, rand *rand.Rand) {
		for i := 0; i < len(slice); i++ {
			slice[i] = reflect.ValueOf(big.NewInt(rand.Int63n(1000)))
		}
	}
	if err := quick.Check(property, &quick.Config{100, 0.0, nil, values}); err != nil {
		t.Error(err)
	}
}

func Test_check_factorial_iterative_BigInt(t *testing.T) {
	checkCorrectFactorialBigIntValues(t, Factorial_iterative_bigInt)
}

func Test_check_factorial_recursive_BigInt(t *testing.T) {
	checkCorrectFactorialBigIntValues(t, Factorial_recursive_bigInt)
}

func Test_check_factorial_tailRecursive_BigInt(t *testing.T) {
	checkCorrectFactorialBigIntValues(t, Factorial_tailRecursive_bigInt)
}
