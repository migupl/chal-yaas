class Anagrammatist {

    static void main(String... args) {
        if (dictionaryFolderIsReceived(args)) {
            String dictionary = args[0]
            println "Dictionary folder $dictionary will be loaded"

            searchForAnagrams()

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
            print query
            String input = console.nextLine()

            println "Longest anagram is ... $input"
        }
    }
}