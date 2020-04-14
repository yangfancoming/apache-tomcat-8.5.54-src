
package javax.websocket.server;

import java.util.Set;

import javax.websocket.Endpoint;

/**
 * Applications may provide an implementation of this interface to filter the
 * discovered WebSocket endpoints that are deployed. Implementations of this
 * class will be discovered via an ServletContainerInitializer scan.
 */
public interface ServerApplicationConfig {

    /**
     * Enables applications to filter the discovered implementations of
     * {@link ServerEndpointConfig}.
     *
     * @param scanned   The {@link Endpoint} implementations found in the
     *                  application
     * @return  The set of configurations for the endpoint the application
     *              wishes to deploy
     */
    Set<ServerEndpointConfig> getEndpointConfigs(
            Set<Class<? extends Endpoint>> scanned);

    /**
     * Enables applications to filter the discovered classes annotated with
     * {@link ServerEndpoint}.
     *
     * @param scanned   The POJOs annotated with {@link ServerEndpoint} found in
     *                  the application
     * @return  The set of POJOs the application wishes to deploy
     */
    Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned);
}
