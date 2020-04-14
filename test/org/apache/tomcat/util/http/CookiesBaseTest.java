

package org.apache.tomcat.util.http;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;

/**
 * Base Test case for {@link LegacyCookieProcessor}. <b>Note</b> because of the
 * use of <code>static final</code> constants in {@link LegacyCookieProcessor},
 * each of these tests  must be executed in a new JVM instance. The tests have
 * been place in separate classes to facilitate this when running the unit tests
 * via Ant.
 */
public abstract class CookiesBaseTest extends TomcatBaseTest {

    /**
     * Servlet for cookie naming test.
     */
    public static class CookieServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        private final String cookieName;
        private final String cookieValue;

        public CookieServlet(String cookieName, String cookieValue) {
            this.cookieName = cookieName;
            this.cookieValue = cookieValue;
        }

        @Override
        public void doGet(HttpServletRequest req, HttpServletResponse res)
                throws IOException {
            try {
                Cookie cookie = new Cookie(cookieName, cookieValue);
                res.addCookie(cookie);
                res.getWriter().write("Cookie name ok");
            } catch (IllegalArgumentException iae) {
                res.getWriter().write("Cookie name fail");
            }
        }

    }


    public static void addServlets(Tomcat tomcat) {
        // No file system docBase required
        Context ctx = tomcat.addContext("", null);
        ctx.setCookieProcessor(new LegacyCookieProcessor());

        Tomcat.addServlet(ctx, "invalid", new CookieServlet("na;me", "value"));
        ctx.addServletMappingDecoded("/invalid", "invalid");
        Tomcat.addServlet(ctx, "null", new CookieServlet(null, "value"));
        ctx.addServletMappingDecoded("/null", "null");
        Tomcat.addServlet(ctx, "blank", new CookieServlet("", "value"));
        ctx.addServletMappingDecoded("/blank", "blank");
        Tomcat.addServlet(ctx, "invalidFwd",
                new CookieServlet("na/me", "value"));
        ctx.addServletMappingDecoded("/invalidFwd", "invalidFwd");
        Tomcat.addServlet(ctx, "invalidStrict",
                new CookieServlet("$name", "value"));
        ctx.addServletMappingDecoded("/invalidStrict", "invalidStrict");
        Tomcat.addServlet(ctx, "valid", new CookieServlet("name", "value"));
        ctx.addServletMappingDecoded("/valid", "valid");
        Tomcat.addServlet(ctx, "switch", new CookieServlet("name", "val?ue"));
        ctx.addServletMappingDecoded("/switch", "switch");

    }

    public abstract void testCookiesInstance() throws Exception;

}
