# Anagrams solver

An [anagram][1] is a word or sentence that can be rearranged to create another one, like **parliament** can be 
turn to **partial men**, or **Clint Eastwood** can turn into **Old West Action** ([Internet Anagram Server][2])

The goal is to implement a command line application that allows both finding the longest anagram and all existing 
anagrams in the dictionary used.

A dictionary will be a list of words where a line contains only a word or a sentence.

The running application reads all the text files from a given directory, as parameter, that would work as dictionaries and gives a 
prompt to search for anagrams as for example:

```bash
$ groovy -cp ... <path-to-dictionary-folder>
Dictionary folder dict/words/en/ will be loaded
Use <ctrl> + C to finish
* Write a word for searching anagrams: ...
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

## Running at the project root folder

The test will be running with 

```bash
[project.path]$ ./gradlew test
```

Run the application executing 

```bash
[project.path]$ groovy -cp anagrams/src/main/groovy:anagrams/src/main/resources app/src/main/groovy/Anagrammatist <dictionaries folder path>
```

or 

```bash
[project.path]$ groovy -cp anagrams/build/libs/anagrams-solver-0.1.jar app/src/main/groovy/Anagrammatist <dictionaries folder path>
```

when project was build.

---
## Fun facts

"Anagrams" itself can be anagrammatized as "Ars magna" (Latin, 'the great art').

English language:
- [Longest Words in the English Language][3]
- A word can be composed by only vowels (a, e, i, o, u) as the word [euouae][4]
- [I found the best anagram in English][5]

Playing dictionaries in [English][6] (275k words), [Spanish][7] (250k words) or [French][8] (210k words).

---
[1]: https://en.wikipedia.org/wiki/Anagram
[2]: https://wordsmith.org/anagram/index.html
[3]: https://grammar.yourdictionary.com/word-lists/longest-words-in-the-english-language.html
[4]: https://en.wikipedia.org/wiki/Euouae
[5]: https://blog.plover.com/lang/anagram-scoring.html
[6]: https://github.com/zeke/an-array-of-english-words
[7]: https://github.com/words/an-array-of-spanish-words
[8]: https://github.com/words/an-array-of-french-words
