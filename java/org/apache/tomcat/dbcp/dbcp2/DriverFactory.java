

package org.apache.tomcat.dbcp.dbcp2;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Creates {@link Driver} instances.
 *
 * @since 2.7.0
 */
class DriverFactory {

    static Driver createDriver(final BasicDataSource basicDataSource) throws SQLException {
        // Load the JDBC driver class
        Driver driverToUse = basicDataSource.getDriver();
        String driverClassName = basicDataSource.getDriverClassName();
        ClassLoader driverClassLoader = basicDataSource.getDriverClassLoader();
        String url = basicDataSource.getUrl();

        if (driverToUse == null) {
            Class<?> driverFromCCL = null;
            if (driverClassName != null) {
                try {
                    try {
                        if (driverClassLoader == null) {
                            driverFromCCL = Class.forName(driverClassName);
                        } else {
                            driverFromCCL = Class.forName(driverClassName, true, driverClassLoader);
                        }
                    } catch (final ClassNotFoundException cnfe) {
                        driverFromCCL = Thread.currentThread().getContextClassLoader().loadClass(driverClassName);
                    }
                } catch (final Exception t) {
                    final String message = "Cannot load JDBC driver class '" + driverClassName + "'";
                    basicDataSource.log(message, t);
                    throw new SQLException(message, t);
                }
            }

            try {
                if (driverFromCCL == null) {
                    driverToUse = DriverManager.getDriver(url);
                } else {
                    // Usage of DriverManager is not possible, as it does not
                    // respect the ContextClassLoader
                    // N.B. This cast may cause ClassCastException which is
                    // handled below
                    driverToUse = (Driver) driverFromCCL.getConstructor().newInstance();
                    if (!driverToUse.acceptsURL(url)) {
                        throw new SQLException("No suitable driver", "08001");
                    }
                }
            } catch (final Exception t) {
                final String message = "Cannot create JDBC driver of class '"
                        + (driverClassName != null ? driverClassName : "") + "' for connect URL '" + url + "'";
                basicDataSource.log(message, t);
                throw new SQLException(message, t);
            }
        }
        return driverToUse;
    }

}
