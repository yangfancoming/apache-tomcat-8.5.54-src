
package javax.servlet.http;

import org.junit.Test;

/**
 * Basic tests for Cookie in default configuration.
 */
public class TestCookieRFC2109Validator {
    static {
        System.setProperty("org.apache.tomcat.util.http.ServerCookie.FWD_SLASH_IS_SEPARATOR", "true");
    }

    private RFC2109Validator validator = new RFC2109Validator();

    @Test
    public void actualCharactersAllowedInName() {
        TestCookie.checkCharInName(validator, TestCookie.TOKEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void leadingDollar() {
        validator.validate("$Version");
    }
}
