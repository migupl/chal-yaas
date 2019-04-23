package chal.yaas

import chal.yaas.dictionary.Dictionary
import chal.yaas.dictionary.UnscrambledDictionary
import chal.yaas.dictionary.files.FolderProcess
import chal.yaas.dictionary.load.DictionaryFolderLoader
import chal.yaas.exceptions.MinLengthAllowedException
import chal.yaas.exceptions.NoAnagramFoundException

class AnagramSolver {

    static final byte MIN_LENGTH = 3

    private final Dictionary dictionary

    AnagramSolver(String dictionariesFolder) {
        def (folder, dictionary) = [
                new FolderProcess(dictionariesFolder),
                new UnscrambledDictionary()
        ]

        def loader = new DictionaryFolderLoader(folder)
        loader.load(dictionary)

        this.dictionary = dictionary
    }

    String getLongestAnagram(String s) throws MinLengthAllowedException, NoAnagramFoundException {
        validate(s)
        dictionary.longestAnagramOf(s)
    }

    List<String> getAllAnagrams(String s) throws NoAnagramFoundException {
        validate(s)
        dictionary.anagramsOf(s)
    }

    private static def validate(String s) throws MinLengthAllowedException {
        def length = s?.trim().length()
        if (length < MIN_LENGTH) {
            throw new MinLengthAllowedException(MIN_LENGTH)
        }
    }
}
