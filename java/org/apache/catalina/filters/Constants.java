
package org.apache.catalina.filters;


/**
 * Manifest constants for this Java package.
 *
 *
 * @author Craig R. McClanahan
 */
public final class Constants {

    /**
     * The session attribute key under which the CSRF nonce
     * cache will be stored.
     */
    public static final String CSRF_NONCE_SESSION_ATTR_NAME =
        "org.apache.catalina.filters.CSRF_NONCE";

    /**
     * The request attribute key under which the current
     * requests's CSRF nonce can be found.
     */
    public static final String CSRF_NONCE_REQUEST_ATTR_NAME =
        "org.apache.catalina.filters.CSRF_REQUEST_NONCE";

    /**
     * The name of the request parameter which carries CSRF nonces
     * from the client to the server for validation.
     */
    public static final String CSRF_NONCE_REQUEST_PARAM =
        "org.apache.catalina.filters.CSRF_NONCE";

    /**
     * The servlet context attribute key under which the
     * CSRF request parameter name can be found.
     */
    public static final String CSRF_NONCE_REQUEST_PARAM_NAME_KEY =
        "org.apache.catalina.filters.CSRF_NONCE_PARAM_NAME";

    public static final String METHOD_GET = "GET";

    public static final String CSRF_REST_NONCE_HEADER_NAME = "X-CSRF-Token";

    public static final String CSRF_REST_NONCE_HEADER_FETCH_VALUE = "Fetch";

    public static final String CSRF_REST_NONCE_HEADER_REQUIRED_VALUE = "Required";

    /**
     * The session attribute key under which the CSRF REST nonce
     * cache will be stored.
     */
    public static final String CSRF_REST_NONCE_SESSION_ATTR_NAME =
        "org.apache.catalina.filters.CSRF_REST_NONCE";

    /**
     * The servlet context attribute key under which the
     * CSRF REST header name can be found.
     */
    public static final String CSRF_REST_NONCE_HEADER_NAME_KEY =
        "org.apache.catalina.filters.CSRF_REST_NONCE_HEADER_NAME";
}
