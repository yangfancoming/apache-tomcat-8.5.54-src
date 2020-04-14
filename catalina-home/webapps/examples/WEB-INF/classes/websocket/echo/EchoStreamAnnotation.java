
package websocket.echo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import javax.websocket.OnMessage;
import javax.websocket.PongMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * The three annotated echo endpoints can be used to test with Autobahn and
 * the following command "wstest -m fuzzingclient -s servers.json". See the
 * Autobahn documentation for setup and general information.
 */
@ServerEndpoint("/websocket/echoStreamAnnotation")
public class EchoStreamAnnotation {

    Writer writer;
    OutputStream stream;

    @OnMessage
    public void echoTextMessage(Session session, String msg, boolean last)
            throws IOException {
        if (writer == null) {
            writer = session.getBasicRemote().getSendWriter();
        }
        writer.write(msg);
        if (last) {
            writer.close();
            writer = null;
        }
    }

    @OnMessage
    public void echoBinaryMessage(byte[] msg, Session session, boolean last)
            throws IOException {
        if (stream == null) {
            stream = session.getBasicRemote().getSendStream();
        }
        stream.write(msg);
        stream.flush();
        if (last) {
            stream.close();
            stream = null;
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
}
