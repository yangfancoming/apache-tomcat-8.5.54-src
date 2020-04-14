
package org.apache.catalina.startup;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.loader.WebappClassLoaderBase;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestTomcatClassLoader extends TomcatBaseTest {

    @Test
    public void testDefaultClassLoader() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        // No file system docBase required
        Context ctx = tomcat.addContext("", null);

        Tomcat.addServlet(ctx, "ClassLoaderReport", new ClassLoaderReport(null));
        ctx.addServletMappingDecoded("/", "ClassLoaderReport");

        tomcat.start();

        ByteChunk res = getUrl("http://localhost:" + getPort() + "/");
        Assert.assertEquals("WEBAPP,SYSTEM,OTHER,", res.toString());
    }

    @Test
    public void testNonDefaultClassLoader() throws Exception {

        ClassLoader cl = new URLClassLoader(new URL[0],
                Thread.currentThread().getContextClassLoader());

        Thread.currentThread().setContextClassLoader(cl);

        Tomcat tomcat = getTomcatInstance();
        tomcat.getServer().setParentClassLoader(cl);

        // No file system docBase required
        Context ctx = tomcat.addContext("", null);

        Tomcat.addServlet(ctx, "ClassLoaderReport", new ClassLoaderReport(cl));
        ctx.addServletMappingDecoded("/", "ClassLoaderReport");

        tomcat.start();

        ByteChunk res = getUrl("http://localhost:" + getPort() + "/");
        Assert.assertEquals("WEBAPP,CUSTOM,SYSTEM,OTHER,", res.toString());
    }

    private static final class ClassLoaderReport extends HttpServlet {
        private static final long serialVersionUID = 1L;

        private transient ClassLoader custom;

        public ClassLoaderReport(ClassLoader custom) {
            this.custom = custom;
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            resp.setContentType("text/plain");
            PrintWriter out = resp.getWriter();

            ClassLoader system = ClassLoader.getSystemClassLoader();

            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            while (cl != null) {
                if (system == cl) {
                    out.print("SYSTEM,");
                } else if (custom == cl) {
                    out.print("CUSTOM,");
                } else if (cl instanceof WebappClassLoaderBase) {
                    out.print("WEBAPP,");
                } else {
                    out.print("OTHER,");
                }
                cl = cl.getParent();
            }
        }
    }
}
