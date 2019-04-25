package chal.yaas.search

import chal.yaas.dictionary.concept.Word

trait MinLengthValidation {

    static final byte MIN_LENGTH = 2

    boolean isValid(Word word) {
        def length = word.trimmed.length()
        length > MIN_LENGTH
    }
}