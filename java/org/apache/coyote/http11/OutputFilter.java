
package org.apache.coyote.http11;

import org.apache.coyote.Response;

/**
 * Output filter.
 *
 * @author Remy Maucherat
 */
public interface OutputFilter extends HttpOutputBuffer {

    /**
     * Some filters need additional parameters from the response. All the
     * necessary reading can occur in that method, as this method is called
     * after the response header processing is complete.
     *
     * @param response The response to associate with this OutputFilter
     */
    public void setResponse(Response response);


    /**
     * Make the filter ready to process the next request.
     */
    public void recycle();


    /**
     * Set the next buffer in the filter pipeline.
     *
     * @param buffer The next buffer instance
     */
    public void setBuffer(HttpOutputBuffer buffer);
}
