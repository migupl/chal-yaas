package chal.yaas.dictionary.files

import chal.yaas.exceptions.DictionaryException
import groovy.transform.EqualsAndHashCode

import java.nio.charset.StandardCharsets

@EqualsAndHashCode
class FileProcess {

    private final File file

    FileProcess(File file) {
        this.file = file
    }

    String getName() {
        file.name
    }

    List<String> getLines() throws DictionaryException {
        def lines
        try {
            lines = file.readLines(StandardCharsets.UTF_8.toString())

        } catch (_) {
            throw new DictionaryException(file, _.message)
        }

        lines
    }
}
