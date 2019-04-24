package chal.yaas.exceptions

import chal.yaas.messages.MessageBundle

class DictionaryException extends Exception {

    DictionaryException(File file, String message) {
        super(MessageBundle.getString('load.dictionary.error', file.name, message))
    }
}
