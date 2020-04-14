
package org.apache.catalina.webresources;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestFileResource extends TomcatBaseTest {

    @Test
    public void testGetCodePath() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        ByteChunk out = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() + "/test/bug5nnnn/bug58096.jsp", out, null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        // Build the expected location the same way the webapp base dir is built
        File f = new File("test/webapp/WEB-INF/classes");
        Assert.assertEquals(f.toURI().toURL().toString(), out.toString().trim());
    }
}
