package chal.yaas.dictionary.files

import chal.yaas.messages.MessageBundle
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class FolderProcessTest extends Specification {

    def "When FolderProcess(#folderName) is called and folder does not exist Then FileNotFoundException is expected with message '#expectedMessage'"() {
        when:
        new FolderProcess(folderName)

        then:
        def e = thrown FileNotFoundException
        expectedMessage == e.message

        where:
        folderName << [
                'must_no_exist',
                getClass().getResource('/lorem_ipsum.txt').toURI() as String
        ]

        expectedMessage = getErrorMessage(folderName)
    }

    private static String getErrorMessage(String folderName) {
        def (lang, folder) = [
                MessageBundle.locale.language,
                new File(folderName)
        ]

        lang.startsWith('es') ? "-${folder.name}- debe ser un folder que exista" :
                "-${folder.name}- must be an existing folder"
    }

    def "When FolderProcess(#expectedFile.parent) is called Then any sub folders are NOT loaded"() {
        expect:
        expectedFiles == new FolderProcess(expectedFile.parent).dictionaries

        where:
        expectedFile = new File(getClass().getResource('/lorem_ipsum.txt').toURI())

        expectedFiles = [ new FileProcess(expectedFile) ]
    }
}
