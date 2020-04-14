
package org.apache.tomcat.util.descriptor.web;

import org.junit.Assert;
import org.junit.Test;

public class TestJspPropertyGroup {

    private JspPropertyGroup group = new JspPropertyGroup();

    @Test
    public void testBug55262() {
        group.addIncludePrelude("/prelude");
        group.addIncludePrelude("/prelude");
        group.addIncludeCoda("/coda");
        group.addIncludeCoda("/coda");
        Assert.assertEquals(2, group.getIncludePreludes().size());
        Assert.assertEquals(2, group.getIncludeCodas().size());
    }
}
