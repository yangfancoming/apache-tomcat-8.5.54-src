
package org.apache.catalina.util;

import org.apache.catalina.Globals;
import org.apache.tomcat.util.net.SSLSupport;

public class TLSUtil {

    /**
     * Determines if the named request attribute is used to pass information
     * about the TLS configuration of the connection to the application. Both
     * the standard request attributes defined by the Servlet specification and
     * Tomcat specific attributes are supported.
     *
     * @param name  The attribute name to test
     *
     * @return {@code true} if the attribute is used to pass TLS configuration
     *         information, otherwise {@code false}
     */
    public static boolean isTLSRequestAttribute(String name) {
        return Globals.CERTIFICATES_ATTR.equals(name) ||
                Globals.CIPHER_SUITE_ATTR.equals(name) ||
                Globals.KEY_SIZE_ATTR.equals(name)  ||
                Globals.SSL_SESSION_ID_ATTR.equals(name) ||
                Globals.SSL_SESSION_MGR_ATTR.equals(name) ||
                SSLSupport.PROTOCOL_VERSION_KEY.equals(name);
    }
}
