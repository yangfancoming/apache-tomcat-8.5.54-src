
package org.apache.catalina.servlet4preview.http;

import javax.servlet.annotation.WebServlet;

/**
 * Represents how the request from which this object was obtained was mapped to
 * the associated servlet.
 *
 * @since 4.0
 */
public interface HttpServletMapping {

    /**
     * @return The value that was matched or the empty String if not known.
     */
    String getMatchValue();

    /**
     * @return The {@code url-pattern} that matched this request or the empty
     *         String if not known.
     */
    String getPattern();

    /**
     * @return The name of the servlet (as specified in web.xml,
     *         {@link WebServlet#name()},
     *         {@link javax.servlet.ServletContext#addServlet(String, Class)} or
     *         one of the other <code>addServlet()</code> methods) that the
     *         request was mapped to.
     */
    String getServletName();

    /**
     * @return The type of match ({@code null} if not known)
     */
    MappingMatch getMappingMatch();
}
