
package org.apache.catalina.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

/**
 * Concrete implementation of <code>RequestFilter</code> that filters
 * based on the remote client's host name.
 *
 * @author Craig R. McClanahan
 *
 */
public final class RemoteHostFilter extends RequestFilter {

    // Log must be non-static as loggers are created per class-loader and this
    // Filter may be used in multiple class loaders
    private final Log log = LogFactory.getLog(RemoteHostFilter.class); // must not be static


    /**
     * Extract the desired request property, and pass it (along with the
     * specified request and response objects and associated filter chain) to
     * the protected <code>process()</code> method to perform the actual
     * filtering.
     *
     * @param request  The servlet request to be processed
     * @param response The servlet response to be created
     * @param chain    The filter chain for this request
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        process(request.getRemoteHost(), request, response, chain);

    }

    @Override
    protected Log getLogger() {
        return log;
    }
}
