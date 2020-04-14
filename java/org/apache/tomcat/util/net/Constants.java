
package org.apache.tomcat.util.net;

public class Constants {

    /**
     * Name of the system property containing
     * the tomcat instance installation path
     */
    public static final String CATALINA_BASE_PROP = "catalina.base";

    /**
     * JSSE and OpenSSL protocol names
     */
    public static final String SSL_PROTO_ALL        = "all";
    public static final String SSL_PROTO_TLS        = "TLS";
    public static final String SSL_PROTO_TLSv1_3    = "TLSv1.3";
    public static final String SSL_PROTO_TLSv1_2    = "TLSv1.2";
    public static final String SSL_PROTO_TLSv1_1    = "TLSv1.1";
    // Two different forms for TLS 1.0
    public static final String SSL_PROTO_TLSv1_0    = "TLSv1.0";
    public static final String SSL_PROTO_TLSv1      = "TLSv1";
    public static final String SSL_PROTO_SSLv3      = "SSLv3";
    public static final String SSL_PROTO_SSLv2      = "SSLv2";
    public static final String SSL_PROTO_SSLv2Hello = "SSLv2Hello";
}
