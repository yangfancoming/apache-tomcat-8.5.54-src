
package org.apache.tomcat.util.http.parser;

import java.io.IOException;
import java.io.StringReader;

import org.apache.tomcat.util.collections.ConcurrentCache;

/**
 * Caches the results of parsing content-type headers.
 */
public class MediaTypeCache {

    private final ConcurrentCache<String,String[]> cache;

    public MediaTypeCache(int size) {
        cache = new ConcurrentCache<>(size);
    }

    /**
     * Looks in the cache and returns the cached value if one is present. If no
     * match exists in the cache, a new parser is created, the input parsed and
     * the results placed in the cache and returned to the user.
     *
     * @param input The content-type header value to parse
     * @return      The results are provided as a two element String array. The
     *                  first element is the media type less the charset and
     *                  the second element is the charset
     */
    public String[] parse(String input) {
        String[] result = cache.get(input);

        if (result != null) {
            return result;
        }

        MediaType m = null;
        try {
            m = MediaType.parseMediaType(new StringReader(input));
        } catch (IOException e) {
            // Ignore - return null
        }
        if (m != null) {
            result = new String[] {m.toStringNoCharset(), m.getCharset()};
            cache.put(input, result);
        }

        return result;
    }
}
