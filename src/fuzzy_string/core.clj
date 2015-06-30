(ns fuzzy-string.core
  (:use [clojure set]))

(defn bigrams 
  "create bigrams out of a string"
  [^String strn]
  (loop [os strn rs #{}]
    (if (<= (.length os) 2)
      (conj rs os)
      (recur (subs os 1)
             (conj rs (subs os 0 2))))))

(def bigram (memoize bigrams))

(defn dice 
  "The dice comparison, takes two strings - compares bigrams
   The dice algorithm always returns a value between 0 and 1"
  [a b]
  (let [a (bigram a)
        b (bigram b)]
    (/ (* 2 (count (intersection a b)))
       (+ (count a) (count b)))))

(declare levenshtein)

(defn levenshtein-raw
  "calculates the levenshtein distane between two strings"
  [a b]
  (let 
      [len-a (count a)
       len-b (count b)
       cost (if (= (first a) (first b)) 0 1)]
    (if (or (= len-a 0) (= len-b 0)) 
      (+ len-a len-b)
      (min
       (+ (levenshtein (rest a) b) 1)
       (+ (levenshtein a (rest b)) 1)
       (+ (levenshtein (rest a) (rest b)) cost)))))

(def levenshtein (memoize levenshtein-raw))
