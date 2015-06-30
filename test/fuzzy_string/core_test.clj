(ns fuzzy-string.core-test
  (:use clojure.test
        fuzzy-string.core))

(deftest bigram-test
  (testing "bigram"
    (let [strn "test"
        bgrm #{"te" "es" "st"}]
    (is (= bgrm (bigram strn))))))

(deftest dice-test-1
  (testing "dice - equal strings"
    (let [a "foo"
      b "foo"]
        (is (= (dice a b) 1)))))


(deftest dice-test-2
  (testing "dice - different strings"
    (let [a "bla"
      b "foo"]
        (is (= (dice a b) 0)))))

(deftest dice-test-3
  (testing "dice - overlapping strings"
    (let [a "bar"
      b "baz"]
        (is (= (dice a b) 2/4)))))

(deftest levenshtein-1
  (testing "levenshtein - same string"
    (let [a "foo"
      b "foo"]
        (is (= (levenshtein a b) 0)))))


(deftest levenshtein-2
  (testing "levenshtein - different strings"
    (let [a "bla"
      b "foo"]
        (is (= (levenshtein a b) 3)))))

(deftest levenshtein-2
  (testing "levenshtein - overlapping strings"
    (let [a "bar"
      b "baz"]
        (is (= (levenshtein a b) 1)))))
