
package org.apache.tomcat.jdbc.test;

import org.junit.Before;
import org.junit.Test;

import org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;

public class Bug50571 extends DefaultTestCase{

    @Before
    public void setUp() throws Exception {
        this.datasource.setUrl("jdbc:h2:~/.h2/test;QUERY_TIMEOUT=0;DB_CLOSE_ON_EXIT=FALSE");
        this.datasource.setJdbcInterceptors(ConnectionState.class.getName());
        this.datasource.setDefaultTransactionIsolation(-55);
        this.datasource.setInitialSize(1);
    }

    @Test
    public void testBug50571() throws Exception {
        this.datasource.getConnection().close();
    }
}
