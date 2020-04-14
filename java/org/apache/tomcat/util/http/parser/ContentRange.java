
package org.apache.tomcat.util.http.parser;

import java.io.IOException;
import java.io.StringReader;

public class ContentRange {

    private final String units;
    private final long start;
    private final long end;
    private final long length;


    public ContentRange(String units, long start, long end, long length) {
        this.units = units;
        this.start = start;
        this.end = end;
        this.length = length;
    }


    public String getUnits() {
        return units;
    }


    public long getStart() {
        return start;
    }


    public long getEnd() {
        return end;
    }


    public long getLength() {
        return length;
    }


    /**
     * Parses a Content-Range header from an HTTP header.
     *
     * @param input a reader over the header text
     *
     * @return the range parsed from the input, or null if not valid
     *
     * @throws IOException if there was a problem reading the input
     */
    public static ContentRange parse(StringReader input) throws IOException {
        // Units (required)
        String units = HttpParser.readToken(input);
        if (units == null || units.length() == 0) {
            return null;
        }

        // Must be followed by '='
        if (HttpParser.skipConstant(input, "=") == SkipResult.NOT_FOUND) {
            return null;
        }

        // Start
        long start = HttpParser.readLong(input);

        // Must be followed by '-'
        if (HttpParser.skipConstant(input, "-") == SkipResult.NOT_FOUND) {
            return null;
        }

        // End
        long end = HttpParser.readLong(input);

        // Must be followed by '/'
        if (HttpParser.skipConstant(input, "/") == SkipResult.NOT_FOUND) {
            return null;
        }

        // Length
        long length = HttpParser.readLong(input);

        // Doesn't matter what we look for, result should be EOF
        SkipResult skipResult = HttpParser.skipConstant(input, "X");

        if (skipResult != SkipResult.EOF) {
            // Invalid range
            return null;
        }

        return new ContentRange(units, start, end, length);
    }
}
