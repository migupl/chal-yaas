package chal.yaas.dictionary.files

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class FileProcessTest extends Specification {

    def "When file '#file.path' is read Then its contents is available"() {
        setup:
        FileProcess fileProcess = new FileProcess(file)

        expect:
        expectedText == fileProcess.lines

        where:
        file = new File(getClass().getResource('/lorem_ipsum.txt').toURI())
        expectedText = [
                'Lorem ipsum dolor sit amet, consectetur adipiscing elit',
                'Vivamus condimentum sagittis lacus, laoreet luctus ligula laoreet ut',
                'Vestibulum ullamcorper accumsan velit vel vehicula',
                'Proin tempor lacus arcuNunc at elit condimentum, semper nisi et, condimentum mi',
                'In venenatis blandit nibh at sollicitudin',
                'Vestibulum dapibus mauris at orci maximus pellentesque',
                'Nullam id elementum ipsum',
        ]
    }
}
