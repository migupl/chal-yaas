package chal.yaas.dictionary.unscramble

import groovy.transform.PackageScope

@PackageScope
class CharNode {

    final Set<String> words = [] as Set
    final Map<Character, CharNode> nextChar = [:]

    boolean isEmpty() {
        nextChar.isEmpty()
    }
}