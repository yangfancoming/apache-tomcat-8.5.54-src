
package org.apache.tomcat.jdbc.test;

import org.junit.Test;

public class TestGCClose extends DefaultTestCase {

    @Test
    public void testGCStop() throws Exception {
        datasource.getConnection();
        System.out.println("Got a connection, but didn't return it");
        tearDown();
        Thread.sleep(20000);
    }

    @Test
    public void testClose() throws Exception {
        datasource.getConnection();
        System.out.println("Got a connection, but didn't return it");
        datasource.close(true);
        Thread.sleep(20000);
    }
}
