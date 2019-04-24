package chal.yaas.messages.i18n

import java.nio.charset.StandardCharsets

class UTF8Control extends ResourceBundle.Control {

    private static final UTF_8 = StandardCharsets.UTF_8.name()

    ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader,
                             boolean reload) throws IllegalAccessException, InstantiationException, IOException {
        // The below is a copy of the default implementation.
        String bundleName = toBundleName(baseName, getLocaleForLanguage(locale))
        String resourceName = getRootedResourcesPath(toResourceName(bundleName, "properties"))

        ResourceBundle bundle
        InputStream stream

        if (reload) {
            URL url = loader.getResource(resourceName)
            if (url != null) {
                URLConnection connection = url.openConnection()
                if (connection != null) {
                    connection.setUseCaches(false)
                    stream = connection.getInputStream()
                }
            }
        } else {
            stream = this.class.getResourceAsStream(resourceName)
        }

        if (stream != null) {
            try {
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, UTF_8))

            } finally {
                stream.close()
            }
        }

        bundle
    }

    private static Locale getLocaleForLanguage(Locale locale) {
        new Locale(locale.language)
    }

    private static String getRootedResourcesPath(String resource) {
        resource.startsWith('/') ? resource : "/$resource"
    }
}