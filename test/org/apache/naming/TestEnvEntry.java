
package org.apache.naming;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.tomcat.util.buf.ByteChunk;

public class TestEnvEntry extends TomcatBaseTest {

    @Test
    public void testEnvEntryBasic() throws Exception {
        doTestJndiLookup("env-entry/basic", "basic-value");
    }


    @Test
    public void testEnvEntryValid() throws Exception {
        doTestJndiLookup("env-entry/valid", "valid");
    }


    @Test
    public void testEnvEntryInvalid() throws Exception {
        doTestJndiLookup("env-entry/invalid", "Not Found");
    }


    @Test
    public void testEnvEntryInjectField() throws Exception {
        doTestJndiInjection("property1", "inject-value-1");
    }


    @Test
    public void testEnvEntryInjectProperty() throws Exception {
        doTestJndiInjection("property2", "inject-value-2");
    }


    @Test
    public void testEnvEntryInjectFieldNoType() throws Exception {
        doTestJndiInjection("property3", "inject-value-3");
    }


    @Test
    public void testEnvEntryInjectionNoValue() throws Exception {
        doTestJndiLookup("env-entry/injectNoValue", "Not Found");
    }


    @Test
    public void testEnvEntryLookup() throws Exception {
        doTestJndiLookup("env-entry/lookup", "basic-value");
    }


    @Test
    public void testEnvEntryLookupCircular() throws Exception {
        doTestJndiLookup("env-entry/circular1", "Naming Error");
    }


    @Test
    public void testEnvEntryLookupInvalid() throws Exception {
        doTestJndiLookup("env-entry/lookup-invalid", "Naming Error");
    }


    private void doTestJndiLookup(String jndiName, String expected) throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp-fragments");
        tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        tomcat.enableNaming();
        tomcat.start();

        ByteChunk out = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() + "/test/jndi.jsp?jndiName=" +
                jndiName, out, null);
        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        // JSP has leading and trailing white-space
        String result = out.toString().trim();
        Assert.assertEquals(expected, result);
    }


    private void doTestJndiInjection(String injectionName, String expected) throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp-fragments");
        Context context = tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());

        Tomcat.addServlet(context, "InjectionServlet", "org.apache.naming.TesterInjectionServlet");
        context.addServletMappingDecoded("/injection", "InjectionServlet");

        tomcat.enableNaming();
        tomcat.start();

        ByteChunk out = new ByteChunk();

        int rc = getUrl("http://localhost:" + getPort() + "/test/injection?injectionName=" +
                injectionName, out, null);
        Assert.assertEquals(HttpServletResponse.SC_OK, rc);

        // JSP has leading and trailing white-space
        String result = out.toString().trim();
        Assert.assertEquals(expected, result);
    }
}
