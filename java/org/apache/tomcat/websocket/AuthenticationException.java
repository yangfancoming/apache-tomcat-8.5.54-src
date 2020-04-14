
package org.apache.tomcat.websocket;

/**
 * Exception thrown on authentication error connecting to a remote
 * websocket endpoint.
 */
public class AuthenticationException extends Exception {

    private static final long serialVersionUID = 5709887412240096441L;

    /**
     * Create authentication exception.
     * @param message the error message
     */
    public AuthenticationException(String message) {
        super(message);
    }

}
