
package org.apache.coyote.http2;

import org.junit.Assert;
import org.junit.Test;

public class TestByteUtil {

    @Test
    public void testGet31Bits() {
        byte[] input = new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff };
        int result = ByteUtil.get31Bits(input, 0);
        Assert.assertEquals(0x7fffffff, result);
    }


    @Test
    public void testGetFourBytes() {
        byte[] input = new byte[] { (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff };
        long result = ByteUtil.getFourBytes(input, 0);
        Assert.assertEquals(0xffffffffL, result);
    }

}
