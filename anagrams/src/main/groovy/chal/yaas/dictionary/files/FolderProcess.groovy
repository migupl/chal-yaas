package chal.yaas.dictionary.files


import chal.yaas.messages.MessageBundle
import groovy.io.FileType

class FolderProcess {

    private final dictionariesFolder

    FolderProcess(String path) {
        def folder = new File(path)
        check folder

        this.dictionariesFolder = folder
    }

    private static def check(File dictionariesFolder) throws FileNotFoundException {
        if (!dictionariesFolder.isDirectory()) {
            throw new FileNotFoundException(MessageBundle.getString('must.be.a.folder.message',
                    dictionariesFolder.name))
        }
    }

    String getName() {
        dictionariesFolder.name
    }

    List<FileProcess> getDictionaries() {
        def files = []
        dictionariesFolder.eachFile(FileType.FILES) { File file ->
            files << new FileProcess(file)
        }

        files
    }
}
