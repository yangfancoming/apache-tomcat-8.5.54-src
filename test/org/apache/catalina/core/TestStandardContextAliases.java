

package org.apache.catalina.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestStandardContextAliases extends TomcatBaseTest {

    @Test
    public void testDirContextAliases() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        // No file system docBase required
        Context ctx = tomcat.addContext("", null);

        File lib = new File("webapps/examples/WEB-INF/lib");
        ctx.setResources(new StandardRoot(ctx));
        ctx.getResources().createWebResourceSet(
                WebResourceRoot.ResourceSetType.POST, "/WEB-INF/lib",
                lib.getAbsolutePath(), null, "/");


        Tomcat.addServlet(ctx, "test", new TestServlet());
        ctx.addServletMappingDecoded("/", "test");

        tomcat.start();

        ByteChunk res = getUrl("http://localhost:" + getPort() + "/");

        String result = res.toString();
        if (result == null) {
            result = "";
        }

        Assert.assertTrue(result.indexOf("00-PASS") > -1);
        Assert.assertTrue(result.indexOf("01-PASS") > -1);
        Assert.assertTrue(result.indexOf("02-PASS") > -1);
    }


    /**
     * Looks for the JSTL JARs in WEB-INF/lib.
     */
    public static class TestServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            resp.setContentType("text/plain");

            ServletContext context = getServletContext();

            // Check resources individually
            URL url = context.getResource("/WEB-INF/lib/taglibs-standard-spec-1.2.5.jar");
            if (url != null) {
                resp.getWriter().write("00-PASS\n");
            }

            url = context.getResource("/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar");
            if (url != null) {
                resp.getWriter().write("01-PASS\n");
            }

            // Check a directory listing
            Set<String> libs = context.getResourcePaths("/WEB-INF/lib");
            if (libs == null) {
                return;
            }

            if (!libs.contains("/WEB-INF/lib/taglibs-standard-spec-1.2.5.jar")) {
                return;
            }
            if (!libs.contains("/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar")) {
                return;
            }

            resp.getWriter().write("02-PASS\n");
        }

    }
}
