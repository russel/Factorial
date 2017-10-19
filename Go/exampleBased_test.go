package factorial

import (
	"github.com/stretchr/testify/assert"
	"math/big"
	"testing"
)

var test_data = map[uint]string{
	0:  "1",
	1:  "1",
	2:  "2",
	3:  "6",
	4:  "24",
	5:  "120",
	6:  "720",
	7:  "5040",
	8:  "40320",
	9:  "362880",
	10: "3628800",
	11: "39916800",
	12: "479001600",
	13: "6227020800",
	14: "87178291200",
	20: "2432902008176640000",
	30: "265252859812191058636308480000000",
	40: "815915283247897734345611269596115894272000000000",
}

func testCorrectUintValues(t *testing.T, function func(uint) *big.Int) {
	for parameter, expectedString := range test_data {
		expected, success := new(big.Int).SetString(expectedString, 0)
		if !success {
			t.Errorf("Failed to create the expected %s value.\n", expectedString)
		}
		assert.Equal(t, expected, function(parameter))
	}
}

func Test_iterative_uint(t *testing.T) {
	testCorrectUintValues(t, Iterative_uint)
}

func Test_recursive_uint(t *testing.T) {
	testCorrectUintValues(t, Recursive_uint)
}

func Test_tailRecursive_uint(t *testing.T) {
	testCorrectUintValues(t, TailRecursive_uint)
}

func testCorrectBigIntValues(t *testing.T, function func(*big.Int) *big.Int) {
	for parameter, expectedString := range test_data {
		expected, success := new(big.Int).SetString(expectedString, 0)
		if !success {
			t.Errorf("Failed to create the expected %s value.\n", expectedString)
		}
		assert.Equal(t, expected, function(big.NewInt(int64(parameter))))
	}
}

func Test_iterative_bigInt(t *testing.T) {
	testCorrectBigIntValues(t, Iterative_bigInt)
}

func Test_recursive_bigInt(t *testing.T) {
	testCorrectBigIntValues(t, Recursive_bigInt)
}

func Test_tailRecursive_bigInt(t *testing.T) {
	testCorrectBigIntValues(t, TailRecursive_bigInt)
}
