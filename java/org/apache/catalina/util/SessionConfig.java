
package org.apache.catalina.util;

import javax.servlet.SessionCookieConfig;

import org.apache.catalina.Context;

public class SessionConfig {

    private static final String DEFAULT_SESSION_COOKIE_NAME = "JSESSIONID";
    private static final String DEFAULT_SESSION_PARAMETER_NAME = "jsessionid";

    /**
     * Determine the name to use for the session cookie for the provided
     * context.
     * @param context The context
     * @return the cookie name for the context
     */
    public static String getSessionCookieName(Context context) {

        String result = getConfiguredSessionCookieName(context);

        if (result == null) {
            result = DEFAULT_SESSION_COOKIE_NAME;
        }

        return result;
    }

    /**
     * Determine the name to use for the session path parameter for the provided
     * context.
     * @param context The context
     * @return the parameter name for the session
     */
    public static String getSessionUriParamName(Context context) {

        String result = getConfiguredSessionCookieName(context);

        if (result == null) {
            result = DEFAULT_SESSION_PARAMETER_NAME;
        }

        return result;
    }


    private static String getConfiguredSessionCookieName(Context context) {

        // Priority is:
        // 1. Cookie name defined in context
        // 2. Cookie name configured for app
        // 3. Default defined by spec
        if (context != null) {
            String cookieName = context.getSessionCookieName();
            if (cookieName != null && cookieName.length() > 0) {
                return cookieName;
            }

            SessionCookieConfig scc =
                context.getServletContext().getSessionCookieConfig();
            cookieName = scc.getName();
            if (cookieName != null && cookieName.length() > 0) {
                return cookieName;
            }
        }

        return null;
    }


    /**
     * Determine the value to use for the session cookie path for the provided
     * context.
     *
     * @param context The context
     * @return the parameter name for the session
     */
    public static String getSessionCookiePath(Context context) {

        SessionCookieConfig scc = context.getServletContext().getSessionCookieConfig();

        String contextPath = context.getSessionCookiePath();
        if (contextPath == null || contextPath.length() == 0) {
            contextPath = scc.getPath();
        }
        if (contextPath == null || contextPath.length() == 0) {
            contextPath = context.getEncodedPath();
        }
        if (context.getSessionCookiePathUsesTrailingSlash()) {
            // Handle special case of ROOT context where cookies require a path of
            // '/' but the servlet spec uses an empty string
            // Also ensure the cookies for a context with a path of /foo don't get
            // sent for requests with a path of /foobar
            if (!contextPath.endsWith("/")) {
                contextPath = contextPath + "/";
            }
        } else {
            // Only handle special case of ROOT context where cookies require a
            // path of '/' but the servlet spec uses an empty string
            if (contextPath.length() == 0) {
                contextPath = "/";
            }
        }

        return contextPath;
    }


    private SessionConfig() {
        // Utility class. Hide default constructor.
    }
}
