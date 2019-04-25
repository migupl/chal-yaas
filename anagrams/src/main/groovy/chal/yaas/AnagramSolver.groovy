package chal.yaas

import chal.yaas.dictionary.DictionarySearchMethod
import chal.yaas.dictionary.concept.Word
import chal.yaas.dictionary.files.FolderProcess
import chal.yaas.dictionary.load.DictionaryFolderLoader
import chal.yaas.dictionary.unscramble.UnscrambledDictionary
import chal.yaas.dictionary.unscramble.UnscrambledDictionaryDeepSearchMethod
import chal.yaas.exceptions.MinLengthAllowedException
import chal.yaas.exceptions.NoAnagramFoundException
import chal.yaas.search.AllAnagrams
import chal.yaas.search.LongestAnagram
import chal.yaas.search.MinLengthValidation

class AnagramSolver implements MinLengthValidation, AllAnagrams, LongestAnagram {

    private final DictionarySearchMethod searchMethod

    AnagramSolver(String dictionariesFolder) {
        def (folder, dictionary) = [
                new FolderProcess(dictionariesFolder),
                new UnscrambledDictionary()
        ]

        def loader = new DictionaryFolderLoader(folder)
        loader.load(dictionary)

        searchMethod = new UnscrambledDictionaryDeepSearchMethod(dictionary)
    }

    String getLongestAnagram(String s) throws MinLengthAllowedException, NoAnagramFoundException {
        Word word = getValidatedWord(s)
        longestAnagram(word, searchMethod)
    }

    private Word getValidatedWord(String s) throws MinLengthValidation {
        def word = new Word(s)
        if (isValid(word)) {
            return word
        }

        throw new MinLengthAllowedException(MIN_LENGTH)
    }

    Set<String> getAllAnagrams(String s) throws MinLengthAllowedException, NoAnagramFoundException {
        Word word = getValidatedWord(s)
        allAnagrams(word, searchMethod)
    }
}
