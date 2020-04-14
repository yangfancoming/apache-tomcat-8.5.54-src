

package org.apache.tomcat.dbcp.dbcp2;

import java.util.List;

/**
 * An exception wrapping a list of exceptions.
 *
 * @since 2.4.0
 */
public class ListException extends Exception {

    private static final long serialVersionUID = 1L;

    private final List<Throwable> exceptionList;

    /**
     * Constructs a new exception with the specified detail message. The cause is not initialized, and may subsequently
     * be initialized by a call to {@link #initCause}.
     *
     * @param message
     *            the detail message. The detail message is saved for later retrieval by the {@link #getMessage()}
     *            method.
     * @param exceptionList
     *            a list of exceptions.
     */
    public ListException(final String message, final List<Throwable> exceptionList) {
        super(message);
        this.exceptionList = exceptionList;
    }

    /**
     * Gets the list of exceptions.
     *
     * @return the list of exceptions.
     */
    public List<Throwable> getExceptionList() {
        return exceptionList;
    }

}
