
package org.apache.tomcat.util.net.jsse;

import org.apache.tomcat.util.compat.JreCompat;
import org.apache.tomcat.util.net.Constants;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.apache.tomcat.util.net.SSLHostConfigCertificate;
import org.apache.tomcat.util.net.SSLUtil;

public class TesterBug50640SslImpl extends JSSEImplementation {

    public static final String PROPERTY_VALUE = "magic";


    @Override
    public SSLUtil getSSLUtil(SSLHostConfigCertificate certificate) {
        SSLHostConfig sslHostConfig = certificate.getSSLHostConfig();
        if (sslHostConfig.getProtocols().size() == 1 &&
                sslHostConfig.getProtocols().contains(PROPERTY_VALUE)) {
            if (JreCompat.isJre8Available()) {
                sslHostConfig.setProtocols(Constants.SSL_PROTO_TLSv1_2);
            } else {
                sslHostConfig.setProtocols(Constants.SSL_PROTO_TLSv1);
            }
            return super.getSSLUtil(certificate);
        } else {
            return null;
        }
    }
}
