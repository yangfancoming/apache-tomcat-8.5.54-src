
package org.apache.catalina.core;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import org.apache.catalina.startup.TomcatBaseTest;

@RunWith(value = Parameterized.class)
public class TestApplicationContextStripPathParams extends TomcatBaseTest {

    private final String input;
    private final String expectedOutput;

    public TestApplicationContextStripPathParams(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameters(name = "{index}: input[{0}]")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            { "/foo", "/foo"},
            { "/foo/", "/foo/"},
            { "/foo/bar", "/foo/bar"},
            { "/foo;", "/foo"},
            { "/foo;/", "/foo/"},
            { "/foo;/bar", "/foo/bar"},
            { "/foo;a=1", "/foo"},
            { "/foo;a=1/", "/foo/"},
            { "/foo;a=1/bar", "/foo/bar"},
            // Arguably not valid but does the right thing anyway
            { ";/foo", "/foo"},
            { ";a=1/foo", "/foo"},
            { ";/foo/bar", "/foo/bar"},
            { ";/foo;a=1/bar", "/foo/bar"},
        });
    }

    @Test
    public void testStringPathParams() {
        String output = ApplicationContext.stripPathParams(input);
        Assert.assertEquals(expectedOutput, output);
    }
}