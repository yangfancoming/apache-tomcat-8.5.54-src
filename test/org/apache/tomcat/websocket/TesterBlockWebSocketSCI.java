
package org.apache.tomcat.websocket;

import java.util.Collections;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

/**
 * This configuration blocks any endpoints discovered by the SCI from being
 * deployed. It is intended to prevent testing errors generated when the
 * WebSocket SCI scans the test classes for endpoints as it will discover
 * multiple endpoints mapped to the same path ('/'). The tests all explicitly
 * configure their required endpoints so have no need for SCI based
 * configuration.
 */
public class TesterBlockWebSocketSCI implements ServerApplicationConfig {

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(
            Set<Class<? extends Endpoint>> scanned) {
        return Collections.emptySet();
    }

    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        return Collections.emptySet();
    }
}
