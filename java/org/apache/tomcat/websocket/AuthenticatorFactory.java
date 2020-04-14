
package org.apache.tomcat.websocket;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Utility method to return the appropriate authenticator according to
 * the scheme that the server uses.
 */
public class AuthenticatorFactory {

    /**
     * Return a new authenticator instance.
     * @param authScheme The scheme used
     * @return the authenticator
     */
    public static Authenticator getAuthenticator(String authScheme) {

        Authenticator auth = null;
        switch (authScheme.toLowerCase()) {

        case BasicAuthenticator.schemeName:
            auth = new BasicAuthenticator();
            break;

        case DigestAuthenticator.schemeName:
            auth = new DigestAuthenticator();
            break;

        default:
            auth = loadAuthenticators(authScheme);
            break;
        }

        return auth;

    }

    private static Authenticator loadAuthenticators(String authScheme) {
        ServiceLoader<Authenticator> serviceLoader = ServiceLoader.load(Authenticator.class);
        Iterator<Authenticator> auths = serviceLoader.iterator();

        while (auths.hasNext()) {
            Authenticator auth = auths.next();
            if (auth.getSchemeName().equalsIgnoreCase(authScheme))
                return auth;
        }

        return null;
    }

}
