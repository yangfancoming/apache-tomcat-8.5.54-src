
package websocket.echo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.websocket.OnMessage;
import javax.websocket.PongMessage;
import javax.websocket.Session;

/**
 * The three annotated echo endpoints can be used to test with Autobahn and
 * the following command "wstest -m fuzzingclient -s servers.json". See the
 * Autobahn documentation for setup and general information.
 *
 * Note: This one is disabled by default since it allocates memory, and needs
 * to be enabled back.
 */
//@javax.websocket.server.ServerEndpoint("/websocket/echoAsyncAnnotation")
public class EchoAsyncAnnotation {

    private static final Future<Void> COMPLETED = new CompletedFuture();

    Future<Void> f = COMPLETED;
    StringBuilder sb = null;
    ByteArrayOutputStream bytes = null;

    @OnMessage
    public void echoTextMessage(Session session, String msg, boolean last) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        sb.append(msg);
        if (last) {
            // Before we send the next message, have to wait for the previous
            // message to complete
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                // Let the container deal with it
                throw new RuntimeException(e);
            }
            f = session.getAsyncRemote().sendText(sb.toString());
            sb = null;
        }
    }

    @OnMessage
    public void echoBinaryMessage(byte[] msg, Session session, boolean last)
            throws IOException {
        if (bytes == null) {
            bytes = new ByteArrayOutputStream();
        }
        bytes.write(msg);
        if (last) {
            // Before we send the next message, have to wait for the previous
            // message to complete
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                // Let the container deal with it
                throw new RuntimeException(e);
            }
            f = session.getAsyncRemote().sendBinary(ByteBuffer.wrap(bytes.toByteArray()));
            bytes = null;
        }
    }

    /**
     * Process a received pong. This is a NO-OP.
     *
     * @param pm    Ignored.
     */
    @OnMessage
    public void echoPongMessage(PongMessage pm) {
        // NO-OP
    }

    private static class CompletedFuture implements Future<Void> {

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public Void get() throws InterruptedException, ExecutionException {
            return null;
        }

        @Override
        public Void get(long timeout, TimeUnit unit)
                throws InterruptedException, ExecutionException,
                TimeoutException {
            return null;
        }
    }
}
