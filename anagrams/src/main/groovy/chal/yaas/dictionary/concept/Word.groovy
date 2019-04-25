package chal.yaas.dictionary.concept

class Word {

    final String trimmed
    final KeyForm keyForm

    Word(String s) {
        trimmed = s?.trim()
        keyForm = new KeyForm(trimmed)
    }

    String asKey() {
        keyForm as String
    }
}
