package chal.yaas.dictionary.concept

import chal.yaas.search.MinLengthValidation
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class WordTest extends Specification {

    def "When Word('#word') is called Then 'keyForm' property is initialized lazily"() {
        when:
        def word = new Word(s)

        then:
        s == word.trimmed
        word.dump().contains('keyForm=null')

        when:
        word.keyForm

        then:
        def keyForm = word.keyForm
        keyForm
        word.trimmed.toCharArray().every {
            keyForm.toList().contains(it)
        }

        where:
        s = 'anagram'
    }

    def "When Word('#word') is checked with MinLengthValidation trait and the length of '#word' is less than 3 Then false is expected"() {
        given:
        def word = new Word(s)

        and: "Class has traits"
        def validate = new Validate()

        expect: "Validation returns false for #word.trimmed"
        !validate.isValid(word)

        where:
        s << [
                'A', 'He'
        ]
    }

    private class Validate implements MinLengthValidation {}
}
