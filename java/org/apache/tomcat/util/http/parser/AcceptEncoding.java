
package org.apache.tomcat.util.http.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class AcceptEncoding {

    private final String encoding;
    private final double quality;

    protected AcceptEncoding(String encoding, double quality) {
        this.encoding = encoding;
        this.quality = quality;
    }

    public String getEncoding() {
        return encoding;
    }

    public double getQuality() {
        return quality;
    }


    public static List<AcceptEncoding> parse(StringReader input) throws IOException {

        List<AcceptEncoding> result = new ArrayList<>();

        do {
            String encoding = HttpParser.readToken(input);
            if (encoding == null) {
                // Invalid encoding, skip to the next one
                HttpParser.skipUntil(input, 0, ',');
                continue;
            }

            if (encoding.length() == 0) {
                // No more data to read
                break;
            }

            // See if a quality has been provided
            double quality = 1;
            SkipResult lookForSemiColon = HttpParser.skipConstant(input, ";");
            if (lookForSemiColon == SkipResult.FOUND) {
                quality = HttpParser.readWeight(input, ',');
            }

            if (quality > 0) {
                result.add(new AcceptEncoding(encoding, quality));
            }
        } while (true);

        return result;
    }
}
