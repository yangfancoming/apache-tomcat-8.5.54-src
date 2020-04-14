
package org.apache.tomcat.util.bcel.classfile;

import java.io.DataInput;
import java.io.IOException;

import org.apache.tomcat.util.bcel.Const;

/**
 * This class is derived from the abstract
 * <A HREF="org.apache.tomcat.util.bcel.classfile.Constant.html">Constant</A> class
 * and represents a reference to a Utf8 encoded string.
 *
 * @see     Constant
 */
public final class ConstantUtf8 extends Constant {

    private final String bytes;

    static ConstantUtf8 getInstance(final DataInput input) throws IOException {
        return new ConstantUtf8(input.readUTF());
    }

    /**
     * @param bytes Data
     */
    private ConstantUtf8(final String bytes) {
        super(Const.CONSTANT_Utf8);
        if (bytes == null) {
            throw new IllegalArgumentException("bytes must not be null!");
        }
        this.bytes = bytes;
    }

    /**
     * @return Data converted to string.
     */
    public final String getBytes() {
        return bytes;
    }
}
