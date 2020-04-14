
package org.apache.catalina.ssi;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.RequestUtil;

public class SSIServletRequestUtil {
    /**
     * Return the relative path associated with this servlet. Taken from
     * DefaultServlet.java. Perhaps this should be put in
     * org.apache.catalina.util somewhere? Seems like it would be widely used.
     *
     * @param request
     *            The servlet request we are processing
     * @return the relative path
     */
    public static String getRelativePath(HttpServletRequest request) {
        // Are we being processed by a RequestDispatcher.include()?
        if (request.getAttribute(
                RequestDispatcher.INCLUDE_REQUEST_URI) != null) {
            String result = (String)request.getAttribute(
                    RequestDispatcher.INCLUDE_PATH_INFO);
            if (result == null)
                result = (String)request.getAttribute(
                        RequestDispatcher.INCLUDE_SERVLET_PATH);
            if ((result == null) || (result.equals(""))) result = "/";
            return result;
        }
        // No, extract the desired path directly from the request
        String result = request.getPathInfo();
        if (result == null) {
            result = request.getServletPath();
        }
        if ((result == null) || (result.equals(""))) {
            result = "/";
        }
        return RequestUtil.normalize(result);
    }

}