
package org.apache.tomcat.jdbc.test;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.jdbc.pool.DataSourceProxy;
import org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;

public class TestConnectionState extends DefaultTestCase {

    @Test
    public void testAutoCommitFalse() throws Exception {
        DataSourceProxy d1 = this.createDefaultDataSource();
        d1.setMaxActive(1);
        d1.setMinIdle(1);
        d1.setMaxIdle(1);
        d1.setJdbcInterceptors(ConnectionState.class.getName());
        d1.setDefaultAutoCommit(Boolean.FALSE);
        Connection c1 = d1.getConnection();
        Assert.assertFalse("Auto commit should be false",c1.getAutoCommit());
        c1.setAutoCommit(true);
        Assert.assertTrue("Auto commit should be true",c1.getAutoCommit());
        c1.close();
        c1 = d1.getConnection();
        Assert.assertFalse("Auto commit should be false for a reused connection",c1.getAutoCommit());
        d1.close(true);
        Assert.assertTrue("Connection should be closed",c1.isClosed());
    }

    @Test
    public void testAutoCommitTrue() throws Exception {
        DataSourceProxy d1 = this.createDefaultDataSource();
        d1.setMaxActive(1);
        d1.setJdbcInterceptors(ConnectionState.class.getName());
        d1.setDefaultAutoCommit(Boolean.TRUE);
        d1.setMinIdle(1);
        Connection c1 = d1.getConnection();
        Assert.assertTrue("Auto commit should be true",c1.getAutoCommit());
        c1.setAutoCommit(false);
        Assert.assertFalse("Auto commit should be false",c1.getAutoCommit());
        c1.close();
        c1 = d1.getConnection();
        Assert.assertTrue("Auto commit should be true for a reused connection",c1.getAutoCommit());
    }

    @Test
    public void testDefaultCatalog() throws Exception {
        DataSourceProxy d1 = this.createDefaultDataSource();
        d1.setMaxActive(1);
        d1.setJdbcInterceptors(ConnectionState.class.getName());
        d1.setDefaultCatalog("information_schema");
        d1.setMinIdle(1);
        Connection c1 = d1.getConnection();
        Assert.assertEquals("Catalog should be information_schema",c1.getCatalog(),"information_schema");
        c1.close();
        c1 = d1.getConnection();
        Assert.assertEquals("Catalog should be information_schema",c1.getCatalog(),"information_schema");
        c1.setCatalog("mysql");
        Assert.assertEquals("Catalog should be information_schema",c1.getCatalog(),"mysql");
        c1.close();
        c1 = d1.getConnection();
        Assert.assertEquals("Catalog should be information_schema",c1.getCatalog(),"information_schema");
    }
}
