
package org.apache.catalina.webresources.war;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;

public class TestWarURLConnection {

    @Before
    public void register() {
        TomcatURLStreamHandlerFactory.register();
    }


    @Test
    public void testContentLength() throws Exception {
        File f = new File("test/webresources/war-url-connection.war");
        String fileUrl = f.toURI().toURL().toString();

        URL indexHtmlUrl = new URL("jar:war:" + fileUrl +
                "*/WEB-INF/lib/test.jar!/META-INF/resources/index.html");

        URLConnection urlConn = indexHtmlUrl.openConnection();
        urlConn.connect();

        int size = urlConn.getContentLength();

        Assert.assertEquals(137, size);
    }
}
