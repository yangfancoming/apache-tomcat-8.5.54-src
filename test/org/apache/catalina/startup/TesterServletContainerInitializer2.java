
package org.apache.catalina.startup;

import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class TesterServletContainerInitializer2 implements
        ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx)
            throws ServletException {
        Servlet s = new TesterServlet();
        ServletRegistration.Dynamic r = ctx.addServlet("TesterServlet2", s);
        r.addMapping("/TesterServlet2");
    }

}
