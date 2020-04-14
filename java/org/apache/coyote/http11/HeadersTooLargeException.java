
package org.apache.coyote.http11;

/**
 * Exception used to mark the specific error condition of the HTTP headers
 * exceeding the maximum permitted size.
 */
public class HeadersTooLargeException extends IllegalStateException {

    private static final long serialVersionUID = 1L;

    public HeadersTooLargeException() {
        super();
    }

    public HeadersTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeadersTooLargeException(String s) {
        super(s);
    }

    public HeadersTooLargeException(Throwable cause) {
        super(cause);
    }
}
