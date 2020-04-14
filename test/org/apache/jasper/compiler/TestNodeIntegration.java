
package org.apache.jasper.compiler;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestNodeIntegration extends TomcatBaseTest {

    @Test
    public void testJspAttributeIsLiteral() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        ByteChunk res = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug55642a.jsp");

        String result = res.toString();

        Assert.assertTrue(
                result.indexOf("/test/bug5nnnn/bug55642b.jsp?foo=bar&a=1&b=2") > 0);
    }
}
