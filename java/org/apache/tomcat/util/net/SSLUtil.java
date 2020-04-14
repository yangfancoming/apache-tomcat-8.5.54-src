
package org.apache.tomcat.util.net;

import java.util.List;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.TrustManager;

/**
 * Provides a common interface for {@link SSLImplementation}s to create the
 * necessary JSSE implementation objects for TLS connections created via the
 * JSSE API.
 */
public interface SSLUtil {

    public SSLContext createSSLContext(List<String> negotiableProtocols) throws Exception;

    public KeyManager[] getKeyManagers() throws Exception;

    public TrustManager[] getTrustManagers() throws Exception;

    public void configureSessionContext(SSLSessionContext sslSessionContext);

    /**
     * The set of enabled protocols is the intersection of the implemented
     * protocols and the configured protocols. If no protocols are explicitly
     * configured, then all of the implemented protocols will be included in the
     * returned array.
     *
     * @return The protocols currently enabled and available for clients to
     *         select from for the associated connection
     *
     * @throws IllegalArgumentException  If there is no intersection between the
     *         implemented and configured protocols
     */
    public String[] getEnabledProtocols() throws IllegalArgumentException;

    /**
     * The set of enabled ciphers is the intersection of the implemented ciphers
     * and the configured ciphers. If no ciphers are explicitly configured, then
     * the default ciphers will be included in the returned array.
     * <p>
     * The ciphers used during the TLS handshake may be further restricted by
     * the {@link #getEnabledProtocols()} and the certificates.
     *
     * @return The ciphers currently enabled and available for clients to select
     *         from for the associated connection
     *
     * @throws IllegalArgumentException  If there is no intersection between the
     *         implemented and configured ciphers
     */
    public String[] getEnabledCiphers() throws IllegalArgumentException;

    /**
     * Optional interface that can be implemented by
     * {@link javax.net.ssl.SSLEngine}s to indicate that they support ALPN and
     * can provided the protocol agreed with the client.
     */
    public interface ProtocolInfo {
        /**
         * ALPN information.
         * @return the protocol selected using ALPN
         */
        public String getNegotiatedProtocol();
    }
}
