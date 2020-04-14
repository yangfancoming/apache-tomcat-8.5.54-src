
package org.apache.tomcat.util.buf;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class TestCharsetCache {

    @Test
    public void testAllKnownCharsets() {

        Set<String> known = new HashSet<>();
        known.addAll(Arrays.asList(CharsetCache.LAZY_CHARSETS));
        Set<String> initial = new HashSet<>();
        initial.addAll(Arrays.asList(CharsetCache.INITIAL_CHARSETS));

        List<String> cacheMisses = new ArrayList<>();

        for (Charset charset: Charset.availableCharsets().values()) {
            String name = charset.name().toLowerCase(Locale.ENGLISH);

            // No need to test the charsets that are pre-loaded
            if (initial.contains(name)) {
                continue;
            }

            if (!known.contains(name)) {
                cacheMisses.add(name);
            }

            for (String alias : charset.aliases()) {
                alias = alias.toLowerCase(Locale.ENGLISH);
                if (!known.contains(alias)) {
                    cacheMisses.add(alias);
                }
            }
        }

        if (cacheMisses.size() != 0) {
            StringBuilder sb = new StringBuilder();
            Collections.sort(cacheMisses);
            for (String name : cacheMisses) {
                if (sb.length() == 0) {
                    sb.append('"');
                } else {
                    sb.append(", \"");
                }
                sb.append(name.toLowerCase(Locale.ENGLISH));
                sb.append('"');
            }
            System.out.println(sb.toString());
        }

        Assert.assertTrue(cacheMisses.size() == 0);
    }
}
