
package org.apache.jasper.runtime;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestCustomHttpJspPage extends TomcatBaseTest {

    /*
     * Bug 58444
     */
    @Test
    public void testCustomBasePageWhenUsingTagFiles() throws Exception {
        getTomcatInstanceTestWebapp(true, true);

        ByteChunk out = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() + "/test/bug5nnnn/bug58444.jsp", out, null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        String result = out.toString();

        Assert.assertTrue(result, result.contains("00-PASS"));
    }
}
