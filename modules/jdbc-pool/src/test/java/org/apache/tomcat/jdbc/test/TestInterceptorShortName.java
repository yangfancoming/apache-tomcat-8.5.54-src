
package org.apache.tomcat.jdbc.test;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.jdbc.pool.interceptor.TestInterceptor;

public class TestInterceptorShortName extends DefaultTestCase {

    @Test
    public void testShortInterceptor() throws Exception {
        this.datasource = this.createDefaultDataSource();
        this.datasource.setJdbcInterceptors("TestInterceptor");
        this.datasource.setUseDisposableConnectionFacade(false);
        this.datasource.setMaxActive(1);
        this.datasource.createPool();
        Assert.assertEquals("Only one interceptor should have been called setProperties[1]",1,TestInterceptor.instancecount.get());
        TestInterceptor.instancecount.set(0);
        Connection con = this.datasource.getConnection();
        Assert.assertTrue("Pool should have been started.",TestInterceptor.poolstarted);
        Assert.assertEquals("Only one interceptor should have been called setProperties[2]",1,TestInterceptor.instancecount.get());
        con.close();
        this.datasource.close();
        Assert.assertTrue("Pool should have been closed.",TestInterceptor.poolclosed);
    }
}
