
package org.apache.tomcat.util.scan;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarInputStream;

/**
 * When using a {@link JarInputStream} with an XML parser, the stream will be
 * closed by the parser. This causes problems if multiple entries from the JAR
 * need to be parsed. This implementation makes {{@link #close()} a NO-OP and
 * adds {@link #reallyClose()} that will close the stream.
 */
public class NonClosingJarInputStream extends JarInputStream {

    public NonClosingJarInputStream(InputStream in, boolean verify)
            throws IOException {
        super(in, verify);
    }

    public NonClosingJarInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    public void close() throws IOException {
        // Make this a NO-OP so that further entries can be read from the stream
    }

    public void reallyClose() throws IOException {
        super.close();
    }
}
