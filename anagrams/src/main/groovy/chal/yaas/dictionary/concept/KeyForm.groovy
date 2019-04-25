package chal.yaas.dictionary.concept

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includeFields = true)
class KeyForm {

    private char[] chars

    KeyForm(String s) {
        chars = normalForm(s)
    }

    @Override
    String toString() {
        chars.join()
    }

    List<Character> toList() {
        chars.toList()
    }

    private static char[] normalForm(String s) {
        if (s) {
            return s.toLowerCase().
                    toCharArray().
                    toList().
                    grep { it.isLetter() }.
                    sort()
        }

        []
    }
}
