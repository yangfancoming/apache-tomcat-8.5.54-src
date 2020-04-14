
package org.apache.coyote;

import java.io.IOException;

/**
 * This exception is thrown to signal to the Tomcat internals that an error has
 * occurred that requires the connection to be closed. For multiplexed protocols
 * such as HTTP/2, this means the channel must be closed but the connection can
 * continue. For non-multiplexed protocols, the connection must be closed. It
 * corresponds to {@link ErrorState#CLOSE_NOW}.
 */
public class CloseNowException extends IOException {

    private static final long serialVersionUID = 1L;


    public CloseNowException() {
        super();
    }


    public CloseNowException(String message, Throwable cause) {
        super(message, cause);
    }


    public CloseNowException(String message) {
        super(message);
    }


    public CloseNowException(Throwable cause) {
        super(cause);
    }
}
