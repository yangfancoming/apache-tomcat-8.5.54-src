
package org.apache.jasper.runtime;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestJspWriterImpl extends TomcatBaseTest {

    @Test
    public void bug54241a() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        ByteChunk res = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug54241a.jsp", res, null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        String body = res.toString();
        Assert.assertTrue(body.contains("01: null"));
        Assert.assertTrue(body.contains("02: null"));
    }

    @Test
    public void bug54241b() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        ByteChunk res = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug54241b.jsp", res, null);

        Assert.assertEquals(res.toString(),
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR, rc);
    }
}
