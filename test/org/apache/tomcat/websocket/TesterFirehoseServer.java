
package org.apache.tomcat.websocket;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.tomcat.websocket.server.TesterEndpointConfig;

/**
 * Sends {@link #MESSAGE_COUNT} messages of size {@link #MESSAGE_SIZE} bytes as
 * quickly as possible after the client sends its first message.
 */
public class TesterFirehoseServer {

    public static final int MESSAGE_COUNT = 100000;
    public static final String MESSAGE;
    public static final int MESSAGE_SIZE = 1024;
    public static final int WAIT_TIME_MILLIS = 300000;
    public static final int SEND_TIME_OUT_MILLIS = 5000;

    static {
        StringBuilder sb = new StringBuilder(MESSAGE_SIZE);
        for (int i = 0; i < MESSAGE_SIZE; i++) {
            sb.append('x');
        }
        MESSAGE = sb.toString();
    }


    public static class Config extends TesterEndpointConfig {

        public static final String PATH = "/firehose";

        @Override
        protected Class<?> getEndpointClass() {
            return Endpoint.class;
        }
    }


    @ServerEndpoint(Config.PATH)
    public static class Endpoint {

        private static AtomicInteger openConnectionCount = new AtomicInteger(0);
        private static AtomicInteger errorCount = new AtomicInteger(0);

        private volatile boolean started = false;

        public static int getOpenConnectionCount() {
            return openConnectionCount.intValue();
        }

        public static int getErrorCount() {
            return errorCount.intValue();
        }

        @OnOpen
        public void onOpen() {
            openConnectionCount.incrementAndGet();
        }

        @OnMessage
        public void onMessage(Session session, String msg) throws IOException {

            if (started) {
                return;
            }
            synchronized (this) {
                if (started) {
                    return;
                } else {
                    started = true;
                }
            }

            System.out.println("Received " + msg + ", now sending data");

            session.getUserProperties().put(
                    org.apache.tomcat.websocket.Constants.BLOCKING_SEND_TIMEOUT_PROPERTY,
                    Long.valueOf(SEND_TIME_OUT_MILLIS));

            Basic remote = session.getBasicRemote();
            remote.setBatchingAllowed(true);

            for (int i = 0; i < MESSAGE_COUNT; i++) {
                remote.sendText(MESSAGE);
                if (i % (MESSAGE_COUNT * 0.4) == 0) {
                    remote.setBatchingAllowed(false);
                    remote.setBatchingAllowed(true);
                }
            }

            // Flushing should happen automatically on session close
            session.close();
        }

        @OnError
        public void onError(@SuppressWarnings("unused") Throwable t) {
            errorCount.incrementAndGet();
        }

        @OnClose
        public void onClose() {
            openConnectionCount.decrementAndGet();
        }
    }
}
