package chal.yaas.search

import chal.yaas.dictionary.DictionarySearchMethod
import chal.yaas.dictionary.concept.Word
import chal.yaas.dictionary.criteria.LongestAnagramCriteria
import chal.yaas.exceptions.NoAnagramFoundException

trait LongestAnagram {

    String longestAnagram(Word word, DictionarySearchMethod searchMethod) throws NoAnagramFoundException {
        def criteria = new LongestAnagramCriteria(word)
        def anagrams = searchMethod.search(word, criteria)
        if (anagrams) {
            return anagrams[0]
        }

        throw new NoAnagramFoundException()
    }
}