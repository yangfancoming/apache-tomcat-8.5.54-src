
package org.apache.tomcat.util.buf;

import org.junit.Test;

public class TestMessageBytes {

    @Test
    public void testToStringFromNull() {
        MessageBytes mb = MessageBytes.newInstance();
        mb.toString();
    }


    @Test
    public void testToBytesFromNull() {
        MessageBytes mb = MessageBytes.newInstance();
        mb.toBytes();
    }


    @Test
    public void testToCharsFromNull() {
        MessageBytes mb = MessageBytes.newInstance();
        mb.toChars();
    }


    @Test
    public void testToStringAfterRecycle() {
        MessageBytes mb = MessageBytes.newInstance();
        mb.setString("foo");
        mb.recycle();
        mb.toString();
    }


    @Test
    public void testToBytesAfterRecycle() {
        MessageBytes mb = MessageBytes.newInstance();
        mb.setString("foo");
        mb.recycle();
        mb.toBytes();
    }


    @Test
    public void testToCharsAfterRecycle() {
        MessageBytes mb = MessageBytes.newInstance();
        mb.setString("foo");
        mb.recycle();
        mb.toChars();
    }
}
