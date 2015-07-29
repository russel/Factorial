(ns factorial.properties
  (:require
    [clojure.test.check.clojure-test :as check]
    [clojure.test.check.generators :as gen]
    [clojure.test.check.properties :as prop]
    [com.gfredericks.test.chuck.generators :as gen']
    [factorial.variants :refer :all]))

(defn- run-positive-check [f]
  (prop/for-all [n gen/pos-int]
                (= (f (inc n)) (*' (inc n) (f n)))))

(defn- run-negative-check [f]
  (prop/for-all [n gen/neg-int]
                (try
                  (f (dec n))                               ;  neg-int generates 0 – WTF
                  false
                  (catch IllegalArgumentException e true))))

(defn- run-real-check [f]
  (prop/for-all [n gen'/double]
                (try
                  (f n)
                  false
                  (catch IllegalArgumentException e true))))

(defn- run-checks [f]
  (run-positive-check f)
  (run-negative-check f)
  (run-real-check f)
  )

(check/defspec naïve-check 100 (run-checks naïve))

(check/defspec looping-check 100 (run-checks looping))

(check/defspec pattern-match-check 100 (run-checks pattern-match))

(check/defspec reducing-check 100 (run-checks reducing))

(check/defspec apply-range-check 100 (run-checks apply-range))

(check/defspec apply-iterate-check 100 (run-checks apply-iterate))
