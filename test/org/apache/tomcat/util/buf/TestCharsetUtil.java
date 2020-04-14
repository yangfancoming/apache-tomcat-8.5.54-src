
package org.apache.tomcat.util.buf;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

public class TestCharsetUtil {

    /*
     * Check the standard character sets return the expected values
     */
    @Test
    public void testIsAsciiSupersetStandardCharsets() {
        Assert.assertTrue(CharsetUtil.isAsciiSuperset(StandardCharsets.US_ASCII));
        Assert.assertTrue(CharsetUtil.isAsciiSuperset(StandardCharsets.ISO_8859_1));
        Assert.assertTrue(CharsetUtil.isAsciiSuperset(StandardCharsets.UTF_8));

        Assert.assertFalse(CharsetUtil.isAsciiSuperset(StandardCharsets.UTF_16));
        Assert.assertFalse(CharsetUtil.isAsciiSuperset(StandardCharsets.UTF_16BE));
        Assert.assertFalse(CharsetUtil.isAsciiSuperset(StandardCharsets.UTF_16LE));
    }


    /*
     * More comprehensive test that checks that, part from where the encoding
     * overlaps with ASCII, no valid ASCII bytes are used.
     *
     * This is relatively slow.
     * Only need to run this when we detect a new Charset.
     */
    //@Test
    public void testIsAcsiiSupersetAll() {
        for (Charset charset : Charset.availableCharsets().values()) {
            System.out.println("Testing: " + charset.name());

            if (CharsetUtil.isAsciiSuperset(charset)) {
                // Run a more in-depth check to make sure
                // Encoding Unicode 128 onwards should never generate bytes 0 to 127.
                CharsetEncoder encoder = charset.newEncoder();
                CharBuffer inChars = CharBuffer.allocate(8);
                ByteBuffer outBytes;

                for (int i = 128; i < Character.MAX_CODE_POINT; i++) {
                    inChars.clear();
                    char[] chars = Character.toChars(i);
                    for (char c : chars) {
                        inChars.append(c);
                    }
                    inChars.flip();
                    try {
                        outBytes = encoder.encode(inChars);
                    } catch (CharacterCodingException e) {
                        // Ignore. The encoding can't handle the codepoint. That is fine.
                        continue;
                    }
                    outBytes.flip();
                    while (outBytes.hasRemaining()) {
                        byte b = outBytes.get();
                        // All bytes should have the highest bit set
                        if ((b & 0x80) == 0) {
                            Assert.fail("[" + charset.name() + " is not a superset of ASCII");
                        }
                    }
                }
            } else {
                System.out.println("Not: " + charset.name());
            }
        }
    }
}
