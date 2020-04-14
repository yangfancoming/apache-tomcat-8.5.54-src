
package org.apache.tomcat.jdbc.test;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer;

public class AbandonPercentageTest extends DefaultTestCase {

    @Test
    public void testDefaultAbandon() throws Exception {
        this.datasource.setMaxActive(100);
        this.datasource.setMaxIdle(100);
        this.datasource.setInitialSize(0);
        this.datasource.getPoolProperties().setAbandonWhenPercentageFull(0);
        this.datasource.getPoolProperties().setTimeBetweenEvictionRunsMillis(100);
        this.datasource.getPoolProperties().setRemoveAbandoned(true);
        this.datasource.getPoolProperties().setRemoveAbandonedTimeout(1);
        try (Connection con = datasource.getConnection()) {
            Assert.assertEquals("Number of connections active/busy should be 1",1,datasource.getPool().getActive());
            Thread.sleep(2000);
            Assert.assertEquals("Number of connections active/busy should be 0",0,datasource.getPool().getActive());
        }
    }

    @Test
    public void testMaxedOutAbandon() throws Exception {
        int size = 100;
        this.datasource.setMaxActive(size);
        this.datasource.setMaxIdle(size);
        this.datasource.setInitialSize(0);
        this.datasource.getPoolProperties().setAbandonWhenPercentageFull(100);
        this.datasource.getPoolProperties().setTimeBetweenEvictionRunsMillis(100);
        this.datasource.getPoolProperties().setRemoveAbandoned(true);
        this.datasource.getPoolProperties().setRemoveAbandonedTimeout(1);
        try (Connection con = datasource.getConnection()) {
            Assert.assertEquals("Number of connections active/busy should be 1",1,datasource.getPool().getActive());
            Thread.sleep(2000);
            Assert.assertEquals("Number of connections active/busy should be 1",1,datasource.getPool().getActive());
        }
    }

    @Test
    public void testResetConnection() throws Exception {
        int size = 1;
        this.datasource.setMaxActive(size);
        this.datasource.setMaxIdle(size);
        this.datasource.setInitialSize(0);
        this.datasource.getPoolProperties().setAbandonWhenPercentageFull(100);
        this.datasource.getPoolProperties().setTimeBetweenEvictionRunsMillis(100);
        this.datasource.getPoolProperties().setRemoveAbandoned(true);
        this.datasource.getPoolProperties().setRemoveAbandonedTimeout(1);
        this.datasource.getPoolProperties().setJdbcInterceptors(ResetAbandonedTimer.class.getName());
        try (Connection con = datasource.getConnection()) {
            Assert.assertEquals("Number of connections active/busy should be 1",1,datasource.getPool().getActive());
            for (int i=0; i<20; i++) {
                Thread.sleep(200);
                // This call is here to ensure the pool thinks the connection
                // is being used.
                con.isClosed();
            }
            Assert.assertEquals("Number of connections active/busy should be 1",1,datasource.getPool().getActive());
        }
    }

    @Test
    public void testHalfway() throws Exception {
        int size = 100;
        this.datasource.setMaxActive(size);
        this.datasource.setMaxIdle(size);
        this.datasource.setInitialSize(0);
        this.datasource.getPoolProperties().setAbandonWhenPercentageFull(50);
        this.datasource.getPoolProperties().setTimeBetweenEvictionRunsMillis(500);
        this.datasource.getPoolProperties().setRemoveAbandoned(true);
        this.datasource.getPoolProperties().setRemoveAbandonedTimeout(1);
        Connection[] con = new Connection[size];
        con[0] = datasource.getConnection();
        Assert.assertEquals("Number of connections active/busy should be 1",1,datasource.getPool().getActive());
        for (int i=1; i<25; i++) {
            con[i] = datasource.getConnection();
        }
        Assert.assertEquals("Number of connections active/busy should be 25",25,datasource.getPool().getActive());
        Thread.sleep(2500);
        Assert.assertEquals("Number of connections active/busy should be 25",25,datasource.getPool().getActive());
        this.datasource.getPoolProperties().setRemoveAbandonedTimeout(100);
        for (int i=25; i<con.length; i++) {
            con[i] = datasource.getConnection();
        }
        int active = datasource.getPool().getActive();
        System.out.println("Active:"+active);
        Assert.assertEquals("Number of connections active/busy should be "+con.length,con.length,datasource.getPool().getActive());
        this.datasource.getPoolProperties().setRemoveAbandonedTimeout(1);
        Thread.sleep(2500);
        Assert.assertTrue("Number of connections should be less than 50.", (datasource.getPool().getActive()<50));
        this.datasource.getPoolProperties().setAbandonWhenPercentageFull(0);
        Thread.sleep(2500);
        Assert.assertEquals("Number of connections active/busy should be "+0,0,datasource.getPool().getActive());
    }
}
