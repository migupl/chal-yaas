# Anagrams solver

An [anagram][1] is a word or sentence that can be rearranged to create another one, like **parliament** can be 
turn to **partial men**, or **Clint Eastwood** can turn into **Old West Action** ([Internet Anagram Server][2])

The goal is to implement a command line application that allows both finding the longest anagram and all existing 
anagrams in the dictionary used.

A dictionary will be a list of words where a line contains only a word or a sentence.

The running application reads all the text files from a given directory, as parameter, that would work as dictionaries and gives a 
prompt to search for anagrams as for example:

```bash
$ groovy src/main/groovy/Anagrammatist <path-to-dictionary-folder>
Dictionary folder dict/words/en/ will be loaded
Found in 3714 ms
Use <ctrl> + C to finish
* Write a word for searching anagrams: aa
```

## Environment

Groovy is used as programming language and Gradle (local installation) as build tool 

```bash
$ ./gradlew -v

------------------------------------------------------------
Gradle 5.2.1
------------------------------------------------------------

Build time:   2019-02-08 19:00:10 UTC
Revision:     f02764e074c32ee8851a4e1877dd1fea8ffb7183

Kotlin DSL:   1.1.3
Kotlin:       1.3.20
Groovy:       2.5.4
Ant:          Apache Ant(TM) version 1.9.13 compiled on July 10 2018
JVM:          1.8.0_202 (Azul Systems, Inc. 25.202-b05)
OS:           Linux 4.19.32-1-MANJARO amd64

```

---
[1]: https://en.wikipedia.org/wiki/Anagram
[2]: https://wordsmith.org/anagram/index.html