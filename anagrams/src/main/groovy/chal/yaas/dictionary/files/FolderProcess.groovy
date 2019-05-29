package chal.yaas.dictionary.files

import chal.yaas.exceptions.DictionaryException
import chal.yaas.messages.MessageBundle
import groovy.io.FileType

class FolderProcess {

    private final path
    private final dictionariesFolder

    FolderProcess(String path) {
        this.path = path
        dictionariesFolder = existingFile(path)
    }

    private File existingFile(String path) {
        def folder = new File(path)
        check folder
        folder
    }

    private def check(File dictionariesFolder) throws FileNotFoundException {
        if (!dictionariesFolder.isDirectory()) {
            throw new FileNotFoundException(MessageBundle.getString('must.be.a.folder.message',
                    path))
        }
    }

    String getName() {
        dictionariesFolder.name
    }

    List<FileProcess> getDictionaries() throws DictionaryException {
        def files = []
        dictionariesFolder.eachFile(FileType.FILES) { File file ->
            files << new FileProcess(file)
        }

        files
    }
}
