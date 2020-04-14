
package javax.servlet.jsp;

/**
 * Contains information about an error, for error pages. The information
 * contained in this instance is meaningless if not used in the context of an
 * error page. To indicate a JSP is an error page, the page author must set the
 * isErrorPage attribute of the page directive to "true".
 *
 * @see PageContext#getErrorData
 * @since 2.0
 */
public final class ErrorData {

    private final Throwable throwable;
    private final int statusCode;
    private final String uri;
    private final String servletName;

    /**
     * Creates a new ErrorData object.
     *
     * @param throwable
     *            The Throwable that is the cause of the error
     * @param statusCode
     *            The status code of the error
     * @param uri
     *            The request URI
     * @param servletName
     *            The name of the servlet invoked
     */
    public ErrorData(Throwable throwable, int statusCode, String uri,
            String servletName) {
        this.throwable = throwable;
        this.statusCode = statusCode;
        this.uri = uri;
        this.servletName = servletName;
    }

    /**
     * Returns the Throwable that caused the error.
     *
     * @return The Throwable that caused the error
     */
    public Throwable getThrowable() {
        return this.throwable;
    }

    /**
     * Returns the status code of the error.
     *
     * @return The status code of the error
     */
    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * Returns the request URI.
     *
     * @return The request URI
     */
    public String getRequestURI() {
        return this.uri;
    }

    /**
     * Returns the name of the servlet invoked.
     *
     * @return The name of the servlet invoked
     */
    public String getServletName() {
        return this.servletName;
    }
}
