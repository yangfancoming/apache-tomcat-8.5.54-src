
package javax.websocket.server;

import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

/**
 * Provides the ability to deploy endpoints programmatically.
 */
public interface ServerContainer extends WebSocketContainer {
    public abstract void addEndpoint(Class<?> clazz) throws DeploymentException;

    public abstract void addEndpoint(ServerEndpointConfig sec)
            throws DeploymentException;
}
