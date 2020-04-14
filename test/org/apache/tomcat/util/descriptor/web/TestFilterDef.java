
package org.apache.tomcat.util.descriptor.web;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test case for {@link FilterDef}.
 */
public class TestFilterDef {

    @Test(expected = IllegalArgumentException.class)
    public void testSetFilterNameNull() {
        new FilterDef().setFilterName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetFilterNameEmptyString() {
        new FilterDef().setFilterName("");
    }

    @Test
    public void testSetFilterName() {
        FilterDef filterDef = new FilterDef();
        filterDef.setFilterName("test");
        Assert.assertEquals("'test' is expected as filter name",
            "test", filterDef.getFilterName());
    }

}