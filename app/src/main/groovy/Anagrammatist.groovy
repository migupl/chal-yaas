import chal.yaas.AnagramSolver

class Anagrammatist {

    static final EXIT_ERROR_CODE = 1

    static AnagramSolver anagramSolver

    static void main(String... args) {
        if (dictionaryFolderIsReceived(args)) {
            try {

                String dictionariesFolder = args[0]
                println "Dictionaries folder $dictionariesFolder will be loaded"

                anagramSolver = new AnagramSolver(dictionariesFolder)
                searchForAnagrams()

            } catch (_) {
                println "Error: ${_.message}"
                System.exit EXIT_ERROR_CODE
            }
        } else {
            println """
Use: 

    groovy src/main/groovy/Anagrammatist <dictionaries folder path>
    
"""
        }
    }

    static boolean dictionaryFolderIsReceived(String... args) {
        1 == args.size()
    }

    static def searchForAnagrams() {
        println 'Use <ctrl> + C to finish'

        def (query, console) = [
                '* Write a word for searching anagrams: ',
                new Scanner(new InputStreamReader(System.in, "UTF-8")),
        ]

        while (true) {
            try {
                print query
                String input = console.nextLine()

                def longestAnagram = anagramSolver.getLongestAnagram(input)
                println "Longest anagram is ... $longestAnagram"

                def allAnagrams = anagramSolver.getAllAnagrams(input)
                prettyPrint input.length(), allAnagrams

                def a = [:]
                a.take(2)
            } catch (_) {
                println _.message
            }
        }
    }

    static def prettyPrint(int originalLenght, Set<String> anagrams) {
        def groupByLength = anagrams.groupBy { it.length() }
        if (originalLenght < 8) {
            println "--- ${anagrams.size()} anagrams found at dictionaries are:"
            printMap groupByLength

        } else {
            println "--- Some of the ${anagrams.size()} anagrams found at dictionaries are:"
            printMap groupByLength.take(2)
        }

        println ''
    }

    static def printMap(Map<Integer, List<String>> listMap) {
        listMap.each {
            def count = it.value.size()
            println "-- $count of length ${it.key}  ${it.value.take(10).join(', ')} ${count > 10 ? '...' : ''}"
        }
    }
}
