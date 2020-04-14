
package org.apache.tomcat.websocket;

import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLException;

/**
 * This is a wrapper for a {@link java.nio.channels.AsynchronousSocketChannel}
 * that limits the methods available thereby simplifying the process of
 * implementing SSL/TLS support since there are fewer methods to intercept.
 */
public interface AsyncChannelWrapper {

    Future<Integer> read(ByteBuffer dst);

    <B,A extends B> void read(ByteBuffer dst, A attachment,
            CompletionHandler<Integer,B> handler);

    Future<Integer> write(ByteBuffer src);

    <B,A extends B> void write(ByteBuffer[] srcs, int offset, int length,
            long timeout, TimeUnit unit, A attachment,
            CompletionHandler<Long,B> handler);

    void close();

    Future<Void> handshake() throws SSLException;
}
