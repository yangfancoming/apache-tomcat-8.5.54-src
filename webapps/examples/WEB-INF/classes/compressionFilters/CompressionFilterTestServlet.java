
package compressionFilters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Very Simple test servlet to test compression filter
 * @author Amy Roh
 */
public class CompressionFilterTestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        ServletOutputStream out = response.getOutputStream();
        response.setContentType("text/plain");

        Enumeration<String> e = request.getHeaders("Accept-Encoding");
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            out.println(name);
            if (name.indexOf("gzip") != -1) {
                out.println("gzip supported -- able to compress");
            }
            else {
                out.println("gzip not supported");
            }
        }


        out.println("Compression Filter Test Servlet");
        out.println("Minimum content length for compression is 128 bytes");
        out.println("**********  32 bytes  **********");
        out.println("**********  32 bytes  **********");
        out.println("**********  32 bytes  **********");
        out.println("**********  32 bytes  **********");
        out.close();
    }

}

