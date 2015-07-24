(ns factorial.properties
  (:require
    [clojure.test.check.clojure-test :refer :all]           ; 'defspec fails here :-(
    [clojure.test.check.generators :as gen]
    [clojure.test.check.properties :as prop]
    [factorial.variants :refer :all]))

(defn- run-test [f]
  (prop/for-all [n gen/pos-int]
                (= (f (inc n)) (*' (inc n) (f n)))))

(defspec naïve-check 100 (run-test naïve))

(defspec looping-check 100 (run-test looping))

(defspec breakable-check 100 (run-test breakable))

(defspec reducing-check 100 (run-test reducing))

(defspec apply-range-check 100 (run-test apply-range))
