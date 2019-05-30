package chal.yaas.dictionary.criteria

import chal.yaas.dictionary.concept.Word

class LongestAnagramCriteria extends SearchingCriteria {

    private String longest

    LongestAnagramCriteria(Word word) {
        super(word)
    }

    @Override
    def clear() {
        longest = ''
    }

    @Override
    Set<String> getCandidates() {
        longest ? [ longest ] : []
    }

    @Override
    boolean keepTrying() {
        !longest
    }

    @Override
    def choose(Set<String> anagrams) {
        if (anagrams) {
            def cleanAnagrams = remove(anagrams, word.trimmed)
            longest = (cleanAnagrams + longest).max { it.length() }
        }
    }
}
