
package org.apache.tomcat.util.http;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class TestHeaderUtiltoPrintableString {

    @Parameterized.Parameters(name = "{index}: expected[{1}]")
    public static Collection<Object[]> parameters() {
        List<Object[]> parameterSets = new ArrayList<>();

        parameterSets.add(new String[] { "", "" });

        parameterSets.add(new String[] { "abcd", "abcd" });

        parameterSets.add(new String[] { "\u0000abcd", "0x00abcd" });
        parameterSets.add(new String[] { "ab\u0000cd", "ab0x00cd" });
        parameterSets.add(new String[] { "abcd\u0000", "abcd0x00" });

        parameterSets.add(new String[] { "\tabcd", "0x09abcd" });
        parameterSets.add(new String[] { "ab\tcd", "ab0x09cd" });
        parameterSets.add(new String[] { "abcd\t", "abcd0x09" });

        parameterSets.add(new String[] { " abcd", " abcd" });
        parameterSets.add(new String[] { "ab cd", "ab cd" });
        parameterSets.add(new String[] { "abcd ", "abcd " });

        parameterSets.add(new String[] { "~abcd", "~abcd" });
        parameterSets.add(new String[] { "ab~cd", "ab~cd" });
        parameterSets.add(new String[] { "abcd~", "abcd~" });

        parameterSets.add(new String[] { "\u007fabcd", "0x7fabcd" });
        parameterSets.add(new String[] { "ab\u007fcd", "ab0x7fcd" });
        parameterSets.add(new String[] { "abcd\u007f", "abcd0x7f" });

        parameterSets.add(new String[] { "\u00a3abcd", "0xa3abcd" });
        parameterSets.add(new String[] { "ab\u00a3cd", "ab0xa3cd" });
        parameterSets.add(new String[] { "abcd\u00a3", "abcd0xa3" });

        return parameterSets;
    }


    @Parameter(0)
    public String input;
    @Parameter(1)
    public String expected;


    @Test
    public void doTest() {
        byte[] bytes = input.getBytes(StandardCharsets.ISO_8859_1);

        String result = HeaderUtil.toPrintableString(bytes, 0, bytes.length);

        Assert.assertEquals(expected, result);
    }
}
