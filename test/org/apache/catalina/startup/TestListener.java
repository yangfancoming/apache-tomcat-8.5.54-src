
package org.apache.catalina.startup;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;

public class TestListener extends TomcatBaseTest {

    /*
     * Check that a ServletContainerInitializer can install a
     * {@link ServletContextListener} and that it gets initialized.
     * @throws Exception
     */
    @Test
    public void testServletContainerInitializer() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        // No file system docBase required
        Context context = tomcat.addContext("", null);

        context.addServletContainerInitializer(new SCI(), null);
        tomcat.start();
        Assert.assertTrue(SCL.initialized);
    }

    /*
     * Check that a {@link ServletContextListener} cannot install a
     * {@link ServletContainerInitializer}.
     * @throws Exception
     */
    @Test
    public void testServletContextListener() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        // No file system docBase required
        Context context = tomcat.addContext("", null);

        // SCL2 pretends to be in web.xml, and tries to install a
        // ServletContainerInitializer.
        context.addApplicationListener(SCL2.class.getName());
        tomcat.start();

        //check that the ServletContainerInitializer wasn't initialized.
        Assert.assertFalse(SCL3.initialized);
    }

    public static class SCI implements ServletContainerInitializer {

        @Override
        public void onStartup(Set<Class<?>> c, ServletContext ctx)
                throws ServletException {
            ctx.addListener(new SCL());
        }
    }

    public static class SCL implements ServletContextListener {

        static boolean initialized = false;

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            initialized = true;
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            // NOOP
        }
    }

    public static class SCL2 implements ServletContextListener {

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            ServletContext sc = sce.getServletContext();
            sc.addListener(SCL3.class.getName());
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            // NOOP
        }
    }

    public static class SCL3 implements ServletContextListener {

        static boolean initialized = false;

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            initialized = true;
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            // NOOP
        }
    }
}
