
package org.apache.catalina.realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class TestJNDIRealmConvertToHexEscape {

    @Parameterized.Parameters(name = "{index}: in[{0}], out[{1}]")
    public static Collection<Object[]> parameters() {
        List<Object[]> parameterSets = new ArrayList<>();

        parameterSets.add(new String[] { "none", "none" });
        parameterSets.add(new String[] { "\\", "\\" });
        parameterSets.add(new String[] { "\\\\", "\\5C" });
        parameterSets.add(new String[] { "\\5C", "\\5C" });
        parameterSets.add(new String[] { "\\ ", "\\20" });
        parameterSets.add(new String[] { "\\20", "\\20" });
        parameterSets.add(new String[] { "\\ foo", "\\20foo" });
        parameterSets.add(new String[] { "\\20foo", "\\20foo" });
        parameterSets.add(new String[] { "\\  foo", "\\20 foo" });
        parameterSets.add(new String[] { "\\20 foo", "\\20 foo" });
        parameterSets.add(new String[] { "\\ \\ foo", "\\20\\20foo" });
        parameterSets.add(new String[] { "\\20\\20foo", "\\20\\20foo" });
        parameterSets.add(new String[] { "foo\\ ", "foo\\20" });
        parameterSets.add(new String[] { "foo\\20", "foo\\20" });
        parameterSets.add(new String[] { "foo \\ ", "foo \\20" });
        parameterSets.add(new String[] { "foo \\20", "foo \\20" });
        parameterSets.add(new String[] { "foo\\ \\ ", "foo\\20\\20" });
        parameterSets.add(new String[] { "foo\\20\\20", "foo\\20\\20" });

        return parameterSets;
    }


    @Parameter(0)
    public String in;
    @Parameter(1)
    public String out;


    @Test
    public void testConvertToHexEscape() throws Exception {
        String result = JNDIRealm.convertToHexEscape(in);
        Assert.assertEquals(out, result);
    }
}
