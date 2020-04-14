
package org.apache.jasper.tagplugins.jstl.core;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.util.buf.ByteChunk;

public class TestForEach extends AbstractTestTag {

    @Test
    public void testBug54242() throws Exception {
        ByteChunk res = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug54242.jsp", res, null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        String body = res.toString();
        Assert.assertTrue(body.contains("OK - 1"));
        Assert.assertTrue(body.contains("OK - 2"));
        Assert.assertFalse(body.contains("FAIL"));
    }

    @Test
    public void testBug54888() throws Exception {
        ByteChunk res = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug54888.jsp", res, null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        String body = res.toString();
        Assert.assertTrue(body.contains("OK - 1"));
        Assert.assertTrue(body.contains("OK - 2"));
        Assert.assertTrue(body.contains("OK - 3"));
    }
}
