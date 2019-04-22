package chal.yaas.exceptions

import chal.yaas.messages.MessageBundle

class MinLengthAllowedException extends Exception {

    MinLengthAllowedException(int length) {
        super(MessageBundle.getString('min.length.allowed.error', length))
    }
}
