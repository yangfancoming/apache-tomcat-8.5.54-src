
package javax.servlet.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestServletSecurity extends TomcatBaseTest {

    @Test
    public void testFooThenFooBar() throws Exception {
        doTestFooAndFooBar(true);
    }


    @Test
    public void testFooBarThenFoo() throws Exception {
        doTestFooAndFooBar(false);
    }


    public void doTestFooAndFooBar(boolean fooFirst) throws Exception {
        // Setup Tomcat instance
        Tomcat tomcat = getTomcatInstance();

        // No file system docBase required
        Context ctx = tomcat.addContext("", null);

        Tomcat.addServlet(ctx, "Foo", Foo.class.getName());
        ctx.addServletMappingDecoded("/foo/*", "Foo");

        Tomcat.addServlet(ctx, "FooBar", FooBar.class.getName());
        ctx.addServletMappingDecoded("/foo/bar/*", "FooBar");

        tomcat.start();

        ByteChunk bc = new ByteChunk();
        int rc;

        if (fooFirst) {
            rc = getUrl("http://localhost:" + getPort() + "/foo", bc, null, null);
        } else {
            rc = getUrl("http://localhost:" + getPort() + "/foo/bar", bc, null, null);
        }

        bc.recycle();
        Assert.assertEquals(403, rc);

        if (fooFirst) {
            rc = getUrl("http://localhost:" + getPort() + "/foo/bar", bc, null, null);
        } else {
            rc = getUrl("http://localhost:" + getPort() + "/foo", bc, null, null);
        }

        Assert.assertEquals(403, rc);
    }


    @ServletSecurity(@HttpConstraint(ServletSecurity.EmptyRoleSemantic.DENY))
    public static class Foo extends HttpServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            resp.getWriter().print("OK: Foo");
        }
    }


    public static class FooBar extends HttpServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            resp.getWriter().print("OK: FooBar");
        }
    }
}
