
package org.apache.tomcat.util.compat;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.tomcat.util.net.Constants;

/**
 * This class checks for the availability of TLS features.
 *
 * @deprecated Unused. This will be removed in Tomcat 10.
 */
@Deprecated
public class TLS {

    private static final boolean tlsv13Available;

    static {
        boolean ok = false;
        try {
            SSLContext.getInstance(Constants.SSL_PROTO_TLSv1_3);
            ok = true;
        } catch (NoSuchAlgorithmException ex) {
        }
        tlsv13Available = ok;
    }

    public static boolean isTlsv13Available() {
        return tlsv13Available;
    }

}
