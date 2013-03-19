# fuzzy-string

A Clojure library for fuzzy string matching

It implements the following matching algorithms

* dice
* levenshtein

## Usage

add the following to your lein project:

```clojure
  [fuzzy-string "0.1.0-SNAPSHOT"]
```

then 
```clojure
  (use 'fuzzy-string.core)
  (dice "Hair" "Wear")
  (levenshtein "Hair" "Wear")
```

## License

Copyright © 2013 Michael Bauer

Distributed under the Eclipse Public License, the same as Clojure.
