class Anagrammatist {

    static void main(String... args) {
        if (dictionaryFolderIsReceived(args)) {
            String dictionary = args[0]
            println "Dictionary folder $dictionary will be loaded"

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
}