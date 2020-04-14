

package org.apache.tomcat.util.buf;

import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for {@link HexUtils}.
 */
public class TestHexUtils {

    private static final String TEST01_STRING = "Hello World";
    private static final byte[] TEST01_BYTES = TEST01_STRING.getBytes(StandardCharsets.UTF_8);
    private static final String TEST02_STRING = "foo";
    private static final byte[] TEST02_BYTES = TEST02_STRING.getBytes(StandardCharsets.UTF_8);

    @Test
    public void testGetDec() {
        Assert.assertEquals(0, HexUtils.getDec('0'));
        Assert.assertEquals(9, HexUtils.getDec('9'));
        Assert.assertEquals(10, HexUtils.getDec('a'));
        Assert.assertEquals(15, HexUtils.getDec('f'));
        Assert.assertEquals(10, HexUtils.getDec('A'));
        Assert.assertEquals(15, HexUtils.getDec('F'));
        Assert.assertEquals(-1, HexUtils.getDec(0));
        Assert.assertEquals(-1, HexUtils.getDec('Z'));
        Assert.assertEquals(-1, HexUtils.getDec(255));
        Assert.assertEquals(-1, HexUtils.getDec(-60));
    }

    @Test
    public void testRoundTrip01() {
        Assert.assertArrayEquals(TEST01_STRING, TEST01_BYTES,
                HexUtils.fromHexString(HexUtils.toHexString(TEST01_BYTES)));
    }

    @Test
    public void testRoundTrip02() {
        Assert.assertArrayEquals(TEST02_STRING, TEST02_BYTES,
                HexUtils.fromHexString(HexUtils.toHexString(TEST02_BYTES)));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFromHex01() {
        HexUtils.fromHexString("Not a hex string");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFromHex02() {
        // Odd number of hex characters
        HexUtils.fromHexString("aaa");
    }
}