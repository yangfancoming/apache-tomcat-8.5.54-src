
package javax.servlet;

import java.io.IOException;
import java.util.EventListener;

/**
 * TODO SERVLET3 - Add comments
 * @since Servlet 3.0
 */
public interface AsyncListener extends EventListener {
    void onComplete(AsyncEvent event) throws IOException;
    void onTimeout(AsyncEvent event) throws IOException;
    void onError(AsyncEvent event) throws IOException;
    void onStartAsync(AsyncEvent event) throws IOException;
}
