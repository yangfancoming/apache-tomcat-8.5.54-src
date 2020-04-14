
package org.apache.tomcat.util.http.parser;

import org.junit.Assert;
import org.junit.Test;

public class TestHttpParser {

    @Test
    public void testTokenDel() {
        Assert.assertFalse("DEL is not a token", HttpParser.isToken(127));
    }
}
