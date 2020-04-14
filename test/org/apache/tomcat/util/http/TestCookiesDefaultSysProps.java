
package org.apache.tomcat.util.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class TestCookiesDefaultSysProps extends CookiesBaseTest {

    @Override
    @Test
    public void testCookiesInstance() throws Exception {

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
        Assert.assertEquals("Cookie name fail", res.toString());
        res = getUrl("http://localhost:" + getPort() + "/invalidStrict");
        Assert.assertEquals("Cookie name ok", res.toString());
        res = getUrl("http://localhost:" + getPort() + "/valid");
        Assert.assertEquals("Cookie name ok", res.toString());

        // Need to read response headers to test version switching
        Map<String,List<String>> headers = new HashMap<>();
        getUrl("http://localhost:" + getPort() + "/switch", res, headers);
        List<String> cookieHeaders = headers.get("Set-Cookie");
        for (String cookieHeader : cookieHeaders) {
            Assert.assertEquals("name=\"val?ue\"; Version=1", cookieHeader);
        }
    }
}
