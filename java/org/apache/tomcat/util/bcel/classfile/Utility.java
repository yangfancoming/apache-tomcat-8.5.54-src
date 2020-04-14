
package org.apache.tomcat.util.bcel.classfile;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;

import org.apache.tomcat.util.bcel.Const;

/**
 * Utility functions that do not really belong to any class in particular.
 */
final class Utility {

    private Utility() {
        // Hide default constructor
    }

    /**
     * Shorten long class name <em>str</em>, i.e., chop off the <em>prefix</em>,
     * if the
     * class name starts with this string and the flag <em>chopit</em> is true.
     * Slashes <em>/</em> are converted to dots <em>.</em>.
     *
     * @param str The long class name
     * @return Compacted class name
     */
    static String compactClassName(final String str) {
        return str.replace('/', '.'); // Is `/' on all systems, even DOS
    }

    static String getClassName(final ConstantPool constant_pool, final int index) {
        Constant c = constant_pool.getConstant(index, Const.CONSTANT_Class);
        int i = ((ConstantClass) c).getNameIndex();

        // Finally get the string from the constant pool
        c = constant_pool.getConstant(i, Const.CONSTANT_Utf8);
        String name = ((ConstantUtf8) c).getBytes();

        return compactClassName(name);
    }

    static void skipFully(final DataInput file, final int length) throws IOException {
        int total = file.skipBytes(length);
        if (total != length) {
            throw new EOFException();
        }
    }

    static void swallowFieldOrMethod(final DataInput file)
            throws IOException {
        // file.readUnsignedShort(); // Unused access flags
        // file.readUnsignedShort(); // name index
        // file.readUnsignedShort(); // signature index
        skipFully(file, 6);

        int attributes_count = file.readUnsignedShort();
        for (int i = 0; i < attributes_count; i++) {
            swallowAttribute(file);
        }
    }

    static void swallowAttribute(final DataInput file)
            throws IOException {
        //file.readUnsignedShort();   // Unused name index
        skipFully(file, 2);
        // Length of data in bytes
        int length = file.readInt();
        skipFully(file, length);
    }
}
