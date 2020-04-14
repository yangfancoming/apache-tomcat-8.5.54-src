
package org.apache.tomcat.websocket.pojo;

import javax.websocket.ClientEndpoint;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.apache.tomcat.websocket.server.TesterEndpointConfig;

public class TesterUtil {

    public static class ServerConfigListener extends TesterEndpointConfig {

        private static Class<?> pojoClazz;

        public static void setPojoClazz(Class<?> pojoClazz) {
            ServerConfigListener.pojoClazz = pojoClazz;
        }


        @Override
        protected Class<?> getEndpointClass() {
            return pojoClazz;
        }
    }


    public static class SingletonConfigurator extends Configurator {

        private static Object instance;

        public static void setInstance(Object instance) {
            SingletonConfigurator.instance = instance;
        }

        @Override
        public <T> T getEndpointInstance(Class<T> clazz)
                throws InstantiationException {
            @SuppressWarnings("unchecked")
            T result = (T) instance;
            return result;
        }
    }


    @ClientEndpoint
    public static final class SimpleClient {
    }
}
