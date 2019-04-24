package chal.yaas.dictionary

import chal.yaas.dictionary.concept.KeyForm
import chal.yaas.dictionary.concept.Word
import chal.yaas.dictionary.criteria.AllAnagramsCriteria
import chal.yaas.dictionary.criteria.LongestAnagramCriteria
import chal.yaas.dictionary.criteria.SearchingCriteria
import chal.yaas.exceptions.NoAnagramFoundException

class UnscrambledDictionary implements Dictionary {

    private final index = new CharNode()

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
        def anagrams = search(word, criteria)
        if (anagrams) {
            return anagrams
        }

        throw new NoAnagramFoundException()
    }

    private Set<String> search(Word word, SearchingCriteria criteria) {
        criteria.clear()
        deepSearch(index, criteria, word.trimmed, word.keyForm)
        criteria.candidates
    }

    private deepSearch(CharNode keyChar, SearchingCriteria criteria, String original, KeyForm keyForm) {
        def chars = keyChar.nextChar.keySet().intersect(keyForm.toList())

        if (chars) {
            String key = keyForm.toString()

            for (char c in chars) {
                def keyPos = key.indexOf(c as String)
                if (0 <= keyPos) {
                    deepSearch(keyChar.nextChar.get(c), criteria, original, new KeyForm(key.substring(keyPos + 1)))
                    if (!criteria.keepTrying()) break
                }
            }
        }

        criteria.choose(keyChar.words)
    }

    @Override
    String longestAnagramOf(String s) throws NoAnagramFoundException {
        def word = new Word(s)
        def criteria = new LongestAnagramCriteria(word)
        def anagrams = search(word, criteria)
        if (anagrams) {
            return anagrams[0]
        }

        throw new NoAnagramFoundException()
    }

    private class CharNode {

        final Set<String> words = [] as Set
        final Map<Character, CharNode> nextChar = [:]

        boolean isEmpty() {
            nextChar.isEmpty()
        }
    }
}
