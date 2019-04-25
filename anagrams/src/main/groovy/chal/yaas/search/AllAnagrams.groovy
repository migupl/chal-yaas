package chal.yaas.search

import chal.yaas.dictionary.DictionarySearchMethod
import chal.yaas.dictionary.concept.Word
import chal.yaas.dictionary.criteria.AllAnagramsCriteria
import chal.yaas.exceptions.NoAnagramFoundException

trait AllAnagrams {

    Set<String> allAnagrams(Word word, DictionarySearchMethod searchMethod) throws NoAnagramFoundException {
        def criteria = new AllAnagramsCriteria(word)
        def anagrams = searchMethod.search(word, criteria)
        if (anagrams) {
            return anagrams
        }

        throw new NoAnagramFoundException()
    }
}
