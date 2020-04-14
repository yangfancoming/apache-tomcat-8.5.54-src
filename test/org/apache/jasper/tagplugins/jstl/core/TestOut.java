
package org.apache.jasper.tagplugins.jstl.core;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.util.buf.ByteChunk;

public class TestOut extends AbstractTestTag {

    @Test
    public void testBug54011() throws Exception {
        ByteChunk res = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug54011.jsp", res, null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        String body = res.toString();
        Assert.assertTrue(body.contains("OK - 1"));
        Assert.assertTrue(body.contains("OK - 2"));
    }

    @Test
    public void testBug54144() throws Exception {
        ByteChunk res = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/bug5nnnn/bug54144.jsp", res, null);

        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        String body = res.toString();
        Assert.assertTrue(body.contains("OK - 1"));
        Assert.assertTrue(body.contains("OK - 2"));
        Assert.assertTrue(body.contains("OK - 3"));
        Assert.assertTrue(body.contains("OK - 4"));
        Assert.assertFalse(body.contains("FAIL"));
    }
}
