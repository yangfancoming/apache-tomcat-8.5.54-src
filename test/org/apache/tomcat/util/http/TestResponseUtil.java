
package org.apache.tomcat.util.http;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.unittest.TesterResponse;

public class TestResponseUtil {

    @Test
    public void testAddValidWithAll() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        response.addHeader("vary", "host");
        Set<String> expected = new HashSet<>();
        expected.add("*");
        doTestAddVaryFieldName(response, "*", expected);
    }


    @Test
    public void testAddAllWithAll() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        response.addHeader("vary", "*");
        Set<String> expected = new HashSet<>();
        expected.add("*");
        doTestAddVaryFieldName(response, "*", expected);
    }


    @Test
    public void testAddAllWithNone() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        Set<String> expected = new HashSet<>();
        expected.add("*");
        doTestAddVaryFieldName(response, "*", expected);
    }


    @Test
    public void testAddValidWithValidSingleHeader() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        response.addHeader("vary", "foo, bar");
        Set<String> expected = new HashSet<>();
        expected.add("bar");
        expected.add("foo");
        expected.add("too");
        doTestAddVaryFieldName(response, "too", expected);
    }


    @Test
    public void testAddValidWithValidSingleHeaderIncludingAll() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        response.addHeader("vary", "foo, *");
        Set<String> expected = new HashSet<>();
        expected.add("*");
        doTestAddVaryFieldName(response, "too", expected);
    }


    @Test
    public void testAddValidWithValidSingleHeaderAlreadyPresent() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        response.addHeader("vary", "foo, bar");
        Set<String> expected = new HashSet<>();
        expected.add("bar");
        expected.add("foo");
        doTestAddVaryFieldName(response, "foo", expected);
    }


    @Test
    public void testAddValidWithValidHeaders() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        response.addHeader("vary", "foo");
        response.addHeader("vary", "bar");
        Set<String> expected = new HashSet<>();
        expected.add("bar");
        expected.add("foo");
        expected.add("too");
        doTestAddVaryFieldName(response, "too", expected);
    }


    @Test
    public void testAddValidWithValidHeadersIncludingAll() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        response.addHeader("vary", "foo");
        response.addHeader("vary", "*");
        Set<String> expected = new HashSet<>();
        expected.add("*");
        doTestAddVaryFieldName(response, "too", expected);
    }


    @Test
    public void testAddValidWithValidHeadersAlreadyPresent() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        response.addHeader("vary", "foo");
        response.addHeader("vary", "bar");
        Set<String> expected = new HashSet<>();
        expected.add("bar");
        expected.add("foo");
        doTestAddVaryFieldName(response, "foo", expected);
    }


    @Test
    public void testAddValidWithPartiallyValidSingleHeader() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        response.addHeader("vary", "{{{, bar");
        Set<String> expected = new HashSet<>();
        expected.add("bar");
        expected.add("too");
        doTestAddVaryFieldName(response, "too", expected);
    }


    @Test
    public void testAddValidWithPartiallyValidSingleHeaderIncludingAll() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        response.addHeader("vary", "{{{, *");
        Set<String> expected = new HashSet<>();
        expected.add("*");
        doTestAddVaryFieldName(response, "too", expected);
    }


    @Test
    public void testAddValidWithPartiallyValidSingleHeaderAlreadyPresent() {
        TesterResponse response = new TesterResponse();
        response.getCoyoteResponse();
        response.addHeader("vary", "{{{, bar");
        Set<String> expected = new HashSet<>();
        expected.add("bar");
        doTestAddVaryFieldName(response, "bar", expected);
    }


    private void doTestAddVaryFieldName(TesterResponse response, String fieldName,
            Set<String> expected) {
        ResponseUtil.addVaryFieldName(response, fieldName);
        // There will now only be one Vary header
        String resultHeader = response.getHeader("vary");
        Set<String> result = new HashSet<>();
        // Deliberately do not use Vary.parseVary as it will skip invalid values.
        for (String value : resultHeader.split(",")) {
            result.add(value.trim());
        }
        Assert.assertEquals(expected, result);
    }


    @Test
    public void testMimeHeadersAddAllWithNone() {
        MimeHeaders mh = new MimeHeaders();
        Set<String> expected = new HashSet<>();
        expected.add("*");
        doTestAddVaryFieldName(mh, "*", expected);
    }


    @Test
    public void testMimeHeadersAddValidWithValidHeaders() {
        MimeHeaders mh = new MimeHeaders();
        mh.addValue("vary").setString("foo");
        mh.addValue("vary").setString("bar");
        Set<String> expected = new HashSet<>();
        expected.add("bar");
        expected.add("foo");
        expected.add("too");
        doTestAddVaryFieldName(mh, "too", expected);
    }

    private void doTestAddVaryFieldName(MimeHeaders mh, String fieldName,
            Set<String> expected) {
        ResponseUtil.addVaryFieldName(mh, fieldName);
        // There will now only be one Vary header
        String resultHeader = mh.getHeader("vary");
        Set<String> result = new HashSet<>();
        // Deliberately do not use Vary.parseVary as it will skip invalid values.
        for (String value : resultHeader.split(",")) {
            result.add(value.trim());
        }
        Assert.assertEquals(expected, result);
    }
}
