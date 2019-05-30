package chal.yaas.dictionary.criteria

import chal.yaas.dictionary.concept.Word
import groovy.transform.PackageScope

abstract class SearchingCriteria {

    @PackageScope Word word

    SearchingCriteria(Word word) {
        this.word = word
        clear()
    }

    abstract clear()
    abstract choose(Set<String> anagrams)
    abstract boolean keepTrying()

    abstract Set<String> getCandidates()

    static Set<String> remove(Set<String> stringSet, String s) {
        stringSet - stringSet.findAll { s.equalsIgnoreCase(it) }
    }
}