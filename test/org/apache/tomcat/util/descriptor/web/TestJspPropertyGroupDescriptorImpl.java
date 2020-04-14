
package org.apache.tomcat.util.descriptor.web;

import org.junit.Assert;
import org.junit.Test;

public class TestJspPropertyGroupDescriptorImpl {

    @Test
    public void testPreludesAreIsolated() {
        JspPropertyGroup jpg = new JspPropertyGroup();
        jpg.addIncludePrelude("prelude");
        JspPropertyGroupDescriptorImpl descriptor = new JspPropertyGroupDescriptorImpl(jpg);
        descriptor.getIncludePreludes().clear();
        Assert.assertEquals(1, descriptor.getIncludePreludes().size());
    }

    @Test
    public void testCodasAreIsolated() {
        JspPropertyGroup jpg = new JspPropertyGroup();
        jpg.addIncludeCoda("coda");
        JspPropertyGroupDescriptorImpl descriptor = new JspPropertyGroupDescriptorImpl(jpg);
        descriptor.getIncludeCodas().clear();
        Assert.assertEquals(1, descriptor.getIncludeCodas().size());
    }

    @Test
    public void testUrlPatternsAreIsolated() {
        JspPropertyGroup jpg = new JspPropertyGroup();
        jpg.addUrlPatternDecoded("pattern");
        JspPropertyGroupDescriptorImpl descriptor = new JspPropertyGroupDescriptorImpl(jpg);
        descriptor.getUrlPatterns().clear();
        Assert.assertEquals(1, descriptor.getUrlPatterns().size());
    }
}
