
package org.apache.tomcat.util.buf;

import java.math.BigInteger;

import org.apache.tomcat.util.res.StringManager;

/**
 * This is a very basic ASN.1 parser that provides the limited functionality
 * required by Tomcat. It is a long way from a complete parser.
 *
 * TODO: Consider extending this parser and refactoring the SpnegoTokenFixer to
 *       use it.
 */
public class Asn1Parser {

    private static final StringManager sm = StringManager.getManager(Asn1Parser.class);

    private final byte[] source;

    private int pos = 0;


    public Asn1Parser(byte[] source) {
        this.source = source;
    }


    public void parseTag(int tag) {
        int value = next();
        if (value != tag) {
            throw new IllegalArgumentException(sm.getString("asn1Parser.tagMismatch",
                    Integer.valueOf(tag), Integer.valueOf(value)));
        }
    }


    public void parseFullLength() {
        int len = parseLength();
        if (len + pos != source.length) {
            throw new IllegalArgumentException(sm.getString("asn1Parser.lengthInvalid",
                    Integer.valueOf(len), Integer.valueOf(source.length - pos)));
        }
    }


    public int parseLength() {
        int len = next();
        if (len > 127) {
            int bytes = len - 128;
            len = 0;
            for (int i = 0; i < bytes; i++) {
                len = len << 8;
                len = len + next();
            }
        }
        return len;
    }


    public BigInteger parseInt() {
        parseTag(0x02);
        int len = parseLength();
        byte[] val = new byte[len];
        System.arraycopy(source, pos, val, 0, len);
        pos += len;
        return new BigInteger(val);
    }


    public void parseBytes(byte[] dest) {
        System.arraycopy(source, pos, dest, 0, dest.length);
        pos += dest.length;
    }


    private int next() {
        return source[pos++] & 0xFF;
    }
}
