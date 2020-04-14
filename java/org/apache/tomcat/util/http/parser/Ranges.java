
package org.apache.tomcat.util.http.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranges {

    private final String units;
    private final List<Entry> entries;


    private Ranges(String units, List<Entry> entries) {
        this.units = units;
        this.entries = Collections.unmodifiableList(entries);
    }


    public List<Entry> getEntries() {
        return entries;
    }

    public String getUnits() {
        return units;
    }


    public static class Entry {

        private final long start;
        private final long end;


        public Entry(long start, long end) {
            this.start = start;
            this.end = end;
        }


        public long getStart() {
            return start;
        }


        public long getEnd() {
            return end;
        }
    }


    /**
     * Parses a Range header from an HTTP header.
     *
     * @param input a reader over the header text
     *
     * @return a set of ranges parsed from the input, or null if not valid
     *
     * @throws IOException if there was a problem reading the input
     */
    public static Ranges parse(StringReader input) throws IOException {

        // Units (required)
        String units = HttpParser.readToken(input);
        if (units == null || units.length() == 0) {
            return null;
        }

        // Must be followed by '='
        if (HttpParser.skipConstant(input, "=") == SkipResult.NOT_FOUND) {
            return null;
        }

        // Range entries
        List<Entry> entries = new ArrayList<>();

        SkipResult skipResult;
        do {
            long start = HttpParser.readLong(input);
            // Must be followed by '-'
            if (HttpParser.skipConstant(input, "-") == SkipResult.NOT_FOUND) {
                return null;
            }
            long end = HttpParser.readLong(input);

            if (start == -1 && end == -1) {
                // Invalid range
                return null;
            }

            entries.add(new Entry(start, end));

            skipResult = HttpParser.skipConstant(input, ",");
            if (skipResult == SkipResult.NOT_FOUND) {
                // Invalid range
                return null;
            }
        } while (skipResult == SkipResult.FOUND);

        // There must be at least one entry
        if (entries.size() == 0) {
            return null;
        }

        return new Ranges(units, entries);
    }
}
