package chal.yaas.dictionary

import chal.yaas.dictionary.concept.Word
import chal.yaas.dictionary.criteria.SearchingCriteria

interface DictionarySearchMethod {

    Set<String> search(Word word, SearchingCriteria criteria)
}