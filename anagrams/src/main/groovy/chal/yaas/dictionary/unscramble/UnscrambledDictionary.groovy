package chal.yaas.dictionary.unscramble

import chal.yaas.dictionary.Dictionary
import chal.yaas.dictionary.concept.Word
import chal.yaas.dictionary.criteria.AllAnagramsCriteria
import chal.yaas.dictionary.criteria.LongestAnagramCriteria
import chal.yaas.exceptions.NoAnagramFoundException

class UnscrambledDictionary implements Dictionary {

    protected final index = new CharNode()
    private final search

    UnscrambledDictionary() {
        search = new UnscrambledDictionaryDeepSearchMethod(this)
    }

    @Override
    def add(String s) {
        if (s) {
            def word = new Word(s)
            addWord(index, word)
        }
    }

    private void addWord(CharNode keyChar, Word word, int keyPos = 0) {
        String keyForm = word.asKey()
        if (keyPos == keyForm.length()) {
            keyChar.words << word.trimmed

        } else {
            char actualChar = keyForm.charAt(keyPos)

            if (!keyChar.nextChar.containsKey(actualChar)) {
                keyChar.nextChar.put(actualChar, new CharNode())
            }

            addWord(keyChar.nextChar.get(actualChar), word, keyPos + 1)
        }
    }

    @Override
    boolean exists(String s) {
        def word = new Word(s)
        def words = getWords(index, word.asKey()).toList()

        words.contains(word.trimmed)
    }

    private Set<String> getWords(CharNode keyChar, String anagram, int keyPos = 0) {
        if (keyPos == anagram.length()) {
            return keyChar.words
        }

        char actualChar = anagram.charAt(keyPos)
        getWords(keyChar.nextChar.get(actualChar), anagram, keyPos + 1) ?: []
    }

    @Override
    boolean isEmpty() {
        index.isEmpty()
    }

    @Override
    Set<String> anagramsOf(String s) throws NoAnagramFoundException {
        def word = new Word(s)
        def criteria = new AllAnagramsCriteria(word)
        def anagrams = search.search(word, criteria)
        if (anagrams) {
            return anagrams
        }

        throw new NoAnagramFoundException()
    }

    @Override
    String longestAnagramOf(String s) throws NoAnagramFoundException {
        def word = new Word(s)
        def criteria = new LongestAnagramCriteria(word)
        def anagrams = search.search(word, criteria)
        if (anagrams) {
            return anagrams[0]
        }

        throw new NoAnagramFoundException()
    }
}
