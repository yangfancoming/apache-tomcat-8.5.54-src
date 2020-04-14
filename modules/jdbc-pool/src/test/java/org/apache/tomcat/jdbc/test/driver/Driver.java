
package org.apache.tomcat.jdbc.test.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Driver implements java.sql.Driver {
    public static final String url = "jdbc:tomcat:test";
    public static final AtomicInteger connectCount = new AtomicInteger(0);
    public static final AtomicInteger disconnectCount = new AtomicInteger(0);

    public static void reset() {
        connectCount.set(0);
        disconnectCount.set(0);
    }

    static {
        try {
            DriverManager.registerDriver(new Driver());
        }catch (Exception x) {
            x.printStackTrace();
            throw new RuntimeException(x);
        }
    }

    public Driver() {
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url!=null && url.equals(Driver.url);
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        connectCount.addAndGet(1);
        return new org.apache.tomcat.jdbc.test.driver.Connection(info);
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return null;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }


}
