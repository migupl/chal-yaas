package chal.yaas.dictionary

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Unroll

@Stepwise
@Unroll
abstract class DictionaryTest extends Specification {

    @Shared
    def dictionary

    def "At initial Dictionary is empty"() {
        expect:
        dictionary.isEmpty()
    }

    def "Any empty word (#word) is NOT inserted"() {
        when:
        dictionary.add(word)

        then: "The '#word' does not exists into the Dictionary"
        !dictionary.exists(word)

        and: 'Obviously, because Dictionary is empty'
        dictionary.isEmpty()

        where:
        word << [ null, '' ]
    }

    def "When '#word' is added Then it can be found"() {
        when:
        dictionary.add(word)

        then:
        dictionary.exists(word)

        where:
        word << anagrams + nonEnglishWords
    }

    private static List<String> getAnagrams() {
        [
                'acts', 'cats'
        ]
    }

    private static List<String> getNonEnglishWords() {
        [
                'calçots', 'ñandús', 'vêtîtes'
        ]
    }

    def "When a '#word' is inserted Then Dictionary has trimmed word '#insertedWord'"() {
        when:
        dictionary.add(word)

        then:
        dictionary.exists(word)
        dictionary.exists(insertedWord)

        where:
        word = '   gaps on both sides   '
        insertedWord = 'gaps on both sides'
    }
}
