
package org.apache.coyote;

/**
 * Used when we need to indicate failure but the (Servlet) API doesn't declare
 * any appropriate exceptions.
 */
public class ProtocolException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProtocolException() {
        super();
    }

    public ProtocolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProtocolException(String message) {
        super(message);
    }

    public ProtocolException(Throwable cause) {
        super(cause);
    }
}
