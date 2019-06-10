# Anagrams solver with command line application

An [anagram][1] is a word or sentence that can be rearranged to create another one, such as **parliament** that can be 
turn into **partial men**, or **Clint Eastwood** can turn into **Old West Action** ([Internet Anagram Server][2])

The initial goal is to implement a command line application that allows both finding the longest anagram and all existing 
ones at the loaded dictionary.

A dictionary is a list of lines in which each line contains only a word or a sentence.

The running application reads all the text files from the given directory, as a parameter, and gives us a 
prompt to search for anagrams as below:

```bash
$ groovy -cp ... <path-to-dictionary-folder>
Dictionary folder dict/words/en/ will be loaded
Use <ctrl> + C to finish
* Write a word for searching anagrams: ...
```

## Environment

Groovy and Gradle are used 

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
[project.path]$ groovy -cp libs/anagrams-solver-0.1.jar app/src/main/groovy/Anagrammatist <dictionaries folder path>
```

when 'anagrams' subproject jar was generated.

```bash
[project.path]$ ./gradlew anagrams:jar
```

## OWASP 10 dependency check

Dependency check tasks are available

```bash
[project.path]$ ./gradlew tasks
...
OWASP dependency-check tasks
----------------------------
dependencyCheckAggregate - Identifies and reports known vulnerabilities (CVEs) in multi-project dependencies.
dependencyCheckAnalyze - Identifies and reports known vulnerabilities (CVEs) in project dependencies.
dependencyCheckPurge - Purges the local cache of the NVD.
dependencyCheckUpdate - Downloads and stores updates from the NVD CVE data feeds.
...

```

As by example
```bash
[project.path]$ ./gradlew dependencyCheckAnalyze
Unable to determine Package-URL identifiers for 2 dependencies

> Task :dependencyCheckAnalyze
Verifying dependencies for project chal-yaas
Checking for updates and analyzing dependencies for vulnerabilities
Generating report for project chal-yaas
Found 0 vulnerabilities in project chal-yaas

BUILD SUCCESSFUL in 2s
1 actionable task: 1 executed
```

## Anagrammatist command line application

### Installation

Unpack the ZIP or TAR file in a suitable location

### Usage

```bash
$ cd anagrammatist-0.1
$ ./bin/anagrammatist <dictionary folder>
Dictionaries folder <dictionary folder> will be loaded
Use <ctrl> + C to finish
* Write a word for searching anagrams: anagrams
```

---
## Fun facts

"Anagrams" itself can be anagrammatized as "Ars magna" (Latin, 'the great art').

English language:
- [Longest Words in the English Language][3]
- A word can be composed by only vowels (a, e, i, o, u) as the word [euouae][4]
- [I found the best anagram in English][5]

Playing with dictionaries of [English][6] (275k words), [Spanish][7] (250k words), [French][8] (210k words) ... 
[Wordnet][9] database for English ([MIT license][10] [citation][11]) or [Other laguages][12]

---
[1]: https://en.wikipedia.org/wiki/Anagram
[2]: https://wordsmith.org/anagram/index.html
[3]: https://grammar.yourdictionary.com/word-lists/longest-words-in-the-english-language.html
[4]: https://en.wikipedia.org/wiki/Euouae
[5]: https://blog.plover.com/lang/anagram-scoring.html
[6]: https://github.com/zeke/an-array-of-english-words
[7]: https://github.com/words/an-array-of-spanish-words
[8]: https://github.com/words/an-array-of-french-words
[9]: https://wordnet.princeton.edu/download/current-version
[10]: https://wordnet.princeton.edu/license-and-commercial-use
[11]: https://wordnet.princeton.edu/citing-wordnet
[12]: http://globalwordnet.org/resources/wordnets-in-the-world/
