
package javax.servlet.http;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;

/**
 * The interface used by a {@link HttpUpgradeHandler} to interact with an upgraded
 * HTTP connection.
 *
 * @since Servlet 3.1
 */
public interface WebConnection extends AutoCloseable {

    /**
     * Provides access to the {@link ServletInputStream} for reading data from
     * the client.
     *
     * @return the input stream
     *
     * @throws IOException If an I/O occurs while obtaining the stream
     */
    ServletInputStream getInputStream() throws IOException;

    /**
     * Provides access to the {@link ServletOutputStream} for writing data to
     * the client.
     *
     * @return the output stream
     *
     * @throws IOException If an I/O occurs while obtaining the stream
     */
    ServletOutputStream getOutputStream() throws IOException;
}