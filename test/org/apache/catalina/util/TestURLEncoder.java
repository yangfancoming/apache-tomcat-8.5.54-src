
package org.apache.catalina.util;

import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

public class TestURLEncoder {

    private static final String SPACE = " ";
    private static final String DOLLAR = "$";
    private static final String AMPERSAND = "&";
    private static final String AMPERSAND_ENCODED = "%26";

    @Test
    public void testClone() {
        URLEncoder original = new URLEncoder();
        URLEncoder clone = (URLEncoder) original.clone();

        // Ensure encode as space is not shared
        original.setEncodeSpaceAsPlus(true);
        Assert.assertNotEquals(original.encode(SPACE, StandardCharsets.UTF_8),
                clone.encode(SPACE, StandardCharsets.UTF_8));

        // Ensure safe characters is not shared
        original.addSafeCharacter('$');
        Assert.assertNotEquals(original.encode(DOLLAR, StandardCharsets.UTF_8),
                clone.encode(DOLLAR, StandardCharsets.UTF_8));
    }


    @Test
    public void testRemoveSafeCharacter() {
        URLEncoder xml = (URLEncoder) URLEncoder.DEFAULT.clone();
        // This should not encode '&'
        Assert.assertEquals(AMPERSAND, xml.encode(AMPERSAND, StandardCharsets.UTF_8));
        xml.removeSafeCharacter('&');
        Assert.assertEquals(AMPERSAND_ENCODED, xml.encode(AMPERSAND, StandardCharsets.UTF_8));
    }
}
