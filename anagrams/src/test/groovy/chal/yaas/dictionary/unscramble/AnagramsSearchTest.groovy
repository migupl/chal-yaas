package chal.yaas.dictionary.unscramble

import chal.yaas.dictionary.Dictionary
import chal.yaas.dictionary.concept.Word
import chal.yaas.dictionary.files.FileProcess
import chal.yaas.dictionary.files.FolderProcess
import chal.yaas.dictionary.load.DictionaryFolderLoader
import chal.yaas.exceptions.NoAnagramFoundException
import chal.yaas.messages.MessageBundle
import chal.yaas.search.AllAnagrams
import chal.yaas.search.LongestAnagram
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class AnagramsSearchTest extends Specification {

    Dictionary dictionary

    def setup() {
        dictionary = new UnscrambledDictionary()
    }

    def "When there are anagrams with same normal form than '#word' Then the longest ('#longestExpected') is expected"() {
        given: "Dictionary is loaded"
        loadDictionaries(dictionary, '/search/anagrams/paired/same_length')
        
        and: "Search method is defined"
        def searchMethod = new UnscrambledDictionaryDeepSearchMethod(dictionary)
        
        and: "Class uses trait 'longestAnagram'"
        def search = new AnagramsSearch()

        expect:
        longestExpected == search.longestAnagram(word, searchMethod)

        where:
        word = new Word('Aabacharis')
        longestExpected = 'Sari Abacha'
    }

    private void loadDictionaries(Dictionary dictionary, String fromPath) {
        def path = getClass().getResource(fromPath).path
        def folderProcess = new FolderProcess(path)

        new DictionaryFolderLoader(folderProcess).load(dictionary)
    }

    def "When there are anagrams with shorten normal form than '#word' Then the longest ('#longestExpected') is expected"() {
        given: "Dictionary is loaded"
        loadDictionaries(dictionary, '/search/anagrams/paired/same_length/no')

        and: "Search method is defined"
        def searchMethod = new UnscrambledDictionaryDeepSearchMethod(dictionary)

        and: "Class uses trait 'longestAnagram'"
        def search = new AnagramsSearch()

        expect:
        longestExpected == search.longestAnagram(word, searchMethod)

        where:
        word = new Word('Aabacharis')
        longestExpected = 'ABRACHIAS'
    }

    def "When there are anagrams with shorten normal form than '#word' for non English words Then the longest ('#longestExpected') is expected"() {
        given: "Dictionary is loaded"
        loadDictionaries(dictionary, words.dictionaryFolder)

        and: "Search method is defined"
        def searchMethod = new UnscrambledDictionaryDeepSearchMethod(dictionary)

        and: "Class uses trait 'longestAnagram'"
        def search = new AnagramsSearch()

        expect:
        longestExpected == search.longestAnagram(word, searchMethod)

        where:
        words << nonEnglishWords

        word = new Word("${words.word} ${words.word}")
        longestExpected = words.word
    }

    private List<Map> getNonEnglishWords() {
        def file = new File(getClass().getResource('/search/words/non_english.txt').toURI())
        new FileProcess(file).lines.collect {
            [
                    word            : it,
                    dictionaryFolder: '/search/words/'
            ]
        }
    }

    def "When '#word' has NOT anagrams and longest anagrams is requested Then NoAnagramFoundException is expected with message '#expectedMessage'"() {
        given: "Dictionary is loaded"
        loadDictionaries(dictionary, '/search/anagrams/no')
        
        and: "Search method is defined"
        def searchMethod = new UnscrambledDictionaryDeepSearchMethod(dictionary)

        and: "Class uses trait 'longestAnagram'"
        def search = new AnagramsSearch()

        when:
        search.longestAnagram(word, searchMethod)

        then:
        def ex = thrown NoAnagramFoundException
        expectedMessage == ex.message

        where:
        word = new Word('any word')
        expectedMessage = getErrorMessage()
    }

    private static String getErrorMessage() {
        def lang = MessageBundle.locale.language

        lang.startsWith('es') ? 'No se han encontrado anagramas en los diccionarios' :
                'Anagram is not found into dictionaries'
    }

    def "When '#word' has a lot of anagrams into dictionary and longest anagrams is requested Then the longest ('#longestExpected') is expected"() {
        given: "Dictionary is loaded"
        loadDictionaries(dictionary, '/search/anagrams/multiple')

        and: "Search method is defined"
        def searchMethod = new UnscrambledDictionaryDeepSearchMethod(dictionary)

        and: "Class uses trait 'longestAnagram'"
        def search = new AnagramsSearch()

        expect:
        longestExpected == search.longestAnagram(word, searchMethod)

        where:
        word                    || longestExpected
        new Word('catalystics') || 'stalactic'
        new Word('catalysts')   || 'saltcats'
        new Word('catalyst')    || 'saltcat'
    }

    def "When '#word' has NOT anagrams and all anagrams are requested Then NoAnagramFoundException is expected with message '#expectedMessage'"() {
        given: "Dictionary is loaded"
        loadDictionaries(dictionary, '/search/anagrams/no')

        and: "Search method is defined"
        def searchMethod = new UnscrambledDictionaryDeepSearchMethod(dictionary)

        and: "Class uses trait 'allAnagrams'"
        def search = new AnagramsSearch()

        when:
        search.allAnagrams(word, searchMethod)

        then:
        def ex = thrown NoAnagramFoundException
        expectedMessage == ex.message

        where:
        word = new Word('say')
        expectedMessage = getErrorMessage()
    }

    def "When '#word' has a lot of anagrams into dictionary and all anagrams are requested Then the anagrams's list ('#anagramsExpected') is expected"() {
        given: "Dictionary is loaded"
        loadDictionaries(dictionary, '/search/anagrams/multiple')

        and: "Search method is defined"
        def searchMethod = new UnscrambledDictionaryDeepSearchMethod(dictionary)

        and: "Class uses trait 'allAnagrams'"
        def search = new AnagramsSearch()

        expect:
        !(anagramsExpected - search.allAnagrams(word, searchMethod))

        where:
        word                    || anagramsExpected
        new Word('cats')        || ['acts', 'cat', 'at']
        new Word('catalystics') || ['stalactic', 'catalytic', 'catalysis', 'tactical', 'saltcats', 'catalyst', 'stylist',
                                    'saltcat', 'classic', 'tactic', 'static', 'statal', 'atlas', 'scala', 'sails', 'stay',
                                    'list', 'acts', 'tic', 'say', 'cat', 'si', 'la', 'at',]
        new Word('catalysts')   || ['saltcats', 'catalyst', 'saltcat', 'scala', 'statal', 'atlas', 'acts', 'cat', 'la', 'stay',
                                    'say', 'at']
        new Word('catalyst')    || ['saltcat', 'scala', 'statal', 'atlas', 'acts', 'cat', 'la', 'stay', 'say', 'at']
    }
    
    private class AnagramsSearch implements AllAnagrams, LongestAnagram {}
}
