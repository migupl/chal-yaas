package chal.yaas.dictionary.criteria

import chal.yaas.dictionary.concept.Word

class AllAnagramsCriteria extends SearchingCriteria {

    private Set<String> candidates

    AllAnagramsCriteria(Word word) {
        super(word)
        clear()
    }

    @Override
    def clear() {
        candidates = []
    }

    @Override
    Set<String> getCandidates() {
        candidates.unique() - word.trimmed
    }

    @Override
    boolean keepTrying() {
        true
    }

    @Override
    String choose(Set<String> anagrams) {
        candidates.addAll anagrams
    }
}
