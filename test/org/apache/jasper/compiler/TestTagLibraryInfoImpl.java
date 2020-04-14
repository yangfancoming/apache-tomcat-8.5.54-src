
package org.apache.jasper.compiler;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

/**
 * Test case for {@link TagLibraryInfoImpl}.
 */
public class TestTagLibraryInfoImpl extends TomcatBaseTest {

    @Test
    public void testRelativeTldLocation() throws Exception {
        getTomcatInstanceTestWebapp(false, true);

        ByteChunk res = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() +
                "/test/jsp/test.jsp", res, null);
        Assert.assertEquals(HttpServletResponse.SC_OK, rc);
    }

}
