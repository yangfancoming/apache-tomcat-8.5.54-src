
package org.apache.catalina.webresources;

import java.util.regex.Pattern;

import org.junit.Test;

public class TestAbstractFileResourceSetPerformance {

    private static final Pattern UNSAFE_WINDOWS_FILENAME_PATTERN = Pattern.compile(" $|[\"<>]");

    private static final int LOOPS = 10_000_000;

    /*
     * Checking individual characters is about 10 times faster on markt's dev
     * PC for typical length file names. The file names need to get to ~65
     * characters before the Pattern matching is faster.
     */
    @Test
    public void testFileNameFiltering() {

        long start = System.nanoTime();
        for (int i = 0; i < LOOPS; i++) {
            UNSAFE_WINDOWS_FILENAME_PATTERN.matcher("testfile.jsp ").find();
        }
        long end = System.nanoTime();
        System.out.println("Regular expression took " + (end - start) + "ns or " +
                (end-start)/LOOPS + "ns per iteration");

        start = System.nanoTime();
        for (int i = 0; i < LOOPS; i++) {
            checkForBadCharsArray("testfile.jsp ");
        }
        end = System.nanoTime();
        System.out.println("char[] check took " + (end - start) + "ns or " +
                (end-start)/LOOPS + "ns per iteration");

        start = System.nanoTime();
        for (int i = 0; i < LOOPS; i++) {
            checkForBadCharsAt("testfile.jsp ");
        }
        end = System.nanoTime();
        System.out.println("charAt() check took " + (end - start) + "ns or " +
                (end-start)/LOOPS + "ns per iteration");

    }

    private boolean checkForBadCharsArray(String filename) {
        char[] chars = filename.toCharArray();
        for (char c : chars) {
            if (c == '\"' || c == '<' || c == '>') {
                return false;
            }
        }
        if (chars[chars.length -1] == ' ') {
            return false;
        }
        return true;
    }


    private boolean checkForBadCharsAt(String filename) {
        final int len = filename.length();
        for (int i = 0; i < len; i++) {
            char c = filename.charAt(i);
            if (c == '\"' || c == '<' || c == '>') {
                return false;
            }
        }
        if (filename.charAt(len - 1) == ' ') {
            return false;
        }
        return true;
    }
}
