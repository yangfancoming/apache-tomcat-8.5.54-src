
package org.apache.tomcat.util.buf;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class CharsetUtil {

    private CharsetUtil() {
        // Utility class. Hide default constructor.
    }


    public static boolean isAsciiSuperset(Charset charset) {
        // Bytes 0x00 to 0x7F must decode to the first 128 Unicode characters
        CharsetDecoder decoder = charset.newDecoder();
        ByteBuffer inBytes = ByteBuffer.allocate(1);
        CharBuffer outChars;
        for (int i = 0; i < 128; i++) {
            inBytes.clear();
            inBytes.put((byte) i);
            inBytes.flip();
            try {
                outChars = decoder.decode(inBytes);
            } catch (CharacterCodingException e) {
                return false;
            }
            try {
                if (outChars.get() != i) {
                    return false;
                }
            } catch (BufferUnderflowException e) {
                return false;
            }
        }

        return true;
    }
}
