# Anagrams solver

An [anagram][1] is a word or sentence that can be rearranged to create another one, like **parliament** can be 
turn to **partial men**, or **Clint Eastwood** can turn into **Old West Action** ([Internet Anagram Server][2])

The goal is to implement a command line application that allows both finding the longest anagram and all existing 
anagrams in the dictionary used.

A dictionary will be a list of words where a line contains only a word or a sentence.

The running application reads all the text files from a given directory that would work as dictionaries and gives a 
prompt to search for anagrams as for example:

```bash
$ groovy -cp ... 
Dictionary folder dict/words/en/ will be loaded
Found in 3714 ms
Use <ctrl> + C to finish
* Write a word for searching anagrams: aa
```

---
[1]: https://en.wikipedia.org/wiki/Anagram
[2]: https://wordsmith.org/anagram/index.html