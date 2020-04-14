
package org.apache.jasper;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestJspCompilationContext extends TomcatBaseTest {

    @Test
    public void testTagFileInJar() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        ByteChunk body = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/jsp/tagFileInJar.jsp", body, null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);
        Assert.assertTrue(body.toString().contains("00 - OK"));
    }


    /*
     * Test case for https://bz.apache.org/bugzilla/show_bug.cgi?id=57626
     */
    @Test
    public void testModifiedTagFileInJar() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        ByteChunk body = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/jsp/tagFileInJar.jsp", body, null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);
        Assert.assertTrue(body.toString().contains("00 - OK"));

        File jsp = new File("test/webapp/jsp/tagFileInJar.jsp");
        Assert.assertTrue("Failed to set last modified for [" + jsp + "]",
                jsp.setLastModified(jsp.lastModified() + 10000));

        // This test requires that modificationTestInterval is set to zero in
        // web.xml. If not, a sleep longer that modificationTestInterval is
        // required here.

        rc = getUrl("http://localhost:" + getPort() +
                "/test/jsp/tagFileInJar.jsp", body, null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);
        Assert.assertTrue(body.toString().contains("00 - OK"));
    }
}
