

package org.apache.tomcat.dbcp.dbcp2.datasources;

import javax.sql.PooledConnection;

import org.apache.tomcat.dbcp.dbcp2.Utils;

/**
 * Immutable poolable object holding a PooledConnection along with the user name and password used to create the
 * connection.
 *
 * @since 2.0
 */
final class PooledConnectionAndInfo {
    private final PooledConnection pooledConnection;
    private final char[] userPassword;
    private final String userName;
    private final UserPassKey upKey;

    /**
     * @since 2.4.0
     */
    PooledConnectionAndInfo(final PooledConnection pc, final String userName, final char[] userPassword) {
        this.pooledConnection = pc;
        this.userName = userName;
        this.userPassword = userPassword;
        this.upKey = new UserPassKey(userName, userPassword);
    }

    /**
     * @deprecated Since 2.4.0
     */
    @Deprecated
    PooledConnectionAndInfo(final PooledConnection pc, final String userName, final String userPassword) {
        this(pc, userName, Utils.toCharArray(userPassword));
    }

    PooledConnection getPooledConnection() {
        return pooledConnection;
    }

    UserPassKey getUserPassKey() {
        return upKey;
    }

    /**
     * Gets the value of password.
     *
     * @return value of password.
     */
    String getPassword() {
        return Utils.toString(userPassword);
    }

    /**
     * Gets the value of password.
     *
     * @return value of password.
     * @since 2.4.0
     */
    char[] getPasswordCharArray() {
        return userPassword;
    }

    /**
     * Gets the value of userName.
     *
     * @return value of userName.
     */
    String getUsername() {
        return userName;
    }
}
