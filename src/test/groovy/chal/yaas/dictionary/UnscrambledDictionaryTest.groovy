package chal.yaas.dictionary

import chal.yaas.dictionary.files.FileProcess
import chal.yaas.dictionary.files.FolderProcess
import chal.yaas.dictionary.load.DictionaryFolderLoader
import chal.yaas.exceptions.NoAnagramFoundException
import chal.yaas.messages.MessageBundle
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class UnscrambledDictionaryTest extends Specification {

    Dictionary dictionary

    def setup() {
        dictionary = new UnscrambledDictionary()
    }

    def "When there are anagrams with same normal form than '#word' Then the longest ('#longestExpected') is expected"() {
        given: "Dictionary is loaded"
        loadDictionaries(dictionary, '/search/anagrams/paired/same_length')

        expect:
        longestExpected == dictionary.longestAnagramOf(word)

        where:
        word = 'Aabacharis'
        longestExpected = 'Sari Abacha'
    }

    private void loadDictionaries(Dictionary dictionary, fromPath) {
        def path = getClass().getResource(fromPath).path
        def folderProcess = new FolderProcess(path)

        new DictionaryFolderLoader(folderProcess).load(dictionary)
    }

    def "When there are anagrams with shorten normal form than '#word' Then the longest ('#longestExpected') is expected"() {
        given: "Dictionary is loaded"
        loadDictionaries(dictionary, '/search/anagrams/paired/same_length/no')

        expect:
        longestExpected == dictionary.longestAnagramOf(word)

        where:
        word = 'Aabacharis'
        longestExpected = 'ABRACHIAS'
    }

    def "When there are anagrams with shorten normal form than '#word' for non English words Then the longest ('#longestExpected') is expected"() {
        given: "Dictionary is loaded"
        loadDictionaries(dictionary, words.dictionaryFolder)

        expect:
        longestExpected == dictionary.longestAnagramOf(word)

        where:
        words << nonEnglishWords

        word = "${words.word} ${words.word}"
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

        when:
        dictionary.longestAnagramOf(word)

        then:
        def ex = thrown NoAnagramFoundException
        expectedMessage == ex.message

        where:
        word = 'any word'
        expectedMessage = getErrorMessage()
    }

    private String getErrorMessage() {
        def lang = MessageBundle.locale.language

        lang.startsWith('es') ? 'No se han encontrado anagramas en los diccionarios' :
                'Anagram is not found into dictionaries'
    }

    def "When '#word' has a lot of anagrams into dictionary and longest anagrams is requested Then the longest ('#longestExpected') is expected"() {
        given: "Dictionary is loaded"
        loadDictionaries(dictionary, '/search/anagrams/multiple')

        expect:
        longestExpected == dictionary.longestAnagramOf(word)

        where:
        word          || longestExpected
        'catalystics' || 'stalactic'
        'catalysts'   || 'saltcats'
        'catalyst'    || 'saltcat'
    }
}
