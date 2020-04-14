
package org.apache.catalina.webresources.war;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;

public class TestHandler {

    @Before
    public void register() {
        TomcatURLStreamHandlerFactory.register();
    }


    @Test
    public void testUrlFileInJarInWar() throws Exception {
        doTestUrl("jar:war:", "*/WEB-INF/lib/test.jar!/META-INF/resources/index.html");
    }


    @Test
    public void testUrlJarInWar() throws Exception {
        doTestUrl("war:", "*/WEB-INF/lib/test.jar");
    }


    @Test
    public void testUrlWar() throws Exception {
        doTestUrl("", "");
    }


    private void doTestUrl(String prefix, String suffix) throws Exception {
        File f = new File("test/webresources/war-url-connection.war");
        String fileUrl = f.toURI().toURL().toString();

        String urlString = prefix + fileUrl + suffix;
        URL url = new URL(urlString);

        Assert.assertEquals(urlString, url.toExternalForm());
    }


    @Test
    public void testOldFormat() throws Exception {
        File f = new File("test/webresources/war-url-connection.war");
        String fileUrl = f.toURI().toURL().toString();

        URL indexHtmlUrl = new URL("jar:war:" + fileUrl +
                "^/WEB-INF/lib/test.jar!/META-INF/resources/index.html");

        URLConnection urlConn = indexHtmlUrl.openConnection();
        urlConn.connect();

        int size = urlConn.getContentLength();

        Assert.assertEquals(137, size);
    }
}
