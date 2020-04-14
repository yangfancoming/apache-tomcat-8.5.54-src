
package org.apache.catalina;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface AsyncDispatcher {

    /**
     * Perform an asynchronous dispatch. The method does not check if the
     * request is in an appropriate state for this; it is the caller's
     * responsibility to check this.
     * @param request  The request object to pass to the dispatch target
     * @param response The response object to pass to the dispatch target
     * @throws ServletException if thrown by the dispatch target
     * @throws IOException      if an I/O error occurs while processing the
     *                          dispatch
     */
    public void dispatch(ServletRequest request, ServletResponse response)
            throws ServletException, IOException;
}
