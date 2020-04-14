

package org.apache.tomcat.util.net;

import java.security.KeyManagementException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.TrustManager;

/**
 * This interface is needed to override the default SSLContext class
 * to allow SSL implementation pluggability without having to use JCE. With
 * regular JSSE it will do nothing but delegate to the SSLContext.
 */
public interface SSLContext {

    public void init(KeyManager[] kms, TrustManager[] tms,
            SecureRandom sr) throws KeyManagementException;

    public void destroy();

    public SSLSessionContext getServerSessionContext();

    public SSLEngine createSSLEngine();

    public SSLServerSocketFactory getServerSocketFactory();

    public SSLParameters getSupportedSSLParameters();

    public X509Certificate[] getCertificateChain(String alias);

    public X509Certificate[] getAcceptedIssuers();
}
