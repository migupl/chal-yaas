package chal.yaas.dictionary.load

import chal.yaas.dictionary.Dictionary
import chal.yaas.dictionary.UnscrambledDictionary
import chal.yaas.dictionary.files.FileProcess
import chal.yaas.dictionary.files.FolderProcess
import chal.yaas.exceptions.DictionaryEmptyException
import chal.yaas.messages.MessageBundle
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class DictionaryFolderLoaderTest extends Specification {

    def "when dictionaries into '#dictionaryFolder.name' are empty Then a DictionaryEmptyException is expected with message '#expectedMessage'"() {
        setup:
        Dictionary dictionary = new UnscrambledDictionary()

        when:
        new DictionaryFolderLoader(dictionaryFolder).load(dictionary)

        then:
        def ex = thrown DictionaryEmptyException
        expectedMessage == ex.message

        where:
        dictionaryFolder = getFolderProcess('/empty_dictionaries')
        expectedMessage = getErrorMessage()
    }

    private static FolderProcess getFolderProcess(String folder) {
        def path = getClass().getResource(folder).path

        new FolderProcess(path)
    }

    private static String getErrorMessage() {
        def lang = MessageBundle.locale.language

        lang.startsWith('es') ? 'No hay palabras cargadas. Los diccionarios no contienen palabras.' :
                'Dictionary files are empty. No words loaded.'
    }

    def "when dictionaries into '#dictionaryFolder.name' contains words Then they are loaded"() {
        setup:
        Dictionary dictionary = Mock(Dictionary)

        when:
        new DictionaryFolderLoader(dictionaryFolder).load(dictionary)

        then:
        expectedLines * dictionary.insert(_)

        where:
        expectedDictionary = new File(getClass().getResource('/lorem_ipsum.txt').toURI())
        expectedLines = new FileProcess(expectedDictionary).lines.size()

        dictionaryFolder = new FolderProcess(expectedDictionary.parent)
    }

    def "when dictionaries into '#dictionaryFolder.name' contains non English words Then they are loaded (#expectedLines)"() {
        setup:
        Dictionary dictionary = new UnscrambledDictionary()

        when:
        new DictionaryFolderLoader(dictionaryFolder).load(dictionary)

        then:
        !dictionary.isEmpty()
        expectedLines.each { String word ->
            assert dictionary.exists(word)
        }

        where:
        file = new File(getClass().getResource('/search/words/non_english.txt').toURI())
        expectedLines = new FileProcess(file).lines

        dictionaryFolder = new FolderProcess(file.parent)
    }
}
