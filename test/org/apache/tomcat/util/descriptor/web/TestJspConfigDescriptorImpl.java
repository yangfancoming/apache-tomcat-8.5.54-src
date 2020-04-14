
package org.apache.tomcat.util.descriptor.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.descriptor.TaglibDescriptor;

import org.junit.Assert;
import org.junit.Test;

public class TestJspConfigDescriptorImpl {

    @Test
    public void testTaglibsAreIsolate() {
        List<TaglibDescriptor> taglibs = new ArrayList<>();
        taglibs.add(new TaglibDescriptorImpl("location", "uri"));
        List<JspPropertyGroupDescriptor> propertyGroups = Collections.emptyList();
        JspConfigDescriptor descriptor = new JspConfigDescriptorImpl(propertyGroups, taglibs);
        descriptor.getTaglibs().clear();
        Assert.assertEquals(taglibs, descriptor.getTaglibs());
    }

    @Test
    public void testPropertyGroupsAreIsolate() {
        List<TaglibDescriptor> taglibs = Collections.emptyList();
        List<JspPropertyGroupDescriptor> propertyGroups = new ArrayList<>();
        propertyGroups.add(new JspPropertyGroupDescriptorImpl(new JspPropertyGroup()));
        JspConfigDescriptor descriptor = new JspConfigDescriptorImpl(propertyGroups, taglibs);
        descriptor.getJspPropertyGroups().clear();
        Assert.assertEquals(propertyGroups, descriptor.getJspPropertyGroups());
    }
}
