
package org.apache.tomcat.util.http.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.buf.MessageBytes;

public class Host {

    /**
     * Parse the given input as an HTTP Host header value.
     *
     * @param mb The host header value
     *
     * @return The position of ':' that separates the host from the port or -1
     *         if it is not present
     *
     * @throws IllegalArgumentException If the host header value is not
     *         specification compliant
     */
    public static int parse(MessageBytes mb) {
        return parse(new MessageBytesReader(mb));
    }


    /**
     * Parse the given input as an HTTP Host header value.
     *
     * @param string The host header value
     *
     * @return The position of ':' that separates the host from the port or -1
     *         if it is not present
     *
     * @throws IllegalArgumentException If the host header value is not
     *         specification compliant
     */
    public static int parse(String string) {
        return parse(new StringReader(string));
    }


    private static int parse(Reader reader) {
        try {
            reader.mark(1);
            int first = reader.read();
            reader.reset();
            if (HttpParser.isAlpha(first)) {
                return HttpParser.readHostDomainName(reader);
            } else if (HttpParser.isNumeric(first)) {
                return HttpParser.readHostIPv4(reader, false);
            } else if ('[' == first) {
                return HttpParser.readHostIPv6(reader);
            } else {
                // Invalid
                throw new IllegalArgumentException();
            }
        } catch (IOException ioe) {
            // Should never happen
            throw new IllegalArgumentException(ioe);
        }
    }


    private static class MessageBytesReader extends Reader {

        private final byte[] bytes;
        private final int end;
        private int pos;
        private int mark;

        public MessageBytesReader(MessageBytes mb) {
            ByteChunk bc = mb.getByteChunk();
            bytes = bc.getBytes();
            pos = bc.getOffset();
            end = bc.getEnd();
        }

        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            for (int i = off; i < off + len; i++) {
                // Want output in range 0 to 255, not -128 to 127
                cbuf[i] = (char) (bytes[pos++] & 0xFF);
            }
            return len;
        }

        @Override
        public void close() throws IOException {
            // NO-OP
        }

        // Over-ridden methods to improve performance

        @Override
        public int read() throws IOException {
            if (pos < end) {
                // Want output in range 0 to 255, not -128 to 127
                return bytes[pos++] & 0xFF;
            } else {
                return -1;
            }
        }

        // Methods to support mark/reset

        @Override
        public boolean markSupported() {
            return true;
        }

        @Override
        public void mark(int readAheadLimit) throws IOException {
            mark = pos;
        }

        @Override
        public void reset() throws IOException {
            pos = mark;
        }
    }
}
