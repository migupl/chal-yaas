package chal.yaas.dictionary.files

import chal.yaas.messages.MessageBundle
import groovy.transform.EqualsAndHashCode

import java.nio.charset.StandardCharsets

@EqualsAndHashCode
class FileProcess {

    private final File file
    boolean entirelyRead = false

    FileProcess(File file) {
        this.file = file
    }

    String getName() {
        file.name
    }

    List<String> getLines() throws IOException {
        def lines = []
        try {
            lines = file.readLines(StandardCharsets.UTF_8.toString())
            entirelyRead = true

        } catch (_) {
            MessageBundle.getString('load.dictionary.error')
        }

        lines
    }
}
