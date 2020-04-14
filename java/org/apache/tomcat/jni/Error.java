

package org.apache.tomcat.jni;

/** Error
 *
 * @author Mladen Turk
 */
public class Error extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * APR error type.
     */
    private final int error;

    /**
     * A description of the problem.
     */
    private final String description;

    /**
     * Construct an APRException.
     *
     * @param error one of the value in Error
     * @param description error message
     */
    private Error(int error, String description)
    {
        super(error + ": " + description);
        this.error = error;
        this.description = description;
    }

    /**
     * Get the APR error code of the exception.
     *
     * @return error of the Exception
     */
    public int getError()
    {
        return error;
    }

    /**
     * Get the APR description of the exception.
     *
     * @return description of the Exception
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Get the last platform error.
     * @return apr_status_t the last platform error, folded into apr_status_t, on most platforms
     * This retrieves errno, or calls a GetLastError() style function, and
     *      folds it with APR_FROM_OS_ERROR.  Some platforms (such as OS2) have no
     *      such mechanism, so this call may be unsupported.  Do NOT use this
     *      call for socket errors from socket, send, recv etc!
     */
    public static native int osError();

    /**
     * Get the last platform socket error.
     * @return the last socket error, folded into apr_status_t, on all platforms
     * This retrieves errno or calls a GetLastSocketError() style function,
     *      and folds it with APR_FROM_OS_ERROR.
     */
    public static native int netosError();

    /**
     * Return a human readable string describing the specified error.
     * @param statcode The error code the get a string for.
     * @return The error string.
    */
    public static native String strerror(int statcode);

}
