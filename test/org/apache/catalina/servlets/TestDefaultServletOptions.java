
package org.apache.catalina.servlets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.Servlet;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestDefaultServletOptions extends ServletOptionsBaseTest {

    @Parameters
    public static Collection<Object[]> inputs() {
        String[] urls = new String[] { COLLECTION_NAME, FILE_NAME, UNKNOWN_NAME };
        String[] methods = new String[] { "GET", "POST", "HEAD", "TRACE", "PUT", "DELETE" };

        List<Object[]> result = new ArrayList<>();

        for (Boolean listingsValue : booleans) {
            for (Boolean readOnlyValue : booleans) {
                for (Boolean traceValue : booleans) {
                    for (String url : urls) {
                        for (String method : methods) {
                            result.add(new Object[] {
                                    listingsValue, readOnlyValue, traceValue, url, method } );
                        }
                    }
                }
            }

        }
        return result;
    }


    @Override
    protected Servlet createServlet() {
        return new DefaultServlet();
    }
}
