
package org.apache.catalina.servlet4preview.http;


/**
 * Provides early access to some parts of the Servlet 4.0 API.
 */
public class HttpServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper
        implements HttpServletRequest {

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     *
     * @throws java.lang.IllegalArgumentException
     *             if the request is null
     */
    public HttpServletRequestWrapper(javax.servlet.http.HttpServletRequest request) {
        super(request);
    }

    private HttpServletRequest _getHttpServletRequest() {
        return (HttpServletRequest) super.getRequest();
    }

    /**
     * The default behavior of this method is to return
     * {@link HttpServletRequest#getHttpServletMapping()} on the wrapped request
     * object.
     *
     * @since Servlet 4.0
     */
    @Override
    public HttpServletMapping getHttpServletMapping() {
        return this._getHttpServletRequest().getHttpServletMapping();
    }
}
