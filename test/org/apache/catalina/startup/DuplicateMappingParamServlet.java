
package org.apache.catalina.startup;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Test Mock with wrong Annotation!
 *
 * @author Peter Rossbach
 */
@WebServlet(value = "/annotation/overwrite", urlPatterns ="/param2", name = "param", initParams = {
        @WebInitParam(name = "foo", value = "Hello"),
        @WebInitParam(name = "bar", value = "World!") })
public class DuplicateMappingParamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        out.print("<p>" + getInitParameter("foo") + " "
                + getInitParameter("bar") + "</p>");
    }
}
