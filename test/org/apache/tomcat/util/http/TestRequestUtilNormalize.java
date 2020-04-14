
package org.apache.tomcat.util.http;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class TestRequestUtilNormalize {

    @Parameterized.Parameters(name = "{index}: input[{0}]")
    public static Collection<Object[]> parameters() {
        List<Object[]> parameterSets = new ArrayList<>();

        parameterSets.add(new String[] { "//something", "/something" });
        parameterSets.add(new String[] { "some//thing", "/some/thing" });
        parameterSets.add(new String[] { "something//", "/something/" });
        parameterSets.add(new String[] { "//", "/" });
        parameterSets.add(new String[] { "///", "/" });
        parameterSets.add(new String[] { "////", "/" });
        parameterSets.add(new String[] { "/.", "/" });
        parameterSets.add(new String[] { "/./", "/" });
        parameterSets.add(new String[] { ".", "/" });
        parameterSets.add(new String[] { "/..", null });
        parameterSets.add(new String[] { "/../", null });
        parameterSets.add(new String[] { "..", null });
        parameterSets.add(new String[] { "//..", null });
        parameterSets.add(new String[] { "//../", null });
        parameterSets.add(new String[] { "/./..", null });
        parameterSets.add(new String[] { "/./../", null });
        parameterSets.add(new String[] { "/a/../..", null });
        parameterSets.add(new String[] { "/a/../../", null });
        parameterSets.add(new String[] { "/a/..", "/" });
        parameterSets.add(new String[] { "/a/.", "/a" });
        parameterSets.add(new String[] { "/a/../", "/" });
        parameterSets.add(new String[] { "/a/./", "/a/" });
        parameterSets.add(new String[] { "/a/b/..", "/a" });
        parameterSets.add(new String[] { "/a/b/.", "/a/b" });
        parameterSets.add(new String[] { "/a/b/../", "/a/" });
        parameterSets.add(new String[] { "/a/b/./", "/a/b/" });

        return parameterSets;
    }


    @Parameter(0)
    public String input;
    @Parameter(1)
    public String expected;


    @Test
    public void testNormalize() {
        Assert.assertEquals(expected,RequestUtil.normalize(input));
    }
}
