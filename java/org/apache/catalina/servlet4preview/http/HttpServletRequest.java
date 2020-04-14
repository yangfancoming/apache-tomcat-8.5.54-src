
package org.apache.catalina.servlet4preview.http;

/**
 * Provides early access to some parts of the Servlet 4.0 API.
 */
public interface HttpServletRequest extends javax.servlet.http.HttpServletRequest {

    public HttpServletMapping getHttpServletMapping();
}
