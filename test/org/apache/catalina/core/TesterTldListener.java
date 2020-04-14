
package org.apache.catalina.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TesterTldListener implements ServletContextListener {

    private static StringBuilder log = new StringBuilder();

    public static String getLog() {
        return log.toString();
    }

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext sc = sce.getServletContext();
        servletContext = sc;

        // Try and use one of the Servlet 3.0 methods that should be blocked
        try {
            sc.getEffectiveMajorVersion();
            log.append("FAIL-01");
        } catch (UnsupportedOperationException uoe) {
            log.append("PASS-01");
        } catch (Exception e) {
            log.append("FAIL-02");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Bug 57446. Same ServletContext should be presented as at init
        if (servletContext == sce.getServletContext()) {
            log.append("PASS-02");
        } else {
            //log.append("FAIL-03");
        }
    }
}
