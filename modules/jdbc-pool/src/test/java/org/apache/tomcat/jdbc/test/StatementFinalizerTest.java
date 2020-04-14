
package org.apache.tomcat.jdbc.test;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;

import org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;

public class StatementFinalizerTest extends DefaultTestCase {

    @Test
    public void testStatementFinalization() throws Exception {
        datasource.setJdbcInterceptors(StatementFinalizer.class.getName());
        Connection con = datasource.getConnection();
        Statement st = con.createStatement();
        Assert.assertFalse("Statement should not be closed.",st.isClosed());
        con.close();
        Assert.assertTrue("Statement should be closed.",st.isClosed());
    }


    @Test
    public void testStatementFinalizationForMultiple() throws Exception {
        datasource.setJdbcInterceptors(StatementFinalizer.class.getName());
        Connection con = datasource.getConnection();
        Statement[] statements = new Statement[1000];
        for (int i = 0; i < statements.length; i++) {
            statements[i] = con.createStatement();
        }
        for (Statement st : statements) {
            Assert.assertFalse("Statement should not be closed.", st.isClosed());
        }
        con.close();
        for (Statement st : statements) {
            Assert.assertTrue("Statement should be closed.", st.isClosed());
        }
    }
}
