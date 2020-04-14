
package org.apache.tomcat.util.http;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.buf.ByteChunk;

/**
 * Test case for {@link LegacyCookieProcessor}. <b>Note</b> because of the use
 * of <code>final static</code> constants in the cookie processing code, each of
 * these tests must be executed in a new JVM instance. The tests have been place
 * in separate classes to facilitate this when running the unit tests via Ant.
 */
public class TestCookiesNoFwdStrictSysProps extends CookiesBaseTest {

    @Override
    @Test
    public void testCookiesInstance() throws Exception {

        System.setProperty("org.apache.catalina.STRICT_SERVLET_COMPLIANCE",
                "true");
        System.setProperty("org.apache.tomcat.util.http.ServerCookie.FWD_SLASH_IS_SEPARATOR",
                "false");

        Tomcat tomcat = getTomcatInstance();

        addServlets(tomcat);

        tomcat.start();

        ByteChunk res = getUrl("http://localhost:" + getPort() + "/invalid");
        Assert.assertEquals("Cookie name fail", res.toString());
        res = getUrl("http://localhost:" + getPort() + "/null");
        Assert.assertEquals("Cookie name fail", res.toString());
        res = getUrl("http://localhost:" + getPort() + "/blank");
        Assert.assertEquals("Cookie name fail", res.toString());
        res = getUrl("http://localhost:" + getPort() + "/invalidFwd");
        Assert.assertEquals("Cookie name ok", res.toString());
        res = getUrl("http://localhost:" + getPort() + "/invalidStrict");
        Assert.assertEquals("Cookie name fail", res.toString());
        res = getUrl("http://localhost:" + getPort() + "/valid");
        Assert.assertEquals("Cookie name ok", res.toString());
    }
}
