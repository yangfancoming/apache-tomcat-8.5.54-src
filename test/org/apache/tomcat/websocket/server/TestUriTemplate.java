
package org.apache.tomcat.websocket.server;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class TestUriTemplate {

    @Test
    public void testBasic() throws Exception {
        UriTemplate t = new UriTemplate("/{a}/{b}");
        Map<String,String> result = t.match(new UriTemplate("/foo/bar"));

        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.containsKey("a"));
        Assert.assertTrue(result.containsKey("b"));
        Assert.assertEquals("foo", result.get("a"));
        Assert.assertEquals("bar", result.get("b"));
    }


    @Test
    public void testOneOfTwo() throws Exception {
        UriTemplate t = new UriTemplate("/{a}/{b}");
        Map<String,String> result = t.match(new UriTemplate("/foo"));
        Assert.assertNull(result);
    }


    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testBasicPrefix() throws Exception {
        @SuppressWarnings("unused")
        UriTemplate t = new UriTemplate("/x{a}/y{b}");
    }


    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testPrefixOneOfTwo() throws Exception {
        UriTemplate t = new UriTemplate("/x{a}/y{b}");
        t.match(new UriTemplate("/xfoo"));
    }


    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testPrefixTwoOfTwo() throws Exception {
        UriTemplate t = new UriTemplate("/x{a}/y{b}");
        t.match(new UriTemplate("/ybar"));
    }


    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testQuote1() throws Exception {
        UriTemplate t = new UriTemplate("/.{a}");
        t.match(new UriTemplate("/yfoo"));
    }


    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testQuote2() throws Exception {
        @SuppressWarnings("unused")
        UriTemplate t = new UriTemplate("/.{a}");
    }


    @Test
    public void testNoParams() throws Exception {
        UriTemplate t = new UriTemplate("/foo/bar");
        Map<String,String> result = t.match(new UriTemplate("/foo/bar"));

        Assert.assertEquals(0, result.size());
    }


    @Test
    public void testSpecExample1_01() throws Exception {
        UriTemplate t = new UriTemplate("/a/b");
        Map<String,String> result = t.match(new UriTemplate("/a/b"));

        Assert.assertEquals(0, result.size());
    }


    @Test
    public void testSpecExample1_02() throws Exception {
        UriTemplate t = new UriTemplate("/a/b");
        Map<String,String> result = t.match(new UriTemplate("/a"));

        Assert.assertNull(result);
    }


    @Test
    public void testSpecExample1_03() throws Exception {
        UriTemplate t = new UriTemplate("/a/b");
        Map<String,String> result = t.match(new UriTemplate("/a/bb"));

        Assert.assertNull(result);
    }


    @Test
    public void testSpecExample2_01() throws Exception {
        UriTemplate t = new UriTemplate("/a/{var}");
        Map<String,String> result = t.match(new UriTemplate("/a/b"));

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("b", result.get("var"));
    }


    @Test
    public void testSpecExample2_02() throws Exception {
        UriTemplate t = new UriTemplate("/a/{var}");
        Map<String,String> result = t.match(new UriTemplate("/a/apple"));

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("apple", result.get("var"));
    }


    @Test
    public void testSpecExample2_03() throws Exception {
        UriTemplate t = new UriTemplate("/a/{var}");
        Map<String,String> result = t.match(new UriTemplate("/a"));

        Assert.assertNull(result);
    }


    @Test
    public void testSpecExample2_04() throws Exception {
        UriTemplate t = new UriTemplate("/a/{var}");
        Map<String,String> result = t.match(new UriTemplate("/a/b/c"));

        Assert.assertNull(result);
    }


    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testDuplicate01() throws Exception {
        @SuppressWarnings("unused")
        UriTemplate t = new UriTemplate("/{var}/{var}");
    }


    @Test
    public void testDuplicate02() throws Exception {
        UriTemplate t = new UriTemplate("/{a}/{b}");
        Map<String,String> result = t.match(new UriTemplate("/x/x"));

        Assert.assertEquals(2, result.size());
        Assert.assertEquals("x", result.get("a"));
        Assert.assertEquals("x", result.get("b"));
    }


    public void testEgMailingList01() throws Exception {
        UriTemplate t = new UriTemplate("/a/{var}");
        Map<String,String> result = t.match(new UriTemplate("/a/b/"));

        Assert.assertNull(result);
    }


    public void testEgMailingList02() throws Exception {
        UriTemplate t = new UriTemplate("/a/{var}");
        Map<String,String> result = t.match(new UriTemplate("/a/"));

        Assert.assertNull(result);
    }


    @Test
    public void testEgMailingList03() throws Exception {
        UriTemplate t = new UriTemplate("/a/{var}");
        Map<String,String> result = t.match(new UriTemplate("/a"));

        Assert.assertNull(result);
    }


    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testEgMailingList04() throws Exception {
        UriTemplate t = new UriTemplate("/a/{var1}/{var2}");
        @SuppressWarnings("unused")
        Map<String,String> result = t.match(new UriTemplate("/a//c"));
    }


    @Test(expected=java.lang.IllegalArgumentException.class)
    public void testEgMailingList05() throws Exception {
        UriTemplate t = new UriTemplate("/a/{var}/");
        @SuppressWarnings("unused")
        Map<String,String> result = t.match(new UriTemplate("/a/b/"));
    }
}
