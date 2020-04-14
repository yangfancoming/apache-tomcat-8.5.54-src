
package org.apache.tomcat.util.http;

public class HeaderUtil {

    /**
     * Converts an HTTP header line in byte form to a printable String.
     * Bytes corresponding to visible ASCII characters will converted to those
     * characters. All other bytes (0x00 to 0x1F, 0x7F to OxFF) will be
     * represented in 0xNN form.
     *
     * @param bytes  Contains an HTTP header line
     * @param offset The start position of the header line in the array
     * @param len    The length of the HTTP header line
     *
     * @return A String with non-printing characters replaced by the 0xNN
     *         equivalent
     */
    public static String toPrintableString(byte[] bytes, int offset, int len) {
        StringBuilder result = new StringBuilder();
        for (int i = offset; i < offset + len; i++) {
            char c = (char) (bytes[i] & 0xFF);
            if (c < 0x20 || c > 0x7E) {
                result.append("0x");
                result.append(Character.forDigit((c >> 4) & 0xF, 16));
                result.append(Character.forDigit((c) & 0xF, 16));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }


    private HeaderUtil() {
        // Utility class. Hide default constructor.
    }
}
