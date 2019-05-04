package factorial

import (
	"math/big"
	"math/rand"
	"reflect"
	"testing"
	"testing/quick"
)

func Test_CheckUintValues(t *testing.T) {
	for _, algorithm := range algorithms_uint {
		property := func(i uint) bool {
			v := big.NewInt(int64(i + 1))
			return algorithm(i+1).Cmp(v.Mul(v, algorithm(i))) == 0
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
}

func Test_CheckBigIntValues(t *testing.T) {
	for _, algorithm := range algorithms_bigInt {
		property := func(i *big.Int) bool {
			ip1 := big.NewInt(1)
			ip1.Add(i, ip1)
			v := algorithm(i)
			v.Mul(ip1, v)
			return algorithm(ip1).Cmp(v) == 0
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
}
