
package org.apache.catalina.webresources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestClasspathUrlStreamHandler {

    @BeforeClass
    public static void setup() {
        TomcatURLStreamHandlerFactory.getInstance();
    }

    @Test
    public void testClasspathURL01() throws IOException {
        URL u = new URL("classpath:/org/apache/catalina/webresources/LocalStrings.properties");
        InputStream is = u.openStream();
        Properties p = new Properties();
        p.load(is);
        String msg = (String) p.get("dirResourceSet.writeNpe");
        Assert.assertEquals("The input stream may not be null",  msg);
    }
}
