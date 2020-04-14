
package javax.websocket;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Use the {@link ServiceLoader} mechanism to provide instances of the WebSocket
 * client container.
 */
public abstract class ContainerProvider {

    private static final String DEFAULT_PROVIDER_CLASS_NAME =
            "org.apache.tomcat.websocket.WsWebSocketContainer";

    /**
     * Create a new container used to create outgoing WebSocket connections.
     *
     * @return A newly created container.
     */
    public static WebSocketContainer getWebSocketContainer() {
        WebSocketContainer result = null;

        ServiceLoader<ContainerProvider> serviceLoader =
                ServiceLoader.load(ContainerProvider.class);
        Iterator<ContainerProvider> iter = serviceLoader.iterator();
        while (result == null && iter.hasNext()) {
            result = iter.next().getContainer();
        }

        // Fall-back. Also used by unit tests
        if (result == null) {
            try {
                @SuppressWarnings("unchecked")
                Class<WebSocketContainer> clazz =
                        (Class<WebSocketContainer>) Class.forName(
                                DEFAULT_PROVIDER_CLASS_NAME);
                result = clazz.getConstructor().newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                    IllegalArgumentException | InvocationTargetException | NoSuchMethodException |
                    SecurityException e) {
                // No options left. Just return null.
            }
        }
        return result;
    }

    protected abstract WebSocketContainer getContainer();
}
