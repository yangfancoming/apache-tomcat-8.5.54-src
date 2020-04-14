
package javax.el;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestCompositeELResolver extends TomcatBaseTest {

    @Test
    public void testBug50408() throws Exception {
        getTomcatInstanceTestWebapp(true, true);

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug50408.jsp", new ByteChunk(), null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);
    }
}
