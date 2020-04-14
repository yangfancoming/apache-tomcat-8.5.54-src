
package org.apache.tomcat.util.http;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import org.apache.catalina.connector.Request;

@RunWith(Parameterized.class)
public class TestRequestUtilSameOrigin {

    @Parameterized.Parameters(name = "{index}: request[{0}], origin[{1}]")
    public static Collection<Object[]> parameters() {
        List<Object[]> parameterSets = new ArrayList<>();

        TesterRequest request1 = new TesterRequest("http", "example.com", 80);
        TesterRequest request2 = new TesterRequest("ws", "example.com", 80);
        TesterRequest request3 = new TesterRequest("http", "example.com", 443);
        TesterRequest request4 = new TesterRequest("http", "example.com", 8080);

        parameterSets.add(new Object[] { request1, "http://example.com", Boolean.TRUE });
        parameterSets.add(new Object[] { request1, "http://example.com:80", Boolean.TRUE });
        parameterSets.add(new Object[] { request1, "http://example.com:8080", Boolean.FALSE});

        parameterSets.add(new Object[] { request2, "ws://example.com", Boolean.TRUE });
        parameterSets.add(new Object[] { request2, "ws://example.com:80", Boolean.TRUE });
        parameterSets.add(new Object[] { request2, "ws://example.com:8080", Boolean.FALSE});

        parameterSets.add(new Object[] { request3, "http://example.com", Boolean.FALSE });
        parameterSets.add(new Object[] { request3, "http://example.com:80", Boolean.FALSE });
        parameterSets.add(new Object[] { request3, "http://example.com:443", Boolean.TRUE});

        parameterSets.add(new Object[] { request4, "http://example.com", Boolean.FALSE });
        parameterSets.add(new Object[] { request4, "http://example.com:80", Boolean.FALSE });
        parameterSets.add(new Object[] { request4, "http://example.com:8080", Boolean.TRUE});

        return parameterSets;
    }


    @Parameter(0)
    public HttpServletRequest request;
    @Parameter(1)
    public String origin;
    @Parameter(2)
    public Boolean same;


    @Test
    public void testSameOrigin() {
        Assert.assertEquals(same, Boolean.valueOf(RequestUtil.isSameOrigin(request, origin)));
    }


    private static class TesterRequest extends HttpServletRequestWrapper {

        private final String scheme;
        private final String host;
        private final int port;

        public TesterRequest(String scheme, String host, int port) {
            super(new Request());
            this.scheme = scheme;
            this.host = host;
            this.port = port;
        }

        @Override
        public String getScheme() {
            return scheme;
        }

        @Override
        public String getServerName() {
            return host;
        }

        @Override
        public int getServerPort() {
            return port;
        }

        @Override
        public String toString() {
            return scheme + "://" + host + ":" + port;
        }
    }
}
