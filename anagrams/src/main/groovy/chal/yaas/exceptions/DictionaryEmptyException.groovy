package chal.yaas.exceptions

import chal.yaas.messages.MessageBundle

class DictionaryEmptyException extends Exception {

    DictionaryEmptyException() {
        super(MessageBundle.getString('empty.dictionary.error'))
    }
}
