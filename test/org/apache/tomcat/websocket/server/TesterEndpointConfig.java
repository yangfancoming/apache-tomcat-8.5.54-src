
package org.apache.tomcat.websocket.server;

import javax.servlet.ServletContextEvent;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

public abstract class TesterEndpointConfig extends WsContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        super.contextInitialized(sce);

        ServerContainer sc = (ServerContainer) sce.getServletContext().getAttribute(
                Constants.SERVER_CONTAINER_SERVLET_CONTEXT_ATTRIBUTE);

        try {
            ServerEndpointConfig sec = getServerEndpointConfig();
            if (sec == null) {
                sc.addEndpoint(getEndpointClass());
            } else {
                sc.addEndpoint(sec);
            }
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        }
    }


    protected Class<?> getEndpointClass() {
        return null;
    }


    protected ServerEndpointConfig getServerEndpointConfig() {
        return null;
    }
}
