
package org.apache.tomcat.jdbc.test;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.jdbc.pool.interceptor.QueryTimeoutInterceptor;
import org.apache.tomcat.jdbc.test.driver.Driver;

public class TestQueryTimeoutInterceptor extends DefaultTestCase {

    @Test
    public void testTimeout() throws Exception {
        int timeout = 10;
        int withoutuser =10;
        int withuser = withoutuser;
        this.datasource.setMaxActive(withuser+withoutuser);
        this.datasource.setJdbcInterceptors(QueryTimeoutInterceptor.class.getName()+"(queryTimeout="+timeout+")");
        this.datasource.setDriverClassName(Driver.class.getName());
        this.datasource.setUrl("jdbc:tomcat:test");
        Connection con = this.datasource.getConnection();
        Statement st = con.createStatement();
        Assert.assertEquals(st.getClass().getName(),timeout,st.getQueryTimeout());
        st.close();
        st = con.prepareStatement("");
        Assert.assertEquals(st.getClass().getName(),timeout,st.getQueryTimeout());
        st.close();
        st = con.prepareCall("");
        Assert.assertEquals(st.getClass().getName(),timeout,st.getQueryTimeout());
        st.close();
        con.close();
    }
}
