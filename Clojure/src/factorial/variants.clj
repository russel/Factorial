(ns factorial.variants)

"
Various implementations of the factorial function:

f 0 = 1
f n = n . f (n - 1), n > 0

"

(defn- validate-argument [n]
  ; integer? delivers true for java.lang.Long and clojure.lang.BigInt, but not java.math.BigDecimal.
  (if (not (integer? n))
    (throw (IllegalArgumentException. (format "Argument must be integer, got: %s" (type n)))))
  (if (< n 0)
    (throw (IllegalArgumentException. (format "Argument must be a non-negative integer, got: %d" n)))))

(defn naïve [n]
  "Factorial via the naïve recursive algorithm,
  i.e. not tail recursive and so prone to stack overflow."
  (validate-argument n)
  (if (< n 2)
    1
    (*' n (naïve (dec n)))))

(defn looping [n]
  "Factorial via a tail recursive algorithm.
  The tail recursion is to an \"inner function\" preserving the
   signature of the exposed function."
  (validate-argument n)
  (if (< n 2)
    1
    (loop [
           i n
           result n]
      (if (<= i 1)
        result
        (recur (dec i) (*' result (dec i)))))))

(defn pattern-match
  "Factorial via a tail recursive algorithm but using pattern
   matching on the signature of the exposed function which
    can therefore lead to misuse."
  ([n acc]
   (validate-argument n)
   (if (< n 2)
     acc
     (recur (dec n) (*' acc n))))
  ([n]
   (validate-argument n)
   (pattern-match n 1N)))

(defn reducing [n]
  "Factorial via the built-in reduce function."
  (validate-argument n)
  (reduce *' (range 1 (inc n))))

(defn apply-range [n]
  "Factorial via use of the built-in range and apply functions."
  (validate-argument n)
  (apply *' (range 1 (inc  n))))

(defn apply-iterate [n]
  "Factorial via use of the built-in iterate and apply functions."
  (validate-argument n)
  (apply *' (take n (iterate inc 1))))