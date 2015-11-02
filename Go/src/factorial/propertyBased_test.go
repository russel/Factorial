package factorial

import (
	"math/big"
	"math/rand"
	"reflect"
	"testing"
	"testing/quick"
)

func checkCorrectUintValues(t *testing.T, function func(uint) *big.Int) {
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

func Test_check_iterative_uint(t *testing.T) {
	checkCorrectUintValues(t, Iterative_uint)
}

func Test_check_recursive_uint(t *testing.T) {
	checkCorrectUintValues(t, Recursive_uint)
}

func Test_check_tailRecursive_uint(t *testing.T) {
	checkCorrectUintValues(t, TailRecursive_uint)
}

func checkCorrectBigIntValues(t *testing.T, function func(*big.Int) *big.Int) {
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

func Test_check_iterative_BigInt(t *testing.T) {
	checkCorrectBigIntValues(t, Iterative_bigInt)
}

func Test_check_recursive_BigInt(t *testing.T) {
	checkCorrectBigIntValues(t, Recursive_bigInt)
}

func Test_check_tailRecursive_BigInt(t *testing.T) {
	checkCorrectBigIntValues(t, TailRecursive_bigInt)
}
