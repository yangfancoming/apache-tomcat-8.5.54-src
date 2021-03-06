
package org.apache.jasper.compiler;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.util.security.Escape;

/**
 * Performance tests for {@link Validator}.
 */
public class TesterValidator {

    private static String[] bug53867TestData = new String[] {
            "Hello World!",
            "<meta http-equiv=\"Content-Language\">",
            "This connection has limited network connectivity.",
            "Please use this web page & to access file server resources." };

    @Test
    public void testBug53867() {
        for (int i = 0; i < 10; i++) {
            doTestBug53867();
        }
    }

    private static void doTestBug53867() {
        int count = 100000;

        for (int j = 0; j < bug53867TestData.length; j++) {
            Assert.assertEquals(doTestBug53867OldVersion(bug53867TestData[j]),
                    Escape.xml(bug53867TestData[j]));
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < bug53867TestData.length; j++) {
                doTestBug53867OldVersion(bug53867TestData[j]);
            }
        }
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < bug53867TestData.length; j++) {
                Escape.xml(bug53867TestData[j]);
            }
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < bug53867TestData.length; j++) {
                doTestBug53867OldVersion(bug53867TestData[j]);
            }
        }
        System.out.println(
                "Old escape:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < bug53867TestData.length; j++) {
                Escape.xml(bug53867TestData[j]);
            }
        }
        System.out.println(
                "New escape:" + (System.currentTimeMillis() - start));
    }

    private static String doTestBug53867OldVersion(String s) {
        if (s == null)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '<') {
                sb.append("&lt;");
            } else if (c == '>') {
                sb.append("&gt;");
            } else if (c == '\'') {
                sb.append("&#039;"); // &apos;
            } else if (c == '&') {
                sb.append("&amp;");
            } else if (c == '"') {
                sb.append("&#034;"); // &quot;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
