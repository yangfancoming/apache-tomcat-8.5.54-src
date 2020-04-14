
package javax.servlet.jsp.el;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestScopedAttributeELResolver extends TomcatBaseTest {

    @Test
    public void testBug49196() throws Exception {
        getTomcatInstanceTestWebapp(true, true);

        ByteChunk res = getUrl("http://localhost:" + getPort() +
                "/test/bug6nnnn/bug62453.jsp");

        String result = res.toString();
        Assert.assertTrue(result, result.contains("<div>foo:  OK</div>"));
        Assert.assertTrue(result, result.contains("<div>bar:  </div>"));
        Assert.assertTrue(result, result.contains("<div>baz:  </div>"));
    }
}
