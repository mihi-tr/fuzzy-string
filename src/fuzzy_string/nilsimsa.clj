(ns fuzzy-string.nilsimsa
  )

"Implements the Nilsimsa similarity hash"

(def tran (mapv hash
                (concat
                 "\u0002\u00D6\u009E\u006F\u00F9\u001D\u0004\u00AB"
                 "\u00D0\u0022\u0016\u001F\u00D8\u0073\u00A1\u00AC"
                 "\u003B\u0070\u0062\u0096\u001E\u006E\u008F\u0039"
                 "\u009D\u0005\u0014\u004A\u00A6\u00BE\u00AE\u000E"
                 "\u00CF\u00B9\u009C\u009A\u00C7\u0068\u0013\u00E1"
                 "\u002D\u00A4\u00EB\u0051\u008D\u0064\u006B\u0050"
                 "\u0023\u0080\u0003\u0041\u00EC\u00BB\u0071\u00CC"
                 "\u007A\u0086\u007F\u0098\u00F2\u0036\u005E\u00EE"
                 "\u008E\u00CE\u004F\u00B8\u0032\u00B6\u005F\u0059"
                 "\u00DC\u001B\u0031\u004C\u007B\u00F0\u0063\u0001"
                 "\u006C\u00BA\u0007\u00E8\u0012\u0077\u0049\u003C"
                 "\u00DA\u0046\u00FE\u002F\u0079\u001C\u009B\u0030"
                 "\u00E3\u0000\u0006\u007E\u002E\u000F\u0038\u0033"
                 "\u0021\u00AD\u00A5\u0054\u00CA\u00A7\u0029\u00FC"
                 "\u005A\u0047\u0069\u007D\u00C5\u0095\u00B5\u00F4"
                 "\u000B\u0090\u00A3\u0081\u006D\u0025\u0055\u0035"
                 "\u00F5\u0075\u0074\u000A\u0026\u00BF\u0019\u005C"
                 "\u001A\u00C6\u00FF\u0099\u005D\u0084\u00AA\u0066"
                 "\u003E\u00AF\u0078\u00B3\u0020\u0043\u00C1\u00ED"
                 "\u0024\u00EA\u00E6\u003F\u0018\u00F3\u00A0\u0042"
                 "\u0057\u0008\u0053\u0060\u00C3\u00C0\u0083\u0040"
                 "\u0082\u00D7\u0009\u00BD\u0044\u002A\u0067\u00A8"
                 "\u0093\u00E0\u00C2\u0056\u009F\u00D9\u00DD\u0085"
                 "\u0015\u00B4\u008A\u0027\u0028\u0092\u0076\u00DE"
                 "\u00EF\u00F8\u00B2\u00B7\u00C9\u003D\u0045\u0094"
                 "\u004B\u0011\u000D\u0065\u00D5\u0034\u008B\u0091"
                 "\u000C\u00FA\u0087\u00E9\u007C\u005B\u00B1\u004D"
                 "\u00E5\u00D4\u00CB\u0010\u00A2\u0017\u0089\u00BC"
                 "\u00DB\u00B0\u00E2\u0097\u0088\u0052\u00F7\u0048"
                 "\u00D3\u0061\u002C\u003A\u002B\u00D1\u008C\u00FB"
                 "\u00F1\u00CD\u00E4\u006A\u00E7\u00A9\u00FD\u00C4"
                 "\u0037\u00C8\u00D2\u00F6\u00DF\u0058\u0072\u004E")))

