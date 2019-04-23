package chal.yaas.dictionary

import chal.yaas.exceptions.NoAnagramFoundException

interface Dictionary {

    def add(String s)
    boolean exists(String s)

    boolean isEmpty()

    Set<String> anagramsOf(String s) throws NoAnagramFoundException
    String longestAnagramOf(String s) throws NoAnagramFoundException
}
