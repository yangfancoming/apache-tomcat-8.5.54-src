
package org.apache.tomcat.util.buf;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

/*
 * None of these tests should throw a NPE.
 */
public class TestStringUtils {

    @Test
    public void testNullArray() {
        Assert.assertEquals("", StringUtils.join((String[]) null));
    }


    @Test
    public void testNullArrayCharStringBuilder() {
        StringBuilder sb = new StringBuilder();
        StringUtils.join((String[]) null, ',', sb);
        Assert.assertEquals("", sb.toString());
    }


    @Test
    public void testNullCollection() {
        Assert.assertEquals("", StringUtils.join((Collection<String>) null));
    }


    @Test
    public void testNullCollectionChar() {
        Assert.assertEquals("", StringUtils.join(null, ','));
    }


    @Test
    public void testNullIterableCharStringBuilder() {
        StringBuilder sb = new StringBuilder();
        StringUtils.join((Iterable<String>) null, ',', sb);
        Assert.assertEquals("", sb.toString());
    }


    @Test
    public void testNullArrayCharFunctionStringBuilder() {
        StringBuilder sb = new StringBuilder();
        StringUtils.join((String[]) null, ',', null, sb);
        Assert.assertEquals("", sb.toString());
    }


    @Test
    public void testNullIterableCharFunctionStringBuilder() {
        StringBuilder sb = new StringBuilder();
        StringUtils.join((Iterable<String>) null, ',', null, sb);
        Assert.assertEquals("", sb.toString());
    }
}
