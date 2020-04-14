
package javax.servlet.jsp;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestPageContext extends TomcatBaseTest {

    @Test
    public void testBug49196() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        ByteChunk res = getUrl("http://localhost:" + getPort() +
                "/test/bug49nnn/bug49196.jsp");

        String result = res.toString();
        Assert.assertTrue(result.contains("OK"));
    }
}
