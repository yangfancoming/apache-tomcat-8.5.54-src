
package org.apache.tomcat.util.http.fileupload;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>Abstracts access to the request information needed for file uploads. This
 * interface should be implemented for each type of request that may be
 * handled by FileUpload, such as servlets and portlets.</p>
 *
 * @since FileUpload 1.1
 */
public interface RequestContext {

    /**
     * Retrieve the character encoding for the request.
     *
     * @return The character encoding for the request.
     */
    String getCharacterEncoding();

    /**
     * Retrieve the content type of the request.
     *
     * @return The content type of the request.
     */
    String getContentType();

    /**
     * Retrieve the input stream for the request.
     *
     * @return The input stream for the request.
     *
     * @throws IOException if a problem occurs.
     */
    InputStream getInputStream() throws IOException;

}
