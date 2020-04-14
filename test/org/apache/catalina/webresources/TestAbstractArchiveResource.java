
package org.apache.catalina.webresources;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.WebResource;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;

public class TestAbstractArchiveResource extends TomcatBaseTest {

    @Test
    public void testNestedJarGetURL() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File docBase = new File("test/webresources/war-url-connection.war");
        Context ctx = tomcat.addWebapp("/test", docBase.getAbsolutePath());
        skipTldsForResourceJars(ctx);

        ((StandardHost) tomcat.getHost()).setUnpackWARs(false);

        tomcat.start();

        WebResource webResource =
                ctx.getResources().getClassLoaderResource("/META-INF/resources/index.html");

        StringBuilder expectedURL = new StringBuilder("jar:war:");
        expectedURL.append(docBase.getAbsoluteFile().toURI().toURL().toString());
        expectedURL.append("*/WEB-INF/lib/test.jar!/META-INF/resources/index.html");

        Assert.assertEquals(expectedURL.toString(), webResource.getURL().toString());
    }


    @Test
    public void testJarGetURL() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File docBase = new File("test/webapp");
        Context ctx = tomcat.addWebapp("/test", docBase.getAbsolutePath());
        skipTldsForResourceJars(ctx);

        ((StandardHost) tomcat.getHost()).setUnpackWARs(false);

        tomcat.start();

        WebResource webResource =
                ctx.getResources().getClassLoaderResource("/META-INF/tags/echo.tag");

        StringBuilder expectedURL = new StringBuilder("jar:");
        expectedURL.append(docBase.getAbsoluteFile().toURI().toURL().toString());
        expectedURL.append("WEB-INF/lib/test-lib.jar!/META-INF/tags/echo.tag");

        Assert.assertEquals(expectedURL.toString(), webResource.getURL().toString());
    }

}
