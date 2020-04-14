
package org.apache.jasper.compiler;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.junit.Assert;
import org.junit.Test;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.TomcatBaseTest;
import org.apache.jasper.JspCompilationContext;
import org.apache.jasper.compiler.ELInterpreterFactory.DefaultELInterpreter;

public class TestELInterpreterFactory extends TomcatBaseTest {

    public static class SimpleELInterpreter implements ELInterpreter {

        @Override
        public String interpreterCall(JspCompilationContext context,
                boolean isTagFile, String expression, Class<?> expectedType,
                String fnmapvar) {
            return expression;
        }
    }

    @Test
    public void testBug54239() throws Exception {
        Tomcat tomcat = getTomcatInstance();

        File appDir = new File("test/webapp");
        Context ctx = tomcat.addWebapp(null, "/test", appDir.getAbsolutePath());
        tomcat.start();

        ServletContext context = ctx.getServletContext();

        ELInterpreter interpreter =
                ELInterpreterFactory.getELInterpreter(context);
        Assert.assertNotNull(interpreter);
        Assert.assertTrue(interpreter instanceof DefaultELInterpreter);

        context.removeAttribute(ELInterpreter.class.getName());

        context.setAttribute(ELInterpreter.class.getName(),
                SimpleELInterpreter.class.getName());
        interpreter = ELInterpreterFactory.getELInterpreter(context);
        Assert.assertNotNull(interpreter);
        Assert.assertTrue(interpreter instanceof SimpleELInterpreter);

        context.removeAttribute(ELInterpreter.class.getName());

        SimpleELInterpreter simpleInterpreter = new SimpleELInterpreter();
        context.setAttribute(ELInterpreter.class.getName(), simpleInterpreter);
        interpreter = ELInterpreterFactory.getELInterpreter(context);
        Assert.assertNotNull(interpreter);
        Assert.assertTrue(interpreter instanceof SimpleELInterpreter);
        Assert.assertTrue(interpreter == simpleInterpreter);

        context.removeAttribute(ELInterpreter.class.getName());

        ctx.stop();
        ctx.addApplicationListener(Bug54239Listener.class.getName());
        ctx.start();

        interpreter = ELInterpreterFactory.getELInterpreter(ctx.getServletContext());
        Assert.assertNotNull(interpreter);
        Assert.assertTrue(interpreter instanceof SimpleELInterpreter);
    }

    public static class Bug54239Listener implements ServletContextListener {

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            sce.getServletContext().setInitParameter(ELInterpreter.class.getName(),
                    SimpleELInterpreter.class.getName());
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            // NO-OP
        }
    }
}
