package chal.yaas.exceptions

import chal.yaas.messages.MessageBundle

class NoAnagramFoundException extends Exception {

    NoAnagramFoundException() {
        super(MessageBundle.getString('anagram.not.found'))
    }
}
