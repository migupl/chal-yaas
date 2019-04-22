package chal.yaas.messages

import chal.yaas.messages.i18n.UTF8Control

import java.text.MessageFormat

@Singleton
class MessageBundle {

    private static final BUNDLE_PREFIX = 'messages'

    private static final ResourceBundle.Control UTF_8_CONTROL = new UTF8Control()
    private static final RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_PREFIX, locale, UTF_8_CONTROL)

    static String getString(String code, ... args) {
        def message = RESOURCE_BUNDLE.getString(code)

        MessageFormat.format(message, args)
    }

    static Locale getLocale() {
        Locale.getDefault()
    }
}
