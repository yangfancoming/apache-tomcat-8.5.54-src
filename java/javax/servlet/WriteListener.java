
package javax.servlet;

import java.io.IOException;

/**
 * Receives notification of write events when using non-blocking IO.
 *
 * @since Servlet 3.1
 */
public interface WriteListener extends java.util.EventListener{

    /**
     * Invoked when it it possible to write data without blocking. The container
     * will invoke this method the first time for a request as soon as data can
     * be written. Subsequent invocations will only occur if a call to
     * {@link ServletOutputStream#isReady()} has returned false and it has since
     * become possible to write data.
     *
     * @throws IOException if an I/O error occurs while processing this event
     */
    public void onWritePossible() throws IOException;

    /**
     * Invoked if an error occurs while writing the response.
     *
     * @param throwable The throwable that represents the error that occurred
     */
    public void onError(java.lang.Throwable throwable);
}