
package org.apache.catalina.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * A {@link javax.servlet.Filter} that initializes the {@link HttpSession} for
 * the {@link HttpServletRequest} by calling its getSession() method.
 * <p>
 * This is required for some operations with WebSocket requests, where it is
 * too late to initialize the HttpSession object, and the current Java WebSocket
 * specification does not provide a way to do so.
 */
public class SessionInitializerFilter implements Filter {

    /**
     * Calls {@link HttpServletRequest}'s getSession() to initialize the
     * HttpSession and continues processing the chain.
     *
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this
     *                 filter to pass the request and response to for further
     *                 processing
     * @throws IOException      if an I/O error occurs during this filter's
     *                          processing of the request
     * @throws ServletException if the processing fails for any other reason
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ((HttpServletRequest)request).getSession();

        chain.doFilter(request, response);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // NO-OP
    }

    @Override
    public void destroy() {
        // NO-OP
    }
}
