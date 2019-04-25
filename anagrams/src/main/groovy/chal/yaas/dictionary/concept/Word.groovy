package chal.yaas.dictionary.concept

class Word {

    final String trimmed
    @Lazy KeyForm keyForm = new KeyForm(trimmed)

    Word(String s) {
        trimmed = s?.trim()
    }

    String asKey() {
        keyForm as String
    }
}
