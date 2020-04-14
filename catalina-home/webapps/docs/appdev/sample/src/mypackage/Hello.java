
package mypackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Simple servlet to validate that the Hello, World example can
 * execute servlets.  In the web application deployment descriptor,
 * this servlet must be mapped to correspond to the link in the
 * "index.html" file.
 *
 * @author Craig R. McClanahan <Craig.McClanahan@eng.sun.com>
 */

public final class Hello extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Respond to a GET request for the content produced by
     * this servlet.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are producing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
      throws IOException, ServletException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = response.getWriter()) {

            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>Sample Application Servlet Page</title>");
            writer.println("</head>");
            writer.println("<body>");


            writer.println("<div style=\"float: left; padding: 10px;\">");
            writer.println("<img src=\"images/tomcat.gif\" alt=\"\" />");
            writer.println("</div>");
            writer.println("<h1>Sample Application Servlet</h1>");
            writer.println("<p>");
            writer.println("This is the output of a servlet that is part of");
            writer.println("the Hello, World application.");
            writer.println("</p>");

            writer.println("</body>");
            writer.println("</html>");
        }
    }


}
