
package javax.websocket.server;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Represents the HTTP request that asked to be upgraded to WebSocket.
 */
public interface HandshakeRequest {

    static final String SEC_WEBSOCKET_KEY = "Sec-WebSocket-Key";
    static final String SEC_WEBSOCKET_PROTOCOL = "Sec-WebSocket-Protocol";
    static final String SEC_WEBSOCKET_VERSION = "Sec-WebSocket-Version";
    static final String SEC_WEBSOCKET_EXTENSIONS= "Sec-WebSocket-Extensions";

    Map<String,List<String>> getHeaders();

    Principal getUserPrincipal();

    URI getRequestURI();

    boolean isUserInRole(String role);

    /**
     * Get the HTTP Session object associated with this request. Object is used
     * to avoid a direct dependency on the Servlet API.
     * @return The javax.servlet.http.HttpSession object associated with this
     *         request, if any.
     */
    Object getHttpSession();

    Map<String, List<String>> getParameterMap();

    String getQueryString();
}
