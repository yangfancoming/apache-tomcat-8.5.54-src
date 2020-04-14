
package org.apache.jasper.compiler;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestJspReader extends TomcatBaseTest {

    @Test
    public void testBug53986() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        ByteChunk res = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug53986.jsp");
        Assert.assertTrue(res.toString().contains("OK"));
    }
}
