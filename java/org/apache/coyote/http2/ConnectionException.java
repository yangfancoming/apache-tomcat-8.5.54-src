
package org.apache.coyote.http2;

/**
 * Thrown when an HTTP/2 connection error occurs.
 */
public class ConnectionException extends Http2Exception {

    private static final long serialVersionUID = 1L;

    ConnectionException(String msg, Http2Error error) {
        super(msg, error);
    }


    ConnectionException(String msg, Http2Error error, Throwable cause) {
        super(msg, error, cause);
    }
}
