
package org.apache.catalina.webresources.war;

import java.io.File;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;

public class TestHandlerIntegration extends TomcatBaseTest {

    @Test
    public void testToURI() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File docBase = new File("test/webresources/war-url-connection.war");
        Context ctx = tomcat.addWebapp("/test", docBase.getAbsolutePath());
        skipTldsForResourceJars(ctx);

        ((StandardHost) tomcat.getHost()).setUnpackWARs(false);

        tomcat.start();

        URL url = ctx.getServletContext().getResource("/index.html");
        try {
            url.toURI();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
