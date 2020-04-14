
package org.apache.tomcat.util.http.fileupload.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.apache.tomcat.util.http.fileupload.UploadContext;


/**
 * <p>Provides access to the request information needed for a request made to
 * an HTTP servlet.</p>
 *
 * @since FileUpload 1.1
 */
public class ServletRequestContext implements UploadContext {

    // ----------------------------------------------------- Instance Variables

    /**
     * The request for which the context is being provided.
     */
    private final HttpServletRequest request;

    // ----------------------------------------------------------- Constructors

    /**
     * Construct a context for this request.
     *
     * @param request The request to which this context applies.
     */
    public ServletRequestContext(HttpServletRequest request) {
        this.request = request;
    }

    // --------------------------------------------------------- Public Methods

    /**
     * Retrieve the character encoding for the request.
     *
     * @return The character encoding for the request.
     */
    @Override
    public String getCharacterEncoding() {
        return request.getCharacterEncoding();
    }

    /**
     * Retrieve the content type of the request.
     *
     * @return The content type of the request.
     */
    @Override
    public String getContentType() {
        return request.getContentType();
    }

    /**
     * Retrieve the content length of the request.
     *
     * @return The content length of the request.
     * @since 1.3
     */
    @Override
    public long contentLength() {
        long size;
        try {
            size = Long.parseLong(request.getHeader(FileUploadBase.CONTENT_LENGTH));
        } catch (NumberFormatException e) {
            size = request.getContentLength();
        }
        return size;
    }

    /**
     * Retrieve the input stream for the request.
     *
     * @return The input stream for the request.
     *
     * @throws IOException if a problem occurs.
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return request.getInputStream();
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a string representation of this object.
     */
    @Override
    public String toString() {
        return String.format("ContentLength=%s, ContentType=%s",
                      Long.valueOf(this.contentLength()),
                      this.getContentType());
    }

}
