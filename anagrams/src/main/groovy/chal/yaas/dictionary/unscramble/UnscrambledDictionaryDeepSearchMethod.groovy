package chal.yaas.dictionary.unscramble

import chal.yaas.dictionary.DictionarySearchMethod
import chal.yaas.dictionary.concept.KeyForm
import chal.yaas.dictionary.concept.Word
import chal.yaas.dictionary.criteria.SearchingCriteria

class UnscrambledDictionaryDeepSearchMethod implements DictionarySearchMethod {

    private CharNode index

    UnscrambledDictionaryDeepSearchMethod(UnscrambledDictionary dictionary) {
        this.index = dictionary.index
    }

    Set<String> search(Word word, SearchingCriteria criteria) {
        criteria.clear()
        deepSearch(index, criteria, word.trimmed, word.keyForm)
        criteria.candidates
    }

    private deepSearch(CharNode keyChar, SearchingCriteria criteria, String original, KeyForm keyForm) {
        def chars = keyChar.nextChar.keySet().intersect(keyForm.toList())

        if (chars) {
            String key = keyForm.toString()

            for (char c in chars) {
                def keyPos = key.indexOf(c as String)
                if (0 <= keyPos) {
                    deepSearch(keyChar.nextChar.get(c), criteria, original, new KeyForm(key.substring(keyPos + 1)))
                    if (!criteria.keepTrying()) break
                }
            }
        }

        criteria.choose(keyChar.words)
    }
}
