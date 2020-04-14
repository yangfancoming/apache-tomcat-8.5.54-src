
package org.apache.tomcat.util.res;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class TestStringManager {

    private static final String PACKAGE_NAME = "org.apache.tomcat.util";
    private static final StringManager sm = StringManager.getManager(PACKAGE_NAME);

    @Test
    public void testNullKey() {
        boolean iaeThrown = false;

        try {
            sm.getString(null);
        } catch (IllegalArgumentException iae) {
            iaeThrown = true;
        }
        Assert.assertTrue("IAE not thrown on null key", iaeThrown);
    }

    @Test
    public void testBug46933() {
        // Check null args are OK
        sm.getString("namingContext.nameNotBound");
        sm.getString("namingContext.nameNotBound", (Object[]) null);
        sm.getString("namingContext.nameNotBound", new Object[1]);
    }

    @Test
    public void testFrench() {
        StringManager sm = StringManager.getManager(PACKAGE_NAME, Locale.FRENCH);
        Assert.assertEquals(Locale.FRENCH, sm.getLocale());
    }

    @Test
    public void testMissing() {
        Thread.currentThread().setContextClassLoader(TestStringManager.class.getClassLoader());
        StringManager sm = StringManager.getManager("org.does.no.exist");
        Assert.assertNull(sm.getLocale());
    }

}
