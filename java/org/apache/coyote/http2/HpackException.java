
package org.apache.coyote.http2;

/**
 * Exception that is thrown when the HPACK compress context is broken. In this
 * case the connection must be closed.
 */
public class HpackException extends Exception {

    private static final long serialVersionUID = 1L;

    public HpackException(String message, Throwable cause) {
        super(message, cause);
    }
    public HpackException(String message) {
        super(message);
    }
    public HpackException() {
        super();
    }
}
