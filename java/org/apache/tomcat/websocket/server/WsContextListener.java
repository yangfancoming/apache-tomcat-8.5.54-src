
package org.apache.tomcat.websocket.server;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * In normal usage, this {@link ServletContextListener} does not need to be
 * explicitly configured as the {@link WsSci} performs all the necessary
 * bootstrap and installs this listener in the {@link ServletContext}. If the
 * {@link WsSci} is disabled, this listener must be added manually to every
 * {@link ServletContext} that uses WebSocket to bootstrap the
 * {@link WsServerContainer} correctly.
 */
public class WsContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        // Don't trigger WebSocket initialization if a WebSocket Server
        // Container is already present
        if (sc.getAttribute(Constants.SERVER_CONTAINER_SERVLET_CONTEXT_ATTRIBUTE) == null) {
            WsSci.init(sce.getServletContext(), false);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        Object obj = sc.getAttribute(Constants.SERVER_CONTAINER_SERVLET_CONTEXT_ATTRIBUTE);
        if (obj instanceof WsServerContainer) {
            ((WsServerContainer) obj).destroy();
        }
    }
}
