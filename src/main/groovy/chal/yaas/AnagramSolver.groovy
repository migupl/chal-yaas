package chal.yaas

import chal.yaas.exceptions.MinLengthAllowedException

class AnagramSolver {

    static final byte MIN_LENGTH = 3

    private final String dictionariesFolder

    AnagramSolver(String dictionariesFolder) {
        this.dictionariesFolder = dictionariesFolder
    }

    String getLongestAnagram(String s) throws MinLengthAllowedException {
        validate(s)

        ''
    }

    private static def validate(String s) throws MinLengthAllowedException {
        def length = s?.trim().length()
        if ( length < MIN_LENGTH) {
            throw new MinLengthAllowedException(MIN_LENGTH)
        }
    }
}
