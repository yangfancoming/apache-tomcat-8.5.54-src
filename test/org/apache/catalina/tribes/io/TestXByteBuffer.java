
package org.apache.catalina.tribes.io;

import org.junit.Assert;
import org.junit.Test;

public class TestXByteBuffer {

    @Test
    public void testEmptyArray() throws Exception {
        Object obj = XByteBuffer.deserialize(new byte[0]);
        Assert.assertNull(obj);
    }

    @Test
    public void testSerializationString() throws Exception {
        String test = "This is as test.";
        byte[] msg = XByteBuffer.serialize(test);
        Object obj = XByteBuffer.deserialize(msg);
        Assert.assertTrue(obj instanceof String);
        Assert.assertEquals(test, obj);
    }
}
