
package org.apache.catalina.startup;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.RealmBase;

/**
 * Simple Realm that uses a configurable {@link Map} to link user names and
 * passwords.
 */
public final class TesterMapRealm extends RealmBase {
    private Map<String,String> users = new HashMap<>();
    private Map<String,List<String>> roles = new HashMap<>();

    public void addUser(String username, String password) {
        users.put(username, password);
    }

    public void addUserRole(String username, String role) {
        List<String> userRoles = roles.get(username);
        if (userRoles == null) {
            userRoles = new ArrayList<>();
            roles.put(username, userRoles);
        }
        userRoles.add(role);
    }

    @Override
    @Deprecated
    protected String getName() {
        return "MapRealm";
    }

    @Override
    protected String getPassword(String username) {
        return users.get(username);
    }

    @Override
    protected Principal getPrincipal(String username) {
        return new GenericPrincipal(username, getPassword(username),
                roles.get(username));
    }

}