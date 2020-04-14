
package org.apache.tomcat.websocket;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Base class for the authentication methods used by the websocket client.
 */
public abstract class Authenticator {
    private static final Pattern pattern = Pattern
            .compile("(\\w+)\\s*=\\s*(\"([^\"]+)\"|([^,=\"]+))\\s*,?");

    /**
     * Generate the authentication header that will be sent to the server.
     * @param requestUri The request URI
     * @param WWWAuthenticate The server auth challenge
     * @param UserProperties The user information
     * @return The auth header
     * @throws AuthenticationException When an error occurs
     */
    public abstract String getAuthorization(String requestUri, String WWWAuthenticate,
            Map<String, Object> UserProperties) throws AuthenticationException;

    /**
     * Get the authentication method.
     * @return the auth scheme
     */
    public abstract String getSchemeName();

    /**
     * Utility method to parse the authentication header.
     * @param WWWAuthenticate The server auth challenge
     * @return the parsed header
     */
    public Map<String, String> parseWWWAuthenticateHeader(String WWWAuthenticate) {

        Matcher m = pattern.matcher(WWWAuthenticate);
        Map<String, String> challenge = new HashMap<>();

        while (m.find()) {
            String key = m.group(1);
            String qtedValue = m.group(3);
            String value = m.group(4);

            challenge.put(key, qtedValue != null ? qtedValue : value);

        }

        return challenge;

    }

}
