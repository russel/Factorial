package factorial

import "math/big"

func Iterative_uint(n uint) (total *big.Int) {
	total = big.NewInt(1)
	for i := uint(2); i <= n; i++ {
		total.Mul(total, big.NewInt(int64(i)))
	}
	return
}

func Iterative_bigInt(n *big.Int) (total *big.Int) {
	total = big.NewInt(1)
	for i := new(big.Int).Set(n); i.Cmp(bigOne) > 0; i.Sub(i, bigOne) {
		total.Mul(total, i)
	}
	return
}

func Recursive_uint(n uint) *big.Int {
	return Recursive_bigInt(big.NewInt(int64(n)))
}

func Recursive_bigInt(n *big.Int) (value *big.Int) {
	value = big.NewInt(1)
	if n.Cmp(bigOne) > 0 {
		value.Mul(n, Recursive_bigInt(new(big.Int).Sub(n, bigOne)))
	}
	return
}

func TailRecursive_uint(n uint) *big.Int {
	return TailRecursive_bigInt(big.NewInt(int64(n)))
}

func tailRecursive_bigInt_iterate(n, result *big.Int) *big.Int {
	if n.Cmp(bigTwo) < 0 {
		return result
	}
	return tailRecursive_bigInt_iterate(new(big.Int).Sub(n, bigOne), new(big.Int).Mul(result, n))
}

func TailRecursive_bigInt(n *big.Int) *big.Int {
	value := big.NewInt(1)
	if n.Cmp(bigOne) > 0 {
		return tailRecursive_bigInt_iterate(new(big.Int).Set(n), value)
	}
	return value
}
