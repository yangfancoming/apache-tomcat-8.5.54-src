
package org.apache.tomcat.util.scan;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.catalina.util.IOTools;
import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.apache.tomcat.Jar;

public class TestAbstractInputStreamJar {

    @Before
    public void register() {
        TomcatURLStreamHandlerFactory.register();
    }


    @Test
    public void testNestedJarGetInputStream() throws Exception {
        File f = new File("test/webresources/war-url-connection.war");
        StringBuilder sb = new StringBuilder("war:");
        sb.append(f.toURI().toURL());
        sb.append("*/WEB-INF/lib/test.jar");

        Jar jar = JarFactory.newInstance(new URL(sb.toString()));

        InputStream is1 = jar.getInputStream("META-INF/resources/index.html");
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        IOTools.flow(is1, baos1);

        InputStream is2 = jar.getInputStream("META-INF/resources/index.html");
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        IOTools.flow(is2, baos2);

        Assert.assertArrayEquals(baos1.toByteArray(), baos2.toByteArray());
    }
}
