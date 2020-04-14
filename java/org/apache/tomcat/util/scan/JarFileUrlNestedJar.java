
package org.apache.tomcat.util.scan;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Implementation of {@link org.apache.tomcat.Jar} that is optimised for file
 * based JAR URLs that refer to a JAR file nested inside a WAR
 * (e.g URLs of the form jar:file: ... .war!/ ... .jar).
 */
public class JarFileUrlNestedJar extends AbstractInputStreamJar {

    private final JarFile warFile;
    private final JarEntry jarEntry;

    public JarFileUrlNestedJar(URL url) throws IOException {
        super(url);
        JarURLConnection jarConn = (JarURLConnection) url.openConnection();
        jarConn.setUseCaches(false);
        warFile = jarConn.getJarFile();

        String urlAsString = url.toString();
        int pathStart = urlAsString.indexOf("!/") + 2;
        String jarPath = urlAsString.substring(pathStart);
        jarEntry = warFile.getJarEntry(jarPath);
    }


    @Override
    public void close() {
        closeStream();
        if (warFile != null) {
            try {
                warFile.close();
            } catch (IOException e) {
                // Ignore
            }
        }
    }


    @Override
    protected NonClosingJarInputStream createJarInputStream() throws IOException {
        return new NonClosingJarInputStream(warFile.getInputStream(jarEntry));
    }
}
