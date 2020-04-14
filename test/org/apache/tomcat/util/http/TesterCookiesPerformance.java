
package org.apache.tomcat.util.http;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.util.buf.MessageBytes;

public class TesterCookiesPerformance {

    @Test
    public void testPerformance01() throws Exception {
        final int cookieCount = 100;
        final int parsingLoops = 200000;

        MimeHeaders mimeHeaders = new MimeHeaders();

        StringBuilder cookieHeader = new StringBuilder();
        // Create cookies
        for (int i = 0; i < cookieCount; i++) {
            cookieHeader.append("name");
            cookieHeader.append(i);
            cookieHeader.append('=');
            cookieHeader.append("value");
            cookieHeader.append(i);
            cookieHeader.append(';');
        }

        byte[] cookieHeaderBytes = cookieHeader.toString().getBytes("UTF-8");

        MessageBytes headerValue = mimeHeaders.addValue("Cookie");
        headerValue.setBytes(cookieHeaderBytes, 0, cookieHeaderBytes.length);
        ServerCookies serverCookies = new ServerCookies(4);

        LegacyCookieProcessor originalCookieProcessor = new LegacyCookieProcessor();
        Rfc6265CookieProcessor rfc6265CookieProcessor = new Rfc6265CookieProcessor();

        // warm up
        for (int i = 0; i < parsingLoops; i++) {
            originalCookieProcessor.parseCookieHeader(mimeHeaders, serverCookies);
            Assert.assertEquals(cookieCount, serverCookies.getCookieCount());
            serverCookies.recycle();
        }

        long oldStart = System.nanoTime();
        for (int i = 0; i < parsingLoops; i++) {
            originalCookieProcessor.parseCookieHeader(mimeHeaders, serverCookies);
            Assert.assertEquals(cookieCount, serverCookies.getCookieCount());
            serverCookies.recycle();
        }
        long oldDuration = System.nanoTime() - oldStart;

        long newStart = System.nanoTime();
        for (int i = 0; i < parsingLoops; i++) {
            rfc6265CookieProcessor.parseCookieHeader(mimeHeaders, serverCookies);
            Assert.assertEquals(cookieCount, serverCookies.getCookieCount());
            serverCookies.recycle();
        }
        long newDuration = System.nanoTime() - newStart;

        System.out.println("Original duration: " + oldDuration);
        System.out.println("RFC6265 duration:  " + newDuration);
    }
}
