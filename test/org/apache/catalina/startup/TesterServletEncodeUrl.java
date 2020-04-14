
package org.apache.catalina.startup;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A test servlet that will always encode the url in case the client requires
 * session persistence but is not configured to support cookies.
 */
public class TesterServletEncodeUrl extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Almost minimal processing for a servlet.
     * <p>
     * The request parameter <code>nextUrl</code> specifies the url to which the
     * caller would like to go next. If supplied, put an encoded url into the
     * returned html page as a hyperlink.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.print("OK");

        String param = req.getParameter("nextUrl");
        if (param!=null) {
            // append an encoded url to carry the sessionids
            String targetUrl = resp.encodeURL(param);
            out.print(". You want to go <a href=\"");
            out.print(targetUrl);
            out.print("\">here next</a>.");
        }
    }
}