(def popc (mapv hash
                (concat
                 "\u0000\u0001\u0001\u0002\u0001\u0002\u0002\u0003"
                 "\u0001\u0002\u0002\u0003\u0002\u0003\u0003\u0004"
                 "\u0001\u0002\u0002\u0003\u0002\u0003\u0003\u0004"
                 "\u0002\u0003\u0003\u0004\u0003\u0004\u0004\u0005"
                 "\u0001\u0002\u0002\u0003\u0002\u0003\u0003\u0004"
                 "\u0002\u0003\u0003\u0004\u0003\u0004\u0004\u0005"
                 "\u0002\u0003\u0003\u0004\u0003\u0004\u0004\u0005"
                 "\u0003\u0004\u0004\u0005\u0004\u0005\u0005\u0006"
                 "\u0001\u0002\u0002\u0003\u0002\u0003\u0003\u0004"
                 "\u0002\u0003\u0003\u0004\u0003\u0004\u0004\u0005"
                 "\u0002\u0003\u0003\u0004\u0003\u0004\u0004\u0005"
                 "\u0003\u0004\u0004\u0005\u0004\u0005\u0005\u0006"
                 "\u0002\u0003\u0003\u0004\u0003\u0004\u0004\u0005"
                 "\u0003\u0004\u0004\u0005\u0004\u0005\u0005\u0006"
                 "\u0003\u0004\u0004\u0005\u0004\u0005\u0005\u0006"
                 "\u0004\u0005\u0005\u0006\u0005\u0006\u0006\u0007"
                 "\u0001\u0002\u0002\u0003\u0002\u0003\u0003\u0004"
                 "\u0002\u0003\u0003\u0004\u0003\u0004\u0004\u0005"
                 "\u0002\u0003\u0003\u0004\u0003\u0004\u0004\u0005"
                 "\u0003\u0004\u0004\u0005\u0004\u0005\u0005\u0006"
                 "\u0002\u0003\u0003\u0004\u0003\u0004\u0004\u0005"
                 "\u0003\u0004\u0004\u0005\u0004\u0005\u0005\u0006"
                 "\u0003\u0004\u0004\u0005\u0004\u0005\u0005\u0006"
                 "\u0004\u0005\u0005\u0006\u0005\u0006\u0006\u0007"
                 "\u0002\u0003\u0003\u0004\u0003\u0004\u0004\u0005"
                 "\u0003\u0004\u0004\u0005\u0004\u0005\u0005\u0006"
                 "\u0003\u0004\u0004\u0005\u0004\u0005\u0005\u0006"
                 "\u0004\u0005\u0005\u0006\u0005\u0006\u0006\u0007"
                 "\u0003\u0004\u0004\u0005\u0004\u0005\u0005\u0006"
                 "\u0004\u0005\u0005\u0006\u0005\u0006\u0006\u0007"
                 "\u0004\u0005\u0005\u0006\u0005\u0006\u0006\u0007"
                 "\u0005\u0006\u0006\u0007\u0006\u0007\u0007\u0008")))

(defn tran-hash
  "calculate the tran hash"
  [a b c n]
  (bit-and (+ (bit-xor (tran (bit-and (+ a n) 255))
                       (* (tran b)
                          (+ n n 1)))
              (tran (bit-xor c (tran n))))
           255))

(defn process
  "process data"
  [data]
  (let [data (map hash data)]
    (loop [d (drop 3 data)
           window (reverse (take 3 data))
           num (count window)
           acc (vec (repeat 256 0))]
      (let [cross [[[0 1 2] 0]
                   [[0 1 3] 1]
                   [[0 2 3] 2]
                   [[0 1 4] 3]
                   [[0 2 4] 4]
                   [[0 3 4] 5]
                   [[4 1 0] 6]
                   [[4 3 0] 7]]
            acc (reduce (fn [y x]
                       (let [n (x 1)
                             dm (x 0)
                             a (nth window (dm 0))
                             b (nth window (dm 1))
                             c (nth window (dm 2))
                             i (tran-hash a b c n)]
                         (assoc y i (inc (y i)))))
                        acc
                        (take (case (count window)
                                3 1
                                4 3
                                5 8)                                
                              cross))]
        (if-let [fd (first d)]
          (recur (rest d)
                 (take 5 (cons fd window))
                 (inc num)
                 acc)
          [acc num])))))

(defn compute-digest
  "computes the digest"
  [acc num]
  (let [num-trigrams (if (= num 3)
                       1
                       (if (= num 4)
                         3
                         (- (* 8 num) 28)))
        threshold (/ num-trigrams 256)]
    (loop [byte (take 8 acc)
           r (drop 8 acc)
           ret []]
      (let [rbyte (reduce (fn [x y]
                               (let [x (bit-shift-right x 1)]
                                 (bit-or x (if (> y threshold) 128
                                                0))))
                          byte)]
        (if (first r)
          (recur (take 8 r)
                 (drop 8 r)
                 (conj ret rbyte))
          (reverse (conj ret rbyte)))))))

(defn digest
  "computes the digest of a string"
  [s]
  (apply compute-digest (process s)))

(defn hexdigest
  "computes the hexdigest of a string"
  [s]
    (apply str (map (partial format "%02x") (digest s))))) 

(defn distance
  "computes the bit distance between two digests"
  [a b]
  (- 128 (reduce + (map (fn [x y] (popc (bit-xor x y)))
                        a b))))
