package chal.yaas.dictionary

import chal.yaas.dictionary.concept.KeyForm
import chal.yaas.dictionary.concept.Word
import chal.yaas.exceptions.NoAnagramFoundException

class UnscrambledDictionary implements Dictionary {

    private final index = new CharNode()

    @Override
    def insert(String s) {
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
    List<String> anagramsOf(String s) throws NoAnagramFoundException {
        def word = new Word(s)
        def anagrams = search2(index, word.trimmed, word.keyForm) - word.trimmed
        if (anagrams) {
            return anagrams
        }

        throw new NoAnagramFoundException()
    }

    // TODO: refactor / criteria?
    private List<String> search2(CharNode keyChar, String original, KeyForm keyForm) {
        def chars = keyChar.nextChar.keySet().intersect(keyForm.toList())

        def anagrams = []
        if (chars) {
            String key = keyForm.toString()

            for (char c in chars) {
                def keyPos = key.indexOf(c as String)
                if (0 <= keyPos) {
                    anagrams << search2(keyChar.nextChar.get(c), original, new KeyForm(key.substring(keyPos + 1)))
                }
            }
        }

        // TODO: remove magic number
        (anagrams.flatten() + (keyChar.words.grep { it.length() > 1 }.toList())) as List<String>
    }

    @Override
    String longestAnagramOf(String s) throws NoAnagramFoundException {
        def word = new Word(s)
        def longest = search(index, word.trimmed, word.keyForm)
        if (longest) {
            return longest.max { it.length() }
        }

        throw new NoAnagramFoundException()
    }

    // TODO: refactor / criteria?
    private List<String> search(CharNode keyChar, String original, KeyForm keyForm) {
        def chars = keyChar.nextChar.keySet().intersect(keyForm.toList())

        if (chars) {
            String key = keyForm.toString()

            for (char c in chars) {
                def keyPos = key.indexOf(c as String)
                if (0 <= keyPos) {
                    def longest = search(keyChar.nextChar.get(c), original, new KeyForm(key.substring(keyPos + 1)))
                    if(longest) return longest
                }
            }
        }

        (keyChar.words.toList() - original) as List<String>
    }

    private class CharNode {

        final Set<String> words = []
        final Map<Character, CharNode> nextChar = [:]

        boolean isEmpty() {
            nextChar.isEmpty()
        }
    }
}
