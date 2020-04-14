
package org.apache.catalina.core;

import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.modeler.Registry;

public class TestApplicationFilterConfig extends TomcatBaseTest {

    @Test
    public void testBug54170() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        // No file system docBase required
        Context ctx = tomcat.addContext("", null);

        Tomcat.addServlet(ctx, "HelloWorld", new HelloWorldServlet());
        ctx.addServletMappingDecoded("/", "HelloWorld");

        // Add a filter with a name that should be escaped if used in a JMX
        // object name
        FilterDef filterDef = new FilterDef();
        filterDef.setFilterClass(AddDefaultCharsetFilter.class.getName());
        filterDef.setFilterName("bug54170*");
        ctx.addFilterDef(filterDef);

        tomcat.start();

        final MBeanServer mbeanServer =
                Registry.getRegistry(null, null).getMBeanServer();

        // There should be one Servlet MBean registered
        Set<ObjectName> servlets = mbeanServer.queryNames(
                new ObjectName("Tomcat:j2eeType=Servlet,*"), null);
        Assert.assertEquals(1, servlets.size());

        // There should be one Filter MBean registered
        Set<ObjectName> filters = mbeanServer.queryNames(
                new ObjectName("Tomcat:j2eeType=Filter,*"), null);
        Assert.assertEquals(1, filters.size());
    }
}
