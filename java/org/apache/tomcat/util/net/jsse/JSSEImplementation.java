
package org.apache.tomcat.util.net.jsse;

import javax.net.ssl.SSLSession;

import org.apache.tomcat.util.compat.JreCompat;
import org.apache.tomcat.util.net.SSLHostConfigCertificate;
import org.apache.tomcat.util.net.SSLImplementation;
import org.apache.tomcat.util.net.SSLSupport;
import org.apache.tomcat.util.net.SSLUtil;

/* JSSEImplementation:

   Concrete implementation class for JSSE

   @author EKR
*/

public class JSSEImplementation extends SSLImplementation {

    public JSSEImplementation() {
        // Make sure the keySizeCache is loaded now as part of connector startup
        // else the cache will be populated on first use which will slow that
        // request down.
        JSSESupport.init();
    }

    @Override
    public SSLSupport getSSLSupport(SSLSession session) {
        return new JSSESupport(session);
    }

    @Override
    public SSLUtil getSSLUtil(SSLHostConfigCertificate certificate) {
        return new JSSEUtil(certificate);
    }

    @Override
    public boolean isAlpnSupported() {
        return JreCompat.isJre9Available();
    }
}
