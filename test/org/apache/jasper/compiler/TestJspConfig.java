

package org.apache.jasper.compiler;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestJspConfig extends TomcatBaseTest {

    @Test
    public void testServlet22NoEL() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp-2.2");
        // app dir is relative to server home
        tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        tomcat.start();

        ByteChunk res = getUrl("http://localhost:" + getPort() +
                "/test/el-as-literal.jsp");

        String result = res.toString();

        Assert.assertTrue(result.indexOf("<p>00-${'hello world'}</p>") > 0);
        Assert.assertTrue(result.indexOf("<p>01-#{'hello world'}</p>") > 0);
    }

    @Test
    public void testServlet23NoEL() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir =
            new File("test/webapp-2.3");
        // app dir is relative to server home
        tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        tomcat.start();

        ByteChunk res = getUrl("http://localhost:" + getPort() +
                "/test/el-as-literal.jsp");

        String result = res.toString();

        Assert.assertTrue(result.indexOf("<p>00-${'hello world'}</p>") > 0);
        Assert.assertTrue(result.indexOf("<p>01-#{'hello world'}</p>") > 0);
    }

    @Test
    public void testServlet24NoEL() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp-2.4");
        // app dir is relative to server home
        tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        tomcat.start();

        ByteChunk res = getUrl("http://localhost:" + getPort() +
                "/test/el-as-literal.jsp");

        String result = res.toString();

        Assert.assertTrue(result.indexOf("<p>00-hello world</p>") > 0);
        Assert.assertTrue(result.indexOf("<p>01-#{'hello world'}</p>") > 0);
    }

    @Test
    public void testServlet25NoEL() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp-2.5");
        // app dir is relative to server home
        tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        tomcat.start();

        ByteChunk res = getUrl("http://localhost:" + getPort() +
                "/test/el-as-literal.jsp");

        String result = res.toString();

        Assert.assertTrue(result.indexOf("<p>00-hello world</p>") > 0);
    }

    @Test
    public void testServlet30NoEL() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp-3.0");
        // app dir is relative to server home
        tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        tomcat.start();

        ByteChunk res = getUrl("http://localhost:" + getPort() +
                "/test/el-as-literal.jsp");

        String result = res.toString();

        Assert.assertTrue(result.indexOf("<p>00-hello world</p>") > 0);
    }

    @Test
    public void testServlet31NoEL() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp-3.1");
        // app dir is relative to server home
        tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        tomcat.start();

        ByteChunk res = getUrl("http://localhost:" + getPort() +
                "/test/el-as-literal.jsp");

        String result = res.toString();

        Assert.assertTrue(result.indexOf("<p>00-hello world</p>") > 0);
    }

}
