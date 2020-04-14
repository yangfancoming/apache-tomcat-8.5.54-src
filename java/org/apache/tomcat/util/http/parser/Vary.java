
package org.apache.tomcat.util.http.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

/**
 * @deprecated  Use {@link TokenList}.
 */
@Deprecated
public class Vary {

    private Vary() {
        // Utility class. Hide default constructor.
    }


    public static void parseVary(StringReader input, Set<String> result) throws IOException {
        TokenList.parseTokenList(input, result);
    }
}
