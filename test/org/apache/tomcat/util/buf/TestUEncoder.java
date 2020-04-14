

package org.apache.tomcat.util.buf;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.util.buf.UEncoder.SafeCharsSet;

/**
 * Test cases for {@link UEncoder}.
 */
public class TestUEncoder {

    @Test
    public void testEncodeURLWithSlashInit() throws IOException {
        UEncoder urlEncoder = new UEncoder(SafeCharsSet.WITH_SLASH);

        String s = "a+b/c/d+e.class";
        Assert.assertTrue(urlEncoder.encodeURL(s, 0, s.length()).equals(
                "a%2bb/c/d%2be.class"));
        Assert.assertTrue(urlEncoder.encodeURL(s, 2, s.length() - 2).equals(
                "b/c/d%2be.cla"));

        s = new String(new char[] { 0xD801, 0xDC01 });
        Assert.assertTrue(urlEncoder.encodeURL(s, 0, s.length())
                .equals("%f0%90%90%81"));
    }
}
