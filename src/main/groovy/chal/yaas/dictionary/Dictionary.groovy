package chal.yaas.dictionary

import chal.yaas.exceptions.NoAnagramFoundException

interface Dictionary {

    def insert(String s)
    boolean exists(String s)

    boolean isEmpty()

    String longestAnagramOf(String s) throws NoAnagramFoundException
}
