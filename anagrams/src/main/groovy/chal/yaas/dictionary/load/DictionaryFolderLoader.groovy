package chal.yaas.dictionary.load

import chal.yaas.dictionary.Dictionary
import chal.yaas.dictionary.files.FileProcess
import chal.yaas.dictionary.files.FolderProcess
import chal.yaas.exceptions.DictionaryEmptyException
import chal.yaas.exceptions.DictionaryException

class DictionaryFolderLoader implements DictionaryLoader {

    private final folder

    DictionaryFolderLoader(FolderProcess folder) {
        this.folder = folder
    }

    @Override
    def load(Dictionary dictionary) throws DictionaryException, DictionaryEmptyException {
        folder.dictionaries.each { FileProcess dictionaryFile ->
            def lines = dictionaryFile.lines

            lines.each { String word ->
                dictionary.add(word)
            }
        }

        if (dictionary.isEmpty()) {
            throw new DictionaryEmptyException()
        }
    }
}
