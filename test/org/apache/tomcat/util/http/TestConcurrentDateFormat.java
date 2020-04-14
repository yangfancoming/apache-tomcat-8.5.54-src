
package org.apache.tomcat.util.http;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

public class TestConcurrentDateFormat {

    private static final String DATE_RFC5322 = "EEE, dd MMM yyyy HH:mm:ss z";
    TimeZone tz = TimeZone.getTimeZone("GMT");

    @Test
    public void testFormatReturnsGMT() {
        ConcurrentDateFormat format = createConcurrentDateFormat();
        Date date = new Date();
        String formattedDate = format.format(date);
        Assert.assertTrue(formattedDate.endsWith("GMT"));
    }

    @Test
    public void testFormatReturnsGMTAfterParseCET() throws Exception {
        ConcurrentDateFormat format = createConcurrentDateFormat();
        format.parse("Thu, 12 Mar 2020 14:40:22 CET");
        Date date = new Date();
        String formattedDate = format.format(date);
        Assert.assertTrue(formattedDate.endsWith("GMT"));
    }

    private ConcurrentDateFormat createConcurrentDateFormat() {
        return new ConcurrentDateFormat(DATE_RFC5322, Locale.US, tz);
    }
}
