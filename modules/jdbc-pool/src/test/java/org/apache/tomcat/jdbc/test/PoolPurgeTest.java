
package org.apache.tomcat.jdbc.test;

import java.sql.Connection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.jdbc.test.driver.Driver;

public class PoolPurgeTest extends DefaultTestCase {

    static final int expectedSize = 2;


    @Override
    public org.apache.tomcat.jdbc.pool.DataSource createDefaultDataSource() {
        // TODO Auto-generated method stub
        org.apache.tomcat.jdbc.pool.DataSource ds = super.createDefaultDataSource();
        ds.getPoolProperties().setDriverClassName(Driver.class.getName());
        ds.getPoolProperties().setUrl(Driver.url);
        ds.getPoolProperties().setInitialSize(expectedSize);
        ds.getPoolProperties().setMaxIdle(expectedSize);
        ds.getPoolProperties().setMinIdle(expectedSize);
        ds.getPoolProperties().setMaxActive(expectedSize);
        ds.getPoolProperties().setTimeBetweenEvictionRunsMillis(30000);
        ds.getPoolProperties().setMaxAge(Long.MAX_VALUE);
        return ds;
    }


    @Override
    @After
    public void tearDown() throws Exception {
        Driver.reset();
        super.tearDown();
    }


    @Test
    public void testPoolPurge() throws Exception {
        this.datasource.getConnection().close();
        Assert.assertEquals("Nr of connections should be "+expectedSize, expectedSize , datasource.getSize());
        this.datasource.purge();
        Assert.assertEquals("Nr of connections should be 0", 0 , datasource.getSize());
    }

    @Test
    public void testPoolPurgeWithActive() throws Exception {
        Connection con = datasource.getConnection();
        Assert.assertEquals("Nr of connections should be "+expectedSize, expectedSize , datasource.getSize());
        this.datasource.purge();
        Assert.assertEquals("Nr of connections should be "+(expectedSize-1), (expectedSize-1) , datasource.getSize());
        con.close();
        Assert.assertEquals("Nr of connections should be 0", 0 , datasource.getSize());
    }

    @Test
    public void testPoolPurgeOnReturn() throws Exception {
        init();
        Connection con = datasource.getConnection();
        Assert.assertEquals("Nr of connections should be "+expectedSize, expectedSize , datasource.getSize());
        this.datasource.purgeOnReturn();
        Assert.assertEquals("Nr of connections should be "+expectedSize, expectedSize , datasource.getSize());
        con.close();
        Assert.assertEquals("Nr of connections should be "+(expectedSize-1), (expectedSize-1) , datasource.getSize());
        tearDown();
    }
}

