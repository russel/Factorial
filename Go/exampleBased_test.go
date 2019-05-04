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

func TestUintParameters(t *testing.T) {
	for _, algorithm := range algorithms_uint {
		for parameter, expectedString := range test_data {
			expected, success := new(big.Int).SetString(expectedString, 0)
			if !success {
				t.Errorf("Failed to create the expected %s value.\n", expectedString)
			}
			assert.Equal(t, expected, algorithm(parameter))
		}
	}
}

func TestBigIntValues(t *testing.T) {
	for _, algorithm := range algorithms_bigInt {
		for parameter, expectedString := range test_data {
			expected, success := new(big.Int).SetString(expectedString, 0)
			if !success {
				t.Errorf("Failed to create the expected %s value.\n", expectedString)
			}
			assert.Equal(t, expected, algorithm(big.NewInt(int64(parameter))))
		}
	}
}
