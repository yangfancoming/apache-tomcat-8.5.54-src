
package org.apache.tomcat.util.descriptor.web;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test case for {@link ServletDef}
 */
public class TestServletDef {

    @Test(expected = IllegalArgumentException.class)
    public void testSetServletNameNull() {
        new ServletDef().setServletName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetServletNameEmptyString() {
        new ServletDef().setServletName("");
    }

    @Test
    public void testSetServletName() {
        ServletDef servletDef = new ServletDef();
        servletDef.setServletName("test");
        Assert.assertEquals("'test' is expected as servlet name",
            "test", servletDef.getServletName());
    }

}