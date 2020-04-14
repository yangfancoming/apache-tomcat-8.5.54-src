
package org.apache.catalina.valves;

import org.junit.Assert;
import org.junit.Test;

public class TestExtendedAccessLogValve {

    @Test
    public void alpha() {
        Assert.assertEquals("\"foo\"", ExtendedAccessLogValve.wrap("foo"));
    }

    @Test
    public void testNull() {
        Assert.assertEquals("-", ExtendedAccessLogValve.wrap(null));
    }

    @Test
    public void empty() {
        Assert.assertEquals("\"\"", ExtendedAccessLogValve.wrap(""));
    }

    @Test
    public void singleQuoteMiddle() {
        Assert.assertEquals("\"foo'bar\"", ExtendedAccessLogValve.wrap("foo'bar"));
    }

    @Test
    public void doubleQuoteMiddle() {
        Assert.assertEquals("\"foo\"\"bar\"", ExtendedAccessLogValve.wrap("foo\"bar"));
    }

    @Test
    public void doubleQuoteStart() {
        Assert.assertEquals("\"\"\"foobar\"", ExtendedAccessLogValve.wrap("\"foobar"));
    }

    @Test
    public void doubleQuoteEnd() {
        Assert.assertEquals("\"foobar\"\"\"", ExtendedAccessLogValve.wrap("foobar\""));
    }

    @Test
    public void doubleQuote() {
        Assert.assertEquals("\"\"\"\"", ExtendedAccessLogValve.wrap("\""));
    }
}
