
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
 *
 * @author Peter Rossbach
 */
@WebServlet(value = "/annotation/overwrite", name = "param", initParams = {
        @WebInitParam(name = "foo", value = "Hello"),
        @WebInitParam(name = "bar", value = "World!") }, displayName = "param", description = "param", largeIcon = "paramLarge.png", smallIcon = "paramSmall.png", loadOnStartup = 0, asyncSupported = false)
public class ParamServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        out.print("<p>" + getInitParameter("foo") + " "
                + getInitParameter("bar") + "</p>");
    }
}
