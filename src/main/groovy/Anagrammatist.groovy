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

            } catch (_) {
                println _.message
            }
        }
    }
}
