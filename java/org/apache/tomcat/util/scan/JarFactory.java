
package org.apache.tomcat.util.scan;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;

import org.apache.tomcat.Jar;
import org.apache.tomcat.util.buf.UriUtil;

/**
 * Provide a mechanism to obtain objects that implement {@link Jar}.
 */
public class JarFactory {

    private JarFactory() {
        // Factory class. Hide public constructor.
    }


    public static Jar newInstance(URL url) throws IOException {
        String urlString = url.toString();
        if (urlString.startsWith("jar:file:")) {
            if (urlString.endsWith("!/")) {
                return new JarFileUrlJar(url, true);
            } else {
                return new JarFileUrlNestedJar(url);
            }
        } else if (urlString.startsWith("war:file:")) {
            URL jarUrl = UriUtil.warToJar(url);
            return new JarFileUrlNestedJar(jarUrl);
        } else if (urlString.startsWith("file:")) {
            return new JarFileUrlJar(url, false);
        } else {
            return new UrlJar(url);
        }
    }


    public static URL getJarEntryURL(URL baseUrl, String entryName)
            throws MalformedURLException {

        String baseExternal = baseUrl.toExternalForm();

        if (baseExternal.startsWith("jar")) {
            // Assume this is pointing to a JAR file within a WAR. Java doesn't
            // support jar:jar:file:... so switch to Tomcat's war:file:...
            baseExternal = baseExternal.replaceFirst("^jar:", "war:");
            baseExternal = baseExternal.replaceFirst("!/",
                    Matcher.quoteReplacement(UriUtil.getWarSeparator()));
        }

        return new URL("jar:" + baseExternal + "!/" + entryName);
    }
}
