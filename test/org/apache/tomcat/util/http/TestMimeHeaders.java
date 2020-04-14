
package org.apache.tomcat.util.http;

import org.junit.Assert;
import org.junit.Test;

public class TestMimeHeaders {

    public static final String HEADER_NAME_LC_STRING = "test";
    public static final String HEADER_NAME_UC_STRING = "TEST";
    public static final String HEADER_NAME_MIXED_STRING = "tEsT";

    @Test
    public void testSetValueStringIgnoresCase01() {
        MimeHeaders mh = new MimeHeaders();

        mh.setValue(HEADER_NAME_LC_STRING).setString(HEADER_NAME_LC_STRING);
        mh.setValue(HEADER_NAME_UC_STRING).setString(HEADER_NAME_UC_STRING);

        Assert.assertEquals(HEADER_NAME_UC_STRING, mh.getValue(HEADER_NAME_UC_STRING).toString());
        Assert.assertEquals(HEADER_NAME_UC_STRING, mh.getValue(HEADER_NAME_LC_STRING).toString());
        Assert.assertEquals(HEADER_NAME_UC_STRING, mh.getValue(HEADER_NAME_MIXED_STRING).toString());
    }

    @Test
    public void testSetValueStringIgnoresCase02() {
        MimeHeaders mh = new MimeHeaders();

        mh.setValue(HEADER_NAME_UC_STRING).setString(HEADER_NAME_UC_STRING);
        mh.setValue(HEADER_NAME_LC_STRING).setString(HEADER_NAME_LC_STRING);

        Assert.assertEquals(HEADER_NAME_LC_STRING, mh.getValue(HEADER_NAME_LC_STRING).toString());
        Assert.assertEquals(HEADER_NAME_LC_STRING, mh.getValue(HEADER_NAME_UC_STRING).toString());
        Assert.assertEquals(HEADER_NAME_LC_STRING, mh.getValue(HEADER_NAME_MIXED_STRING).toString());
    }

    @Test
    public void testSetValueStringIgnoresCase03() {
        MimeHeaders mh = new MimeHeaders();

        mh.setValue(HEADER_NAME_UC_STRING).setString(HEADER_NAME_UC_STRING);
        mh.setValue(HEADER_NAME_MIXED_STRING).setString(HEADER_NAME_MIXED_STRING);

        Assert.assertEquals(HEADER_NAME_MIXED_STRING, mh.getValue(HEADER_NAME_LC_STRING).toString());
        Assert.assertEquals(HEADER_NAME_MIXED_STRING, mh.getValue(HEADER_NAME_UC_STRING).toString());
        Assert.assertEquals(HEADER_NAME_MIXED_STRING, mh.getValue(HEADER_NAME_MIXED_STRING).toString());
    }

}
