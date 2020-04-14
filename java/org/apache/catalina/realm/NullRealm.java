
package org.apache.catalina.realm;

import java.security.Principal;

/**
 * Minimal Realm implementation that always returns null when an attempt is made
 * to validate a user name and password. It is intended to be used as a default
 * Realm implementation when no other Realm is specified.
 */
public class NullRealm extends RealmBase {

    private static final String NAME = "NullRealm";

    @Override
    @Deprecated
    protected String getName() {
        return NAME;
    }

    @Override
    protected String getPassword(String username) {
        // Always return null
        return null;
    }

    @Override
    protected Principal getPrincipal(String username) {
        // Always return null
        return null;
    }
}
