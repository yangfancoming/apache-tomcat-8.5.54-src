
package org.apache.catalina.webresources;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import org.junit.Before;
import org.junit.Test;

public class TestTomcatURLStreamHandlerFactory {

    @Before
    public void register() {
        TomcatURLStreamHandlerFactory.register();
    }

    @Test
    public void testUserFactory() throws Exception {
        URLStreamHandlerFactory factory = new URLStreamHandlerFactory() {
            @Override
            public URLStreamHandler createURLStreamHandler(String protocol) {
                return null;
            }
        };
        TomcatURLStreamHandlerFactory.getInstance().addUserFactory(factory);
        TomcatURLStreamHandlerFactory.release(factory.getClass().getClassLoader());
    }
}