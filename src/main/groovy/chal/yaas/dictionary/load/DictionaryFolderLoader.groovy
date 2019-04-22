package chal.yaas.dictionary.load

import chal.yaas.dictionary.Dictionary
import chal.yaas.dictionary.files.FileProcess
import chal.yaas.dictionary.files.FolderProcess
import chal.yaas.exceptions.DictionaryEmptyException

class DictionaryFolderLoader implements DictionaryLoader {

    private final folder

    DictionaryFolderLoader(FolderProcess folder) {
        this.folder = folder
    }

    @Override
    def load(Dictionary dictionary) {
        folder.dictionaries.each { FileProcess dictionaryFile ->
            def lines = dictionaryFile.lines

            lines.each { String word ->
                dictionary.insert(word)
            }
        }

        if (dictionary.isEmpty()) {
            throw new DictionaryEmptyException()
        }
    }
}
