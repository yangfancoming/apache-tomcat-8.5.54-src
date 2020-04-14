

package org.apache.tomcat.util.net.openssl.ciphers;

import org.apache.tomcat.util.net.Constants;

enum Protocol {

    SSLv3(Constants.SSL_PROTO_SSLv3),
    SSLv2(Constants.SSL_PROTO_SSLv2),
    TLSv1(Constants.SSL_PROTO_TLSv1),
    TLSv1_2(Constants.SSL_PROTO_TLSv1_2),
    TLSv1_3(Constants.SSL_PROTO_TLSv1_3);

    private final String openSSLName;

    private Protocol(String openSSLName) {
        this.openSSLName = openSSLName;
    }

    /**
     * The name returned by OpenSSL in the protocol column when using
     * <code>openssl ciphers -v</code>. This is currently only used by the unit
     * tests hence it is package private.
     */
    String getOpenSSLName() {
        return openSSLName;
    }
}
