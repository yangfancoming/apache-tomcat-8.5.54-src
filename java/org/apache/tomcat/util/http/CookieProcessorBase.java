
package org.apache.tomcat.util.http;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public abstract class CookieProcessorBase implements CookieProcessor {

    private static final String COOKIE_DATE_PATTERN = "EEE, dd-MMM-yyyy HH:mm:ss z";

    protected static final ThreadLocal<DateFormat> COOKIE_DATE_FORMAT =
        new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            DateFormat df =
                new SimpleDateFormat(COOKIE_DATE_PATTERN, Locale.US);
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            return df;
        }
    };

    protected static final String ANCIENT_DATE;

    static {
        ANCIENT_DATE = COOKIE_DATE_FORMAT.get().format(new Date(10000));
    }

    private SameSiteCookies sameSiteCookies = SameSiteCookies.UNSET;

    public SameSiteCookies getSameSiteCookies() {
        return sameSiteCookies;
    }

    public void setSameSiteCookies(String sameSiteCookies) {
        this.sameSiteCookies = SameSiteCookies.fromString(sameSiteCookies);
    }

    /**
     * {@inheritDoc}
     *
     * @deprecated This implementation calls the deprecated
     *             {@link #generateHeader(Cookie)} method. Implementors should
     *             not rely on this method as it is present only for
     *             transitional compatibility and will be removed in Tomcat 9.
     */
    @Deprecated
    @Override
    public String generateHeader(Cookie cookie, HttpServletRequest request) {
        return generateHeader(cookie);
    }
}
